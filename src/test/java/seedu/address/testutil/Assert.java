package seedu.address.testutil;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

/**
 * A set of assertion methods useful for writing tests.
 */
public class Assert {

    /**
     * Asserts that the {@code executable} throws the {@code expectedType} Exception.
     * This is a wrapper method that invokes {@link Assertions#assertThrows(Class, Executable)}, to maintain
     * consistency with our custom {@link #assertThrows(Class, String, Executable)} method.
     * To standardize API calls in this project, users should use this method instead of
     * {@link Assertions#assertThrows(Class, Executable)}.
     */
    public static void assertThrows(Class<? extends Throwable> expectedType, Executable executable) {
        Assertions.assertThrows(expectedType, executable);
    }

    /**
     * Asserts that the {@code executable} throws the {@code expectedType} Exception with the {@code expectedMessage}.
     * If there's no need for the verification of the exception's error message, call
     * {@link #assertThrows(Class, Executable)} instead.
     *
     * @see #assertThrows(Class, Executable)
     */
    public static void assertThrows(Class<? extends Throwable> expectedType, String expectedMessage,
            Executable executable) {
        Throwable thrownException = Assertions.assertThrows(expectedType, executable);
        Assertions.assertEquals(expectedMessage, thrownException.getMessage());
    }

    /**
     * Asserts that when the {@code function} is applied with any of the inputs, it throws the
     * {@code expectedType} of exception.
     *
     * @param expectedType the type of exception that is expected to be thrown
     * @param function the function to be executed
     * @param inputs the inputs to the function, applied one by one
     * @param <T> the type of input
     * @param <R> the type that the function returns
     */
    @SafeVarargs
    public static <T, R> void assertAllThrows(Class<? extends Throwable> expectedType,
                                              Function<? super T, R> function, T... inputs) {

        for (T input : inputs) {
            assertThrows(expectedType, () -> function.apply(input));
        }
    }

    /**
     * Asserts that when the {@code function} is applied with any of the inputs, it throws the
     * {@code expectedType} of exception.
     *
     * @param expectedType the type of exception that is expected to be thrown
     * @param expectedMessage the error message expected for every run of the function
     * @param function the function to be executed
     * @param inputs the inputs to the function, applied one by one
     * @param <T> the type of input
     * @param <R> the type that the function returns
     */
    @SafeVarargs
    public static <T, R> void assertAllThrows(Class<? extends Throwable> expectedType, String expectedMessage,
                                              Function<? super T, R> function, T... inputs) {

        for (T input : inputs) {
            assertThrows(expectedType, expectedMessage, () -> function.apply(input));
        }
    }

    /**
     * Asserts that for each of the items in {@code testItems}, if it is passed as the parameter of the
     * {@code predicate}, then the {@code predicate} will evaluate to false.
     *
     * @param predicate the predicate to be used
     * @param testItems the items to be supplied as input to {@code predicate}
     * @param <T> the type of the items
     */
    @SafeVarargs
    public static <T> void assertAllFalse(Predicate<? super T> predicate, T... testItems) {
        for (T item : testItems) {
            assertFalse(predicate.test(item));
        }
    }

    /**
     * Asserts that for each of the items in {@code testItems}, if it is passed as the parameter of the
     * {@code predicate}, then the {@code predicate} will evaluate to true.
     *
     * @param predicate the predicate to be used
     * @param testItems the items to be supplied as input to {@code predicate}
     * @param <T> the type of the items
     */
    @SafeVarargs
    public static <T> void assertAllTrue(Predicate<? super T> predicate, T... testItems) {
        for (T item : testItems) {
            assertTrue(predicate.test(item));
        }
    }
}
