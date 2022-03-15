package seedu.address.logic.parser.help;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.help.DetailHelpCommand;
import seedu.address.logic.commands.help.HelpCommand;
import seedu.address.logic.commands.help.OverallHelpCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;


public class HelpCommandParser implements Parser<HelpCommand> {
    @Override
    public HelpCommand parse(String userInput) throws ParseException {
        if (userInput.equals("")) {
            return new OverallHelpCommand();
        } else {
            return new DetailHelpCommand(userInput.replaceAll("\\s+", ""));
        }
    }
}
