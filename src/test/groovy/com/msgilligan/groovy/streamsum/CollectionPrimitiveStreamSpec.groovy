package com.msgilligan.groovy.streamsum

import spock.lang.Specification
import spock.lang.Unroll
import spock.util.mop.Use

/**
 *  Test Specification for CollectionPrimitiveStream
 */
class CollectionPrimitiveStreamSpec extends Specification {
    def "intStream extension works"() {
        given:
        var square = x -> x * x

        when:
        var sumOfSquares = n -> (0..n).intStream().map(square).sum()
        var result = sumOfSquares(100)

        then:
        result == 338350
    }

    def "longStream extension works"() {
        given:
        var square = x -> x * x

        when:
        var sumOfSquares = n -> (0..n).longStream().map(square).sum()
        var result = sumOfSquares(100)

        then:
        result == 338350L
    }

    def "doubleStream extension works"() {
        given:
        var square = x -> x * x

        when:
        var sumOfSquares = n -> (0.0..n).doubleStream().map(square).sum()
        var result = sumOfSquares(100)

        then:
        result == 338350.0
    }

    @Unroll
    def "sum extension works on a variety of inputs (#inputCollection, #expectedResult)"(Collection inputCollection, Number expectedResult) {
        when:
        var sum = inputCollection.intStream().sum()

        then:
        sum == expectedResult

        where:
        [inputCollection, expectedResult] << [
                [0..100, 5050],
                [0.0..100.0, 5050.0],
                [0L..100L, 5050L],
                [[0,1,2], 3],
                [[0.5,1.75,2.25], 3],
                [[new Distance(3 as BigDecimal, "feet"), new Distance(4 as BigDecimal, "yards")], 7]
        ]
    }

}
