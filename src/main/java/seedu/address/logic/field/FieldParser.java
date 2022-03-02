package seedu.address.logic.field;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Field;

public interface FieldParser<T extends Field> {
    T parse(String args) throws ParseException;
}
