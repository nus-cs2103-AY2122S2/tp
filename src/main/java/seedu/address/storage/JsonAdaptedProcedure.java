package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.procedure.Completion;
import seedu.address.model.procedure.Cost;
import seedu.address.model.procedure.Date;
import seedu.address.model.procedure.Information;
import seedu.address.model.procedure.Procedure;


public class JsonAdaptedProcedure {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Procedure's %s field is missing!";

    private final String information;
    private final String date;
    private final String cost;
    private final String hasCompleted;

    /**
     * Constructs a {@code JsonAdaptedProcedure} with the given procedure details.
     */
    @JsonCreator
    public JsonAdaptedProcedure(@JsonProperty("information") String information,
            @JsonProperty("date") String date, @JsonProperty("cost") String cost,
            @JsonProperty("hasCompleted") String hasCompleted) {
        this.information = information;
        this.date = date;
        this.cost = cost;
        this.hasCompleted = hasCompleted;
    }

    /**
     * Converts a given {@code Procedure} into this class for Jackson use.
     */
    public JsonAdaptedProcedure(Procedure source) {
        information = source.getInfo().info;
        date = source.getDate().toString();
        cost = source.getCost().toString();
        hasCompleted = source.getHasCompleted().toString();
    }

    /**
     * Converts this Jackson-friendly adapted procClient object into the model's {@code Procedure} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted client.
     */
    public Procedure toModelType() throws IllegalValueException {
        if (information == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Information.class.getSimpleName()));
        }
        if (!Information.isValidInformation(information)) {
            throw new IllegalValueException(Information.MESSAGE_CONSTRAINTS);
        }
        final Information modelInfo = new Information(information);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        if (cost == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Cost.class.getSimpleName()));
        }
        if (!Cost.isValidCost(cost)) {
            throw new IllegalValueException(Cost.MESSAGE_CONSTRAINTS);
        }
        final Cost modelCost = new Cost(cost);

        if (hasCompleted == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Completion.class.getSimpleName()));
        }
        if (!Completion.isValidHasCompleted(hasCompleted)) {
            throw new IllegalValueException(Completion.MESSAGE_CONSTRAINTS);
        }
        final Completion modelCompletion = new Completion(hasCompleted);


        return new Procedure(modelInfo, modelDate, modelCost, modelCompletion);
    }
}
