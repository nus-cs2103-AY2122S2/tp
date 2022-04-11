package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PERSON_NAME;
import static seedu.address.logic.commands.CommandTestUtil.PERSON_AMOUNT_1;
import static seedu.address.logic.commands.CommandTestUtil.PERSON_AMOUNT_2;
import static seedu.address.logic.commands.CommandTestUtil.PERSON_NAME_1;
import static seedu.address.logic.commands.CommandTestUtil.PERSON_NAME_2;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_1_AMOUNT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_1_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalExpenses.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PersonOwesCommand;
import seedu.address.model.expense.Amount;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.testutil.PersonBuilder;


public class PersonOwesCommandParserTest {
    private PersonOwesCommandParser parser = new PersonOwesCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).build();
        System.out.println(expectedPerson);

        // multiple names - last name accepted
        assertParseSuccess(parser, PERSON_NAME_2 + PERSON_NAME_1
                        + PERSON_AMOUNT_1,
                new PersonOwesCommand(expectedPerson));

        // multiple amounts - last amount accepted
        assertParseSuccess(parser, PERSON_NAME_1 + PERSON_AMOUNT_2
                        + PERSON_AMOUNT_1,
                new PersonOwesCommand(expectedPerson));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PersonOwesCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, PERSON_NAME_1 + VALID_PERSON_1_AMOUNT,
                expectedMessage);

        // missing amount prefix
        assertParseFailure(parser, VALID_PERSON_1_NAME + PERSON_AMOUNT_1,
                expectedMessage);


        // all prefixes missing
        assertParseFailure(parser, VALID_PERSON_1_NAME + VALID_PERSON_1_AMOUNT,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_PERSON_NAME + PERSON_AMOUNT_1,
                PersonName.MESSAGE_CONSTRAINTS);

        // invalid amount
        assertParseFailure(parser, PERSON_NAME_2 + INVALID_AMOUNT_DESC,
                Amount.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + PERSON_NAME_1
                        + PERSON_AMOUNT_1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PersonOwesCommand.MESSAGE_USAGE));
    }
}
