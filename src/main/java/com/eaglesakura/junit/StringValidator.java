package com.eaglesakura.junit;

import com.eaglesakura.util.StringUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * 文字列のValidateを行う
 */
public class StringValidator {
    String value;

    public StringValidator(String value) {
        this.value = value;
    }

    public StringValidator notNull() {
        assertNotNull(value);
        return this;
    }

    /**
     * equalsと紛らわしいため、someとしておく
     */
    public StringValidator some(String check) {
        assertEquals(value, check);
        return this;
    }

    public StringValidator notEqual(String check) {
        assertNotEquals(value, check);
        return this;
    }

    public StringValidator notEmpty() {
        assertNotNull(value);
        assertNotEquals(value.length(), 0);
        return this;
    }

    public StringValidator contains(CharSequence check) {
        assertTrue(StringUtil.format("not contains[%s] -> value[%s]", check, value), value.contains(check));
        return this;
    }

    public StringValidator notContains(CharSequence check) {
        assertTrue(StringUtil.format("contains[%s] -> value[%s]", check, value), !value.contains(check));
        return this;
    }

    public String get() {
        return value;
    }
}
