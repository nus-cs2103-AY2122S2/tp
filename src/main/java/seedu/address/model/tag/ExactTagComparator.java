package seedu.address.model.tag;

import java.util.Comparator;

public class ExactTagComparator<Tag> implements Comparator<Tag> {
    @Override
    public int compare(Tag tag1, Tag tag2) {
        return CharSequence.compare(tag1.toString(), tag2.toString());
    }
}
