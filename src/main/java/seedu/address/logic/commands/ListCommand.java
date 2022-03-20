package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.entity.EntityType;
import seedu.address.model.entity.exceptions.UnknownEntityException;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all %s";
    public static final String MESSAGE_STUDENTS = "students";
    public static final String MESSAGE_MODULES = "modules";
    public static final String MESSAGE_CLASS_GROUPS = "class groups";

    private EntityType entityType;
    private Optional<EntityType> filterEntityType;
    private Optional<Index> filterIndex;

    public ListCommand(EntityType entityType, Optional<EntityType> filterEntityType, Optional<Index> filterIndex) {
        this.entityType = entityType;
        this.filterEntityType = filterEntityType;
        this.filterIndex = filterIndex;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String result = MESSAGE_SUCCESS;
        switch(entityType) {
        case STUDENT:
            model.updateFilteredStudentList(PREDICATE_SHOW_ALL);
            result = String.format(result, MESSAGE_STUDENTS);
            break;
        case TA_MODULE:
            model.updateFilteredModuleList(PREDICATE_SHOW_ALL);
            result = String.format(result, MESSAGE_MODULES);
            break;
        case CLASS_GROUP:
            model.updateFilteredClassGroupList(PREDICATE_SHOW_ALL);
            result = String.format(result, MESSAGE_CLASS_GROUPS);
            break;
        default:
            throw new UnknownEntityException();
        }
        return new CommandResult(result, entityType);
    }
}
