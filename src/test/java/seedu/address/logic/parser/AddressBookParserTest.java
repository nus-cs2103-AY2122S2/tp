package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COVID_STATUS_BOB;
import static seedu.address.logic.parser.AddressBookParser.MESSAGE_IRRELEVANT_PARAMETERS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COVID_STATUS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ArchiveCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.SummariseCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CovidStatus;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

/**
 * Contains helper methods for testing address book parsers.
 */
public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_clear_withExtraArgs() {
        String userInput = ClearCommand.COMMAND_WORD + " " + VALID_COVID_STATUS_BOB;
        assertThrows(ParseException.class, MESSAGE_IRRELEVANT_PARAMETERS, () -> parser.parseCommand(userInput));
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_exit_withExtraArgs() {
        String userInput = ExitCommand.COMMAND_WORD + " " + VALID_COVID_STATUS_BOB;
        assertThrows(ParseException.class, MESSAGE_IRRELEVANT_PARAMETERS, () -> parser.parseCommand(userInput));
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_help_withExtraArgs() {
        String userInput = HelpCommand.COMMAND_WORD + " " + VALID_COVID_STATUS_BOB;
        assertThrows(ParseException.class, MESSAGE_IRRELEVANT_PARAMETERS, () -> parser.parseCommand(userInput));
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
    }

    @Test
    public void parseCommand_list_withExtraArgs() {
        String userInput = ListCommand.COMMAND_WORD + " " + VALID_COVID_STATUS_BOB;
        assertThrows(ParseException.class, MESSAGE_IRRELEVANT_PARAMETERS, () -> parser.parseCommand(userInput));
    }

    @Test
    public void parseCommand_summarise() throws Exception {
        assertTrue(parser.parseCommand(SummariseCommand.COMMAND_WORD) instanceof SummariseCommand);
    }

    @Test
    public void parseCommand_summarise_withExtraArgs() {
        String userInput = SummariseCommand.COMMAND_WORD + " " + VALID_COVID_STATUS_BOB;
        assertThrows(ParseException.class, MESSAGE_IRRELEVANT_PARAMETERS, () -> parser.parseCommand(userInput));
    }

    @Test
    public void parseCommand_archive() throws Exception {
        assertTrue(parser.parseCommand(ArchiveCommand.COMMAND_WORD) instanceof ArchiveCommand);
    }

    @Test
    public void parseCommand_archive_withExtraArgs() {
        String userInput = ArchiveCommand.COMMAND_WORD + " " + VALID_COVID_STATUS_BOB;
        assertThrows(ParseException.class, MESSAGE_IRRELEVANT_PARAMETERS, () -> parser.parseCommand(userInput));
    }

    @Test
    public void parseCommand_filter() throws Exception {
        FilterCommand.FilterDescriptor filterDescriptorTest = new FilterCommand.FilterDescriptor();
        CovidStatus positive = new CovidStatus("positive");
        filterDescriptorTest.setCovidStatus(positive);
        FilterCommand command = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + PREFIX_COVID_STATUS + VALID_COVID_STATUS_BOB);
        assertEquals(new FilterCommand(filterDescriptorTest), command);
    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
    }

    @Test
    public void parseCommand_undo_withExtraArgs() {
        String userInput = UndoCommand.COMMAND_WORD + " " + VALID_COVID_STATUS_BOB;
        assertThrows(ParseException.class, MESSAGE_IRRELEVANT_PARAMETERS, () -> parser.parseCommand(userInput));
    }

    @Test
    public void parseCommand_redo() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
    }

    @Test
    public void parseCommand_redo_withExtraArgs() {
        String userInput = RedoCommand.COMMAND_WORD + " " + VALID_COVID_STATUS_BOB;
        assertThrows(ParseException.class, MESSAGE_IRRELEVANT_PARAMETERS, () -> parser.parseCommand(userInput));
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
