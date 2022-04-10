package seedu.address.testutil;

import seedu.address.model.record.ClientID;
import seedu.address.model.record.EndDate;
import seedu.address.model.record.InsuranceID;
import seedu.address.model.record.Record;
import seedu.address.model.record.StartDate;

/**
 * A utility class to help with building Record objects.
 */
public class RecordBuilder {
    public static final String DEFAULT_CLIENT_ID = "1";
    public static final String DEFAULT_INSURANCE_ID = "1";
    public static final String DEFAULT_STARTDATE = "06-12-2000";
    public static final String DEFAULT_ENDDATE = "06-12-2075";


    private ClientID clientID;
    private InsuranceID insuranceID;
    private StartDate startDate;
    private EndDate endDate;

    /**
     * Creates a {@code RecordBuilder} with the default details.
     */
    public RecordBuilder() {
        clientID = new ClientID(DEFAULT_CLIENT_ID);
        insuranceID = new InsuranceID(DEFAULT_INSURANCE_ID);
        startDate = new StartDate(DEFAULT_STARTDATE);
        endDate = new EndDate(DEFAULT_ENDDATE);
    }

    /**
     * Sets the {@code ClientID} of the {@code Record} that we are building.
     */
    public RecordBuilder withClientID(String clientID) {
        this.clientID = new ClientID(clientID);
        return this;
    }

    /**
     * Sets the {@code InsuranceID} of the {@code Record} that we are building.
     */
    public RecordBuilder withInsuranceID(String insuranceID) {
        this.insuranceID = new InsuranceID(insuranceID);
        return this;
    }

    /**
     * Sets the {@code StartDate} of the {@code Record} that we are building.
     */
    public RecordBuilder withStartDate(String startDate) {
        this.startDate = new StartDate(startDate);
        return this;
    }

    /**
     * Sets the {@code EndDate} of the {@code Record} that we are building.
     */
    public RecordBuilder withEndDate(String endDate) {
        this.endDate = new EndDate(endDate);
        return this;
    }

    public Record build() {
        return new Record(clientID, insuranceID, startDate, endDate);
    }
}
