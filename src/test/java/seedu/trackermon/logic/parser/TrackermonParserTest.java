package seedu.trackermon.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackermon.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.trackermon.testutil.Assert.assertThrows;
import static seedu.trackermon.testutil.TypicalIndexes.INDEX_FIRST_SHOW;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.trackermon.logic.commands.AddCommand;
import seedu.trackermon.logic.commands.DeleteCommand;
import seedu.trackermon.logic.commands.EditCommand;
import seedu.trackermon.logic.commands.EditCommand.EditShowDescriptor;
import seedu.trackermon.logic.commands.ExitCommand;
import seedu.trackermon.logic.commands.FindCommand;
import seedu.trackermon.logic.commands.HelpCommand;
import seedu.trackermon.logic.commands.ListCommand;
import seedu.trackermon.logic.parser.exceptions.ParseException;
import seedu.trackermon.model.show.Show;
import seedu.trackermon.model.show.ShowContainsKeywordsPredicate;
import seedu.trackermon.testutil.EditShowDescriptorBuilder;
import seedu.trackermon.testutil.ShowBuilder;
import seedu.trackermon.testutil.ShowUtil;

/**
 * Contains unit tests for {@code TrackermonParser}.
 */
public class TrackermonParserTest {
    private static final String UNKNOWN_COMMAND_HELP = String.format(MESSAGE_UNKNOWN_COMMAND,
            HelpCommand.MESSAGE_USAGE);

    private final TrackermonParser parser = new TrackermonParser();

    /**
     * Tests the parsing of {@code AddCommand}.
     * @throws Exception for invalid input string.
     */
    @Test
    public void parseCommand_add() throws Exception {
        Show show = new ShowBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ShowUtil.getAddCommand(show));
        assertEquals(new AddCommand(show), command);
    }

    /**
     * Tests the parsing of {@code DeleteCommand}.
     * @throws Exception for invalid input string.
     */
    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_SHOW.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_SHOW), command);
    }

    /**
     * Tests the parsing of {@code EditCommand}.
     * @throws Exception for invalid input string.
     */
    @Test
    public void parseCommand_edit() throws Exception {
        Show show = new ShowBuilder().build();
        EditShowDescriptor descriptor = new EditShowDescriptorBuilder(show).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_SHOW.getOneBased() + " " + ShowUtil.getEditShowDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_SHOW, descriptor), command);
    }

    /**
     * Tests the parsing of {@code ExitCommand}.
     * @throws Exception for invalid input string.
     */
    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    /**
     * Tests the parsing of {@code FindCommand}.
     * @throws Exception for invalid input string.
     */
    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new ShowContainsKeywordsPredicate(keywords)), command);
    }

    /**
     * Tests the parsing of {@code HelpCommand}.
     * @throws Exception for invalid input string.
     */
    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    /**
     * Tests the parsing of {@code ListCommand}.
     * @throws Exception for invalid input string.
     */
    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    /**
     * Tests the parsing of invalid input.
     */
    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, UNKNOWN_COMMAND_HELP, () -> parser.parseCommand(""));
    }

    /**
     * Tests the parsing of invalid command.
     */
    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, UNKNOWN_COMMAND_HELP, () -> parser.parseCommand("unknownCommand"));
    }
}
