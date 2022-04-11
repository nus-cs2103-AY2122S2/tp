package seedu.address.model.tutorial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_DAY_TG1;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_VENUE_TG1;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutorials.T01;
import static seedu.address.testutil.TypicalTutorials.T02;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tutorial.exceptions.DuplicateTutorialException;
import seedu.address.model.tutorial.exceptions.TutorialNotFoundException;
import seedu.address.testutil.TutorialBuilder;

public class UniqueTutorialListTest {

    private final UniqueTutorialList uniqueTutorialList = new UniqueTutorialList();

    @Test
    public void contains_nullTutorial_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialList.contains(null));
    }

    @Test
    public void contains_tutorialNotInList_returnsFalse() {
        assertFalse(uniqueTutorialList.contains(T01));
    }

    @Test
    public void contains_tutorialInList_returnsTrue() {
        uniqueTutorialList.add(T01);
        assertTrue(uniqueTutorialList.contains(T01));
    }

    @Test
    public void contains_tutorialWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTutorialList.add(T01);
        Tutorial editedT01 = new TutorialBuilder(T01).withVenue(VALID_VENUE_TG1).withDay(VALID_DAY_TG1)
                .build();
        assertTrue(uniqueTutorialList.contains(editedT01));
    }

    @Test
    public void add_nullTutorial_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialList.add(null));
    }

    @Test
    public void add_duplicateTutorial_throwsDuplicateTutorialException() {
        uniqueTutorialList.add(T01);
        assertThrows(DuplicateTutorialException.class, () -> uniqueTutorialList.add(T01));
    }

    @Test
    public void seTutorial_nullTargetTutorial_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialList.setTutorial(null, T01));
    }

    @Test
    public void setTutorial_nullEditedTutorial_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialList.setTutorial(T01, null));
    }

    @Test
    public void setTutorial_targetTutorialNotInList_throwsTutorialNotFoundException() {
        assertThrows(TutorialNotFoundException.class, () -> uniqueTutorialList.setTutorial(T01, T01));
    }

    @Test
    public void setTutorial_editedTutorialIsSameTutorial_success() {
        uniqueTutorialList.add(T01);
        uniqueTutorialList.setTutorial(T01, T01);
        UniqueTutorialList expectedUniqueTutorialList = new UniqueTutorialList();
        expectedUniqueTutorialList.add(T01);
        assertEquals(expectedUniqueTutorialList, uniqueTutorialList);
    }

    @Test
    public void setTutorial_editedTutorialHasSameIdentity_success() {
        uniqueTutorialList.add(T01);
        Tutorial editedT01 = new TutorialBuilder(T01).withVenue(VALID_VENUE_TG1).withDay(VALID_DAY_TG1)
                .build();
        uniqueTutorialList.setTutorial(T01, editedT01);
        UniqueTutorialList expectedUniqueTutorialList = new UniqueTutorialList();
        expectedUniqueTutorialList.add(editedT01);
        assertEquals(expectedUniqueTutorialList, uniqueTutorialList);
    }

    @Test
    public void setTutorial_editedTutorialHasDifferentIdentity_success() {
        uniqueTutorialList.add(T01);
        uniqueTutorialList.setTutorial(T01, T02);
        UniqueTutorialList expectedUniqueTutorialList = new UniqueTutorialList();
        expectedUniqueTutorialList.add(T02);
        assertEquals(expectedUniqueTutorialList, uniqueTutorialList);
    }

    @Test
    public void setTutorial_editedTutorialHasNonUniqueIdentity_throwsDuplicateTutorialException() {
        uniqueTutorialList.add(T01);
        uniqueTutorialList.add(T02);
        assertThrows(DuplicateTutorialException.class, () -> uniqueTutorialList.setTutorial(T01, T02));
    }

    @Test
    public void remove_nullTutorial_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialList.remove(null));
    }

    @Test
    public void remove_tutorialDoesNotExist_throwsTutorialNotFoundException() {
        assertThrows(TutorialNotFoundException.class, () -> uniqueTutorialList.remove(T01));
    }

    @Test
    public void remove_existingTutorial_removesTutorial() {
        uniqueTutorialList.add(T01);
        uniqueTutorialList.remove(T01);
        UniqueTutorialList expectedUniqueTutorialList = new UniqueTutorialList();
        assertEquals(expectedUniqueTutorialList, uniqueTutorialList);
    }

    @Test
    public void setTutorials_nullUniqueTutorialList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialList.setTutorials((List<Tutorial>) null));
    }

    @Test
    public void setTutorials_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTutorialList.setTutorials((List<Tutorial>) null));
    }

    @Test
    public void setTutorial_list_replacesOwnListWithProvidedList() {
        uniqueTutorialList.add(T01);
        List<Tutorial> tutorialList = Collections.singletonList(T02);
        uniqueTutorialList.setTutorials(tutorialList);
        UniqueTutorialList expectedUniqueTutorialList = new UniqueTutorialList();
        expectedUniqueTutorialList.add(T02);
        assertEquals(expectedUniqueTutorialList, uniqueTutorialList);
    }

    @Test
    public void setTutorials_listWithDuplicateTutorials_throwsDuplicateTutorialException() {
        List<Tutorial> listWithDuplicateTutorials = Arrays.asList(T01, T01);
        assertThrows(DuplicateTutorialException.class, () -> uniqueTutorialList
                .setTutorials(listWithDuplicateTutorials));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueTutorialList
                .asUnmodifiableObservableList().remove(0));
    }
}
