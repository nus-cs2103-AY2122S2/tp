package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NUMBER;

import seedu.address.logic.commands.MailXCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new MailXCommand object
 */
public class MailXCommandParser implements Parser<MailXCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MailXCommand
     * and returns a MailXCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MailXCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_EMAIL,
                        PREFIX_MOD, PREFIX_GROUP, PREFIX_STUDENT_NUMBER);

        if (argumentChecks(args, argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MailXCommand.MESSAGE_USAGE));
        }

        MailXCommand.MailXDescriptor mailXDescriptor = new MailXCommand.MailXDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            mailXDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            mailXDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        if (argMultimap.getValue(PREFIX_GROUP).isPresent()) {
            mailXDescriptor.setGroup(ParserUtil.parseGroup(argMultimap.getValue(PREFIX_GROUP).get()));
        }

        if (argMultimap.getValue(PREFIX_MOD).isPresent()) {
            mailXDescriptor.setMod(ParserUtil.parseMod(argMultimap.getValue(PREFIX_MOD).get()));
        }

        if (argMultimap.getValue(PREFIX_STUDENT_NUMBER).isPresent()) {
            mailXDescriptor.setStudentNumber(ParserUtil.parseStudentNumber(argMultimap
                    .getValue(PREFIX_STUDENT_NUMBER).get()));
        }

        return new MailXCommand(mailXDescriptor);
    }

    /**
     * Performs checks on the arguments received.
     * @param args The arguments.
     * @param argMultimap The tokenized argument map.
     * @return The boolean value associated with whether the arguments pass the checks or not.
     */
    public boolean argumentChecks(String args, ArgumentMultimap argMultimap) {
        return (argMultimap.getPreamble().trim().isEmpty() && argMultimap.isEmpty())
                || !argMultimap.getPreamble().trim().isEmpty()
                || argMultimap.isEmpty()
                || args.trim().isEmpty();
    }
}
