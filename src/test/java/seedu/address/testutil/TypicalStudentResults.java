package seedu.address.testutil;

import static seedu.address.logic.commands.AssessmentTestUtil.VALID_FULL_MARK_OP1;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_FULL_MARK_OP2;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_SCORE_OP1;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_SCORE_OP2;
import static seedu.address.logic.commands.StudentResultTestUtil.VALID_NUSNETID_E8888888;
import static seedu.address.logic.commands.StudentResultTestUtil.VALID_NUSNETID_E9999999;
import static seedu.address.logic.commands.StudentResultTestUtil.VALID_SCORE_E8888888;
import static seedu.address.logic.commands.StudentResultTestUtil.VALID_SCORE_E9999999;
import static seedu.address.logic.commands.StudentTestUtil.VALID_NAME_AARON;
import static seedu.address.logic.commands.StudentTestUtil.VALID_NAME_BILL;
import static seedu.address.logic.commands.StudentTestUtil.VALID_STUDENT_ID_AARON;
import static seedu.address.logic.commands.StudentTestUtil.VALID_STUDENT_ID_BILL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.assessment.StudentResult;

public class TypicalStudentResults {
    public static final StudentResult E0123456 = new StudentResultBuilder().withName("John").withNusNetId("e0123456")
            .withScore("5").build();
    public static final StudentResult E6543210 = new StudentResultBuilder().withName("Mary").withNusNetId("e6543210")
            .withScore("7").build();
    public static final StudentResult E9012944 = new StudentResultBuilder().withName("Bob").withNusNetId("e9012944")
            .withScore("90").build();
    public static final StudentResult E6070482 = new StudentResultBuilder().withName("Bill").withNusNetId("e6070482")
            .withScore("53").build();

    // Manually added - StudentResult's details found in {@code StudentResultUtil}
    public static final StudentResult E8888888 = new StudentResultBuilder().withName("Sean")
            .withNusNetId(VALID_NUSNETID_E8888888).withScore(VALID_SCORE_E8888888).build();
    public static final StudentResult E9999999 = new StudentResultBuilder().withName("Jean")
            .withNusNetId(VALID_NUSNETID_E9999999).withScore(VALID_SCORE_E9999999).build();

    // Manually added - Student result's details found in {@code StudentTestUtil} and {@code AssessmentTestUtil}
    public static final StudentResult AARON_RESULT_FOR_OP1 = new StudentResultBuilder()
            .withName(VALID_NAME_AARON)
            .withNusNetId(VALID_STUDENT_ID_AARON)
            .withScore(VALID_SCORE_OP1, VALID_FULL_MARK_OP1).build();
    public static final StudentResult BILL_RESULT_FOR_OP2 = new StudentResultBuilder()
            .withName(VALID_NAME_BILL)
            .withNusNetId(VALID_STUDENT_ID_BILL)
            .withScore(VALID_SCORE_OP2, VALID_FULL_MARK_OP2).build();

    private TypicalStudentResults() {
    } // prevents instantiation

    public static List<StudentResult> getTypicalTutorials() {
        return new ArrayList<>(Arrays.asList(E0123456, E6543210, E9012944, E6070482));
    }

}
