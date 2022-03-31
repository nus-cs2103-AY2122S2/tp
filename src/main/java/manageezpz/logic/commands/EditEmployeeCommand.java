package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;
import static manageezpz.logic.parser.CliSyntax.PREFIX_EMAIL;
import static manageezpz.logic.parser.CliSyntax.PREFIX_NAME;
import static manageezpz.logic.parser.CliSyntax.PREFIX_PHONE;
import static manageezpz.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import manageezpz.commons.core.Messages;
import manageezpz.commons.core.index.Index;
import manageezpz.commons.util.CollectionUtil;
import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.person.Email;
import manageezpz.model.person.Name;
import manageezpz.model.person.Person;
import manageezpz.model.person.Phone;
import manageezpz.model.task.Task;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditEmployeeCommand extends Command {

    public static final String COMMAND_WORD = "editEmployee";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the details of the employee identified by the "
            + "index number used in the displayed employee list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Employee: %1$s";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.\n";

    public static final String MESSAGE_DUPLICATE_PERSON = "Employee %1$s already exists!\n";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * Constructor to initialize a EditEmployeeCommand class with the given
     * index and editPersonDescriptor.
     *
     * @param index Index of the Employee to edit
     * @param editPersonDescriptor Details of the Employee to edit
     */
    public EditEmployeeCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Task> fullTaskList = model.getAddressBook().getTaskList();

        if (index.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX,
                    MESSAGE_USAGE));
        }

        Person personToEdit = lastShownPersonList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON,
                    editedPerson.getName().toString()) + "\n" + MESSAGE_USAGE);
        }

        model.setPerson(personToEdit, editedPerson);

        List<Task> affectedTaskList = fullTaskList.stream()
                .filter(task -> task.getAssignees().contains(personToEdit))
                .collect(Collectors.toList());

        for (Task task : affectedTaskList) {
            List<Person> assignees = task.getAssignees();

            for (Person assignee : assignees) {
                if (assignee.equals(personToEdit)) {
                    Task taskToUpdate = fullTaskList.get(fullTaskList.indexOf(task));
                    model.updateTaskWithEditedPerson(taskToUpdate, assignees.indexOf(assignee), editedPerson);
                }
            }
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        int personToEditNumOfTask = personToEdit.getNumOfTasks();

        return new Person(updatedName, updatedPhone, updatedEmail, personToEditNumOfTask);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditEmployeeCommand)) {
            return false;
        }

        // state check
        EditEmployeeCommand e = (EditEmployeeCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail());
        }
    }
}
