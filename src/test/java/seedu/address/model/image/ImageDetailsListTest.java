package seedu.address.model.image;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSavedImages.TEST_IMAGE_1;
import static seedu.address.testutil.TypicalSavedImages.TEST_IMAGE_2;
import static seedu.address.testutil.TypicalSavedImages.TEST_IMAGE_3;
import static seedu.address.testutil.TypicalSavedImages.TEST_IMAGE_4;
import static seedu.address.testutil.TypicalSavedImages.TEST_IMAGE_5;
import static seedu.address.testutil.TypicalSavedImages.TEST_IMAGE_6;
import static seedu.address.testutil.TypicalSavedImages.TEST_IMAGE_7;
import static seedu.address.testutil.TypicalSavedImages.getTypicalImageDetailsList;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

class ImageDetailsListTest {
    private final ImageDetailsList emptyList = new ImageDetailsList();

    @Test
    void appendImageDetails_success() {
        ImageDetailsList expectedList = new ImageDetailsList(List.of(
                TEST_IMAGE_1, TEST_IMAGE_2, TEST_IMAGE_3, TEST_IMAGE_4, TEST_IMAGE_5, TEST_IMAGE_6, TEST_IMAGE_7
        ));

        final ImageDetailsList originalTestList = getTypicalImageDetailsList();
        List<ImageDetails> listToAppend = List.of(TEST_IMAGE_6, TEST_IMAGE_7);
        ImageDetailsList actualList = originalTestList.appendImageDetails(listToAppend);

        assertEquals(expectedList, actualList);
    }

    @Test
    void getImages_typicalImageDetailsList_success() {
        List<ImageDetails> expectedList = List.of(TEST_IMAGE_1, TEST_IMAGE_2, TEST_IMAGE_3, TEST_IMAGE_4, TEST_IMAGE_5);

        ImageDetailsList originalTestList = getTypicalImageDetailsList();
        List<ImageDetails> actualList = originalTestList.getImages();

        assertEquals(expectedList, actualList);
    }

    @Test
    void size() {
        assertEquals(0, emptyList.size());

        assertEquals(5, getTypicalImageDetailsList().size());
    }

    @Test
    void getImages_emptyImagesDetailsList_success() {
        assertEquals(List.of(), emptyList.getImages());
    }

    @Test
    void isEmpty_emptyList_returnsTrue() {
        // empty list
        assertTrue(emptyList.isEmpty());
    }

    @Test
    void isEmpty_nonEmptyList_returnsFalse() {
        // non-empty list
        assertFalse(getTypicalImageDetailsList().isEmpty());
    }

    @Test
    void get_success() {
        ImageDetailsList list = getTypicalImageDetailsList();
        // first item in list
        assertEquals(TEST_IMAGE_1, list.get(Index.fromOneBased(1).getZeroBased()));

        // last item in list
        assertEquals(TEST_IMAGE_5, list.get(Index.fromOneBased(list.size()).getZeroBased()));
    }

    @Test
    void get_outOfBounds_throwsIndexOutOfBoundsException() {
        ImageDetailsList list = getTypicalImageDetailsList();
        // index beyond size
        Index outOfBoundsIndex = Index.fromZeroBased(getTypicalImageDetailsList().size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(outOfBoundsIndex.getZeroBased()));

        // index negative;
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    void equals() {
        ImageDetailsList imageDetailsList = getTypicalImageDetailsList();
        ImageDetailsList otherImageDetailsList = new ImageDetailsList(List.of(
                TEST_IMAGE_1, TEST_IMAGE_2, TEST_IMAGE_3, TEST_IMAGE_4, TEST_IMAGE_5));

        // same object -> return true
        assertTrue(imageDetailsList.equals(imageDetailsList));

        // different object, same values -> return true
        assertTrue(imageDetailsList.equals(otherImageDetailsList));

        // different object, different values -> return false
        assertFalse(imageDetailsList.equals(emptyList));
    }
}
