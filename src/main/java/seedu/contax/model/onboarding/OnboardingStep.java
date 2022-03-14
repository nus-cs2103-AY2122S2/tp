package seedu.contax.model.onboarding;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import seedu.contax.model.Model;
import seedu.contax.ui.onboarding.OnboardingCommandBox;
import seedu.contax.ui.onboarding.OnboardingStoryManager;

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
    private BiFunction<Model, OnboardingCommandBox, String>  commandInstruction;
    private Function<Model, String> labelInstruction;

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
     * @param commandInstruction command instruction to run
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
                          BiFunction<Model, OnboardingCommandBox, String> commandInstruction,
                          Function<Model, String> instruction,
                          boolean isCommandCustom) {

        this.displayMessage = message;
        this.eventType = eventType;
        this.messageHeight = height;
        this.messageWidth = width;
        this.overlayOption = overlay;
        this.positionOption = position;
        this.highlightOption = highlight;
        this.commandInstruction = commandInstruction;
        this.labelInstruction = instruction;
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

    public BiFunction<Model, OnboardingCommandBox, String> getCommandInstruction() {
        return commandInstruction;
    }

    public String getCommand() {
        return command;
    }

    public Function<Model, String> getLabelInstruction() {
        return labelInstruction;
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
                && otherStep.getLabelInstruction() == getLabelInstruction()
                && otherStep.getIsCommandCustom() == getIsCommandCustom()
                && otherStep.getCommandInstruction() == getCommandInstruction();
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventType, displayMessage, overlayOption,
                messageHeight, messageWidth, highlightOption,
                positionOption, commandInstruction, command, labelInstruction, isCommandCustom);
    }
}
