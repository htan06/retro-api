package com.retro.api.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCreateSlug {
    @Test
    public void testCreateSlug() {
        String name = "Laptop Lenovo  2025";
        String slugName = CreateSlug.create(name);

        assertEquals("Laptop-Lenovo-2025", slugName);
    }
}
