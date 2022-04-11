package seedu.address.storage;

import java.util.Map;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Priority;
import seedu.address.model.tag.Tag;

/**
 * CSV-friendly version of {@link Tag}.
 */
class CsvAdaptedTag {

    public static final Map<String, Priority> STRING_PRIORITY_MAP = Map.of("[P1]", Priority.PRIORITY_1,
            "[P2]", Priority.PRIORITY_2, "[P3]", Priority.PRIORITY_3, "[P4]", Priority.PRIORITY_4);
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

    public String getTagNameString() {
        String priorityString = "";
        if (tagPriority != null) {
            for (Map.Entry<String, Priority> e: STRING_PRIORITY_MAP.entrySet()) {
                if (e.getValue() == tagPriority) {
                    priorityString = e.getKey();
                    break;
                }
            }
        }

        return tagName + priorityString;
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
        return other == this // short circuit if same object
                || (other instanceof CsvAdaptedTag // instanceof handles nulls
                && tagName.equals(((CsvAdaptedTag) other).tagName)
                && tagPriority == ((CsvAdaptedTag) other).tagPriority);
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    @Override
    public String toString() {
        return tagName + tagPriority;
    }
}
