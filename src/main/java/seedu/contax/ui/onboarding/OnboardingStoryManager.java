package seedu.contax.ui.onboarding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.contax.model.onboarding.OnboardingStep;
import seedu.contax.model.onboarding.OnboardingStory;
import seedu.contax.model.person.Address;
import seedu.contax.model.person.Email;
import seedu.contax.model.person.Name;
import seedu.contax.model.person.Person;
import seedu.contax.model.person.Phone;
import seedu.contax.model.tag.Tag;



public class OnboardingStoryManager {
    private static final String CLICK_CONTINUE = "\n\nClick any where to continue...";
    private static final String CLICK_EXIT = "\n\nClick any where to exit Quick Tour...";

    private OnboardingStory story;
    private OnboardingStep previousStep;
    private int currStep = 0;
    private ArrayList<Person> personList;

    /**
     * Constructs a manager to manage Onboarding Guide stories
     */
    public OnboardingStoryManager() {
        personList = new ArrayList<>();
        createPersons();
        createStories();
    }

    private void createStories() {
        OnboardingStory test = new OnboardingStory();
        test.addStory(new OnboardingStep("Welcome to a quick tour of ContaX" + CLICK_CONTINUE,
                0, 0.2, 0.5, 0, 0, 0, null, null, -1));
        test.addStory(new OnboardingStep(
                "You will now be guided through\nthe basic features of ContaX" + CLICK_CONTINUE,
                0, 0.2, 0.5, 0, 0, 0, null, null, -1));
        test.addStory(new OnboardingStep("This is the command box.\nYour commands will go here" + CLICK_CONTINUE,
                2, 0.2, 0.5, 3, 1, 0, null, null, -1));
        test.addStory(new OnboardingStep("Now lets try adding a person." + CLICK_CONTINUE,
                0, 0.2, 0.5, 0, 0, 0, null, null, -1));
        test.addStory(new OnboardingStep(
                "Type 'add n/Johnny p/91234567 e/Johnny@j.com a/Johnny street'\n\n Hit enter when you are done",
                2, 0.2, 0.5, 3, 1, 1,
                "add n/Johnny p/91234567 e/Johnny@j.com a/Johnny street", null, -1));
        test.addStory(new OnboardingStep(
                "Great! Johnny is now added into the system!" + CLICK_CONTINUE,
                0, 0.2, 0.5, 0, 0, 0, null, null, -1));
        test.addStory(new OnboardingStep("Lets try to see Johnny's record!" + CLICK_CONTINUE,
                0, 0.2, 0.5, 0, 0, 0, null, null, -1));
        test.addStory(new OnboardingStep("Type 'list' and hit enter",
                2, 0.1, 0.5, 3, 1, 1, "list", null, -1));
        test.addStory(new OnboardingStep("Great! Here is Johnny's record.",
                3, 0.2, 0.5, 4, 2, 0, null, personList.get(0), 1));
        test.addStory(new OnboardingStep("End of Quick Tour!" + CLICK_EXIT,
                0, 0.2, 0.5, 0, 0, 0, null, null, -1));
        test.addStory(new OnboardingStep("End of Quick Tour!" + CLICK_EXIT,
                0, 0.1, 0.5, 0, 0, 0, null, null, 0));
        story = test;
    }

    /**
     * Populates peronList with persons.
     * These person instances are for use in the Onboarding Guide
     */
    private void createPersons() {
        Set<Tag> tags = new HashSet<>();
        Person p = new Person(new Name("Johnny"), new Phone("91234567"),
                new Email("johnny@jj.com"), new Address("Johnny Street"), tags);
        personList.add(p);
    }

    /**
     * Returns the OnboardingStep object at the first index of story
     * @return The first OnboardingStep of story
     */
    public OnboardingStep start() {
        previousStep = story.getStep(currStep);
        return story.getStep(currStep);
    }

    /**
     * Handler for mouse click event.
     * @return Returns the OnboardingStep object at the current step
     */
    public OnboardingStep handleMouseClick() {
        if (currStep > story.getSize() - 1) {
            return null;
        }
        if (previousStep.getEventType() == 0) {
            previousStep = story.getStep(currStep);
            return story.getStep(currStep);
        }
        return null;
    }

    /**
     * Handler for Enter keypress event
     * @return Returns the OnboardingStep object at the current step
     */
    public OnboardingStep handleEnter() {
        if (currStep > story.getSize() - 1) {
            return null;
        }
        if (previousStep.getEventType() == 1) {
            previousStep = story.getStep(currStep);
            return story.getStep(currStep);
        }
        return null;
    }

    public void stepFront() {
        currStep += 1;
    }

    /**
     * Get the OnboardingStep at the next index
     * @return Returns the next OnboardingStep object
     */
    public OnboardingStep getNextStep() {
        previousStep = story.getStep(currStep + 1);
        return story.getStep(currStep + 1);
    }
}
