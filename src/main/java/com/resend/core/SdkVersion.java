package com.resend.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Provides the SDK version loaded from build properties.
 */
public final class SdkVersion {

    private static final String VERSION;

    static {
        String version = "unknown";
        try (InputStream is = SdkVersion.class.getClassLoader()
                .getResourceAsStream("resend-sdk.properties")) {
            if (is != null) {
                Properties props = new Properties();
                props.load(is);
                version = props.getProperty("version", "unknown");
            }
        } catch (IOException ignored) {
        }
        VERSION = version;
    }

    private SdkVersion() {
    }

    /**
     * Returns the SDK version.
     *
     * @return the version string
     */
    public static String getVersion() {
        return VERSION;
    }
}
