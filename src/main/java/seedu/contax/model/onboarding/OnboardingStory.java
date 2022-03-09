package seedu.contax.model.onboarding;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents an onboarding guide story, which is made up of a sequence of OnboardingSteps
 */
public class OnboardingStory {
    private ArrayList<OnboardingStep> story;

    public OnboardingStory() {
        story = new ArrayList<>();
    }

    public void addStory(OnboardingStep os) {
        story.add(os);
    }

    public OnboardingStep getStep(int index) {
        return story.get(index);
    }

    public ArrayList<OnboardingStep> getStory() {
        return story;
    }

    public int getSize() {
        return story.size();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof OnboardingStory)) {
            return false;
        }
        OnboardingStory otherStory = (OnboardingStory) other;
        return story.equals(otherStory.getStory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(story);
    }
}
