package seedu.address.model.assessment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssessments.CS2101_PARTICIPATION_WITH_ATTEMPT;
import static seedu.address.testutil.TypicalAssessments.CS2103T_PARTICIPATION_NO_ATTEMPT;
import static seedu.address.testutil.TypicalModules.CS2103T_WITH_STUDENT;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.assessment.exceptions.AssessmentNotFoundException;
import seedu.address.model.assessment.exceptions.DuplicateAssessmentException;
import seedu.address.testutil.AssessmentBuilder;
import seedu.address.testutil.TypicalAssessments;


public class UniqueAssessmentListTest {

    private final UniqueAssessmentList uniqueAssessmentList = new UniqueAssessmentList();

    @Test
    public void contains_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssessmentList.contains(null));
    }

    @Test
    public void contains_moduleNotInList_returnsFalse() {
        assertFalse(uniqueAssessmentList.contains(CS2103T_PARTICIPATION_NO_ATTEMPT));
    }

    @Test
    public void contains_moduleInList_returnsTrue() {
        uniqueAssessmentList.add(CS2103T_PARTICIPATION_NO_ATTEMPT);
        assertTrue(uniqueAssessmentList.contains(CS2103T_PARTICIPATION_NO_ATTEMPT));
    }

    @Test
    public void contains_moduleWithSameIdentityFieldsInList_returnsTrue() {
        uniqueAssessmentList.add(CS2103T_PARTICIPATION_NO_ATTEMPT);
        Assessment editedAssessment = new AssessmentBuilder()
                .withAssessmentName(TypicalAssessments.PARTICIPATION).withTaModule(CS2103T_WITH_STUDENT)
                .build();
        assertTrue(uniqueAssessmentList.contains(editedAssessment));

        editedAssessment = new AssessmentBuilder()
                .withSimpleName(TypicalAssessments.PARTICIPATION_SIMPLE_NAME).withTaModule(CS2103T_WITH_STUDENT)
                .build();
        assertTrue(uniqueAssessmentList.contains(editedAssessment));
    }

    @Test
    public void add_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssessmentList.add(null));
    }

    @Test
    public void add_duplicateModule_throwsDuplicateAssessmentException() {
        uniqueAssessmentList.add(CS2103T_PARTICIPATION_NO_ATTEMPT);
        assertThrows(DuplicateAssessmentException.class, () ->
                uniqueAssessmentList.add(CS2103T_PARTICIPATION_NO_ATTEMPT));
    }

    @Test
    public void setAssessment_nullTargetModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssessmentList
                .setAssessment(null, CS2103T_PARTICIPATION_NO_ATTEMPT));
    }

    @Test
    public void setAssessment_nullEditedModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssessmentList
                .setAssessment(CS2103T_PARTICIPATION_NO_ATTEMPT, null));
    }

    @Test
    public void setAssessment_targetModuleNotInList_throwsAssessmentNotFoundException() {
        assertThrows(AssessmentNotFoundException.class, () -> uniqueAssessmentList
                .setAssessment(CS2103T_PARTICIPATION_NO_ATTEMPT, CS2103T_PARTICIPATION_NO_ATTEMPT));
    }

    @Test
    public void setAssessment_editedModuleIsSameModule_success() {
        uniqueAssessmentList.add(CS2103T_PARTICIPATION_NO_ATTEMPT);
        uniqueAssessmentList.setAssessment(CS2103T_PARTICIPATION_NO_ATTEMPT, CS2103T_PARTICIPATION_NO_ATTEMPT);
        UniqueAssessmentList expectedUniqueAssessmentList = new UniqueAssessmentList();
        expectedUniqueAssessmentList.add(CS2103T_PARTICIPATION_NO_ATTEMPT);
        assertEquals(expectedUniqueAssessmentList, uniqueAssessmentList);
    }

    @Test
    public void setAssessment_editedModuleHasDifferentIdentity_success() {
        uniqueAssessmentList.add(CS2103T_PARTICIPATION_NO_ATTEMPT);
        uniqueAssessmentList.setAssessment(CS2103T_PARTICIPATION_NO_ATTEMPT, CS2101_PARTICIPATION_WITH_ATTEMPT);
        UniqueAssessmentList expectedUniqueAssessmentList = new UniqueAssessmentList();
        expectedUniqueAssessmentList.add(CS2101_PARTICIPATION_WITH_ATTEMPT);
        assertEquals(expectedUniqueAssessmentList, uniqueAssessmentList);
    }

    @Test
    public void setAssessment_editedModuleHasNonUniqueIdentity_throwsDuplicateAssessmentException() {
        uniqueAssessmentList.add(CS2103T_PARTICIPATION_NO_ATTEMPT);
        uniqueAssessmentList.add(CS2101_PARTICIPATION_WITH_ATTEMPT);
        assertThrows(DuplicateAssessmentException.class, () -> uniqueAssessmentList
                .setAssessment(CS2103T_PARTICIPATION_NO_ATTEMPT, CS2101_PARTICIPATION_WITH_ATTEMPT));
    }

    @Test
    public void remove_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssessmentList.remove(null));
    }

    @Test
    public void remove_moduleDoesNotExist_throwsAssessmentNotFoundException() {
        assertThrows(AssessmentNotFoundException.class, () ->
                 uniqueAssessmentList.remove(CS2103T_PARTICIPATION_NO_ATTEMPT));
    }

    @Test
    public void remove_existingModule_removesModule() {
        uniqueAssessmentList.add(CS2103T_PARTICIPATION_NO_ATTEMPT);
        uniqueAssessmentList.remove(CS2103T_PARTICIPATION_NO_ATTEMPT);
        UniqueAssessmentList expectedUniqueAssessmentList = new UniqueAssessmentList();
        assertEquals(expectedUniqueAssessmentList, uniqueAssessmentList);
    }

    @Test
    public void setAssessments_nullUniqueAssessmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueAssessmentList.setAssessments((UniqueAssessmentList) null));
    }

    @Test
    public void setAssessments_uniqueAssessmentList_replacesOwnListWithProvidedUniqueAssessmentList() {
        uniqueAssessmentList.add(CS2103T_PARTICIPATION_NO_ATTEMPT);
        UniqueAssessmentList expectedUniqueAssessmentList = new UniqueAssessmentList();
        expectedUniqueAssessmentList.add(CS2101_PARTICIPATION_WITH_ATTEMPT);
        uniqueAssessmentList.setAssessments(expectedUniqueAssessmentList);
        assertEquals(expectedUniqueAssessmentList, uniqueAssessmentList);
    }

    @Test
    public void setAssessments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssessmentList.setAssessments((List<Assessment>) null));
    }

    @Test
    public void setAssessments_list_replacesOwnListWithProvidedList() {
        uniqueAssessmentList.add(CS2103T_PARTICIPATION_NO_ATTEMPT);
        List<Assessment> assessmentList = Collections.singletonList(CS2101_PARTICIPATION_WITH_ATTEMPT);
        uniqueAssessmentList.setAssessments(assessmentList);
        UniqueAssessmentList expectedUniqueAssessmentList = new UniqueAssessmentList();
        expectedUniqueAssessmentList.add(CS2101_PARTICIPATION_WITH_ATTEMPT);
        assertEquals(expectedUniqueAssessmentList, uniqueAssessmentList);
    }

    @Test
    public void setAssessments_listWithDuplicateModules_throwsDuplicateAssessmentException() {
        List<Assessment> listWithDuplicateAssessments = Arrays.asList(CS2103T_PARTICIPATION_NO_ATTEMPT,
                CS2103T_PARTICIPATION_NO_ATTEMPT);
        assertThrows(DuplicateAssessmentException.class, () ->
                uniqueAssessmentList.setAssessments(listWithDuplicateAssessments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
            uniqueAssessmentList.asUnmodifiableObservableList().remove(0));
    }
}
