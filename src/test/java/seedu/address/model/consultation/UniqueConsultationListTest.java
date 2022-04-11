package seedu.address.model.consultation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import static seedu.address.testutil.TypicalConsultations.CONSULTATION_A;
import static seedu.address.testutil.TypicalConsultations.CONSULTATION_B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.UniqueConsultationList;
import seedu.address.model.consultation.exceptions.ConsultationNotFoundException;
import seedu.address.model.consultation.exceptions.DuplicateConsultationException;

public class UniqueConsultationListTest {

    private final UniqueConsultationList uniqueConsultationList = new UniqueConsultationList();

    @Test
    public void contains_nullConsultation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueConsultationList.contains(null));
    }

    @Test
    public void contains_consultationNotInList_returnsFalse() {
        assertFalse(uniqueConsultationList.contains(CONSULTATION_A));
    }

    @Test
    public void contains_consultationInList_returnsTrue() {
        uniqueConsultationList.add(CONSULTATION_A);
        assertTrue(uniqueConsultationList.contains(CONSULTATION_A));
    }


    @Test
    public void add_nullConsultation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueConsultationList.add(null));
    }

    @Test
    public void add_duplicateConsultation_throwsDuplicateConsultationException() {
        uniqueConsultationList.add(CONSULTATION_A);
        assertThrows(DuplicateConsultationException.class, () -> uniqueConsultationList.add(CONSULTATION_A));
    }

    @Test
    public void setConsultation_nullTargetConsultation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueConsultationList
                .setConsultation(null, CONSULTATION_A));
    }

    @Test
    public void setConsultation_nullEditedConsultation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueConsultationList
                .setConsultation(CONSULTATION_A, null));
    }

    @Test
    public void setConsultation_targetConsultationNotInList_throwsConsultationNotFoundException() {
        assertThrows(ConsultationNotFoundException.class, () -> uniqueConsultationList
                .setConsultation(CONSULTATION_A, CONSULTATION_A));
    }

    @Test
    public void setConsultation_editedConsultationIsSame_success() {
        uniqueConsultationList.add(CONSULTATION_A);
        uniqueConsultationList.setConsultation(CONSULTATION_A, CONSULTATION_A);
        UniqueConsultationList expectedUniqueConsultationList = new UniqueConsultationList();
        expectedUniqueConsultationList.add(CONSULTATION_A);
        assertEquals(expectedUniqueConsultationList, uniqueConsultationList);
    }

    @Test
    public void setConsultation_editedConsultationHasDifferentIdentity_success() {
        uniqueConsultationList.add(CONSULTATION_A);
        uniqueConsultationList.setConsultation(CONSULTATION_A, CONSULTATION_B);
        UniqueConsultationList expectedUniqueConsultationList = new UniqueConsultationList();
        expectedUniqueConsultationList.add(CONSULTATION_B);
        assertEquals(expectedUniqueConsultationList, uniqueConsultationList);
    }

    @Test
    public void setConsultation_editedConsultationHasNonUniqueIdentity_throwsDuplicateConsultationException() {
        uniqueConsultationList.add(CONSULTATION_A);
        uniqueConsultationList.add(CONSULTATION_B);
        assertThrows(DuplicateConsultationException.class, () ->
                uniqueConsultationList.setConsultation(CONSULTATION_A, CONSULTATION_B));
    }

    @Test
    public void remove_nullConsultation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueConsultationList.remove(null));
    }

    @Test
    public void remove_consultationDoesNotExist_throwsConsultationNotFoundException() {
        assertThrows(ConsultationNotFoundException.class, () -> uniqueConsultationList.remove(CONSULTATION_B));
    }

    @Test
    public void remove_existingConsultation_removesConsultation() {
        uniqueConsultationList.add(CONSULTATION_A);
        uniqueConsultationList.remove(CONSULTATION_A);
        UniqueConsultationList expectedUniqueConsultationList = new UniqueConsultationList();
        assertEquals(expectedUniqueConsultationList, uniqueConsultationList);
    }

    @Test
    public void setConsultations_nullUniqueConsultationList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueConsultationList.setConsultations((UniqueConsultationList) null));
    }

    @Test
    public void setConsultations_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueConsultationList.setConsultations((List<Consultation>) null));
    }

    @Test
    public void setConsultation_list_replacesOwnListWithProvidedList() {
        uniqueConsultationList.add(CONSULTATION_A);
        List<Consultation> consultationList = Collections.singletonList(CONSULTATION_B);
        uniqueConsultationList.setConsultations(consultationList);
        UniqueConsultationList expectedUniqueConsultationList = new UniqueConsultationList();
        expectedUniqueConsultationList.add(CONSULTATION_B);
        assertEquals(expectedUniqueConsultationList, uniqueConsultationList);
    }

    @Test
    public void setConsultation_listWithDuplicateConsultations_throwsDuplicateConsultationException() {
        List<Consultation> listWithDuplicateConsultations = Arrays.asList(CONSULTATION_A, CONSULTATION_A);
        assertThrows(DuplicateConsultationException.class, () ->
                uniqueConsultationList.setConsultations(listWithDuplicateConsultations));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueConsultationList
                .asUnmodifiableObservableList().remove(0));
    }
}
