package datatypesutility.model.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class UtilityReader {
    BufferedReader bufferedReader;

    public void setBufferedReader(String inputFileName) throws IOException {
        bufferedReader = new BufferedReader(new FileReader(inputFileName, StandardCharsets.UTF_8));
    }

    public String getLine() throws IOException {
        return bufferedReader.readLine();
    }
}
