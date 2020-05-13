package com.msgilligan.groovy.streamsum

import java.util.stream.DoubleStream
import java.util.stream.IntStream
import java.util.stream.LongStream

/**
 * Groovy category/extension to add methods to create primitive streams from {@code Collection}
 * <p>
 * Note: This class cannot be {@code @CompileStatic} because it relies on dynamic
 * method dispatch in order for {@code asType()} to work correctly.
 * </p>
 */
@Category(Collection<T>)
class CollectionPrimitiveStream<T extends Comparable> {

    IntStream intStream() {
        return this.stream().mapToInt(x -> x.asType(Integer))
    }

    LongStream longStream() {
        return this.stream().mapToLong(x -> x.asType(Long))
    }

    DoubleStream doubleStream() {
        return this.stream().mapToDouble(x -> x.asType(Double))
    }
}
