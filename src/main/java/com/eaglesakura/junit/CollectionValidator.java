package com.eaglesakura.junit;

import com.eaglesakura.lambda.Action1;
import com.eaglesakura.lambda.Action2;
import com.eaglesakura.util.StringUtil;

import java.util.Collection;
import java.util.Iterator;

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

    /**
     * 要素全てがNULLではないことを検証する
     */
    public CollectionValidator<T> allNotNull() {
        Iterator<T> iterator = values.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            T item = iterator.next();
            if (item == null) {
                fail(StringUtil.format("%s[%d] == null", values.toString(), index));
            }
            ++index;
        }
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

    /**
     * 最初の要素を検証する
     */
    public CollectionValidator<T> checkFirst(Action1<T> action) {
        return checkAt(0, action);
    }

    /**
     * 最後の要素を検証する
     */
    public CollectionValidator<T> checkLast(Action1<T> action) {
        return checkAt(values.size() - 1, action);
    }

    /**
     * 設定したインターバルで検証を行う。
     * この時、firstの要素はチェックを行わない。
     */
    public CollectionValidator<T> checkInterval(int interval, Action1<T> action) {
        try {

            Iterator<T> iterator = values.iterator();
            for (int i = 0; i < values.size(); ++i) {
                T item = iterator.next();
                if (i != 0 && (i % interval) == 0) {
                    // 0を除き、インターバルに一致すれば検証する
                    action.action(item);
                }
            }
        } catch (Error e) {
            throw e;
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
        return this;
    }

    /**
     * 指定したインデックスのデータを検証する
     *
     * @param position 配列インデックス
     * @param action   検証処理
     */
    public CollectionValidator<T> checkAt(int position, Action1<T> action) {
        try {
            new LongValidator((long) position)
                    .from(0)
                    .less(values.size());
            Iterator<T> iterator = values.iterator();
            for (int i = 0; i < position; ++i) {
                iterator.next();
            }

            action.action(iterator.next());
        } catch (Error e) {
            throw e;
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
        return this;
    }

    public CollectionValidator<T> each(Action1<T> action) {
        try {
            for (T it : values) {
                action.action(it);
            }
        } catch (Error e) {
            throw e;
        } catch (Throwable e) {
            e.printStackTrace();
            fail();
        }
        return this;
    }

    public CollectionValidator<T> eachWithIndex(Action2<Integer, T> action) {
        try {
            int index = 0;
            for (T it : values) {
                action.action(index, it);
                ++index;
            }
        } catch (Error e) {
            throw e;
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
