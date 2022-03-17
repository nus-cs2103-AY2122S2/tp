package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShowFriendCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;


public class ShowFriendCommandParserTest {

    private ShowFriendCommandParser parser = new ShowFriendCommandParser();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void parse_validName_returnsShowFriendCommand() {
        Person personToShow = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Name name = personToShow.getName();
        ShowFriendCommand showFriendCommand = new ShowFriendCommand(personToShow);

        assertParseSuccess(parser, " n/" + name.fullName, showFriendCommand);
    }


    @Test
    public void parse_noNameGiven_throwsParseException() {
        assertParseFailure(parser, " n/", Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidNameArgs_throwsParseException() {
        assertParseFailure(parser, " n/" + "$12345", Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validNameWithoutFlag_Args() {
        //valid name input but without 'n/' flag
        Person personToShow = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String fullName = personToShow.getName().fullName;
        assertParseFailure(parser, fullName, String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowFriendCommand.MESSAGE_USAGE));
    }


    @Test
    public void parse_invalidArgs_throwsParseException() {
        //random input of special characters
        assertParseFailure(parser, "@%^", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowFriendCommand.MESSAGE_USAGE));
    }


    @Test
    public void parse_noArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowFriendCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "   ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowFriendCommand.MESSAGE_USAGE));
    }

}
