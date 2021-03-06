import spock.lang.Specification

import java.util.stream.IntStream

/**
 * Demonstration of various approaches to summing a Stream of integers in Groovy.
 */
class SumOfSquaresSamplesSpec extends Specification {

    /*
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

    /*
     * Groovy example using Streams. We use {@code stream()} to create a stream
     * (of objects) from the Groovy Range which we {@code map(square)} and then
     * use {@code mapToInt(x -> x)} to convert to an {@code IntStream} and {@code sum()}.
     *
     * The {@code mapToInt(x -> x)} feels like unnecessary verbosity
     */
    def "Sum of squares using Java Streams (mapToInt after squaring)"() {
        given:
        var square = x -> x * x

        when:
        var sumOfSquares = n -> (0..n).stream().map(square).mapToInt(x -> x).sum()
        var result = sumOfSquares(100)

        then:
        result == 338350
    }

    /*
     * Groovy example using Streams. We use {@code stream()} to create a stream
     * (of objects) from the Groovy Range and then use {@code mapToInt(x -> x)} to convert
     * it to an {@code IntStream} that we {@code map(square)} and {@code sum()}.
     * <p>
     * The {@code mapToInt(x -> x)} feels like unnecessary verbosity
     * </p>
     */
    def "Sum of squares using Java Streams (mapToInt)"() {
        given:
        var square = x -> x * x

        when:
        var sumOfSquares = n -> (0..n).stream().mapToInt(x -> x).map(square).sum()
        var result = sumOfSquares(100)

        then:
        result == 338350
    }

    /*
     * Alternative Groovy example using Streams. We use {@code as int[]} to convert the
     * Groovy range to an array so we can use {@code IntStream.of()} to get an {@code IntStream}.
     * This allows us to use {@code .sum()} without converting from an Object stream.
     * <p>
     * The {@code IntStream.of()} and {@code as int[]}  feel like unnecessary verbosity
     * </p>
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

    /*
     * Groovy example using {@link com.msgilligan.groovy.streamsum.CollectionPrimitiveStream}.
     * This Groovy extension allows us to more easily (and less verbosely) create an
     * {@link IntStream} from a Groovy {@link Range} (or any {@link Collection}.)
     */
    def "Sum of squares with Groovy CollectionPrimitiveStream extension"() {
        given:
        var square = x -> x * x

        when:
        var sumOfSquares = n -> (0..n).intStream().map(square).sum()
        var result = sumOfSquares(100)

        then:
        result == 338350
    }

    /**
     * Groovy example using {@link com.msgilligan.groovy.streamsum.StreamSum}.
     * This extension results in what is perhaps the most <i>Groovy</i> example
     * of all and also allows summing collections of different types of {@link Number}.
     */
    def "Sum of squares with Groovy StreamSum extension"() {
        given:
        var square = x -> x * x

        when:
        var sumOfSquares = n -> (0..n).stream().map(square).sum()
        var result = sumOfSquares(100)

        then:
        result == 338350
    }

}
