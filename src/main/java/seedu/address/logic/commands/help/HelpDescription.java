package seedu.address.logic.commands.help;

public class HelpDescription {
    public static final String OVERALL_HELPING_DESCRIPTION =
            "Looks like you forget something. Don't worry, here are the overall command list. "
            + "For a more detail of each command, you can type 'help + command' to view the full description. \n"
            + "1. add: Add different types of data into HireLah. \n"
            + "2. edit: Edit different types of data in HireLah. \n"
            + "3. delete: Delete different types of data in HireLah. \n"
            + "4. list: List different data types in HireLah. \n"
            + "5. filter: Filter HireLah data based on different filter type. \n"
            + "6. sort: Sort HireLah data ascending or descending accordingly. \n"
            + "7. exit: Exits the program";

    public static final String ADD_COMMAND_DESCRIPTION =
            "1. Adding an applicant: \n"
            + "Format: 'add -a n/APPLICANT_NAME d/DOB g/GENDER p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…' \n"
            + "Some notice: \n"
            + "- An applicant can have any number of tags (including 0). \n"
            + "- DOB provided must be in format YYYY-MM-DD. \n"
            + "- Gender must be M/F. \n"
            + "Examples: \n"
            + "app -a n/Max d/2000-01-01 g/M p/97123456 e/max@yahoo.com a/12 Kent Ridge Drive, 119243 t/Data Analyst\n"
            + "\n 2. Adding Interview: \n"
            + "Format: 'add -i n/CANDIDATE_INDEX d/DATE r/ROLE' \n"
            + "Some notice: \n"
            + "- Date provided must be in format YYYY-MM-DD HH:MM. \n"
            + "- Role must currently exist in position. \n"
            + "Examples: \n"
            + "add -i n/1 d/2022-01-01 14:00 r/Senior Frontend Software Engineer \n \n"
            + "3. Adding positions: \n"
            + "Format: 'add -p POSITION_NAME n/NUM_OPENINGS [d/DESCRIPTION] [r/REQUIREMENTS]' \n"
            + "Some notice: \n"
            + "- Positions must have a unique name. \n"
            + "- Name provided is case-insensitive. \n"
            + "- Number of openings in the position must be 0 or more 0, 1, 2,… \n"
            + "Examples: \n"
            + "add -p Senior Software Engineer n/3 d/More than 5 years experience r/JavaScript r/HTML r/CSS";
}
