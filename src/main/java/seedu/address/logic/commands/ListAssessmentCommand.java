package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSESSMENTS;

import seedu.address.model.Model;

/**
 * Lists all assessment in the address book to the user.
 */
public class ListAssessmentCommand extends Command {

    public static final String COMMAND_WORD = "list_assessment";

    public static final String MESSAGE_SUCCESS = "Listed all assessments";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAssessmentList(PREDICATE_SHOW_ALL_ASSESSMENTS);
        return CommandResult.createAssessmentCommandResult(MESSAGE_SUCCESS);
    }
}
