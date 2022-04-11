package seedu.address.testutil;

import seedu.address.model.InternApplyMemory;
import seedu.address.model.application.Application;

/**
 * A utility class to help with building InternApplyMemory objects.
 * Example usage: <br>
 *     {@code InternApplyMemory iam = new InternApplyMemoryBuilder().withPerson("Shopee", "Garena").build();}
 */
public class InternApplyMemoryBuilder {

    private InternApplyMemory internApplyMemory;

    public InternApplyMemoryBuilder() {
        internApplyMemory = new InternApplyMemory();
    }

    public InternApplyMemoryBuilder(InternApplyMemory internApplyMemory) {
        this.internApplyMemory = internApplyMemory;
    }

    /**
     * Adds a new {@code Application} to the {@code InternApplyMemory} that we are building.
     */
    public InternApplyMemoryBuilder withApplication(Application application) {
        internApplyMemory.addApplication(application);
        return this;
    }

    public InternApplyMemory build() {
        return internApplyMemory;
    }
}
