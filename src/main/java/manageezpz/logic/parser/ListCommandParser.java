package manageezpz.logic.parser;

import manageezpz.logic.commands.ListCommand;

/**
 * The parser for list command.
 */
public class ListCommandParser implements Parser<ListCommand> {
    /**
     * Parse the user input before executing the list command.
     * @param userInput The input entered in the GUI by the user (Will be ignored).
     * @return The List Command.
     */
    @Override
    public ListCommand parse(String userInput) {
        return new ListCommand();
    }
}
