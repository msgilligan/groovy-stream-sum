import spock.lang.Specification

import java.util.stream.IntStream

/**
 * Demonstration of various approaches to summing a Stream of integers in Groovy.
 */
class StreamSumSpec extends Specification {

    /**
     * This isn't using streams, but is nice, clean, idiomatic Groovy.
     * We would expect to be able to do something similar with Streams.
     */
    def "Idiomatic summing using Groovy Collections"() {
        given:
        var square = x -> x * x

        when:
        var sumOfSquares = n -> (0..n).collect(square).sum()
        var result = sumOfSquares(100)

        then:
        result == 338350
    }

    /**
     * Groovy example using Streams. We use {@code stream()} to create a stream
     * (of objects) from the Groovy Range and then use {@code mapToInt(x -> x)} to convert
     * it to an {@code IntStream} so we can use {@sum}.
     *
     * The {@code mapToInt(x -> x)} feels like unnecessary verbosity
     */
    def "Sum of squares using Java Streams (mapToInt)"() {
        given:
        var square = x -> x * x

        when:
        var sumOfSquares = n -> (0..n).stream().map(square).mapToInt(x -> x).sum()
        var result = sumOfSquares(100)

        then:
        result == 338350
    }

    /**
     * Alternative Groovy example using Streams. We use {@code as int[]} to convert the
     * Groovy range to an array so we can use {@code IntStream.of()} to get an {@code IntStream}.
     * This allows us to use {@code .sum()} without converting from an Object stream.
     *
     * The {@code IntStream.of()} and {@code as int[]}  feel like unnecessary verbosity
     */
    def "Sum of squares using Java Streams (IntStream.of)"() {
        given:
        var square = x -> x * x

        when:
        var sumOfSquares = n -> IntStream.of(0..n as int[]).map(square).sum()
        var result = sumOfSquares(100)

        then:
        result == 338350
    }

}
