package seedu.address.logic.parser;
/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_TYPE = new Prefix("t/");
    public static final Prefix PREFIX_NRIC = new Prefix("i/");
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("tg/");

    /* Consultation Prefixes */
    public static final Prefix PREFIX_DATE = new Prefix("dt/");
    public static final Prefix PREFIX_TIME = new Prefix("tm/");
    public static final Prefix PREFIX_DIAGNOSIS = new Prefix("dg/");
    public static final Prefix PREFIX_FEE = new Prefix("fe/");
    public static final Prefix PREFIX_NOTES = new Prefix("nt/");

    public static final Prefix PREFIX_INSTRUCTION = new Prefix("s/");
    public static final Prefix PREFIX_MEDICALTEST = new Prefix("mt/");
    public static final Prefix PREFIX_RESULT = new Prefix("r/");
    public static final Prefix PREFIX_TESTDATE = new Prefix("td/");
    public static final Prefix PREFIX_AGE = new Prefix("a/");
    public static final Prefix PREFIX_BLOODTYPE = new Prefix("bt/");
    public static final Prefix PREFIX_MEDICATION = new Prefix("md/");
    public static final Prefix PREFIX_HEIGHT = new Prefix("ht/");
    public static final Prefix PREFIX_WEIGHT = new Prefix("wt/");
    public static final Prefix PREFIX_ILLNESSES = new Prefix("il/");
    public static final Prefix PREFIX_SURGERIES = new Prefix("su/");
    public static final Prefix PREFIX_FAMILY_HISTORY = new Prefix("fh/");
    public static final Prefix PREFIX_IMMUNIZATION_HISTORY = new Prefix("ih/");
    public static final Prefix PREFIX_GENDER = new Prefix("gd/");
    public static final Prefix PREFIX_ETHNICITY = new Prefix("et/");
    public static final Prefix PREFIX_INDEX = new Prefix("id/");
}
