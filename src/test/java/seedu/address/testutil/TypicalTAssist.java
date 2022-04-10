package seedu.address.testutil;

import seedu.address.model.TAssist;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;

public class TypicalTAssist {

    private TypicalTAssist() {}

    /**
     * Returns an {@code TAssist} with all the typical entities.
     */
    public static TAssist getTypicalTAssist() {
        TAssist tassist = new TAssist();
        for (Student student : TypicalStudents.getTypicalStudents()) {
            tassist.addStudent(student);
        }
        for (TaModule module : TypicalModules.getTypicalModules()) {
            tassist.addModule(module);
        }
        for (ClassGroup classGroup : TypicalClassGroups.getTypicalClassGroups()) {
            tassist.addClassGroup(classGroup);
        }
        for (Assessment assessment : TypicalAssessments.getTypicalAssessments()) {
            tassist.addAssessment(assessment);
        }
        return tassist;
    }

}
