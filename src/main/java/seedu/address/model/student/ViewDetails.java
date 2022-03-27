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

    /**
     * @return {@code String} for the name of the student to view
     */
    public String getName() {
        return name.fullName;
    }

    /**
     * @return {@code String} for the name and lab details of the student to view
     */
    public String getDescription() {
        return getName() + "\n" + getLabs();
    }

    /**
     * @return {@code String} for the lab details of the student to view
     */
    public String getLabs() {
        List<String> labDetails = labList.getLabDetails();
        StringBuilder sb = new StringBuilder();

        labDetails.forEach(sb::append);

        return sb.toString();

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (this == other) {
            return true;
        }

        // instanceof handles unit
        if (!(other instanceof ViewDetails)) {
            return false;
        }

        ViewDetails otherViewDetails = (ViewDetails) other;

        return name.equals(otherViewDetails.name)
                && labList.equals(otherViewDetails.labList); // state check
    }

}
