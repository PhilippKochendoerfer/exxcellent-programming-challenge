package de.exxcellent.challenge.data.json;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.data.DataFileReader;
import de.exxcellent.challenge.exception.InvalidDataException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class to read data from a JSON file and create a Data object. Expects the
 * file to contain a JSON array of flat objects, e.g.
 * {@code [{"Day":"1","MxT":"88"}, {"Day":"2","MxT":"84"}]}. All objects are
 * expected to have the same set of keys as the first one; the header column
 * order is taken from the first object.
 *
 * @author Philipp Kochendörfer <philipp.kochendoerfer@outlook.com>
 */
public class JsonReader extends DataFileReader {

    /**
     * Opens the JSON file at the given path for reading.
     *
     * @param filePath path to the JSON file to read.
     * @throws FileNotFoundException if no file exists at the given path.
     */
    public JsonReader(String filePath) throws FileNotFoundException {
        super(filePath);
    }

    /**
     * Reads data from the JSON file and creates a Data object.
     *
     * @return The Data object containing the header and rows from the JSON file.
     * @throws IOException          if the file cannot be read.
     * @throws InvalidDataException if the file does not contain a well-formed
     *                              JSON array of objects, the array is empty, or
     *                              an object is missing a key present in the header.
     */
    @Override
    public Data readData() throws IOException, InvalidDataException {
        String jsonContent = bufferedReader.lines().collect(Collectors.joining());

        try {
            JSONArray jsonArray = new JSONArray(jsonContent);
            if (jsonArray.length() == 0) {
                throw new InvalidDataException("JSON array must contain at least one object.");
            }

            String[] header = jsonArray.getJSONObject(0).keySet().toArray(new String[0]);

            List<String[]> rows = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                rows.add(toRow(jsonArray.getJSONObject(i), header));
            }

            return new Data(header, rows);
        } catch (JSONException e) {
            throw new InvalidDataException("Malformed JSON: " + e.getMessage());
        }
    }

    /**
     * Builds one row by looking up each header column's value in the given
     * JSON object by name, so the row lines up with {@code header} regardless
     * of the object's own key order.
     */
    private static String[] toRow(JSONObject jsonObject, String[] header) {
        String[] row = new String[header.length];
        for (int i = 0; i < header.length; i++) {
            row[i] = String.valueOf(jsonObject.get(header[i]));
        }
        return row;
    }

}
