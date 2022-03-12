package unibook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import unibook.logic.commands.exceptions.CommandException;
import unibook.logic.parser.CliSyntax;
import unibook.model.Model;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.ModuleName;
import unibook.model.person.Person;

/**
 * Adds a person or module to the UniBook.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student/professor/module to the UniBook. "
        + "Parameters: "
        + CliSyntax.PREFIX_OPTION + "OPTION "
        + CliSyntax.PREFIX_NAME + "NAME "
        + "[" + CliSyntax.PREFIX_PHONE + "PHONE] "
        + "[" + CliSyntax.PREFIX_EMAIL + "EMAIL] "
        + "[" + CliSyntax.PREFIX_TAG + "TAG]... "
        + "[" + CliSyntax.PREFIX_MODULE + "MODULE]...\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_OPTION + "student "
        + CliSyntax.PREFIX_NAME + "John Doe "
        + CliSyntax.PREFIX_PHONE + "98765432 "
        + CliSyntax.PREFIX_EMAIL + "johnd@example.com "
        + CliSyntax.PREFIX_TAG + "friends "
        + CliSyntax.PREFIX_TAG + "owesMoney "
        + CliSyntax.PREFIX_MODULE + "CS2103 "
        + CliSyntax.PREFIX_MODULE + "CS2105";

    public static final String MESSAGE_SUCCESS_PERSON = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the UniBook";

    public static final String MESSAGE_SUCCESS_MODULE = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the UniBook";

    public static final String MESSAGE_MODULE_DOES_NOT_EXIST = "One or more of the modules entered"
            + " does not exist in the UniBook";

    private Person personToAdd = new Person();
    private Module moduleToAdd = new Module();

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        personToAdd = person;
    }

    /**
     * Creates an AddCommand to add the specified {@code Module}
     */
    public AddCommand(ModuleName moduleName, ModuleCode moduleCode) {
        requireNonNull(moduleName);
        requireNonNull(moduleCode);
        Module module = new Module(moduleName, moduleCode);
        moduleToAdd = module;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (personToAdd.getName() == null) {
            if (model.hasModule(moduleToAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_MODULE);
            }

            model.addModule(moduleToAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS_MODULE, moduleToAdd));
        } else {
            if (model.hasPerson(personToAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            } else if (!model.isModuleExist(personToAdd)) {
                throw new CommandException(MESSAGE_MODULE_DOES_NOT_EXIST);
            }
            Set<ModuleCode> moduleCodes = personToAdd.getModuleCodes();
            Set<Module> modules = personToAdd.getModulesModifiable();
            for (ModuleCode moduleCode : moduleCodes) {
                modules.add(model.getModule(moduleCode));
            }

            model.addPerson(personToAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS_PERSON, personToAdd));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && personToAdd.equals(((AddCommand) other).personToAdd));
    }
}
