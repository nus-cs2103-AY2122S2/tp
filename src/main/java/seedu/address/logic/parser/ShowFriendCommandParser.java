package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ShowFriendCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FriendName;

/**
 * Parses input arguments and creates a new ShowFriendCommand object
 */
public class ShowFriendCommandParser implements Parser<ShowFriendCommand> {

    /**
     * Parses string input argument entered by user
     * @param args Input entered by user.
     * @return A new ShowFriendCommand containing the person to be shown.
     * @throws ParseException If the input entered is invalid.
     */
    public ShowFriendCommand parse(String args) throws ParseException {

        Pattern p = Pattern.compile("^-?\\d*\\.{0,1}\\d+$"); //Regex to match all numeric Strings
        boolean isShowByIndex = isNumeric(p, args);

        if (isShowByIndex) {
            Index index = ParserUtil.parseIndex(args);
            return new ShowFriendCommand(index);
        } else { //deletion by name
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);

            if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowFriendCommand.MESSAGE_USAGE));
            }

            FriendName name = ParserUtil.parseFriendName(argMultimap.getValue(PREFIX_NAME).get());
            return new ShowFriendCommand(name);
        }
    }

    /**
     * Checks whether the argument entered by user is numeric
     * @param p Regex to check if the argument entered by user is numeric
     * @param strNum Argument entered by user.
     * @return True if the argument entered by user is numeric
     */
    private static boolean isNumeric(Pattern p, String strNum) {
        String strNumTrimmed = strNum.trim();
        return p.matcher(strNumTrimmed).matches();
    }
}
