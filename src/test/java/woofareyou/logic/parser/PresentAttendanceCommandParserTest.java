package woofareyou.logic.parser;

import static woofareyou.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static woofareyou.logic.commands.CommandTestUtil.ATTENDANCE_DATE_DESC_AMY;
import static woofareyou.logic.commands.CommandTestUtil.ATTENDANCE_DATE_DESC_BOB;
import static woofareyou.logic.commands.CommandTestUtil.ATTENDANCE_DATE_DESC_CHARLIE;
import static woofareyou.logic.commands.CommandTestUtil.DROPOFF_TIME_DESC_AMY;
import static woofareyou.logic.commands.CommandTestUtil.DROPOFF_TIME_DESC_BOB;
import static woofareyou.logic.commands.CommandTestUtil.PICKUP_TIME_DESC_AMY;
import static woofareyou.logic.commands.CommandTestUtil.PICKUP_TIME_DESC_BOB;
import static woofareyou.logic.commands.CommandTestUtil.PRESENT_DESC_WITHOUT_TRANSPORT_CHARLIE;
import static woofareyou.logic.commands.CommandTestUtil.PRESENT_DESC_WITH_TRANSPORT_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_ATTENDANCE_DATE_AMY;
import static woofareyou.logic.commands.CommandTestUtil.VALID_DROPOFF_TIME_AMY;
import static woofareyou.logic.commands.CommandTestUtil.VALID_PICKUP_TIME_AMY;
import static woofareyou.logic.parser.CliSyntax.PREFIX_DATE;
import static woofareyou.logic.parser.CliSyntax.PREFIX_DROPOFF;
import static woofareyou.logic.parser.CliSyntax.PREFIX_PICKUP;
import static woofareyou.logic.parser.CommandParserTestUtil.assertParseFailure;
import static woofareyou.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static woofareyou.testutil.TypicalIndexes.INDEX_FIRST_PET;

import org.junit.jupiter.api.Test;

import woofareyou.commons.core.Messages;
import woofareyou.commons.core.index.Index;
import woofareyou.logic.commands.PresentAttendanceCommand;
import woofareyou.logic.commands.PresentAttendanceCommand.PresentAttendanceDescriptor;

public class PresentAttendanceCommandParserTest {
    private static final Index TARGET_PET_INDEX = INDEX_FIRST_PET;
    private static final String NEGATIVE_INDEX_STUB = "-1";
    private static final String ZERO_INDEX_STUB = "0";
    private static final String ALPHABET_INDEX_STUB = "two";
    private static final String SYMBOL_INDEX_STUB = "%^&";

    private final PresentAttendanceCommandParser parser = new PresentAttendanceCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        PresentAttendanceDescriptor expectedDescriptor = PRESENT_DESC_WITH_TRANSPORT_BOB;

        // no multiple fields
        assertParseSuccess(parser, TARGET_PET_INDEX.getOneBased() + ATTENDANCE_DATE_DESC_BOB
                + PICKUP_TIME_DESC_BOB + DROPOFF_TIME_DESC_BOB,
            new PresentAttendanceCommand(TARGET_PET_INDEX, expectedDescriptor));

        // multiple dates - last date accepted
        assertParseSuccess(parser, TARGET_PET_INDEX.getOneBased() + ATTENDANCE_DATE_DESC_AMY
                + ATTENDANCE_DATE_DESC_BOB + PICKUP_TIME_DESC_BOB + DROPOFF_TIME_DESC_BOB,
            new PresentAttendanceCommand(TARGET_PET_INDEX, expectedDescriptor));

        // multiple pick-up times - last pick-up time accepted
        assertParseSuccess(parser, TARGET_PET_INDEX.getOneBased() + ATTENDANCE_DATE_DESC_BOB
                + PICKUP_TIME_DESC_AMY + PICKUP_TIME_DESC_BOB + DROPOFF_TIME_DESC_BOB,
            new PresentAttendanceCommand(TARGET_PET_INDEX, expectedDescriptor));

        // multiple drop-off times - last drop-off time accepted
        assertParseSuccess(parser, TARGET_PET_INDEX.getOneBased() + ATTENDANCE_DATE_DESC_BOB
                + PICKUP_TIME_DESC_BOB + DROPOFF_TIME_DESC_AMY + DROPOFF_TIME_DESC_BOB,
            new PresentAttendanceCommand(TARGET_PET_INDEX, expectedDescriptor));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        PresentAttendanceDescriptor expectedDescriptor = PRESENT_DESC_WITHOUT_TRANSPORT_CHARLIE;

        // no pick-up AND drop-off times
        assertParseSuccess(parser, TARGET_PET_INDEX.getOneBased() + ATTENDANCE_DATE_DESC_CHARLIE,
            new PresentAttendanceCommand(TARGET_PET_INDEX, expectedDescriptor));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String wrongCommandFormatMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            PresentAttendanceCommand.MESSAGE_USAGE);

        // missing pet index
        assertParseFailure(parser, ATTENDANCE_DATE_DESC_AMY + PICKUP_TIME_DESC_AMY + DROPOFF_TIME_DESC_AMY,
            wrongCommandFormatMessage);

        // missing attendance date
        assertParseFailure(parser, TARGET_PET_INDEX.getOneBased() + PICKUP_TIME_DESC_AMY + DROPOFF_TIME_DESC_AMY,
            wrongCommandFormatMessage);

        // all prefixes missing
        assertParseFailure(parser, TARGET_PET_INDEX.getOneBased() + VALID_ATTENDANCE_DATE_AMY
            + VALID_PICKUP_TIME_AMY + VALID_DROPOFF_TIME_AMY, wrongCommandFormatMessage);
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

    @Test
    public void parse_invalidPickUpTime_failure() {
        String[] wrongFormatUserInput = {"9am, 09.00, 9:00, 9, 08:00:17"};
        String[] invalidTimesUserInput = {"-1:00, 26:00"};

        // wrong format
        for (String s : wrongFormatUserInput) {
            assertParseFailure(parser, TARGET_PET_INDEX.getOneBased() + ATTENDANCE_DATE_DESC_AMY
                    + createPickUpTimeDesc(s) + DROPOFF_TIME_DESC_AMY,
                ParserUtil.MESSAGE_INVALID_PICKUP_TIME);
        }

        // invalid times
        for (String s : invalidTimesUserInput) {
            assertParseFailure(parser, TARGET_PET_INDEX.getOneBased() + ATTENDANCE_DATE_DESC_AMY
                    + createPickUpTimeDesc(s) + DROPOFF_TIME_DESC_AMY,
                ParserUtil.MESSAGE_INVALID_PICKUP_TIME);
        }
    }

    @Test
    public void parse_invalidDropOffTime_failure() {
        String[] wrongFormatUserInput = {"9pm, 09.00, 9, 21:00:17"};
        String[] invalidTimesUserInput = {"-1:00, 26:00"};

        // wrong format
        for (String s : wrongFormatUserInput) {
            assertParseFailure(parser, TARGET_PET_INDEX.getOneBased() + ATTENDANCE_DATE_DESC_AMY
                    + PICKUP_TIME_DESC_AMY + createDropOffTimeDesc(s),
                ParserUtil.MESSAGE_INVALID_DROPOFF_TIME);
        }

        // invalid times
        for (String s : invalidTimesUserInput) {
            assertParseFailure(parser, TARGET_PET_INDEX.getOneBased() + ATTENDANCE_DATE_DESC_AMY
                    + PICKUP_TIME_DESC_AMY + createDropOffTimeDesc(s),
                ParserUtil.MESSAGE_INVALID_DROPOFF_TIME);
        }
    }

    private static String createAttendanceDateDesc(String date) {
        return " " + PREFIX_DATE + date;
    }

    private static String createPickUpTimeDesc(String pickUpTime) {
        return " " + PREFIX_PICKUP + pickUpTime;
    }

    private static String createDropOffTimeDesc(String dropOffTime) {
        return " " + PREFIX_DROPOFF + dropOffTime;
    }
}
