package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.PresentAttendanceCommand.PetAttendanceDescriptor;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DROPOFF;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PICKUP;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PresentAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class PresentAttendanceCommandParser implements Parser<PresentAttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PresentAttendanceCommand
     * and returns a PresentAttendanceCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PresentAttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_PICKUP, PREFIX_DROPOFF);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PresentAttendanceCommand.MESSAGE_USAGE), pe);
        }

        if (!allFieldsPresent(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PresentAttendanceCommand.MESSAGE_USAGE));
        }

        PetAttendanceDescriptor petAttendanceDescriptor =
                new PetAttendanceDescriptor();

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            petAttendanceDescriptor.setAttendanceDate(ParserUtil.parseAttendanceDate(
                    argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_PICKUP).isPresent()) {
            petAttendanceDescriptor.setPickUpTime(ParserUtil.parsePickUpTime(
                    argMultimap.getValue(PREFIX_PICKUP).get()));
        }
        if (argMultimap.getValue(PREFIX_DROPOFF).isPresent()) {
            petAttendanceDescriptor.setDropOffTime(ParserUtil.parseDropOffTime(
                    argMultimap.getValue(PREFIX_DROPOFF).get()));
        }

        return new PresentAttendanceCommand(index, petAttendanceDescriptor);
    }

    /**
     * Checks to see if all the compulsory arguments for the command are present.
     *
     * @param argumentMultimap the arguments of the command.
     * @return true if all compulsory arguments are present, false otherwise.
     */
    private boolean allFieldsPresent(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_DATE).isPresent() &&
                argumentMultimap.getValue(PREFIX_PICKUP).isPresent() &&
                argumentMultimap.getValue(PREFIX_DROPOFF).isPresent();
    }
}

