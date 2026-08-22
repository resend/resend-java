package com.resend.services.emails;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.ListParams;
import com.resend.core.net.RequestOptions;
import com.resend.services.emails.model.*;
import com.resend.services.util.EmailsUtil;
import java.util.Collections;
import okhttp3.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class EmailsTest {

    private static final String EMAIL_ID = "qwert";
    private static final String UPDATE_EMAIL_ID = "123";
    private static final String ATTACHMENT_ID = "2a0c9ce0-3112-4728-976e-47ddcd16a318";
    private static final String ATTACHMENT_EMAIL_ID = "4ef9a417-02e9-4d39-ad75-9611e0fcc33c";

    private static final String SEND_RESPONSE_JSON = "{\"id\":\"mock_id\"}";

    private static final String GET_EMAIL_JSON =
            "{\"object\":\"email_object\",\"id\":\"" + EMAIL_ID + "\"," +
            "\"from\":\"sender@example.com\",\"to\":[\"recipient@example.com\"]," +
            "\"created_at\":\"2023-04-08 00:11:13.110779+00\"," +
            "\"subject\":\"Test Email Subject\"," +
            "\"html\":\"<html><body>This is the HTML content</body></html>\"," +
            "\"text\":\"This is the plain text content\"," +
            "\"bcc\":[\"bcc@example.com\"],\"cc\":[\"cc@example.com\"]," +
            "\"reply_to\":[\"replyto@example.com\"],\"last_event\":\"last_event_status\"," +
            "\"message_id\":\"111-222-333@email.example.com\"}";

    private static final String UPDATE_RESPONSE_JSON = "{\"id\":\"" + UPDATE_EMAIL_ID + "\",\"object\":\"emails\"}";

    private static final String CANCEL_RESPONSE_JSON = "{\"id\":\"" + UPDATE_EMAIL_ID + "\",\"object\":\"emails\"}";

    private static final String SHARE_RESPONSE_JSON =
            "{\"object\":\"email\",\"id\":\"" + UPDATE_EMAIL_ID + "\",\"url\":\"https://resend.com/share/abc123\"}";

    private static final String LIST_RESPONSE_JSON =
            "{\"object\":\"emails\",\"has_more\":true,\"data\":[" +
            "{\"id\":\"email_1\",\"from\":\"sender1@example.com\"}," +
            "{\"id\":\"email_2\",\"from\":\"sender2@example.com\"}," +
            "{\"id\":\"email_3\",\"from\":\"sender3@example.com\"}" +
            "]}";

    private static final String ATTACHMENT_RESPONSE_JSON =
            "{\"object\":\"attachment\",\"id\":\"" + ATTACHMENT_ID + "\"," +
            "\"filename\":\"avatar.png\",\"size\":4096,\"content_type\":\"image/png\"," +
            "\"download_url\":\"https://outbound-cdn.resend.com/attachments/" + ATTACHMENT_ID + "\"}";

    private static final String LIST_ATTACHMENTS_JSON =
            "{\"object\":\"list\",\"has_more\":false,\"data\":[" +
            "{\"object\":\"attachment\",\"id\":\"" + ATTACHMENT_ID + "\",\"filename\":\"avatar.png\",\"size\":4096,\"content_type\":\"image/png\"}," +
            "{\"object\":\"attachment\",\"id\":\"3b0d9ce0-4223-5839-087f-58eede27b429\",\"filename\":\"invoice.pdf\",\"size\":8192,\"content_type\":\"application/pdf\"}" +
            "]}";

    private static final String METRICS_TOTALS_ONLY_JSON =
            "{\"object\":\"metrics\",\"start_date\":\"2026-07-01T00:00:00.000Z\"," +
            "\"end_date\":\"2026-07-08T00:00:00.000Z\",\"metrics\":[\"delivered\",\"opened\"]," +
            "\"dimensions\":[],\"granularity\":\"daily\"," +
            "\"totals\":{\"delivered\":100,\"opened\":40}}";

    private static final String METRICS_WITH_PERIOD_JSON =
            "{\"object\":\"metrics\",\"start_date\":\"2026-07-01T00:00:00.000Z\"," +
            "\"end_date\":\"2026-07-08T00:00:00.000Z\",\"metrics\":[\"delivered\"]," +
            "\"dimensions\":[\"period\"],\"granularity\":\"daily\"," +
            "\"totals\":{\"delivered\":10},\"data\":[" +
            "{\"period\":\"2026-07-01\",\"delivered\":10}]}";

    private static final String METRICS_WITH_DOMAIN_JSON =
            "{\"object\":\"metrics\",\"start_date\":\"2026-07-01T00:00:00.000Z\"," +
            "\"end_date\":\"2026-07-08T00:00:00.000Z\",\"metrics\":[\"delivered\"]," +
            "\"dimensions\":[\"domain\"],\"granularity\":\"daily\"," +
            "\"totals\":{\"delivered\":10},\"data\":[" +
            "{\"domain_id\":\"d1\",\"domain_name\":\"example.com\",\"delivered\":10}]}";

    private static final String METRICS_WITH_EMAIL_JSON =
            "{\"object\":\"metrics\",\"start_date\":\"2026-07-01T00:00:00.000Z\"," +
            "\"end_date\":\"2026-07-08T00:00:00.000Z\",\"metrics\":[\"delivered\"]," +
            "\"dimensions\":[\"email\"],\"granularity\":\"daily\"," +
            "\"totals\":{\"delivered\":10},\"data\":[" +
            "{\"email_id\":\"e1\",\"delivered\":10}]}";

    private static final String METRICS_WITH_BROADCAST_JSON =
            "{\"object\":\"metrics\",\"start_date\":\"2026-07-01T00:00:00.000Z\"," +
            "\"end_date\":\"2026-07-08T00:00:00.000Z\",\"metrics\":[\"delivered\",\"opened\"]," +
            "\"dimensions\":[\"period\",\"broadcast\"],\"granularity\":\"daily\"," +
            "\"totals\":{\"delivered\":100,\"opened\":40},\"data\":[" +
            "{\"period\":\"2026-07-01\",\"broadcast_id\":\"uuid\",\"broadcast_name\":\"July Newsletter\"," +
            "\"delivered\":10,\"opened\":4}]}";

    @Mock
    private IHttpClient httpClient;

    private Emails emails;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        emails = new Emails("test-api-key", httpClient);
    }

    @Test
    public void testSendEmail_Success() throws ResendException {
        CreateEmailOptions createEmailOptions = EmailsUtil.createEmailOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, SEND_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateEmailResponse response = emails.send(createEmailOptions);

        assertNotNull(response);
        assertEquals("mock_id", response.getId());
    }

    @Test
    public void testSendEmail_ApiError_ThrowsResendException() throws ResendException {
        CreateEmailOptions createEmailOptions = EmailsUtil.createEmailOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"Invalid recipient\"}", false);

        when(httpClient.perform(eq("/emails"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> emails.send(createEmailOptions));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testSendEmail_WithIdempotencyKey_Success() throws ResendException {
        CreateEmailOptions createEmailOptions = EmailsUtil.createEmailOptions();
        RequestOptions requestOptions = EmailsUtil.createRequestOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, SEND_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class), any(RequestOptions.class)))
                .thenReturn(httpResponse);

        CreateEmailResponse response = emails.send(createEmailOptions, requestOptions);

        assertNotNull(response);
        assertEquals("mock_id", response.getId());
    }

    @Test
    public void testRetrieveEmail_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, GET_EMAIL_JSON, true);

        when(httpClient.perform(eq("/emails/" + EMAIL_ID), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        Email response = emails.get(EMAIL_ID);

        assertNotNull(response);
        assertEquals(EMAIL_ID, response.getId());
        assertEquals("sender@example.com", response.getFrom());
        assertEquals("111-222-333@email.example.com", response.getMessageId());
    }

    @Test
    public void testUpdateEmail_Success() throws ResendException {
        UpdateEmailOptions updateEmailOptions = EmailsUtil.updateEmailOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, UPDATE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails/" + UPDATE_EMAIL_ID), anyString(), eq(HttpMethod.PATCH), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        UpdateEmailResponse response = emails.update(UPDATE_EMAIL_ID, updateEmailOptions);

        assertNotNull(response);
        assertEquals(UPDATE_EMAIL_ID, response.getId());
    }

    @Test
    public void testCancelEmail_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, CANCEL_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails/" + UPDATE_EMAIL_ID + "/cancel"), anyString(), eq(HttpMethod.POST), eq(""), any(MediaType.class)))
                .thenReturn(httpResponse);

        CancelEmailResponse response = emails.cancel(UPDATE_EMAIL_ID);

        assertNotNull(response);
        assertEquals(UPDATE_EMAIL_ID, response.getId());
    }

    @Test
    public void testShareEmail_DefaultExpiresIn_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, SHARE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails/" + UPDATE_EMAIL_ID + "/share"), anyString(), eq(HttpMethod.POST), eq(""), any(MediaType.class)))
                .thenReturn(httpResponse);

        ShareEmailResponse response = emails.share(UPDATE_EMAIL_ID);

        assertNotNull(response);
        assertEquals(UPDATE_EMAIL_ID, response.getId());
        assertEquals("https://resend.com/share/abc123", response.getUrl());
    }

    @Test
    public void testShareEmail_CustomExpiresIn_Success() throws ResendException {
        ShareEmailOptions shareEmailOptions = EmailsUtil.shareEmailOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, SHARE_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails/" + UPDATE_EMAIL_ID + "/share"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ShareEmailResponse response = emails.share(UPDATE_EMAIL_ID, shareEmailOptions);

        assertNotNull(response);
        assertEquals(UPDATE_EMAIL_ID, response.getId());
        assertEquals("https://resend.com/share/abc123", response.getUrl());
    }

    @Test
    public void testShareEmail_InvalidExpiresIn_ThrowsResendException() throws ResendException {
        ShareEmailOptions shareEmailOptions = ShareEmailOptions.builder().expiresIn("72h").build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"expires_in exceeds the 48 hour maximum\"}", false);

        when(httpClient.perform(eq("/emails/" + UPDATE_EMAIL_ID + "/share"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> emails.share(UPDATE_EMAIL_ID, shareEmailOptions));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testShareEmail_NotFound_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(404,
                "{\"name\":\"not_found\",\"message\":\"Email not found\"}", false);

        when(httpClient.perform(eq("/emails/" + EMAIL_ID + "/share"), anyString(), eq(HttpMethod.POST), eq(""), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> emails.share(EMAIL_ID));
        assertEquals(404, (int) ex.getStatusCode());
    }

    @Test
    public void testListEmails_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListEmailsResponseSuccess response = emails.list();

        assertNotNull(response);
        assertEquals(3, response.getData().size());
    }

    @Test
    public void testListEmailsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(3).build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_RESPONSE_JSON, true);

        when(httpClient.perform(startsWith("/emails?"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListEmailsResponseSuccess response = emails.list(params);

        assertNotNull(response);
        assertEquals(3, response.getData().size());
    }

    @Test
    public void testGetAttachment_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, ATTACHMENT_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails/" + ATTACHMENT_EMAIL_ID + "/attachments/" + ATTACHMENT_ID),
                anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        AttachmentResponse response = emails.getAttachment(ATTACHMENT_EMAIL_ID, ATTACHMENT_ID);

        assertNotNull(response);
        assertEquals(ATTACHMENT_ID, response.getId());
        assertEquals("avatar.png", response.getFilename());
        assertEquals(4096, response.getSize());
    }

    @Test
    public void testListAttachments_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_ATTACHMENTS_JSON, true);

        when(httpClient.perform(eq("/emails/" + ATTACHMENT_EMAIL_ID + "/attachments"),
                anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListAttachmentsResponse response = emails.listAttachments(ATTACHMENT_EMAIL_ID);

        assertNotNull(response);
        assertEquals(2, response.getData().size());
        assertEquals("list", response.getObject());
    }

    @Test
    public void testListAttachmentsWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder().limit(10).build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, LIST_ATTACHMENTS_JSON, true);

        when(httpClient.perform(startsWith("/emails/" + ATTACHMENT_EMAIL_ID + "/attachments?"),
                anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ListAttachmentsResponse response = emails.listAttachments(ATTACHMENT_EMAIL_ID, params);

        assertNotNull(response);
        assertEquals(2, response.getData().size());
    }

    @Test
    public void testSendEmail_WithTemplate_Success() throws ResendException {
        Template template = Template.builder()
                .id("template_123")
                .addVariable("firstName", "John")
                .build();

        CreateEmailOptions emailWithTemplate = CreateEmailOptions.builder()
                .from("Acme <onboarding@resend.dev>")
                .to("john.doe@example.com")
                .subject("Welcome John!")
                .template(template)
                .build();

        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, SEND_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateEmailResponse response = emails.send(emailWithTemplate);

        assertNotNull(response);
        assertEquals("mock_id", response.getId());
    }

    @Test
    public void testGetEmailsMetrics_NoOptions_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, METRICS_TOTALS_ONLY_JSON, true);

        when(httpClient.perform(eq("/emails/metrics"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        EmailsMetricsResponse response = emails.metrics();

        assertNotNull(response);
        assertEquals("metrics", response.getObject());
        assertEquals("2026-07-01T00:00:00.000Z", response.getStartDate());
        assertEquals("2026-07-08T00:00:00.000Z", response.getEndDate());
        assertEquals(MetricsGranularity.DAILY, response.getGranularity());
        assertEquals(2, response.getMetrics().size());
        assertTrue(response.getDimensions().isEmpty());
        assertNull(response.getData());
        assertEquals(100, response.getTotals().get("delivered"));
        assertEquals(40, response.getTotals().get("opened"));
    }

    @Test
    public void testGetEmailsMetrics_NullOptions_Success() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, METRICS_TOTALS_ONLY_JSON, true);

        when(httpClient.perform(eq("/emails/metrics"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        EmailsMetricsResponse response = emails.metrics(null);

        assertNotNull(response);
        assertEquals("metrics", response.getObject());
    }

    @Test
    public void testGetEmailsMetrics_DimensionPeriod_Success() throws ResendException {
        GetEmailsMetricsOptions options = GetEmailsMetricsOptions.builder()
                .dimensions(MetricsDimension.PERIOD)
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, METRICS_WITH_PERIOD_JSON, true);

        when(httpClient.perform(eq("/emails/metrics?dimensions=period"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        EmailsMetricsResponse response = emails.metrics(options);

        assertNotNull(response);
        assertEquals(1, response.getData().size());
        assertEquals("2026-07-01", response.getData().get(0).getPeriod());
        assertEquals(10, response.getData().get(0).getMetrics().get("delivered"));
    }

    @Test
    public void testGetEmailsMetrics_DimensionDomain_Success() throws ResendException {
        GetEmailsMetricsOptions options = GetEmailsMetricsOptions.builder()
                .dimensions(MetricsDimension.DOMAIN)
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, METRICS_WITH_DOMAIN_JSON, true);

        when(httpClient.perform(eq("/emails/metrics?dimensions=domain"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        EmailsMetricsResponse response = emails.metrics(options);

        assertNotNull(response);
        assertEquals("d1", response.getData().get(0).getDomainId());
        assertEquals("example.com", response.getData().get(0).getDomainName());
    }

    @Test
    public void testGetEmailsMetrics_DimensionEmail_Success() throws ResendException {
        GetEmailsMetricsOptions options = GetEmailsMetricsOptions.builder()
                .dimensions(MetricsDimension.EMAIL)
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, METRICS_WITH_EMAIL_JSON, true);

        when(httpClient.perform(eq("/emails/metrics?dimensions=email"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        EmailsMetricsResponse response = emails.metrics(options);

        assertNotNull(response);
        assertEquals("e1", response.getData().get(0).getEmailId());
    }

    @Test
    public void testGetEmailsMetrics_DimensionBroadcast_Success() throws ResendException {
        GetEmailsMetricsOptions options = GetEmailsMetricsOptions.builder()
                .dimensions(MetricsDimension.PERIOD, MetricsDimension.BROADCAST)
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, METRICS_WITH_BROADCAST_JSON, true);

        when(httpClient.perform(eq("/emails/metrics?dimensions=period%2Cbroadcast"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        EmailsMetricsResponse response = emails.metrics(options);

        assertNotNull(response);
        assertEquals("2026-07-01", response.getData().get(0).getPeriod());
        assertEquals("uuid", response.getData().get(0).getBroadcastId());
        assertEquals("July Newsletter", response.getData().get(0).getBroadcastName());
        assertEquals(10, response.getData().get(0).getMetrics().get("delivered"));
        assertEquals(4, response.getData().get(0).getMetrics().get("opened"));
    }

    @Test
    public void testGetEmailsMetrics_DomainIdFilter_SingleValue_Success() throws ResendException {
        GetEmailsMetricsOptions options = GetEmailsMetricsOptions.builder()
                .domainIds("d1")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, METRICS_TOTALS_ONLY_JSON, true);

        when(httpClient.perform(eq("/emails/metrics?domain_id=d1"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        EmailsMetricsResponse response = emails.metrics(options);

        assertNotNull(response);
    }

    @Test
    public void testGetEmailsMetrics_DomainIdFilter_MultipleValues_Success() throws ResendException {
        GetEmailsMetricsOptions options = GetEmailsMetricsOptions.builder()
                .domainIds("d1", "d2")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, METRICS_TOTALS_ONLY_JSON, true);

        when(httpClient.perform(eq("/emails/metrics?domain_id=d1%2Cd2"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        EmailsMetricsResponse response = emails.metrics(options);

        assertNotNull(response);
    }

    @Test
    public void testGetEmailsMetrics_EmailIdFilter_SingleValue_Success() throws ResendException {
        GetEmailsMetricsOptions options = GetEmailsMetricsOptions.builder()
                .emailIds("e1")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, METRICS_TOTALS_ONLY_JSON, true);

        when(httpClient.perform(eq("/emails/metrics?email_id=e1"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        EmailsMetricsResponse response = emails.metrics(options);

        assertNotNull(response);
    }

    @Test
    public void testGetEmailsMetrics_EmailIdFilter_MultipleValues_Success() throws ResendException {
        GetEmailsMetricsOptions options = GetEmailsMetricsOptions.builder()
                .emailIds("e1", "e2")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, METRICS_TOTALS_ONLY_JSON, true);

        when(httpClient.perform(eq("/emails/metrics?email_id=e1%2Ce2"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        EmailsMetricsResponse response = emails.metrics(options);

        assertNotNull(response);
    }

    @Test
    public void testGetEmailsMetrics_BroadcastIdFilter_SingleValue_Success() throws ResendException {
        GetEmailsMetricsOptions options = GetEmailsMetricsOptions.builder()
                .broadcastIds("b1")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, METRICS_TOTALS_ONLY_JSON, true);

        when(httpClient.perform(eq("/emails/metrics?broadcast_id=b1"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        EmailsMetricsResponse response = emails.metrics(options);

        assertNotNull(response);
    }

    @Test
    public void testGetEmailsMetrics_BroadcastIdFilter_MultipleValues_Success() throws ResendException {
        GetEmailsMetricsOptions options = GetEmailsMetricsOptions.builder()
                .broadcastIds("b1", "b2")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, METRICS_TOTALS_ONLY_JSON, true);

        when(httpClient.perform(eq("/emails/metrics?broadcast_id=b1%2Cb2"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        EmailsMetricsResponse response = emails.metrics(options);

        assertNotNull(response);
    }

    @Test
    public void testGetEmailsMetrics_MetricsGranularityTimezone_PassedThrough() throws ResendException {
        GetEmailsMetricsOptions options = GetEmailsMetricsOptions.builder()
                .metrics(MetricName.DELIVERED, MetricName.OPENED)
                .granularity(MetricsGranularity.WEEKLY)
                .timezone("America/New_York")
                .build();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, METRICS_TOTALS_ONLY_JSON, true);

        when(httpClient.perform(
                eq("/emails/metrics?timezone=America%2FNew_York&granularity=weekly&metrics=delivered%2Copened"),
                anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        EmailsMetricsResponse response = emails.metrics(options);

        assertNotNull(response);
    }

    @Test
    public void testGetEmailsMetricsOptions_ToQueryString_AllParams() {
        GetEmailsMetricsOptions options = GetEmailsMetricsOptions.builder()
                .startDate("2026-07-01")
                .endDate("2026-07-08")
                .timezone("UTC")
                .granularity(MetricsGranularity.DAILY)
                .metrics(MetricName.DELIVERED, MetricName.OPENED)
                .dimensions(MetricsDimension.PERIOD, MetricsDimension.BROADCAST)
                .domainIds("d1")
                .broadcastIds("b1", "b2")
                .build();

        assertEquals(
                "?start_date=2026-07-01&end_date=2026-07-08&timezone=UTC&granularity=daily" +
                "&metrics=delivered%2Copened&dimensions=period%2Cbroadcast&domain_id=d1" +
                "&broadcast_id=b1%2Cb2",
                options.toQueryString());
    }

    @Test
    public void testGetEmailsMetricsOptions_ToQueryString_Empty() {
        GetEmailsMetricsOptions options = GetEmailsMetricsOptions.builder().build();

        assertEquals("", options.toQueryString());
    }

    @Test
    public void testGetEmailsMetricsOptions_Build_RejectsEmailAndBroadcastDimensions() {
        assertThrows(IllegalArgumentException.class, () -> GetEmailsMetricsOptions.builder()
                .dimensions(MetricsDimension.EMAIL, MetricsDimension.BROADCAST)
                .build());
    }

    @Test
    public void testGetEmailsMetricsOptions_Build_RejectsBroadcastDimensionWithEmailIds() {
        assertThrows(IllegalArgumentException.class, () -> GetEmailsMetricsOptions.builder()
                .dimensions(MetricsDimension.BROADCAST)
                .emailIds("e1")
                .build());
    }

    @Test
    public void testGetEmailsMetricsOptions_Build_RejectsEmailDimensionWithBroadcastIds() {
        assertThrows(IllegalArgumentException.class, () -> GetEmailsMetricsOptions.builder()
                .dimensions(MetricsDimension.EMAIL)
                .broadcastIds("b1")
                .build());
    }

    @Test
    public void testGetEmailsMetricsOptions_Build_RejectsEmailIdsWithBroadcastIds() {
        assertThrows(IllegalArgumentException.class, () -> GetEmailsMetricsOptions.builder()
                .emailIds("e1")
                .broadcastIds("b1")
                .build());
    }

    @Test
    public void testGetEmailsMetricsOptions_Build_RejectsMoreThan100DomainIds() {
        assertThrows(IllegalArgumentException.class, () -> GetEmailsMetricsOptions.builder()
                .domainIds(Collections.nCopies(101, "d1"))
                .build());
    }

    @Test
    public void testGetEmailsMetricsOptions_Build_AllowsDomainAndBroadcastDimensionsCombined() {
        GetEmailsMetricsOptions options = GetEmailsMetricsOptions.builder()
                .dimensions(MetricsDimension.DOMAIN, MetricsDimension.BROADCAST)
                .build();

        assertEquals("?dimensions=domain%2Cbroadcast", options.toQueryString());
    }

    @Test
    public void testGetEmailsMetrics_ApiError_ThrowsResendException() throws ResendException {
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"email cannot be combined with broadcast\"}", false);

        when(httpClient.perform(eq("/emails/metrics"), anyString(), eq(HttpMethod.GET), isNull(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> emails.metrics());
        assertEquals(422, (int) ex.getStatusCode());
    }
}
