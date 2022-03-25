package seedu.address.logic.parser.medical;

import static java.util.Objects.requireNonNull;
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
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.medical.EditMedicalCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new EditMedicalCommand object
 */
public class EditMedicalCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the EditMedicalCommand
     * and returns an EditMedicalCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditMedicalCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                    PREFIX_NRIC,
                    PREFIX_AGE,
                    PREFIX_BLOODTYPE,
                    PREFIX_MEDICATION,
                    PREFIX_HEIGHT,
                    PREFIX_WEIGHT,
                    PREFIX_ILLNESSES,
                    PREFIX_SURGERIES,
                    PREFIX_FAMILY_HISTORY,
                    PREFIX_IMMUNIZATION_HISTORY,
                    PREFIX_GENDER,
                    PREFIX_ETHNICITY);

            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());

            EditMedicalCommand.EditMedicalDescriptor editMedicalDescriptor =
                    new EditMedicalCommand.EditMedicalDescriptor();

            if (argMultimap.getValue(PREFIX_NRIC).isPresent()) {
                editMedicalDescriptor.setNric(ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get()));
            }
            if (argMultimap.getValue(PREFIX_AGE).isPresent()) {
                editMedicalDescriptor.setAge(ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get()));
            }
            if (argMultimap.getValue(PREFIX_BLOODTYPE).isPresent()) {
                editMedicalDescriptor.setBloodType(
                        ParserUtil.parseBloodType(argMultimap.getValue(PREFIX_BLOODTYPE).get()));
            }
            if (argMultimap.getValue(PREFIX_MEDICATION).isPresent()) {
                editMedicalDescriptor.setMedication(
                        ParserUtil.parseMedication(argMultimap.getValue(PREFIX_MEDICATION).get()));
            }
            if (argMultimap.getValue(PREFIX_HEIGHT).isPresent()) {
                editMedicalDescriptor.setHeight(ParserUtil.parseHeight(argMultimap.getValue(PREFIX_HEIGHT).get()));
            }
            if (argMultimap.getValue(PREFIX_WEIGHT).isPresent()) {
                editMedicalDescriptor.setWeight(ParserUtil.parseWeight(argMultimap.getValue(PREFIX_WEIGHT).get()));
            }
            if (argMultimap.getValue(PREFIX_ILLNESSES).isPresent()) {
                editMedicalDescriptor.setIllnesses(
                        ParserUtil.parseIllnesses(argMultimap.getValue(PREFIX_ILLNESSES).get()));
            }
            if (argMultimap.getValue(PREFIX_SURGERIES).isPresent()) {
                editMedicalDescriptor.setSurgeries(
                        ParserUtil.parseSurgeries(argMultimap.getValue(PREFIX_SURGERIES).get()));
            }
            if (argMultimap.getValue(PREFIX_FAMILY_HISTORY).isPresent()) {
                editMedicalDescriptor.setFamilyHistory(
                        ParserUtil.parseFamilyHistory(argMultimap.getValue(PREFIX_FAMILY_HISTORY).get()));
            }
            if (argMultimap.getValue(PREFIX_IMMUNIZATION_HISTORY).isPresent()) {
                editMedicalDescriptor.setImmunizationHistory(
                        ParserUtil.parseImmunizationHistory(argMultimap.getValue(PREFIX_IMMUNIZATION_HISTORY).get()));
            }
            if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
                editMedicalDescriptor.setGender(ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get()));
            }
            if (argMultimap.getValue(PREFIX_ETHNICITY).isPresent()) {
                editMedicalDescriptor.setEthnicity(
                        ParserUtil.parseEthnicity(argMultimap.getValue(PREFIX_ETHNICITY).get()));
            }

            return new EditMedicalCommand(index, editMedicalDescriptor);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMedicalCommand.MESSAGE_USAGE), pe);
        }
    }
}
