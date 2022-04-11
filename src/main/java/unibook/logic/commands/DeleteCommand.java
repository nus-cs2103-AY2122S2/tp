package unibook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import unibook.commons.core.Messages;
import unibook.commons.core.index.Index;
import unibook.logic.commands.exceptions.CommandException;
import unibook.logic.parser.CliSyntax;
import unibook.model.Model;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.ModuleKeyEvent;
import unibook.model.module.group.Group;
import unibook.model.person.Person;
import unibook.model.person.Professor;
import unibook.model.person.Student;
import unibook.model.tag.Tag;

/**
 * Deletes a person identified using it's displayed index from the unibook.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ":\n\n"
        + "All Pages: \n"
        + COMMAND_WORD + String.format(" [index] (Delete person or module or group at that index)\n")
        + COMMAND_WORD + String.format(" %smodule %s[modulecode] (Delete module that matches the code)\n",
            CliSyntax.PREFIX_OPTION, CliSyntax.PREFIX_MODULE)
        + COMMAND_WORD + String.format(" %sgroup %s[modulecode] %s[groupname] "
        + "(Delete the group that is associated with this module code)\n\n",
            CliSyntax.PREFIX_OPTION, CliSyntax.PREFIX_MODULE, CliSyntax.PREFIX_GROUP)
        + "Person Page: \n"
        + COMMAND_WORD + String.format(" [index] %s %s %s[tag] %s "
        + "(Delete attributes from person on person page only)\n\n",
            CliSyntax.PREFIX_PHONE, CliSyntax.PREFIX_EMAIL, CliSyntax.PREFIX_TAG, CliSyntax.PREFIX_OFFICE)
        + "Module Page: \n"
        + COMMAND_WORD + String.format(" [index1] %s[index2] (Delete professor at index2 from module at index1)\n",
            CliSyntax.PREFIX_PROF_INDEX)
        + COMMAND_WORD + String.format(" [index1] %s[index2] (Delete student at index2 from module at index1)\n",
            CliSyntax.PREFIX_STU_INDEX)
        + COMMAND_WORD + String.format(" [index] %s[groupname] (Delete group from module at index)\n",
            CliSyntax.PREFIX_GROUP)
        + COMMAND_WORD + String.format(" [index1] %s[index2] (Delete key event at index2 from module at index1)\n\n",
            CliSyntax.PREFIX_KEYEVENT)
        + "Group page: \n"
        + COMMAND_WORD + String.format(" [index1] %s[index2] (Delete student at index2 from group at index1)\n",
            CliSyntax.PREFIX_STU_INDEX)
        + COMMAND_WORD + String.format(" [index1] %s[index2] (Delete meeting time at index2 from group at index1)\n",
            CliSyntax.PREFIX_MEETING_TIME);

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Successfully deleted Person: %1$s";
    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Successfully deleted Module: %1$s";
    public static final String MESSAGE_DELETE_MODULE_AND_PERSON_SUCCESS = "Deleted Module: %1$s, and all Persons";
    public static final String MESSAGE_DELETE_UNSUCCESSFUL = "Delete Unsuccessful";
    public static final String MESSAGE_DELETE_GROUP_SUCCESS = "Successfully deleted Group: %1$s from Module: %2$s";
    public static final String MESSAGE_DELETE_TRAITS_SUCCESS = "Successfully deleted the following traits:\n";
    public static final String PHONE = "Phone: ";
    public static final String EMAIL = "Email: ";
    public static final String TAG = "Tag: ";
    public static final String OFFICE = "Office: ";
    public static final String MESSAGE_DELETE_PROF_FROM_MODULE_SUCCESS = "Successfully deleted Prof: %1$s "
            + "from Module: %2$s";
    public static final String MESSAGE_DELETE_STUDENT_FROM_MODULE_SUCCESS = "Successfully deleted Student: %1$s "
            + "from Module: %2$s";
    public static final String MESSAGE_DELETE_STUDENT_FROM_GROUP_SUCCESS = "Successfully deleted Student: %1$s "
            + "from Group: %2$s";
    public static final String MESSAGE_DELETE_GROUP_FROM_MODULE_SUCCESS = "Successfully deleted Group: %1$s "
            + "from Module: %2$s";
    public static final String MESSAGE_DELETE_MEETING_FROM_GROUP_SUCCESS = "Successfully deleted Meeting: %1$s "
            + "from Group: %2$s";
    public static final String MESSAGE_DELETE_KEY_EVENT_FROM_MODULE_SUCCESS = "Successfully deleted Key Event: %1$s "
            + "from Module: %2$s";
    public static final String MESSAGE_CANNOT_REMOVE_TRAIT = "Cannot remove these traits as there will be a "
            + "duplicate person";

    private Index targetIndex;
    private boolean indexOnly = false;
    private ModuleCode moduleCode;
    private Group group;
    private boolean phone;
    private boolean email;
    private String tag;
    private boolean office;
    private Index profIndex;
    private Index stuIndex;
    private Index meetingTimeIndex;
    private Index keyEventIndex;

    /**
     * Creates a Delete Command Object that will delete a person at targetIndex.
     *
     * @param targetIndex Index object that describes the person at that index to delete
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.indexOnly = true;
    }

    /**
     * Creates a Delete Command Object that will delete a module that has moduleCode
     *
     * @param moduleCode
     */
    public DeleteCommand(ModuleCode moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Creates a Delete Command Object that will delete a group in the module that
     * matches module code provided
     *
     * @param moduleCode
     */
    public DeleteCommand(ModuleCode moduleCode, Group group) {
        this.moduleCode = moduleCode;
        this.group = group;
    }

    /**
     * Creates a Delete Command Object that will delete person attributes such as phone, email, tag, office
     *
     * @param targetIndex
     * @param phone
     * @param email
     * @param tag
     * @param office
     */
    public DeleteCommand(Index targetIndex, boolean phone, boolean email, String tag, boolean office) {
        this.targetIndex = targetIndex;
        this.phone = phone;
        this.email = email;
        this.tag = tag;
        this.office = office;
    }

    /**
     * Creates a Delete Command that involves 2 Index
     *
     * @param index
     * @param stuIndex
     * @param profIndex
     * @param meetingTimeIndex
     * @param keyEventIndex
     */
    public DeleteCommand(Index index, Index stuIndex, Index profIndex, Index meetingTimeIndex, Index keyEventIndex) {
        this.targetIndex = index;
        this.stuIndex = stuIndex;
        this.profIndex = profIndex;
        this.meetingTimeIndex = meetingTimeIndex;
        this.keyEventIndex = keyEventIndex;
    }

    /**
     * Creates a Delete Command that deletes a group
     *
     * @param index
     * @param group
     */
    public DeleteCommand(Index index, Group group) {
        this.targetIndex = index;
        this.group = group;
    }

    @Override
    public CommandResult execute(Model model,
                                 Boolean isPersonListShowing,
                                 Boolean isModuleListShowing, Boolean isGroupListShowing) throws CommandException {
        requireNonNull(model);

        // delete person by index case

        if (indexOnly) {

            // if not on person page, throw exception telling user to change pages
            if (isPersonListShowing) {

                List<Person> lastShownPersonList = model.getFilteredPersonList();

                if (targetIndex.getZeroBased() >= lastShownPersonList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
                }

                Person personToDelete = lastShownPersonList.get(targetIndex.getZeroBased());

                // Bi-directionality
                model.deletePerson(personToDelete);
                // delete person from UniquePersonList
                // delete person from each module in module list if exists

                // delete person from each group they are in
                if (personToDelete instanceof Student) {
                    Set<Group> groupToLeave = ((Student) personToDelete).getGroups();
                    for (Group g : groupToLeave) {
                        g.removeMember((Student) personToDelete);
                    }
                }

                return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));

            } else if (isModuleListShowing) {

                List<Module> lastShownModuleList = model.getFilteredModuleList();

                if (targetIndex.getZeroBased() >= lastShownModuleList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
                }

                Module moduleToDelete = lastShownModuleList.get(targetIndex.getZeroBased());

                // Bi-directionality
                model.deleteModule(moduleToDelete); // delete person from UniquePersonList

                return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete.getModuleCode()));

            } else if (isGroupListShowing) {

                List<Group> lastShownGroupList = model.getShowingGroupList();

                if (targetIndex.getZeroBased() >= lastShownGroupList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
                }

                Group groupToDelete = lastShownGroupList.get(targetIndex.getZeroBased());
                Module moduleThatHasThisGroup = groupToDelete.getModule();
                model.removeGroup(moduleThatHasThisGroup.getModuleCode(), groupToDelete);
                lastShownGroupList.remove(targetIndex.getZeroBased());


                return new CommandResult(String.format(MESSAGE_DELETE_GROUP_SUCCESS,
                        groupToDelete.getGroupName(), groupToDelete.getModule().getModuleCode()));

            }



            // delete module by code case
        } else if (moduleCode != null && group == null) {

            // check if module code is valid
            if (!model.hasModule(moduleCode)) {
                throw new CommandException(String.format(Messages.MESSAGE_MODULE_CODE_NOT_EXIST, moduleCode));
            }

            // Bi-directionality
            model.deleteByModuleCode(moduleCode); // delete module from ModuleList and remove module from all persons

            return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleCode));


            // delete group case
        } else if (moduleCode != null && group != null) {

            // check if module code is valid
            if (!model.hasModule(moduleCode)) {
                throw new CommandException(String.format(Messages.MESSAGE_MODULE_CODE_NOT_EXIST, moduleCode));
            }

            // check if group exist
            if (!model.hasModuleAndGroup(moduleCode, group)) {
                throw new CommandException(String.format(Messages.MESSAGE_GROUP_NOT_EXIST, group.getGroupName()));
            }

            // module and group exists
            Group removedGroup = model.removeGroup(moduleCode, group);
            if (isGroupListShowing) {
                List<Group> lastShownGroupList = model.getShowingGroupList();
                lastShownGroupList.remove(removedGroup);
            }
            return new CommandResult(String.format(MESSAGE_DELETE_GROUP_SUCCESS,
                    removedGroup, removedGroup.getModule().getModuleCode()));

        } else if (phone || email || tag != null || office) { // delete person attributes

            if (!isPersonListShowing) {
                throw new CommandException(Messages.MESSAGE_CHANGE_TO_PERSON_VIEW);
            }

            List<Person> lastShownPersonList = model.getFilteredPersonList();

            if (targetIndex.getZeroBased() >= lastShownPersonList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDeleteTrait = lastShownPersonList.get(targetIndex.getZeroBased());
            Person newPerson = personToDeleteTrait;
            String phoneDeleted = null;
            String emailDeleted = null;
            String tagDeleted = null;
            String officeDeleted = null;

            if (phone) {
                String oldPhone = newPerson.getPhone().toString();
                phoneDeleted = oldPhone.equals("") ? "None" : oldPhone;
                newPerson = newPerson.deletePhone();
            }
            if (email) {
                String oldEmail = newPerson.getEmail().toString();
                emailDeleted = oldEmail.equals("") ? "None" : oldEmail;
                newPerson = newPerson.deleteEmail();
            }
            if (tag != null) {
                Tag oldTag = newPerson.getTag(tag);
                tagDeleted = oldTag == null ? "Tag not found" : newPerson.getTag(tag).tagName;
                newPerson = newPerson.deleteTag(tag);
            }
            if (personToDeleteTrait instanceof Professor && office) {
                String oldOffice = ((Professor) newPerson).getOffice().toString();
                officeDeleted = oldOffice.equals("") ? "None" : oldOffice;
                newPerson = ((Professor) newPerson).deleteOffice();
            }

            personToDeleteTrait.removePersonFromAllTheirModules();
            model.deletePerson(personToDeleteTrait);

            newPerson.addPersonToAllTheirModules();
            if (personToDeleteTrait instanceof Student) {
                Student studentToDeleteTrait = (Student) personToDeleteTrait;
                studentToDeleteTrait.removeStudentFromAllTheirGroups();
                Student newStudent = (Student) newPerson;
                newStudent.addStudentToAllTheirGroups();
            }
            model.addPerson(newPerson);

            // message can be improved if have time
            String phoneString = phoneDeleted != null ? PHONE + phoneDeleted + "\n" : "";
            String emailString = emailDeleted != null ? EMAIL + emailDeleted + "\n" : "";
            String tagString = tagDeleted != null ? TAG + tagDeleted + "\n" : "";
            String officeString = officeDeleted != null ? OFFICE + officeDeleted + "\n" : "";
            return new CommandResult(MESSAGE_DELETE_TRAITS_SUCCESS + phoneString
                    + emailString + tagString + officeString + "from " + personToDeleteTrait.getName());

        } else if (targetIndex != null && profIndex != null) { // delete prof from module

            if (!isModuleListShowing) {
                throw new CommandException(Messages.MESSAGE_CHANGE_TO_MODULE_VIEW);
            }

            List<Module> lastShownModuleList = model.getFilteredModuleList();

            if (targetIndex.getZeroBased() >= lastShownModuleList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
            }

            Module moduleToDeleteProf = lastShownModuleList.get(targetIndex.getZeroBased());
            List<Professor> lastShownProfList = moduleToDeleteProf.getProfessors();

            if (profIndex.getZeroBased() >= lastShownProfList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PROF_DISPLAYED_INDEX);
            }

            Professor professorToDelete = lastShownProfList.get(profIndex.getZeroBased());

            professorToDelete.removeModule(moduleToDeleteProf.getModuleCode()); // remove module from prof
            moduleToDeleteProf.removePerson(professorToDelete);

            return new CommandResult(String.format(MESSAGE_DELETE_PROF_FROM_MODULE_SUCCESS,
                    professorToDelete.getName(), moduleToDeleteProf.getModuleCode()));

        } else if (targetIndex != null && stuIndex != null) { // delete student from module

            if (isModuleListShowing) {

                List<Module> lastShownModuleList = model.getFilteredModuleList();

                if (targetIndex.getZeroBased() >= lastShownModuleList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
                }

                Module moduleToDeleteStudent = lastShownModuleList.get(targetIndex.getZeroBased());
                List<Student> lastShownStuList = moduleToDeleteStudent.getStudents();

                if (stuIndex.getZeroBased() >= lastShownStuList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_STU_DISPLAYED_INDEX);
                }

                Student studentToDelete = lastShownStuList.get(stuIndex.getZeroBased());

                studentToDelete.removeModule(moduleToDeleteStudent.getModuleCode()); // remove module from student
                moduleToDeleteStudent.removePerson(studentToDelete);

                return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_FROM_MODULE_SUCCESS,
                        studentToDelete.getName(), moduleToDeleteStudent.getModuleCode()));

            } else if (isGroupListShowing) {

                List<Group> lastShownGroupList = model.getShowingGroupList();

                if (targetIndex.getZeroBased() >= lastShownGroupList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
                }

                Group groupToDeleteStudent = lastShownGroupList.get(targetIndex.getZeroBased());
                List<Student> listOfStudentsInGroup = groupToDeleteStudent.getMembers();

                if (stuIndex.getZeroBased() >= listOfStudentsInGroup.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_STU_DISPLAYED_INDEX);
                }

                Student studentToDelete = listOfStudentsInGroup.get(stuIndex.getZeroBased());
                groupToDeleteStudent.removeMember(studentToDelete);
                studentToDelete.removeGroup(groupToDeleteStudent.getModule().getModuleCode(), groupToDeleteStudent);

                return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_FROM_GROUP_SUCCESS,
                        studentToDelete.getName(), groupToDeleteStudent.getGroupName()));

            }

            throw new CommandException(Messages.MESSAGE_CHANGE_TO_MODULE_OR_GROUP_VIEW);

        } else if (targetIndex != null && group != null) { // delete group from module

            if (!isModuleListShowing) {
                throw new CommandException(Messages.MESSAGE_CHANGE_TO_MODULE_VIEW);
            }

            List<Module> lastShownModuleList = model.getFilteredModuleList();

            if (targetIndex.getZeroBased() >= lastShownModuleList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
            }

            Module moduleToDeleteGroup = lastShownModuleList.get(targetIndex.getZeroBased());

            if (!moduleToDeleteGroup.hasGroup(group)) {
                throw new CommandException(String.format(Messages.MESSAGE_GROUP_NOT_EXIST, group.getGroupName()));
            }

            Group groupToDelete = moduleToDeleteGroup.getGroupByName(group.getGroupName());
            moduleToDeleteGroup.removeGroupByName(group);
            groupToDelete.removeStudentsFromThisGroup();

            return new CommandResult(String.format(MESSAGE_DELETE_GROUP_FROM_MODULE_SUCCESS,
                    groupToDelete.getGroupName(), moduleToDeleteGroup.getModuleCode()));

        } else if (targetIndex != null && meetingTimeIndex != null) { // delete meeting time from group page

            if (!isGroupListShowing) {
                throw new CommandException(Messages.MESSAGE_CHANGE_TO_GROUP_VIEW);
            }

            List<Group> lastShownGroupList = model.getShowingGroupList();

            if (targetIndex.getZeroBased() >= lastShownGroupList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
            }

            Group groupToDeleteMeetingTime = lastShownGroupList.get(targetIndex.getZeroBased());
            List<LocalDateTime> meetingTimes = groupToDeleteMeetingTime.getMeetingTimes();

            if (meetingTimeIndex.getZeroBased() >= meetingTimes.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
            }

            LocalDateTime meetingToDelete = meetingTimes.get(meetingTimeIndex.getZeroBased());
            groupToDeleteMeetingTime.removeMeetingTime(meetingTimeIndex.getZeroBased());

            return new CommandResult(String.format(MESSAGE_DELETE_MEETING_FROM_GROUP_SUCCESS,
                    meetingToDelete,
                    groupToDeleteMeetingTime.getGroupName()));

        } else if (targetIndex != null && keyEventIndex != null) {

            if (!isModuleListShowing) {
                throw new CommandException(Messages.MESSAGE_CHANGE_TO_MODULE_VIEW);
            }

            List<Module> lastShownModuleList = model.getFilteredModuleList();

            if (targetIndex.getZeroBased() >= lastShownModuleList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
            }

            Module moduleToDeleteKeyEvent = lastShownModuleList.get(targetIndex.getZeroBased());
            List<ModuleKeyEvent> lastShownKeyEventList = moduleToDeleteKeyEvent.getKeyEvents();

            if (keyEventIndex.getZeroBased() >= lastShownKeyEventList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_KEY_EVENT_DISPLAYED_INDEX);
            }

            ModuleKeyEvent keyToBeDeleted = lastShownKeyEventList.get(keyEventIndex.getZeroBased());
            lastShownKeyEventList.remove(keyEventIndex.getZeroBased());

            return new CommandResult(String.format(MESSAGE_DELETE_KEY_EVENT_FROM_MODULE_SUCCESS,
                    keyToBeDeleted,
                    moduleToDeleteKeyEvent.getModuleCode()));

        }

        return new CommandResult(String.format(MESSAGE_DELETE_UNSUCCESSFUL, moduleCode));


    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteCommand // instanceof handles nulls
            && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
