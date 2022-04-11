package seedu.address.model.attendance;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;

import seedu.address.model.DisplayType;
import seedu.address.model.Displayable;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;

/**
 * Contains a list of the attendance for a particular Student.
 */
public class Attendance implements Displayable {

    private static final DisplayType displayType = DisplayType.ATTENDANCE;

    private final ArrayList<Integer> attendanceList;
    private final Name studentName;
    private final NusNetId studentId;
    private final Comment comment;

    /**
     * Constructs an Attendance record.
     *
     * @param attendanceList the attendance records for each week.
     * @param studentName the Name of a student.
     * @param studentId the NusNetId of a student.
     * @param comment motes for a student.
     */
    public Attendance(ArrayList<Integer> attendanceList, Name studentName, NusNetId studentId, Comment comment) {
        requireAllNonNull(attendanceList, studentName, studentId, comment);
        this.attendanceList = attendanceList;
        this.studentName = studentName;
        this.studentId = studentId;
        this.comment = comment;
    }

    public ArrayList<Integer> getAttendanceList() {
        return attendanceList;
    }

    public Name getStudentName() {
        return studentName;
    }

    public NusNetId getStudentId() {
        return studentId;
    }

    public Comment getComment() {
        return comment;
    }

    public String getAttendanceStatusByWeek(int week) {
        int index = week - 1;
        if (attendanceList.get(index).equals(0)) {
            return "Status: Absent";
        }
        return "Status: Present";
    }

    /**
     * Marks attendance for a particular week.
     *
     * @param week The week to mark the attendance for.
     */
    public void markWeek(int week) {
        int index = week - 1;
        attendanceList.set(index, 1);
    }

    /**
     * Unmarks attendance for a particular week.
     *
     * @param week The week to unmark the attendance for.
     */
    public void unmarkWeek(int week) {
        int index = week - 1;
        attendanceList.set(index, 0);
    }

    /**
     * Adds a comment.
     *
     * @param comment The comment to add for.
     */
    public void addComment(Comment comment) {
        this.comment.setCommentString(comment.getCommentString());
    }

    @Override
    public DisplayType getDisplayType() {
        return displayType;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < attendanceList.size(); i++) {
            int index = i + 1;
            int present = attendanceList.get(i);

            stringBuilder.append("Week ")
                    .append(index)
                    .append(": ")
                    .append(present)
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        return this == o
                || (o instanceof Attendance
                && studentName.equals(((Attendance) o).studentName)
                && studentId.equals(((Attendance) o).studentId)
                && attendanceList.equals(((Attendance) o).attendanceList)
                && comment.equals(((Attendance) o).comment));
    }
}
