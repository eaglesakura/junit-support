package com.eaglesakura.junit;

import org.junit.Assert;

import java.util.Collection;

/**
 * UnitTestでのAssertサポート
 */
public class SupportAssertion extends Assert {

    public static void assertNotEmpty(String str) {
        assertNotNull(str);
        assertNotEquals(str.length(), 0);
    }

    public static void assertNotEmpty(Collection collection) {
        assertNotNull(collection);
        assertNotEquals(collection.size(), 0);
    }
}
