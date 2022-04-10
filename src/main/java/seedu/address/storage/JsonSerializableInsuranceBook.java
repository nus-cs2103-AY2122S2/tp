package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.InsuranceBook;
import seedu.address.model.ReadOnlyInsuranceBook;
import seedu.address.model.insurance.Insurance;

/**
 * An Immutable InsuranceBook that is serializable to JSON format.
 */
@JsonRootName(value = "insurancebook")
class JsonSerializableInsuranceBook {

    public static final String MESSAGE_DUPLICATE_INSURANCE = "Insurances list contains duplicate insurance(s).";

    private final List<JsonAdaptedInsurance> insurances = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableInsuranceBook} with the given insurances.
     */
    @JsonCreator
    public JsonSerializableInsuranceBook(@JsonProperty("insurances") List<JsonAdaptedInsurance> insurances) {
        this.insurances.addAll(insurances);
    }

    /**
     * Converts a given {@code ReadOnlyInsuranceBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableInsuranceBook}.
     */
    public JsonSerializableInsuranceBook(ReadOnlyInsuranceBook source) {
        insurances.addAll(source.getInsuranceList().stream().map(JsonAdaptedInsurance::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this insurance book into the model's {@code InsuranceBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public InsuranceBook toModelType() throws IllegalValueException {
        InsuranceBook insuranceBook = new InsuranceBook();
        for (JsonAdaptedInsurance jsonAdaptedInsurance : insurances) {
            Insurance insurance = jsonAdaptedInsurance.toModelType();
            if (insuranceBook.hasInsurance(insurance)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_INSURANCE);
            }
            insuranceBook.addInsurance(insurance);
        }
        return insuranceBook;
    }

}
