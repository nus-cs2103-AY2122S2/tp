package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOG_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOG_DESCRIPTION_OTHER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOG_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOG_TITLE_PRECEDING_SPACE_TRIMMED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOG_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LOG;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_LOG;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditLogCommand;
import seedu.address.model.common.Description;
import seedu.address.model.person.FriendName;
import seedu.address.model.person.LogName;


public class EditLogCommandParserTest {
    private static final EditLogCommandParser parser = new EditLogCommandParser();
    private static final String MESSAGE_INVALID_FORMAT = EditLogCommandParser.MESSAGE_INVALID_FORMAT;
    private static final String MESSAGE_INVALID_TITLE = LogName.MESSAGE_CONSTRAINTS;
    private static final String MESSAGE_INVALID_INDEX = ParserUtil.MESSAGE_INVALID_INDEX;
    private static final String MESSAGE_INVALID_DESCRIPTION = Description.MESSAGE_CONSTRAINTS;


    @Test
    public void parse_invalidFormat_failure() {

        Index personIndex = INDEX_FIRST_PERSON;
        Index logIndex = INDEX_FIRST_LOG;
        String args;

        // missing person index/name -> fail
        args = " " + PREFIX_LOG_INDEX + logIndex.getOneBased()
                + " " + PREFIX_TITLE + VALID_LOG_TITLE;
        assertParseFailure(parser, args, MESSAGE_INVALID_FORMAT);

        // missing log index -> fail
        args = personIndex.getOneBased()
                + " " + PREFIX_TITLE + VALID_LOG_TITLE;
        assertParseFailure(parser, args, MESSAGE_INVALID_FORMAT);

        // missing both new title and new description
        args = personIndex.getOneBased() + " " + PREFIX_LOG_INDEX + logIndex.getOneBased();
        assertParseFailure(parser, args, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidFieldValues_failure() {

        Index personIndex = INDEX_FIRST_PERSON;
        Index logIndex = INDEX_FIRST_LOG;
        String args;

        // invalid new title
        args = personIndex.getOneBased() + " " + PREFIX_LOG_INDEX + logIndex.getOneBased() + " "
                + PREFIX_TITLE + "";
        assertParseFailure(parser, args, MESSAGE_INVALID_TITLE);

        // invalid person index
        args = -1 + " " + PREFIX_LOG_INDEX + logIndex.getOneBased()
                + " " + PREFIX_TITLE + VALID_LOG_TITLE;
        assertParseFailure(parser, args, MESSAGE_INVALID_INDEX);

        // invalid log index
        args = personIndex.getOneBased() + " " + PREFIX_LOG_INDEX + "-1"
                + " " + PREFIX_TITLE + VALID_LOG_TITLE;
        assertParseFailure(parser, args, MESSAGE_INVALID_INDEX);

        // invalid new description
        args = personIndex.getOneBased() + " " + PREFIX_LOG_INDEX + logIndex.getOneBased() + " "
                + PREFIX_DESCRIPTION + "   "; // only spaces
        assertParseFailure(parser, args, MESSAGE_INVALID_DESCRIPTION);

    }

    @Test
    public void parse_invalidPrefixesPresent_failure() {

        String args;

        // random garbage input
        args = "1 i/ string";
        assertParseFailure(parser, args, MESSAGE_INVALID_FORMAT);
        args = "1 i/string /g something else";
        assertParseFailure(parser, args, MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {

        Index targetIndex = INDEX_FIRST_PERSON;
        Index targetLog = INDEX_FIRST_LOG;
        Index otherLog = INDEX_SECOND_LOG;
        String firstTitle = VALID_LOG_TITLE;
        String otherTitle = VALID_LOG_TITLE_PRECEDING_SPACE_TRIMMED;
        String firstDescription = VALID_LOG_DESCRIPTION;
        String otherDescription = VALID_LOG_DESCRIPTION_OTHER;

        // ===== WITH INDEX =====
        String args = targetIndex.getOneBased() + " "
                + PREFIX_LOG_INDEX + targetLog.getOneBased() + " "
                + PREFIX_LOG_INDEX + otherLog.getOneBased() + " "
                + PREFIX_TITLE + firstTitle + " "
                + PREFIX_TITLE + otherTitle + " "
                + PREFIX_DESCRIPTION + firstDescription + " "
                + PREFIX_DESCRIPTION + otherDescription;

        // expected
        EditLogCommand.EditLogDescriptor descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(otherTitle);
        descriptor.setNewDescription(otherDescription);
        EditLogCommand expectedCommand = new EditLogCommand(targetIndex, otherLog, descriptor);

        assertParseSuccess(parser, args, expectedCommand);

        // ===== WITH NAME =====
        // expected to take last name
        FriendName targetName = new FriendName(VALID_NAME_BOB);
        FriendName otherName = new FriendName(VALID_NAME_AMY);
        args = NAME_DESC_BOB + " " + NAME_DESC_AMY + " "
                + PREFIX_LOG_INDEX + targetLog.getOneBased() + " "
                + PREFIX_LOG_INDEX + otherLog.getOneBased() + " "
                + PREFIX_TITLE + firstTitle + " "
                + PREFIX_TITLE + otherTitle + " "
                + PREFIX_DESCRIPTION + firstDescription + " "
                + PREFIX_DESCRIPTION + otherDescription;

        // expected
        descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(otherTitle);
        descriptor.setNewDescription(otherDescription);
        expectedCommand = new EditLogCommand(otherName, otherLog, descriptor);

        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedInvalidFieldsButLastValid_success() {

        Index targetIndex = INDEX_FIRST_PERSON;
        String invalidLogIndex = "-1";
        Index otherLog = INDEX_SECOND_LOG;
        String otherTitle = VALID_LOG_TITLE_PRECEDING_SPACE_TRIMMED;
        String otherDescription = VALID_LOG_DESCRIPTION_OTHER;

        // ===== WITH INDEX =====
        String args = targetIndex.getOneBased() + " "
                + PREFIX_LOG_INDEX + invalidLogIndex + " "
                + PREFIX_LOG_INDEX + otherLog.getOneBased() + " "
                + PREFIX_TITLE + otherTitle + " "
                + PREFIX_DESCRIPTION + otherDescription;

        // expected
        EditLogCommand.EditLogDescriptor descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(otherTitle);
        descriptor.setNewDescription(otherDescription);
        EditLogCommand expectedCommand = new EditLogCommand(targetIndex, otherLog, descriptor);

        assertParseSuccess(parser, args, expectedCommand);

        // ===== WITH NAME =====
        // expected to take last name
        FriendName otherName = new FriendName(VALID_NAME_AMY);
        args = NAME_DESC_BOB + " " + NAME_DESC_AMY + " "
                + PREFIX_LOG_INDEX + invalidLogIndex + " "
                + PREFIX_LOG_INDEX + otherLog.getOneBased() + " "
                + PREFIX_TITLE + otherTitle + " "
                + PREFIX_DESCRIPTION + otherDescription;

        // expected
        descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(otherTitle);
        descriptor.setNewDescription(otherDescription);
        expectedCommand = new EditLogCommand(otherName, otherLog, descriptor);

        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_validFormat_success() {

        EditLogCommand expectedCommand;
        String args;
        String targetName = VALID_NAME_AMY;
        EditLogCommand.EditLogDescriptor descriptor;
        Index targetIndex = INDEX_FIRST_PERSON;
        Index targetLogIndex = INDEX_FIRST_LOG;
        String validTitle = VALID_LOG_TITLE;
        String validDescription = VALID_LOG_DESCRIPTION;

        // ===== WITH INDEX =====

        args = targetIndex.getOneBased() + " "
                + PREFIX_LOG_INDEX + targetLogIndex.getOneBased() + " "
                + PREFIX_TITLE + validTitle + " "
                + PREFIX_DESCRIPTION + validDescription;

        // expected
        descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(validTitle);
        descriptor.setNewDescription(validDescription);
        expectedCommand = new EditLogCommand(targetIndex, targetLogIndex, descriptor);
        assertParseSuccess(parser, args, expectedCommand);

        // WITH NAME
        args = NAME_DESC_AMY + " "
                + PREFIX_LOG_INDEX + targetLogIndex.getOneBased() + " "
                + PREFIX_TITLE + validTitle + " "
                + PREFIX_DESCRIPTION + validDescription;

        // expected
        descriptor = new EditLogCommand.EditLogDescriptor();
        descriptor.setNewTitle(validTitle);
        descriptor.setNewDescription(validDescription);
        expectedCommand = new EditLogCommand(new FriendName(targetName), targetLogIndex, descriptor);
        assertParseSuccess(parser, args, expectedCommand);

    }
}
