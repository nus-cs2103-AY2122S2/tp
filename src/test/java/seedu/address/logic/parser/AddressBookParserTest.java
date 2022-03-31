package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.LOG_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOG_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOG_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOG_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRIEND_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.EventPredicateListBuilder.DEFAULT_DATE_END_STRING;
import static seedu.address.testutil.EventPredicateListBuilder.DEFAULT_DATE_START_STRING;
import static seedu.address.testutil.EventPredicateListBuilder.DEFAULT_FRIEND_NAME_SUBSTRING_1;
import static seedu.address.testutil.EventPredicateListBuilder.DEFAULT_FRIEND_NAME_SUBSTRING_2;
import static seedu.address.testutil.EventPredicateListBuilder.DEFAULT_NAME_SUBSTRING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.AddLogCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteEventCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindEventCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ShowFriendCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.person.FriendName;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.EventPredicateListBuilder;
import seedu.address.testutil.EventUtil;
import seedu.address.testutil.FriendFilterPredicateBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        AddCommand commandByAlias = (AddCommand) parser.parseCommand(PersonUtil.getAddCommandAlias(person));
        assertEquals(new AddCommand(person), command);

        //to check if command alias works
        assertEquals(new AddCommand(person), commandByAlias);
    }

    @Test
    public void parseCommandByName_deletefriend() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(DeleteCommand.COMMAND_WORD + " n/Dummy Name");
        DeleteCommand commandByAlias = (DeleteCommand) parser.parseCommand(DeleteCommand.COMMAND_ALIAS + " n/Dummy Name");
        assertEquals(new DeleteCommand(new FriendName("Dummy Name")), command);

        //to check if command alias works
        assertEquals(new DeleteCommand(new FriendName("Dummy Name")), commandByAlias);
    }

    @Test
    public void parseCommandByIndex_deletefriend() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(DeleteCommand.COMMAND_WORD + " "
            + INDEX_FIRST_PERSON.getOneBased());
        DeleteCommand commandByAlias = (DeleteCommand) parser.parseCommand(DeleteCommand.COMMAND_ALIAS + " "
                + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);

        //to check if command alias works
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), commandByAlias);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));

        EditCommand commandByAlias = (EditCommand) parser.parseCommand(EditCommand.COMMAND_ALIAS + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));

        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);

        //to check if command alias works
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), commandByAlias);
    }

    @Test
    public void parseCommand_editEvent() throws Exception {
        Event event = new EventBuilder().build();
        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder(event).build();
        EditEventCommand command = (EditEventCommand) parser.parseCommand(EditEventCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + "n/Default Event " + "dt/1-1-2022 1500 " + "d/Default Description "
                + "af/Amy Koh af/Alex Yeoh");

        EditEventCommand commandByAlias = (EditEventCommand) parser.parseCommand(EditEventCommand.COMMAND_ALIAS + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + "n/Default Event " + "dt/1-1-2022 1500 " + "d/Default Description "
                + "af/Amy Koh af/Alex Yeoh");

        assertEquals(new EditEventCommand(INDEX_FIRST_PERSON, descriptor), command);

        //to check if command alias works
        assertEquals(new EditEventCommand(INDEX_FIRST_PERSON, descriptor), commandByAlias);

    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_NAME + "foo " + PREFIX_NAME + "bar " + PREFIX_NAME + "baz");
        assertEquals(new FindCommand(new FriendFilterPredicateBuilder()
                .withNameSubstring("foo").withNameSubstring("bar").withNameSubstring("baz").build()), command);

        FindCommand commandByAlias = (FindCommand) parser.parseCommand(FindCommand.COMMAND_ALIAS + " " + PREFIX_NAME + "foo " + PREFIX_NAME + "bar " + PREFIX_NAME + "baz");
        assertEquals(new FindCommand(new FriendFilterPredicateBuilder()
                .withNameSubstring("foo").withNameSubstring("bar").withNameSubstring("baz").build()), command);

    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listfriends() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_ALIAS) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_ALIAS + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_showFriendByName() throws Exception {
        Person person = new PersonBuilder().build();
        ShowFriendCommand command = (ShowFriendCommand) parser.parseCommand(ShowFriendCommand.COMMAND_WORD
                + " n/" + person.getName().fullName);
        assertEquals(new ShowFriendCommand(person.getName()), command);
    }

    @Test
    public void parseCommand_showFriendByIndex() throws Exception {
        Person person = new PersonBuilder().build();
        ShowFriendCommand command = (ShowFriendCommand) parser.parseCommand(ShowFriendCommand.COMMAND_WORD
                + " " + INDEX_FIRST_PERSON.getOneBased());

        assertEquals(new ShowFriendCommand(INDEX_FIRST_PERSON), command);

        ShowFriendCommand commandByAlias = (ShowFriendCommand) parser.parseCommand(ShowFriendCommand.COMMAND_ALIAS
                + " " + INDEX_FIRST_PERSON.getOneBased());

        //to check if command alias works
        assertEquals(new ShowFriendCommand(INDEX_FIRST_PERSON), commandByAlias);

    }


    @Test
    public void parseCommand_addLog() throws Exception {
        Index targetIndex = INDEX_FIRST_PERSON;

        // command
        String validAddLogCommand = AddLogCommand.COMMAND_WORD + " "
                + targetIndex.getOneBased() + LOG_TITLE_DESC + LOG_DESCRIPTION_DESC;

        //command by alias
        String validAddLogCommandByAlias = AddLogCommand.COMMAND_ALIAS + " "
                + targetIndex.getOneBased() + LOG_TITLE_DESC + LOG_DESCRIPTION_DESC;

        // expected command
        AddLogCommand.AddLogDescriptor descriptor = new AddLogCommand.AddLogDescriptor();
        descriptor.setNewTitle(VALID_LOG_TITLE);
        descriptor.setNewDescription(VALID_LOG_DESCRIPTION);
        AddLogCommand command = new AddLogCommand(INDEX_FIRST_PERSON, descriptor);

        assertEquals(command, parser.parseCommand(validAddLogCommand));
        assertEquals(command, parser.parseCommand(validAddLogCommandByAlias));
        assertTrue(parser.parseCommand(validAddLogCommand) instanceof AddLogCommand);
        assertTrue(parser.parseCommand(validAddLogCommandByAlias) instanceof AddLogCommand);
    }

    @Test
    public void parseCommand_editLog() throws Exception {
        // todo
    }

    @Test
    public void parseCommand_deleteLog() throws Exception {
        // todo

    }

    @Test
    public void parseCommand_addevent() throws Exception {
        Event event = new EventBuilder().build();
        AddEventCommand command = (AddEventCommand) parser.parseCommand(EventUtil.getAddEventCommand(event));
        AddEventCommand commandByAlias = (AddEventCommand) parser.parseCommand(EventUtil.getAddEventCommandAlias(event));

        assertEquals(new AddEventCommand(event), command);
        assertEquals(new AddEventCommand(event), commandByAlias);
    }

    @Test
    public void parseCommand_deleteevent() throws Exception {
        DeleteEventCommand command = (DeleteEventCommand) parser.parseCommand(
                DeleteEventCommand.COMMAND_WORD + " " + INDEX_FIRST_EVENT.getOneBased());
        DeleteEventCommand commandByAlias = (DeleteEventCommand) parser.parseCommand(
                DeleteEventCommand.COMMAND_ALIAS + " " + INDEX_FIRST_EVENT.getOneBased());
        assertEquals(new DeleteEventCommand(INDEX_FIRST_EVENT), command);
        assertEquals(new DeleteEventCommand(INDEX_FIRST_EVENT), commandByAlias);

    }

    @Test
    public void parseCommand_findevent() throws Exception {
        List<Predicate<Event>> predicates = new EventPredicateListBuilder().build();

        FindEventCommand command = (FindEventCommand) parser.parseCommand(
                FindEventCommand.COMMAND_WORD + " " + PREFIX_NAME + DEFAULT_NAME_SUBSTRING
                + " " + PREFIX_DATE_START + DEFAULT_DATE_START_STRING
                + " " + PREFIX_DATE_END + DEFAULT_DATE_END_STRING
                + " " + PREFIX_FRIEND_NAME + DEFAULT_FRIEND_NAME_SUBSTRING_1
                + " " + PREFIX_FRIEND_NAME + DEFAULT_FRIEND_NAME_SUBSTRING_2);
        assertEquals(new FindEventCommand(predicates), command);

        FindEventCommand commandByAlias = (FindEventCommand) parser.parseCommand(
                FindEventCommand.COMMAND_ALIAS + " " + PREFIX_NAME + DEFAULT_NAME_SUBSTRING
                        + " " + PREFIX_DATE_START + DEFAULT_DATE_START_STRING
                        + " " + PREFIX_DATE_END + DEFAULT_DATE_END_STRING
                        + " " + PREFIX_FRIEND_NAME + DEFAULT_FRIEND_NAME_SUBSTRING_1
                        + " " + PREFIX_FRIEND_NAME + DEFAULT_FRIEND_NAME_SUBSTRING_2);
        assertEquals(new FindEventCommand(predicates), commandByAlias);
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
