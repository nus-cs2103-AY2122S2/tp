package unibook.commons.util;

import static java.util.Objects.requireNonNull;

import java.util.function.Function;

/**
 * Simple function interface for functions that take in 3 parameters to give 1 output.
 */
@FunctionalInterface
public interface TriFunction<A,B,C,R> {

    R apply(A a, B b, C c);

    default <V> TriFunction<A, B, C, V> andThen(
        Function<? super R, ? extends V> after) {
        requireNonNull(after);
        return (A a, B b, C c) -> after.apply(apply(a, b, c));
    }
}
