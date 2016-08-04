package com.eaglesakura.junit;

import com.eaglesakura.util.ReflectionUtil;
import com.eaglesakura.util.StringUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * オブジェクトの妥当性をチェックする
 */
public class ObjectValidator<T> {
    T value;

    public ObjectValidator(T value) {
        this.value = value;
    }

    public ObjectValidator<T> notNull() {
        assertNotNull(value);
        return this;
    }

    /**
     * 指定したクラスのインスタンスであることを保証する
     */
    public ObjectValidator<T> instanceIs(Class<? extends T> clazz) {
        assertEquals(value.getClass(), clazz);
        return this;
    }

    /**
     * 指定したクラスを含むサブクラスであることを保証する
     */
    public ObjectValidator<T> instanceOf(Class<? extends T> clazz) {
        assertTrue(StringUtil.format("%s instanceof %s", value.getClass(), clazz), ReflectionUtil.instanceOf(value, clazz));
        return this;
    }
}
