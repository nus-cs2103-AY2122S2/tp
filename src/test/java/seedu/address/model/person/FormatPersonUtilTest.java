package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;

class FormatPersonUtilTest {
    private FormatPersonUtil fpDefault = new FormatPersonUtil();
    private FormatPersonUtil fpCsv = new FormatPersonUtil("csv");
    private FormatPersonUtil fpJson = new FormatPersonUtil("json");
    private List<Prefix> prefixes = List.of(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
            PREFIX_ADDRESS, PREFIX_MODULE, PREFIX_COMMENT);
    private String ls = System.lineSeparator();

    @Test
    public void formatPerson_default_validInputSuccess() throws Exception {
        String formattedPerson = fpDefault.formatPerson(ALICE, prefixes);

        assertEquals(helperPerson(ALICE, "\n", prefixes), formattedPerson);
    }

    @Test
    public void formatAddressBook_default_validInputSuccess() throws Exception {
        String formattedPerson = fpDefault.formatAddressBook(List.of(ALICE, BENSON), prefixes);

        StringBuilder sb = new StringBuilder();
        sb.append(helperPerson(ALICE, "\n", prefixes));
        sb.append("\n");
        sb.append(helperPerson(BENSON, "\n", prefixes));

        assertEquals(sb.toString(), formattedPerson);
    }

    @Test
    public void formatPerson_csv_validInputSuccess() throws Exception {
        String formattedPerson = fpCsv.formatPerson(ALICE, prefixes);

        assertEquals(helperPerson(ALICE, " | ", prefixes), formattedPerson);
    }

    @Test
    public void formatAddressBook_csv_validInputSuccess() throws Exception {
        String formattedPerson = fpCsv.formatAddressBook(List.of(ALICE, BENSON),
                prefixes);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < prefixes.size(); i++) {
            sb.append(prefixes.get(i).getDescription());
            if (i != prefixes.size() - 1) {
                sb.append(" | ");
            }
        }
        sb.append("\n");
        sb.append(helperPerson(ALICE, " | ", prefixes));
        sb.append("\n");
        sb.append(helperPerson(BENSON, " | ", prefixes));

        assertEquals(sb.toString(), formattedPerson);
    }

    @Test
    public void formatPerson_json_validInputSuccess() throws Exception {
        String formattedPerson = fpJson.formatPerson(ALICE, prefixes);

        assertEquals(helperPersonJson(ALICE, prefixes), formattedPerson);
    }

    private String helperPersonJson(Person p, List<Prefix> prefixes) {
        String tab = "  ";

        StringBuilder sb = new StringBuilder();
        sb.append("{" + ls);
        for (int i = 0; i < prefixes.size(); i++) {
            sb.append(tab);
            sb.append("\"");
            sb.append(prefixes.get(i).getDescription());
            sb.append("\"");
            sb.append(" : ");

            if (prefixes.get(i).equals(PREFIX_MODULE)) {
                String module = FormatPersonUtil.getPersonField(p, prefixes.get(i));
                sb.append("[ \"");
                sb.append(module.substring(2, module.length() - 2));
                sb.append("\" ]");
            } else {
                sb.append("\"");
                sb.append(FormatPersonUtil.getPersonField(p, prefixes.get(i)));
                sb.append("\"");
            }

            if (i != prefixes.size() - 1) {
                sb.append("," + ls);
            } else {
                sb.append(ls);
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private String helperPerson(Person p, String delimiter, List<Prefix> prefixes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < prefixes.size(); i++) {
            sb.append(FormatPersonUtil.getPersonField(p, prefixes.get(i)));
            if (i != prefixes.size() - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }
}
