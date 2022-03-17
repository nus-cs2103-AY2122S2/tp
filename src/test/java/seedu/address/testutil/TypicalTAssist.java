package seedu.address.testutil;

import seedu.address.model.TAssist;
import seedu.address.model.student.Student;

public class TypicalTAssist {

    private TypicalTAssist() {}

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static TAssist getTypicalTAssist() {
        TAssist tassist = new TAssist();
        for (Student student : TypicalStudents.getTypicalStudents()) {
            tassist.addStudent(student);
        }
        return tassist;
    }

}
