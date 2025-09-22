package com.resend.services.audiences;

import com.resend.core.exception.ResendException;
import com.resend.core.net.ListParams;
import com.resend.services.audiences.model.CreateAudienceOptions;
import com.resend.services.audiences.model.CreateAudienceResponseSuccess;
import com.resend.services.audiences.model.ListAudiencesResponseSuccess;
import com.resend.services.audiences.model.RemoveAudienceResponseSuccess;

import com.resend.services.util.AudiencesUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class AudiencesTest {

    @Mock
    private Audiences audiences;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        audiences = mock(Audiences.class);
    }

    @Test
    public void testCreateAudience_Success() throws ResendException {
        CreateAudienceResponseSuccess expectedAudience = AudiencesUtil.createAudienceResponse();
        CreateAudienceOptions param = AudiencesUtil.createAudienceRequest();

        when(audiences.create(param))
                .thenReturn(expectedAudience);

        CreateAudienceResponseSuccess createdAud = audiences.create(param);


        assertEquals(createdAud, expectedAudience);
        verify(audiences, times(1)).create(param);
    }

    @Test
    public void testDeleteAudience_Success() throws ResendException {
        String audienceId = "123";
        RemoveAudienceResponseSuccess removed = AudiencesUtil.removeAudiencesResponseSuccess();
        when(audiences.remove(audienceId))
                .thenReturn(removed);

        RemoveAudienceResponseSuccess res = audiences.remove(audienceId);

        assertEquals(removed, res);
    }

    @Test
    public void testListAudiences_Success() throws ResendException {
        ListAudiencesResponseSuccess expectedResponse = AudiencesUtil.createAudiencesListResponse();

        when(audiences.list())
                .thenReturn(expectedResponse);

        ListAudiencesResponseSuccess res = audiences.list();

        assertNotNull(res);
        assertEquals(expectedResponse.getData().size(), res.getData().size());
        assertEquals(expectedResponse.getObject(), res.getObject());
    }

    @Test
    public void testListAudienceWithPagination_Success() throws ResendException {
        ListParams params = ListParams.builder()
                .limit(3).build();
        ListAudiencesResponseSuccess expectedResponse = AudiencesUtil.createAudiencesListResponse();

        when(audiences.list(params))
                .thenReturn(expectedResponse);

        ListAudiencesResponseSuccess res = audiences.list(params);

        assertNotNull(res);
        assertEquals(params.getLimit(), res.getData().size());
        assertEquals(expectedResponse.getObject(), res.getObject());
    }
}
