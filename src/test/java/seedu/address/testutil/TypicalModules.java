package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.tamodule.TaModule;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalModules {
    public static final TaModule CS2101 = new ModuleBuilder().withModuleName("Effectiv Communication")
            .withModuleCode("CS2101").withAcademicYer("2022").build();

    private TypicalModules() {} // prevents instantiation

    public static List<TaModule> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2101));
    }
}
