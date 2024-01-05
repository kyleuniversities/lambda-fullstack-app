package com.lambda.lambda.common.util.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.util.wrapper.IntegerWrapper;

/**
 * Utility class for performing hierarchal comparison
 */
public final class ComparatorList<T> extends ArrayList<Comparator<T>> {
    // New Instance Method
    public static <T> ComparatorList<T> newInstance() {
        return new ComparatorList<>();
    }

    // Constructor Method
    private ComparatorList() {
        super();
    }

    // Operant Methods
    public int compare(T item1, T item2) {
        IntegerWrapper comparison = IntegerWrapper.newInstance();
        ListHelper.forEach(this, (Comparator<T> comparator) -> {
            comparison.setValue(comparator.compare(item1, item2));
            return comparison.isEqualTo(0);
        });
        return comparison.getValue();
    }
}
