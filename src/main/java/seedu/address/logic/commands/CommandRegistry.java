package seedu.address.logic.commands;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import seedu.address.logic.parser.AddCommandParser;
import seedu.address.logic.parser.AddMembershipParser;
import seedu.address.logic.parser.AddTransactionParser;
import seedu.address.logic.parser.AppendCommandParser;
import seedu.address.logic.parser.DeleteCommandParser;
import seedu.address.logic.parser.EditCommandParser;
import seedu.address.logic.parser.FindCommandParser;
import seedu.address.logic.parser.ListTransactionCommandParser;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.RemarkCommandParser;
import seedu.address.logic.parser.RemoveCommandParser;
import seedu.address.logic.parser.SortCommandParser;



public class CommandRegistry {
    public static final Map<String, Parser<? extends Command>> PARSERS;
    static {
        HashMap<String, Parser<? extends Command>> parsers = new HashMap<>();
        // --- Do not modify above this line unless you know what you're doing. (Trust me, you don't.) ---

        parsers.put(AddCommand.COMMAND_WORD, new AddCommandParser());
        parsers.put(EditCommand.COMMAND_WORD, new EditCommandParser());
        parsers.put(AppendCommand.COMMAND_WORD, new AppendCommandParser());
        parsers.put(RemoveCommand.COMMAND_WORD, new RemoveCommandParser());
        parsers.put(DeleteCommand.COMMAND_WORD, new DeleteCommandParser());
        parsers.put(ClearCommand.COMMAND_WORD, (String args) -> new ClearCommand());
        parsers.put(FindCommand.COMMAND_WORD, new FindCommandParser());
        parsers.put(ListCommand.COMMAND_WORD, (String args) -> new ListCommand());
        parsers.put(ExitCommand.COMMAND_WORD, (String args) -> new ExitCommand());
        parsers.put(HelpCommand.COMMAND_WORD, (String args) -> new HelpCommand());
        parsers.put(DeleteFilteredCommand.COMMAND_WORD, (String args) -> new DeleteFilteredCommand());
        parsers.put(RemarkCommand.COMMAND_WORD, new RemarkCommandParser());
        parsers.put(AddMembershipCommand.COMMAND_WORD, new AddMembershipParser());
        parsers.put(SortCommand.COMMAND_WORD, new SortCommandParser());
        parsers.put(AddTransactionCommand.COMMAND_WORD, new AddTransactionParser());
        parsers.put(ListTransactionCommand.COMMAND_WORD, new ListTransactionCommandParser());

        // --- Do not modify below this line unless you know what you're doing. (Trust me, you don't.) ---
        PARSERS = Collections.unmodifiableMap(parsers);
    }
}
