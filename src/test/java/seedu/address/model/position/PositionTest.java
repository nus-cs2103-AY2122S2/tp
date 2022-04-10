package seedu.address.model.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_NAME_SR_FE_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_NAME_SR_FE_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_OPENINGS_SR_FE_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_EXPERIENCE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicants.ALICE;
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

        // different offers count -> returns false
        Position editedJrSw4 = new PositionBuilder(JR_SOFTWARE_ENGINEER).build();
        editedJrSw4 = editedJrSw4.extendOffer();
        assertFalse(JR_SOFTWARE_ENGINEER.equals(editedJrSw4));
    }

    @Test
    public void isValidOpeningsToPosition() {
        Position jrSweCopy = new PositionBuilder(JR_SOFTWARE_ENGINEER).withPositionOpenings("2").build();

        // no offers yet - valid
        assertTrue(jrSweCopy.isValidOpeningsToOffers());

        // extend first offer - 1 offer to 2 openings - valid
        Position jrSweWithOffer = jrSweCopy.extendOffer();
        assertTrue(jrSweWithOffer.isValidOpeningsToOffers());

        // should have 1 offer
        assertTrue(jrSweWithOffer.getPositionOffers().getCount().equals(1));

        // extend second offer - 2 offers to 2 openings - valid
        Position jrSweWithTwoOffers = jrSweWithOffer.extendOffer();
        assertTrue(jrSweWithTwoOffers.isValidOpeningsToOffers());

        // should have 2 offers
        assertTrue(jrSweWithTwoOffers.getPositionOffers().getCount().equals(2));

        // extend third offer - invalid
        Position invalidSwe = new Position(jrSweWithTwoOffers.getPositionName(), jrSweWithTwoOffers.getDescription(),
                jrSweWithTwoOffers.getPositionOpenings(), jrSweWithTwoOffers.getPositionOffers().increment(),
                jrSweWithTwoOffers.getRequirements());
        assertFalse(invalidSwe.isValidOpeningsToOffers());
    }

    @Test
    public void canExtendOffer() {
        Position jrSweCopy = new PositionBuilder(JR_SOFTWARE_ENGINEER).withPositionOpenings("2").build();

        // no offers yet, can extend offer
        assertTrue(jrSweCopy.canExtendOffer());

        // after offering 1, still can extend offer
        Position jrSweWithOffer = jrSweCopy.extendOffer();
        assertTrue(jrSweWithOffer.canExtendOffer());

        // after offering 2nd, cannot extend offer anymore
        Position jrSweWithTwoOffers = jrSweWithOffer.extendOffer();
        assertFalse(jrSweWithTwoOffers.canExtendOffer());
    }

    @Test
    public void canAcceptOffer() {
        Position jrSweCopy = new PositionBuilder(JR_SOFTWARE_ENGINEER).withPositionOpenings("1").build();

        // no offers, cannot accept offer
        assertFalse(jrSweCopy.canAcceptOffer());

        // after 1 offer, can accept
        Position jrSweWithOffer = jrSweCopy.extendOffer();
        assertTrue(jrSweWithOffer.canAcceptOffer());

        // after accepting 1st offer, cannot accept
        jrSweCopy = jrSweWithOffer.acceptOffer();
        assertFalse(jrSweCopy.canAcceptOffer());
    }
}
