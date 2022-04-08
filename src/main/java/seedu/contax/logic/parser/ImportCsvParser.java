package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_FILE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TAG;

import java.io.File;
import java.util.HashSet;

import seedu.contax.logic.commands.ImportCsvCommand;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.IndexedCsvFile;

/**
 * Parses input arguments and creates a new ImportCsvCommand object.
 */
public class ImportCsvParser implements Parser<ImportCsvCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ImportCsvCommand
     * and returns a ImportCsvCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ImportCsvCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_FILE, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        if (!argMultimap.arePrefixesPresent(PREFIX_FILE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCsvCommand.MESSAGE_USAGE));
        }
        File file = ParserUtil.parseCsvFilePath(argMultimap.getValue(PREFIX_FILE).get());

        int namePosition = checkPrefix(1, PREFIX_NAME, argMultimap);
        int phonePosition = checkPrefix(2, PREFIX_PHONE, argMultimap);
        int emailPosition = checkPrefix(3, PREFIX_EMAIL, argMultimap);
        int addressPosition = checkPrefix(4, PREFIX_ADDRESS, argMultimap);
        int tagPosition = checkPrefix(5, PREFIX_TAG, argMultimap);

        HashSet<Integer> positionHashSet = new HashSet<>();
        positionHashSet.add(namePosition);
        checkDuplicateAndAdd(positionHashSet, phonePosition);
        checkDuplicateAndAdd(positionHashSet, emailPosition);
        checkDuplicateAndAdd(positionHashSet, addressPosition);
        checkDuplicateAndAdd(positionHashSet, tagPosition);

        IndexedCsvFile indexedCsvFileObject = new IndexedCsvFile(file, namePosition, phonePosition,
                emailPosition, addressPosition, tagPosition);

        return new ImportCsvCommand(indexedCsvFileObject);
    }

    /**
     * Checks if the integer doesn't exist, add to {@code HashSet}, else throw {@code ParseException}.
     * Used to check for duplicate positions.
     */
    private void checkDuplicateAndAdd(HashSet<Integer> hashSet, int position) throws ParseException {
        if (!hashSet.contains(position)) {
            hashSet.add(position);
        } else {
            throw new ParseException(IndexedCsvFile.MESSAGE_CLASHING_POSITIONS);
        }
    }

    /**
     * Checks if the prefix exists in the command and provides the index, otherwise uses the default index.
     */
    private int checkPrefix(int defaultValue, Prefix prefix, ArgumentMultimap argsMultimap) throws ParseException {
        if (argsMultimap.arePrefixesPresent(prefix)) {
            return ParserUtil.parseCsvPositions(argsMultimap.getValue(prefix).get());
        } else {
            return defaultValue;
        }
    }
}
