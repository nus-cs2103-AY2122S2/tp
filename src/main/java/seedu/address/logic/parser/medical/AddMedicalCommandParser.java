package seedu.address.logic.parser.medical;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOODTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ETHNICITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FAMILY_HISTORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ILLNESSES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMMUNIZATION_HISTORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SURGERIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.stream.Stream;

import seedu.address.logic.commands.medical.AddMedicalCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.medical.Age;
import seedu.address.model.medical.BloodType;
import seedu.address.model.medical.Ethnicity;
import seedu.address.model.medical.FamilyHistory;
import seedu.address.model.medical.Gender;
import seedu.address.model.medical.Height;
import seedu.address.model.medical.Illnesses;
import seedu.address.model.medical.ImmunizationHistory;
import seedu.address.model.medical.Medical;
import seedu.address.model.medical.Medication;
import seedu.address.model.medical.Surgeries;
import seedu.address.model.medical.Weight;
import seedu.address.model.patient.Nric;

public class AddMedicalCommandParser implements Parser<AddMedicalCommand> {
    public static final String EMPTY_PLACEHOLDER = "nil";

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddContactCommand
     * and returns an AddContactCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMedicalCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap;
        argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TYPE, PREFIX_NRIC, PREFIX_AGE, PREFIX_BLOODTYPE,
                PREFIX_MEDICATION, PREFIX_HEIGHT, PREFIX_WEIGHT, PREFIX_ILLNESSES, PREFIX_SURGERIES,
                PREFIX_FAMILY_HISTORY, PREFIX_IMMUNIZATION_HISTORY, PREFIX_GENDER, PREFIX_ETHNICITY);

        if (!arePrefixesPresent(argMultimap, PREFIX_TYPE, PREFIX_NRIC) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMedicalCommand.MESSAGE_USAGE));
        }

        Nric patientNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        Age age = ParserUtil.parseAge(
                argMultimap.getValue(PREFIX_AGE).orElse(EMPTY_PLACEHOLDER));
        BloodType bloodtype = ParserUtil.parseBloodType(
                argMultimap.getValue(PREFIX_BLOODTYPE).orElse(EMPTY_PLACEHOLDER));
        Medication medication = ParserUtil.parseMedication(
                argMultimap.getValue(PREFIX_MEDICATION).orElse(EMPTY_PLACEHOLDER));
        Height height = ParserUtil.parseHeight(
                argMultimap.getValue(PREFIX_HEIGHT).orElse(EMPTY_PLACEHOLDER));
        Weight weight = ParserUtil.parseWeight(
                argMultimap.getValue(PREFIX_WEIGHT).orElse(EMPTY_PLACEHOLDER));
        Illnesses illnesses = ParserUtil.parseIllnesses(
                argMultimap.getValue(PREFIX_ILLNESSES).orElse(EMPTY_PLACEHOLDER));
        Surgeries surgeries = ParserUtil.parseSurgeries(
                argMultimap.getValue(PREFIX_SURGERIES).orElse(EMPTY_PLACEHOLDER));
        FamilyHistory familyHistory = ParserUtil.parseFamilyHistory(
                argMultimap.getValue(PREFIX_FAMILY_HISTORY).orElse(EMPTY_PLACEHOLDER));
        ImmunizationHistory immunizationHistory = ParserUtil.parseImmunizationHistory(
                argMultimap.getValue(PREFIX_IMMUNIZATION_HISTORY).orElse(EMPTY_PLACEHOLDER));
        Gender gender = ParserUtil.parseGender(
                argMultimap.getValue(PREFIX_GENDER).orElse(EMPTY_PLACEHOLDER));
        Ethnicity ethnicity = ParserUtil.parseEthnicity(
                argMultimap.getValue(PREFIX_ETHNICITY).orElse(EMPTY_PLACEHOLDER));

        Medical medical = new Medical(patientNric, age, bloodtype, medication, height, weight, illnesses, surgeries,
                familyHistory, immunizationHistory, gender, ethnicity);

        return new AddMedicalCommand(patientNric, medical);
    }

}
