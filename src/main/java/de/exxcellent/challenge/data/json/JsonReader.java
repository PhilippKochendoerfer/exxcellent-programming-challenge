package de.exxcellent.challenge.data.json;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.exxcellent.challenge.data.Data;
import de.exxcellent.challenge.data.DataReader;
import de.exxcellent.challenge.exception.InvalidDataException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class to read data from a JSON input stream and create a Data object.
 * Expects the stream to contain a JSON array of flat objects, e.g.
 * {@code [{"Day":"1","MxT":"88"}, {"Day":"2","MxT":"84"}]}. All objects are
 * expected to have the same set of keys as the first one; the header column
 * order is taken from the first object.
 *
 * @author Philipp Kochendörfer <philipp.kochendoerfer@outlook.com>
 */
public class JsonReader extends DataReader {

    /**
     * Creates a new JsonReader reading from the given input stream.
     *
     * @param inputStream The input stream to read JSON data from.
     */
    public JsonReader(InputStream inputStream) {
        super(inputStream);
    }

    /**
     * Reads data from the JSON input stream and creates a Data object.
     *
     * @return The Data object containing the header and rows from the JSON data.
     * @throws IOException          if the input stream cannot be read.
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
