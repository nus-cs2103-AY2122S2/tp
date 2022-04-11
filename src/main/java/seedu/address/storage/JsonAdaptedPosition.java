package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.position.Description;
import seedu.address.model.position.Position;
import seedu.address.model.position.PositionName;
import seedu.address.model.position.PositionOffers;
import seedu.address.model.position.PositionOpenings;
import seedu.address.model.position.Requirement;

public class JsonAdaptedPosition {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Position's %s field is missing!";

    private final String positionName;
    private final String description;
    private final String positionOpening;
    private final List<JsonAdaptedRequirement> requirements = new ArrayList<>();
    private final String positionOffers;

    /**
     * Constructs a {@code JsonAdaptedPosition} with the given position details.
     */
    @JsonCreator
    public JsonAdaptedPosition(@JsonProperty("positionName") String positionName,
            @JsonProperty("description") String description, @JsonProperty("positionOpening") String positionOpening,
            @JsonProperty("requirements") List<JsonAdaptedRequirement> requirements,
            @JsonProperty("positionOffers") String positionOffers) {
        this.positionName = positionName;
        this.description = description;
        this.positionOpening = positionOpening;
        this.positionOffers = positionOffers;

        if (requirements != null) {
            this.requirements.addAll(requirements);
        }
    }

    /**
     * Converts a given {@code Position} into this class for Jackson use.
     */
    public JsonAdaptedPosition(Position source) {
        positionName = source.getPositionName().positionName;
        description = source.getDescription().descriptionText;
        positionOpening = source.getPositionOpenings().toString();
        positionOffers = source.getPositionOffers().toString();

        requirements.addAll(source.getRequirements().stream()
                .map(JsonAdaptedRequirement::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted position object into the model's {@code Position} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted position.
     */
    public Position toModelType() throws IllegalValueException {
        final List<Requirement> positionRequirements = new ArrayList<>();
        for (JsonAdaptedRequirement requirement: requirements) {
            positionRequirements.add(requirement.toModelType());
        }
        if (positionName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PositionName.class.getSimpleName()));
        }
        if (!PositionName.isValidPositionName(positionName)) {
            throw new IllegalValueException(PositionName.MESSAGE_CONSTRAINTS);
        }
        final PositionName modelPositionName = new PositionName(positionName);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescriptionText(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (positionOpening == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PositionOpenings.class.getSimpleName()));
        }
        if (!PositionOpenings.isValidNumber(positionOpening)) {
            throw new IllegalValueException(PositionOpenings.MESSAGE_CONSTRAINTS);
        }
        final PositionOpenings modelPositionOpenings = new PositionOpenings(positionOpening);

        if (positionOffers == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PositionOffers.class.getSimpleName()));
        }
        final PositionOffers modelPositionOffers = new PositionOffers(positionOffers);

        final Set<Requirement> modelRequirement = new HashSet<>(positionRequirements);

        return new Position(modelPositionName, modelDescription, modelPositionOpenings, modelPositionOffers,
                modelRequirement);
    }
}
