package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_QUALIFICATION = new Prefix("q/");


    /* Prefix for applicant */
    public static final Prefix PREFIX_DATEAPPLIED = new Prefix("d/");
    public static final Prefix PREFIX_JOB = new Prefix("j/");
    public static final Prefix PREFIX_DATEINTERVIEW = new Prefix("i/");
    public static final Prefix PREFIX_NRIC = new Prefix("nric/");
    public static final Prefix PREFIX_STATUS = new Prefix("s/");
    public static final Prefix PREFIX_SORTAPPLICANT = new Prefix("by/");

    /* Prefix for job */
    public static final Prefix PREFIX_JOBTITLE = new Prefix("jt/");
    public static final Prefix PREFIX_SALARY = new Prefix("sal/");
    public static final Prefix PREFIX_COMPANY_NAME = new Prefix("c/");
    public static final Prefix PREFIX_JOBSTATUS = new Prefix("js/");
    public static final Prefix PREFIX_JOB_POSITION = new Prefix("pos/");
    public static final Prefix PREFIX_JOBID = new Prefix("id/");
}
