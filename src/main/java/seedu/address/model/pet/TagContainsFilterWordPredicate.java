package seedu.address.model.pet;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

import java.util.HashSet;
import java.util.Set;

public class TagContainsFilterWordPredicate extends FilterByContainsFilterWordPredicate{

    public TagContainsFilterWordPredicate(String filterWord) {
        super(filterWord);
    }

    @Override
    public boolean test(Pet pet) {
        Tag tempFilterTag = new Tag(filterWord);
        return pet.getTags().contains(tempFilterTag);
    }
}
