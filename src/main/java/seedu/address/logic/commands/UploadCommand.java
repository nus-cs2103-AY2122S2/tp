package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERIMAGE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.userimage.UserImage;



/**
 * Adds image to an existing person in the address book.
 */
public class UploadCommand extends Command {

    public static final String COMMAND_WORD = "upload";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Uploads image "
            + "by the index number of the person in the displayed person list and file path\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_USERIMAGE + "File path from designated folder] "
            + "[:DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_USERIMAGE + "livingroom.png:Main living room of flat.";
    public static final String MESSAGE_UPLOAD_SUCCESS = "Image Uploaded";
    public static final String MESSAGE_FILE_NOT_IMAGE = "File provided is not a image file";

    private final Index targetIndex;
    private final UserImage userImage;

    /**
     * @param targetIndex index of person to upload to
     * @param userImage Image to be associated to person
     */
    public UploadCommand(Index targetIndex, UserImage userImage) {
        requireAllNonNull(targetIndex, userImage);
        this.targetIndex = targetIndex;
        this.userImage = userImage;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        if (!userImage.isImage()) {
            throw new CommandException(MESSAGE_FILE_NOT_IMAGE);
        }

        Person personToUpload = lastShownList.get(targetIndex.getZeroBased());
        personToUpload.getUserImages().add(userImage);
        return new CommandResult(MESSAGE_UPLOAD_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UploadCommand // instanceof handles nulls
            && targetIndex.equals(((UploadCommand) other).targetIndex))
            && userImage.equals((((UploadCommand) other).userImage));
    }
}
