package seedu.contax.model.onboarding;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents an onboarding guide story, which is made up of a sequence of OnboardingSteps.
 */
public class OnboardingStory {

    public enum HighlightOption {
        CLEAR_ALL,
        COMMAND_BOX,
        PERSON_LIST
    }

    public enum PositionOption {
        CENTER,
        MENU_BAR_TOP,
        COMMAND_BOX_TOP,
        RESULT_DISPLAY_TOP,
        PERSON_LIST_MIDDLE
    }

    public enum OverlayOption {
        ALL,
        SHOW_MENU_BAR,
        SHOW_COMMAND_BOX,
        SHOW_PERSON_LIST
    }

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
