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
 * A command that allows users to append tags to the end of the specified person's current tag set.
 */
public class AddTagCommand extends Command {
    public static final String COMMAND_WORD = "addtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Appends the specified tags (case-insensitive) of the "
            + "person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a " + MESSAGE_INTEGER_RANGE + ") "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "friend ";

    public static final String MESSAGE_APPEND_TAG_SUCCESS = "Appended tag: %1$s";
    public static final String MESSAGE_MISSING_PREFIX = "At least one " + PREFIX_TAG + " must be provided.";
    public static final String MESSAGE_DUPLICATE_TAG = "%s (case-insensitive) is already present in the person.";

    private final Index index;
    private final Set<Tag> tagsToAppend;

    /**
     * Constructs AddTagCommand for appending tags to a person identified by the index number in the displayed person
     * set.
     *
     * @param index Index position of the person in the set.
     * @param tagsToAppend Set of tags to append to the person's current tag set.
     */
    public AddTagCommand(Index index, Set<Tag> tagsToAppend) {
        requireNonNull(index);
        requireNonNull(tagsToAppend);

        this.index = index;
        this.tagsToAppend = tagsToAppend;
    }

    /**
     * Executes the main logic of the AddTagCommand. Appends a set of tags to the back of the current person's tag
     * set.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Message that should be reflected in the {@link seedu.address.ui.Ui}.
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
        Set<Tag> duplicatedTags = findDuplicatedTags(personTags, tagsToAppend);

        if (!duplicatedTags.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_TAG, duplicatedTags));
        }

        Person editedPerson = createEditedPerson(personToEdit, tagsToAppend);
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.saveAddressBookState();
        return new CommandResult(String.format(MESSAGE_APPEND_TAG_SUCCESS, tagsToAppend));
    }

    /**
     * Finds tags that are present in the current person's tag set and the tags to be appended to the person.
     *
     * @param personTags Tag set obtained from the current person.
     * @param tagsToAppend Set of tags to append to the current person's tag set.
     * @return Set of tags that is present in both the current person's tag set and the tags to be appended.
     */
    private Set<Tag> findDuplicatedTags(Set<Tag> personTags, Set<Tag> tagsToAppend) {
        Set<Tag> duplicatedTags = new HashSet<>(tagsToAppend);
        duplicatedTags.retainAll(personTags); //set intersection
        return duplicatedTags;
    }

    /**
     * Creates a new person with the new tags appended to the end of the person's tag set.
     *
     * @param personToEdit The reference person whose attributes are copied into the newly created person. Requires
     *                     it to be non-null.
     * @param tagsToAppend Set of tags that are appended to the newly created person's tag set.
     * @return A new person with the updated tag set.
     */
    private Person createEditedPerson(Person personToEdit, Set<Tag> tagsToAppend) {
        requireNonNull(personToEdit);
        requireNonNull(tagsToAppend);

        Name personName = personToEdit.getName();
        Phone personPhone = personToEdit.getPhone();
        Email personEmail = personToEdit.getEmail();
        Address personAddress = personToEdit.getAddress();
        ContactedDate personContactedDate = personToEdit.getContactedDate();
        Memo personMemo = personToEdit.getMemo();
        Set<Tag> personTags = personToEdit.getTags();
        Set<Tag> updatedTags = createUpdatedTags(personTags, tagsToAppend);
        return new Person(personName, personPhone, personEmail, personAddress, personContactedDate, personMemo,
                updatedTags);
    }

    /**
     * Creates a new tag set that contains tags from both the given set parameters.
     *
     * @param personTags Tags from the specified person.
     * @param tagsToAppend Tags to append.
     * @return A new tag set that contains all the tags from both the sets.
     */
    private Set<Tag> createUpdatedTags(Set<Tag> personTags, Set<Tag> tagsToAppend) {
        Set<Tag> updatedTagSet = new HashSet<>(personTags);
        updatedTagSet.addAll(tagsToAppend);
        return updatedTagSet;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTagCommand)) {
            return false;
        }

        AddTagCommand command = (AddTagCommand) other;
        return index.equals(command.index)
                && tagsToAppend.equals(command.tagsToAppend);
    }

}
