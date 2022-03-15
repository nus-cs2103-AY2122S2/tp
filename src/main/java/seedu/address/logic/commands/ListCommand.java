package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;

import seedu.address.model.Model;
import seedu.address.model.entity.EntityType;
import seedu.address.model.entity.exceptions.UnknownEntityException;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all students/modules/class groups";

    private EntityType entityType;

    public ListCommand(EntityType entityType) {
        this.entityType = entityType;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        switch(entityType) {
        case STUDENT:
            model.updateFilteredStudentList(PREDICATE_SHOW_ALL);
            break;
        case TA_MODULE:
            model.updateFilteredModuleList(PREDICATE_SHOW_ALL);
            break;
        case CLASS_GROUP:
            model.updateFilteredClassGroupList(PREDICATE_SHOW_ALL);
            break;
        default:
            throw new UnknownEntityException();
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
