package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COVID_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRICULATION_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
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
import seedu.address.model.person.Address;
import seedu.address.model.person.Block;
import seedu.address.model.person.CovidStatus;
import seedu.address.model.person.Email;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
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
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_BLOCK + "BLOCK] "
            + "[" + PREFIX_FACULTY + "FACULTY]"
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_MATRICULATION_NUMBER + "MATRICULATIONNUMBER] "
            + "[" + PREFIX_COVID_STATUS + "COVIDSTATUS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_SAME_INPUT = "The edited value is the same as the current one.";

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

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.deletePerson(personToEdit);

        if (personToEdit.isDifferentPerson(editedPerson) && model.hasPerson(editedPerson)) {
            model.addPerson(personToEdit);
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(personToEdit);
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    private static <T> T editChecker(Optional<T> updatedOptionalField, T prevField) throws
            CommandException {
        T updatedField = updatedOptionalField.orElse(prevField);
        if (updatedOptionalField.isPresent() && updatedField.equals(prevField)) {
            throw new CommandException(MESSAGE_SAME_INPUT);
        }
        return updatedField;
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     * @throws CommandException if the edited status is same as the current status of the student
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) throws
            CommandException {
        assert personToEdit != null;

        Name updatedName = editChecker(editPersonDescriptor.getName(), personToEdit.getName());
        Block updatedBlock = editChecker(editPersonDescriptor.getBlock(), personToEdit.getBlock());
        Faculty updatedFaculty = editChecker(editPersonDescriptor.getFaculty(), personToEdit.getFaculty());
        Phone updatedPhone = editChecker(editPersonDescriptor.getPhone(), personToEdit.getPhone());
        Email updatedEmail = editChecker(editPersonDescriptor.getEmail(), personToEdit.getEmail());
        Address updatedAddress = editChecker(editPersonDescriptor.getAddress(), personToEdit.getAddress());
        MatriculationNumber updatedMatriculationNumber = editChecker(
                editPersonDescriptor.getMatriculationNumber(), personToEdit.getMatriculationNumber());
        CovidStatus updatedCovidStatus = editChecker(editPersonDescriptor.getCovidStatus(),
                personToEdit.getStatus());

        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(updatedName, updatedBlock, updatedFaculty, updatedPhone, updatedEmail, updatedAddress,
                updatedMatriculationNumber, updatedCovidStatus, updatedTags);
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
        private Block block;
        private Faculty faculty;
        private Phone phone;
        private Email email;
        private Address address;
        private MatriculationNumber matriculationNumber;
        private CovidStatus covidStatus;
        private Set<Tag> tags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setBlock(toCopy.block);
            setFaculty(toCopy.faculty);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setMatriculationNumber(toCopy.matriculationNumber);
            setCovidStatus(toCopy.covidStatus);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, block, faculty, phone, email, address,
                    matriculationNumber, covidStatus, tags);
        }

        /**
         * Sets {@code Name} to this object's {@code Name}.
         */
        public void setName(Name name) {
            this.name = name;
        }

        /**
         * Returns an Optional object with may or may not contain a Name.
         * Returns {@code Optional#empty()} if {@code Name} is null.
         */
        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        /**
         * Sets {@code Block} to this object's {@code Block}.
         */
        public void setBlock(Block block) {
            this.block = block;
        }

        /**
         * Returns an Optional object with may or may not contain a Block.
         * Returns {@code Optional#empty()} if {@code Block} is null.
         */
        public Optional<Block> getBlock() {
            return Optional.ofNullable(block);
        }

        /**
         * Sets {@code Faculty} to this object's {@code Faculty}.
         */
        public void setFaculty(Faculty faculty) {
            this.faculty = faculty;
        }

        /**
         * Returns an Optional object with may or may not contain a Faculty.
         * Returns {@code Optional#empty()} if {@code Faculty} is null.
         */
        public Optional<Faculty> getFaculty() {
            return Optional.ofNullable(faculty);
        }

        /**
         * Sets {@code Phone} to this object's {@code Phone}.
         */
        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        /**
         * Returns an Optional object with may or may not contain a Phone.
         * Returns {@code Optional#empty()} if {@code Phone} is null.
         */
        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        /**
         * Sets {@code Email} to this object's {@code Email}.
         */
        public void setEmail(Email email) {
            this.email = email;
        }

        /**
         * Returns an Optional object with may or may not contain an Email.
         * Returns {@code Optional#empty()} if {@code Email} is null.
         */
        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        /**
         * Sets {@code Address} to this object's {@code Address}.
         */
        public void setAddress(Address address) {
            this.address = address;
        }

        /**
         * Returns an Optional object with may or may not contain an Address.
         * Returns {@code Optional#empty()} if {@code Address} is null.
         */
        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code MatriculationNumber} to this object's {@code MatriculationNumber}.
         */
        public void setMatriculationNumber(MatriculationNumber matriculationNumber) {
            this.matriculationNumber = matriculationNumber;
        }

        /**
         * Returns an Optional object with may or may not contain a MatriculationNumber.
         * Returns {@code Optional#empty()} if {@code MatriculationNumber} is null.
         */
        public Optional<MatriculationNumber> getMatriculationNumber() {
            return Optional.ofNullable(matriculationNumber);
        }

        /**
         * Sets {@code CovidStatus} to this object's {@code CovidStatus}.
         */
        public void setCovidStatus(CovidStatus covidStatus) {
            this.covidStatus = covidStatus;
        }

        /**
         * Returns an Optional object with may or may not contain a CovidStatus
         * Returns {@code Optional#empty()} if {@code CovidStatus} is null.
         */
        public Optional<CovidStatus> getCovidStatus() {
            return Optional.ofNullable(covidStatus);
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
                    && getBlock().equals(e.getBlock())
                    && getPhone().equals(e.getPhone())
                    && getFaculty().equals(e.getFaculty())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getMatriculationNumber().equals(e.getMatriculationNumber())
                    && getCovidStatus().equals(e.getCovidStatus())
                    && getTags().equals(e.getTags());
        }
    }
}
