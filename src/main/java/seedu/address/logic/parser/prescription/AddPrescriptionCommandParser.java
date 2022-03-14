package seedu.address.logic.parser.prescription;

import seedu.address.logic.commands.contact.AddContactCommand;
import seedu.address.logic.commands.prescription.AddPrescriptionCommand;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Nric;
import seedu.address.model.prescription.DrugName;
import seedu.address.model.prescription.Instruction;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.PrescriptionDate;

import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class AddPrescriptionCommandParser implements Parser<AddPrescriptionCommand> {

    public AddPrescriptionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TYPE, PREFIX_NRIC, PREFIX_NAME, PREFIX_DATE,
                        PREFIX_INSTRUCTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_TYPE, PREFIX_NRIC, PREFIX_NAME,
                PREFIX_DATE, PREFIX_INSTRUCTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactCommand.MESSAGE_USAGE));
        }

        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        DrugName drugName = ParserUtil.parseDrugName(argMultimap.getValue(PREFIX_NAME).get());
        PrescriptionDate date = ParserUtil.parsePrescriptionDate(argMultimap.getValue(PREFIX_DATE).get());
        Instruction instruction = ParserUtil.parseInstruction(argMultimap.getValue(PREFIX_INSTRUCTION).get());

        Prescription prescription = new Prescription(nric, drugName, date, instruction);

        return new AddPrescriptionCommand(nric, prescription);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
