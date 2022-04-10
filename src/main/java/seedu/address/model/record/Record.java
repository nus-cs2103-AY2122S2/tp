package seedu.address.model.record;

import java.time.LocalDate;

/**
 * Represents a Record in Mr. Agent.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Record {
    //TODO

    private static int globalId = 0;

    private final int recordID;
    private final ClientID clientID;
    private final InsuranceID insuranceID;
    private final StartDate startDate;
    private final EndDate endDate;


    /**
     * Every field must be present and not null.
     */
    public Record(ClientID clientID, InsuranceID insuranceID, StartDate startDate, EndDate endDate) {
        this.recordID = globalId++;
        this.clientID = clientID;
        this.insuranceID = insuranceID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getRecordId() {
        return this.recordID;
    }

    public ClientID getClientID() {
        return this.clientID;
    }

    public InsuranceID getInsuranceID() {
        return this.insuranceID;
    }

    public StartDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getStartLocalDate() {
        return this.startDate.getStartDate();
    }

    public EndDate getEndDate() {
        return this.endDate;
    }

    public LocalDate getEndLocalDate() {
        return this.endDate.getEndDate();
    }

    /**
     * Returns true if both record have the same clientID.
     * This defines a weaker notion of equality between two records.
     */
    public boolean isSameRecord(Record otherRecord) {
        if (otherRecord == this) {
            return true;
        }

        return otherRecord != null
                && otherRecord.getClientID().equals(this.clientID)
                && otherRecord.getInsuranceID().equals(this.insuranceID)
                && otherRecord.getStartLocalDate().isBefore(this.getEndLocalDate())
                && otherRecord.getEndLocalDate().isAfter(this.getStartLocalDate());
    }


    /**
     * Returns true if both records have the same identity and data fields.
     * This defines a stronger notion of equality between two records.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Record)) {
            return false;
        }

        Record otherRecord = (Record) other;
        return otherRecord.getRecordId() == this.recordID
                && otherRecord.getClientID().equals(this.clientID)
                && otherRecord.getInsuranceID().equals(this.insuranceID)
                && otherRecord.getStartDate().equals(this.startDate)
                && otherRecord.getEndDate().equals(this.endDate);
    }


    /**
     * Format state as text for viewing.
     */
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Client ")
                .append(this.getClientID())
                .append(" bought insurance ")
                .append(this.getInsuranceID())
                .append(" valid from ")
                .append(this.getStartDate())
                .append(" to ")
                .append(this.getEndDate());
        return builder.toString();
    }
}
