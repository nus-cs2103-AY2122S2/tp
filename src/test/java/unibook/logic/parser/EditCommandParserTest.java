package unibook.logic.parser;

/*
public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            MESSAGE_INVALID_COMMAND_FORMAT + EditCommand.PERSON_MESSAGE_USAGE;

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {

        // no field specified
        assertParseFailure(parser, " 1 o/person ", EditCommand.MESSAGE_NOT_EDITED);

//         no option and no field specified
         assertParseFailure(parser, " 1 ", MESSAGE_INVALID_FORMAT);
    }

        @Test
        public void parse_invalidPreamble_failure() {
            // negative index
            assertParseFailure(parser, "edit -5" + " o/person " + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

            // zero index
            assertParseFailure(parser, "edit 0" + " o/person " + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT );

            // invalid arguments being parsed as preamble
            assertParseFailure(parser, "edit 1 some random string o/person", MESSAGE_INVALID_FORMAT);

            // invalid prefix being parsed as preamble
            assertParseFailure(parser, "edit 1 i/ string o/person", MESSAGE_INVALID_FORMAT);
        }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + " o/person " + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
        // invalid phone
        assertParseFailure(parser, "1" + " o/person " + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);
        // invalid email
        assertParseFailure(parser, "1" + " o/person " + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS);
        // invalid tag
        assertParseFailure(parser, "1" + " o/person " + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + " o/person "
                + INVALID_PHONE_DESC + EMAIL_DESC_ALEX, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + " o/person " + PHONE_DESC_BERNICE
                + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1"
                                    + " o/person "
                                    + TAG_DESC_FRIEND
                                    + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1"
                                   + " o/person "
                                   + TAG_DESC_FRIEND
                                   + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1"
                                    + " o/person "
                                    + TAG_EMPTY
                                    + TAG_DESC_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1"
                                    + " o/person "
                                    + INVALID_NAME_DESC
                                    + INVALID_EMAIL_DESC, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " o/person " + PHONE_DESC_BERNICE + TAG_DESC_HUSBAND
                + EMAIL_DESC_ALEX + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " o/person " + PHONE_DESC_BERNICE + EMAIL_DESC_ALEX;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + " o/person " + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + " o/person " + PHONE_DESC_ALEX;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + " o/person " + EMAIL_DESC_ALEX;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + " o/person " + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " o/person " + PHONE_DESC_ALEX + EMAIL_DESC_ALEX
                + TAG_DESC_FRIEND + PHONE_DESC_ALEX + EMAIL_DESC_ALEX + TAG_DESC_FRIEND
                + PHONE_DESC_BERNICE + EMAIL_DESC_BERNICE + TAG_DESC_HUSBAND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " o/person " + INVALID_PHONE_DESC + PHONE_DESC_BERNICE;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + " o/person " + EMAIL_DESC_BERNICE + INVALID_PHONE_DESC
                + PHONE_DESC_BERNICE;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + " o/person " + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
*/

