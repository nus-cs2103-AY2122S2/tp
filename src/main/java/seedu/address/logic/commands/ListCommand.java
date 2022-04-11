package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import java.util.Comparator;
import java.util.Optional;

import seedu.address.logic.sort.ApplicationStatusComparator;
import seedu.address.logic.sort.InterviewSlotComparator;
import seedu.address.logic.sort.NameComparator;
import seedu.address.logic.sort.PriorityComparator;
import seedu.address.model.Model;
import seedu.address.model.application.Application;

/**
 * Lists all applications in InternApply to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String COMMAND_ORDER_WORD_ASCENDING = "ASC";

    public static final String COMMAND_ORDER_WORD_DESCENDING = "DESC";

    public static final String MESSAGE_SUCCESS = "Sorted applications by %1$s";

    public static final String MESSAGE_NO_CHANGE_FULL = "Listed all application(s).";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Show a sorted list of all applications with the given sort parameter.\n"
            + "Parameters: [parameter "
                + NameComparator.COMMAND_WORD.toUpperCase() + "|"
                + InterviewSlotComparator.COMMAND_WORD.toUpperCase() + "|"
                + PriorityComparator.COMMAND_WORD.toUpperCase() + "|"
                + ApplicationStatusComparator.COMMAND_WORD.toUpperCase() + "] "
            + "[order " + COMMAND_ORDER_WORD_ASCENDING + "|" + COMMAND_ORDER_WORD_DESCENDING + "] \n"
            + "Example: " + COMMAND_WORD + " INTERVIEW " + COMMAND_ORDER_WORD_DESCENDING + "\n"
            + "Note: Using list without parameters will revert to the last sorted.";

    private final Optional<Comparator<Application>> sortingComparator;
    private final String orderBy;

    /**
     * Creates a ListCommand with the previous sorting order.
     * Notes: the {@code sortingComparator} and {@code orderBy} are just placeholder values.
     */
    public ListCommand() {
        sortingComparator = Optional.empty();
        orderBy = COMMAND_ORDER_WORD_ASCENDING;
    }

    /**
     * Creates a ListCommand with a specified {@code sortingComparator} and {@code orderBy}
     */
    public ListCommand(Comparator<Application> sortingComparator, String orderBy) {
        this.sortingComparator = Optional.ofNullable(sortingComparator);
        this.orderBy = orderBy;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        sortingComparator.ifPresent(applicationComparator -> model.sortApplications(applicationComparator, orderBy));
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        return sortingComparator.map(applicationComparator -> new CommandResult(String.format(MESSAGE_SUCCESS,
                String.format("%s order by %s.", applicationComparator,
                        orderBy.toLowerCase())))).orElseGet(() -> new CommandResult(MESSAGE_NO_CHANGE_FULL));
    }

    public String getComparatorType() {
        if (this.sortingComparator.isPresent()) {
            return sortingComparator.get().toString();
        } else {
            return COMMAND_WORD;
        }
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ListCommand) {
            ListCommand listCommand = (ListCommand) object;
            return this.getOrderBy().equals(listCommand.getOrderBy())
                    && this.getComparatorType().equals(listCommand.getComparatorType());
        }
        return false;
    }
}
