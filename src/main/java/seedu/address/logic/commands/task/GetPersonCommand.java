package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Tabs.CONTACT_TAB;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.task.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.name.Name;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Finds and lists all persons in address book whose name are in the specific task.
 */
public class GetPersonCommand extends Command {

    public static final String COMMAND_WORD = "get-person";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds and lists all persons tagged to a specific task. "
            + "The task is identified by the index number used in the TaskList.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    public GetPersonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task indicatedTask = lastShownList.get(targetIndex.getZeroBased());
        List<String> keywords = new ArrayList<>();
        for (Name name: indicatedTask.getPersons()) {
            keywords.add(name.fullName);
        }
        CustomPersonNameContainsKeywordsPredicate predicate =
                new CustomPersonNameContainsKeywordsPredicate(keywords);
        model.updateFilteredPersonList(predicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()),
                false, false, CONTACT_TAB, false, null, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GetPersonCommand // instanceof handles nulls
                && targetIndex.equals(((GetPersonCommand) other).targetIndex)); // state check
    }

    /**
     * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
     * This class differs from {@code PersonNameContainsKeywordPredicate} as it takes in keywords that has
     * more than one word
     */
    public static class CustomPersonNameContainsKeywordsPredicate implements Predicate<Person> {
        private final List<String> keywords;

        public CustomPersonNameContainsKeywordsPredicate(List<String> keywords) {
            this.keywords = keywords;
        }

        @Override
        public boolean test(Person person) {
            return keywords.stream()
                    .anyMatch(keyword -> person.getName().fullName.equals(keyword));
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                    || (other instanceof CustomPersonNameContainsKeywordsPredicate // instanceof handles nulls
                    && keywords.equals(((CustomPersonNameContainsKeywordsPredicate) other).keywords)); // state check
        }
    }

}
