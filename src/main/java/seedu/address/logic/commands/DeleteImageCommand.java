package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.CommandResult.SpecialCommandResult.VIEW_IMAGES;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.image.ImageDetails;
import seedu.address.model.image.ImageDetailsList;
import seedu.address.model.image.util.ImageUtil;
import seedu.address.model.person.Address;
import seedu.address.model.person.DeadlineList;
import seedu.address.model.person.Email;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.HighImportance;
import seedu.address.model.person.Name;
import seedu.address.model.person.Notes;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class DeleteImageCommand extends Command implements DetailedViewExecutable {
    public static final String COMMAND_WORD = "delimg";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the image identified by the index number used in the displayed images list of "
            + "the person identified by the index number used in the displayed person list.\n"
            + "Parameters: PERSON_INDEX (must be a positive integer) i/IMAGE_INDEX\n"
            + "Example: " + COMMAND_WORD + " 1 i/2";

    public static final String MESSAGE_INVALID_IMAGE_DISPLAYED_INDEX = "The image index provided is invalid";
    public static final String MESSAGE_DELETE_IMAGE_SUCCESSFUL = "Image %d has been deleted for person %s";

    private final Index personIndex;
    private final Index imageIndex;

    /**
     * Constructs a command to delete an image.
     *
     * @param personIndex of the person to delete.
     * @param imageIndex of the image to delete, relative to the person to delete.
     */
    public DeleteImageCommand(Index personIndex, Index imageIndex) {
        requireNonNull(personIndex);
        requireNonNull(imageIndex);

        this.personIndex = personIndex;
        this.imageIndex = imageIndex;
    }

    /**
     * Constructs a command to delete the image of the {@code Person} in detailed view.
     * @param imageIndex of the image to delete.
     */
    public DeleteImageCommand(Index imageIndex) {
        this.personIndex = null;
        this.imageIndex = imageIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireNonNull(personIndex);

        List<Person> lastShownList = model.getSortedPersonList();

        if (personIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(personIndex.getZeroBased());
        ImageDetailsList images = personToEdit.getImageDetailsList();

        if (imageIndex.getZeroBased() >= images.size()) {
            throw new CommandException(MESSAGE_INVALID_IMAGE_DISPLAYED_INDEX);
        }

        ImageDetails imageToDelete = images.get(imageIndex.getZeroBased());
        ImageUtil.removeFile(imageToDelete);
        ImageDetailsList sanitizedList = ImageUtil.sanitizeList(images, model.getContactImagesFilePath());
        Person editedPerson = createImageDeletedPerson(personToEdit, sanitizedList);

        model.setPerson(personToEdit, editedPerson);
        model.setImagesToView(editedPerson.getImageDetailsList());

        return new CommandResult(
                String.format(MESSAGE_DELETE_IMAGE_SUCCESSFUL, imageIndex.getOneBased(), editedPerson), VIEW_IMAGES);
    }

    @Override
    public CommandResult executeInDetailedView(Model model) throws CommandException {
        requireNonNull(model);

        Person personToEdit = model.getDetailedContactViewPerson();
        ImageDetailsList images = personToEdit.getImageDetailsList();

        if (imageIndex.getZeroBased() >= images.size()) {
            throw new CommandException(MESSAGE_INVALID_IMAGE_DISPLAYED_INDEX);
        }

        ImageDetails imageToDelete = images.get(imageIndex.getZeroBased());
        ImageUtil.removeFile(imageToDelete);
        ImageDetailsList sanitizedList = ImageUtil.sanitizeList(images, model.getContactImagesFilePath());
        Person editedPerson = createImageDeletedPerson(personToEdit, sanitizedList);

        model.setPerson(personToEdit, editedPerson);
        model.setDetailedContactView(editedPerson);

        return new CommandResult(
                String.format(MESSAGE_DELETE_IMAGE_SUCCESSFUL, imageIndex.getOneBased(), editedPerson),
                CommandResult.SpecialCommandResult.DETAILED_VIEW);
    }

    private static Person createImageDeletedPerson(Person personToEdit, ImageDetailsList sanitizedList) {
        assert personToEdit != null;
        assert sanitizedList != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        DeadlineList deadlines = personToEdit.getDeadlines();
        Notes notes = personToEdit.getNotes();
        HighImportance highImportanceStatus = personToEdit.getHighImportanceStatus();
        Favourite favouriteStatus = personToEdit.getFavouriteStatus();
        Set<Tag> tags = personToEdit.getTags();

        return new Person(name, phone, email, address, deadlines,
                notes, tags, favouriteStatus, highImportanceStatus, sanitizedList);

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteImageCommand)) {
            return false;
        }

        DeleteImageCommand e = (DeleteImageCommand) other;
        return Objects.equals(this.personIndex, e.personIndex)
                && Objects.equals(this.imageIndex, e.imageIndex);
    }
}
