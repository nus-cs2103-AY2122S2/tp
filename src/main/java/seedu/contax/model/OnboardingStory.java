package seedu.contax.model;

import java.util.ArrayList;

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
}
