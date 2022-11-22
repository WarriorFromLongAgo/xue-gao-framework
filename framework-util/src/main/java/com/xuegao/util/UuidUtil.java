package com.xuegao.util;

import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

/**
 * https://neilmadden.blog/2018/08/30/moving-away-from-uuids/
 */
public class UuidUtil {
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();
    private static final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();

    public static String uuid() {
        byte[] buffer = new byte[20];
        random.nextBytes(buffer);
        return encoder.encodeToString(buffer);
    }
}
