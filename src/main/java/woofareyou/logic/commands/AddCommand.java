package woofareyou.logic.commands;

import static java.util.Objects.requireNonNull;
import static woofareyou.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static woofareyou.logic.parser.CliSyntax.PREFIX_NAME;
import static woofareyou.logic.parser.CliSyntax.PREFIX_OWNER_NAME;
import static woofareyou.logic.parser.CliSyntax.PREFIX_PHONE;
import static woofareyou.logic.parser.CliSyntax.PREFIX_TAG;

import woofareyou.logic.commands.exceptions.CommandException;
import woofareyou.model.Model;
import woofareyou.model.pet.Pet;

/**
 * Adds a pet to WoofAreYou.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a pet to WoofAreYou database."
            + "Parameters: "
            + PREFIX_NAME + "NAME_OF_PET "
            + PREFIX_OWNER_NAME + "OWNER_NAME "
            + PREFIX_PHONE + "PHONE_NUMBER "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "BREED] ... \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Pee Pee "
            + PREFIX_OWNER_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "Mini Beagle ";

    public static final String MESSAGE_SUCCESS = "New pet added: %1$s";
    public static final String MESSAGE_DUPLICATE_PET = "This pet already exists in WoofAreYou!";

    private final Pet toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Pet}
     */
    public AddCommand(Pet pet) {
        requireNonNull(pet);
        toAdd = pet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPet(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PET);
        }

        model.addPet(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
