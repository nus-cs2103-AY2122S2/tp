package seedu.trackbeau.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.trackbeau.commons.exceptions.IllegalValueException;
import seedu.trackbeau.model.service.Duration;
import seedu.trackbeau.model.service.Price;
import seedu.trackbeau.model.service.Service;
import seedu.trackbeau.model.service.ServiceName;


/**
 * Jackson-friendly version of {@link Service}.
 */
class JsonAdaptedService {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Service's %s field is missing!";

    private final String name;
    private final Double price;
    private final Integer duration;

    /**
     * Constructs a {@code JsonAdaptedService} with the given service details.
     */
    @JsonCreator
    public JsonAdaptedService(@JsonProperty("name") String name,
                              @JsonProperty("price") Double price,
                              @JsonProperty("duration") Integer duration) {
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

    /**
     * Converts a given {@code Service} into this class for Jackson use.
     */
    public JsonAdaptedService(Service source) {
        name = source.getName().fullName;
        price = source.getPrice().value;
        duration = source.getDuration().value;
    }

    ServiceName getModelName() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ServiceName.class.getSimpleName()));
        }
        if (!ServiceName.isValidName(name)) {
            throw new IllegalValueException(ServiceName.MESSAGE_CONSTRAINTS);
        }
        return new ServiceName(name);
    }

    Price getModelPrice() throws IllegalValueException {
        if (price == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }

        return new Price(price);
    }

    Duration getModelDuration() throws IllegalValueException {
        if (duration == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Duration.class.getSimpleName()));
        }

        return new Duration(duration);
    }

    /**
     * Converts this Jackson-friendly adapted service object into the model's {@code Service} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted customer.
     */
    public Service toModelType() throws IllegalValueException {
        final ServiceName modelName = this.getModelName();
        final Price modelPrice = this.getModelPrice();
        final Duration modelDuration = this.getModelDuration();
        return new Service(modelName, modelPrice, modelDuration);
    }

}
