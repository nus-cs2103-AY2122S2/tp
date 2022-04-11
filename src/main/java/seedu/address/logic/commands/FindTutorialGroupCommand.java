package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.tutorialgroup.TutorialGroupKeywordsPredicate;

public class FindTutorialGroupCommand extends Command {
    public static final String COMMAND_WORD = "findtg";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students in a tutorial group "
            + "sorted (alphabetical order) and displays them as a list with index numbers.\n"
            + "Parameters: Module code and Tutorial name\n"
            + "Example: " + COMMAND_WORD + " CS2103T W15-3";

    private final TutorialGroupKeywordsPredicate predicate;

    public FindTutorialGroupCommand(TutorialGroupKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTutorialGroupCommand // instanceof handles nulls
                && predicate.equals(((FindTutorialGroupCommand) other).predicate)); // state check
    }

}
