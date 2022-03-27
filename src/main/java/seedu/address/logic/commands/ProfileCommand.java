package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.ui.MainWindow;
import seedu.address.ui.UiManager;
import seedu.address.ui.general.Profile;

/**
 * View the profile of a person identified using it's displayed index from the address book.
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
        Profile profile = new Profile(personToShowProfile);
        MainWindow mainWindow = UiManager.getMainWindow();
        //set the general display to show the profile of the specified person
        mainWindow.getGeneralDisplay().setProfile(profile);
        //highlight the person in person list panel
        mainWindow.getPersonListPanel().getPersonListView().getSelectionModel().select(targetIndex.getZeroBased());

        return new CommandResult(String.format(MESSAGE_DISPLAY_PROFILE_SUCCESS, personToShowProfile.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProfileCommand // instanceof handles nulls
                && targetIndex.equals(((ProfileCommand) other).targetIndex)); // state check
    }
}
