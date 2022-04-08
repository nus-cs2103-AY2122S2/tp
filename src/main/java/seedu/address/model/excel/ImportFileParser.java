package seedu.address.model.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Parse import file into command format
 */
public class ImportFileParser {

    private List<String> resultList;

    /**
     * Convert result file into list of command string
     * @param file
     * @return list of command for adding student records
     */
    public List<String> jsonToPerson(File file) throws NullPointerException {
        resultList = new ArrayList<>();
        ExcelToJsonConverter converter = new ExcelToJsonConverter();
        JsonNode data = converter.excelToJson(file);
        int len = data.get("Sheet1").size();
        for (int i = 0; i < len; i++) {
            String res = "";
            JsonNode person = data.get("Sheet1").get(i);
            String name = "n/" + person.get("NAME").textValue() + " ";
            String block = "b/" + person.get("BLOCK").textValue() + " ";
            String faculty = "f/" + person.get("FACULTY").textValue().toUpperCase() + " ";
            String phone = "p/" + person.get("PHONE").asInt() + " ";
            String email = "e/" + person.get("EMAIL").textValue() + " ";
            String address = "a/" + person.get("ADDRESS").textValue() + " ";
            String mc = "mc/" + person.get("MATRICULATION NUMBER").textValue() + " ";
            String cs = "cs/" + person.get("COVID STATUS").textValue() + " ";
            String tag = "t/" + person.get("TAG").textValue() + " ";
            res = "add " + name + block + faculty + phone + email + address + mc + cs + tag;
            resultList.add(res);
        }
        return resultList;
    }
}

