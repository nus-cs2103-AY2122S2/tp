package seedu.address.model.student;

import java.util.List;

import seedu.address.model.lab.LabList;

/**
 * Student's details for {@code view} command
 */

public class ViewDetails {

    private final Name name;

    private final LabList labList;

    /**
     * Constructs a {@code StudentDetail} of a {@code Student}
     * @param name Name of student
     * @param labList Labs for student
     */
    private ViewDetails(Name name, LabList labList) {
        this.name = name;
        this.labList = labList;
    }

    public ViewDetails(Student student) {
        this(student.getName(), student.getLabs());
    }

    public String getName() {
        return name.fullName;
    }

    public String getDescription() {
        return getName() + "\n" + getLabs();
    }

    public String getLabs() {
        List<String> labDetails = labList.getLabDetails();
        StringBuilder sb = new StringBuilder();

        labDetails.forEach(sb::append);

        return sb.toString();

    }

}
