package seedu.contax.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.contax.model.onboarding.OnboardingStep;
import seedu.contax.model.onboarding.OnboardingStory;

public class OnboardingStoryTest {

    @Test
    public void equals() {
        OnboardingStory story1 = new OnboardingStory();
        OnboardingStory story2 = new OnboardingStory();

        // both empty stories -> returns true
        assertTrue(story1.equals(story2));

        OnboardingStep step1 = new OnboardingStep("message 1", 0, 0, 0,
                0, 0, 0, "", null, 1, false);
        OnboardingStep step2 = new OnboardingStep("message 2", 0, 0, 0,
                0, 0, 0, "", null, 1, false);



        // different number of steps -> returns false
        story1.addStory(step1);
        assertFalse(story1.equals(story2));

        // same size, same stories -> returns ture
        story2.addStory(step1);
        assertTrue(story1.equals(story2));

        // same size, different stories -> returns false
        story1.addStory(step1);
        story2.addStory(step2);
        assertFalse(story1.equals(story2));

        // different type -> returns false
        assertTrue(story1.equals(story1));

        // different type -> returns false
        assertFalse(story1.equals(5));

        // null -> returns false
        assertFalse(story1.equals(null));
    }

    @Test
    public void addStory() {
        OnboardingStory story1 = new OnboardingStory();
        OnboardingStep step1 = new OnboardingStep("message 1", 0, 0, 0,
                0, 0, 0, "", null, 1, false);
        story1.addStory(step1);

        // Size of story with 1 step
        assertEquals(story1.getSize(), 1);

        assertEquals(story1.getStep(0), step1);
    }

    @Test void hashCodeTest() {
        OnboardingStory story1 = new OnboardingStory();
        OnboardingStory story2 = new OnboardingStory();

        // same object -> returns true
        assertEquals(story1.hashCode(), story1.hashCode());

        // different objects -> returns false
        assertEquals(story1.hashCode(), story2.hashCode());
    }
}
