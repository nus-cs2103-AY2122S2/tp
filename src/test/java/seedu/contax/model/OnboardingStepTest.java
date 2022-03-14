package seedu.contax.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.TypicalPersons.ALICE;
import static seedu.contax.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.contax.model.onboarding.OnboardingStep;
import seedu.contax.model.person.Person;
import seedu.contax.ui.onboarding.OnboardingStoryManager;
import seedu.contax.ui.onboarding.OnboardingUtil;
import seedu.contax.model.Model;
import seedu.contax.ui.onboarding.OnboardingCommandBox;

public class OnboardingStepTest {

    @Test
    public void equals() {
        OnboardingStep step1 = new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStoryManager.OverlayOption.ShowCommandBox, OnboardingStoryManager.PositionOption.ResultDisplayTop, OnboardingStoryManager.HighlightOption.CommandBox,
                0, "null", null, null, false);

        OnboardingStep step2 = new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStoryManager.OverlayOption.ShowCommandBox, OnboardingStoryManager.PositionOption.ResultDisplayTop, OnboardingStoryManager.HighlightOption.CommandBox,
                0, "null", null, null, false);

        // same object -> returns true
        assertTrue(step1.equals(step1));

        // all values same -> returns true
        assertTrue(step1.equals(step2));

        // different type -> returns false
        assertFalse(step1.equals(5));

        // null -> returns false
        assertFalse(step1.equals(null));

        // attribute difference checks
        assertFalse(step1.equals(new OnboardingStep("message 2",
                0.2, 0.5, OnboardingStoryManager.OverlayOption.ShowCommandBox, OnboardingStoryManager.PositionOption.ResultDisplayTop, OnboardingStoryManager.HighlightOption.CommandBox,
                0, "null", null, null, false)));
        assertFalse(step1.equals(new OnboardingStep("message 1",
                0.3, 0.5, OnboardingStoryManager.OverlayOption.ShowCommandBox, OnboardingStoryManager.PositionOption.ResultDisplayTop, OnboardingStoryManager.HighlightOption.CommandBox,
                0, "null", null, null, false)));
        assertFalse(step1.equals(new OnboardingStep("message 1",
                0.2, 0.45, OnboardingStoryManager.OverlayOption.ShowCommandBox, OnboardingStoryManager.PositionOption.ResultDisplayTop, OnboardingStoryManager.HighlightOption.CommandBox,
                0, "null", null, null, false)));
        assertFalse(step1.equals(new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStoryManager.OverlayOption.All, OnboardingStoryManager.PositionOption.ResultDisplayTop, OnboardingStoryManager.HighlightOption.CommandBox,
                0, "null", null, null, false)));
        assertFalse(step1.equals(new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStoryManager.OverlayOption.ShowCommandBox, OnboardingStoryManager.PositionOption.Center, OnboardingStoryManager.HighlightOption.CommandBox,
                0, "null", null, null, false)));
        assertFalse(step1.equals(new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStoryManager.OverlayOption.ShowCommandBox, OnboardingStoryManager.PositionOption.ResultDisplayTop, OnboardingStoryManager.HighlightOption.ClearAll,
                0, "null", null, null, false)));
        assertFalse(step1.equals(new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStoryManager.OverlayOption.ShowCommandBox, OnboardingStoryManager.PositionOption.ResultDisplayTop, OnboardingStoryManager.HighlightOption.CommandBox,
                1, "null", null, null, false)));
        assertFalse(step1.equals(new OnboardingStep("message 2",
                0.2, 0.5, OnboardingStoryManager.OverlayOption.ShowCommandBox, OnboardingStoryManager.PositionOption.ResultDisplayTop, OnboardingStoryManager.HighlightOption.CommandBox,
                0, "null11", null, null, false)));
        assertFalse(step1.equals(new OnboardingStep("message 2",
                0.2, 0.5, OnboardingStoryManager.OverlayOption.ShowCommandBox, OnboardingStoryManager.PositionOption.ResultDisplayTop, OnboardingStoryManager.HighlightOption.CommandBox,
                0, "null", (o, a) -> "1", null, false)));
        assertFalse(step1.equals(new OnboardingStep("message 2",
                0.2, 0.5, OnboardingStoryManager.OverlayOption.ShowCommandBox, OnboardingStoryManager.PositionOption.ResultDisplayTop, OnboardingStoryManager.HighlightOption.CommandBox,
                0, "null", null, (a) -> "2", false)));
        assertFalse(step1.equals(new OnboardingStep("message 2",
                0.2, 0.5, OnboardingStoryManager.OverlayOption.ShowCommandBox, OnboardingStoryManager.PositionOption.ResultDisplayTop, OnboardingStoryManager.HighlightOption.CommandBox,
                0, "null", null, null, true)));
    }

    @Test
    public void setEventType() {
        OnboardingStep step1 = new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStoryManager.OverlayOption.ShowCommandBox, OnboardingStoryManager.PositionOption.ResultDisplayTop, OnboardingStoryManager.HighlightOption.CommandBox,
                0, "null", null, null, false);

        step1.setEventType(OnboardingStoryManager.PositionOption.Center);

        // Value is set after setting -> returns true
        assertEquals(step1.getPositionOption(), OnboardingStoryManager.PositionOption.Center);
    }

    @Test
    public void setDisplayMessage() {
        OnboardingStep step1 = new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStoryManager.OverlayOption.ShowCommandBox, OnboardingStoryManager.PositionOption.ResultDisplayTop, OnboardingStoryManager.HighlightOption.CommandBox,
                0, "null", null, null, false);
        step1.setDisplayMessage("54321");

        // Value is set after setting -> returns true
        assertEquals(step1.getDisplayMessage(), "54321");
    }

    @Test
    public void setCommand() {
        OnboardingStep step1 = new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStoryManager.OverlayOption.ShowCommandBox, OnboardingStoryManager.PositionOption.ResultDisplayTop, OnboardingStoryManager.HighlightOption.CommandBox,
                0, "null", null, null, false);
        step1.setCommand("54321");

        // Value is set after setting -> returns true
        assertEquals(step1.getCommand(), "54321");
    }

    @Test void hashCodeTest() {
        OnboardingStep step1 = new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStoryManager.OverlayOption.ShowCommandBox, OnboardingStoryManager.PositionOption.ResultDisplayTop, OnboardingStoryManager.HighlightOption.CommandBox,
                0, "null", null, null, false);
        OnboardingStep step2 = new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStoryManager.OverlayOption.ShowCommandBox, OnboardingStoryManager.PositionOption.ResultDisplayTop, OnboardingStoryManager.HighlightOption.CommandBox,
                0, "null", null, null, false);
        OnboardingStep step3 = new OnboardingStep("message 123",
                0.2, 0.5, OnboardingStoryManager.OverlayOption.ShowCommandBox, OnboardingStoryManager.PositionOption.ResultDisplayTop, OnboardingStoryManager.HighlightOption.CommandBox,
                0, "null", null, null, false);

        // same object -> returns true
        assertEquals(step1.hashCode(), step1.hashCode());

        // different object, same parameters -> returns true
        assertEquals(step1.hashCode(), step2.hashCode());

        // different object, different parameters -> returns false
        assertNotEquals(step3.hashCode(), step2.hashCode());

    }
}
