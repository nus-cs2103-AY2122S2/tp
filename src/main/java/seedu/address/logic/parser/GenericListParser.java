package seedu.address.logic.parser;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.applicant.ListApplicantCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public abstract class GenericListParser<T extends Command> implements Parser<T> {

    @Override
    public T parse(String userInput) throws ParseException {
        if (userInput.equals("")) {
            return returnFullList();
        }
        char featureType = userInput.trim().charAt(0);

        if (featureType == 'f') {
            return parseFilter(userInput);
        } else if (featureType == 's') {
            return parseSort(userInput);
        }
        throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                ListApplicantCommand.MESSAGE_USAGE));
    }

    /**
     * Returns {T} command
     * @return
     */
    public abstract T returnFullList();

    /**
     * Parses the given {@code String} of arguments in the context of performing sort feature
     * and returns an {T} object for execution.
     * @param userInput The input arguments string
     * @return {T} object with respective sort argument for execution
     * @throws ParseException if the user input does not conform the expected sort format
     */
    public abstract T parseSort(String userInput) throws ParseException;

    /**
     * Parses the given {@code String} of arguments in the context of performing filter feature
     * and returns an {T} object for execution.
     * @param userInput The input arguments string
     * @return {T} object with respective filter type and filter argument for execution
     * @throws ParseException if the user input does not conform the expected filter format
     */
    public abstract T parseFilter(String userInput) throws ParseException;
}
