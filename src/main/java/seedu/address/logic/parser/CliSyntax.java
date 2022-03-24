package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_OWNER_NAME = new Prefix("o/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_DIET = new Prefix("d/");
    public static final Prefix PREFIX_DROP_OFF = new Prefix("do/");
    public static final Prefix PREFIX_PICK_UP = new Prefix("pu/");
    public static final Prefix PREFIX_ATTENDANCE_DATE = new Prefix("att/");
    public static final Prefix PREFIX_APPOINTMENT_DATE = new Prefix("date/");
    public static final Prefix PREFIX_APPOINTMENT_LOCATION = new Prefix("at/");
    public static final Prefix PREFIX_FILTER_TODAY = new Prefix("today/");
    public static final Prefix PREFIX_FILTER_BY_DATE = new Prefix("byDate/");
    public static final Prefix PREFIX_FILTER_BY_TAGS = new Prefix("byTags/");
    public static final Prefix PREFIX_FILTER_BY_OWNER_NAME = new Prefix("byOwner/");



}
