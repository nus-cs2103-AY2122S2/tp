package seedu.address.logic;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.HashMap;

public class HelpArgument {
    public static final String OVERALL_HELPING_DESCRIPTION =
            "Looks like you forget something. Don't worry, here are the overall command list. "
            + "For a more detail of each command, you can type 'help + command' to view the full description. \n"
            + "1. add: Add different types of data into HireLah. \n"
            + "2. edit: Edit different types of data in HireLah. \n"
            + "3. delete: Delete different types of data in HireLah. \n"
            + "4. list: List different data types in HireLah. Can also display filter and sort result. \n"
            + "5. pass: Mark an interview as passed. \n"
            + "6. fail: Mark an interview as failed. \n"
            + "7. accept: Accept a passed interview. \n"
            + "8. reject: Reject a passed interview. \n"
            + "9. export: Export data to a CSV file. \n"
            + "10. clear: Clears all data in HireLah. \n"
            + "11. exit: Exits the program";

    public static final String ADD_COMMAND_DESCRIPTION =
            "1. Adding an applicant: \n"
            + "Format: 'add -a n/APPLICANT_NAME ag/AGE g/GENDER p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…' \n"
            + "Some notice: \n"
            + "- Age provided must be two digits, and cannot start with a 0. eg: “23”. \n"
            + "- Name, Phone number and email must be unique  \n"
            + "- Gender must be M/F. \n"
            + "Examples: \n"
            + "add -a n/Benedict ag/20 g/M p/98123456 e/ben@gmail.com a/12 Kent Ridge Drive, 119243 \n"
            + "add -a n/Max ag/15 g/M p/97123456 e/max@yahoo.com a/12 Kent Ridge Drive, 119243 t/Data Analyst \n"
            + "\n 2. Adding Interview: \n"
            + "Format: 'add -i APPLICANT_INDEX d/DATE p/POSITION_INDEX' \n"
            + "Some notice: \n"
            + "- Date provided must be in format YYYY-MM-DD HH:MM. \n"
            + "- The index refers to the index number shown in the last displayed Applicant"
            + "  list and Position list. \n"
            + "- Index provided must be positive. \n"
            + "Examples: \n"
            + "add -i 1 d/2022-01-01 14:00 p/2 \n \n"
            + "3. Adding positions: \n"
            + "Format: 'add -p p/POSITION_NAME o/NUM_OPENINGS [d/DESCRIPTION] [r/REQUIREMENTS]' \n"
            + "Some notice: \n"
            + "- Positions must have a unique name. \n"
            + "- Name provided is case-insensitive. \n"
            + "- Number of openings in the position must be between 1 to 5 digits, and cannot start with 0. \n"
            + "Examples: \n"
            + "add -p p/Senior Software Engineer o/3 d/More than 5 years experience r/JavaScript r/HTML r/CSS";

    public static final String EDIT_COMMAND_DESCRIPTION =
            "1. Editing an applicant: \n"
            + "Format: 'edit -a INDEX [n/APPLICANT_NAME] [ag/AGE] [g/GENDER]"
            + " [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…' \n"
            + "Some notice: \n"
            + "- Edits the Applicant at the specified INDEX. The index refers to the index number shown in the "
            + "displayed Applicant list. The index must be a positive integer 1, 2, 3,…\n"
            + "- At least one of the optional fields must be provided. \n"
            + "- Existing values will be updated to the input values. \n"
            + "- You can remove all the Applicant’s tags by typing t/ without specifying any tags after it."
            + "Examples: \n"
            + "edit -a 2 e/belle@yahoo.com a/13 Computing Drive 612345 t/\n"
            + "Edits the name, DOB, gender and phone number of the 1st applicant to be Belle, 1960-03-04, "
            + "F and 81234567 respectively. \n"
            + "\n 2. Editing an Interview: \n"
            + "Format: 'edit -i INTERVIEW_INDEX [d/DATE] [p/POSITION_INDEX]' \n"
            + "Some notice: \n"
            + "- Edits the interview at the specified INTERVIEW_INDEX. The interview index refers to the index number"
            + " shown in the last displayed interview list. \n"
            + "- At least one optional field must be provided. \n"
            + "- Existing attribute of the interview will be updated to the input value. \n"
            + "- When editing requirements, the existing requirements of the interview will be removed. i.e. "
            + "adding requirements is not cumulative."
            + "Examples: \n"
            + "edit -i 3 d/2022-01-01 15:00 p/1 \n \n"
            + "3. Editing a positions: \n"
            + "Format: 'edit -p POSITION_INDEX [p/POSITION_NAME] [o/NUM_OPENINGS] [d/DESCRIPTION] [r/REQUIREMENTS]' \n"
            + "Some notice: \n"
            + "- Edits the available position with POSITION_INDEX. \n"
            + "- At least one optional field must be provided. \n"
            + "- Existing attributes of the position will be updated to the input value. \n"
            + "- When editing requirements, the existing requirements of the position will be removed. i.e. "
            + "adding requirements is not cumulative. \n"
            + "- Requirements can be removed by providing an empty requirement field. i.e. r/ \n"
            + "Examples: \n"
            + "edit -p 1 p/Senior Frontend Software Engineer o/5";

    public static final String DELETE_COMMAND_DESCRIPTION =
            "1. Deleting an applicant: \n"
            + "Format: 'delete -a CANDIDATE_INDEX' \n"
            + "Some notice: \n"
            + "- Deletes the Applicant at the specified CANDIDATE_INDEX. \n"
            + "- The index refers to the index number shown in the displayed Applicant list. \n"
            + "Examples: \n"
            + "'list -a f/name a/Betsy' followed by 'delete -a 1' deletes the 1st person name Betsy"
            + " in the results of the 'list -a f/name a/Betsy' command.\n"
            + "\n 2. Deleting an Interview: \n"
            + "Format: 'delete -i INTERVIEW_INDEX' \n"
            + "Some notice: \n"
            + "- Deletes the Interview at the specified `INTERVIEW_INDEX`. \n"
            + "- The index refers to the index number shown in the displayed Interview list. \n"
            + "Examples: \n"
            + "delete -i 3 \n \n"
            + "3. Deleting positions: \n"
            + "Format: 'delete -p POSITION_INDEX' \n"
            + "Some notice: \n"
            + "- Deletes the Position at the specified `POSITION_INDEX`. \n"
            + "- The index refers to the index number shown in the displayed Interview list \n"
            + "Examples: \n"
            + "delete -p 3";

    public static final String LIST_COMMAND_DESCRIPTION =
            "General command to list different data type in HireLah. \n"
            + " User can provide optional parameters to filter and sort the data to display. \n"
            + " If there are no parameters provided, HireLah will display all data of that type by default.  \n"
            + "Format: 'list -TYPE [f/FILTER_TYPE a/FILTER_ARGUMENT] [s/SORT_ARGUMENT]' \n"
            + "Some notice: \n"
            + "- Both 'FILTER_TYPE' and 'FILTER_ARGUMENT' must be provided if you want to use filter. \n"
            + "- 'SORT_ARGUMENT' can only be 'asc' (ascending) or 'dsc' (descending) \n"
            + "1. List all applicants: \n"
            + "Format: 'list -a [f/FILTER_TYPE a/FILTER_ARGUMENT] [s/SORT_ARGUMENT]' \n"
            + " + FILTER_TYPE: name, FILTER_ARGUMENT: name keyword: View applicants whose name contains the keyword \n"
            + " + FILTER_TYPE: gender, FILTER_ARGUMENT: M/F:"
            + " View applicants of the given gender \n"
            + " + FILTER_TYPE: status, FILTER_ARGUMENT: available/hired: View applicants with the status given \n"
            + " + FILTER_TYPE: tag, FILTER_ARGUMENT: tag keyword: "
            + "View applicants with a tag that contains the keywords(s) \n"
            + "The applicants displayed can be sorted by their name using the parameter s/SORT_ARGUMENT \n"
            + "Examples: \n"
            + "list -a f/status a/hired s/dsc \n"
            + "list -a s/asc \n \n"
            + "\n 2. Listing all Interview for a candidate: \n"
            + "Format: 'list -i [f/FILTER_TYPE a/FILTER_ARGUMENT] [s/SORT_ARGUMENT]' \n"
            + " + FILTER_TYPE: appl, FILTER_ARGUMENT: applicant name keyword: "
            + "View interviews for applicants whose name contains the keyword(s) \n"
            + " + FILTER_TYPE: pos, FILTER_ARGUMENT: position name keyword:"
            + " View interviews for position with names that contains the keyword(s) \n"
            + " + FILTER_TYPE: date, FILTER_ARGUMENT: Date the interview is happening in 'yyyy-mm-dd':"
            + " View applicants with the status given \n"
            + " + FILTER_TYPE: status, FILTER_ARGUMENT: pending / passed / failed / accepted / rejected: "
            + "View interviews with the status given \n"
            + "The interviews displayed can be sorted by their date using the parameter s/SORT_ARGUMENT \n"
            + "Examples: \n"
            + "list -i f/status a/accepted s/asc \n"
            + "list -i f/date a/2022-05-04\n \n"
            + "3. Listing all positions: \n"
            + "Format: 'list -p [f/FILTER_TYPE a/FILTER_ARGUMENT] [s/SORT_ARGUMENT]' \n"
            + " + FILTER_TYPE: name, FILTER_ARGUMENT: name keyword: "
            + "View positions with names that contains the keyword(s) \n"
            + " + FILTER_TYPE: req, FILTER_ARGUMENT: requirement keyword: "
            + "View positions with a requirement that contains the keywords(s) \n"
            + "The positions displayed can be sorted by their name using the parameter s/SORT_ARGUMENT \n"
            + "Examples: \n"
            + "list -p f/name a/Software Engineer \n"
            + "list -p f/req a/Java s/dsc";

    public static final String PASS_COMMAND_DESCRIPTION =
            "Passes an existing interview in Hirelah.\nFormat: pass INTERVIEW_INDEX\n"
            + "- Passes the Interview at the specified INTERVIEW_INDEX.\n"
            + "- Interview must have status pending before it can be passed.\n"
            + "- The index must be a positive integer 1, 2, 3, …\u200B \n"
            + "Additional details:\n"
            + "- A job offer is handed out for the interviewed position when applicant passes interview.\n"
            + "- Job offer is tracked by the Position interviewed for.\n"
            + "- Job can only be offered if offered < openings.\n"
            + "- A job offered will increase offered by 1.\n"
            + "Example: pass 1";

    public static final String FAIL_COMMAND_DESCRIPTION =
            "Fails an existing interview in Hirelah.\nFormat: fail INTERVIEW_INDEX\n"
            + "- Passes the Interview at the specified INTERVIEW_INDEX.\n"
            + "- Interview must have status pending before it can be failed.\n"
            + "- The index must be a positive integer 1, 2, 3, …\u200B \n"
            + "Example: fail 1";

    public static final String ACCEPT_COMMAND_DESCRIPTION =
            "Accepts an existing passed interview in Hirelah. This command accepts the passed interview, meaning"
            + "that the candidate has accepted the job.\nFormat: accept INTERVIEW_INDEX\n"
            + "- Accepts the Interview at the specified INTERVIEW_INDEX.\n"
            + "- Interview must have status passed before it can be accepted.\n"
            + "- The index must be a positive integer 1, 2, 3, …\u200B";

    public static final String REJECT_COMMAND_DESCRIPTION =
            "Rejects an existing interview in Hirelah. This command rejects the passed interview, meaning that the "
            + "candidate has rejected the job.\nFormat: reject INTERVIEW_INDEX\n"
            + "- Reject the Interview at the specified INTERVIEW_INDEX.\n"
            + "- Interview must have status passed before it can be accepted.\n"
            + "- The index must be a positive integer 1, 2, 3, …\u200B \n"
            + "Additional details:\n"
            + "- Rejecting a job offer will decrement the number of offered in Position";

    public static final String EXPORT_COMMAND_DESCRIPTION =
            "Exports all current displayed data of the specified typo in HireLah to a CSV file."
            + " The export csv file will be stored at export_csv folder. \n"
            + "Format: export -TYPE\n"
            + "TYPE can be a for applicants, p for positions, and i for interviews. \n"
            + "Examples:\n"
            + "'export -p' will export all positions to the corresponding csv file.\n"
            + "'list -a f/name a/Betsy' then 'export -a' will export csv all applicants"
            + " name Betsy to the corresponding csv file.";

    public static final String CLEAR_COMMAND_DESCRIPTION =
            "Clears all data in HireLah, including all applicants, positions, and interviews`. \n"
            + "Warning: This command cannot be undone!";

    public static final String EXIT_COMMAND_DESCRIPTION =
            "Well its an exit command, of course it going to terminate the program \n"
            + "Format: 'exit'";

    public static final String COMMAND_NOT_FOUND_DESCRIPTION =
            "Sorry, we don't have this command. Please try again.";

    public static final String HELP_COMMAND_DESCRIPTION =
            "Well what do you expect? New version of help? Here you are, welcome to our new help."
                    + " Now please type 'help' to get the real help.";

    public static final HashMap<String, String> HELP_ARGUMENT_WITH_DESCRIPTION = loadHelpArgument();

    private final String argument;

    /**
     * Constructs a {@code HelpArgument}.
     */
    public HelpArgument(String argument) {
        requireNonNull(argument);
        checkArgument(isValidHelpArgument(argument), COMMAND_NOT_FOUND_DESCRIPTION);
        this.argument = HELP_ARGUMENT_WITH_DESCRIPTION.get(argument);
    }

    private static HashMap<String, String> loadHelpArgument() {
        HashMap<String, String> argumentWithDescription = new HashMap<>();
        argumentWithDescription.put("add", ADD_COMMAND_DESCRIPTION);
        argumentWithDescription.put("edit", EDIT_COMMAND_DESCRIPTION);
        argumentWithDescription.put("delete", DELETE_COMMAND_DESCRIPTION);
        argumentWithDescription.put("list", LIST_COMMAND_DESCRIPTION);
        argumentWithDescription.put("pass", PASS_COMMAND_DESCRIPTION);
        argumentWithDescription.put("fail", FAIL_COMMAND_DESCRIPTION);
        argumentWithDescription.put("accept", ACCEPT_COMMAND_DESCRIPTION);
        argumentWithDescription.put("reject", REJECT_COMMAND_DESCRIPTION);
        argumentWithDescription.put("export", EXPORT_COMMAND_DESCRIPTION);
        argumentWithDescription.put("exit", EXIT_COMMAND_DESCRIPTION);
        argumentWithDescription.put("help", HELP_COMMAND_DESCRIPTION);
        argumentWithDescription.put("clear", CLEAR_COMMAND_DESCRIPTION);
        argumentWithDescription.put("", OVERALL_HELPING_DESCRIPTION);
        return argumentWithDescription;
    }

    public static Boolean isValidHelpArgument(String argument) {
        return HELP_ARGUMENT_WITH_DESCRIPTION.containsKey(argument);
    }

    @Override
    public String toString() {
        return this.argument;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpArgument // instanceof handles nulls
                && argument.equals(((HelpArgument) other).argument)); // state check
    }
}
