package com.msgilligan.groovy.streamsum

/**
 * Class for testing {@code asType()} conversions
 */
class Distance {
    BigDecimal measure
    String unit

    Distance(BigDecimal measure, String unit) {
        this.measure = measure
        this.unit = unit
    }

    Object asType(Class clazz) {
        if ((clazz == Number) || (clazz == BigDecimal))  {
            return measure;
        } else if (clazz == Double) {
            return measure.toDouble()
        } else if (clazz == Long) {
            return measure.toLong()
        } else if (clazz == Integer) {
            return measure.toInteger()
        } else {
            throw new ClassCastException("Can't convert to type: " + clazz)
        }
    }
}
