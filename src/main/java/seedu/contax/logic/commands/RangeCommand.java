package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_RANGE_FROM;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_RANGE_TO;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.contax.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.Model;
import seedu.contax.model.person.Address;
import seedu.contax.model.person.Email;
import seedu.contax.model.person.Person;
import seedu.contax.model.person.Phone;
import seedu.contax.model.tag.Tag;

/**
 * Range edit or delete a person identified using it's displayed from index and to index from the address book.
 */
public class RangeCommand extends Command {
    public static final String COMMAND_WORD = "range";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": command in range"
            + "by the index number used in the displayed person list. "
            + "Parameters: from/FROM_INDEX to/TO_INDEX (must be a positive integer) "
            + "Example command range edit and range delete ";

    public static final String MESSAGE_EDIT_USAGE = COMMAND_WORD + ": Edits the details of the person in range"
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Parameters: from/FROM_INDEX to/TO_INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com"
            + PREFIX_RANGE_FROM + "1"
            + PREFIX_RANGE_TO + "3";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index fromIndex;
    private final Index toIndex;
    private final EditCommand.EditPersonDescriptor editPersonDescriptor;
    /**
     * @param fromIndex                of the person in the filtered person list to edit
     * @param toIndex                of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public RangeCommand(Index fromIndex, Index toIndex, EditCommand.EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(fromIndex);
        requireNonNull(toIndex);
        if (editPersonDescriptor == null) {
            this.editPersonDescriptor = null;
        } else {
            this.editPersonDescriptor = new EditCommand.EditPersonDescriptor(editPersonDescriptor);
        }
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();
        if (this.editPersonDescriptor != null) {
            if (fromIndex.getZeroBased() > toIndex.getZeroBased() || toIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            List<Person> personToEditList = new ArrayList<>();
            for (int i = fromIndex.getZeroBased(); i <= toIndex.getZeroBased(); i++) {
                Person personToEdit = lastShownList.get(i);
                personToEditList.add(personToEdit);
            }
            personToEditList = createEditedPerson(personToEditList, editPersonDescriptor);
            for (int i = 0; i < personToEditList.size(); i++) {
                Person personToEdit = lastShownList.get(fromIndex.getZeroBased() + i);
                Person editedPerson = personToEditList.get(i);
                model.setPerson(personToEdit, editedPerson);
                model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            }
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, personToEditList));
        } else {
            if (fromIndex.getZeroBased() > toIndex.getZeroBased() || toIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            List<Person> personToDeleteList = new ArrayList<>();
            for (int i = fromIndex.getZeroBased(); i <= toIndex.getZeroBased(); i++) {
                Person personToDelete = lastShownList.get(i);
                personToDeleteList.add(personToDelete);
                model.deletePerson(personToDelete);
            }
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDeleteList));
        }
    }

    /**
     * Creates and returns a list of {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static List<Person> createEditedPerson(List<Person> personToEditList,
                                                   EditCommand.EditPersonDescriptor editPersonDescriptor) {
        assert personToEditList != null;
        for (int i = 0; i < personToEditList.size(); i++) {
            Person person = personToEditList.get(i);
            Phone updatedPhone = editPersonDescriptor.getPhone().orElse(person.getPhone());
            Email updatedEmail = editPersonDescriptor.getEmail().orElse(person.getEmail());
            Address updatedAddress = editPersonDescriptor.getAddress().orElse(person.getAddress());
            Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(person.getTags());
            personToEditList.set(i,
                    new Person(person.getName(), updatedPhone, updatedEmail, updatedAddress, updatedTags));
        }
        return personToEditList;
    }
}
