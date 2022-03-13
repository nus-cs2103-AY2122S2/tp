package seedu.address.model.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_NAME_SR_FE_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_NAME_SR_FE_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_OPENINGS_SR_FE_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_EXPERIENCE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPositions.JR_SOFTWARE_ENGINEER;
import static seedu.address.testutil.TypicalPositions.SR_FE_SOFTWARE_ENGINEER;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PositionBuilder;

public class PositionTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Position position = new PositionBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> position.getRequirements().remove(0));
    }

    @Test
    public void isSamePosition() {
        // same object -> true
        assertTrue(JR_SOFTWARE_ENGINEER.isSamePosition(JR_SOFTWARE_ENGINEER));

        // null -> false
        assertFalse(JR_SOFTWARE_ENGINEER.isSamePosition(null));

        // same position name, all other attributes different -> returns true
        assertTrue(JR_SOFTWARE_ENGINEER.isSamePosition(new PositionBuilder(JR_SOFTWARE_ENGINEER)
                .withDescription(VALID_DESCRIPTION_NAME_SR_FE_SWE)
                .build()));

        // different name, all other attributes same -> returns false
        Position editedJrSwe = new PositionBuilder(JR_SOFTWARE_ENGINEER).withPositionName(VALID_POSITION_NAME_SR_FE_SWE)
                .build();
        assertFalse(JR_SOFTWARE_ENGINEER.isSamePosition(editedJrSwe));

        // name differs in case, all attributes same -> returns False
        Position editedJrSwe2 = new PositionBuilder(JR_SOFTWARE_ENGINEER)
                .withPositionName(VALID_POSITION_NAME_SR_FE_SWE.toLowerCase())
                .build();
        assertFalse(JR_SOFTWARE_ENGINEER.isSamePosition(editedJrSwe2));

        // name has trailing space, all other attributes same -> returns false
        Position editedJrSwe3 = new PositionBuilder(JR_SOFTWARE_ENGINEER)
                .withPositionName(VALID_POSITION_NAME_SR_FE_SWE + " ")
                .build();
        assertFalse(JR_SOFTWARE_ENGINEER.isSamePosition(editedJrSwe3));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Position jrSweCopy = new PositionBuilder(JR_SOFTWARE_ENGINEER).build();
        assertTrue(JR_SOFTWARE_ENGINEER.equals(jrSweCopy));

        // same object -> returns true
        assertTrue(JR_SOFTWARE_ENGINEER.equals(JR_SOFTWARE_ENGINEER));

        // null -> returns false
        assertFalse(JR_SOFTWARE_ENGINEER.equals(null));

        // different type -> returns false
        assertFalse(JR_SOFTWARE_ENGINEER.equals(ALICE));

        // different applicant -> returns false
        assertFalse(JR_SOFTWARE_ENGINEER.equals(SR_FE_SOFTWARE_ENGINEER));

        // different position name -> returns false
        Position editedJrSwe = new PositionBuilder(JR_SOFTWARE_ENGINEER).withPositionName(VALID_POSITION_NAME_SR_FE_SWE)
                .build();
        assertFalse(JR_SOFTWARE_ENGINEER.equals(editedJrSwe));

        // different number of openings -> returns false
        editedJrSwe = new PositionBuilder(JR_SOFTWARE_ENGINEER).withPositionOpenings(VALID_POSITION_OPENINGS_SR_FE_SWE)
                .build();
        assertFalse(JR_SOFTWARE_ENGINEER.equals(editedJrSwe));

        // different description -> returns false
        editedJrSwe = new PositionBuilder(JR_SOFTWARE_ENGINEER).withDescription(VALID_DESCRIPTION_NAME_SR_FE_SWE)
                .build();
        assertFalse(JR_SOFTWARE_ENGINEER.equals(editedJrSwe));

        // different requirements -> returns false
        editedJrSwe = new PositionBuilder(JR_SOFTWARE_ENGINEER).withRequirements(VALID_REQUIREMENT_EXPERIENCE)
                .build();
        assertFalse(JR_SOFTWARE_ENGINEER.equals(editedJrSwe));
    }
}
