package com.retro.api.utils;

public class CreateSlug {
    public static String create(String value) {
        return value.replaceAll("\\s+", "-");
    }
}
