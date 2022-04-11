package seedu.address.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.module.Module;
import seedu.address.testutil.module.ModuleBuilder;

public class AddModuleCommandTest {

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddModuleCommand(null));
    }

    @Test
    public void execute_moduleAcceptedByModel_addSuccessful() throws Exception {
        final ModelStubAcceptingModuleAdded modelStub = new ModelStubAcceptingModuleAdded();
        final Module validModule = new ModuleBuilder().build();

        final CommandResult commandResult = new AddModuleCommand(validModule).execute(modelStub);

        assertEquals(String.format(AddModuleCommand.MESSAGE_SUCCESS, validModule), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validModule), modelStub.modulesAdded);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        final Module validModule = new ModuleBuilder().build();
        final AddModuleCommand addModuleCommand = new AddModuleCommand(validModule);
        final ModelStubWithModule modelStub = new ModelStubWithModule(validModule);

        assertThrows(CommandException.class,
                AddModuleCommand.MESSAGE_DUPLICATE_MODULE, () -> addModuleCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        final Module cs2103 = new ModuleBuilder().withCode("CS2103").build();
        final Module cs2101 = new ModuleBuilder().withCode("CS2101").build();
        final AddModuleCommand addCS2103Command = new AddModuleCommand(cs2103);
        final AddModuleCommand addCS2101Command = new AddModuleCommand(cs2101);

        // same object -> returns true
        assertTrue(addCS2103Command.equals(addCS2103Command));

        // same values -> returns true
        final AddModuleCommand addCS2103CommandCopy = new AddModuleCommand(cs2103);
        assertTrue(addCS2103Command.equals(addCS2103CommandCopy));

        // different types -> returns false
        assertFalse(addCS2103Command.equals(1));

        // null -> returns false
        assertFalse(addCS2103CommandCopy.equals(null));

        // different meeting -> returns false
        assertFalse(addCS2103Command.equals(addCS2101Command));
    }

    /**
     * A Model stub that contains a single module.
     */
    private class ModelStubWithModule extends ModelStub {
        private final Module module;

        ModelStubWithModule(Module module) {
            requireNonNull(module);
            this.module = module;
        }

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return this.module.equals(module);
        }
    }

    /**
     * A Model stub that always accept the Module being added.
     */
    private class ModelStubAcceptingModuleAdded extends ModelStub {
        final ArrayList<Module> modulesAdded = new ArrayList<>();

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return modulesAdded.stream().anyMatch(module::equals);
        }

        @Override
        public void addModule(Module module) {
            requireNonNull(module);
            modulesAdded.add(module);
        }
    }

}
