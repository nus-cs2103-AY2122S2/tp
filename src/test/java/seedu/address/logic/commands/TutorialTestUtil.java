package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

public class TutorialTestUtil {
    public static final String VALID_TUTORIAL_NAME_TG1 = "TG1";
    public static final String VALID_TUTORIAL_NAME_TG2 = "TG2";
    public static final String VALID_TIME_TG1 = "11:00";
    public static final String VALID_TIME_TG2 = "12:00";
    public static final String VALID_DAY_TG1 = "Monday";
    public static final String VALID_DAY_TG2 = "Tuesday";
    public static final String VALID_VENUE_TG1 = "LT15";
    public static final String VALID_VENUE_TG2 = "SFAH Frontier";

    public static final String VALID_TUTORIALNAME_T01 = "T01";
    public static final String VALID_TUTORIALNAME_T02 = "T02";
    public static final String VALID_DAY_T01 = "Wed";
    public static final String VALID_DAY_T02 = "Thu";
    public static final String VALID_TIME_T01 = "10:00";
    public static final String VALID_TIME_T02 = "11:00";
    public static final String VALID_VENUE_T01 = "LT15";
    public static final String VALID_VENUE_T02 = "LT16";
    public static final int VALID_WEEK_T01 = 13;
    public static final int VALID_WEEK_T02 = 13;

    public static final String VALID_WEEK_DESC = " wk/1";
    public static final String VENUE_DESC_T01 = " " + PREFIX_VENUE + VALID_VENUE_T01;
    public static final String VENUE_DESC_T02 = " " + PREFIX_VENUE + VALID_VENUE_T02;
    public static final String TUTORIALNAME_DESC_T01 = " " + PREFIX_TUTORIALNAME + VALID_TUTORIALNAME_T01;
    public static final String TUTORIALNAME_DESC_T02 = " " + PREFIX_TUTORIALNAME + VALID_TUTORIALNAME_T02;
    public static final String DAY_DESC_T01 = " " + PREFIX_DAY + VALID_DAY_T01;
    public static final String DAY_DESC_T02 = " " + PREFIX_DAY + VALID_DAY_T02;
    public static final String WEEKS_DESC_T01 = " " + PREFIX_WEEK + VALID_WEEK_T01;
    public static final String WEEKS_DESC_T02 = " " + PREFIX_WEEK + VALID_WEEK_T02;
    public static final String TIME_DESC_T01 = " " + PREFIX_TIME + VALID_TIME_T01;
    public static final String TIME_DESC_T02 = " " + PREFIX_TIME + VALID_TIME_T02;

    public static final String INVALID_TUTORIAL_NAME = " " + PREFIX_TUTORIALNAME; // empty string not allowed
    public static final String INVALID_DAY_DESC = " " + PREFIX_DAY + "thur"; // four letters abbreviation not allowed
    public static final String INVALID_TIME_DESC = " " + PREFIX_TIME + "1300"; // missing ':' symbol
    public static final String INVALID_WEEK_DESC = " " + PREFIX_WEEK + "100"; // value exceeds limit

}
