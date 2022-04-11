package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INTEGER_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.ContactedDate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Memo;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Deletes tags of an existing person in the address book.
 */
public class DeleteTagCommand extends Command {
    public static final String COMMAND_WORD = "deletetag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the specified tags (case-insensitive) of the "
            + "person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a " + MESSAGE_INTEGER_RANGE + ") "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "friend";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted tag: %1$s (case-insensitive)";
    public static final String MESSAGE_MISSING_PREFIX = "At least one " + PREFIX_TAG + " must be provided.";
    public static final String MESSAGE_MISSING_TAG = "%s (case-insensitive) does not exist in the specified person.";

    private final Index index;
    private final Set<Tag> tagsToDelete;

    /**
     * Creates a DeleteTagCommand to delete tags of the person at the specified index with
     * tagsToDelete.
     *
     * @param index Index of the person in the filtered person list to edit.
     * @param tagsToDelete Tags to delete.
     */
    public DeleteTagCommand(Index index, Set<Tag> tagsToDelete) {
        requireNonNull(index);
        requireNonNull(tagsToDelete);

        this.index = index;
        this.tagsToDelete = tagsToDelete;
    }

    /**
     * Executes the delete tag command and returns the result message.
     *
     * @param model {@code Model} which the delete tag command should operate on.
     * @return Feedback message of the delete tag operation result for display.
     * @throws CommandException If an error occurs during delete tag command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Set<Tag> personTags = personToEdit.getTags();
        Set<Tag> missingTags = findMissingTags(personTags, tagsToDelete);

        if (!missingTags.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_MISSING_TAG, missingTags));
        }

        Person editedPerson = createEditedPersonWithTagsDeleted(personToEdit, tagsToDelete);
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.saveAddressBookState();
        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS, tagsToDelete));
    }

    /**
     * Finds missing tags that are to be deleted but does not exist in personTags.
     *
     * @param personTags Tags that belongs to a person.
     * @param tagsToDelete Tags to delete.
     * @return A set of tags that contains missing tags that are to be deleted but does not exist in personTags.
     */
    private Set<Tag> findMissingTags(Set<Tag> personTags, Set<Tag> tagsToDelete) {
        Set<Tag> missingTags = new HashSet<>(tagsToDelete);
        missingTags.removeAll(personTags);
        return missingTags;
    }

    /**
     * Creates an edited {@code Person} with certain tags deleted.
     *
     * @param personToEdit {@code Person} to be edited.
     * @param tagsToDelete Tags to delete.
     * @return Edited {@code Person} with tags deleted.
     */
    private Person createEditedPersonWithTagsDeleted(Person personToEdit, Set<Tag> tagsToDelete) {
        requireNonNull(personToEdit);
        requireNonNull(tagsToDelete);

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        ContactedDate contactedDate = personToEdit.getContactedDate();
        Memo memo = personToEdit.getMemo();
        Set<Tag> tags = personToEdit.getTags();
        Set<Tag> updatedTags = createUpdatedTagsWithTagsDeleted(tags, tagsToDelete);
        return new Person(name, phone, email, address, contactedDate, memo, updatedTags);
    }

    /**
     * Creates a set of tag with certain tags deleted.
     *
     * @param personTags Tags belonging to a {@code Person}.
     * @param tagsToDelete Tags to delete.
     * @return A set of tag with certain tags deleted.
     */
    private Set<Tag> createUpdatedTagsWithTagsDeleted(Set<Tag> personTags, Set<Tag> tagsToDelete) {
        Set<Tag> updatedTagSet = new HashSet<>(personTags);
        updatedTagSet.removeAll(tagsToDelete);
        return updatedTagSet;
    }

    /**
     * Checks if two {@code DeleteTagCommand} are equal.
     *
     * @param other The other {@code DeleteTagCommand} object.
     * @return If equal true; otherwise false.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTagCommand)) {
            return false;
        }

        // state check
        DeleteTagCommand dt = (DeleteTagCommand) other;
        return index.equals(dt.index)
                && tagsToDelete.equals(dt.tagsToDelete);
    }

}
