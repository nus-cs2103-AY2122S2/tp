package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.RecordBook;
import seedu.address.model.record.Record;

/**
 * A utility class containing a list of {@code Record} objects to be used in tests.
 */
public class TypicalRecords {
    public static final Record RECORD1 = new RecordBuilder()
            .withClientID("1")
            .withInsuranceID("1").withStartDate("12-12-2021").withEndDate("12-12-2029").build();

    public static final Record RECORD2 = new RecordBuilder()
            .withClientID("2")
            .withInsuranceID("2").withStartDate("03-12-2021").withEndDate("03-12-2029").build();

    public static final Record RECORD3 = new RecordBuilder()
            .withClientID("3")
            .withInsuranceID("3").withStartDate("01-12-2021").withEndDate("01-12-2029").build();

    private TypicalRecords() {} // prevents instantiation

    /**
     * Returns an {@code RecordBook} with all the typical records.
     */
    public static RecordBook getTypicalRecordBook() {
        RecordBook rb = new RecordBook();
        for (Record record : getTypicalRecords()) {
            rb.addRecord(record);
        }
        return rb;
    }

    public static List<Record> getTypicalRecords() {
        return new ArrayList<>(Arrays.asList(RECORD1, RECORD2, RECORD3));
    }
}
