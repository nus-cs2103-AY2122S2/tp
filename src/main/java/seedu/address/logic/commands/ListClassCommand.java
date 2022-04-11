package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORIALS;

import java.util.Objects;

import seedu.address.model.Model;
import seedu.address.model.tutorial.TutorialHasSameDay;
/**
 * Lists all tutorials in the address book to the user.
 */
public class ListClassCommand extends Command {

    public static final String COMMAND_WORD = "list_class";

    public static final String MESSAGE_SUCCESS = "Listed all classes";

    public static final String MESSAGE_SUCCESS_DAY = "Listed all classes on %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all classes or classes on a specified date \n"
            + "Parameters: " + PREFIX_DAY + "DAY\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_DAY + "Wed";

    private final TutorialHasSameDay predicate;

    /**
     * Constructor for a ListClassCommand.
     * @param predicate The predicate to check for the classes of the same day.
     */
    public ListClassCommand(TutorialHasSameDay predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTutorialList(PREDICATE_SHOW_ALL_TUTORIALS);

        if (predicate != null) {
            model.updateFilteredTutorialList(predicate);
            return CommandResult.createClassCommandResult(String.format(MESSAGE_SUCCESS_DAY, predicate.getDay()));
        }

        return CommandResult.createClassCommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListClassCommand // instanceof handles nulls
                && (Objects.equals(predicate, ((ListClassCommand) other).predicate))); // state check
    }
}
