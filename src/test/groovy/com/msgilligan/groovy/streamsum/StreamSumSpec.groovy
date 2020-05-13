package com.msgilligan.groovy.streamsum

import spock.lang.Specification
import spock.lang.Unroll

/**
 *  Test Specification for StreamSum
 */
class StreamSumSpec extends Specification {

    def "1-liners"() {
        expect:
        [0i, 1i].intStream().sum() == 1i
        [0L, 1L].longStream().sum() == 1L
        [0.0, 1.0].doubleStream().sum() == 1.0d
    }

    def "sum extension works for sumOfSquares example"() {
        given:
        var square = x -> x * x

        when:
        var sumOfSquares = n -> (0..n).stream().map(square).sum()
        var result = sumOfSquares(100)

        then:
        result == 338350
    }

    @Unroll
    def "sum extension works on a variety of inputs (#inputCollection, #expectedResult)"(Collection inputCollection, Number expectedResult) {
        when:
        var sum = inputCollection.stream().sum()

        then:
        sum == expectedResult

        where:
        [inputCollection, expectedResult] << [
                [0..100, 5050],
                [0.0..100.0, 5050.0],
                [0L..100L, 5050L],
                [[0,1,2], 3],
                [[0.5,1.75,2.25], 4.5],
                [[new Distance(3 as BigDecimal, "feet"), new Distance(4 as BigDecimal, "yards")], 7]
        ]
    }

    @Unroll
    def "Invalid inputs throw ClassCastException (#inputCollection, #expectedResult)"(Collection inputCollection, Number expectedResult) {
        when:
        var sum = inputCollection.stream().sum()

        then:
        thrown(ClassCastException)

        where:
        [inputCollection, expectedResult] << [
                [['a','b','c'], 0],
                [[1,2,'c'], 3],

        ]
    }

    /**
     * Class for testing {@code asType()} conversions
     */
    static class Distance {
        BigDecimal measure
        String unit

        Distance(BigDecimal measure, String unit) {
            this.measure = measure
            this.unit = unit
        }

        Object asType(Class clazz) {
            if (clazz == Number) {
                return measure;
            } else {
                throw new ClassCastException("Can't convert to type: " + clazz)
            }
        }
    }

}
