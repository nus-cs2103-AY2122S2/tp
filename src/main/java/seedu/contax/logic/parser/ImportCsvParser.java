package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_FILE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.contax.logic.commands.ImportCsvCommand;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.ImportCsv;

public class ImportCsvParser implements Parser<ImportCsvCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ImportCsvCommand
     * and returns a ImportCsvCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ImportCsvCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_FILE, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        if (!arePrefixesPresent(argMultimap, PREFIX_FILE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCsvCommand.MESSAGE_USAGE));
        }

        String fileName = ParserUtil.parseCsvFilePath(argMultimap.getValue(PREFIX_FILE).get());

        //check if file exists, else throw exception

        int namePosition = 1;
        int phonePosition = 2;
        int emailPosition = 3;
        int addressPosition = 4;
        int tagPosition = 5;
        if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            namePosition = ParserUtil.parseCsvPositions(argMultimap.getValue(PREFIX_NAME).get());
        }
        if (arePrefixesPresent(argMultimap, PREFIX_PHONE)) {
            phonePosition = ParserUtil.parseCsvPositions(argMultimap.getValue(PREFIX_PHONE).get());
        }
        if (arePrefixesPresent(argMultimap, PREFIX_EMAIL)) {
            emailPosition = ParserUtil.parseCsvPositions(argMultimap.getValue(PREFIX_EMAIL).get());
        }
        if (arePrefixesPresent(argMultimap, PREFIX_ADDRESS)) {
            addressPosition = ParserUtil.parseCsvPositions(argMultimap.getValue(PREFIX_ADDRESS).get());
        }
        if (arePrefixesPresent(argMultimap, PREFIX_TAG)) {
            tagPosition = ParserUtil.parseCsvPositions(argMultimap.getValue(PREFIX_TAG).get());
        }

        //remember to add check whether all the positions are distinct, otherwise throw exception

        ImportCsv importCsvObject = new ImportCsv(fileName, namePosition, phonePosition,
                emailPosition, addressPosition, tagPosition);

        return new ImportCsvCommand(importCsvObject);

    }
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
