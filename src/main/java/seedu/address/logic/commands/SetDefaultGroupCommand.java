package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD;

import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Mod;




public class SetDefaultGroupCommand extends Command {

    public static final String COMMAND_WORD = "set-default-group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a default value for group in the given Mod. \n"
            + "Parameters: "
            + PREFIX_MOD + "MOD "
            + PREFIX_GROUP + "GROUP \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP + "W12-1 "
            + PREFIX_MOD + "CS2103T";

    public static final String MESSAGE_SUCCESS = "Default value for %1$s Module has been set to %2$s successfully!";
    public static final String MESSAGE_DEFAULT_UPDATE = "Default value for %1$s has been updated from %2$s to %3$s.";
    public static final String MESSAGE_SAME_UPDATE = "Default value provided for %1$s is %2$s which is "
            + "the same as before.";
    private static final String DEFAULT_GROUP = "T01";

    private static final Logger logger = LogsCenter.getLogger(SetDefaultGroupCommand.class);


    private final Mod mod;
    private final String defaultValue;

    /**
     * Constructor for {@code SetDefaultGroupCommand}.
     * @param mod The module whose default has to be set.
     * @param defaultValue The default value.
     */
    public SetDefaultGroupCommand(Mod mod, String defaultValue) {
        this.mod = mod;
        this.defaultValue = defaultValue;
    }

    /**
     * Constructor for {@code SetDefaultGroupCommand}.
     * @param mod The module whose default has to be set.
     */
    public SetDefaultGroupCommand(Mod mod) {
        requireNonNull(mod);
        this.mod = mod;
        this.defaultValue = DEFAULT_GROUP;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.doesModExistInList(mod)) {
            Optional<Mod> matchingMod = model.getMod(mod);
            assert matchingMod != null;
            if (model.isDefaultGroupOfModPresent(matchingMod.get())) {
                String prevDefault = model.retrievePrevDefault(matchingMod.get());
                if (!prevDefault.equals(defaultValue)) {
                    model.setDefaultGroup(matchingMod.get(), defaultValue);
                    model.commitContent();
                    return new CommandResult(String.format(MESSAGE_DEFAULT_UPDATE, mod, prevDefault, defaultValue));
                } else {
                    return new CommandResult(String.format(MESSAGE_SAME_UPDATE, mod, prevDefault));
                }
            }
        } else {
            model.addMod(mod);
            model.setDefaultGroup(mod, defaultValue);
            model.commitContent();
        }


        return new CommandResult(String.format(MESSAGE_SUCCESS, mod, defaultValue));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetDefaultGroupCommand) // instanceof handles nulls
                && mod.equals(((SetDefaultGroupCommand) other).mod)
                && defaultValue.equals(((SetDefaultGroupCommand) other).defaultValue);
    }
}

