package seedu.trackbeau.logic.parser;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.logic.commands.FindCommand;
import seedu.trackbeau.model.customer.SearchContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " name ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // Find name with no leading and trailing whitespaces
        FindCommand expectedFindNameCommand =
                new FindCommand(new SearchContainsKeywordsPredicate("getName",
                        0, Arrays.asList("alice", "bob")));
        assertParseSuccess(parser, "name alice bob", expectedFindNameCommand);

        // Find name with multiple whitespaces between keywords
        assertParseSuccess(parser, "name \n alice \n \t bob  \t", expectedFindNameCommand);

        // Find phone number with no leading and trailing whitespaces
        FindCommand expectedFindPhoneCommand =
                new FindCommand(new SearchContainsKeywordsPredicate("getPhone",
                        0, Arrays.asList("87438807", "99272758")));
        assertParseSuccess(parser, "phone 87438807 99272758", expectedFindPhoneCommand);

        // Find phone number with multiple whitespaces between keywords
        assertParseSuccess(parser, "phone \n 87438807 \n \t 99272758  \t", expectedFindPhoneCommand);

        // Find skin type with no leading and trailing whitespaces
        FindCommand expectedFindSkinTypeCommand =
                new FindCommand(new SearchContainsKeywordsPredicate("getSkinType",
                        0, Arrays.asList("sensitive", "oily")));
        assertParseSuccess(parser, "skintype sensitive oily", expectedFindSkinTypeCommand);

        // Find skin type with multiple whitespaces between keywords
        assertParseSuccess(parser, "skintype \n sensitive \n \t oily  \t", expectedFindSkinTypeCommand);

        // Find hair type with no leading and trailing whitespaces
        FindCommand expectedFindHairTypeCommand =
                new FindCommand(new SearchContainsKeywordsPredicate("getHairType",
                        0, Arrays.asList("dry", "oily")));
        assertParseSuccess(parser, "hairtype dry oily", expectedFindHairTypeCommand);

        // Find hair type with multiple whitespaces between keywords
        assertParseSuccess(parser, "hairtype \n dry \n \t oily  \t", expectedFindHairTypeCommand);

        // Find staff preference with no leading and trailing whitespaces
        FindCommand expectedFindStaffPrefCommand =
                new FindCommand(new SearchContainsKeywordsPredicate("getStaffs",
                        1, Arrays.asList("jason", "lee")));
        assertParseSuccess(parser, "staffpref jason lee", expectedFindStaffPrefCommand);

        // Find staff preference with multiple whitespaces between keywords
        assertParseSuccess(parser, "staffpref \n jason \n \t lee  \t", expectedFindStaffPrefCommand);

        // Find service preference with no leading and trailing whitespaces
        FindCommand expectedFindServicePrefCommand =
                new FindCommand(new SearchContainsKeywordsPredicate("getServices",
                        1, Arrays.asList("hair", "acne")));
        assertParseSuccess(parser, "servicepref hair acne", expectedFindServicePrefCommand);

        // Find service preference with multiple whitespaces between keywords
        assertParseSuccess(parser, "servicepref \n hair \n \t acne  \t", expectedFindServicePrefCommand);

        // Find allergies with no leading and trailing whitespaces
        FindCommand expectedFindAllergiesCommand =
                new FindCommand(new SearchContainsKeywordsPredicate("getAllergies",
                        1, Arrays.asList("nickle", "chemical")));
        assertParseSuccess(parser, "allergies nickle chemical", expectedFindAllergiesCommand);

        // Find allergies with multiple whitespaces between keywords
        assertParseSuccess(parser, "allergies \n nickle \n \t chemical \t", expectedFindAllergiesCommand);
    }

}
