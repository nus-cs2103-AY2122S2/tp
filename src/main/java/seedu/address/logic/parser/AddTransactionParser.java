package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddTransactionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionField;
import seedu.address.model.transaction.TransactionFieldRegistry;


public class AddTransactionParser implements Parser<AddTransactionCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the
     * AddTransactionCommand and returns an AddTransactionCommand
     * object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTransactionCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Prefix[] allPrefixes = Arrays.copyOf(TransactionFieldRegistry.PREFIXES,
                TransactionFieldRegistry.PREFIXES.length);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, allPrefixes);

        // Ensure that all required fields are present.
        if (!arePrefixesPresent(argMultimap, TransactionFieldRegistry.REQUIRED_PREFIXES)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTransactionCommand.MESSAGE_USAGE));
        }

        // Get the index of person we want to modify, throw error if there isn't one.
        Index index;
        try {
            index = IndexParser.parse(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTransactionCommand.MESSAGE_USAGE), ive);
        }

        // Parse all transaction fields.
        ArrayList<TransactionField> transactionFields = new ArrayList<>();
        for (Prefix p : TransactionFieldRegistry.PREFIXES) {
            Optional<String> argOpt = argMultimap.getValue(p);
            if (argOpt.isEmpty()) {
                continue;
            }
            TransactionFieldParser<? extends TransactionField> parser = TransactionFieldRegistry.PARSERS.get(p);
            TransactionField field = parser.parse(argOpt.get());
            transactionFields.add(field);
        }

        return new AddTransactionCommand(index, (String identifier) -> new Transaction(transactionFields, identifier));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
