package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Field;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + Name.PREFIX + "NAME] "
            + "[" + Phone.PREFIX + "PHONE] "
            + "[" + Email.PREFIX + "EMAIL] "
            + "[" + Address.PREFIX + "ADDRESS] "
            + "[" + Remark.PREFIX + "ADDRESS] "
            + "[" + Tag.PREFIX + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + Phone.PREFIX + "91234567 "
            + Email.PREFIX + "johndoe@example.com "
            + Remark.PREFIX + "Likes to adventure.";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "Another person is already using this email.";

    private final Index index;
    private final List<Field> fields;
    private final Set<Tag> tags;
    private final boolean replaceTags;

    /**
     * Placeholder
     * @param index placeholder
     * @param fields placeholder
     * @param tags placeholder
     */
    public EditCommand(Index index, List<Field> fields, Set<Tag> tags) {
        requireAllNonNull(index, fields);
        this.index = index;
        this.fields = Collections.unmodifiableList(fields);
        this.tags = tags == null ? Collections.emptySet() : Collections.unmodifiableSet(tags);
        this.replaceTags = !(tags == null);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        // Update fields.
        Person editedPerson = new Person(personToEdit.getFields(), personToEdit.getTags());
        for (Field f : fields) {
            editedPerson = editedPerson.setField(f);
        }

        // Update tags.
        if (replaceTags) {
            editedPerson = editedPerson.setTags(tags);
        }

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index) && new HashSet<>(fields).equals(new HashSet<>(e.fields)) && tags.equals(e.tags);
    }
}
