package seedu.address.model.prescription;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPrescriptions.PRESCRIPTION_A;
import static seedu.address.testutil.TypicalPrescriptions.PRESCRIPTION_B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.prescription.exceptions.DuplicatePrescriptionException;
import seedu.address.model.prescription.exceptions.PrescriptionNotFoundException;
import seedu.address.testutil.PrescriptionBuilder;

public class UniquePrescriptionListTest {

    private final UniquePrescriptionList uniquePrescriptionList = new UniquePrescriptionList();

    @Test
    public void contains_nullPrescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePrescriptionList.contains(null));
    }

    @Test
    public void contains_prescriptionNotInList_returnsFalse() {
        assertFalse(uniquePrescriptionList.contains(PRESCRIPTION_A));
    }

    @Test
    public void contains_prescriptionInList_returnsTrue() {
        uniquePrescriptionList.add(PRESCRIPTION_A);
        assertTrue(uniquePrescriptionList.contains(PRESCRIPTION_A));
    }


    @Test
    public void add_nullPrescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePrescriptionList.add(null));
    }

    @Test
    public void add_duplicatePrescription_throwsDuplicatePrescriptionException() {
        uniquePrescriptionList.add(PRESCRIPTION_A);
        assertThrows(DuplicatePrescriptionException.class, () -> uniquePrescriptionList.add(PRESCRIPTION_A));
    }

    @Test
    public void setPrescription_nullTargetPrescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePrescriptionList
                .setPrescription(null, PRESCRIPTION_A));
    }

    @Test
    public void setPrescription_nullEditedPrescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePrescriptionList
                .setPrescription(PRESCRIPTION_A, null));
    }

    @Test
    public void setPrescription_targetPrescriptionNotInList_throwsPrescriptionNotFoundException() {
        assertThrows(PrescriptionNotFoundException.class, () -> uniquePrescriptionList
                .setPrescription(PRESCRIPTION_A, PRESCRIPTION_A));
    }

    @Test
    public void setPrescription_editedPrescriptionIsSame_success() {
        uniquePrescriptionList.add(PRESCRIPTION_A);
        uniquePrescriptionList.setPrescription(PRESCRIPTION_A, PRESCRIPTION_A);
        UniquePrescriptionList expectedUniquePrescriptionList = new UniquePrescriptionList();
        expectedUniquePrescriptionList.add(PRESCRIPTION_A);
        assertEquals(expectedUniquePrescriptionList, uniquePrescriptionList);
    }

    @Test
    public void setPrescription_editedPrescriptionHasSameField_success() {
        uniquePrescriptionList.add(PRESCRIPTION_A);
        Prescription editedPrescription = new PrescriptionBuilder(PRESCRIPTION_A).withDrugName("Azithromycin")
                .withDate("2022-02-04")
                .build();
        uniquePrescriptionList.setPrescription(PRESCRIPTION_A, editedPrescription);
        UniquePrescriptionList expectedUniquePrescriptionList = new UniquePrescriptionList();
        expectedUniquePrescriptionList.add(editedPrescription);
        assertEquals(expectedUniquePrescriptionList, uniquePrescriptionList);
    }

    @Test
    public void setPrescription_editedPrescriptionHasDifferentIdentity_success() {
        uniquePrescriptionList.add(PRESCRIPTION_A);
        uniquePrescriptionList.setPrescription(PRESCRIPTION_A, PRESCRIPTION_B);
        UniquePrescriptionList expectedUniquePrescriptionList = new UniquePrescriptionList();
        expectedUniquePrescriptionList.add(PRESCRIPTION_B);
        assertEquals(expectedUniquePrescriptionList, uniquePrescriptionList);
    }

    @Test
    public void setPrescription_editedPrescriptionHasNonUniqueIdentity_throwsDuplicatePrescriptionException() {
        uniquePrescriptionList.add(PRESCRIPTION_A);
        uniquePrescriptionList.add(PRESCRIPTION_B);
        assertThrows(DuplicatePrescriptionException.class, () ->
                uniquePrescriptionList.setPrescription(PRESCRIPTION_A, PRESCRIPTION_B));
    }

    @Test
    public void remove_nullPrescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePrescriptionList.remove(null));
    }

    @Test
    public void remove_prescriptionDoesNotExist_throwsPrescriptionNotFoundException() {
        assertThrows(PrescriptionNotFoundException.class, () -> uniquePrescriptionList.remove(PRESCRIPTION_A));
    }

    @Test
    public void remove_existingPrescription_removesPrescription() {
        uniquePrescriptionList.add(PRESCRIPTION_A);
        uniquePrescriptionList.remove(PRESCRIPTION_A);
        UniquePrescriptionList expectedUniquePrescriptionList = new UniquePrescriptionList();
        assertEquals(expectedUniquePrescriptionList, uniquePrescriptionList);
    }

    @Test
    public void setPrescriptions_nullUniquePrescriptionList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePrescriptionList.setPrescriptions((UniquePrescriptionList) null));
    }

    @Test
    public void setPrescriptions_uniquePrescriptionList_replacesOwnListWithProvidedUniquePrescriptionList() {
        uniquePrescriptionList.add(PRESCRIPTION_A);
        UniquePrescriptionList expectedUniquePrescriptionList = new UniquePrescriptionList();
        expectedUniquePrescriptionList.add(PRESCRIPTION_B);
        uniquePrescriptionList.setPrescriptions(expectedUniquePrescriptionList);
        assertEquals(expectedUniquePrescriptionList, uniquePrescriptionList);
    }

    @Test
    public void setPrescriptions_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePrescriptionList.setPrescriptions((List<Prescription>) null));
    }

    @Test
    public void setPrescription_list_replacesOwnListWithProvidedList() {
        uniquePrescriptionList.add(PRESCRIPTION_A);
        List<Prescription> prescriptionList = Collections.singletonList(PRESCRIPTION_B);
        uniquePrescriptionList.setPrescriptions(prescriptionList);
        UniquePrescriptionList expectedUniquePrescriptionList = new UniquePrescriptionList();
        expectedUniquePrescriptionList.add(PRESCRIPTION_B);
        assertEquals(expectedUniquePrescriptionList, uniquePrescriptionList);
    }

    @Test
    public void setPrescription_listWithDuplicatePrescriptions_throwsDuplicatePrescriptionException() {
        List<Prescription> listWithDuplicatePrescriptions = Arrays.asList(PRESCRIPTION_A, PRESCRIPTION_A);
        assertThrows(DuplicatePrescriptionException.class, () ->
                uniquePrescriptionList.setPrescriptions(listWithDuplicatePrescriptions));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniquePrescriptionList
                .asUnmodifiableObservableList().remove(0));
    }
}
