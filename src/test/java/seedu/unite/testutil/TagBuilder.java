package seedu.unite.testutil;

import seedu.unite.model.tag.Tag;

public class TagBuilder {

    public static final String DEFAULT_TAG_NAME = "friend";
    private Tag tag;

    /**
     * Creates a {@code TagBuilder} with the default details.
     */
    public TagBuilder() {
        tag = new Tag(DEFAULT_TAG_NAME);
    }

    /**
     * Initializes the TagBuilder with the data of {@code tagToCopy}.
     */
    public TagBuilder(Tag tagToCopy) {
        tag = tagToCopy;
    }

    /**
     * Sets the {@code TagName} of the {@code Tag} that we are building.
     */
    public TagBuilder withTagName(String tagName) {
        this.tag = new Tag(tagName);
        return this;
    }

    public Tag build() {
        return tag;
    }
}
