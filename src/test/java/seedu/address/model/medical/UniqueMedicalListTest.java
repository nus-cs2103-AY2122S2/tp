package seedu.address.model.medical;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMedicals.MEDICAL_A;
import static seedu.address.testutil.TypicalMedicals.MEDICAL_B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.medical.exceptions.DuplicateMedicalException;
import seedu.address.model.medical.exceptions.MedicalNotFoundException;

public class UniqueMedicalListTest {

    private final UniqueMedicalList uniqueMedicalList = new UniqueMedicalList();

    @Test
    public void contains_nullMedical_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMedicalList.contains(null));
    }

    @Test
    public void contains_medicalNotInList_returnsFalse() {
        assertFalse(uniqueMedicalList.contains(MEDICAL_A));
    }

    @Test
    public void contains_medicalInList_returnsTrue() {
        uniqueMedicalList.add(MEDICAL_A);
        assertTrue(uniqueMedicalList.contains(MEDICAL_A));
    }


    @Test
    public void add_nullMedical_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMedicalList.add(null));
    }

    @Test
    public void add_duplicateMedical_throwsDuplicateMedicalException() {
        uniqueMedicalList.add(MEDICAL_A);
        assertThrows(DuplicateMedicalException.class, () -> uniqueMedicalList.add(MEDICAL_A));
    }

    @Test
    public void setMedical_nullTargetMedical_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMedicalList
                .setMedical(null, MEDICAL_A));
    }

    @Test
    public void setMedical_nullEditedMedical_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMedicalList
                .setMedical(MEDICAL_A, null));
    }

    @Test
    public void setMedical_targetMedicalNotInList_throwsMedicalNotFoundException() {
        assertThrows(MedicalNotFoundException.class, () -> uniqueMedicalList
                .setMedical(MEDICAL_A, MEDICAL_A));
    }

    @Test
    public void setMedical_editedMedicalIsSame_success() {
        uniqueMedicalList.add(MEDICAL_A);
        uniqueMedicalList.setMedical(MEDICAL_A, MEDICAL_A);
        UniqueMedicalList expectedUniqueMedicalList = new UniqueMedicalList();
        expectedUniqueMedicalList.add(MEDICAL_A);
        assertEquals(expectedUniqueMedicalList, uniqueMedicalList);
    }

    @Test
    public void setMedical_editedMedicalHasDifferentIdentity_success() {
        uniqueMedicalList.add(MEDICAL_A);
        uniqueMedicalList.setMedical(MEDICAL_A, MEDICAL_B);
        UniqueMedicalList expectedUniqueMedicalList = new UniqueMedicalList();
        expectedUniqueMedicalList.add(MEDICAL_B);
        assertEquals(expectedUniqueMedicalList, uniqueMedicalList);
    }

    @Test
    public void setMedical_editedMedicalHasNonUniqueIdentity_throwsDuplicateMedicalException() {
        uniqueMedicalList.add(MEDICAL_A);
        uniqueMedicalList.add(MEDICAL_B);
        assertThrows(DuplicateMedicalException.class, () ->
                uniqueMedicalList.setMedical(MEDICAL_A, MEDICAL_B));
    }

    @Test
    public void remove_nullMedical_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMedicalList.remove(null));
    }

    @Test
    public void remove_medicalDoesNotExist_throwsMedicalNotFoundException() {
        assertThrows(MedicalNotFoundException.class, () -> uniqueMedicalList.remove(MEDICAL_A));
    }

    @Test
    public void remove_existingMedical_removesMedical() {
        uniqueMedicalList.add(MEDICAL_A);
        uniqueMedicalList.remove(MEDICAL_A);
        UniqueMedicalList expectedUniqueMedicalList = new UniqueMedicalList();
        assertEquals(expectedUniqueMedicalList, uniqueMedicalList);
    }

    @Test
    public void setMedicals_nullUniqueMedicalList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueMedicalList.setMedicals(null));
    }

    @Test
    public void setMedicals_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueMedicalList.setMedicals((List<Medical>) null));
    }

    @Test
    public void setMedical_list_replacesOwnListWithProvidedList() {
        uniqueMedicalList.add(MEDICAL_A);
        List<Medical> medicalList = Collections.singletonList(MEDICAL_B);
        uniqueMedicalList.setMedicals(medicalList);
        UniqueMedicalList expectedUniqueMedicalList = new UniqueMedicalList();
        expectedUniqueMedicalList.add(MEDICAL_B);
        assertEquals(expectedUniqueMedicalList, uniqueMedicalList);
    }

    @Test
    public void setMedical_listWithDuplicateMedicals_throwsDuplicateMedicalException() {
        List<Medical> listWithDuplicateMedicals = Arrays.asList(MEDICAL_A, MEDICAL_A);
        assertThrows(DuplicateMedicalException.class, () ->
                uniqueMedicalList.setMedicals(listWithDuplicateMedicals));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueMedicalList
                .asUnmodifiableObservableList().remove(0));
    }
}
