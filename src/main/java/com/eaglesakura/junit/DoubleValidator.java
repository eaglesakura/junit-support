package com.eaglesakura.junit;

import com.eaglesakura.util.StringUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * 整数値のバリデーションを行う
 */
public class DoubleValidator {
    final Double value;

    /**
     * 許される誤差
     */
    double delta = 0.00001;

    public DoubleValidator(Double value) {
        this.value = value;
    }

    public DoubleValidator notNull() {
        assertNotNull(value);
        return this;
    }

    /**
     * 許容可能な誤差を指定する
     */
    public DoubleValidator delta(double newDelta) {
        delta = newDelta;
        return this;
    }

    public DoubleValidator eq(double check) {
        assertEquals(value, check, delta);
        return this;
    }

    public DoubleValidator from(double check) {
        assertTrue(StringUtil.format("%d >= %d", value, check), value >= check);
        return this;
    }

    public DoubleValidator to(double check) {
        assertTrue(StringUtil.format("%d <= %d", value, check), value <= check);
        return this;
    }

    public Double get() {
        return value;
    }
}
