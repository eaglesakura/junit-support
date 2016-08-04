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

    public static LongValidator validate(int value) {
        return new LongValidator((long) value);
    }

    public static LongValidator validate(long value) {
        return new LongValidator(value);
    }

    public static DoubleValidator validate(float value) {
        return new DoubleValidator((double) value);
    }

    public static DoubleValidator validate(double value) {
        return new DoubleValidator((double) value);
    }

    public static StringValidator validate(String value) {
        return new StringValidator(value);
    }

    public static <T> CollectionValidator<T> validate(Collection<T> list) {
        return new CollectionValidator<>(list);
    }

    public static <T> ObjectValidator<T> validate(T obj) {
        return new ObjectValidator<>(obj);
    }
}
