package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.TYPE_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.TYPE_PERSON;
import static seedu.address.logic.parser.CliSyntax.TYPE_TASK;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {

        String type = ArgumentTokenizer.getType(args.trim());
        String removedType = args.trim().substring(3);

        if (type.equals(TYPE_PERSON)) {
            return new AddPersonCommandParser().parse(removedType);
        } else if (type.equals(TYPE_INTERVIEW)) {
            return new AddInterviewCommandParser().parse(removedType);
        } else if (type.equals(TYPE_TASK)) {
            return new AddTaskCommandParser().parse(removedType);
        } else {
            return null;
        }
    }
}