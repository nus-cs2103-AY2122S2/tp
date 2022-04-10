package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyRecordBook;
import seedu.address.model.RecordBook;
import seedu.address.model.record.Record;

/**
 * An Immutable RecordBook that is serializable to JSON format.
 */
@JsonRootName(value = "recordbook")
class JsonSerializableRecordBook {

    public static final String MESSAGE_DUPLICATE_RECORD = "Records list contains duplicate record(s).";

    private final List<JsonAdaptedRecord> records = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableRecordBook} with the given records.
     */
    @JsonCreator
    public JsonSerializableRecordBook(@JsonProperty("records") List<JsonAdaptedRecord> records) {
        this.records.addAll(records);
    }

    /**
     * Converts a given {@code ReadOnlyRecordBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableRecordBook}.
     */
    public JsonSerializableRecordBook(ReadOnlyRecordBook source) {
        records.addAll(source.getRecordList().stream().map(JsonAdaptedRecord::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this record book into the model's {@code RecordBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public RecordBook toModelType() throws IllegalValueException {
        RecordBook recordBook = new RecordBook();
        for (JsonAdaptedRecord jsonAdaptedRecord : records) {
            Record record = jsonAdaptedRecord.toModelType();
            if (recordBook.hasRecord(record)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_RECORD);
            }
            recordBook.addRecord(record);
        }
        return recordBook;
    }
}
