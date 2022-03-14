package seedu.contax.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.contax.model.onboarding.OnboardingStep;
import seedu.contax.model.onboarding.OnboardingStory;

public class OnboardingStepTest {

    @Test
    public void equals() {
        OnboardingStep step1 = new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStory.OverlayOption.SHOW_COMMAND_BOX,
                OnboardingStory.PositionOption.RESULT_DISPLAY_TOP,
                OnboardingStory.HighlightOption.COMMAND_BOX,
                0, "null", null, null, false);

        OnboardingStep step2 = new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStory.OverlayOption.SHOW_COMMAND_BOX,
                OnboardingStory.PositionOption.RESULT_DISPLAY_TOP,
                OnboardingStory.HighlightOption.COMMAND_BOX,
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
                0.2, 0.5, OnboardingStory.OverlayOption.SHOW_COMMAND_BOX,
                OnboardingStory.PositionOption.RESULT_DISPLAY_TOP,
                OnboardingStory.HighlightOption.COMMAND_BOX,
                0, "null", null, null, false)));
        assertFalse(step1.equals(new OnboardingStep("message 1",
                0.3, 0.5, OnboardingStory.OverlayOption.SHOW_COMMAND_BOX,
                OnboardingStory.PositionOption.RESULT_DISPLAY_TOP,
                OnboardingStory.HighlightOption.COMMAND_BOX,
                0, "null", null, null, false)));
        assertFalse(step1.equals(new OnboardingStep("message 1",
                0.2, 0.45, OnboardingStory.OverlayOption.SHOW_COMMAND_BOX,
                OnboardingStory.PositionOption.RESULT_DISPLAY_TOP,
                OnboardingStory.HighlightOption.COMMAND_BOX,
                0, "null", null, null, false)));
        assertFalse(step1.equals(new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStory.OverlayOption.ALL,
                OnboardingStory.PositionOption.RESULT_DISPLAY_TOP,
                OnboardingStory.HighlightOption.COMMAND_BOX,
                0, "null", null, null, false)));
        assertFalse(step1.equals(new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStory.OverlayOption.SHOW_COMMAND_BOX,
                OnboardingStory.PositionOption.CENTER, OnboardingStory.HighlightOption.COMMAND_BOX,
                0, "null", null, null, false)));
        assertFalse(step1.equals(new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStory.OverlayOption.SHOW_COMMAND_BOX,
                OnboardingStory.PositionOption.RESULT_DISPLAY_TOP,
                OnboardingStory.HighlightOption.CLEAR_ALL,
                0, "null", null, null, false)));
        assertFalse(step1.equals(new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStory.OverlayOption.SHOW_COMMAND_BOX,
                OnboardingStory.PositionOption.RESULT_DISPLAY_TOP,
                OnboardingStory.HighlightOption.COMMAND_BOX,
                1, "null", null, null, false)));
        assertFalse(step1.equals(new OnboardingStep("message 2",
                0.2, 0.5, OnboardingStory.OverlayOption.SHOW_COMMAND_BOX,
                OnboardingStory.PositionOption.RESULT_DISPLAY_TOP,
                OnboardingStory.HighlightOption.COMMAND_BOX,
                0, "null11", null, null, false)));
        assertFalse(step1.equals(new OnboardingStep("message 2",
                0.2, 0.5, OnboardingStory.OverlayOption.SHOW_COMMAND_BOX,
                OnboardingStory.PositionOption.RESULT_DISPLAY_TOP,
                OnboardingStory.HighlightOption.COMMAND_BOX,
                0, "null", (o, a) -> "1", null, false)));
        assertFalse(step1.equals(new OnboardingStep("message 2",
                0.2, 0.5, OnboardingStory.OverlayOption.SHOW_COMMAND_BOX,
                OnboardingStory.PositionOption.RESULT_DISPLAY_TOP,
                OnboardingStory.HighlightOption.COMMAND_BOX,
                0, "null", null, (a) -> "2", false)));
        assertFalse(step1.equals(new OnboardingStep("message 2",
                0.2, 0.5, OnboardingStory.OverlayOption.SHOW_COMMAND_BOX,
                OnboardingStory.PositionOption.RESULT_DISPLAY_TOP,
                OnboardingStory.HighlightOption.COMMAND_BOX,
                0, "null", null, null, true)));
    }

    @Test
    public void setEventType() {
        OnboardingStep step1 = new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStory.OverlayOption.SHOW_COMMAND_BOX,
                OnboardingStory.PositionOption.RESULT_DISPLAY_TOP,
                OnboardingStory.HighlightOption.COMMAND_BOX,
                0, "null", null, null, false);

        step1.setEventType(OnboardingStory.PositionOption.CENTER);

        // Value is set after setting -> returns true
        assertEquals(step1.getPositionOption(), OnboardingStory.PositionOption.CENTER);
    }

    @Test
    public void setDisplayMessage() {
        OnboardingStep step1 = new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStory.OverlayOption.SHOW_COMMAND_BOX,
                OnboardingStory.PositionOption.RESULT_DISPLAY_TOP,
                OnboardingStory.HighlightOption.COMMAND_BOX,
                0, "null", null, null, false);
        step1.setDisplayMessage("54321");

        // Value is set after setting -> returns true
        assertEquals(step1.getDisplayMessage(), "54321");
    }

    @Test
    public void setCommand() {
        OnboardingStep step1 = new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStory.OverlayOption.SHOW_COMMAND_BOX,
                OnboardingStory.PositionOption.RESULT_DISPLAY_TOP,
                OnboardingStory.HighlightOption.COMMAND_BOX,
                0, "null", null, null, false);
        step1.setCommand("54321");

        // Value is set after setting -> returns true
        assertEquals(step1.getCommand(), "54321");
    }

    @Test void hashCodeTest() {
        OnboardingStep step1 = new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStory.OverlayOption.SHOW_COMMAND_BOX,
                OnboardingStory.PositionOption.RESULT_DISPLAY_TOP,
                OnboardingStory.HighlightOption.COMMAND_BOX,
                0, "null", null, null, false);
        OnboardingStep step2 = new OnboardingStep("message 1",
                0.2, 0.5, OnboardingStory.OverlayOption.SHOW_COMMAND_BOX,
                OnboardingStory.PositionOption.RESULT_DISPLAY_TOP,
                OnboardingStory.HighlightOption.COMMAND_BOX,
                0, "null", null, null, false);
        OnboardingStep step3 = new OnboardingStep("message 123",
                0.2, 0.5, OnboardingStory.OverlayOption.SHOW_COMMAND_BOX,
                OnboardingStory.PositionOption.RESULT_DISPLAY_TOP,
                OnboardingStory.HighlightOption.COMMAND_BOX,
                0, "null", null, null, false);

        // same object -> returns true
        assertEquals(step1.hashCode(), step1.hashCode());

        // different object, same parameters -> returns true
        assertEquals(step1.hashCode(), step2.hashCode());

        // different object, different parameters -> returns false
        assertNotEquals(step3.hashCode(), step2.hashCode());

    }
}
