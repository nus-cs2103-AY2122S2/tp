package seedu.address.testutil;

import static seedu.address.logic.commands.AttendancesTestUtil.VALID_ATTENDANCE_E8888888;
import static seedu.address.logic.commands.AttendancesTestUtil.VALID_ATTENDANCE_E9999999;
import static seedu.address.logic.commands.AttendancesTestUtil.VALID_COMMENT_E8888888;
import static seedu.address.logic.commands.AttendancesTestUtil.VALID_COMMENT_E9999999;
import static seedu.address.logic.commands.AttendancesTestUtil.VALID_NUSNETID_E8888888;
import static seedu.address.logic.commands.AttendancesTestUtil.VALID_NUSNETID_E9999999;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.attendance.Attendance;

public class TypicalAttendances {
    public static final Attendance BOBBY = new AttendanceBuilder()
            .withAttendanceList(new String[] {"1", "0", "0", "0", "0"}).withName("Bobby").withNusNetId("e0123456")
            .withComment("Was late to class").build();
    public static final Attendance ELISE = new AttendanceBuilder()
            .withAttendanceList(new String[] {"1", "0", "1", "0", "1"}).withName("Elise").withNusNetId("e6543210")
            .withComment("Answered a lot of questions").build();
    public static final Attendance SASHA = new AttendanceBuilder()
            .withAttendanceList(new String[] {"0", "0", "0", "0", "0"}).withName("Sasha").withNusNetId("e9012944")
            .withComment("Has not attended all class so far").build();
    public static final Attendance KEVIN = new AttendanceBuilder()
            .withAttendanceList(new String[] {"1", "1", "1", "1", "1"}).withName("Kevin").withNusNetId("e6070482")
            .withComment("Attentive during class").build();

    // Manually added - Attendance's details found in {@code AttendancesUtil}
    public static final Attendance PETER = new AttendanceBuilder().withAttendanceList(VALID_ATTENDANCE_E8888888)
            .withName("Peter").withNusNetId(VALID_NUSNETID_E8888888).withComment(VALID_COMMENT_E8888888).build();
    public static final Attendance FLASH = new AttendanceBuilder().withAttendanceList(VALID_ATTENDANCE_E9999999)
            .withName("Flash").withNusNetId(VALID_NUSNETID_E9999999).withComment(VALID_COMMENT_E9999999).build();

    private TypicalAttendances() {
    } // prevents instantiation

    public static List<Attendance> getTypicalAttendances() {
        return new ArrayList<>(Arrays.asList(BOBBY, ELISE, SASHA, KEVIN));
    }
}
