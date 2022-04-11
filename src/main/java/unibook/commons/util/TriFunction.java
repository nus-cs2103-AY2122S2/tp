package unibook.commons.util;

/**
 * Simple function interface for functions that take in 3 parameters to give 1 output.
 */
@FunctionalInterface
public interface TriFunction<A, B, C, R> {

    /**
     * Function to apply to given arguments that returns an object of type R
     */
    R apply(A a, B b, C c);
}
