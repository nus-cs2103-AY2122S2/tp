package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.transaction.TransactionField;

public interface TransactionFieldParser<T extends TransactionField> {
    T parse(String arguments) throws ParseException;
}
