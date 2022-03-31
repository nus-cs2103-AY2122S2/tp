package woofareyou.logic.parser;

import static java.util.Objects.requireNonNull;

import woofareyou.commons.core.index.Index;
import woofareyou.commons.exceptions.IllegalValueException;
import woofareyou.logic.commands.DietCommand;
import woofareyou.logic.parser.exceptions.ParseException;
import woofareyou.model.pet.Diet;
import woofareyou.commons.core.Messages;

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
                CliSyntax.PREFIX_DIET);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    DietCommand.MESSAGE_USAGE), ive);
        }

        Diet diet = new Diet(argMultimap.getValue(CliSyntax.PREFIX_DIET).orElse(""));

        return new DietCommand(index, diet);
    }
}
