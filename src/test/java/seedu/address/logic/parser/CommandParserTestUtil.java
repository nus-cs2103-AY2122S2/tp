package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains helper methods for testing command parsers.
 */
public class CommandParserTestUtil {

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseSuccess(Parser parser, String userInput, Command expectedCommand) {
        try {
            Command command = parser.parse(userInput);
            assertEquals(expectedCommand, command);
        } catch (IllegalArgumentException | ParseException e) {
            throw new IllegalArgumentException("Invalid userInput.", e);
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertParseFailure(Parser parser, String userInput, String expectedMessage) {
        try {
            parser.parse(userInput);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (IllegalArgumentException | ParseException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    /**
     * Asserts that parsing of {@code userinput} by {@code parser} is successful without
     * checking the commandResult.
     */
    public static void assertParseNoFailure(Parser parser, String userInput) {
        try {
            Command command = parser.parse(userInput);
        } catch (IllegalArgumentException | ParseException e) {
            throw new IllegalArgumentException("Invalid userInput.", e);
        }
    }
}
