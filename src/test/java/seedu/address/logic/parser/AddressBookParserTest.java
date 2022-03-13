package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSURANCE_PACKAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.CombineContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.InsurancePackageContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagsContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.PredicatesListBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
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
        Person person = new PersonBuilder().build();
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
    public void parseCommand_findOneFieldName() throws Exception {
        PredicatesListBuilder predicatesListBuilder = new PredicatesListBuilder();
        List<String> nameKeywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_NAME + nameKeywords.stream().collect(Collectors.joining(" ")));
        CombineContainsKeywordsPredicate namePredicate = new CombineContainsKeywordsPredicate(
                predicatesListBuilder.addNamePredicate(new NameContainsKeywordsPredicate(nameKeywords)).build());
        assertEquals(new FindCommand(namePredicate), command);
    }

    @Test
    public void parseCommand_findOneFieldPhone() throws Exception {
        PredicatesListBuilder predicatesListBuilder = new PredicatesListBuilder();
        List<String> phoneKeywords = Arrays.asList("11111111", "22222222", "33333333");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_PHONE
                        + phoneKeywords.stream().collect(Collectors.joining(" ")));
        CombineContainsKeywordsPredicate phonePredicate = new CombineContainsKeywordsPredicate(
                predicatesListBuilder.addPhonePredicate(new PhoneContainsKeywordsPredicate(phoneKeywords)).build());
        assertEquals(new FindCommand(phonePredicate), command);
    }

    @Test
    public void parseCommand_findOneFieldTags() throws Exception {
        PredicatesListBuilder predicatesListBuilder = new PredicatesListBuilder();
        List<String> tagsKeywords = Arrays.asList("friends", "church", "school");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_TAG
                        + tagsKeywords.stream().collect(Collectors.joining(" ")));
        CombineContainsKeywordsPredicate tagsPredicate = new CombineContainsKeywordsPredicate(
                predicatesListBuilder.addTagsPredicate(new TagsContainsKeywordsPredicate(tagsKeywords)).build());
        assertEquals(new FindCommand(tagsPredicate), command);
    }

    @Test
    public void parseCommand_findOneFieldEmail() throws Exception {
        PredicatesListBuilder predicatesListBuilder = new PredicatesListBuilder();
        List<String> emailKeywords = Arrays.asList("cool12@mail.com", "example@test.com", "abc123@abc.com");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_EMAIL
                        + emailKeywords.stream().collect(Collectors.joining(" ")));
        CombineContainsKeywordsPredicate emailPredicate = new CombineContainsKeywordsPredicate(
                predicatesListBuilder.addEmailPredicate(new EmailContainsKeywordsPredicate(emailKeywords)).build());
        assertEquals(new FindCommand(emailPredicate), command);
    }

    @Test
    public void parseCommand_findOneFieldInsurancePackage() throws Exception {
        PredicatesListBuilder predicatesListBuilder = new PredicatesListBuilder();
        List<String> insurancePackageKeywords = Arrays.asList("life", "premium", "elite");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_INSURANCE_PACKAGE
                        + insurancePackageKeywords.stream().collect(Collectors.joining(" ")));
        CombineContainsKeywordsPredicate insurancePackagePredicate = new CombineContainsKeywordsPredicate(
                predicatesListBuilder.addInsurancePackagePredicate(
                        new InsurancePackageContainsKeywordsPredicate(insurancePackageKeywords)).build());
        assertEquals(new FindCommand(insurancePackagePredicate), command);
    }

    @Test
    public void parseCommand_findOneFieldAddress() throws Exception {
        PredicatesListBuilder predicatesListBuilder = new PredicatesListBuilder();
        List<String> addressKeywords = Arrays.asList("jurong", "clementi", "redhill");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_ADDRESS
                        + addressKeywords.stream().collect(Collectors.joining(" ")));
        CombineContainsKeywordsPredicate addressPredicate = new CombineContainsKeywordsPredicate(
                predicatesListBuilder.addAddressPredicate(
                        new AddressContainsKeywordsPredicate(addressKeywords)).build());
        assertEquals(new FindCommand(addressPredicate), command);
    }

    @Test
    public void parseCommand_findMultipleFields() throws Exception {
        PredicatesListBuilder predicatesListBuilder = new PredicatesListBuilder();
        List<String> nameKeywords = Arrays.asList("foo", "bar", "baz");
        List<String> tagsKeywords = Arrays.asList("friends", "church", "school");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD
                        + " " + PREFIX_NAME + nameKeywords.stream().collect(Collectors.joining(" "))
                        + " " + PREFIX_TAG + tagsKeywords.stream().collect(Collectors.joining(" ")));
        CombineContainsKeywordsPredicate manyPredicates = new CombineContainsKeywordsPredicate(
                predicatesListBuilder
                        .addNamePredicate(new NameContainsKeywordsPredicate(nameKeywords))
                        .addTagsPredicate(new TagsContainsKeywordsPredicate(tagsKeywords))
                        .build());
        assertEquals(new FindCommand(manyPredicates), command);
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
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
