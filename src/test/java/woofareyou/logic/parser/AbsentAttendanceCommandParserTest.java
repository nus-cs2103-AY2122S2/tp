package woofareyou.logic.parser;

import static woofareyou.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static woofareyou.logic.commands.CommandTestUtil.ABSENT_DESC_BOB;
import static woofareyou.logic.commands.CommandTestUtil.ATTENDANCE_DATE_DESC_AMY;
import static woofareyou.logic.commands.CommandTestUtil.ATTENDANCE_DATE_DESC_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_ATTENDANCE_DATE_AMY;
import static woofareyou.logic.parser.CliSyntax.PREFIX_DATE;
import static woofareyou.logic.parser.CommandParserTestUtil.assertParseFailure;
import static woofareyou.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static woofareyou.testutil.TypicalIndexes.INDEX_FIRST_PET;

import org.junit.jupiter.api.Test;

import woofareyou.commons.core.Messages;
import woofareyou.commons.core.index.Index;
import woofareyou.logic.commands.AbsentAttendanceCommand;
import woofareyou.logic.commands.AbsentAttendanceCommand.AbsentAttendanceDescriptor;

public class AbsentAttendanceCommandParserTest {
    private static final Index TARGET_PET_INDEX = INDEX_FIRST_PET;
    private static final String NEGATIVE_INDEX_STUB = "-1";
    private static final String ZERO_INDEX_STUB = "0";
    private static final String ALPHABET_INDEX_STUB = "two";
    private static final String SYMBOL_INDEX_STUB = "%^&";

    private final AbsentAttendanceCommandParser parser = new AbsentAttendanceCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        AbsentAttendanceDescriptor expectedDescriptor = ABSENT_DESC_BOB;

        // no multiple fields
        assertParseSuccess(parser, TARGET_PET_INDEX.getOneBased() + ATTENDANCE_DATE_DESC_BOB,
            new AbsentAttendanceCommand(TARGET_PET_INDEX, expectedDescriptor));

        // multiple dates - last date accepted
        assertParseSuccess(parser, TARGET_PET_INDEX.getOneBased() + ATTENDANCE_DATE_DESC_AMY
            + ATTENDANCE_DATE_DESC_BOB, new AbsentAttendanceCommand(TARGET_PET_INDEX, expectedDescriptor));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String wrongCommandFormatMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            AbsentAttendanceCommand.MESSAGE_USAGE);

        // missing pet index
        assertParseFailure(parser, ATTENDANCE_DATE_DESC_AMY, wrongCommandFormatMessage);

        // missing attendance date
        assertParseFailure(parser, Integer.toString(TARGET_PET_INDEX.getOneBased()), wrongCommandFormatMessage);

        // all prefixes missing
        assertParseFailure(parser, TARGET_PET_INDEX.getOneBased() + VALID_ATTENDANCE_DATE_AMY,
            wrongCommandFormatMessage);
    }

    @Test
    public void parse_invalidIndex_failure() {
        // negative index
        assertParseFailure(parser, NEGATIVE_INDEX_STUB + ATTENDANCE_DATE_DESC_AMY,
            Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, ZERO_INDEX_STUB + ATTENDANCE_DATE_DESC_AMY,
            Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);

        // alphabetical index
        assertParseFailure(parser, ALPHABET_INDEX_STUB + ATTENDANCE_DATE_DESC_AMY,
            Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);

        // symbol index
        assertParseFailure(parser, SYMBOL_INDEX_STUB + ATTENDANCE_DATE_DESC_AMY,
            Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalidAttendanceDate_failure() {
        String[] wrongFormatUserInput = {"02-25-2022", "2-25-2022", "2-1-2022", "25/02/2022"};
        String[] invalidDatesUserInput = {"99-12-2022", "-1-12-2022", "31-02-2022"}; // leap year

        // wrong format
        for (String s : wrongFormatUserInput) {
            assertParseFailure(parser, TARGET_PET_INDEX.getOneBased() + " " + createAttendanceDateDesc(s),
                ParserUtil.MESSAGE_INVALID_ATTENDANCE_DATE);
        }

        // invalid dates
        for (String s : invalidDatesUserInput) {
            assertParseFailure(parser, TARGET_PET_INDEX.getOneBased() + " " + createAttendanceDateDesc(s),
                ParserUtil.MESSAGE_INVALID_ATTENDANCE_DATE);
        }
    }

    private static String createAttendanceDateDesc(String date) {
        return " " + PREFIX_DATE + date;
    }
}
