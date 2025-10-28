package com.resend.services.templates;

import com.resend.core.exception.ResendException;
import com.resend.core.net.ListParams;
import com.resend.services.templates.model.*;
import com.resend.services.util.TemplatesUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the Templates service.
 */
public class TemplatesTest {

    @Mock
    private Templates templates;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        templates = mock(Templates.class);
    }

    @Test
    public void testCreateTemplate_Success() throws ResendException {
        CreateTemplateOptions createOptions = TemplatesUtil.createTemplateOptions();
        CreateTemplateResponseSuccess expectedResponse = TemplatesUtil.createTemplateResponse();

        when(templates.create(createOptions)).thenReturn(expectedResponse);

        CreateTemplateResponseSuccess response = templates.create(createOptions);

        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(expectedResponse.getObject(), response.getObject());
        verify(templates, times(1)).create(createOptions);
    }

    @Test
    public void testGetTemplate_Success() throws ResendException {
        String templateId = "34a080c9-b17d-4187-ad80-5af20266e535";
        GetTemplateResponseSuccess expectedTemplate = TemplatesUtil.createTemplate();

        when(templates.get(templateId)).thenReturn(expectedTemplate);

        GetTemplateResponseSuccess retrievedTemplate = templates.get(templateId);

        assertNotNull(retrievedTemplate);
        assertEquals(expectedTemplate.getId(), retrievedTemplate.getId());
        assertEquals(expectedTemplate.getName(), retrievedTemplate.getName());
        assertEquals(expectedTemplate.getStatus(), retrievedTemplate.getStatus());
        verify(templates, times(1)).get(templateId);
    }

    @Test
    public void testGetTemplateByAlias_Success() throws ResendException {
        String alias = "reset-password";
        GetTemplateResponseSuccess expectedTemplate = TemplatesUtil.createTemplate();

        when(templates.get(alias)).thenReturn(expectedTemplate);

        GetTemplateResponseSuccess retrievedTemplate = templates.get(alias);

        assertNotNull(retrievedTemplate);
        assertEquals(expectedTemplate.getAlias(), retrievedTemplate.getAlias());
        verify(templates, times(1)).get(alias);
    }

    @Test
    public void testListTemplates_Success() throws ResendException {
        ListTemplatesResponseSuccess expectedResponse = TemplatesUtil.listTemplatesResponse();

        when(templates.list()).thenReturn(expectedResponse);

        ListTemplatesResponseSuccess response = templates.list();

        assertNotNull(response);
        assertEquals(expectedResponse.getData().size(), response.getData().size());
        assertEquals(expectedResponse.getHasMore(), response.getHasMore());
        verify(templates, times(1)).list();
    }

    @Test
    public void testListTemplatesWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder()
                .limit(2)
                .after("34a080c9-b17d-4187-ad80-5af20266e535")
                .build();
        ListTemplatesResponseSuccess expectedResponse = TemplatesUtil.listTemplatesResponse();

        when(templates.list(params)).thenReturn(expectedResponse);

        ListTemplatesResponseSuccess response = templates.list(params);

        assertNotNull(response);
        assertEquals(expectedResponse.getData().size(), response.getData().size());
        verify(templates, times(1)).list(params);
    }

    @Test
    public void testUpdateTemplate_Success() throws ResendException {
        String templateId = "34a080c9-b17d-4187-ad80-5af20266e535";
        UpdateTemplateOptions updateOptions = TemplatesUtil.updateTemplateOptions();
        UpdateTemplateResponseSuccess expectedResponse = TemplatesUtil.updateTemplateResponse();

        when(templates.update(templateId, updateOptions)).thenReturn(expectedResponse);

        UpdateTemplateResponseSuccess response = templates.update(templateId, updateOptions);

        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(expectedResponse.getObject(), response.getObject());
        verify(templates, times(1)).update(templateId, updateOptions);
    }

    @Test
    public void testDeleteTemplate_Success() throws ResendException {
        String templateId = "34a080c9-b17d-4187-ad80-5af20266e535";
        DeleteTemplateResponseSuccess expectedResponse = TemplatesUtil.deleteTemplateResponse();

        when(templates.remove(templateId)).thenReturn(expectedResponse);

        DeleteTemplateResponseSuccess response = templates.remove(templateId);

        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(expectedResponse.getDeleted(), response.getDeleted());
        verify(templates, times(1)).remove(templateId);
    }

    @Test
    public void testDuplicateTemplate_Success() throws ResendException {
        String templateId = "34a080c9-b17d-4187-ad80-5af20266e535";
        DuplicateTemplateResponseSuccess expectedResponse = TemplatesUtil.duplicateTemplateResponse();

        when(templates.duplicate(templateId)).thenReturn(expectedResponse);

        DuplicateTemplateResponseSuccess response = templates.duplicate(templateId);

        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(expectedResponse.getObject(), response.getObject());
        verify(templates, times(1)).duplicate(templateId);
    }

    @Test
    public void testPublishTemplate_Success() throws ResendException {
        String templateId = "34a080c9-b17d-4187-ad80-5af20266e535";
        PublishTemplateResponseSuccess expectedResponse = TemplatesUtil.publishTemplateResponse();

        when(templates.publish(templateId)).thenReturn(expectedResponse);

        PublishTemplateResponseSuccess response = templates.publish(templateId);

        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(expectedResponse.getObject(), response.getObject());
        verify(templates, times(1)).publish(templateId);
    }
}
