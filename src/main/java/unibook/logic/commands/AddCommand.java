package unibook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
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

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Adds a student/professor/module to the UniBook, depending on the option specified.\n"
        + "Parameters: "
        + CliSyntax.PREFIX_OPTION + "OPTION "
        + CliSyntax.PREFIX_NAME + "NAME "
        + "[" + CliSyntax.PREFIX_PHONE + "PHONE] "
        + "[" + CliSyntax.PREFIX_EMAIL + "EMAIL] "
        + "[" + CliSyntax.PREFIX_OFFICE + "OFFICE] "
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
        + CliSyntax.PREFIX_MODULE + "CS2105\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_OPTION + "professor "
        + CliSyntax.PREFIX_NAME + "Mary Jane "
        + CliSyntax.PREFIX_PHONE + "98765412 "
        + CliSyntax.PREFIX_EMAIL + "maryj@example.com "
        + CliSyntax.PREFIX_OFFICE + "COM2 01-02 "
        + CliSyntax.PREFIX_TAG + "strict "
        + CliSyntax.PREFIX_TAG + "dean "
        + CliSyntax.PREFIX_MODULE + "CS2103 "
        + CliSyntax.PREFIX_MODULE + "CS2105\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_OPTION + "module "
        + CliSyntax.PREFIX_NAME + "Computer Organisation "
        + CliSyntax.PREFIX_MODULE + "CS2100\n";
    public static final String MESSAGE_USAGE_STUDENT = COMMAND_WORD
        + ": To add a student to the UniBook, use the following format.\n"
        + "Parameters: "
        + CliSyntax.PREFIX_OPTION + "OPTION "
        + CliSyntax.PREFIX_NAME + "NAME "
        + CliSyntax.PREFIX_PHONE + "PHONE "
        + CliSyntax.PREFIX_EMAIL + "EMAIL "
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
        + CliSyntax.PREFIX_MODULE + "CS2105\n";
    public static final String MESSAGE_USAGE_MODULE = COMMAND_WORD
        + ": To add a module to the UniBook, use the following format.\n"
        + "Parameters: "
        + CliSyntax.PREFIX_OPTION + "OPTION "
        + CliSyntax.PREFIX_NAME + "NAME "
        + CliSyntax.PREFIX_MODULE + "MODULE\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_OPTION + "module "
        + CliSyntax.PREFIX_NAME + "Computer Organisation "
        + CliSyntax.PREFIX_MODULE + "CS2100\n";
    public static final String MESSAGE_USAGE_PROFESSOR = COMMAND_WORD
        + ": To add a professor to the UniBook, use the following format.\n"
        + "Parameters: "
        + CliSyntax.PREFIX_OPTION + "OPTION "
        + CliSyntax.PREFIX_NAME + "NAME "
        + CliSyntax.PREFIX_PHONE + "PHONE "
        + CliSyntax.PREFIX_EMAIL + "EMAIL "
        + CliSyntax.PREFIX_OFFICE + "OFFICE "
        + "[" + CliSyntax.PREFIX_TAG + "TAG]... "
        + "[" + CliSyntax.PREFIX_MODULE + "MODULE]...\n"
        + "Example: " + COMMAND_WORD + " "
        + CliSyntax.PREFIX_OPTION + "professor "
        + CliSyntax.PREFIX_NAME + "Mary Jane "
        + CliSyntax.PREFIX_PHONE + "98765412 "
        + CliSyntax.PREFIX_EMAIL + "maryj@example.com "
        + CliSyntax.PREFIX_OFFICE + "COM2 01-02 "
        + CliSyntax.PREFIX_TAG + "strict "
        + CliSyntax.PREFIX_TAG + "dean "
        + CliSyntax.PREFIX_MODULE + "CS2103 "
        + CliSyntax.PREFIX_MODULE + "CS2105\n";

    public static final String MESSAGE_SUCCESS_PERSON = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the UniBook";

    public static final String MESSAGE_SUCCESS_MODULE = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the UniBook";

    public static final String MESSAGE_MODULE_DOES_NOT_EXIST = "One or more of the modules entered"
        + " does not exist in the UniBook";

    private Person personToAdd;
    private Module moduleToAdd = new Module();
    private Set<ModuleCode> moduleCodeSet = new HashSet<>();

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        personToAdd = person;
    }

    /**
     * Creates an AddCommand to add the specified {@code Person} with {@code moduleCodes}
     */
    public AddCommand(Person person, Set<ModuleCode> moduleCodes) {
        requireNonNull(person);
        personToAdd = person;
        moduleCodeSet = moduleCodes;
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
    public CommandResult execute(Model model, Boolean isPersonListShowing,
                                 Boolean isModuleListShowing) throws CommandException {
        requireNonNull(model);

        if (personToAdd.getName() == null) {
            if (model.hasModule(moduleToAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_MODULE);
            }

            model.addModule(moduleToAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS_MODULE, moduleToAdd));
        } else {
            /*
            if (model.hasPerson(personToAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            } else if (!model.isModuleExist(moduleCodeSet)) {
                throw new CommandException(MESSAGE_MODULE_DOES_NOT_EXIST);
            } /* TODO need to change this, dont store module codes inside person,
                 instead just go through their codes straight from the parsing,
                 and add modules from there. I've removed module codes from person
                 already
             *//*
            Set<ModuleCode> personModuleCodes = personToAdd.getModuleCodes();
            Set<Module> personModules = personToAdd.getModulesModifiable();
            for (ModuleCode moduleCode : moduleCodeSet) {
                Module toAdd = model.getModuleByCode(moduleCode);
                personModules.add(toAdd);
            }
            model.addPersonToTheirModules(personToAdd);
            model.addPerson(personToAdd); */
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

