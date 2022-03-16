package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Priority;
import seedu.address.model.tag.Tag;

/**
 * CSV-friendly version of {@link Tag}.
 */
class CsvAdaptedTag {

    private final String tagName;
    private final Priority tagPriority;

    /**
     * Constructs a {@code CsvAdaptedTag} with the given {@code tagName}.
     */
    public CsvAdaptedTag(String tagName, Priority priority) {
        this.tagName = tagName;
        this.tagPriority = priority;
    }

    /**
     * Converts a given {@code Tag} into this class for CSV use.
     */
    public CsvAdaptedTag(Tag source) {
        tagName = source.tagName;
        tagPriority = source.tagPriority;
    }

    public String getTagName() {
        return tagName;
    }

    public Priority getTagPriority() {
        return tagPriority;
    }

    /**
     * Converts this CSV-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Tag toModelType() throws IllegalValueException {
        if (!Tag.isValidTagName(tagName)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(tagName, tagPriority);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CsvAdaptedTag)) {
            return false;
        }

        CsvAdaptedTag otherTag = (CsvAdaptedTag) other;
        return tagName.equals(otherTag.tagName) && tagPriority.equals(otherTag.tagPriority);
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }
}
