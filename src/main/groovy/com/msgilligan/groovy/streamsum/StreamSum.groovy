package com.msgilligan.groovy.streamsum

import java.util.stream.Stream

/**
 * Groovy category/extension to add {@code sum()} to {@code Stream}.
 * <p>
 * Note: This class cannot be {@code @CompileStatic} because it relies on dynamic
 * method dispatch in order for {@code asType()} to work correctly.
 * </p>
 */
@Category(Stream<T>)
class StreamSum<T> {
    Object sum() {
        this.reduce((Object) 0, StreamSum::sumAccumulator)
    }

    static Object sumAccumulator(Object a, Object b) {
        if ((a instanceof Number) && (b instanceof Number)) {
            // If they're both numbers, add them
            return a.plus(b)
        } else if ((a instanceof Number)) {
            // If a is a number, try to convert b and then add
            return a.plus(b.asType(Number))
        } else if ((b instanceof Number)) {
            // If b is a number, try to convert a and then add
            return b.plus(a.asType(Number))
        } else {
            // Else try to convert them both before adding
            return a.asType(Number.class).plus(b.asType(Number))
        }
    }
}
