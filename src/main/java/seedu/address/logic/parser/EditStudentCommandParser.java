package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NO_INDEX_OR_PREFIX_PROVIDED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditStudentCommand;
import seedu.address.logic.commands.EditStudentCommand.EditStudentDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditStudentCommand object
 */
public class EditStudentCommandParser implements Parser<EditStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditStudentCommand
     * and returns an EditStudentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditStudentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_NAME, PREFIX_STUDENT_PHONE, PREFIX_STUDENT_EMAIL,
                        PREFIX_STUDENT_ADDRESS, PREFIX_STUDENT_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_NO_INDEX_OR_PREFIX_PROVIDED,
                    EditStudentCommand.MESSAGE_USAGE));
        }

        EditStudentDescriptor editStudentDescriptor = createEditStudentDescriptor(argMultimap);

        boolean isAnyFieldEdited = editStudentDescriptor.isAnyFieldEdited();
        if (!isAnyFieldEdited) {
            throw new ParseException(EditStudentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditStudentCommand(index, editStudentDescriptor);
    }

    private EditStudentDescriptor createEditStudentDescriptor(ArgumentMultimap argMultimap) throws ParseException {
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();

        setStudentName(editStudentDescriptor, argMultimap);
        setPhone(editStudentDescriptor, argMultimap);
        setEmail(editStudentDescriptor, argMultimap);
        setAddress(editStudentDescriptor, argMultimap);
        setTags(editStudentDescriptor, argMultimap);

        return editStudentDescriptor;
    }

    /**
     * Parses and sets the StudentName in a {@code EditStudentDescriptor} from the provided arguments.
     * StudentName must be provided in the arguments.
     */
    private void setStudentName(EditStudentDescriptor editStuDes, ArgumentMultimap argMultimap) throws ParseException {
        if (isNamePrefixPresent(argMultimap)) {
            String nameInString = argMultimap.getValue(PREFIX_STUDENT_NAME).get();
            Name name = ParserUtil.parseName(nameInString);
            editStuDes.setName(name);
        }
    }

    /**
     * Parses and sets the Phone in a {@code EditStudentDescriptor} from the provided arguments.
     * Phone must be provided in the arguments.
     */
    private void setPhone(EditStudentDescriptor editStuDes, ArgumentMultimap argMultimap) throws ParseException {
        if (isPhonePrefixPresent(argMultimap)) {
            String phoneInString = argMultimap.getValue(PREFIX_STUDENT_PHONE).get();
            Phone phone = ParserUtil.parsePhone(phoneInString);
            editStuDes.setPhone(phone);
        }
    }

    /**
     * Parses and sets the Email in a {@code EditStudentDescriptor} from the provided arguments.
     * Email must be provided in the arguments.
     */
    private void setEmail(EditStudentDescriptor editStuDes, ArgumentMultimap argMultimap) throws ParseException {
        if (isEmailPrefixPresent(argMultimap)) {
            String emailInString = argMultimap.getValue(PREFIX_STUDENT_EMAIL).get();
            Email email = ParserUtil.parseEmail(emailInString);
            editStuDes.setEmail(email);
        }
    }

    /**
     * Parses and sets the Address in a {@code EditStudentDescriptor} from the provided arguments.
     * Address must be provided in the arguments.
     */
    private void setAddress(EditStudentDescriptor editStuDes, ArgumentMultimap argMultimap) throws ParseException {
        if (isAddressPrefixPresent(argMultimap)) {
            String addressInString = argMultimap.getValue(PREFIX_STUDENT_ADDRESS).get();
            Address address = ParserUtil.parseAddress(addressInString);
            editStuDes.setAddress(address);
        }
    }

    /**
     * Parses and sets the Tags in a {@code EditStudentDescriptor} from the provided arguments.
     * Tags must be provided in the arguments.
     */
    private void setTags(EditStudentDescriptor editStudentDes, ArgumentMultimap argMultimap) throws ParseException {
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_STUDENT_TAG)).ifPresent(editStudentDes::setTags);
    }

    private boolean isNamePrefixPresent(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_STUDENT_NAME).isPresent();
    }

    private boolean isPhonePrefixPresent(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_STUDENT_PHONE).isPresent();
    }

    private boolean isEmailPrefixPresent(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_STUDENT_EMAIL).isPresent();
    }

    private boolean isAddressPrefixPresent(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_STUDENT_ADDRESS).isPresent();
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
