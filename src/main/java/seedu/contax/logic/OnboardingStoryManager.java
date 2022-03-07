package seedu.contax.logic;

import seedu.contax.model.OnboardingStep;
import seedu.contax.model.OnboardingStory;
import seedu.contax.model.person.*;
import seedu.contax.model.tag.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class OnboardingStoryManager {
    private final String CLICK_TO_CONTINUE = "\n\nClick any where to continue...";
    private final String CLICK_TO_EXIT = "\n\nClick any where to exit Quick Tour...";
    private final String ENTER_TO_CONTINUE = "\n\nPress Enter to continue";

    private OnboardingStory s;
    private OnboardingStep previousStep;
    private int currStep = 0;
    private ArrayList<Person> personList;

    public OnboardingStoryManager() {
        personList = new ArrayList<>();
        createPersons();
        createStories();
    }

    private void createStories() {
        OnboardingStory test = new OnboardingStory();
        test.addStory(new OnboardingStep("Welcome to a quick tour of ContaX" + CLICK_TO_CONTINUE,
                0, 0.2, 0.5, 0, 0,0, null, null, 0));
        test.addStory(new OnboardingStep("You will now be guided through\nthe basic features of ContaX" + CLICK_TO_CONTINUE,
                0, 0.2, 0.5, 0, 0,0,null, null, 0));
        test.addStory(new OnboardingStep("This is the command box.\nYour commands will go here" + CLICK_TO_CONTINUE,
                2, 0.2, 0.5, 3, 1,0, null,null, 0));
        test.addStory(new OnboardingStep("Now lets try adding a person." + CLICK_TO_CONTINUE,
                0, 0.2, 0.5, 0, 0,0, null,null, 0));
        test.addStory(new OnboardingStep("Type 'add n/Johnny p/91234567 e/Johnny@j.com a/Johnny street'\n\n Hit enter when you are done",
                2, 0.2, 0.5, 3, 1,1, "add n/Johnny p/91234567 e/Johnny@j.com a/Johnny street", null, 0));
        test.addStory(new OnboardingStep("Great! Johnny is now added into the system!" + CLICK_TO_CONTINUE,
                0, 0.2, 0.5, 0, 0,0, null,null, 0));
        test.addStory(new OnboardingStep("Lets try to see Johnny's record!" + CLICK_TO_CONTINUE,
                0, 0.2, 0.5, 0, 0,0, null,null, 0));
        test.addStory(new OnboardingStep("Type 'list' and hit enter",
                2, 0.1, 0.5, 3, 1,1, "list",null, 0));
        test.addStory(new OnboardingStep("Great!",
                3, 0.2, 0.5, 1, 2,0, null,personList.get(0), 1));
        test.addStory(new OnboardingStep("End of Quick Tour!" + CLICK_TO_EXIT,
                0, 0.2, 0.5, 0, 0,0, null,null, 0));
        test.addStory(new OnboardingStep("End of Quick Tour!" + CLICK_TO_EXIT,
                0, 0.2, 0.5, 0, 0,0, null,null, 2));
        s = test;
    }

    private void createPersons() {
        Set<Tag> tags = new HashSet<>();
        Person p = new Person(new Name("Johnny"), new Phone("91234567"), new Email("johnny@jj.com"), new Address("Johnny Street"), tags);
        personList.add(p);
    }

    public OnboardingStep start() {
        previousStep = s.getStep(currStep);
        return s.getStep(currStep);
    }

    public OnboardingStep handleMouseClick() {
        if (currStep > s.getSize() - 1) {
           return null;
        }
        if (previousStep.eventType() == 0) {
            previousStep = s.getStep(currStep);
            return s.getStep(currStep);
        }
        return null;
    }

    public OnboardingStep handleEnter() {
        if (currStep > s.getSize() - 1) {
            return null;
        }
        if (previousStep.eventType() == 1) {
            previousStep = s.getStep(currStep);
            return s.getStep(currStep);
        }
        return null;
    }

    public void stepBack() {
        currStep -= 1;
    }

    public void stepFront() {
        currStep += 1;
    }

    public OnboardingStep getNextStory() {
        previousStep = s.getStep(currStep + 1);
        return s.getStep(currStep + 1);
    }

}