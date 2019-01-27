package com.reptile.utlils;

import java.util.UUID;

public class UuidUtils {

    public static String getUUid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
