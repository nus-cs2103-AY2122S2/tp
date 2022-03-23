package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.storage.ReminderPersons;

/**
 * Indicates an existing client in the address book to be reminded.
 */
public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "remind";
    private static final String MESSAGE_REMIND_PERSON_SUCCESS = "Reminder for Person: %1$s";
    private static final String MESSAGE_PERSON_ALREADY_REMINDED =
            "This person has already been added to your reminders list!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets a reminder for the client "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";
    private static ReminderPersons reminderPersons;
    private final Index index;

    /**
     * @param index of the person in the filtered person list to remind of.
     */
    public RemindCommand(Index index) {
        requireAllNonNull(index);
        reminderPersons = ReminderPersons.getInstance();
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToRemind = lastShownList.get(index.getZeroBased());
        boolean isPersonNotReminded = reminderPersons.add(personToRemind);

        if (!isPersonNotReminded) {
            throw new CommandException(MESSAGE_PERSON_ALREADY_REMINDED);
        }

        return new CommandResult(String.format(MESSAGE_REMIND_PERSON_SUCCESS, personToRemind), false, false, false, true, false);
        //return new CommandResult(String.format(MESSAGE_REMIND_PERSON_SUCCESS, personToRemind));
    }
}
