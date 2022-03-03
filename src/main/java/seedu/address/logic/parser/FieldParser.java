package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Field;

public interface FieldParser<T extends Field> {
    T parse(String arguments) throws ParseException;
}
