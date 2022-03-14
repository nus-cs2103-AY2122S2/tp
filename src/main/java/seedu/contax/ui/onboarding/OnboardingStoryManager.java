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
        OnboardingInstruction is = new OnboardingInstruction();
    }

    private void addDisplayStep(OnboardingStory story, String message) {
        story.addStory(new OnboardingStep(message,
                0, 0.25, 0.5, 0, 0,
                0, null, -1, null, false));
    }

    private void createStories() {
        OnboardingStory test = new OnboardingStory();
//        BiConsumer<Model, OnboardingInstruction> formatString =
        addDisplayStep(test, "Welcome to a quick tour of ContaX" + CLICK_CONTINUE);
        addDisplayStep(test, "You will now be guided through\nthe basic features of ContaX" + CLICK_CONTINUE);

        test.addStory(new OnboardingStep("This is the command box.\nYour commands will go here" + CLICK_CONTINUE,
                2, 0.2, 0.5, 3, 1, 0, null, -1, null, false));

        addDisplayStep(test, "Now lets try adding a person." + CLICK_CONTINUE);

        test.addStory(new OnboardingStep(
                "Follow the format 'add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS'"
                        + "\n\nExample: add n/Johnny p/91234567 e/Johnny@j.com a/Johnny street"
                        + "\n\nHit enter when you are done",
                2, 0.2, 0.5, 3, 1, 1,
                "null123null123null", 1, null, true));

        test.addStory(new OnboardingStep(
                "Great! %s is now added into the system!" + CLICK_CONTINUE,
                0, 0.2, 0.5, 0, 0, 0, null, -1, (model, instructionLabel) -> {
            instructionLabel.setText(
                    String.format("Great! %s is now added into the system!" + CLICK_CONTINUE,
                            OnboardingUtil.getLatestPersonName(model))
            );

        }, false));

        test.addStory(new OnboardingStep("Lets try to find %s's record!" + CLICK_CONTINUE,
                0, 0.2, 0.5, 0, 0, 0, null, -1, (model, instructionLabel) -> {
            instructionLabel.setText(
                    String.format("Lets try to find %s's record!" + CLICK_CONTINUE,
                            OnboardingUtil.getLatestPersonName(model))
            );
        }, false));


        test.addStory(new OnboardingStep("Type 'find %s' and hit enter",
                2, 0.1, 0.5, 3, 1, 1, "find %s", -1, ((model, instructionLabel) -> {
            instructionLabel.setText(
                    String.format("Type 'find %s' and hit enter!" + CLICK_CONTINUE,
                            OnboardingUtil.getLatestPersonName(model))
            );
            this.modifyCurrentStepCommand(String.format("find %s", OnboardingUtil.getLatestPersonName(model)));
        }), false));

        test.addStory(new OnboardingStep("Great! Here is %s's record!",
                3, 0.2, 0.5, 4, 2, 0, null, 2, ((model, instructionLabel) -> {
            instructionLabel.setText(
                    String.format("Great! Here is %s's record!" + CLICK_CONTINUE,
                            OnboardingUtil.getLatestPersonName(model))
            );
            System.out.println(model.getFilteredPersonList().size());
            Person pp = OnboardingUtil.getLatestPerson(model);
            model.updateFilteredPersonList((p) -> p.isSamePerson(pp));
        }), false));

        test.addStory(new OnboardingStep("Now lets try to remove %s's record!" + CLICK_CONTINUE,
                0, 0.2, 0.5, 0, 0, 0, null, -1,
                (model, instructionLabel) -> {
                    instructionLabel.setText(
                            String.format("Now lets try to remove %s's record!!" + CLICK_CONTINUE,
                                    OnboardingUtil.getLatestPersonName(model))
                    );
                }, false));

        test.addStory(new OnboardingStep("Type 'delete 1' and hit enter",
                2, 0.1, 0.5, 3, 1, 1, "delete 1", -1, null, false));

        test.addStory(new OnboardingStep("Great, %s is gone!",
                3, 0.2, 0.5, 4, 2, 0, null, -1, (model, instructionLabel) -> {
            instructionLabel.setText(
                    String.format("Great, %s is gone!" + CLICK_CONTINUE,
                            OnboardingUtil.getLatestPersonName(model))
            );
        }, false));

        test.addStory(new OnboardingStep("Now lets try to list all persons." + CLICK_CONTINUE,
                0, 0.2, 0.5, 0, 0, 0, null, -1, null, false));

        test.addStory(new OnboardingStep("Type 'list' and hit enter",
                2, 0.1, 0.5, 3, 1, 1, "list", -1, null, false));

        test.addStory(new OnboardingStep("Great!" + CLICK_CONTINUE,
                3, 0.2, 0.5, 1, 2, 0, null, -1, ((model, instructionLabel) -> {
                    model.updateFilteredPersonList(unused -> true);
        }), false));

        test.addStory(new OnboardingStep("End of Quick Tour!" + CLICK_EXIT,
                0, 0.2, 0.5, 0, 0, 0, null, -1, null, false));

        test.addStory(new OnboardingStep(null,
                0, 0.1, 0.5, 0, 0, 0, null, -1, null, false));

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

    public boolean isAtlast() {
        return currStep == story.getSize() - 1;
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
     * resets this manager
     */
    public void reset() {
        currStep = 0;
        createStories();
    }

    public void modifyCurrentStepCommand(String newCommand) {
        story.getStep(currStep).setCommand(newCommand);
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
