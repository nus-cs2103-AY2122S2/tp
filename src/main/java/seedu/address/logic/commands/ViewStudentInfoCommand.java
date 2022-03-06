package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.misc.InfoPanelTypes;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Views the details of a student identified using it's displayed index.
 */
public class ViewStudentInfoCommand extends Command {
    public static final String COMMAND_WORD = "student";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View details of a selected Student.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_SUCCESS = "Viewing details of %1$s";

    private final Index targetIndex;

    public ViewStudentInfoCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToSelect = lastShownList.get(targetIndex.getZeroBased());
        model.setSelectedPerson(personToSelect);
        return new CommandResult(String.format(MESSAGE_VIEW_SUCCESS, personToSelect.getName()),
                true, InfoPanelTypes.PERSON);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ViewStudentInfoCommand
                && targetIndex.equals(((ViewStudentInfoCommand) other).targetIndex));
    }
}
