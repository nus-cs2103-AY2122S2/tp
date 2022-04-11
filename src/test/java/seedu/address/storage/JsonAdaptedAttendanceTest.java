package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAttendance.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttendances.BOBBY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attendance.Comment;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;

public class JsonAdaptedAttendanceTest {
    private static final List<String> INVALID_EMPTY_LIST = Arrays.asList(new String[]{});
    private static final List<String> INVALID_FILLED_LIST = Arrays.asList(new String[]{"1", "2", "0"});
    private static final String INVALID_NUSNETID = "e012345c";
    private static final String INVALID_NAME = "J@hn";

    private static final List<String> VALID_LIST = convertIntListToStringList(BOBBY.getAttendanceList());
    private static final String VALID_NAME = BOBBY.getStudentName().toString();
    private static final String VALID_NUSNETID = BOBBY.getStudentId().toString();
    private static final String VALID_COMMENT = BOBBY.getComment().toString();

    @Test
    public void toModelType_validAttendanceDetails_returnsAttendance() throws Exception {
        JsonAdaptedAttendance attendance = new JsonAdaptedAttendance(BOBBY);
        assertEquals(BOBBY, attendance.toModelType());
    }

    @Test
    public void toModelType_emptyList_throwsIllegalValueException() {
        JsonAdaptedAttendance attendance = new JsonAdaptedAttendance(VALID_NAME, VALID_NUSNETID,
                VALID_COMMENT, INVALID_EMPTY_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "list");
        assertThrows(IllegalValueException.class, expectedMessage, attendance::toModelType);
    }

    @Test
    public void toModelType_invalidFilledList_throwsIllegalValueException() {
        JsonAdaptedAttendance attendance = new JsonAdaptedAttendance(VALID_NAME, VALID_NUSNETID,
                VALID_COMMENT, INVALID_FILLED_LIST);
        String expectedMessage = "Attendance list is not valid!";
        assertThrows(IllegalValueException.class, expectedMessage, attendance::toModelType);
    }

    @Test
    public void toModelType_nullList_throwsIllegalValueException() {
        JsonAdaptedAttendance attendance = new JsonAdaptedAttendance(VALID_NAME, VALID_NUSNETID,
                VALID_COMMENT, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "list");
        assertThrows(IllegalValueException.class, expectedMessage, attendance::toModelType);
    }

    @Test
    public void toModelType_invalidNusNetId_throwsIllegalValueException() {
        JsonAdaptedAttendance attendance = new JsonAdaptedAttendance(VALID_NAME, INVALID_NUSNETID,
                VALID_COMMENT, VALID_LIST);
        String expectedMessage = NusNetId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attendance::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedAttendance attendance = new JsonAdaptedAttendance(INVALID_NAME, VALID_NUSNETID,
                VALID_COMMENT, VALID_LIST);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attendance::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedAttendance attendance =
                new JsonAdaptedAttendance(null, VALID_NUSNETID, VALID_COMMENT, VALID_LIST);
        String expectedMessage = String.format(JsonAdaptedAttendance.MISSING_FIELD_MESSAGE_FORMAT,
                Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, attendance::toModelType);
    }

    @Test
    public void toModelType_nullNusNetId_throwsIllegalValueException() {
        JsonAdaptedAttendance attendance =
                new JsonAdaptedAttendance(VALID_NAME, null, VALID_COMMENT, VALID_LIST);
        String expectedMessage = String.format(JsonAdaptedAttendance.MISSING_FIELD_MESSAGE_FORMAT,
                NusNetId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, attendance::toModelType);
    }

    @Test
    public void toModelType_nullComment_throwsIllegalValueException() {
        JsonAdaptedAttendance attendance =
                new JsonAdaptedAttendance(VALID_NAME, VALID_NUSNETID, null, VALID_LIST);
        String expectedMessage = String.format(JsonAdaptedAttendance.MISSING_FIELD_MESSAGE_FORMAT,
                Comment.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, attendance::toModelType);
    }

    private static ArrayList<String> convertIntListToStringList(ArrayList<Integer> arr) {
        List<String> strings = arr.stream()
                .map(x -> String.valueOf(x))
                .collect(Collectors.toList());
        ArrayList<String> strArr = new ArrayList<>(strings);
        return strArr;
    }
}
