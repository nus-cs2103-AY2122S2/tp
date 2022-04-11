package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.Comment;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;

public class AttendanceBuilder {

    public static final String[] DEFAULT_ATTENDANCE = { "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
    public static final String DEFAULT_NAME = "Default Student";
    public static final String DEFAULT_NUSNETID = "e0123456";
    public static final String DEFAULT_COMMENT = "Has not handed in work";

    private ArrayList<Integer> attendanceList;
    private Name name;
    private NusNetId nusNetId;
    private Comment comment;

    /**
     * Creates a {@code AttendanceBuilder} with the default details.
     */
    public AttendanceBuilder() {
        attendanceList = convertStringArrToIntList(DEFAULT_ATTENDANCE);
        name = new Name(DEFAULT_NAME);
        nusNetId = new NusNetId(DEFAULT_NUSNETID);
        comment = new Comment(DEFAULT_COMMENT);
    }

    /**
     * Creates a {@code AttendanceBuilder} with the {@code attendance}'s details.
     */
    public AttendanceBuilder(Attendance attendance) {
        attendanceList = attendance.getAttendanceList();
        name = attendance.getStudentName();
        nusNetId = attendance.getStudentId();
        comment = attendance.getComment();
    }

    /**
     * Sets the {@code ArrayList<String>} of the {@code Attendance} that we are building.
     */
    public AttendanceBuilder withAttendanceList(String[] assessmentList) {
        this.attendanceList = convertStringArrToIntList(assessmentList);
        return this;
    }

    /**
     * Sets the {@code NusNetId} of the {@code Attendance} that we are building.
     */
    public AttendanceBuilder withNusNetId(String nusNetId) {
        this.nusNetId = new NusNetId(nusNetId);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Attendance} that we are building.
     */
    public AttendanceBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Comment} of the {@code Attendance} that we are building.
     */
    public AttendanceBuilder withComment(String comment) {
        this.comment = new Comment(comment);
        return this;
    }

    private ArrayList<Integer> convertStringArrToIntList(String[] arr) {
        return new ArrayList<>(Arrays.asList(arr)
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }

    public Attendance build() {
        return new Attendance(attendanceList, name, nusNetId, comment);
    };
}
