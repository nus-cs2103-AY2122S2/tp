package seedu.address.model.excel;

import com.fasterxml.jackson.databind.JsonNode;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.AddCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.ui.MainWindow;
import seedu.address.model.excel.ExcelToJSONConverter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ImportFileParser {

    List<String> list;

    public List<String> JsonToPerson(File file) throws ParseException {

        list = new ArrayList<>();
        ExcelToJSONConverter converter = new ExcelToJSONConverter();
        JsonNode data = converter.excelToJson(file);
        int len = data.get("Sheet1").size();
        for(int i = 0; i < len; i++) {
            String res = "";

            JsonNode person = data.get("Sheet1").get(i);
            String name = person.get("NAME").textValue() + " ";
            String block = person.get("BLOCK").textValue() + " ";
            String facaulty = person.get("FACAULTY").textValue()+ " ";
            String phone = person.get("PHONE").textValue() + " ";
            String email  = person.get("EMAIL").textValue() + " ";
            String address = person.get("ADDRESS").textValue() + " ";
            String mc = person.get("MATRICULATION NUMBER").textValue() + " ";
            String cs = person.get("COVID STATUS").textValue() + " ";
            String tag = person.get("TAG").textValue() + " ";

            res = "add " + name + block + facaulty + phone + email + address + mc + cs + tag;
            list.add(res);

        }
        return list;
    }
}
