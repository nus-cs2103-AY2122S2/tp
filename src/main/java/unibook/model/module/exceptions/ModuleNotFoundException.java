package unibook.model.module.exceptions;

import unibook.model.module.ModuleCode;
import unibook.model.module.Module;

/**
 * Signals that a module does not exist in UniBook.
 */
public class ModuleNotFoundException extends RuntimeException {
    static final String MODULE_NOT_FOUND_MESSAGE_FORMAT = "Module %s not found in UniBook";
    public ModuleNotFoundException(String message) {
        super(message);
    }

    public ModuleNotFoundException(ModuleCode moduleCode) {
        super(String.format(MODULE_NOT_FOUND_MESSAGE_FORMAT, moduleCode.toString()));
    }

    public ModuleNotFoundException(Module module) {
        super(String.format(MODULE_NOT_FOUND_MESSAGE_FORMAT, module.getModuleCode().toString()));
    }
}

