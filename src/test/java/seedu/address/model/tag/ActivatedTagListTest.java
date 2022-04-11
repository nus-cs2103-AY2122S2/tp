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

class ActivatedTagListTest {
    private final ActivatedTagList activatedTagList = new ActivatedTagList();

    @Test
    public void contains_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> activatedTagList.contains(null));
    }

    @Test
    public void contains_tagNotInList_returnsFalse() {
        assertFalse(activatedTagList.contains(VALID_TAG_FRIENDS));
    }

    @Test
    public void contains_tagInList_returnsTrue() {
        activatedTagList.add(VALID_TAG_FRIENDS);
        assertTrue(activatedTagList.contains(VALID_TAG_FRIENDS));
    }

    @Test
    public void contains_tagWithSameNameDifferentCase_returnsTrue() {
        activatedTagList.add(VALID_TAG_NEIGHBOURS);
        Tag sameNameDifferentCaseTag = new Tag("neighBouRs");
        assertTrue(activatedTagList.contains(sameNameDifferentCaseTag));
    }

    @Test
    public void add_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> activatedTagList.add(null));
    }

    @Test
    public void add_duplicateTag_throwsDuplicateTagException() {
        activatedTagList.add(VALID_TAG_FRIENDS);
        assertThrows(DuplicateTagException.class, () -> activatedTagList.add(VALID_TAG_FRIENDS));
    }

    @Test
    public void setTag_nullTargetTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> activatedTagList.setTag(null, VALID_TAG_FRIENDS));
    }

    @Test
    public void setTag_nullEditedTag_throwsNullPointerException() {
        activatedTagList.add(VALID_TAG_FRIENDS);
        assertThrows(NullPointerException.class, () -> activatedTagList.setTag(VALID_TAG_FRIENDS, null));
    }

    @Test
    public void setTag_targetTagNotInLIst_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> activatedTagList.setTag(VALID_TAG_FRIENDS, VALID_TAG_FRIENDS));
    }

    @Test
    public void setTag_editedTagIsSameTag_success() {
        activatedTagList.add(VALID_TAG_NEIGHBOURS);
        activatedTagList.setTag(VALID_TAG_NEIGHBOURS, VALID_TAG_NEIGHBOURS);
        ActivatedTagList expectedActivatedTagList = new ActivatedTagList();
        expectedActivatedTagList.add(VALID_TAG_NEIGHBOURS);
        assertEquals(expectedActivatedTagList, activatedTagList);
    }

    @Test
    public void setTag_editedTagHasSameNameDifferentCase_success() {
        activatedTagList.add(VALID_TAG_FRIENDS);
        Tag sameNameDifferentCaseTag = new Tag("frIeNDs");
        activatedTagList.setTag(VALID_TAG_FRIENDS, sameNameDifferentCaseTag);
        ActivatedTagList expectedActivatedTagList = new ActivatedTagList();
        expectedActivatedTagList.add(sameNameDifferentCaseTag);
        assertEquals(expectedActivatedTagList, activatedTagList);
    }

    @Test
    public void setTag_editedTagHasNonUniqueIdentity_throwsDuplicateTagException() {
        activatedTagList.add(VALID_TAG_TEST);
        activatedTagList.add(VALID_TAG_FRIENDS);
        assertThrows(DuplicateTagException.class, () -> activatedTagList.setTag(VALID_TAG_TEST, VALID_TAG_FRIENDS));
    }

    @Test
    public void remove_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> activatedTagList.remove(null));
    }

    @Test
    public void remove_tagDoesNotExist_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> activatedTagList.remove(VALID_TAG_FRIENDS));
    }

    @Test
    public void remove_existingTag_removesTag() {
        activatedTagList.add(VALID_TAG_FRIENDS);
        activatedTagList.remove(VALID_TAG_FRIENDS);
        ActivatedTagList expectedActivatedTagList = new ActivatedTagList();
        assertEquals(expectedActivatedTagList, activatedTagList);
    }

    @Test
    public void setTags_activatedTagList_replacesOwnListWithProvidedActivatedTagList() {
        activatedTagList.add(VALID_TAG_FRIENDS);
        ActivatedTagList expectedActivatedTagList = new ActivatedTagList();
        expectedActivatedTagList.add(VALID_TAG_TEST);
        activatedTagList.setTags(expectedActivatedTagList);
        assertEquals(expectedActivatedTagList, activatedTagList);
    }

    @Test
    public void setTags_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> activatedTagList.setTags((List<Tag>) null));
    }

    @Test
    public void setTags_list_replacesOwnListWithProvidedList() {
        activatedTagList.add(VALID_TAG_NEIGHBOURS);
        List<Tag> tagList = Collections.singletonList(VALID_TAG_FRIENDS);
        activatedTagList.setTags(tagList);
        ActivatedTagList expectedActivatedTagList = new ActivatedTagList();
        expectedActivatedTagList.add(VALID_TAG_FRIENDS);
        assertEquals(expectedActivatedTagList, activatedTagList);
    }

    @Test
    public void setTags_listWithDuplicateTags_throwsDuplicatePersonException() {
        List<Tag> listWithDuplicatePersons = Arrays.asList(VALID_TAG_COLLEAGUES, VALID_TAG_COLLEAGUES);
        assertThrows(DuplicateTagException.class, () -> activatedTagList.setTags(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> activatedTagList.asUnmodifiableObservableList().remove(0));
    }
}
