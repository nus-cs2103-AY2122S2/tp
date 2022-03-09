package seedu.contax.model.tag;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.contax.model.tag.exceptions.DuplicateTagException;
import seedu.contax.model.tag.exceptions.TagNotFoundException;

/**
 * A list of tags that enforces uniqueness between its elements and does not allow nulls.
 * A tag is considered unique by comparing using {@code Tag#isSameTag(Tag)}. As such, adding and updating of tags
 * uses Tag#isSameTag(Tag) for equality so as to ensure that the tag is being added or updated is unique in terms of
 * identity in the UniqueTagList. However, the removal of a tag uses Tag#equals(Object) so as to ensure that the tag
 * with exactly the same fields will be removed.
 */
public class UniqueTagList implements Iterable<Tag> {
    private final ObservableList<Tag> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tag> internalUnmodifiableList = FXCollections.unmodifiableObservableList(internalList);

    /**
     * Checks the list if it contains an equivalent tag as the given argument.
     * @param toCheck The tag to check
     * @return true if there exists an equivalent tag in the list.
     */
    public boolean contains(Tag toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a tag to the list. The specified tag must not already exist in the list.
     * @param toAdd The specified tag to add
     */
    public void add(Tag toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTagException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes a tag from the list. If the tag does not exist in the list, a {@code TagNotFoundException} is thrown.
     * @param toRemove The specified tag to remove.
     */
    public void remove(Tag toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TagNotFoundException();
        }
    }

    public void setTags(UniqueTagList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setTags(List<Tag> tags) {
        requireNonNull(tags);

        if (!tagsAreUnique(tags)) {
            throw new DuplicateTagException();
        }

        internalList.setAll(tags);
    }

    public ObservableList<Tag> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Tag> iterator() {
        return internalList.iterator();
    }

    private boolean tagsAreUnique(List<Tag> tags) {
        for (int i = 0; i < tags.size(); i++) {
            for (int j = i + 1; j < tags.size(); j++) {
                if (tags.get(i).isSameTag(tags.get(j))) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof UniqueTagList)) {
            return false;
        }

        return ((UniqueTagList) o).internalList.equals(internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
