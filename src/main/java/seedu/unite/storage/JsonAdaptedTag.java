package seedu.unite.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.unite.commons.exceptions.IllegalValueException;
import seedu.unite.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedTag {

    private final String tagName;
    private final String tagRemark;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName} and remark.
     */
    @JsonCreator
    public JsonAdaptedTag(@JsonProperty("name") String tagName, @JsonProperty("remark") String remark) {
        this.tagName = tagName;
        tagRemark = remark;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTag(Tag source) {
        tagName = source.tagName;
        tagRemark = source.getRemark().toString();
    }

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName} and remark.
     */
    @JsonCreator
    public JsonAdaptedTag(String tagName) {
        this.tagName = tagName;
        tagRemark = "";
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Tag toModelType() throws IllegalValueException {
        if (!Tag.isValidTagName(tagName)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(tagName, tagRemark);
    }

}
