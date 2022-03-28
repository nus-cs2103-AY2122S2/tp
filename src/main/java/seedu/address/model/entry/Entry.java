package seedu.address.model.entry;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;

public abstract class Entry {
    private final Name name;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a new Entry object given the name and tags.
     * @param name Name of the entry.
     * @param tags Tags associated with the entry.
     */
    public Entry(Name name, Set<Tag> tags) {
        requireAllNonNull(name, tags);
        this.name = name;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two entries.
     */
    public abstract boolean isSameEntry(Entry otherEntry);

    /**
     * If the companyName of the entry matches {@code oldName}, then it is
     * changed to {@code newName}.
     * Note: This only applies for Person and Event. Company does not have any defined functionality for this.
     */
    public abstract void updateCompanyName(String oldName, String newName);
}
