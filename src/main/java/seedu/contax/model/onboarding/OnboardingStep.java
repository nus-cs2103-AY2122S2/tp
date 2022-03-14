package seedu.contax.model.onboarding;

import java.util.Objects;
import java.util.function.BiConsumer;

import seedu.contax.model.Model;
import seedu.contax.model.person.Person;
import seedu.contax.ui.onboarding.OnboardingInstruction;
import seedu.contax.ui.onboarding.OnboardingStoryManager;
import seedu.contax.ui.onboarding.Overlay;

/**
 * The model that is responsible for storing actions for the OnbordingWindow
 */
public class OnboardingStep {
    private String displayMessage;
    private OnboardingStoryManager.OverlayOption overlayOption;
    private OnboardingStoryManager.HighlightOption highlightOption;
    private OnboardingStoryManager.PositionOption positionOption;
    private int eventType;
    private double messageHeight;
    private double messageWidth;
    private String command;
    private boolean isCommandCustom;
    private int commandType;
    private BiConsumer<Model, OnboardingInstruction> operationInstruction;

    /**
     * Creates an OnboardingStep object
     * @param message message to display
     * @param height height of label
     * @param width width of label
     * @param overlay overlay option
     * @param position position option for label
     * @param highlight highlight option
     * @param eventType trigger event type
     * @param command command to validate
     * @param commandType command type
     * @param instruction instruction to run
     * @param isCommandCustom if user input is allowed to be custom
     */
    public OnboardingStep(String message,
                          double height,
                          double width,
                          OnboardingStoryManager.OverlayOption overlay,
                          OnboardingStoryManager.PositionOption position,
                          OnboardingStoryManager.HighlightOption highlight,
                          int eventType,
                          String command,
                          int commandType,
                          BiConsumer<Model, OnboardingInstruction> instruction,
                          boolean isCommandCustom) {

        this.displayMessage = message;
        this.eventType = eventType;
        this.messageHeight = height;
        this.messageWidth = width;
        this.overlayOption = overlay;
        this.positionOption = position;
        this.highlightOption = highlight;
        this.commandType = commandType;
        this.operationInstruction = instruction;
        this.command = command;
        this.isCommandCustom = isCommandCustom;
    }

    public int getEventType() {
        return eventType;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }


    public OnboardingStoryManager.OverlayOption getOverlayOption() {
        return overlayOption;
    }

    public OnboardingStoryManager.HighlightOption getHighlightOption() {
        return highlightOption;
    }

    public OnboardingStoryManager.PositionOption getPositionOption() {
        return positionOption;
    }

    public double getMessageHeight() {
        return messageHeight;
    }

    public double getMessageWidth() {
        return messageWidth;
    }

    public int getCommandType() {
        return commandType;
    }

    public String getCommand() {
        return command;
    }

    public BiConsumer<Model, OnboardingInstruction> getOperationInstruction() {
        return operationInstruction;
    }

    public boolean getIsCommandCustom() {
        return isCommandCustom;
    }

    public void setEventType(OnboardingStoryManager.PositionOption type) {
        positionOption = type;
    }

    public void setDisplayMessage(String msg) {
        displayMessage = msg;
    }

    public void setCommand(String cmd) {
        command = cmd;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof OnboardingStep)) {
            return false;
        }

        OnboardingStep otherStep = (OnboardingStep) other;

        return otherStep.getEventType() == getEventType()
                && otherStep.getDisplayMessage().equals(getDisplayMessage())
                && otherStep.getOverlayOption() == getOverlayOption()
                && otherStep.getMessageHeight() == getMessageHeight()
                && otherStep.getMessageWidth() == getMessageWidth()
                && otherStep.getHighlightOption() == getHighlightOption()
                && otherStep.getPositionOption() == getPositionOption()
                && otherStep.getCommand().equals(getCommand())
                && otherStep.getOperationInstruction() == getOperationInstruction()
                && otherStep.getIsCommandCustom() == getIsCommandCustom()
                && otherStep.getCommandType() == getCommandType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventType, displayMessage, overlayOption,
                messageHeight, messageWidth, highlightOption,
                positionOption, commandType, command, operationInstruction, isCommandCustom);
    }
}
