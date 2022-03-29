package seedu.address.logic.parser.testresult;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICALTEST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESULT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TESTDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.stream.Stream;

import seedu.address.logic.commands.ViewedNric;
import seedu.address.logic.commands.testresult.AddTestResultCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Nric;
import seedu.address.model.testresult.MedicalTest;
import seedu.address.model.testresult.Result;
import seedu.address.model.testresult.TestDate;
import seedu.address.model.testresult.TestResult;

public class AddTestResultCommandParser implements Parser<AddTestResultCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddTestResultCommand
     * and returns an AddTestResultCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTestResultCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TYPE, PREFIX_NRIC, PREFIX_TESTDATE, PREFIX_MEDICALTEST,
                        PREFIX_RESULT);

        if (!arePrefixesPresent(argMultimap, PREFIX_TYPE, PREFIX_NRIC, PREFIX_TESTDATE, PREFIX_MEDICALTEST,
                PREFIX_RESULT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTestResultCommand.MESSAGE_USAGE));
        }

        Nric patientNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        TestDate testDate = ParserUtil.parseTestDate(argMultimap.getValue(PREFIX_TESTDATE).get());
        MedicalTest medicalTest = ParserUtil.parseMedicalTest(argMultimap.getValue(PREFIX_MEDICALTEST).get());
        Result result = ParserUtil.parseResult(argMultimap.getValue(PREFIX_RESULT).get());

        TestResult testResult = new TestResult(patientNric, testDate, medicalTest, result);
        ViewedNric.setViewedNric(patientNric);

        return new AddTestResultCommand(patientNric, testResult);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
