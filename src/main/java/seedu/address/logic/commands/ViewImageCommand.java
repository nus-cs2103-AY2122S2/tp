package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.userimage.UserImage;

import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Opens viewimagewindow with images associated to person at index
 */
public class ViewImageCommand extends Command{
    public static final String COMMAND_WORD = "viewimage";
    public static final String MESSAGE_VIEW_SUCCESS = "Image displayed";
    public static final String MESSAGE_VIEW_FAILURE = "User has no image to display";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays images associated with client "
            + "by the index number used in the person list. "
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    public ViewImageCommand(Index targetIndex) {
        requireAllNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToViewImage = lastShownList.get(targetIndex.getZeroBased());
        Set<UserImage> userImages = personToViewImage.getUserImages();

        if (!userImages.isEmpty()) {
            model.updateViewPerson(userImages);
            return new CommandResult(MESSAGE_VIEW_SUCCESS, false, false, true, false, false);
        } else {
            throw new CommandException(MESSAGE_VIEW_FAILURE);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
          || (other instanceof ViewImageCommand // instanceof handles nulls
          && targetIndex.equals(((ViewImageCommand) other).targetIndex));
    }
}
