package com.resend.services.batch;

import com.resend.core.exception.ResendException;
import com.resend.core.net.AbstractHttpResponse;
import com.resend.core.net.HttpMethod;
import com.resend.core.net.IHttpClient;
import com.resend.core.net.RequestOptions;
import com.resend.services.batch.model.CreateBatchEmailsResponse;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.util.EmailsUtil;
import okhttp3.MediaType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class BatchTest {

    private static final String BATCH_RESPONSE_JSON =
            "{\"data\":[{\"id\":\"123\"},{\"id\":\"321\"}],\"errors\":[]}";

    private static final String PERMISSIVE_BATCH_RESPONSE_JSON =
            "{\"data\":[{\"id\":\"123\"},{\"id\":\"321\"}]," +
            "\"errors\":[{\"index\":456,\"message\":\"error for email at index 456\"}," +
            "{\"index\":789,\"message\":\"error for email at index 789\"}]}";

    @Mock
    private IHttpClient httpClient;

    private Batch batch;

    private AutoCloseable mocks;

    @BeforeEach
    public void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        batch = new Batch("test-api-key", httpClient);
    }

    @AfterEach
    public void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    public void testCreateBatchEmails_SerializesTags() throws ResendException {
        List<CreateEmailOptions> batchEmailsRequest = EmailsUtil.createBatchEmailOptions();
        ArgumentCaptor<String> payloadCaptor = ArgumentCaptor.forClass(String.class);
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, BATCH_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails/batch"), anyString(), eq(HttpMethod.POST), payloadCaptor.capture(), any(MediaType.class)))
                .thenReturn(httpResponse);

        batch.send(batchEmailsRequest);

        String payload = payloadCaptor.getValue();
        assertTrue(payload.contains("\"tags\":[{\"name\":\"tagName\",\"value\":\"tagValue\"}]"));
        assertFalse(payload.contains("attachments"));
        assertFalse(payload.contains("scheduled_at"));
    }

    @Test
    public void testCreateBatchEmails_Success() throws ResendException {
        List<CreateEmailOptions> batchEmailsRequest = EmailsUtil.createBatchEmailOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, BATCH_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails/batch"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        CreateBatchEmailsResponse response = batch.send(batchEmailsRequest);

        assertNotNull(response);
        assertEquals(2, response.getData().size());
        assertEquals("123", response.getData().get(0).getId());
    }

    @Test
    public void testCreateBatchEmails_ApiError_ThrowsResendException() throws ResendException {
        List<CreateEmailOptions> batchEmailsRequest = EmailsUtil.createBatchEmailOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(422,
                "{\"name\":\"validation_error\",\"message\":\"Batch failed\"}", false);

        when(httpClient.perform(eq("/emails/batch"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class)))
                .thenReturn(httpResponse);

        ResendException ex = assertThrows(ResendException.class, () -> batch.send(batchEmailsRequest));
        assertEquals(422, (int) ex.getStatusCode());
    }

    @Test
    public void testCreatePermissiveBatchEmails_Success() throws ResendException {
        RequestOptions options = RequestOptions.builder()
                .add("x-batch-validation", "permissive").build();
        List<CreateEmailOptions> batchEmailsRequest = EmailsUtil.createBatchEmailOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, PERMISSIVE_BATCH_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails/batch"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class), any(RequestOptions.class)))
                .thenReturn(httpResponse);

        CreateBatchEmailsResponse response = batch.send(batchEmailsRequest, options);

        assertNotNull(response);
        assertEquals(2, response.getData().size());
        assertEquals(2, response.getErrors().size());
        assertTrue(response.hasErrors());
    }

    @Test
    public void testCreateBatchEmailsWithIdempotencyKey_Success() throws ResendException {
        RequestOptions requestOptions = EmailsUtil.createRequestOptions();
        List<CreateEmailOptions> batchEmailsRequest = EmailsUtil.createBatchEmailOptions();
        AbstractHttpResponse<String> httpResponse = new AbstractHttpResponse<>(200, BATCH_RESPONSE_JSON, true);

        when(httpClient.perform(eq("/emails/batch"), anyString(), eq(HttpMethod.POST), anyString(), any(MediaType.class), any(RequestOptions.class)))
                .thenReturn(httpResponse);

        CreateBatchEmailsResponse response = batch.send(batchEmailsRequest, requestOptions);

        assertNotNull(response);
        assertEquals(2, response.getData().size());
    }
}
