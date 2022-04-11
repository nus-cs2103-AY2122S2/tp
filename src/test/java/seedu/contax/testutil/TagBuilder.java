package seedu.contax.testutil;

import seedu.contax.model.tag.Tag;

public class TagBuilder {
    public static final String DEFAULT_TAGNAME = "clients";

    private String tagName;

    public TagBuilder() {
        tagName = DEFAULT_TAGNAME;
    }

    public TagBuilder(Tag tagToCopy) {
        tagName = tagToCopy.getTagNameString();
    }

    /**
     * Sets the {@code tagName} of the {@code Tag} that we are building.
     * @param tagName The specified tag name.
     * @return The updated TagBuilder with the name.
     */
    public TagBuilder withName(String tagName) {
        this.tagName = tagName;
        return this;
    }

    public Tag build() {
        return new Tag(tagName);
    }
}
