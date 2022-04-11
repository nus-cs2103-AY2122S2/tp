package woofareyou.logic.parser;

import static woofareyou.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static woofareyou.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static woofareyou.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static woofareyou.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static woofareyou.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static woofareyou.logic.commands.CommandTestUtil.INVALID_OWNER_NAME_DESC;
import static woofareyou.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static woofareyou.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static woofareyou.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static woofareyou.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static woofareyou.logic.commands.CommandTestUtil.OWNER_NAME_DESC_AMY;
import static woofareyou.logic.commands.CommandTestUtil.OWNER_NAME_DESC_BOB;
import static woofareyou.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static woofareyou.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static woofareyou.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static woofareyou.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static woofareyou.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static woofareyou.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static woofareyou.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_OWNER_NAME_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static woofareyou.logic.parser.CommandParserTestUtil.assertParseFailure;
import static woofareyou.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static woofareyou.testutil.TypicalPets.AMY;
import static woofareyou.testutil.TypicalPets.BOB;

import org.junit.jupiter.api.Test;

import woofareyou.logic.commands.AddCommand;
import woofareyou.model.pet.Address;
import woofareyou.model.pet.Name;
import woofareyou.model.pet.OwnerName;
import woofareyou.model.pet.Pet;
import woofareyou.model.pet.Phone;
import woofareyou.model.tag.Tag;
import woofareyou.testutil.PetBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Pet expectedPet = new PetBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + OWNER_NAME_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPet));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + OWNER_NAME_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPet));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + OWNER_NAME_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPet));

        // multiple ownerNames - last ownerName accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + OWNER_NAME_DESC_AMY + OWNER_NAME_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPet));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + OWNER_NAME_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPet));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Pet expectedPet = new PetBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + OWNER_NAME_DESC_AMY + ADDRESS_DESC_AMY,
                new AddCommand(expectedPet));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + OWNER_NAME_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + OWNER_NAME_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing ownerName prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_OWNER_NAME_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + OWNER_NAME_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_OWNER_NAME_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + OWNER_NAME_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + OWNER_NAME_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND, Phone.MESSAGE_CONSTRAINTS);

        // invalid ownerName
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_OWNER_NAME_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND, OwnerName.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + OWNER_NAME_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + OWNER_NAME_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // invalid number of tags
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + OWNER_NAME_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Tag.MESSAGE_SIZE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + OWNER_NAME_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + OWNER_NAME_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
