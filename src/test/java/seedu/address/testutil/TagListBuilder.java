package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.tag.Priority;
import seedu.address.model.tag.Tag;

public class TagListBuilder {
    public static final Tag DEFAULT_TAG_1 = new Tag("owes money", Priority.PRIORITY_2);
    public static final Tag DEFAULT_TAG_2 = new Tag("friends and family", null);
    public static final Tag DEFAULT_TAG_3 = new Tag("updating address soon", Priority.PRIORITY_4);

    public static final Tag ADD_TAG_1 = new Tag("MEET ON SUNDAY", Priority.PRIORITY_1);
    public static final Tag ADD_TAG_2 = new Tag("not in country now", null);


    private ArrayList<Tag> tagList;

    /**
     * Initializes the TagBuilder with the default tags.
     */
    public TagListBuilder() {
        tagList = new ArrayList<>(List.of(DEFAULT_TAG_1, DEFAULT_TAG_2, DEFAULT_TAG_3));
    }

    /**
     * Initializes the TagBuilder with the specified tags.
     * @param tags specified tags
     */
    public TagListBuilder(Tag... tags) {
        tagList = new ArrayList<>(Arrays.asList(tags));
    }

    /**
     * Turns TagListBuilder to a ArrayList.
     * @return list containing {@code Tag} in tagList
     */
    public ArrayList<Tag> build() {
        return tagList;
    }
}
