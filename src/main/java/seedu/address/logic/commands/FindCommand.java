package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPLETION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.tag.TagContainsKeywordsPredicate;
import seedu.address.model.task.CompletionStatusPredicate;
import seedu.address.model.task.DeadlineInRangePredicate;
import seedu.address.model.task.DescriptionContainsKeywordsPredicate;
import seedu.address.model.task.NameContainsKeywordsPredicate;
import seedu.address.model.task.PriorityMatchedPredicate;
import seedu.address.model.task.Task;

/**
 * Finds and lists all tasks in task list whose name or tag contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks that satisfy all the criteria given"
            + " and displays them as a list with index numbers.\n"
            + "Parameters (must contain at least one keyword): "
            + "[" + PREFIX_NAME + "NAME_KEYWORD]... "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION_KEYWORD]... "
            + "[" + PREFIX_START + "START_DATE] "
            + "[" + PREFIX_END + "END_DATE] "
            + "[" + PREFIX_PRIORITY + "PRIORITY]..."
            + "[" + PREFIX_TAG + "TAG_KEYWORD]... "
            + "[" + PREFIX_COMPLETION_STATUS + "COMPLETION_STATUS]\n"
            + "Example: " + COMMAND_WORD
            + " n/complete n/review t/CS2105 t/CS3240 t/CS2103T "
            + "start/2022-03-11 end/2022-12-31 d/email p/low p/medium c/true";

    /** Predicate that tests whether task name contains any given keyword */
    private final NameContainsKeywordsPredicate namePredicate;

    /** Predicate that tests whether tag contains any given keywords */
    private final TagContainsKeywordsPredicate tagPredicate;

    /** Predicate that tests whether the deadline of a task is within the given date range */
    private final DeadlineInRangePredicate deadlinePredicate;

    /** Predicate that tests whether the priority of a task matches any of the priorities given */
    private final PriorityMatchedPredicate priorityPredicate;

    /** Predicate that tests whether the description of a task matches any of the keywords given */
    private final DescriptionContainsKeywordsPredicate descriptionPredicate;

    /** Predicate that tests whether the completion status of a task matches the specified completion status */
    private final CompletionStatusPredicate completionStatusPredicate;

    /**
     * Constructor for FindCommand.
     *
     * @param namePredicate predicate that tests whether task name contains any given keywords
     * @param tagPredicate predicate that tests whether tag contains any given keywords
     * @param deadlinePredicate predicate that tests whether the deadline is in the range
     * @param descriptionPredicate predicate that tests whether task description contains any given keywords
     * @param priorityPredicate predicate that tests whether the priority of a task matches any given priorities
     * @param completionStatusPredicate predicate that tests whether the completion status matches the one specified
     */
    public FindCommand(NameContainsKeywordsPredicate namePredicate,
                       TagContainsKeywordsPredicate tagPredicate,
                       DeadlineInRangePredicate deadlinePredicate,
                       DescriptionContainsKeywordsPredicate descriptionPredicate,
                       PriorityMatchedPredicate priorityPredicate,
                       CompletionStatusPredicate completionStatusPredicate) {

        this.namePredicate = namePredicate;
        this.tagPredicate = tagPredicate;
        this.deadlinePredicate = deadlinePredicate;
        this.descriptionPredicate = descriptionPredicate;
        this.priorityPredicate = priorityPredicate;
        this.completionStatusPredicate = completionStatusPredicate;
    }

    /**
     * This constructor should be removed after the tests for this class is done.
     * Currently, many of the tests use this constructor, so it is still included here.
     *
     * @param namePredicate predicate that tests whether task name contains any given keywords
     * @param tagPredicate predicate that tests whether tag contains any given keywords
     * @param deadlinePredicate predicate that tests whether the deadline is in the range
     */
    public FindCommand(NameContainsKeywordsPredicate namePredicate,
                       TagContainsKeywordsPredicate tagPredicate,
                       DeadlineInRangePredicate deadlinePredicate) {

        this.namePredicate = namePredicate;
        this.tagPredicate = tagPredicate;
        this.deadlinePredicate = deadlinePredicate;
        this.descriptionPredicate = new DescriptionContainsKeywordsPredicate(new HashSet<>());
        this.priorityPredicate = new PriorityMatchedPredicate(new HashSet<>());
        this.completionStatusPredicate = new CompletionStatusPredicate(null);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Task> combinedPredicate = interceptPredicates(namePredicate, tagPredicate,
                deadlinePredicate, descriptionPredicate, priorityPredicate, completionStatusPredicate);
        model.updateFilteredTaskList(combinedPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && namePredicate.equals(((FindCommand) other).namePredicate)
                && tagPredicate.equals(((FindCommand) other).tagPredicate)
                && deadlinePredicate.equals(((FindCommand) other).deadlinePredicate)
                && descriptionPredicate.equals(((FindCommand) other).descriptionPredicate)
                && priorityPredicate.equals(((FindCommand) other).priorityPredicate));
    }

    /**
     * Combines predicates using 'and'.
     *
     * @param predicates the predicates to be combined
     * @return the combined predicate
     */
    @SafeVarargs
    private Predicate<Task> interceptPredicates(Predicate<Task>... predicates) {
        return Arrays.stream(predicates).reduce(task -> true, Predicate::and);
    }
}
