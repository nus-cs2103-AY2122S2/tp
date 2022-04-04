package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Reminder;
import seedu.address.model.person.Person;
import seedu.address.storage.ReminderPersons;

/**
 * Indicates an existing client in the address book to be reminded.
 */
public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "remind";
    public static final String MESSAGE_REMIND_PERSON_WARNING = "";
    public static final String MESSAGE_UNREMIND_PERSON_WARNING = "If you're trying to remove a Reminder for a Person, "
            + "type \"remind INDEX\"";
    public static final String MESSAGE_REMIND_PERSON_SUCCESS = "Created Reminder for Person: %1$s";
    public static final String MESSAGE_UNREMIND_PERSON_SUCCESS = "Removed Reminder for Person: %1$s";
    public static final String MESSAGE_EDIT_REMIND_PERSON_SUCCESS = "Edited Reminder for Person: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets a Reminder for the client "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REMINDER + "REMINDER\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMINDER + "meet client for home viewing.";
    private static ReminderPersons reminderPersons;
    private final Index index;
    private final Optional<Reminder> reminder;

    /**
     * @param index of the person in the filtered person list to remind of.
     * @param reminder
     */
    public RemindCommand(Index index, Optional<Reminder> reminder) {
        requireAllNonNull(index, reminder);
        reminderPersons = ReminderPersons.getInstance();
        this.index = index;
        this.reminder = reminder;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredAndSortedPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToRemind = lastShownList.get(index.getZeroBased());

        // if the person already has a reminder
        if (reminderPersons.containsKey(personToRemind)) {
            // the RemindCommand does not have a Reminder, remove the Reminder for this current person
            if (reminder.isEmpty()) {
                reminderPersons.remove(personToRemind);
                // update the model with the latest instance of the person with a reminder
                model.setPerson(personToRemind, personToRemind);
                return new CommandResult(String.format(MESSAGE_UNREMIND_PERSON_SUCCESS, personToRemind));
            }
            // the RemindCommand contains a Reminder, edit the current Reminder to be this new one
            reminderPersons.add(personToRemind, reminder.get());
            // update the model with the latest instance of the person with a reminder
            model.setPerson(personToRemind, personToRemind);
            return new CommandResult(String.format(MESSAGE_EDIT_REMIND_PERSON_SUCCESS, personToRemind));
        }

        // a reminder is being added to this person for the first time
        if (reminder.isEmpty()) {
            throw new CommandException(MESSAGE_USAGE);
        } else {
            reminderPersons.add(personToRemind, reminder.get());
        }

        // update the model with the latest instance of the person with a reminder
        model.setPerson(personToRemind, personToRemind);

        return new CommandResult(String.format(MESSAGE_REMIND_PERSON_SUCCESS, personToRemind));
    }

    public Index getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemindCommand)) {
            return false;
        }

        // state check
        RemindCommand e = (RemindCommand) other;
        return reminderPersons.equals(e.reminderPersons)
                && index.equals(e.index)
                && reminder.equals(e.reminder);
    }
}
