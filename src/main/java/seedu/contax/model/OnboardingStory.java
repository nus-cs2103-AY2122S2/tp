package seedu.contax.model;

import java.util.ArrayList;
import java.util.Objects;

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

    public int getSize() {
        return story.size();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof OnboardingStep)) {
            return false;
        }

        OnboardingStory otherStory = (OnboardingStory) other;
        if (otherStory.getSize() != getSize()) {
            return false;
        }

        for(int i = 0; i < getSize(); i++) {
            if (otherStory.getStep(i) != getStep(i)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(story);
    }
}
