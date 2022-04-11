package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INCOMPATIBLE_VIEW_MODE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalTags.VALID_TAGNAME_FRIENDS;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddImageCommand;
import seedu.address.logic.commands.AssignTagCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CreateTagCommand;
import seedu.address.logic.commands.DeadlineCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteDeadlineCommand;
import seedu.address.logic.commands.DeleteImageCommand;
import seedu.address.logic.commands.DeleteNoteCommand;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FavouriteCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindTagCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HighImportanceCommand;
import seedu.address.logic.commands.ImagesCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListFavouritesCommand;
import seedu.address.logic.commands.ListImportantCommand;
import seedu.address.logic.commands.NoteCommand;
import seedu.address.logic.commands.SortsCommand;
import seedu.address.logic.commands.UnassignTagCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Deadline;
import seedu.address.model.person.DeadlineList;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();
    private final Person person = new PersonBuilder().build();

    @Test
    public void parseCommand_add() throws Exception {
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_view() throws Exception {
        ViewCommand command = (ViewCommand) parser.parseCommand(
                ViewCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new ViewCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_detailedViewCommands_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INCOMPATIBLE_VIEW_MODE, ()
            -> parser.parseCommand(DeleteDeadlineCommand.COMMAND_WORD + " 3"));
        assertThrows(ParseException.class, MESSAGE_INCOMPATIBLE_VIEW_MODE, ()
            -> parser.parseCommand(DeleteNoteCommand.COMMAND_WORD + " 2"));
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_findTag() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindTagCommand command = (FindTagCommand) parser.parseCommand(
                FindTagCommand.COMMAND_WORD
                        + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindTagCommand(keywords), command);
    }

    @Test
    public void parseCommand_tag() throws Exception {
        String tagName = VALID_TAGNAME_FRIENDS;
        CreateTagCommand command = (CreateTagCommand) parser.parseCommand(
                CreateTagCommand.COMMAND_WORD + " " + tagName);
        assertEquals(new CreateTagCommand(tagName), command);
    }

    @Test
    public void parseCommand_assign() throws Exception {
        String tagName = VALID_TAGNAME_FRIENDS;
        AssignTagCommand command = (AssignTagCommand) parser.parseCommand(
                AssignTagCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " " + tagName);
        assertEquals(new AssignTagCommand(INDEX_FIRST_PERSON, tagName), command);
    }

    @Test
    public void parseCommand_unassign() throws Exception {
        String tagName = VALID_TAGNAME_FRIENDS;
        UnassignTagCommand command = (UnassignTagCommand) parser.parseCommand(
                UnassignTagCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " " + tagName);
        assertEquals(new UnassignTagCommand(INDEX_FIRST_PERSON, tagName), command);
    }

    @Test
    public void parseCommand_deltag() throws Exception {
        List<String> keywords = Arrays.asList(VALID_TAGNAME_FRIENDS);
        DeleteTagCommand command = (DeleteTagCommand) parser.parseCommand(
                DeleteTagCommand.COMMAND_WORD
                        + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new DeleteTagCommand(keywords), command);
    }

    @Test
    public void parseCommand_fav() throws Exception {
        FavouriteCommand command = (FavouriteCommand) parser.parseCommand(
                FavouriteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new FavouriteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_favourites() throws Exception {
        ListFavouritesCommand command = (ListFavouritesCommand) parser.parseCommand(
                ListFavouritesCommand.COMMAND_WORD);
        assertEquals(new ListFavouritesCommand(), command);
        assertTrue(parser.parseCommand(
                ListFavouritesCommand.COMMAND_WORD + " 3") instanceof ListFavouritesCommand);
    }

    @Test
    public void parseCommand_impts() throws Exception {
        ListImportantCommand command = (ListImportantCommand) parser.parseCommand(
                ListImportantCommand.COMMAND_WORD);
        assertEquals(new ListImportantCommand(), command);
        assertTrue(parser.parseCommand(
                ListImportantCommand.COMMAND_WORD + " 3") instanceof ListImportantCommand);
    }

    @Test
    public void parseCommand_deadline() throws Exception {
        List<Deadline> deadlines = Arrays.asList(new Deadline("description", "11/11/2022"));
        DeadlineList deadlineList = new DeadlineList(deadlines);
        DeadlineCommand command = (DeadlineCommand) parser.parseCommand(
                DeadlineCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()
                        + " d/description 11/11/2022");
        assertEquals(new DeadlineCommand(INDEX_FIRST_PERSON, deadlineList), command);
    }

    @Test
    public void parseDetailedViewCommand_add() throws Exception {
        AddCommand command = (AddCommand) parser.parseDetailedViewCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseDetailedViewCommand_exit() throws Exception {
        assertTrue(parser.parseDetailedViewCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseDetailedViewCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseDetailedViewCommand_help() throws Exception {
        assertTrue(parser.parseDetailedViewCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseDetailedViewCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseDetailedViewCommand_edit() throws Exception {
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseDetailedViewCommand(EditCommand.COMMAND_WORD + " "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(descriptor), command);

        // same command with index given -> index ignored
        EditCommand commandWithIndex = (EditCommand) parser.parseDetailedViewCommand(EditCommand.COMMAND_WORD
                + " 1 " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(command, commandWithIndex);
    }

    @Test
    public void parseDetailedViewCommand_deadline() throws Exception {
        String deadlineString = "d/test 21/02/2022";
        DeadlineList deadlineList = ParserUtil.parseDeadlines(Collections.singletonList(deadlineString));
        DeadlineCommand command = (DeadlineCommand) parser.parseDetailedViewCommand(DeadlineCommand.COMMAND_WORD
                + " " + deadlineString);
        assertEquals(command, new DeadlineCommand(deadlineList));

        // same command with index given -> index ignored
        DeadlineCommand commandWithIndex = (DeadlineCommand) parser.parseDetailedViewCommand(
                DeadlineCommand.COMMAND_WORD + " 2 " + deadlineString);
        assertEquals(commandWithIndex, command);
    }

    @Test
    public void parseDetailedViewCommand_note() throws Exception {
        String note = "likes green";
        NoteCommand command = (NoteCommand) parser.parseDetailedViewCommand(NoteCommand.COMMAND_WORD
                + " " + PREFIX_NOTE + note);
        assertEquals(command, new NoteCommand(note));

        // same command with index given -> index ignored
        NoteCommand commandWithIndex = (NoteCommand) parser.parseDetailedViewCommand(NoteCommand.COMMAND_WORD
                + " 2 " + PREFIX_NOTE + note);
        assertEquals(commandWithIndex, command);
    }

    @Test
    public void parseDetailedViewCommand_fav() throws Exception {
        FavouriteCommand command = (FavouriteCommand) parser.parseDetailedViewCommand(FavouriteCommand.COMMAND_WORD);
        assertEquals(command, new FavouriteCommand());

        // same command with index given -> index ignored
        FavouriteCommand commandWithIndex = (FavouriteCommand) parser.parseDetailedViewCommand(
                FavouriteCommand.COMMAND_WORD + " 2");
        assertEquals(commandWithIndex, command);
    }

    @Test
    public void parseDetailedViewCommand_impt() throws Exception {
        HighImportanceCommand command = (HighImportanceCommand) parser.parseDetailedViewCommand(
                HighImportanceCommand.COMMAND_WORD);
        assertEquals(command, new HighImportanceCommand());

        // same command with index given -> index ignored
        HighImportanceCommand commandWithIndex = (HighImportanceCommand) parser.parseDetailedViewCommand(
                HighImportanceCommand.COMMAND_WORD + " 2");
        assertEquals(commandWithIndex, command);
    }

    @Test
    public void parseDetailedViewCommand_assign() throws Exception {
        String tag = "tag";
        AssignTagCommand command = (AssignTagCommand) parser.parseDetailedViewCommand(
                AssignTagCommand.COMMAND_WORD + " " + tag);
        assertEquals(command, new AssignTagCommand(tag));

        // same command with index given -> index ignored
        AssignTagCommand commandWithIndex = (AssignTagCommand) parser.parseDetailedViewCommand(
                AssignTagCommand.COMMAND_WORD + " 2 " + tag);
        assertEquals(commandWithIndex, command);
    }

    @Test
    public void parseDetailedViewCommand_unassign() throws Exception {
        String tag = "tag";
        UnassignTagCommand command = (UnassignTagCommand) parser.parseDetailedViewCommand(
                UnassignTagCommand.COMMAND_WORD + " " + tag);
        assertEquals(command, new UnassignTagCommand(tag));

        // same command with index given -> index ignored
        UnassignTagCommand commandWithIndex = (UnassignTagCommand) parser.parseDetailedViewCommand(
                UnassignTagCommand.COMMAND_WORD + " 2 " + tag);
        assertEquals(commandWithIndex, command);
    }

    @Test
    public void parseDetailedViewCommand_tag() throws Exception {
        String tag = "tag";
        // same command parsed regardless of panel view
        CreateTagCommand parsedCommand = (CreateTagCommand) parser.parseCommand(
                CreateTagCommand.COMMAND_WORD + " " + tag);
        CreateTagCommand detailedViewParsedCommand = (CreateTagCommand) parser.parseDetailedViewCommand(
                CreateTagCommand.COMMAND_WORD + " " + tag);
        assertEquals(parsedCommand, detailedViewParsedCommand);
    }

    @Test
    public void parseDetailedViewCommand_listCommands_throwException() {
        assertThrows(ParseException.class, MESSAGE_INCOMPATIBLE_VIEW_MODE, ()
            -> parser.parseDetailedViewCommand(ClearCommand.COMMAND_WORD));
        assertThrows(ParseException.class, MESSAGE_INCOMPATIBLE_VIEW_MODE, ()
            -> parser.parseDetailedViewCommand(DeleteCommand.COMMAND_WORD + " 1"));
        assertThrows(ParseException.class, MESSAGE_INCOMPATIBLE_VIEW_MODE, ()
            -> parser.parseDetailedViewCommand(DeleteTagCommand.COMMAND_WORD + " tag"));
        assertThrows(ParseException.class, MESSAGE_INCOMPATIBLE_VIEW_MODE, ()
            -> parser.parseDetailedViewCommand(FindCommand.COMMAND_WORD + " alex"));
        assertThrows(ParseException.class, MESSAGE_INCOMPATIBLE_VIEW_MODE, ()
            -> parser.parseDetailedViewCommand(FindTagCommand.COMMAND_WORD + " tag"));
        assertThrows(ParseException.class, MESSAGE_INCOMPATIBLE_VIEW_MODE, ()
            -> parser.parseDetailedViewCommand(ListFavouritesCommand.COMMAND_WORD));
        assertThrows(ParseException.class, MESSAGE_INCOMPATIBLE_VIEW_MODE, ()
            -> parser.parseDetailedViewCommand(ListImportantCommand.COMMAND_WORD));
        assertThrows(ParseException.class, MESSAGE_INCOMPATIBLE_VIEW_MODE, ()
            -> parser.parseDetailedViewCommand(SortsCommand.COMMAND_WORD + " address"));
        assertThrows(ParseException.class, MESSAGE_INCOMPATIBLE_VIEW_MODE, ()
            -> parser.parseDetailedViewCommand(ViewCommand.COMMAND_WORD + " 1"));
    }

    @Test
    public void parseCommand_impt() throws Exception {
        HighImportanceCommand command = (HighImportanceCommand) parser.parseCommand(
                HighImportanceCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new HighImportanceCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_addimg() throws Exception {
        AddImageCommand command = (AddImageCommand) parser.parseCommand(
                AddImageCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new AddImageCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_images() throws Exception {
        ImagesCommand command = (ImagesCommand) parser.parseCommand(
                ImagesCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new ImagesCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_note() throws Exception {
        NoteCommand command = (NoteCommand) parser.parseCommand(
                NoteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " r/note");
        assertEquals(new NoteCommand(INDEX_FIRST_PERSON, "note"), command);
    }

    @Test
    public void parseCommand_delimg() throws Exception {
        Index indexFirstImage = Index.fromOneBased(1);
        DeleteImageCommand command = (DeleteImageCommand) parser.parseCommand(
                DeleteImageCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " i/"
                        + indexFirstImage.getOneBased());
        assertEquals(new DeleteImageCommand(INDEX_FIRST_PERSON, indexFirstImage), command);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        SortsCommand command = (SortsCommand) parser.parseCommand(
                SortsCommand.COMMAND_WORD + " " + "name");
        assertEquals(new SortsCommand("name"), command);
    }

}
