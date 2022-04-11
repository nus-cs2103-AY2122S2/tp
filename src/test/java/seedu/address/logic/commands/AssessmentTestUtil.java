package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FULLMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;

public class AssessmentTestUtil {
    public static final String VALID_ASSESSMENT_NAME_OP1 = "Oral Presentation 1";
    public static final String VALID_ASSESSMENT_NAME_OP2 = "Oral Presentation 2";
    public static final String VALID_WEIGHTAGE_OP1 = "15";
    public static final String VALID_WEIGHTAGE_OP2 = "20";
    public static final String VALID_FULL_MARK_OP1 = "50";
    public static final String VALID_FULL_MARK_OP2 = "100";
    public static final String VALID_SCORE_OP1 = "25";
    public static final String VALID_SCORE_OP2 = "75";

    public static final String ASSESSMENT_NAME_DESC_OP1 = " " + PREFIX_ASSESSMENTNAME + VALID_ASSESSMENT_NAME_OP1;
    public static final String WEIGHTAGE_DESC_OP1 = " " + PREFIX_WEIGHTAGE + VALID_WEIGHTAGE_OP1;
    public static final String FULL_MARK_DESC_OP1 = " " + PREFIX_FULLMARK + VALID_FULL_MARK_OP1;
    public static final String ASSESSMENT_NAME_DESC_OP2 = " " + PREFIX_ASSESSMENTNAME + VALID_ASSESSMENT_NAME_OP2;

    public static final String INVALID_ASSESSMENT_NAME_DESC = " " + PREFIX_ASSESSMENTNAME + " ";
    public static final String INVALID_WEIGHTAGE_DESC = " " + PREFIX_WEIGHTAGE + "120";
    public static final String INVALID_FULL_MARK_DESC = " " + PREFIX_FULLMARK + "-2";

    public static final String SCORE_DESC_OP1 = " " + PREFIX_SCORE + VALID_SCORE_OP1;
    public static final String SCORE_DESC_OP2 = " " + PREFIX_SCORE + VALID_SCORE_OP2;
    public static final String INVALID_SCORE_DESC = " " + PREFIX_SCORE + "1001";

}
