package unibook.logic.commands;

import static java.util.Objects.requireNonNull;
import static unibook.logic.parser.CliSyntax.PREFIX_DATETIME;
import static unibook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static unibook.logic.parser.CliSyntax.PREFIX_GROUP;
import static unibook.logic.parser.CliSyntax.PREFIX_KEYEVENT;
import static unibook.logic.parser.CliSyntax.PREFIX_MEETINGTIME;
import static unibook.logic.parser.CliSyntax.PREFIX_MODULE;
import static unibook.logic.parser.CliSyntax.PREFIX_NAME;
import static unibook.logic.parser.CliSyntax.PREFIX_NEWMOD;
import static unibook.logic.parser.CliSyntax.PREFIX_OPTION;
import static unibook.logic.parser.CliSyntax.PREFIX_PHONE;
import static unibook.logic.parser.CliSyntax.PREFIX_TAG;
import static unibook.logic.parser.CliSyntax.PREFIX_TYPE;
import static unibook.model.Model.PREDICATE_SHOW_ALL_MODULES;
import static unibook.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.collections.ObservableList;
import unibook.commons.core.Messages;
import unibook.commons.core.index.Index;
import unibook.commons.util.CollectionUtil;
import unibook.logic.commands.exceptions.CommandException;
import unibook.model.Model;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.ModuleKeyEvent;
import unibook.model.module.ModuleName;
import unibook.model.module.exceptions.ModuleNotFoundException;
import unibook.model.module.group.Group;
import unibook.model.person.Email;
import unibook.model.person.Name;
import unibook.model.person.Office;
import unibook.model.person.Person;
import unibook.model.person.Phone;
import unibook.model.person.Professor;
import unibook.model.person.Student;
import unibook.model.person.exceptions.DuplicatePersonException;
import unibook.model.tag.Tag;

/**
 * Edits the details of an existing person in the UniBook.
 */
public class EditCommand extends Command {


    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the UniBook.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the module.";
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "Edited Module: %1$s";
    public static final String MESSAGE_EDIT_GROUP_SUCCESS = "Edit Group success!";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_OPTION_NOT_FOUND = "O/OPTION must either be person, module, group or keyevent. "
            + "\n";
    public static final String MESSAGE_PERSON_NO_SUBTYPE = "Person must be a professor or student";
    public static final String MESSAGE_EDIT_MISSING = "Must include m/MODULECODE when editing a group";
    public static final String MESSAGE_WRONG_DATE_FORMAT = "Date time must be in YYYY-MM-DD HH:mm format";
    public static final String MESSAGE_KEYEVENT_INDEX_MISSING = "Index of key event to be changed must be included. "
            + "E.g. ke/1 edits the fields of 1st key event";


    public static final String PERSON_MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_OPTION + "OPTION (person or module) "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_OPTION + "person "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MODULE_MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the module identified "
            + "by the index number used in the displayed module list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_OPTION + "OPTION "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_MODULE + "MODULECODE] "
            + "[" + PREFIX_NEWMOD + "NEWMODULECODE] \n "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_OPTION + "module "
            + PREFIX_NAME + "Software Engineering "
            + PREFIX_MODULE + "CS2103T";

    public static final String GROUP_MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the group identified "
            + "by the index number used in the displayed group list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_OPTION + "OPTION "
            + PREFIX_MODULE + "MODULECODE "
            + "[" + PREFIX_GROUP + "GROUPNAME] "
            + "[" + PREFIX_MEETINGTIME + "MEETINGTIME] \n "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_OPTION + "group "
            + PREFIX_NAME + "CS2103 Team1 "
            + PREFIX_MEETINGTIME + "12-12-2022 1645";

    public static final String KEYEVENT_MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the key event identified"
            + " by the index number used in the displayed module list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (of the module must be a positive integer) "
            + PREFIX_OPTION + "OPTION "
            + "[" + PREFIX_KEYEVENT + "KEYEVENT] "
            + "[" + PREFIX_DATETIME + "DATETIME] \n "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_OPTION + "keyevent "
            + PREFIX_KEYEVENT + "1 "
            + PREFIX_TYPE + "exam "
            + PREFIX_MEETINGTIME + "12-12-2022 1645";

    private final Index index;
    private ModuleCode modCode;
    private EditPersonDescriptor editPersonDescriptor;
    private EditModuleDescriptor editModuleDescriptor;

    /**
     * @param index                of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
        this.editModuleDescriptor = null;
        this.modCode = null;
    }

    /**
     * @param index                of the person in the filtered person list to edit
     * @param editModuleDescriptor details to edit the person with
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor,
                       EditModuleDescriptor editModuleDescriptor) {
        requireNonNull(index);
        requireNonNull(editModuleDescriptor);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = editPersonDescriptor;
        this.editModuleDescriptor = editModuleDescriptor;
        this.modCode = null;
    }

    /**
     * @param index                of the module in the filtered module list to edit
     * @param editModuleDescriptor details to edit the module with
     */
    public EditCommand(Index index, EditModuleDescriptor editModuleDescriptor) {
        requireNonNull(index);
        requireNonNull(editModuleDescriptor);

        this.index = index;
        this.editModuleDescriptor = new EditModuleDescriptor(editModuleDescriptor);
        this.editPersonDescriptor = null;
        this.modCode = null;
    }

    /**
     * @param index                of the module in the filtered module list to edit
     * @param editPersonDescriptor details to edit the person with
     * @param modCode              module code to be edited
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor, ModuleCode modCode) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
        this.editModuleDescriptor = null;
        this.modCode = modCode;

    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());

        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Set<Module> updatedModules = editPersonDescriptor.getModulesModifiable().orElse(personToEdit.getModules());

        if (!updatedModules.equals(personToEdit.getModulesModifiable())) {
            updatedModules.addAll(personToEdit.getModulesModifiable());
        }

        if (personToEdit instanceof Professor) {
            Office updatedOffice = ((Professor) personToEdit).getOffice();
            return new Professor(updatedName, updatedPhone, updatedEmail, updatedTags, updatedOffice, updatedModules);
        } else {
            Set<Group> groups = ((Student) personToEdit).getGroups();
            return new Student(updatedName, updatedPhone, updatedEmail, updatedTags, updatedModules, groups);
        }
    }

    /**
     * Creates and returns a {@code Module} with the details of {@code moduleToEdit}
     * edited with {@code editModuleDescriptor}.
     */
    private static Module createEditedModule(int idx, Module moduleToEdit, EditModuleDescriptor editModuleDescriptor)
            throws CommandException {
        assert moduleToEdit != null;

        ModuleName updatedModuleName = editModuleDescriptor.getModuleName().orElse(moduleToEdit.getModuleName());
        ModuleCode updatedModuleCode = editModuleDescriptor.getModuleCode().orElse(moduleToEdit.getModuleCode());
        ObservableList<Professor> profs = moduleToEdit.getProfessors();
        ObservableList<Student> students = moduleToEdit.getStudents();

        // TODO ASSUME THAT UPDATEFILTEREDMODULELIST WORKS
        ObservableList<Group> groups = moduleToEdit.getGroups();

        // TODO changed for editing of key events
        ObservableList<ModuleKeyEvent> keyEvents;
        if (editModuleDescriptor.getKeyEvents().isPresent()) {
            int i = editModuleDescriptor.getIdx();
            if (i >= moduleToEdit.getKeyEvents().size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_KEYEVENT_DISPLAYED_INDEX);
            }
            ModuleKeyEvent ke = moduleToEdit.getKeyEvents().get(i);
            if (editModuleDescriptor.getKeyEvents().get().getKeyEventTiming() == null) {
                editModuleDescriptor.getKeyEvents().get().setDateTime(ke.getKeyEventTiming());
            } else if (editModuleDescriptor.getKeyEvents().get().getKeyEventType() == null) {
                editModuleDescriptor.getKeyEvents().get().setKeyDateType(ke.getKeyEventType());
            } else { }
            moduleToEdit.getKeyEvents().set(i, editModuleDescriptor.getKeyEvents().get());
            keyEvents = moduleToEdit.getKeyEvents();
        } else {
            keyEvents = moduleToEdit.getKeyEvents();
        }
        return new Module(updatedModuleName, updatedModuleCode, profs, students, groups, keyEvents);
    }

    /**
     * Creates and returns a {@code Group} with the details of {@code groupToEdit}
     * edited with {@code editGroupDescriptor}.
     */
    public static Group createEditedGroup(Group groupToEdit, EditGroupDescriptor editGroupDescriptor)
            throws CommandException {
        assert groupToEdit != null;

        // should only be able to edit group name and meeting times
        String updatedGroupName = editGroupDescriptor.getGroupName().orElse(groupToEdit.getGroupName());

        // Edits it at the respective index, only 1 meeting time can be edited at a time
        ObservableList<LocalDateTime> updatedMeetingTimes;
        if (editGroupDescriptor.getMeetingTimes().isPresent()) {
            int idxOfMeetingTime = editGroupDescriptor.getIdxOfMeetingTimes().get();
            if (idxOfMeetingTime - 1 >= groupToEdit.getMeetingTimes().size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_MEETINGTIME_DISPLAYED_INDEX);
            }
            groupToEdit.getMeetingTimes().set(idxOfMeetingTime - 1, editGroupDescriptor.getMeetingTimes().get());
            updatedMeetingTimes = groupToEdit.getMeetingTimes();
        } else {
            updatedMeetingTimes = groupToEdit.getMeetingTimes();
        }
        ObservableList<Student> students = groupToEdit.getMembers();
        Module module = groupToEdit.getModule();

        return new Group(updatedGroupName, module, students, updatedMeetingTimes);
    }

    @Override
    public CommandResult execute(Model model, Boolean isPersonListShowing,
                                 Boolean isModuleListShowing) throws CommandException, ModuleNotFoundException {
        requireNonNull(model);

        if (this.modCode != null && !model.hasModule(this.modCode)) {
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_CODE_NOT_EXIST, modCode.toString()));
        }

        List<Person> lastShownList = model.getFilteredPersonList();
        List<Module> latestModList = model.getFilteredModuleList();

        System.out.println(this.editModuleDescriptor == null);
        System.out.println(this.editPersonDescriptor == null);
        // TODO MAIN LOGIC FOR EDIT GROUP CROSS FINGER UPDATE...LIST WORKS
        if (this.editModuleDescriptor != null && this.editPersonDescriptor != null) {
            System.out.println("entered correct logic");
            if (!isModuleListShowing) {
                throw new CommandException(Messages.MESSAGE_CHANGE_TO_MODULE_PAGE);
            }
            EditGroupDescriptor editGroupDescriptor = this.editModuleDescriptor.getGroups().get();
            editGroupDescriptor.setModel(model);
            ModuleCode modcode = editGroupDescriptor.getModuleCode();
            Module mod = model.getModuleByCode(modcode);

            // Checks if module that want to add to person is valid
            // TODO check why the exception not being printed to the console
            if (!latestModList.contains(mod)) {
                throw new ModuleNotFoundException(mod.toString());
            }

            for (Person p : lastShownList) {
                if (p instanceof Student) {
                    if (p.getModules().contains(mod)) { ((Student) p).editGroupByMod(mod, editGroupDescriptor); }
                }
            }
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            System.out.println("updated person list");
            for (Module m : latestModList) {
                if (m.equals(mod)) {
                    if (this.index.getZeroBased() >= m.getGroups().size()) {
                        throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
                    }
                    m.editGroupByIndex(this.index.getZeroBased(), editGroupDescriptor);
                }
            }
            model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
            System.out.println("updated module list");
            // TODO make the command result for editing success more elaborate
            return new CommandResult(MESSAGE_EDIT_GROUP_SUCCESS);
        }
        // Edit person
        if (this.editModuleDescriptor == null) {
            if (!isPersonListShowing) {
                throw new CommandException(Messages.MESSAGE_CHANGE_TO_PERSON_PAGE);
            }


            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            if (this.modCode != null) {
                Module mod = model.getModuleByCode(modCode);
                Set<Module> modSet = new HashSet<>();
                modSet.add(mod);
                this.editPersonDescriptor.setModules(modSet);
            }

            Person personToEdit = lastShownList.get(index.getZeroBased());
            Module checkMod = null;

            // Checks if module that want to add to person is valid
            if (!editPersonDescriptor.getModules().equals(Optional.empty()) && latestModList.size() != 0) {
                checkMod = editPersonDescriptor.getModules().get().iterator().next();
                if (!latestModList.contains(checkMod)) {
                    throw new ModuleNotFoundException(checkMod.toString());
                }
            }

            Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

            // TODO checks if person alr in module
            if (!personToEdit.equals(editedPerson) && model.hasPerson(editedPerson)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }

            // When adding new module with nm/, adds prof/student to person list in each mod
            if (checkMod != null) {
                int modIdx = latestModList.indexOf(checkMod);
                if (editedPerson instanceof Professor) {
                    try {
                        latestModList.get(modIdx).addProfessor((Professor) editedPerson);
                    } catch (DuplicatePersonException e) {
                        throw new CommandException(MESSAGE_DUPLICATE_PERSON);
                    }
                } else if (editedPerson instanceof Student) {
                    try {
                        latestModList.get(modIdx).addStudent((Student) editedPerson);
                    } catch (DuplicatePersonException e) {
                        throw new CommandException(MESSAGE_DUPLICATE_PERSON);
                    }
                } else {
                    throw new CommandException(MESSAGE_PERSON_NO_SUBTYPE);
                }
            }



            // Change person in every module that has this person
            for (Module mod : latestModList) {
                if (editedPerson instanceof Professor) {
                    ObservableList<Professor> profList = mod.getProfessors();
                    if (profList.contains(personToEdit)) {
                        int profIdx = profList.indexOf(personToEdit);
                        profList.set(profIdx, (Professor) editedPerson);
                    }
                } else if (editedPerson instanceof Student) {
                    ObservableList<Student> studentList = mod.getStudents();
                    if (studentList.contains(personToEdit)) {
                        int studentIdx = studentList.indexOf(personToEdit);
                        studentList.set(studentIdx, (Student) editedPerson);
                    }
                } else {
                    throw new CommandException(MESSAGE_PERSON_NO_SUBTYPE);
                }
            }

            model.setPerson(personToEdit, editedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));

        } else {

            // Edit module

            if (!isModuleListShowing) {
                throw new CommandException(Messages.MESSAGE_CHANGE_TO_MODULE_PAGE);
            }

            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
            }

            Module moduleToEdit = latestModList.get(index.getZeroBased());
            System.out.println(editModuleDescriptor.getIdx() + "why");
            Module editedModule = createEditedModule(index.getZeroBased(), moduleToEdit, editModuleDescriptor);

            if (!moduleToEdit.isSameModule(editedModule) && model.hasModule(editedModule)) {
                throw new CommandException(MESSAGE_DUPLICATE_MODULE);
            }

            // Find all profs and students with this module and will be edited
            for (Person person : lastShownList) {
                Set<Module> moduleSet = person.getModulesModifiable();
                if (moduleSet.contains(moduleToEdit)) {
                    moduleSet.remove(moduleToEdit);
                    moduleSet.add(editedModule);
                }
            }

            model.setModule(moduleToEdit, editedModule);
            model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
            return new CommandResult(String.format(MESSAGE_EDIT_MODULE_SUCCESS, editedModule));
        }
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
            && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Set<Tag> tags;
        private Set<Module> modules;
        private EditGroupDescriptor editGroupDescriptor;

        public EditPersonDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setTags(toCopy.tags);
            setModules(toCopy.modules);
            setGroups(toCopy.editGroupDescriptor);

        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, tags, modules);
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<EditGroupDescriptor> getGroups() {
            return Optional.ofNullable(editGroupDescriptor);
        }

        public void setGroups(EditGroupDescriptor editGroupDescriptor) {
            this.editGroupDescriptor = editGroupDescriptor;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code modules} is null.
         */
        public Optional<Set<Module>> getModules() {
            return (modules != null) ? Optional.of(Collections.unmodifiableSet(modules)) : Optional.empty();
        }

        /**
         * Sets {@code modules} to this object's {@code modules}.
         * A defensive copy of {@code modules} is used internally.
         */
        public void setModules(Set<Module> modules) {
            if (modules == null) {
                this.modules = null;
            } else {
                Set<Module> modulesCopy = new HashSet<>(modules);
                modulesCopy.addAll(modules);

                this.modules = modulesCopy;
            }
        }

        /**
         * Returns a mutable module set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         */
        public Optional<Set<Module>> getModulesModifiable() {
            return (modules != null) ? Optional.of(modules) : Optional.empty();
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                && getPhone().equals(e.getPhone())
                && getEmail().equals(e.getEmail())
                && getTags().equals(e.getTags())
                && getGroups().equals(e.getGroups())
                && getModules().equals(e.getModules());
        }
    }

    /**
     * Stores the details to edit the module with. Each non-empty field value will replace the
     * corresponding field value of the module.
     */
    public static class EditModuleDescriptor {
        private ModuleName moduleName;
        private ModuleCode moduleCode;
        private EditGroupDescriptor editGroupDescriptor;
        private ModuleKeyEvent keyEvents;
        private int idxOfKeyEvent = -2;


        public EditModuleDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditModuleDescriptor(EditModuleDescriptor toCopy) {
            setModuleName(toCopy.moduleName);
            setModuleCode(toCopy.moduleCode);
            setGroups(toCopy.editGroupDescriptor);
            this.keyEvents = toCopy.keyEvents;
            this.idxOfKeyEvent = toCopy.idxOfKeyEvent;
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(moduleName, moduleCode);
        }

        public Optional<ModuleName> getModuleName() {
            return Optional.ofNullable(moduleName);
        }

        public void setModuleName(ModuleName moduleName) {
            this.moduleName = moduleName;
        }

        public Optional<ModuleCode> getModuleCode() {
            return Optional.ofNullable(moduleCode);
        }

        public void setModuleCode(ModuleCode moduleCode) {
            this.moduleCode = moduleCode;
        }

        public Optional<EditGroupDescriptor> getGroups() {
            return Optional.ofNullable(editGroupDescriptor);
        }

        public void setGroups(EditGroupDescriptor groups) {
            this.editGroupDescriptor = groups;
        }

        public Optional<ModuleKeyEvent> getKeyEvents() {
            return Optional.ofNullable(keyEvents);
        }

        public void setKeyEvents(ModuleKeyEvent e) {
            this.keyEvents = e;
        }

        public void setIdx(int i) {
            this.idxOfKeyEvent = i;
        }

        public int getIdx() {
            return this.idxOfKeyEvent;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditModuleDescriptor)) {
                return false;
            }

            // state check
            EditModuleDescriptor e = (EditModuleDescriptor) other;

            return getModuleName().equals(e.getModuleName())
                && getModuleCode().equals(e.getModuleCode());
        }
    }


    /**
     * Stores the details to edit the Group with. Each non-empty field value will replace the
     * corresponding field value of the group.
     */
    public static class EditGroupDescriptor {
        private String name;
        private Module module;
        private ModuleCode modcode;
        private ObservableList<Student> members;
        private LocalDateTime meetingTimes;
        private Model model = null;
        private int idxOfMeetingTime = -1;

        public EditGroupDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditGroupDescriptor(EditGroupDescriptor toCopy) {
            setGroupName(toCopy.name);
            this.meetingTimes = toCopy.meetingTimes;
            setStudents(toCopy.members);
            this.module = toCopy.module;

        }

        /**
         * Returns true if at least one field is edited. Only name and meeting times can be edited
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, meetingTimes);
        }



        public void setModule(ModuleCode modcode) {
            System.out.println(modcode.toString());
            Module module = model.getModuleByCode(modcode);
            System.out.println(module + "here");
            this.module = module;
        }

        public void setModel(Model model) {
            this.model = model;
        }

        public void setModuleCode(ModuleCode modcod) {
            this.modcode = modcod;
        }

        public ModuleCode getModuleCode() {
            return this.modcode;
        }

        public Optional<ObservableList<Student>> getMembers() {
            return Optional.ofNullable(members);
        }

        public Optional<Module> getModule() {
            return Optional.ofNullable(module);
        }

        public void setStudents(ObservableList<Student> members) {
            this.members = members;
        }

        public Optional<String> getGroupName() {
            return Optional.ofNullable(name);
        }

        public void setGroupName(String name) {
            this.name = name;
        }

        public Optional<LocalDateTime> getMeetingTimes() {
            return Optional.ofNullable(meetingTimes);
        }

        public Optional<Integer> getIdxOfMeetingTimes() {
            return Optional.ofNullable(idxOfMeetingTime);
        }



        // TODO CHANGED HERE
        public void setMeetingTimes(List<Object> ls) {
            System.out.println(ls);
            LocalDateTime meetingTime = (LocalDateTime) ls.get(1);
            System.out.println(meetingTime.getClass().getSimpleName());
            int idxOfMeetingTime = (int) ls.get(0);
            this.idxOfMeetingTime = idxOfMeetingTime;
            this.meetingTimes = meetingTime;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditGroupDescriptor)) {
                return false;
            }

            // state check
            EditGroupDescriptor e = (EditGroupDescriptor) other;

            return getGroupName().equals(e.getGroupName())
                && getMeetingTimes().equals(e.getMeetingTimes())
                && getModule().equals(e.getModule())
                && getMembers().equals(e.getMembers());
        }
    }
}
