package seedu.trackbeau.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_ALLERGIES;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_HAIRTYPE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SERVICES;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SKINTYPE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_STAFFS;
import static seedu.trackbeau.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.trackbeau.commons.core.Messages;
import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.commons.util.CollectionUtil;
import seedu.trackbeau.logic.commands.exceptions.CommandException;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.customer.Address;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.customer.Email;
import seedu.trackbeau.model.customer.HairType;
import seedu.trackbeau.model.customer.Name;
import seedu.trackbeau.model.customer.Phone;
import seedu.trackbeau.model.customer.SkinType;
import seedu.trackbeau.model.tag.Tag;

/**
 * Edits the details of an existing customer in the trackbeau book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the customer identified "
            + "by the index number used in the displayed customer list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_SKINTYPE + "SKIN TYPE]\n"
            + "[" + PREFIX_HAIRTYPE + "HAIR TYPE]\n"
            + "[" + PREFIX_STAFFS + "STAFFS]...\n"
            + "[" + PREFIX_SERVICES + "SERVICES]...\n"
            + "[" + PREFIX_ALLERGIES + "ALLERGIES]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This customer already exists in the trackbeau book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the customer in the filtered customer list to edit
     * @param editPersonDescriptor details to edit the customer with
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
        List<Customer> lastShownList = model.getFilteredCustomerList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Customer customerToEdit = lastShownList.get(index.getZeroBased());
        Customer editedCustomer = createEditedPerson(customerToEdit, editPersonDescriptor);

        if (!customerToEdit.isSameCustomer(editedCustomer) && model.hasCustomer(editedCustomer)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setCustomer(customerToEdit, editedCustomer);
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedCustomer));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Customer createEditedPerson(Customer customerToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert customerToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(customerToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(customerToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(customerToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(customerToEdit.getAddress());
        SkinType updatedSkinType = editPersonDescriptor.getSkinType().orElse(customerToEdit.getSkinType());
        HairType updatedHairType = editPersonDescriptor.getHairType().orElse(customerToEdit.getHairType());
        Set<Tag> updatedStaffs = editPersonDescriptor.getStaffs().orElse(customerToEdit.getStaffs());
        Set<Tag> updatedServices = editPersonDescriptor.getServices().orElse(customerToEdit.getServices());
        Set<Tag> updatedAllergies = editPersonDescriptor.getAllergies().orElse(customerToEdit.getAllergies());

        return new Customer(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedSkinType, updatedHairType,
                updatedStaffs, updatedServices, updatedAllergies);
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
     * Stores the details to edit the customer with. Each non-empty field value will replace the
     * corresponding field value of the customer.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private SkinType skinType;
        private HairType hairType;
        private Set<Tag> staffs;
        private Set<Tag> services;
        private Set<Tag> allergies;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setStaffs(toCopy.staffs);
            setServices(toCopy.services);
            setAllergies(toCopy.allergies);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address,
                    skinType, hairType, staffs, services, allergies);
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

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setSkinType(SkinType skinType) {
            this.skinType = skinType;
        }

        public Optional<SkinType> getSkinType() {
            return Optional.ofNullable(skinType);
        }

        public void setHairType(HairType hairType) {
            this.hairType = hairType;
        }

        public Optional<HairType> getHairType() {
            return Optional.ofNullable(hairType);
        }

        /**
         * Sets {@code staffs} to this object's {@code staffs}.
         * A defensive copy of {@code staffs} is used internally.
         */
        public void setStaffs(Set<Tag> staffs) {
            this.staffs = (staffs != null) ? new HashSet<>(staffs) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code staffs} is null.
         */
        public Optional<Set<Tag>> getStaffs() {
            return (staffs != null) ? Optional.of(Collections.unmodifiableSet(staffs)) : Optional.empty();
        }

        /**
         * Sets {@code services} to this object's {@code services}.
         * A defensive copy of {@code services} is used internally.
         */
        public void setServices(Set<Tag> services) {
            this.services = (services != null) ? new HashSet<>(services) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code services} is null.
         */
        public Optional<Set<Tag>> getServices() {
            return (services != null) ? Optional.of(Collections.unmodifiableSet(services)) : Optional.empty();
        }

        /**
         * Sets {@code allergies} to this object's {@code allergies}.
         * A defensive copy of {@code services} is used internally.
         */
        public void setAllergies(Set<Tag> allergies) {
            this.allergies = (allergies != null) ? new HashSet<>(allergies) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code allergies} is null.
         */
        public Optional<Set<Tag>> getAllergies() {
            return (allergies != null) ? Optional.of(Collections.unmodifiableSet(allergies)) : Optional.empty();
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
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getSkinType().equals(e.getSkinType())
                    && getHairType().equals(e.getHairType())
                    && getStaffs().equals(e.getStaffs())
                    && getServices().equals(e.getServices())
                    && getAllergies().equals(e.getAllergies());
        }
    }
}
