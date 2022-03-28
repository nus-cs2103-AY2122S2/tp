package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityType;
import seedu.address.model.entity.exceptions.UnknownEntityException;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;

//@@author EvaderFati
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all %s";
    public static final String MESSAGE_STUDENTS = "students";
    public static final String MESSAGE_MODULES = "modules";
    public static final String MESSAGE_CLASS_GROUPS = "class groups";
    public static final String MESSAGE_ASSESSMENTS = "assessments";

    private EntityType entityType;
    private Optional<EntityType> filterEntityType;
    private Optional<Index> filterEntityIndex;

    /**
     * Creates a ListCommand to list the specified entity, Optionally filtering by other entities
     */
    public ListCommand(EntityType entityType, Optional<Entity Type> filterEntityType,
                       Optional<Index> filterEntityIndex) {
        this.entityType = entityType;
        this.filterEntityType = filterEntityType;
        this.filterEntityIndex = filterEntityIndex;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String result = MESSAGE_SUCCESS;
        switch(entityType) {
        case STUDENT:
            updateStudentList(model);
            result = String.format(result, MESSAGE_STUDENTS);
            break;
        case TA_MODULE:
            result = String.format(result, MESSAGE_MODULES);
            break;
        case CLASS_GROUP:
            updateClassGroupList(model);
            result = String.format(result, MESSAGE_CLASS_GROUPS);
            break;
        case ASSESSMENT:
            updateFilteredAssessmentList(model);
            result = String.format(result, MESSAGE_ASSESSMENTS);
            break;
        default:
            throw new UnknownEntityException();
        }
        return new CommandResult(result, entityType);
    }

    private void updateStudentList(Model model) {
        if (filterEntityType.get().equals(EntityType.CLASS_GROUP)) {
            Predicate<Student> filter = filterStudentsByClassGroup(model);
            model.updateFilteredStudentList(filter);
        } else if (filterEntityType.get().equals(EntityType.TA_MODULE)) {
            Predicate<Student> filter = filterStudentsByModule(model);
            model.updateFilteredStudentList(filter);
        } else {
            model.updateFilteredStudentList(PREDICATE_SHOW_ALL);
        }
    }

    private void updateClassGroupList(Model model) {
        if (filterEntityType.get().equals(EntityType.TA_MODULE)) {
            Predicate<ClassGroup> filter = filterClassGroupsByModule(model);
            model.updateFilteredClassGroupList(filter);
        } else {
            model.updateFilteredClassGroupList(PREDICATE_SHOW_ALL);
        }
    }

    private void updateModuleList(Model model) {
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL);
    }

    private void updateFilteredAssessmentList(Model model) {
        model.updateFilteredAssessmentList(PREDICATE_SHOW_ALL);
    }

    private Predicate<Student> filterStudentsByClassGroup(Model model) {
        return (Student student) -> {
            ClassGroup classGroup = model.getUnfilteredClassGroupList().get(filterEntityIndex);
            if (classGroup.hasStudent(student)) {
                return true;
            }
            return false;
        };
    }

    private Predicate<Student> filterStudentsByModule(Model model) {
        return (Student student) -> {
            TaModule module = model.getUnfilteredModuleList().get(filterEntityIndex);
            if (module.hasStudent(student)) {
                return true;
            }
            return false;
        };
    }

    private Predicate<ClassGroup> filterClassGroupsByModule(Model model) {
        return (ClassGroup classGroup) -> {
            TaModule module = model.getUnfilteredModuleList().get(filterEntityIndex);
            if (module.equals(classGroup.getModule())) {
                return true;
            }
            return false;
        };
    }

}
