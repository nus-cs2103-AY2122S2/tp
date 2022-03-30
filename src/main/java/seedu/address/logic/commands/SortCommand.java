package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonBySkillProficiencyComparator;
import seedu.address.model.person.PersonContainsSkillPredicate;

/**
 * Finds and lists all persons in address book with skills matching the given
 * keyword arguments, and sorts them by a descending level of proficiency.
 * Keyword matching is case-insensitive.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a sorted list of all persons who possesses "
            + "the specified skill passed as keywords (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: SKILL\n"
            + "Example: " + COMMAND_WORD + " Java";

    public static final String MESSAGE_INVALID_SKILL = "Invalid skill name: %s";

    private final PersonContainsSkillPredicate predicate;

    private final PersonBySkillProficiencyComparator comparator;

    /**
     * Constructs a {@code SortCommand}.
     *
     * @param predicate Predicate to filter person list by given skill.
     * @param comparator Comparator to compare proficiency level of persons of given skill.
     */
    public SortCommand(PersonContainsSkillPredicate predicate, PersonBySkillProficiencyComparator comparator) {
        this.predicate = predicate;
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateDisplayPersonList(predicate, comparator.reversed()); // Sort by descending skill proficiency
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getDisplayPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && predicate.equals(((SortCommand) other).predicate)
                && comparator.equals(((SortCommand) other).comparator)); // state check
    }
}
