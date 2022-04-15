package seedu.address.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.name.Name;
import seedu.address.model.note.Note;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


/**
 * Stores the details to edit the person with. Each non-empty field value will replace the
 * corresponding field value of the person.
 */
public class EditPersonDescriptor {
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private List<Note> strengths;
    private List<Note> weaknesses;
    private List<Note> miscellaneous;

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
        setTags(toCopy.tags);
        setStrengths(toCopy.strengths);
        setWeaknesses(toCopy.weaknesses);
        setMiscellaneous(toCopy.miscellaneous);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
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

    /**
     * Sets {@code strengths} to this object's {@code strengths}.
     * A defensive copy of {@code strengths} is used internally.
     */
    public void setStrengths(List<Note> strengths) {
        this.strengths = (strengths != null) ? new ArrayList<>(strengths) : null;
    }

    /**
     * Returns an unmodifiable note list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code strengths} is null.
     */
    public Optional<List<Note>> getStrengths() {
        return (strengths != null) ? Optional.of(Collections.unmodifiableList(strengths)) : Optional.empty();
    }

    /**
     * Sets {@code weaknesses} to this object's {@code weaknesses}.
     * A defensive copy of {@code weaknesses} is used internally.
     */
    public void setWeaknesses(List<Note> weaknesses) {
        this.weaknesses = (weaknesses != null) ? new ArrayList<>(weaknesses) : null;
    }

    /**
     * Returns an unmodifiable note list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code weaknesses} is null.
     */
    public Optional<List<Note>> getWeaknesses() {
        return (weaknesses != null) ? Optional.of(Collections.unmodifiableList(weaknesses)) : Optional.empty();
    }

    /**
     * Sets {@code miscellaneous} to this object's {@code miscellaneous}.
     * A defensive copy of {@code miscellaneous} is used internally.
     */
    public void setMiscellaneous(List<Note> miscellaneous) {
        this.miscellaneous = (miscellaneous != null) ? new ArrayList<>(miscellaneous) : null;
    }

    /**
     * Returns an unmodifiable note list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code notes} is null.
     */
    public Optional<List<Note>> getMiscellaneous() {
        return (miscellaneous != null)
                ? Optional.of(Collections.unmodifiableList(miscellaneous)) : Optional.empty();
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
                && getTags().equals(e.getTags())
                && getStrengths().equals(e.getStrengths())
                && getWeaknesses().equals(e.getWeaknesses())
                && getMiscellaneous().equals(e.getMiscellaneous());
    }
}
