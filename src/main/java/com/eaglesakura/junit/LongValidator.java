package com.eaglesakura.junit;

import com.eaglesakura.util.StringUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * 整数値のバリデーションを行う
 */
public class LongValidator {
    final Long value;

    public LongValidator(Long value) {
        this.value = value;
    }

    public LongValidator notNull() {
        assertNotNull(value);
        return this;
    }

    public LongValidator eq(long check) {
        assertEquals((long) value, check);
        return this;
    }

    public LongValidator from(long check) {
        assertTrue(StringUtil.format("%d >= %d", value, check), value >= check);
        return this;
    }

    public LongValidator to(long check) {
        assertTrue(StringUtil.format("%d <= %d", value, check), value <= check);
        return this;
    }

    /**
     * valueがcheck未満であることを保証する
     * @param check
     * @return
     */
    public LongValidator less(long check) {
        assertTrue(StringUtil.format("%d < %d", value, check), value < check);
        return this;
    }

    public Long get() {
        return value;
    }
}
