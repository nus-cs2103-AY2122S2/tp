package unibook.testutil.typicalclasses;

import unibook.model.module.Module;
import unibook.testutil.builders.ModuleBuilder;

public class TypicalModules {
    public static final Module CS1231S = new ModuleBuilder().withModuleName("Discrete Structures")
        .withModuleCode("CS1231S").build();
    public static final Module CS2102 = new ModuleBuilder().withModuleName("Database Systems")
        .withModuleCode("CS2102").build();
}
