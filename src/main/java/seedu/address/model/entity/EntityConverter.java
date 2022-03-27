package seedu.address.model.entity;

import seedu.address.model.assessment.Assessment;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.entity.exceptions.InvalidEntityConversionException;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;

/**
 * The API of the Model component.
 */
public class EntityConverter {

    /**
     * Converts the entity to a {@code Student} class.
     *
     * @param entity The entity to convert.
     * @return The {@code Student} class that the entity represent.
     */
    public static Student entityToStudent(Entity entity) {
        if (entity.getEntityType() != EntityType.STUDENT) {
            throw new InvalidEntityConversionException(EntityType.STUDENT);
        }
        return (Student) entity;
    }

    /**
     * Converts the entity to a {@code TaModule} class.
     *
     * @param entity The entity to convert.
     * @return The {@code TaModule} class that the entity represent.
     */
    public static TaModule entityToTaModule(Entity entity) {
        if (entity.getEntityType() != EntityType.TA_MODULE) {
            throw new InvalidEntityConversionException(EntityType.TA_MODULE);
        }
        return (TaModule) entity;
    }

    /**
     * Converts the entity to a {@code ClassGroup} class.
     *
     * @param entity The entity to convert.
     * @return The {@code ClassGroup} class that the entity represent.
     */
    public static ClassGroup entityToClassGroup(Entity entity) {
        if (entity.getEntityType() != EntityType.CLASS_GROUP) {
            throw new InvalidEntityConversionException(EntityType.CLASS_GROUP);
        }
        return (ClassGroup) entity;
    }

    /**
     * Converts the entity to an {@code Assessment} class.
     *
     * @param entity The entity to convert.
     * @return The {@code Assessment} class that the entity represent.
     */
    public static Assessment entityToAssessment(Entity entity) {
        if (entity.getEntityType() != EntityType.ASSESSMENT) {
            throw new InvalidEntityConversionException(EntityType.ASSESSMENT);
        }
        return (Assessment) entity;
    }
}
