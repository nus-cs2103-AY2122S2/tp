package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Group;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentNumber;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the class group.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer, greater than or equals to 1) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_STUDENT_NUMBER + "STUDENT_NUMBER] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_GROUP + "GROUP] "
            + "[" + PREFIX_MOD + "MOD] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_STUDENT_NUMBER + "A0225771X "
            + PREFIX_EMAIL + "johndoe@example.com ";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT_NUMBER = "Error! Student number %1$s is already associated "
            + "with another student.";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "Student already exists with the unique identifiers "
            + "(email/student number) in the list";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if ((!personToEdit.getEmail().equals(editedPerson.getEmail())
                || !personToEdit.getStudentNumber().equals(editedPerson.getStudentNumber()))
                && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.commitContent();
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
        StudentNumber updatedStudentNumber = editPersonDescriptor.getStudentNumber()
                .orElse(personToEdit.getStudentNumber());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Mod updatedMod = editPersonDescriptor.getMod().orElse(personToEdit.getMod());
        Group updatedGroup = editPersonDescriptor.getGroup().orElse(personToEdit.getGroup());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        return new Person(updatedName, updatedStudentNumber, updatedEmail, updatedMod, updatedGroup, updatedTags);
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
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private StudentNumber studentNumber;
        private Email email;
        private Mod mod;
        private Set<Tag> tags;
        private Group group;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setStudentNumber(toCopy.studentNumber);
            setEmail(toCopy.email);
            setGroup(toCopy.group);
            setMod(toCopy.mod);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, studentNumber, email, mod, group, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setStudentNumber(StudentNumber studentNumber) {
            this.studentNumber = studentNumber;
        }

        public Optional<StudentNumber> getStudentNumber() {
            return Optional.ofNullable(studentNumber);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setMod(Mod mod) {
            this.mod = mod;
        }

        public Optional<Mod> getMod() {
            return Optional.ofNullable(mod);
        }

        public void setGroup(Group group) {
            this.group = group;
        }

        public Optional<Group> getGroup() {
            return Optional.ofNullable(group);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
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
                    && getStudentNumber().equals(e.getStudentNumber())
                    && getEmail().equals(e.getEmail())
                    && getMod().equals(e.getMod())
                    && getTags().equals(e.getTags());
        }
    }
}
