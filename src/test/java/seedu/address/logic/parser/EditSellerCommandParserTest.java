package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.VALID_HOUSE_TYPE_1;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.VALID_LOCATION_1;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.VALID_PR_2;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditSellerCommand;
import seedu.address.testutil.EditSellerDescriptorBuilder;

class EditSellerCommandParserTest {

    public static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditSellerCommand.MESSAGE_USAGE);

    public static final String VALID_NAME = "Bob";
    public static final String VALID_PHONE = "98765432";
    public static final String VALID_TAG = "friend";
    public static final String VALID_HOUSE_TYPE = VALID_HOUSE_TYPE_1;
    public static final String VALID_LOCATION = VALID_LOCATION_1;
    public static final String VALID_ADDRESS = "Bishan ave 3 Block 123";
    public static final String VALID_PRICE_RANGE = VALID_PR_2;


    public final EditSellerCommandParser parser = new EditSellerCommandParser();
    private EditSellerCommand.EditSellerDescriptor descriptorStub1 = new EditSellerDescriptorBuilder()
        .withName(VALID_NAME).build();

    private EditSellerCommand.EditSellerDescriptor descriptorStub2 = new EditSellerDescriptorBuilder()
        .withPhone(VALID_PHONE).build();

    private EditSellerCommand.EditSellerDescriptor descriptorStub3 = new EditSellerDescriptorBuilder()
        .withTags(VALID_TAG).build();

    private EditSellerCommand.EditSellerDescriptor descriptorStub4 = new EditSellerDescriptorBuilder()
        .withHouseType(VALID_HOUSE_TYPE_1).build();

    private EditSellerCommand.EditSellerDescriptor descriptorStub5 = new EditSellerDescriptorBuilder()
        .withLocation(VALID_LOCATION_1).build();

    private EditSellerCommand.EditSellerDescriptor descriptorStub6 = new EditSellerDescriptorBuilder()
        .withPriceRange(10000, 20000).build();

    private EditSellerCommand.EditSellerDescriptor descriptorStub7 = new EditSellerDescriptorBuilder()
        .withAddress(VALID_ADDRESS).build();

    @Test
    void parse_editSeller_success() {
        Index stubIndex = Index.fromOneBased(2);
        String index = "2 ";
        assertParseSuccess(parser, index + PREFIX_NAME + VALID_NAME,
            new EditSellerCommand(stubIndex, descriptorStub1));

        assertParseSuccess(parser, index + PREFIX_PHONE + VALID_PHONE,
            new EditSellerCommand(stubIndex, descriptorStub2));

        assertParseSuccess(parser, index + PREFIX_TAG + VALID_TAG,
            new EditSellerCommand(stubIndex, descriptorStub3));

        assertParseSuccess(parser, index + PREFIX_HOUSE_TYPE + VALID_HOUSE_TYPE,
            new EditSellerCommand(stubIndex, descriptorStub4));

        assertParseSuccess(parser, index + PREFIX_LOCATION + VALID_LOCATION,
            new EditSellerCommand(stubIndex, descriptorStub5));

        assertParseSuccess(parser, index + PREFIX_PRICE_RANGE + VALID_PRICE_RANGE,
            new EditSellerCommand(stubIndex, descriptorStub6));

        assertParseSuccess(parser, index + PREFIX_ADDRESS + VALID_ADDRESS,
            new EditSellerCommand(stubIndex, descriptorStub7));
    }

    @Test
    void parse_noFieldEdited_throwsParseException() {
        String index = "2 ";
        assertParseFailure(parser, index, EditSellerCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    void parse_invalidIndex_throwsParseException() {
        String index = "-1 ";
        assertParseFailure(parser, index + PREFIX_NAME + VALID_NAME,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditSellerCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditSellerCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_weirdString_failure() {

        assertParseFailure(parser, "This is weird", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "//!", MESSAGE_INVALID_FORMAT);
    }

}
