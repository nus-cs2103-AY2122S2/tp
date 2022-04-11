package seedu.trackermon.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.trackermon.model.Model;
import seedu.trackermon.model.show.Show;

/**
 * Sort the list of shows in Trackermon.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Show list has been sorted!";

    private final Comparator<Show> comparator;

    public SortCommand(Comparator<Show> comparator) {
        this.comparator = comparator;
    }

    /**
     * Executes a {@code Model} object.
     * @param model {@code Model} which the command should operate on.
     * @return a {@code CommandResult} object.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedShowList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Returns whether two objects are equal.
     * @param other the second object to be compared with.
     * @return true if both objects are equal, else return false.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        System.out.println(comparator.toString());


        // state check
        SortCommand e = (SortCommand) other;
        return comparator.equals(e.comparator);
    }
}
