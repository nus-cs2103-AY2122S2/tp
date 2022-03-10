package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TagCommand;

public class TagCommandParserTest {
    private TagCommandParser parser = new TagCommandParser();
    private final String nonEmptyEducation = "Some education";
    private final String nonEmptyModule = "Some module";

    @Test
    public void parse_indexSpecified_success() {
        // have tags
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " "
                + PREFIX_EDUCATION + nonEmptyEducation + " "
                + PREFIX_MODULE + nonEmptyModule;
        TagCommand expectedCommand = new TagCommand(INDEX_FIRST_PERSON,
                TagCommandParser.convertToList(nonEmptyEducation, "education"), new ArrayList<>(),
                TagCommandParser.convertToList(nonEmptyModule, "module"), new ArrayList<>());
        assertParseSuccess(parser, userInput, expectedCommand);

        // no tags
        userInput = targetIndex.getOneBased() + " " + PREFIX_EDUCATION + " " + PREFIX_MODULE;
        expectedCommand = new TagCommand(INDEX_FIRST_PERSON, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>());
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, TagCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, TagCommand.COMMAND_WORD + " " + nonEmptyEducation, expectedMessage);
    }
}
