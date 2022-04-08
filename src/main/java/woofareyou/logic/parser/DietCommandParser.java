package woofareyou.logic.parser;

import static java.util.Objects.requireNonNull;
import static woofareyou.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static woofareyou.logic.parser.CliSyntax.PREFIX_DIET;

import woofareyou.commons.core.Messages;
import woofareyou.commons.core.index.Index;
import woofareyou.commons.exceptions.IllegalValueException;
import woofareyou.logic.commands.DietCommand;
import woofareyou.logic.parser.exceptions.ParseException;
import woofareyou.model.pet.Diet;


/**
 * Parses input arguments and creates a new DietCommand object
 */
public class DietCommandParser implements Parser<DietCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DietCommand
     * and returns a DietCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public DietCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_DIET);
        boolean isDietMissing = argMultimap.getValue(PREFIX_DIET).isEmpty();
        if (isDietMissing) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DietCommand.MESSAGE_USAGE));
        }

        Diet diet;
        try {
            diet = ParserUtil.parseDiet(argMultimap.getValue(PREFIX_DIET).orElse(""));
        } catch (ParseException e) {
            throw new ParseException(Diet.MESSAGE_CONSTRAINTS);
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
        }

        return new DietCommand(index, diet);
    }
}
