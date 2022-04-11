package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.MainApp;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
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

public class ImagesCommand extends Command implements DetailedViewExecutable {

    public static final String COMMAND_WORD = "images";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the images of the person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1\n";

    public static final String MESSAGE_IMAGES_SUCCESS = "%d Images for Person [%s]:\n %s";

    private static final Logger logger = Logger.getLogger(String.valueOf(MainApp.class));

    private final Index index;

    /**
     * Creates the command to show all images of a person.
     *
     * @param index of the persons whose images are to be displayed.
     */
    public ImagesCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    public ImagesCommand() {
        this.index = null;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireNonNull(index);

        List<Person> lastShownList = model.getSortedPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person targetPerson = lastShownList.get(index.getZeroBased());
        logger.info(String.format("Sanitizing images of person at index %d, at file path: %s", index.getZeroBased(),
                model.getContactImagesFilePath()));

        ImageDetailsList originalList = targetPerson.getImageDetailsList();
        ImageDetailsList sanitizedList = ImageUtil.sanitizeList(originalList, model.getContactImagesFilePath());
        Person sanitizedPerson = createImageDeletedPerson(targetPerson, sanitizedList);
        logger.info(String.format("Result of sanitization: %d -> %d", originalList.size(), sanitizedList.size()));

        if (originalList.size() != sanitizedList.size()) {
            model.setPerson(targetPerson, sanitizedPerson);
        }

        model.setImagesToView(sanitizedList);

        String result = String.format(MESSAGE_IMAGES_SUCCESS, sanitizedList.size(),
                index.getOneBased(), sanitizedList);
        return new CommandResult(result, CommandResult.SpecialCommandResult.VIEW_IMAGES);
    }

    @Override
    public CommandResult executeInDetailedView(Model model) {
        requireNonNull(model);

        Person targetPerson = model.getDetailedContactViewPerson();
        logger.info("Sanitizing images of person in detailed view");

        ImageDetailsList originalList = targetPerson.getImageDetailsList();
        ImageDetailsList sanitizedList = ImageUtil.sanitizeList(originalList, model.getContactImagesFilePath());
        Person sanitizedPerson = createImageDeletedPerson(targetPerson, sanitizedList);
        logger.info(String.format("Result of sanitization: %d -> %d", originalList.size(), sanitizedList.size()));

        if (originalList.size() != sanitizedList.size()) {
            model.setPerson(targetPerson, sanitizedPerson);
        }

        model.setImagesToView(sanitizedList);
        model.clearDetailedContactView();

        String result =
                String.format(MESSAGE_IMAGES_SUCCESS, originalList.size(), targetPerson.getName(), sanitizedList);
        return new CommandResult(result, CommandResult.SpecialCommandResult.VIEW_IMAGES);
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

        if (!(other instanceof ImagesCommand)) {
            return false;
        }

        ImagesCommand e = (ImagesCommand) other;

        return Objects.equals(this.index, e.index);
    }
}
