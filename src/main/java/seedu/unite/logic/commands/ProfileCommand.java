package seedu.unite.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.unite.commons.core.Messages;
import seedu.unite.commons.core.index.Index;
import seedu.unite.logic.commands.exceptions.CommandException;
import seedu.unite.model.Model;
import seedu.unite.model.person.Person;

/**
 * View the profile of a person identified using it's displayed index from the unite.
 */
public class ProfileCommand extends Command {
    public static final String COMMAND_WORD = "profile";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View the person's profile identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DISPLAY_PROFILE_SUCCESS = "Shown profile: %1$s";

    private final Index targetIndex;

    public ProfileCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToShowProfile = lastShownList.get(targetIndex.getZeroBased());

        model.showProfile(personToShowProfile);
        return new CommandResult(String.format(MESSAGE_DISPLAY_PROFILE_SUCCESS, personToShowProfile.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProfileCommand // instanceof handles nulls
                && targetIndex.equals(((ProfileCommand) other).targetIndex)); // state check
    }
}
