package seedu.address.testutil;

import seedu.address.model.tag.Tag;

public class TagBuilder {
    public static final String DEFAULT_TEXT = "CEO of Tesla";

    private String text;

    /**
     * Creates a {@code TagBuilder} with the default details.
     */
    public TagBuilder() {
        text = DEFAULT_TEXT;
    }

    /**
     * Initializes the ApplicantBuilder with the data of {@code tagToCopy}.
     */
    public TagBuilder(Tag tagToCopy) {
        text = tagToCopy.tagName;
    }

    /**
     * Sets the {@code text} of the {@code Tag} that we are building.
     */
    public TagBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public Tag build() {
        return new Tag(text);
    }
}
