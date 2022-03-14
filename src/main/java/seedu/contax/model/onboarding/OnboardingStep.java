package seedu.contax.model.onboarding;

import java.util.Objects;

import seedu.contax.model.person.Person;

/**
 * The model that is responsible for storing actions for the OnbordingWindow
 */
public class OnboardingStep {
    private String displayMessage;
    private int overlayOption;
    private int highlightOption;
    private int eventType;
    private double messageHeight;
    private double messageWidth;
    private int positionOption;
    private String command;
    private boolean isCommandCustom;
    private Person person;
    private int operationId;

    /**
     * Creates an OnboardingStep object
     * @param message message to display
     * @param option overlay option
     * @param height height of label
     * @param width width of label
     * @param position position option for label
     * @param highlight highlight option
     * @param eventType trigger event type
     * @param command command to validate
     * @param p Person object involved
     * @param op operation id
     */
    public OnboardingStep(String message, int option, double height,
                          double width, int position, int highlight,
                          int eventType,
                          String command,
                          Person p, int op, boolean isCommandCustom) {
        this.displayMessage = message;
        this.overlayOption = option;
        this.eventType = eventType;
        this.messageHeight = height;
        this.messageWidth = width;
        this.positionOption = position;
        this.highlightOption = highlight;
        this.person = p;
        this.operationId = op;
        this.command = command;
        this.isCommandCustom = isCommandCustom;
    }

    public int getEventType() {
        return eventType;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public int getOverlayOption() {
        return overlayOption;
    }

    public double getMessageHeight() {
        return messageHeight;
    }

    public double getMessageWidth() {
        return messageWidth;
    }

    public int getHighlightOption() {
        return highlightOption;
    }

    public int getPositionOption() {
        return positionOption;
    }

    public Person getPerson() {
        return person;
    }

    public String getCommand() {
        return command;
    }

    public int getOperationId() {
        return operationId;
    }

    public boolean getIsCommandCustom() {
        return isCommandCustom;
    }

    public void setEventType(int type) {
        positionOption = type;
    }

    public void setDisplayMessage(String msg) {
        displayMessage = msg;
    }

    public void setCommand(String cmd) {
        command = cmd;
    }

    public boolean isValid() {
        return overlayOption >= 0 && overlayOption <= 3
                && highlightOption >= 0 && highlightOption <= 2
                && positionOption >= 0 && positionOption <= 4;
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

        boolean result = otherStep.getEventType() == getEventType()
                && otherStep.getDisplayMessage().equals(getDisplayMessage())
                && otherStep.getOverlayOption() == getOverlayOption()
                && otherStep.getMessageHeight() == getMessageHeight()
                && otherStep.getMessageWidth() == getMessageWidth()
                && otherStep.getHighlightOption() == getHighlightOption()
                && otherStep.getPositionOption() == getPositionOption()
                && otherStep.getCommand().equals(getCommand())
                && otherStep.getOperationId() == getOperationId()
                && otherStep.getIsCommandCustom() == getIsCommandCustom();

        if (otherStep.getPerson() == null && person == null) {
            return result;
        } else {
            return result && Objects.equals(otherStep.getPerson(), getPerson());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventType, displayMessage, overlayOption,
                messageHeight, messageWidth, highlightOption,
                positionOption, person, command, operationId, isCommandCustom);
    }
}
