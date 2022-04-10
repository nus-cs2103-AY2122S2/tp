package seedu.address.model;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.record.Record;

public class ExpiredRecordBook implements ReadOnlyExpiredRecordBook {

    private final ObservableList<Record> record;

    {
        this.record = FXCollections.observableArrayList();
    }

    public ExpiredRecordBook() {
    }

    /**
     * Constructor using RecordBook
     */
    public ExpiredRecordBook(ReadOnlyRecordBook recordBook) {
        ObservableList<Record> records = recordBook.getRecordList();

        LocalDate now = LocalDate.now();
        for (Record r : records) {
            LocalDate endDate = r.getEndDate().getDate();

            if (endDate.isBefore(now)) {
                this.record.add(r);
            }
        }
    }

    /**
     * Updates the record history list.
     * @param model The model object.
     */
    public void updateExpiredRecordList(Model model) {
        ObservableList<Record> records = model.getRecordBook().getRecordList();

        LocalDate now = LocalDate.now();
        for (Record r : records) {
            LocalDate endDate = r.getEndDate().getDate();

            if (endDate.isBefore(now) && !this.record.contains(r)) {
                this.record.add(r);
            }
        }
    }

    @Override
    public ObservableList<Record> getExpiredRecordList() {
        return this.record;
    }
}
