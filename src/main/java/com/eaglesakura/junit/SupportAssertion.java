package com.eaglesakura.junit;

import com.eaglesakura.lambda.Action0;
import com.eaglesakura.lambda.ResultAction0;
import com.eaglesakura.util.StringUtil;

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

    public static void assertEmpty(String str) {
        assertTrue(StringUtil.isEmpty(str));
    }

    public static void assertNotEmpty(Collection collection) {
        assertNotNull(collection);
        assertNotEquals(collection.size(), 0);
    }

    public static CodeBlockValidator<Object> codeBlock(String blockName, Action0 action) {
        return new CodeBlockValidator<>(blockName, action);
    }

    public static <T> CodeBlockValidator<T> codeBlock(String blockName, ResultAction0<T> action) {
        return new CodeBlockValidator<>(blockName, action);
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
