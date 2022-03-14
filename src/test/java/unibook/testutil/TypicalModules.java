package unibook.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import unibook.model.UniBook;
import unibook.model.module.Module;

public class TypicalModules {

    public static final Module CS2103 = new ModuleBuilder()
            .withModuleName("Introduction to Software Engineering")
            .withModuleCode("CS2103")
            .withProfessor("Damith").build();
    public static final Module CS2105 = new ModuleBuilder()
            .withModuleName("Introduction to Computer Networks")
            .withModuleCode("CS2105")
            .withProfessor("Richard Ma TianBai").build();
    public static final Module CS1231S = new ModuleBuilder()
            .withModuleName("Discrete Structures")
            .withModuleCode("CS1231S")
            .withProfessor("Aaron Tan Tuck Choy").build();
    public static final Module CS2100 = new ModuleBuilder()
            .withModuleName("Computer Organisation")
            .withModuleCode("CS2100")
            .withProfessor("Adi Yoga").build();

    public static final int FIRST_MODULE = 0;
    public static final int SECOND_MODULE = 1;
    public static final int THIRD_MODULE = 2;
    public static final int FOURTH_MODULE = 3;

    private TypicalModules() {
    } // prevents instantiation

    /**
     * Returns an {@code UniBook} with all the typical modules.
     */
    public static UniBook getTypicalUniBook() {
        UniBook ab = new UniBook();
        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }
        return ab;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2103, CS2105, CS1231S, CS2100));
    }

}
