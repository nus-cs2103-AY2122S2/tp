package seedu.address.testutil;

import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalModules.CS2040;
import static seedu.address.testutil.TypicalModules.CS2101;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.CS2103T_WITH_STUDENT;
import static seedu.address.testutil.TypicalModules.CS2105;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.Grade;

/**
 * A utility class containing a list of {@code Assessment} objects to be used in tests.
 */
public class TypicalAssessments {

    public static final int TOTAL_ASSESSMENTS = 11;
    public static final String PARTICIPATION = "Class Participation";
    public static final String PARTICIPATION_SIMPLE_NAME = "part";
    public static final String LAB1 = "Lab 1";
    public static final String LAB2 = "Lab 2";
    public static final String LAB3 = "Lab 3";
    public static final String LAB1_SIMPLE_NAME = "lab1";
    public static final String LAB2_SIMPLE_NAME = "lab2";
    public static final Grade GRADE1 = new Grade(1);
    public static final Grade GRADE2 = new Grade(2);
    public static final Grade GRADE3 = new Grade(3);

    public static final Assessment CS2101_PARTICIPATION_WITH_ATTEMPT = getAssessment(0);
    public static final Assessment CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT = getAssessment(1);
    public static final Assessment CS2103T_PARTICIPATION_NO_ATTEMPT = getAssessment(2);
    public static final Assessment CS2030_LAB1_WITH_ATTEMPT = getAssessment(3);
    public static final Assessment CS2030_LAB2_WITH_SOME_ATTEMPT = getAssessment(4);
    public static final Assessment CS2040_PARTICIPATION_WITH_ATTEMPT = getAssessment(5);
    public static final Assessment CS2040_LAB1_WITH_ATTEMPT = getAssessment(6);
    public static final Assessment CS2040_LAB2_NO_ATTEMPT = getAssessment(7);
    public static final Assessment CS2105_LAB1_WITH_ATTEMPT = getAssessment(8);
    public static final Assessment CS2105_LAB2_WITH_ATTEMPT = getAssessment(9);
    public static final Assessment CS2105_LAB3_WITH_ATTEMPT = getAssessment(10);

    private TypicalAssessments() {} // prevents instantiation

    public static List<Assessment> getTypicalAssessments() {
        List<Assessment> assessmentList = new ArrayList<>();
        for (int i = 0; i < TOTAL_ASSESSMENTS; i++) {
            assessmentList.add(getAssessment(i));
        }
        return assessmentList;
    }

    public static Assessment getAssessment(int i) {
        switch (i) {
        case 0:
            return new AssessmentBuilder()
                    .withAssessmentName(PARTICIPATION).withSimpleName(PARTICIPATION_SIMPLE_NAME).withTaModule(CS2101)
                    .withAttempts(Map.of(TypicalStudents.BENSON, GRADE1)).build();
        case 1:
            return new AssessmentBuilder()
                    .withAssessmentName(PARTICIPATION)
                    .withSimpleName(PARTICIPATION_SIMPLE_NAME).withTaModule(CS2103T).build();
        case 2:
            return new AssessmentBuilder()
                    .withAssessmentName(PARTICIPATION).withSimpleName(PARTICIPATION_SIMPLE_NAME)
                    .withTaModule(CS2103T_WITH_STUDENT).build();
        case 3:
            return new AssessmentBuilder()
                    .withAssessmentName(LAB1).withSimpleName(LAB1_SIMPLE_NAME).withTaModule(CS2030)
                    .withAttempts(Map.of(TypicalStudents.ALICE, GRADE2, TypicalStudents.ELLE, GRADE2,
                            TypicalStudents.FIONA, GRADE2)).build();
        case 4:
            return new AssessmentBuilder()
                    .withAssessmentName(LAB2).withSimpleName(LAB2_SIMPLE_NAME).withTaModule(CS2030)
                    .withAttempts(Map.of(TypicalStudents.ALICE, GRADE2, TypicalStudents.FIONA, GRADE3)).build();
        case 5:
            return new AssessmentBuilder()
                    .withAssessmentName(PARTICIPATION).withTaModule(CS2040)
                    .withAttempts(Map.of(TypicalStudents.ALICE, GRADE1)).build();
        case 6:
            return new AssessmentBuilder()
                    .withAssessmentName(LAB1).withTaModule(CS2040)
                    .withAttempts(Map.of(TypicalStudents.ALICE, GRADE1)).build();
        case 7:
            return new AssessmentBuilder()
                    .withAssessmentName(LAB2).withTaModule(CS2040).build();
        case 8:
            return new AssessmentBuilder()
                    .withAssessmentName(LAB1).withTaModule(CS2105)
                    .withAttempts(Map.of(TypicalStudents.CARL, GRADE3, TypicalStudents.DANIEL, GRADE3)).build();
        case 9:
            return new AssessmentBuilder()
                    .withAssessmentName(LAB2).withTaModule(CS2105)
                    .withAttempts(Map.of(TypicalStudents.CARL, GRADE3, TypicalStudents.DANIEL, GRADE3)).build();
        case 10:
            return new AssessmentBuilder()
                    .withAssessmentName(LAB3).withTaModule(CS2105)
                    .withAttempts(Map.of(TypicalStudents.CARL, GRADE3, TypicalStudents.DANIEL, GRADE3)).build();
        default:
            return new AssessmentBuilder().build();
        }
    }
}
