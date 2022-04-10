package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.record.ClientID;
import seedu.address.model.record.EndDate;
import seedu.address.model.record.InsuranceID;
import seedu.address.model.record.Record;
import seedu.address.model.record.StartDate;


/**
 * Jackson-friendly version of {@link Record}.
 */
class JsonAdaptedRecord {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Record's %s field is missing!";

    private final String clientID;
    private final String insuranceID;
    private final String startDate;
    private final String endDate;

    /**
     * Constructs a {@code JsonAdaptedRecord} with the given record details.
     */
    @JsonCreator
    public JsonAdaptedRecord(@JsonProperty("clientID") String clientID,
                             @JsonProperty("insuranceID") String insuranceID,
                             @JsonProperty("startDate") String startDate,
                             @JsonProperty("endDate") String endDate) {
        this.clientID = clientID;
        this.insuranceID = insuranceID;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    /**
     * Converts a given {@code Record} into this class for Jackson use.
     */
    public JsonAdaptedRecord(Record source) {
        clientID = source.getClientID().toString();
        insuranceID = source.getInsuranceID().toString();
        startDate = source.getStartDate().toString();
        endDate = source.getEndDate().toString();
    }

    /*@JsonValue
    public int getRecordName() {
        return id;
    }*/


    /**
     * Converts this Jackson-friendly adapted record object into the model's {@code Record} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted record.
     */
    public Record toModelType() throws IllegalValueException {
        //TODO: validate Record object
        if (clientID == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ClientID.class.getSimpleName()));
        }
        final ClientID modelClientID = new ClientID(clientID, true);

        if (insuranceID == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    InsuranceID.class.getSimpleName()));
        }
        final InsuranceID modelInsuranceID = new InsuranceID(insuranceID, true);

        if (startDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StartDate.class.getSimpleName()));
        }
        if (StartDate.validateDateTime(startDate) == null) {
            throw new IllegalValueException(StartDate.MESSAGE_CONSTRAINTS);
        }
        final StartDate modelStartDate = new StartDate(startDate);

        if (endDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EndDate.class.getSimpleName()));
        }

        if (EndDate.validateDateTime(endDate) == null) {
            throw new IllegalValueException(EndDate.MESSAGE_CONSTRAINTS);
        }
        final EndDate modelEndDate = new EndDate(endDate);


        return new Record(modelClientID, modelInsuranceID, modelStartDate, modelEndDate);
    }

}
