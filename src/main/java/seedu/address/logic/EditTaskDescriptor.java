package seedu.address.logic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.name.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.EndTime;
import seedu.address.model.task.StartTime;

/**
 * Stores the details to edit the task with. Each non-empty field value will replace the
 * corresponding field value of the task.
 */
public class EditTaskDescriptor {
    private Name name;
    private Date date;
    private StartTime startTime;
    private EndTime endTime;
    private Set<Tag> tags;
    private Set<Name> persons;

    public EditTaskDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditTaskDescriptor(EditTaskDescriptor toCopy) {
        setName(toCopy.name);
        setDate(toCopy.date);
        setStartTime(toCopy.startTime);
        setEndTime(toCopy.endTime);
        setTags(toCopy.tags);
        setPersons(toCopy.persons);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, date, startTime, endTime, tags, persons);
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Optional<Date> getDate() {
        return Optional.ofNullable(date);
    }

    public void setStartTime(StartTime startTime) {
        this.startTime = startTime;
    }

    public Optional<StartTime> getStartTime() {
        return Optional.ofNullable(startTime);
    }

    public void setEndTime(EndTime endTime) {
        this.endTime = endTime;
    }

    public Optional<EndTime> getEndTime() {
        return Optional.ofNullable(endTime);
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
     * Sets {@code persons} to this object's {@code persons}.
     * A defensive copy of {@code persons} is used internally.
     */
    public void setPersons(Set<Name> persons) {
        this.persons = (persons != null) ? new HashSet<>(persons) : null;
    }

    /**
     * Returns an unmodifiable person set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code persons} is null.
     */
    public Optional<Set<Name>> getPersons() {
        return (persons != null) ? Optional.of(Collections.unmodifiableSet(persons)) : Optional.empty();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskDescriptor)) {
            return false;
        }

        // state check
        EditTaskDescriptor e = (EditTaskDescriptor) other;

        return getName().equals(e.getName())
                && getDate().equals(e.getDate())
                && getStartTime().equals(e.getStartTime())
                && getEndTime().equals(e.getEndTime())
                && getTags().equals(e.getTags())
                && getPersons().equals(e.getPersons());
    }
}
