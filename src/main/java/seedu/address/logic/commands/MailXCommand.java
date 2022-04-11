package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.MailUtil.launchMail;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Group;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentNumber;

/**
 * Opens the system default mail app with people
 * identified by the arguments specified by the user.
 */
public class MailXCommand extends Command {

    public static final String COMMAND_WORD = "mail-x";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens the mail box with the emails of the people identified by parameters \n"
            + "Optionally include parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_GROUP + "GROUP] "
            + "[" + PREFIX_MOD + "MOD] \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_GROUP + "G02"
            + " or " + COMMAND_WORD + " " + PREFIX_MOD + "CS1234";

    public static final String MESSAGE_SUCCESS = "Opening System Default Mail App";

    public static final String NAME_NOT_PRESENT = "Name not present in the contact list";

    public static final String EMAIL_NOT_PRESENT = "Email not present in the contact list";

    public static final String MOD_NOT_PRESENT = "Module not present in the contact list";

    public static final String GROUP_NOT_PRESENT = "Group not present in the contact list";

    public static final String URI_SYNTAX_ERROR_MESSAGE = "The URI syntax used is incorrect";

    public static final String DESKTOP_NOT_SUPPORTED_MESSAGE = "The desktop you are using is not supported";

    private final MailXDescriptor mailXDescriptor;

    /**
     * Constructor for the MailXCommand class.
     * @param mailXDescriptor The {@code MailXDescriptor} object which collates all the email addresses to mail to.
     */
    public MailXCommand(MailXDescriptor mailXDescriptor) {
        requireNonNull(mailXDescriptor);

        this.mailXDescriptor = mailXDescriptor;
    }

    /**
     * Executes the mail-x command.
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} object that contains the result of the execution.
     * @throws CommandException if any noncompliance occurs in the command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            launchMail(createEmailList(this.mailXDescriptor, model));
        } catch (URISyntaxException uriSyntaxException) {
            throw new CommandException(URI_SYNTAX_ERROR_MESSAGE);
        } catch (IOException ioException) {
            throw new CommandException(DESKTOP_NOT_SUPPORTED_MESSAGE);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Creates the email list as specified the arguments passed by the user.
     * @param mailXDescriptor The {@code MailXDescriptor} object which collates all the email addresses to mail to.
     * @param model {@code Model} which the command should operate on.
     * @return The list of email addresses to whom the mail is directed towards.
     * @throws CommandException if the command execution goes unexpected.
     */
    public String[] createEmailList(MailXDescriptor mailXDescriptor, Model model) throws CommandException {
        List<String> emailList = new ArrayList<>();
        List<Person> lastShownList = model.getFilteredPersonList();

        Name name = mailXDescriptor.getName().orElse(null);
        handleNameAddition(lastShownList, emailList, name);

        Email email = mailXDescriptor.getEmail().orElse(null);
        handleEmailAddition(lastShownList, emailList, email);

        Mod mod = mailXDescriptor.getMod().orElse(null);
        handleModAddition(lastShownList, emailList, mod);

        Group group = mailXDescriptor.getGroup().orElse(null);
        handleGroupAddition(lastShownList, emailList, group);

        String[] emailListToReturn = new String[emailList.size()];
        emailListToReturn = emailList.toArray(emailListToReturn);

        return emailListToReturn;
    }

    /**
     * Handles name argument based email addition part to the email list.
     * @param lastShownList The list of the people as retrieved from the model.
     * @param emailList The list of the email addresses.
     * @param name The name of the person as parsed from the arguments, can be null.
     * @throws CommandException if the command execution goes unexpected.
     */
    private void handleNameAddition(List<Person> lastShownList, List<String> emailList, Name name)
            throws CommandException {
        if (!isNull(name)) {
            if (lastShownList.stream().anyMatch(person -> person.getName().equals(name))) {
                emailList.add(lastShownList.stream().filter(person ->
                        person.getName().equals(name)).findFirst().get().getEmail().toString());
            } else {
                throw new CommandException(NAME_NOT_PRESENT);
            }
        }
    }

    /**
     * Handles email argument based email addition part to the email list.
     * @param lastShownList The list of the people as retrieved from the model.
     * @param emailList The list of the email addresses.
     * @param email The email of the person as parsed from the arguments, can be null.
     * @throws CommandException if the command execution goes unexpected.
     */
    private void handleEmailAddition(List<Person> lastShownList, List<String> emailList, Email email)
            throws CommandException {
        if (!isNull(email)) {
            if (lastShownList.stream().anyMatch(person -> person.getEmail().equals(email))) {
                String e = lastShownList.stream().filter(person ->
                        person.getEmail().equals(email)).findFirst().get().getEmail().toString();

                if (!emailList.contains(e)) {
                    emailList.add(e);
                }
            } else {
                throw new CommandException(EMAIL_NOT_PRESENT);
            }
        }
    }

    /**
     * Handles module argument based email addition part to the email list.
     * @param lastShownList The list of the people as retrieved from the model.
     * @param emailList The list of the email addresses.
     * @param mod The module of the person(s) as parsed from the arguments, can be null.
     * @throws CommandException if the command execution goes unexpected.
     */
    private void handleModAddition(List<Person> lastShownList, List<String> emailList, Mod mod)
            throws CommandException {
        if (!isNull(mod)) {
            if (lastShownList.stream().anyMatch(person -> person.getMod().equals(mod))) {
                List<Person> peopleInModule = lastShownList.stream().filter(person ->
                        person.getMod().equals(mod)).collect(Collectors.toList());
                for (Person p : peopleInModule) {
                    String e = p.getEmail().toString();
                    if (!emailList.contains(e)) {
                        emailList.add(e);
                    }
                }
            } else {
                throw new CommandException(MOD_NOT_PRESENT);
            }
        }
    }

    /**
     * Handles group argument based email addition part to the email list.
     * @param lastShownList The list of the people as retrieved from the model.
     * @param emailList The list of the email addresses.
     * @param group The group of the person(s) as parsed from the arguments, can be null.
     * @throws CommandException if the command execution goes unexpected.
     */
    private void handleGroupAddition(List<Person> lastShownList, List<String> emailList, Group group)
            throws CommandException {
        if (!isNull(group)) {
            if (lastShownList.stream().anyMatch(person -> person.getGroup().equals(group))) {
                List<Person> peopleInGroup = lastShownList.stream().filter(person ->
                        person.getGroup().equals(group)).collect(Collectors.toList());
                for (Person p : peopleInGroup) {
                    String e = p.getEmail().toString();
                    if (!emailList.contains(e)) {
                        emailList.add(e);
                    }
                }
            } else {
                throw new CommandException(GROUP_NOT_PRESENT);
            }
        }
    }


    /**
     * Checks whether two {@code MailXCommand} objects are equal.
     * @param other The {@code MailXCommand} to check equality against.
     * @return The boolean value specifying the equality.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MailXCommand)) {
            return false;
        }

        // state check
        MailXCommand m = (MailXCommand) other;
        return mailXDescriptor.equals(m.mailXDescriptor);
    }

    public static class MailXDescriptor {
        private Name name;
        private Email email;
        private Mod mod;
        private Group group;
        private StudentNumber studentNumber;

        public MailXDescriptor() {}

        /**
         * Copy constructor with set values.
         */
        public MailXDescriptor(MailXDescriptor toCopy) {
            setName(toCopy.name);
            setEmail(toCopy.email);
            setGroup(toCopy.group);
            setMod(toCopy.mod);
            setStudentNumber(toCopy.studentNumber);
        }

        /**
         * Sets the name field for the {@code MailXDescriptor} object.
         * @param name The name to be set.
         */
        public void setName(Name name) {
            this.name = name;
        }

        /**
         * Obtains the name field for the {@code MailXDescriptor} object.
         * @return The {@code Optional} object containing the name.
         */
        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        /**
         * Sets the student number field for the {@code MailXDescriptor} object.
         * @param studentNumber The student number to be set.
         */
        public void setStudentNumber(StudentNumber studentNumber) {
            this.studentNumber = studentNumber;
        }

        /**
         * Obtains the student number field for the {@code MailXDescriptor} object.
         * @return The {@code Optional} object containing the student number.
         */
        public Optional<StudentNumber> getStudentNumber() {
            return Optional.ofNullable(studentNumber);
        }

        /**
         * Sets the email field for the {@code MailXDescriptor} object.
         * @param email The email to be set.
         */
        public void setEmail(Email email) {
            this.email = email;
        }

        /**
         * Obtains the email field for the {@code MailXDescriptor} object.
         * @return The {@code Optional} object containing the email.
         */
        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        /**
         * Sets the mod field for the {@code MailXDescriptor} object.
         * @param mod The mod to be set.
         */
        public void setMod(Mod mod) {
            this.mod = mod;
        }

        /**
         * Obtains the mod field for the {@code MailXDescriptor} object.
         * @return The {@code Optional} object containing the mod.
         */
        public Optional<Mod> getMod() {
            return Optional.ofNullable(mod);
        }

        /**
         * Sets the group field for the {@code MailXDescriptor} object.
         * @param group The group to be set.
         */
        public void setGroup(Group group) {
            this.group = group;
        }

        /**
         * Obtains the group field for the {@code MailXDescriptor} object.
         * @return The {@code Optional} object containing the group.
         */
        public Optional<Group> getGroup() {
            return Optional.ofNullable(group);
        }

        /**
         * Checks whether two {@code MailXDescriptor} objects are equal or not.
         * @param other The {@code MailXDescriptor} to check equality against.
         * @return The boolean value specifying whether the two objects are equal or not.
         */
        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof MailXDescriptor)) {
                return false;
            }

            // state check
            MailXDescriptor e = (MailXDescriptor) other;

            return getName().equals(e.getName())
                    && getEmail().equals(e.getEmail())
                    && getMod().equals(e.getMod())
                    && getStudentNumber().equals(e.getStudentNumber());
        }
    }
}
