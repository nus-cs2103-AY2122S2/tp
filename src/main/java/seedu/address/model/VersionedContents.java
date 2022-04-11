package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Content.getContentCopy;

import java.util.LinkedList;
import java.util.List;

/**
 * Stores previous Content versions since application initialisation
 */
public class VersionedContents {
    private final List<Content> contentStateList = new LinkedList<>();

    /** the index pointing to the current content state of TAilor */
    private int currentStatePointer;

    /**
     * Constructor for the VersionedContents object.
     * @param initialContent initial content state of the app
     */
    public VersionedContents(Content initialContent) {
        requireNonNull(initialContent);
        contentStateList.add(initialContent);

        currentStatePointer = 0;
    }

    /**
     * Create a new copy of VersionedContents from a given one
     * @param otherVersionedContent other VersionedContents to copy from
     */
    public VersionedContents(VersionedContents otherVersionedContent) {
        requireNonNull(otherVersionedContent);
        List<Content> newContentStates = otherVersionedContent.getContentStateList();

        assert newContentStates.size() > 0; // VersionedContents must always contain 1 content version at least

        this.contentStateList.addAll(List.copyOf(newContentStates));
        this.currentStatePointer = newestVersionIndex();
    }

    /**
     * Adds a new Content version.
     * @param newContentVersion new content version to add
     */
    public void addContentVersion(Content newContentVersion) {
        requireNonNull(newContentVersion);

        if (currentStatePointer != newestVersionIndex()) { // remove all trailing versions
            removeToEnd();
        }

        contentStateList.add(newContentVersion);
        currentStatePointer = newestVersionIndex();
    }

    /**
     * Returns a copy of current content state of TAilor
     */
    public Content getCurrentContent() {
        return getContentCopy(contentStateList.get(currentStatePointer));
    }

    /**
     * Returns a copy of the content version list
     */
    public List<Content> getContentStateList() {
        return List.copyOf(this.contentStateList);
    }

    /**
     * Returns the most recent previous content state
     */
    public Content undo() {
        assert currentStatePointer > 0;

        currentStatePointer -= 1;
        return contentStateList.get(currentStatePointer);
    }

    /**
     * Returns the content state after the current one
     */
    public Content redo() {
        assert currentStatePointer < newestVersionIndex();

        currentStatePointer += 1;
        return contentStateList.get(currentStatePointer);
    }

    /**
     * Returns true if at the earliest Content version, false otherwise
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    /**
     * Returns true if at the latest Content version, false otherwise
     */
    public boolean canRedo() {
        return currentStatePointer < newestVersionIndex();
    }

    /**
     * Returns the index of the last version in content state list
     */
    private int newestVersionIndex() {
        return contentStateList.size() - 1;
    }

    /**
     * Removes all contents after the current state pointer in the content list
     */
    private void removeToEnd() {
        int startIndexToClear = currentStatePointer + 1;
        int endIndexToClear = contentStateList.size();
        contentStateList.subList(startIndexToClear, endIndexToClear).clear();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VersionedContents)) {
            return false;
        }

        return this.contentStateList.equals(((VersionedContents) other).getContentStateList());
    }

    /**
     * String representation of the versioned contact list.
     */
    @Override
    public String toString() {
        StringBuilder resString = new StringBuilder();
        for (Content contentVersion : contentStateList) {
            resString.append(contentVersion.toString() + "\n");
        }
        return resString.toString();
    }
}
