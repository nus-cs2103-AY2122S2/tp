package seedu.contax.model;

import seedu.contax.model.person.Person;

public class OnboardingStep {
    private String displayMessage;
    private int overlayOption;
    private int highlightOption;
    private int eventType;
    private double messageHeight;
    private double messageWidth;
    private int positionOption;
    private String command;
    private Person person;
    private int pOperation;

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
                          Person p, int op) {
        this.displayMessage = message;
        this.overlayOption = option;
        this.eventType = eventType;
        this.messageHeight = height;
        this.messageWidth = width;
        this.positionOption = position;
        this.highlightOption = highlight;
        this.person = p;
        this.pOperation = op;
        this.command = command;
    }

    public int eventType() {
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

    public int getPersonOperation() {
        return pOperation;
    }

    public void setEventType(int type) {
        positionOption = type;
    }


}
