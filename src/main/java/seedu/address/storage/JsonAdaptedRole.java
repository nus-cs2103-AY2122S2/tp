package seedu.address.storage;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.role.Deadline;
import seedu.address.model.role.Description;
import seedu.address.model.role.Role;
import seedu.address.model.role.RoleName;
import seedu.address.model.role.Status;
import seedu.address.model.role.Stipend;

public class JsonAdaptedRole {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Role's %s field is missing!";

    public static final DateTimeFormatter VALIDATION_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private final String name;
    private final String status;
    private final String deadline;
    private final String description;
    private final String stipend;

    /**
     * Constructs a {@code JsonAdaptedRole} with the given fields.
     */
    @JsonCreator
    public JsonAdaptedRole(@JsonProperty("name") String name, @JsonProperty("status") String status,
                           @JsonProperty("deadline") String deadline,
                           @JsonProperty("description") String description,
                           @JsonProperty("stipend") String stipend) {
        this.name = name;
        this.status = status;
        this.deadline = deadline;
        this.description = description;
        this.stipend = stipend;
    }

    /**
     * Converts a given {@code Role} into this class for Jackson use.
     */
    public JsonAdaptedRole(Role source) {
        name = source.getName().fullName;
        status = source.getStatus().value;
        deadline = source.getDeadline().value.format(VALIDATION_FORMATTER);
        description = source.getDescription().value;
        stipend = source.getStipend().value;
    }

    //    @JsonValue
    //    public String getRoleName() {
    //        return name;
    //    }
    //
    //    @JsonValue
    //    public String getRoleStatus() {
    //        return status;
    //    }
    //
    //    @JsonValue
    //    public String getRoleDeadline() {
    //        return deadline;
    //    }
    //
    //    @JsonValue
    //    public String getRoleDescription() {
    //        return description;
    //    }
    //
    //    @JsonValue
    //    public String getRoleStipend() {
    //        return stipend;
    //    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Role} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted
     * role.
     */
    public Role toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    RoleName.class.getSimpleName()));
        }
        if (!RoleName.isValidName(name)) {
            throw new IllegalValueException(RoleName.MESSAGE_CONSTRAINTS);
        }
        final RoleName modelRoleName = new RoleName(name);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status roleStatus = new Status(status);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline roleDeadline = new Deadline(deadline);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description roleDescription = new Description(description);

        if (stipend == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Stipend.class.getSimpleName()));
        }
        if (!Stipend.isValidStipend(stipend)) {
            throw new IllegalValueException(Stipend.MESSAGE_CONSTRAINTS);
        }
        final Stipend roleStipend = new Stipend(stipend);

        return new Role(modelRoleName, roleStatus, roleDeadline, roleDescription, roleStipend);
    }

}
