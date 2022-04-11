package seedu.contax.model.onboarding;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import seedu.contax.model.Model;
import seedu.contax.ui.onboarding.OnboardingCommandBox;

/**
 * Stores actions for the OnboardingWindow.
 */
public class OnboardingStep {
    private String displayMessage;
    private OnboardingStory.OverlayOption overlayOption;
    private OnboardingStory.HighlightOption highlightOption;
    private OnboardingStory.PositionOption positionOption;
    private int eventType;
    private double messageHeight;
    private double messageWidth;
    private String command;
    private boolean isCommandCustom;
    private BiFunction<Model, OnboardingCommandBox, String> commandInstruction;
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
                          OnboardingStory.OverlayOption overlay,
                          OnboardingStory.PositionOption position,
                          OnboardingStory.HighlightOption highlight,
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


    public OnboardingStory.OverlayOption getOverlayOption() {
        return overlayOption;
    }

    public OnboardingStory.HighlightOption getHighlightOption() {
        return highlightOption;
    }

    public OnboardingStory.PositionOption getPositionOption() {
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

    public boolean isCommandCustom() {
        return isCommandCustom;
    }

    public void setEventType(OnboardingStory.PositionOption type) {
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
                && otherStep.isCommandCustom() == isCommandCustom()
                && otherStep.getCommandInstruction() == getCommandInstruction();
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventType, displayMessage, overlayOption,
                messageHeight, messageWidth, highlightOption,
                positionOption, commandInstruction, command, labelInstruction, isCommandCustom);
    }
}
