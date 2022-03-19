package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OWNER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PETS;

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
import seedu.address.model.pet.Address;
import seedu.address.model.pet.Appointment;
import seedu.address.model.pet.AttendanceHashMap;
import seedu.address.model.pet.Diet;
import seedu.address.model.pet.Name;
import seedu.address.model.pet.OwnerName;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.Phone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing pet in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the pet identified "
            + "by the index number used in the displayed pet list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_OWNER_NAME + "OWNERNAME] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_OWNER_NAME + "johndoe";

    public static final String MESSAGE_EDIT_PET_SUCCESS = "Edited Pet: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PET = "This pet already exists in the address book.";

    private final Index index;
    private final EditPetDescriptor editPetDescriptor;

    /**
     * @param index of the pet in the filtered pet list to edit
     * @param editPetDescriptor details to edit the pet with
     */
    public EditCommand(Index index, EditPetDescriptor editPetDescriptor) {
        requireNonNull(index);
        requireNonNull(editPetDescriptor);

        this.index = index;
        this.editPetDescriptor = new EditPetDescriptor(editPetDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Pet> lastShownList = model.getFilteredPetList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
        }

        Pet petToEdit = lastShownList.get(index.getZeroBased());
        Pet editedPet = createEditedPet(petToEdit, editPetDescriptor);

        if (!petToEdit.isSamePet(editedPet) && model.hasPet(editedPet)) {
            throw new CommandException(MESSAGE_DUPLICATE_PET);
        }

        model.setPet(petToEdit, editedPet);
        model.updateFilteredPetList(PREDICATE_SHOW_ALL_PETS);
        return new CommandResult(String.format(MESSAGE_EDIT_PET_SUCCESS, editedPet));
    }

    /**
     * Creates and returns a {@code Pet} with the details of {@code petToEdit}
     * edited with {@code editPetDescriptor}.
     */
    private static Pet createEditedPet(Pet petToEdit, EditPetDescriptor editPetDescriptor) {
        assert petToEdit != null;

        Name updatedName = editPetDescriptor.getName().orElse(petToEdit.getName());
        OwnerName updatedOwnerName = editPetDescriptor.getOwnerName().orElse(petToEdit.getOwnerName());
        Phone updatedPhone = editPetDescriptor.getPhone().orElse(petToEdit.getPhone());
        Address updatedAddress = editPetDescriptor.getAddress().orElse(petToEdit.getAddress());
        Set<Tag> updatedTags = editPetDescriptor.getTags().orElse(petToEdit.getTags());
        Diet updatedDiet = editPetDescriptor.getDiet().orElse(petToEdit.getDiet());
        Appointment updatedAppointment = editPetDescriptor.getAppointment().orElse(petToEdit.getAppointment());
        AttendanceHashMap sameAttendanceHashMap = petToEdit.getAttendanceHashMap();

        return new Pet(updatedName, updatedOwnerName, updatedPhone, updatedAddress,
                updatedTags, updatedDiet, updatedAppointment, sameAttendanceHashMap);
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
                && editPetDescriptor.equals(e.editPetDescriptor);
    }

    /**
     * Stores the details to edit the pet with. Each non-empty field value will replace the
     * corresponding field value of the pet.
     */
    public static class EditPetDescriptor {
        private Name name;
        private OwnerName ownerName;
        private Phone phone;
        private Address address;
        private Set<Tag> tags;
        private Diet diet;
        private Appointment appointment;

        public EditPetDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPetDescriptor(EditPetDescriptor toCopy) {
            setName(toCopy.name);
            setOwnerName(toCopy.ownerName);
            setPhone(toCopy.phone);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setDiet(toCopy.diet);
            setAppointment(toCopy.appointment);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, ownerName, phone, address, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setOwnerName(OwnerName ownerName) {
            this.ownerName = ownerName;
        }

        public Optional<OwnerName> getOwnerName() {
            return Optional.ofNullable(ownerName);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Sets {@code diet} to this object's {@code diet}.
         * A defensive copy of {@code diet} is used internally.
         */
        public void setDiet(Diet diet) {
            this.diet = diet;
        }

        public Optional<Diet> getDiet() {
            return Optional.ofNullable(diet);
        }

        /**
         * Sets {@code appointment} to this object's {@code appointment}.
         * A defensive copy of {@code appointment} is used internally.
         */
        public void setAppointment(Appointment appointment) {
            this.appointment = appointment;
        }

        public Optional<Appointment> getAppointment() {
            return Optional.ofNullable(appointment);
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
            if (!(other instanceof EditPetDescriptor)) {
                return false;
            }

            // state check
            EditPetDescriptor e = (EditPetDescriptor) other;

            return getName().equals(e.getName())
                    && getOwnerName().equals(e.getOwnerName())
                    && getPhone().equals(e.getPhone())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
