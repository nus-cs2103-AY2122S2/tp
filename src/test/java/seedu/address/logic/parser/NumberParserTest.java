package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.person.Flag.FLAG_INPUT_TRUE;
import static seedu.address.testutil.TypicalNames.NAME_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getDuplicatesHustleBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.FlagCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MeetCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Flag;
import seedu.address.model.person.Person;
import seedu.address.model.person.ScheduledMeeting;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;


public class NumberParserTest {

    private Command lastCommand;
    private NumberParser parser;
    private Model model = new ModelManager(getDuplicatesHustleBook(), new UserPrefs());

    @Test
    public void parse_editCommand_success() throws CommandException {
        String userInput = "1";
        Person editedPerson = new PersonBuilder().build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        lastCommand = new EditCommand(NAME_SECOND_PERSON, descriptor);
        lastCommand.execute(model);

        parser = new NumberParser(lastCommand);

        assertParseSuccess(parser, userInput, lastCommand);
    }

    @Test
    public void parse_deleteCommand_success() throws CommandException {
        String userInput = "1";
        lastCommand = new DeleteCommand(NAME_SECOND_PERSON);
        lastCommand.execute(model);

        parser = new NumberParser(lastCommand);

        assertParseSuccess(parser, userInput, lastCommand);
    }

    @Test
    public void parse_flagCommand_success() throws CommandException {
        String userInput = "1";
        Flag flag = new Flag(FLAG_INPUT_TRUE);

        lastCommand = new FlagCommand(NAME_SECOND_PERSON, flag);
        lastCommand.execute(model);

        parser = new NumberParser(lastCommand);

        assertParseSuccess(parser, userInput, lastCommand);
    }

    @Test
    public void parse_meetCommand_success() throws CommandException {
        String userInput = "1";
        ScheduledMeeting scheduledMeeting = new ScheduledMeeting();

        lastCommand = new MeetCommand(NAME_SECOND_PERSON, scheduledMeeting);
        lastCommand.execute(model);

        parser = new NumberParser(lastCommand);

        assertParseSuccess(parser, userInput, lastCommand);
    }

    @Test
    public void parse_invalidIndex() throws CommandException {
        String userInput = "3";
        ScheduledMeeting scheduledMeeting = new ScheduledMeeting();

        lastCommand = new MeetCommand(NAME_SECOND_PERSON, scheduledMeeting);
        lastCommand.execute(model);

        parser = new NumberParser(lastCommand);

        assertParseFailure(parser, userInput, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalidNumberInput() throws CommandException {
        String userInput = "1";

        lastCommand = new ListCommand(PREDICATE_SHOW_ALL_PERSONS);
        lastCommand.execute(model);

        parser = new NumberParser(lastCommand);

        assertParseFailure(parser, userInput, MESSAGE_UNKNOWN_COMMAND);
    }

}
