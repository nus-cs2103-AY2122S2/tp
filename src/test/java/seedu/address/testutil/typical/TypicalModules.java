package seedu.address.testutil.typical;

import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_TUTORIAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.module.Module;
import seedu.address.testutil.module.ModuleBuilder;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {
    public static final Module CS2105 = new ModuleBuilder().withCode("CS2105").build();
    public static final Module CS2106 = new ModuleBuilder().withCode("CS2106").build();
    public static final Module CS2030 = new ModuleBuilder().withCode("CS2030").build();
    public static final Module CS2040 = new ModuleBuilder().withCode("CS2040").build();
    public static final Module CS2100 = new ModuleBuilder().withCode("CS2100").build();
    public static final Module CS1101S = new ModuleBuilder().withCode("CS1101S").build();

    // Manually added
    public static final Module PC1221 = new ModuleBuilder().withCode("PC1221").build();
    public static final Module CS2107 = new ModuleBuilder().withCode("CS2107").build();

    // Manually added - Module's details found in {@code CommandTestUtil}
    public static final Module CS2103 = new ModuleBuilder().withCode(VALID_MODULE_LECTURE).build();
    public static final Module CS2101 = new ModuleBuilder().withCode(VALID_MODULE_TUTORIAL).build();

    public static final String KEYWORD_MATCHING_CS2106 = "CS2106"; // A keyword that matches CS2106

    private TypicalModules() {
    } // prevents instantiation

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2101, CS2105, CS2106, CS2030, CS2040, CS2103, CS1101S, PC1221));
    }
}
