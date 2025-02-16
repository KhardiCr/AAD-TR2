package json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.reflect.Type;


public class JsonReader {
    public static DataSet loadJson(String filePath) {
        try {
            InputStreamReader reader = new InputStreamReader(
                    JsonReader.class.getClassLoader().getResourceAsStream(filePath));

            Gson gson = new Gson();
            Type datasetType = new TypeToken<DataSet>() {}.getType();
            DataSet dataset = gson.fromJson(reader, datasetType);
            reader.close();
            return dataset;
        } catch (NullPointerException e) {
            System.out.println("El dataset que has indicado no existe o no está en la ruta correcta.");
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
