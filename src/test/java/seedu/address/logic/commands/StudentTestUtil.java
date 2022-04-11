package seedu.address.logic.commands;

import static seedu.address.logic.commands.TutorialTestUtil.VALID_TUTORIAL_NAME_TG1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNAME;

public class StudentTestUtil {

    public static final String VALID_NAME_AARON = "Aaron Bee";
    public static final String VALID_NAME_BILL = "Bill Boo";
    public static final String VALID_PHONE_AARON = "91111111";
    public static final String VALID_PHONE_BILL = "82222222";
    public static final String VALID_EMAIL_AARON = "aaron@example.com";
    public static final String VALID_EMAIL_BILL = "bill@example.com";
    public static final String VALID_ADDRESS_AARON = "Block 312, Aaron Street 1";
    public static final String VALID_ADDRESS_BILL = "Block 123, Bill Street 3";
    public static final String VALID_TAG_STUDENT = "student";
    public static final String VALID_STUDENT_ID_AARON = "e0123654";
    public static final String VALID_STUDENT_ID_BILL = "e0654123";

    public static final String NAME_DESC_AARON = " " + PREFIX_NAME + VALID_NAME_AARON;
    public static final String ID_DESC_AARON = " " + PREFIX_STUDENTID + VALID_STUDENT_ID_AARON;
    public static final String VALID_TUTORIAL_NAME_AARON_DESC = " " + PREFIX_TUTORIALNAME + VALID_TUTORIAL_NAME_TG1;

    public static final String INVALID_NAME_ADAM = "Ad@m %%1";
    public static final String INVALID_STUDENT_ID_ADAM = "e999";

    public static final String INVALID_NAME_DESC_ADAM = " " + PREFIX_NAME + INVALID_NAME_ADAM;
    public static final String INVALID_STUDENT_ID_DESC_ADAM = " " + PREFIX_STUDENTID + INVALID_STUDENT_ID_ADAM;
}
