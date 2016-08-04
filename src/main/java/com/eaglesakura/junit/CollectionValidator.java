package com.eaglesakura.junit;

import com.eaglesakura.lambda.Action1;
import com.eaglesakura.util.StringUtil;

import java.util.Collection;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Collectionに対する正当性チェックを行う
 */
public class CollectionValidator<T> {
    Collection<T> values;

    public CollectionValidator(Collection<T> values) {
        this.values = values;
    }

    public CollectionValidator<T> notEmpty() {
        assertNotNull(values);
        assertNotEquals(values.size(), 0);
        return this;
    }

    public CollectionValidator<T> sizeFrom(int size) {
        assertTrue(StringUtil.format("%d >= %d", values.size(), size), values.size() >= size);
        return this;
    }

    public CollectionValidator<T> sizeTo(int size) {
        assertTrue(StringUtil.format("%d <= %d", values.size(), size), values.size() <= size);
        return this;
    }

    public CollectionValidator<T> each(Action1<T> action) {
        try {
            for (T it : values) {
                action.action(it);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
        return this;
    }

    public CollectionValidator<T> contains(T item) {
        for (T it : values) {
            if (it.equals(item)) {
                return this;
            }
        }

        fail(StringUtil.format("failed contains obj[%s] size[%d] find[%s]", values.toString(), values.size(), item.toString()));
        return null;
    }
}
