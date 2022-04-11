package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.TYPE_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.TYPE_PERSON;
import static seedu.address.logic.parser.CliSyntax.TYPE_TASK;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * EditCommand and returns a EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        String type = ArgumentTokenizer.getType(args.trim());
        String removedType = args.trim().substring(3);

        switch (type) {
        case TYPE_PERSON:
            return new EditPersonCommandParser().parse(removedType);
        case TYPE_INTERVIEW:
            return new EditInterviewCommandParser().parse(removedType);
        case TYPE_TASK:
            return new EditTaskCommandParser().parse(removedType);
        default:
            return null;
        }
    }

}

