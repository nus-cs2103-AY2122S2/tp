package seedu.address.testutil;

import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalModules.CS2040;
import static seedu.address.testutil.TypicalModules.CS2101;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.CS2103T_WITH_STUDENT;
import static seedu.address.testutil.TypicalModules.CS2105;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.Grade;

/**
 * A utility class containing a list of {@code Assessment} objects to be used in tests.
 */
public class TypicalAssessments {

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

    public static final Assessment CS2101_PARTICIPATION_WITH_ATTEMPT = new AssessmentBuilder()
            .withAssessmentName(PARTICIPATION).withSimpleName(PARTICIPATION_SIMPLE_NAME).withTaModule(CS2101)
            .withAttempts(Map.of(TypicalStudents.BENSON, GRADE1)).build();
    public static final Assessment CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT = new AssessmentBuilder()
            .withAssessmentName(PARTICIPATION).withSimpleName(PARTICIPATION_SIMPLE_NAME).withTaModule(CS2103T).build();
    public static final Assessment CS2103T_PARTICIPATION_NO_ATTEMPT = new AssessmentBuilder()
            .withAssessmentName(PARTICIPATION).withSimpleName(PARTICIPATION_SIMPLE_NAME)
            .withTaModule(CS2103T_WITH_STUDENT).build();
    public static final Assessment CS2030_LAB1_WITH_ATTEMPT = new AssessmentBuilder()
            .withAssessmentName(LAB1).withSimpleName(LAB1_SIMPLE_NAME).withTaModule(CS2030)
            .withAttempts(Map.of(TypicalStudents.ALICE, GRADE2, TypicalStudents.ELLE, GRADE2,
                    TypicalStudents.FIONA, GRADE2)).build();
    public static final Assessment CS2030_LAB2_WITH_SOME_ATTEMPT = new AssessmentBuilder()
            .withAssessmentName(LAB2).withSimpleName(LAB2_SIMPLE_NAME).withTaModule(CS2030)
            .withAttempts(Map.of(TypicalStudents.ALICE, GRADE2, TypicalStudents.FIONA, GRADE3)).build();
    public static final Assessment CS2040_PARTICIPATION_WITH_ATTEMPT = new AssessmentBuilder()
            .withAssessmentName(PARTICIPATION).withTaModule(CS2040)
            .withAttempts(Map.of(TypicalStudents.ALICE, GRADE1)).build();
    public static final Assessment CS2040_LAB1_WITH_ATTEMPT = new AssessmentBuilder()
            .withAssessmentName(LAB1).withTaModule(CS2040)
            .withAttempts(Map.of(TypicalStudents.ALICE, GRADE1)).build();
    public static final Assessment CS2040_LAB2_NO_ATTEMPT = new AssessmentBuilder()
            .withAssessmentName(LAB2).withTaModule(CS2040).build();
    public static final Assessment CS2105_LAB1_WITH_ATTEMPT = new AssessmentBuilder()
            .withAssessmentName(LAB1).withTaModule(CS2105)
            .withAttempts(Map.of(TypicalStudents.CARL, GRADE3, TypicalStudents.DANIEL, GRADE3)).build();
    public static final Assessment CS2105_LAB2_WITH_ATTEMPT = new AssessmentBuilder()
            .withAssessmentName(LAB2).withTaModule(CS2105)
            .withAttempts(Map.of(TypicalStudents.CARL, GRADE3, TypicalStudents.DANIEL, GRADE3)).build();
    public static final Assessment CS2105_LAB3_WITH_ATTEMPT = new AssessmentBuilder()
            .withAssessmentName(LAB3).withTaModule(CS2105)
            .withAttempts(Map.of(TypicalStudents.CARL, GRADE3, TypicalStudents.DANIEL, GRADE3)).build();

    private TypicalAssessments() {} // prevents instantiation

    public static List<Assessment> getTypicalAssessments() {
        return new ArrayList<>(Arrays.asList(
                CS2101_PARTICIPATION_WITH_ATTEMPT,
                CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT,
                CS2103T_PARTICIPATION_NO_ATTEMPT,
                CS2030_LAB1_WITH_ATTEMPT,
                CS2030_LAB2_WITH_SOME_ATTEMPT,
                CS2040_PARTICIPATION_WITH_ATTEMPT,
                CS2040_LAB1_WITH_ATTEMPT,
                CS2040_LAB2_NO_ATTEMPT,
                CS2105_LAB1_WITH_ATTEMPT,
                CS2105_LAB2_WITH_ATTEMPT,
                CS2105_LAB3_WITH_ATTEMPT));
    }
}
