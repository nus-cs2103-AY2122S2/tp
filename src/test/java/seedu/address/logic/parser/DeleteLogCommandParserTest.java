package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CliSyntax.FLAG_ALL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOG_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.DeleteLogCommandParser.MESSAGE_INVALID_FORMAT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LOG;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_LOG;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteLogCommand;
import seedu.address.model.person.FriendName;

public class DeleteLogCommandParserTest {

    private static final DeleteLogCommandParser parser = new DeleteLogCommandParser();
    private static final String MESSAGE_INVALID_INDEX = ParserUtil.MESSAGE_INVALID_INDEX;

    @Test
    public void parse_invalidFormat_failure() {

        Index personIndex = INDEX_FIRST_PERSON;
        Index logIndex = INDEX_FIRST_LOG;

        String args;

        // missing person index/name without all flag
        args = "" + PREFIX_LOG_INDEX + logIndex.getOneBased();
        assertParseFailure(parser, args, MESSAGE_INVALID_FORMAT);

        // both index and name present
        // valid person (name) and log index but all flag present
        args = personIndex.getOneBased() + " "
                + NAME_DESC_AMY + PREFIX_LOG_INDEX + logIndex.getOneBased();
        assertParseFailure(parser, args, MESSAGE_INVALID_FORMAT);

        // missing log index without all flag
        args = "" + personIndex.getOneBased();
        assertParseFailure(parser, args, MESSAGE_INVALID_FORMAT);

        // valid person (index) and log index but all flag present
        args = personIndex.getOneBased() + " " + PREFIX_LOG_INDEX
                + logIndex.getOneBased() + " " + FLAG_ALL;
        assertParseFailure(parser, args, MESSAGE_INVALID_FORMAT);

        // valid person (name) and log index but all flag present
        args = NAME_DESC_AMY + " " + PREFIX_LOG_INDEX + logIndex.getOneBased() + " " + FLAG_ALL;
        assertParseFailure(parser, args, MESSAGE_INVALID_FORMAT);

        // arguments after all flag
        args = FLAG_ALL + "some garbage";
        assertParseFailure(parser, args, MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_invalidPersonIndex_failure() {

        Index logIndex = INDEX_FIRST_LOG;

        String args;

        // empty string person index
        args = "" + PREFIX_LOG_INDEX + logIndex.getOneBased();
        assertParseFailure(parser, args, MESSAGE_INVALID_FORMAT);

        // non-numeric person index
        args = "qwerty" + " " + FLAG_ALL;
        assertParseFailure(parser, args, MESSAGE_INVALID_INDEX);
        args = "qwerty" + " " + PREFIX_LOG_INDEX + logIndex.getOneBased();
        assertParseFailure(parser, args, MESSAGE_INVALID_INDEX);

        // negative person index
        args = "-5" + " " + PREFIX_LOG_INDEX + logIndex.getOneBased();
        assertParseFailure(parser, args, MESSAGE_INVALID_INDEX);
        args = "-5" + " " + FLAG_ALL;
        assertParseFailure(parser, args, MESSAGE_INVALID_INDEX);

    }

    @Test
    public void parse_invalidPersonName_failure() {

        String args;

        args = INVALID_NAME_DESC + PREFIX_LOG_INDEX + INDEX_FIRST_LOG.getOneBased();
        assertParseFailure(parser, args, MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_invalidLogIndex_failure() {

        Index personIndex = INDEX_FIRST_PERSON;

        String args;

        // ===== INDEX BASED =====

        // empty string log index
        args = personIndex.getOneBased() + " " + PREFIX_LOG_INDEX + "";
        assertParseFailure(parser, args, MESSAGE_INVALID_INDEX);

        // non-numeric log index
        args = personIndex.getOneBased() + " " + PREFIX_LOG_INDEX + "qwerty";
        assertParseFailure(parser, args, MESSAGE_INVALID_INDEX);

        // negative log index
        args = personIndex.getOneBased() + " " + PREFIX_LOG_INDEX + "-5";
        assertParseFailure(parser, args, MESSAGE_INVALID_INDEX);

        // ===== NAME BASED =====
        // empty string log index
        args = NAME_DESC_AMY + " " + PREFIX_LOG_INDEX + "";
        assertParseFailure(parser, args, MESSAGE_INVALID_INDEX);

        // non-numeric log index
        args = NAME_DESC_AMY + " " + PREFIX_LOG_INDEX + "qwerty";
        assertParseFailure(parser, args, MESSAGE_INVALID_INDEX);

        // negative log index
        args = NAME_DESC_AMY + " " + PREFIX_LOG_INDEX + "-5";
        assertParseFailure(parser, args, MESSAGE_INVALID_INDEX);

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
    public void parse_validFormat_success() {

        DeleteLogCommand expectedCommand;
        Index personIndex = INDEX_FIRST_PERSON;
        Index logIndex = INDEX_FIRST_LOG;
        String args;

        // ===== PERSON INDEX =====
        // case 1: person and log
        args = personIndex.getOneBased() + " " + PREFIX_LOG_INDEX + logIndex.getOneBased();
        expectedCommand = new DeleteLogCommand(true, false, personIndex, logIndex);
        assertParseSuccess(parser, args, expectedCommand);

        // case 2: all of person
        args = personIndex.getOneBased() + " " + FLAG_ALL;
        expectedCommand = new DeleteLogCommand(true, true, personIndex, null);
        assertParseSuccess(parser, args, expectedCommand);

        // ===== PERSON NAME =====
        FriendName targetName = new FriendName(VALID_NAME_AMY);
        // case 1: person and log
        args = NAME_DESC_AMY + " " + PREFIX_LOG_INDEX + logIndex.getOneBased();
        expectedCommand = new DeleteLogCommand(true, false, targetName, logIndex);
        assertParseSuccess(parser, args, expectedCommand);

        // case 2: all of person
        args = NAME_DESC_AMY + " " + FLAG_ALL;
        expectedCommand = new DeleteLogCommand(true, true, targetName, null);
        assertParseSuccess(parser, args, expectedCommand);

        // ===== SHARED =====
        // case 1: all persons
        args = " " + FLAG_ALL;
        expectedCommand = new DeleteLogCommand(true);
        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        DeleteLogCommand expectedCommand;
        Index personIndex = INDEX_FIRST_PERSON;
        Index logIndex1 = INDEX_FIRST_LOG;
        Index logIndex2 = INDEX_SECOND_LOG;
        String args;

        // case 1: logs
        args = personIndex.getOneBased() + " "
                + PREFIX_LOG_INDEX + logIndex1.getOneBased() + " "
                + PREFIX_LOG_INDEX + logIndex2.getOneBased() + " ";
        expectedCommand = new DeleteLogCommand(true, false, personIndex, logIndex2);
        assertParseSuccess(parser, args, expectedCommand);

        // case 2: multiple person names
        FriendName targetName = new FriendName(VALID_NAME_BOB);
        args = NAME_DESC_AMY + " " + NAME_DESC_BOB + " "
                + PREFIX_LOG_INDEX + logIndex1.getOneBased();
        expectedCommand = new DeleteLogCommand(true, false, targetName, logIndex1);
        assertParseSuccess(parser, args, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedInvalidFieldsButLastValid_success() {
        DeleteLogCommand expectedCommand;
        Index personIndex = INDEX_SECOND_PERSON;
        String invalidLogIndex = " ";
        Index logIndex = INDEX_SECOND_LOG;
        String args;

        // case 1: log
        args = personIndex.getOneBased() + " "
                + PREFIX_LOG_INDEX + invalidLogIndex + " "
                + PREFIX_LOG_INDEX + logIndex.getOneBased() + " ";
        expectedCommand = new DeleteLogCommand(true, false, personIndex, logIndex);
        assertParseSuccess(parser, args, expectedCommand);

        // case 2: multiple person names
        FriendName targetName = new FriendName(VALID_NAME_BOB);
        args = INVALID_NAME_DESC + " " + NAME_DESC_BOB + " "
                + PREFIX_LOG_INDEX + logIndex.getOneBased();
        expectedCommand = new DeleteLogCommand(true, false, targetName, logIndex);
        assertParseSuccess(parser, args, expectedCommand);
    }

}
