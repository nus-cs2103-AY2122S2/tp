package seedu.address.logic.parser.testresult;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICALTEST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESULT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TESTDATE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.testresult.EditTestResultCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditTestResultCommand object
 */
public class EditTestResultCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the EditTestResultCommand
     * and returns an EditTestResultCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTestResultCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NRIC, PREFIX_TESTDATE,
                    PREFIX_MEDICALTEST, PREFIX_RESULT);

            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());

            EditTestResultCommand.EditTestResultDescriptor editTestResultDescriptor =
                    new EditTestResultCommand.EditTestResultDescriptor();

            if (argMultimap.getValue(PREFIX_NRIC).isPresent()) {
                editTestResultDescriptor.setNric(ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get()));
            }
            if (argMultimap.getValue(PREFIX_TESTDATE).isPresent()) {
                editTestResultDescriptor.setTestDate(ParserUtil.parseTestDate(
                        argMultimap.getValue(PREFIX_TESTDATE).get()));
            }
            if (argMultimap.getValue(PREFIX_MEDICALTEST).isPresent()) {
                editTestResultDescriptor.setMedicalTest(ParserUtil.parseMedicalTest(
                        argMultimap.getValue(PREFIX_MEDICALTEST).get()));
            }
            if (argMultimap.getValue(PREFIX_RESULT).isPresent()) {
                editTestResultDescriptor.setResult(ParserUtil.parseResult(argMultimap.getValue(PREFIX_RESULT).get()));
            }

            return new EditTestResultCommand(index, editTestResultDescriptor);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTestResultCommand.MESSAGE_USAGE), pe);
        }
    }
}
