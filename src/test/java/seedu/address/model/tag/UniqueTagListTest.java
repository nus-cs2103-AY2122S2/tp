package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.VALID_TAG_COLLEAGUES;
import static seedu.address.testutil.TypicalTags.VALID_TAG_FRIENDS;
import static seedu.address.testutil.TypicalTags.VALID_TAG_NEIGHBOURS;
import static seedu.address.testutil.TypicalTags.VALID_TAG_TEST;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

public class UniqueTagListTest {

    private final UniqueTagList uniqueTagList = new UniqueTagList();

    @Test
    public void contains_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.contains(null));
    }

    @Test
    public void contains_tagNotInList_returnsFalse() {
        assertFalse(uniqueTagList.contains(VALID_TAG_FRIENDS));
    }

    @Test
    public void contains_tagInList_returnsTrue() {
        uniqueTagList.add(VALID_TAG_NEIGHBOURS);
        assertTrue(uniqueTagList.contains(VALID_TAG_NEIGHBOURS));
    }

    @Test
    public void contains_tagWithSameNameDifferentCase_returnsTrue() {
        uniqueTagList.add(VALID_TAG_FRIENDS);
        Tag sameNameDifferentCaseTag = new Tag("frIenDs");
        assertTrue(uniqueTagList.contains(sameNameDifferentCaseTag));
    }

    @Test
    public void add_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.add(null));
    }

    @Test
    public void add_duplicateTag_throwsDuplicateTagException() {
        uniqueTagList.add(VALID_TAG_NEIGHBOURS);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.add(VALID_TAG_NEIGHBOURS));
    }

    @Test
    public void setTag_nullTargetTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTag(null, VALID_TAG_FRIENDS));
    }

    @Test
    public void setTag_nullEditedTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTag(VALID_TAG_COLLEAGUES, null));
    }

    @Test
    public void setTag_targetTagNotInList_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagList.setTag(VALID_TAG_TEST, VALID_TAG_TEST));
    }

    @Test
    public void setTag_editedTagIsSameTag_success() {
        uniqueTagList.add(VALID_TAG_NEIGHBOURS);
        uniqueTagList.setTag(VALID_TAG_NEIGHBOURS, VALID_TAG_NEIGHBOURS);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(VALID_TAG_NEIGHBOURS);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTag_editedTagHasSameNameDifferentCase_success() {
        uniqueTagList.add(VALID_TAG_FRIENDS);
        Tag sameNameDifferentCaseTag = new Tag("frIeNdS");
        uniqueTagList.setTag(VALID_TAG_FRIENDS, sameNameDifferentCaseTag);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(sameNameDifferentCaseTag);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTag_editedTagHasDifferentIdentity_success() {
        uniqueTagList.add(VALID_TAG_FRIENDS);
        uniqueTagList.setTag(VALID_TAG_FRIENDS, VALID_TAG_NEIGHBOURS);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(VALID_TAG_NEIGHBOURS);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTag_editedTagHasNonUniqueIdentity_throwsDuplicateTagException() {
        uniqueTagList.add(VALID_TAG_TEST);
        uniqueTagList.add(VALID_TAG_FRIENDS);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.setTag(VALID_TAG_TEST, VALID_TAG_FRIENDS));
    }

    @Test
    public void remove_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.remove(null));
    }

    @Test
    public void remove_tagDoesNotExist_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagList.remove(VALID_TAG_FRIENDS));
    }

    @Test
    public void remove_existingTag_removesTag() {
        uniqueTagList.add(VALID_TAG_FRIENDS);
        uniqueTagList.remove(VALID_TAG_FRIENDS);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_nullUniqueTagList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((UniqueTagList) null));
    }

    @Test
    public void setTags_uniqueTagList_replacesOwnListWithProvidedUniqueTagList() {
        uniqueTagList.add(VALID_TAG_FRIENDS);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(VALID_TAG_TEST);
        uniqueTagList.setTags(expectedUniqueTagList);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((List<Tag>) null));
    }

    @Test
    public void setTags_list_replacesOwnListWithProvidedList() {
        uniqueTagList.add(VALID_TAG_NEIGHBOURS);
        List<Tag> tagList = Collections.singletonList(VALID_TAG_FRIENDS);
        uniqueTagList.setTags(tagList);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(VALID_TAG_FRIENDS);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_listWithDuplicateTags_throwsDuplicatePersonException() {
        List<Tag> listWithDuplicatePersons = Arrays.asList(VALID_TAG_COLLEAGUES, VALID_TAG_COLLEAGUES);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.setTags(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTagList.asUnmodifiableObservableList().remove(0));
    }
}
