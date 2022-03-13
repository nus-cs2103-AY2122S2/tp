package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ArgumentTokenizerUtil.DASH_P;
import static seedu.address.logic.parser.ArgumentTokenizerUtil.DASH_T;
import static seedu.address.logic.parser.ArgumentTokenizerUtil.HAT_Q;
import static seedu.address.logic.parser.ArgumentTokenizerUtil.UNKNOWN_PREFIX;
import static seedu.address.logic.parser.ArgumentTokenizerUtil.argsTokenize;

import org.junit.jupiter.api.Test;

public class ArgumentTokenizerTest {

    @Test
    public void tokenize_emptyArgsString_noValues() {
        String argsString = "  ";
        ArgumentMultimap argMultimap = argsTokenize(argsString, DASH_P);

        assertPreambleEmpty(argMultimap);
        assertArgumentAbsent(argMultimap, DASH_P);
    }

    private void assertPreamblePresent(ArgumentMultimap argMultimap, String expectedPreamble) {
        assertEquals(expectedPreamble, argMultimap.getPreamble());
    }

    private void assertPreambleEmpty(ArgumentMultimap argMultimap) {
        assertTrue(argMultimap.getPreamble().isEmpty());
    }

    /**
     * Asserts all the arguments in {@code argMultimap} with {@code prefix} match the {@code expectedValues}
     * and only the last value is returned upon calling {@code ArgumentMultimap#getValue(Prefix)}.
     */
    private void assertArgumentPresent(ArgumentMultimap argMultimap, Prefix prefix, String... expectedValues) {

        // Verify the last value is returned
        assertEquals(expectedValues[expectedValues.length - 1], argMultimap.getValue(prefix).get());

        // Verify the number of values returned is as expected
        assertEquals(expectedValues.length, argMultimap.getAllValues(prefix).size());

        // Verify all values returned are as expected and in order
        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], argMultimap.getAllValues(prefix).get(i));
        }
    }

    private void assertArgumentAbsent(ArgumentMultimap argMultimap, Prefix prefix) {
        assertFalse(argMultimap.getValue(prefix).isPresent());
    }

    @Test
    public void tokenize_noPrefixes_allTakenAsPreamble() {
        String argsString = "  some random string /t tag with leading and trailing spaces ";
        ArgumentMultimap argMultimap = argsTokenize(argsString);

        // Same string expected as preamble, but leading/trailing spaces should be trimmed
        assertPreamblePresent(argMultimap, argsString.trim());

    }

    @Test
    public void tokenize_oneArgument() {
        // Preamble present
        String argsString = "  Some preamble string -p Argument value ";
        ArgumentMultimap argMultimap = argsTokenize(argsString, DASH_P);
        assertPreamblePresent(argMultimap, "Some preamble string");
        assertArgumentPresent(argMultimap, DASH_P, "Argument value");

        // No preamble
        argsString = " -p   Argument value ";
        argMultimap = argsTokenize(argsString, DASH_P);
        assertPreambleEmpty(argMultimap);
        assertArgumentPresent(argMultimap, DASH_P, "Argument value");

    }

    @Test
    public void tokenize_multipleArguments() {
        // Only two arguments are present
        String argsString = "SomePreambleString -t dashT-Value -pdashP value";
        ArgumentMultimap argMultimap = argsTokenize(argsString, DASH_P, DASH_T, HAT_Q);
        assertPreamblePresent(argMultimap, "SomePreambleString");
        assertArgumentPresent(argMultimap, DASH_P, "dashP value");
        assertArgumentPresent(argMultimap, DASH_T, "dashT-Value");
        assertArgumentAbsent(argMultimap, HAT_Q);

        // All three arguments are present
        argsString = "Different Preamble String ^Q111 -t dashT-Value -pdashP value";
        argMultimap = argsTokenize(argsString, DASH_P, DASH_T, HAT_Q);
        assertPreamblePresent(argMultimap, "Different Preamble String");
        assertArgumentPresent(argMultimap, DASH_P, "dashP value");
        assertArgumentPresent(argMultimap, DASH_T, "dashT-Value");
        assertArgumentPresent(argMultimap, HAT_Q, "111");

        /* Also covers: Reusing of the tokenizer multiple times */

        // Reuse tokenizer on an empty string to ensure ArgumentMultimap is correctly reset
        // (i.e. no stale values from the previous tokenizing remain)
        argsString = "";
        argMultimap = argsTokenize(argsString, DASH_P, DASH_T, HAT_Q);
        assertPreambleEmpty(argMultimap);
        assertArgumentAbsent(argMultimap, DASH_P);

        /* Also covers: testing for prefixes not specified as a prefix */

        // Prefixes not previously given to the tokenizer should not return any values
        argsString = UNKNOWN_PREFIX + "some value";
        argMultimap = argsTokenize(argsString, DASH_P, DASH_T, HAT_Q);
        assertArgumentAbsent(argMultimap, UNKNOWN_PREFIX);
        assertPreamblePresent(argMultimap, argsString); // Unknown prefix is taken as part of preamble
    }

    @Test
    public void tokenize_multipleArgumentsWithRepeats() {
        // Two arguments repeated, some have empty values
        String argsString = "SomePreambleString -t dashT-Value ^Q ^Q -t another dashT value -p dashP value -t";
        assertThrows(IllegalArgumentException.class, () -> {
            argsTokenize(argsString, DASH_P, DASH_T, HAT_Q);
        });
    }

    @Test
    public void tokenize_multipleArgumentsJoined() {
        String argsString = "SomePreambleStringp/ pSlash joined-tjoined -t not joined^Qjoined";
        ArgumentMultimap argMultimap = argsTokenize(argsString, DASH_P, DASH_T, HAT_Q);
        assertPreamblePresent(argMultimap, "SomePreambleStringp/ pSlash joined-tjoined");
        assertArgumentAbsent(argMultimap, DASH_P);
        assertArgumentPresent(argMultimap, DASH_T, "not joined^Qjoined");
        assertArgumentAbsent(argMultimap, HAT_Q);
    }

    @Test
    public void equalsMethod() {
        Prefix aaa = new Prefix("aaa", "tripleA");

        assertEquals(aaa, aaa);
        assertEquals(aaa, new Prefix("aaa", "tripleA"));

        assertNotEquals(aaa, "aaa");
        assertNotEquals(aaa, new Prefix("aab", "DoubleASingleB"));
    }

}
