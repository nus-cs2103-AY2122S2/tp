package seedu.trackbeau.logic.commands.service;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.model.Model.PREDICATE_SHOW_ALL_SERVICES;

import seedu.trackbeau.logic.commands.Command;
import seedu.trackbeau.logic.commands.CommandResult;
import seedu.trackbeau.model.Model;


/**
 * Lists all services in trackBeau to the user.
 */
public class ListServicesCommand extends Command {

    public static final String COMMAND_WORD = "lists";

    public static final String MESSAGE_SUCCESS = "Listed all services";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateServiceList(PREDICATE_SHOW_ALL_SERVICES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
