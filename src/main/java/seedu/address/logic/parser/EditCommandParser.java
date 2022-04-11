package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TELEGRAM,
                    PREFIX_EMAIL, PREFIX_GITHUB, PREFIX_TUTORIAL_GROUP);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, pe.getMessage()));
        }

        EditStudentDescriptor editStudentDescriptor = new EditCommand.EditStudentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_TELEGRAM).isPresent()) {
            if ((argMultimap.getValue(PREFIX_TELEGRAM).get().equals(""))) {
                editStudentDescriptor.setTelegram(ParserUtil.parseTelegram(null));
            } else {
                editStudentDescriptor.setTelegram(ParserUtil.parseTelegram(
                    argMultimap.getValue(PREFIX_TELEGRAM).get()));
            }
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editStudentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_GITHUB).isPresent()) {
            if ((argMultimap.getValue(PREFIX_GITHUB).get().equals(""))) {
                editStudentDescriptor.setGitHub(ParserUtil.parseGitHub(null));
            } else {
                editStudentDescriptor.setGitHub(ParserUtil.parseGitHub(argMultimap.getValue(PREFIX_GITHUB).get()));
            }
        }
        parseTutorialGroupsForEdit(argMultimap.getAllValues(PREFIX_TUTORIAL_GROUP))
            .ifPresent(editStudentDescriptor::setTutorialGroups);

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editStudentDescriptor);
    }

    /**
     * Parses {@code Collection<String> tutorialGroups} into a {@code Set<TutorialGroup>} given that
     * {@code tutorialGroups} is non-empty.
     *
     * @throws ParseException if {@code tutorialGroups} contain only one element which is an empty string
     */
    private Optional<Set<TutorialGroup>> parseTutorialGroupsForEdit(
        Collection<String> tutorialGroups) throws ParseException {
        assert tutorialGroups != null;

        if (tutorialGroups.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(ParserUtil.parseTutorialGroups(tutorialGroups));
    }

}
