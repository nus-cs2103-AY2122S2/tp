package seedu.trackermon.ui;

import javafx.beans.property.SimpleStringProperty;

/**
 * The UI component that is responsible for creating the command information to be displayed in {@code Tableview}.
 */
public class CommandBuilder {
    private SimpleStringProperty commandTitle;
    private SimpleStringProperty commandInput;

    /**
     * Creates a {@code CommandBuilder} to display command information in {@code Tableview}
     * @param commandTitle title of command
     * @param commandInput command to input
     */
    public CommandBuilder(String commandTitle, String commandInput) {
        this.commandTitle = new SimpleStringProperty(commandTitle);
        this.commandInput = new SimpleStringProperty(commandInput);;
    }

    public String getCommandTitle() {
        return commandTitle.get();
    }

    public SimpleStringProperty commandTitleProperty() {
        return commandTitle;
    }

    public String getCommandInput() {
        return commandInput.get();
    }

    public SimpleStringProperty commandInputProperty() {
        return commandInput;
    }
}
