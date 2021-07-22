package de.nikos410.ina.webshop.util;

import java.math.BigDecimal;

public final class BigDecimalUtils {

    // Private constructor to hide the implicit public one.
    private BigDecimalUtils() {}

    /**
     * Check whether the first number is greater than the second.
     *
     * @param first The first number.
     * @param second The second number.
     * @return True if first is greater than second. False if first ist less than or equal to second.
     */
    public static boolean isGreaterThan(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) > 0;
    }
}
