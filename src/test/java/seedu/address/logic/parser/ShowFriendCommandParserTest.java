package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShowFriendCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.FriendName;
import seedu.address.model.person.Person;


public class ShowFriendCommandParserTest {

    private ShowFriendCommandParser parser = new ShowFriendCommandParser();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void parse_validName_returnsShowFriendCommand() {
        Person personToShow = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        FriendName name = personToShow.getName();
        ShowFriendCommand showFriendCommand = new ShowFriendCommand(name);

        assertParseSuccess(parser, " n/" + name.fullName, showFriendCommand);
    }

    @Test
    public void parse_validArgsIndex_returnsShowFriendCommand() {
        assertParseSuccess(parser, "1", new ShowFriendCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "-1", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


    @Test
    public void parse_noNameGiven_throwsParseException() {
        assertParseFailure(parser, " n/", FriendName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidNameArgs_throwsParseException() {
        assertParseFailure(parser, " n/" + "$12345", FriendName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validNameWithoutFlag_throwsParseException() {
        //valid name input but without 'n/' flag
        Person personToShow = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String fullName = personToShow.getName().fullName;
        assertParseFailure(parser, fullName, String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowFriendCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleNamesOnlyLastValidNameShown_success() {
        Person personOneToShow = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personTwoToShow = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        String fullNameOne = personOneToShow.getName().fullName;
        String fullNameTwo = personTwoToShow.getName().fullName;
        ShowFriendCommand showFriendCommand = new ShowFriendCommand(new FriendName(fullNameTwo));
        assertParseSuccess(parser, " n/" + fullNameOne + " n/" + fullNameTwo, showFriendCommand);
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
