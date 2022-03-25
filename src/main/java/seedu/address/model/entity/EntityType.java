package seedu.address.model.entity;

/**
 * Enum class to indicate the different entities in TAssist.
 */
public enum EntityType {
    STUDENT("Student"),
    CLASS_GROUP("ClassGroup"),
    TA_MODULE("TaModule"),
    ASSESSMENT("Assessment");

    private final String entityClass;

    EntityType(String entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public String toString() {
        return entityClass;
    }
}
