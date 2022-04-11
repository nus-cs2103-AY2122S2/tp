package seedu.address.logic.commands.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all applicants in the address book to the user.
 */
public class ListApplicant extends Command {

    public static final String COMMAND_WORD = "listapplicant";

    public static final String MESSAGE_SUCCESS = "Listed all applicants";
    public static final String MESSAGE_EMPTY_LIST = "There are currently no applicants in ReCLIne.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        if (model.getFilteredApplicantList().size() == 0) {
            return new CommandResult(MESSAGE_EMPTY_LIST);
        } else {
            return new CommandResult(MESSAGE_SUCCESS, true, false, true);
        }
    }
}
