package seedu.contax.ui.onboarding;

import java.util.ArrayList;

import seedu.contax.logic.commands.AddPersonCommand;
import seedu.contax.logic.commands.Command;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.logic.parser.AddressBookParser;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.onboarding.OnboardingStep;
import seedu.contax.model.onboarding.OnboardingStory;
import seedu.contax.model.person.Person;

import static seedu.contax.model.onboarding.OnboardingStory.HighlightOption.CLEAR_ALL;
import static seedu.contax.model.onboarding.OnboardingStory.HighlightOption.COMMAND_BOX;
import static seedu.contax.model.onboarding.OnboardingStory.OverlayOption.ALL;
import static seedu.contax.model.onboarding.OnboardingStory.OverlayOption.SHOW_COMMAND_BOX;
import static seedu.contax.model.onboarding.OnboardingStory.PositionOption.CENTER;
import static seedu.contax.model.onboarding.OnboardingStory.PositionOption.RESULT_DISPLAY_TOP;

public class OnboardingStoryManager {

    private static final String CLICK_CONTINUE = "\n\nClick any where to continue...";
    private static final String CLICK_EXIT = "\n\nClick any where to exit Quick Tour...";
    private static final String INVALID_COMMAND = "Invalid format! "
            + "Follow the format 'addperson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS'"
            + "\n\nExample: addperson n/Johnny p/91234567 e/Johnny@j.com a/Johnny street";
    private static final AddressBookParser parser = new AddressBookParser();

    private OnboardingStory story;
    private OnboardingStep previousStep;
    private int currStep = 0;
    private ArrayList<Person> personList;

    /**
     * Constructs a manager to manage Onboarding Guide stories
     */
    public OnboardingStoryManager() {
        personList = new ArrayList<>();
        createStories();
    }

    /**
     * Adds a general display step, where the instruction label with the give message,
     * which is centered and the overlay covers everything.
     * @param story story to add to
     * @param message display message
     */
    private void addGeneralDisplayStep(OnboardingStory story, String message) {
        story.addStory(new OnboardingStep(message,
                0.25, 0.5, ALL, CENTER,
                CLEAR_ALL,
                0, null, null, null, false));
    }

    private void createStories() {
        story = new OnboardingStory();
        createIntroSequence();
        createAddPersonSequence();
        createFindPersonSequence();
        createRemovePersonSequence();
        createListPersonsSequence();
        createEndSequence();
    }

    /**
     * Creates and adds OnboardingSteps for the onboarding guide's introduction
     */
    private void createIntroSequence() {
        addGeneralDisplayStep(story, "Welcome to a quick tour of ContaX" + CLICK_CONTINUE);
        addGeneralDisplayStep(story, "You will now be guided through\nthe basic features of ContaX" + CLICK_CONTINUE);

        story.addStory(new OnboardingStep("This is the command box.\nYour commands will go here" + CLICK_CONTINUE,
                0.2, 0.5, SHOW_COMMAND_BOX, RESULT_DISPLAY_TOP, COMMAND_BOX,
                0, null, null, null, false));
    }

    /**
     * Creates and adds OnboardingSteps for add person
     */
    private void createAddPersonSequence() {
        addGeneralDisplayStep(story, "Now lets try adding a person." + CLICK_CONTINUE);
        story.addStory(new OnboardingStep("Follow the format 'addperson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS'"
                        + "\n\nExample: addperson n/Johnny p/91234567 e/Johnny@j.com a/Johnny street"
                        + "\n\nHit enter when you are done",
                0.2, 0.5, SHOW_COMMAND_BOX, RESULT_DISPLAY_TOP, COMMAND_BOX, 1,
                "null123null123null", ((model, commandBox) -> {
            if (commandBox.getText().length() > 0) {
                Command command = null;
                try {
                    command = parser.parseCommand(commandBox.getText());
                } catch (ParseException e) {
                    return INVALID_COMMAND;
                }

                if (!(command instanceof AddPersonCommand)) {
                    return "Please use a add command";
                }

                try {
                    command.execute(model);
                } catch (CommandException e) {
                    if (e.getMessage().equals(AddPersonCommand.MESSAGE_DUPLICATE_PERSON)) {
                        return "Person name already exists! Add someone else";
                    }
                }
            }
            return null;}), null, true));
        story.addStory(new OnboardingStep("Great! %s is now added into the system!" + CLICK_CONTINUE,
                0.2, 0.5, ALL, CENTER, CLEAR_ALL,
                0, null, null, (model) -> String.format("Great! %s is now added into the system!" + CLICK_CONTINUE,
                OnboardingUtil.getLatestPersonName(model)), false));
    }

    /**
     * Creates and adds OnboardingSteps for find person
     */
    private void createFindPersonSequence() {
        story.addStory(new OnboardingStep("Lets try to find %s's record!" + CLICK_CONTINUE,
                0.2, 0.5, ALL, CENTER,
                CLEAR_ALL, 0,
                null, null, (model) -> String.format("Lets try to find %s's record!" + CLICK_CONTINUE,
                OnboardingUtil.getLatestPersonName(model)), false));


        story.addStory(new OnboardingStep("Type 'findperson %s' and hit enter",
                0.1, 0.5, SHOW_COMMAND_BOX,
                RESULT_DISPLAY_TOP, COMMAND_BOX,
                1, "findperson %s", null, (model) -> {
            this.modifyCurrentStepCommand(String.format("findperson %s", OnboardingUtil.getLatestPersonName(model)));
            return String.format("Type 'findperson %s' and hit enter!" + CLICK_CONTINUE,
                    OnboardingUtil.getLatestPersonName(model));
        }, false));

        story.addStory(new OnboardingStep("Great! Here is %s's record!",
                0.2, 0.5, OnboardingStory.OverlayOption.SHOW_PERSON_LIST,
                OnboardingStory.PositionOption.PERSON_LIST_MIDDLE, OnboardingStory.HighlightOption.PERSON_LIST,
                0, null, (model, commandBox) -> {
            Person lastPerson = OnboardingUtil.getLatestPerson(model);
            model.updateFilteredPersonList((p) -> p.isSamePerson(lastPerson));
            return null;
        }, (model) -> String.format("Great! Here is %s's record!" + CLICK_CONTINUE,
                OnboardingUtil.getLatestPersonName(model)), false));
    }

    /**
     * Creates and adds OnboardingSteps for remove person
     */
    private void createRemovePersonSequence() {
        story.addStory(new OnboardingStep("Now lets try to remove %s's record!" + CLICK_CONTINUE,
                0.2, 0.5, ALL, CENTER,
                CLEAR_ALL, 0, null, null, (
                model) -> String.format("Now lets try to remove %s's record!!" + CLICK_CONTINUE,
                OnboardingUtil.getLatestPersonName(model)), false));

        story.addStory(new OnboardingStep("Type 'deleteperson 1' and hit enter",
                0.2, 0.5, SHOW_COMMAND_BOX,
                RESULT_DISPLAY_TOP,
                COMMAND_BOX, 1, "deleteperson 1", null, null, false));

        story.addStory(new OnboardingStep("Great, the record is gone!",
                0.2, 0.5, OnboardingStory.OverlayOption.SHOW_PERSON_LIST,
                OnboardingStory.PositionOption.PERSON_LIST_MIDDLE, OnboardingStory.HighlightOption.PERSON_LIST,
                0, null, (model, commandBox) -> {
            Person lastPerson = OnboardingUtil.getLatestPerson(model);
            model.deletePerson(lastPerson);
            return null; }, null, false));
    }

    /**
     * Creates and adds OnboardingSteps for ending the onboarding guide
     */
    private void createEndSequence() {
        story.addStory(new OnboardingStep("End of Quick Tour!" + CLICK_EXIT,
                0.2, 0.5, ALL,
                CENTER, CLEAR_ALL,
                0, null, null, null, false));

        story.addStory(new OnboardingStep(null,
                0, 0.1, null,
                CENTER, null, 0, null,
                null, null, false));
    }

    /**
     * Create and adds OnboardingSteps for list persons
     */
    private void createListPersonsSequence() {
        story.addStory(new OnboardingStep("Now lets try to list all persons." + CLICK_CONTINUE,
                0.2, 0.5, ALL, CENTER,
                CLEAR_ALL,
                0, null, null, null, false));

        story.addStory(new OnboardingStep("Type 'listpersons' and hit enter",
                0.2, 0.5, SHOW_COMMAND_BOX,
                RESULT_DISPLAY_TOP,
                COMMAND_BOX, 1, "listpersons", null, null, false));

        story.addStory(new OnboardingStep("Great!" + CLICK_CONTINUE,
                0.2, 0.5, OnboardingStory.OverlayOption.SHOW_PERSON_LIST,
                OnboardingStory.PositionOption.MENU_BAR_TOP,
                OnboardingStory.HighlightOption.PERSON_LIST, 0, null, (model, commandBox) -> {
            model.updateFilteredPersonList(unused -> true);
            return null;
        }, null, false));
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
