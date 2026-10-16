package com.resend.core.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class URLHelperTest {

    @Test
    public void testEncodePathSegment_LeavesIdsUnchanged() {
        assertEquals("b3e2b2b6-3f0e-4c8e-9ad3-2f43a1e2c7f1", URLHelper.encodePathSegment("b3e2b2b6-3f0e-4c8e-9ad3-2f43a1e2c7f1"));
    }

    @Test
    public void testEncodePathSegment_EncodesReservedCharacters() {
        assertEquals("support%40acme.com", URLHelper.encodePathSegment("support@acme.com"));
        assertEquals("a%2Fb%40acme.com", URLHelper.encodePathSegment("a/b@acme.com"));
        assertEquals("a%3Fb%23c%40acme.com", URLHelper.encodePathSegment("a?b#c@acme.com"));
        assertEquals("a%2Bb%25c%40acme.com", URLHelper.encodePathSegment("a+b%c@acme.com"));
        assertEquals("a%20b", URLHelper.encodePathSegment("a b"));
        assertEquals("x%2F..%2F..%2Fdomains", URLHelper.encodePathSegment("x/../../domains"));
    }

    @Test
    public void testEncodePathSegment_RejectsValuesThatAreNotASegment() {
        assertThrows(IllegalArgumentException.class, () -> URLHelper.encodePathSegment(null));
        assertThrows(IllegalArgumentException.class, () -> URLHelper.encodePathSegment(""));
        assertThrows(IllegalArgumentException.class, () -> URLHelper.encodePathSegment("."));
        assertThrows(IllegalArgumentException.class, () -> URLHelper.encodePathSegment(".."));
    }
}
