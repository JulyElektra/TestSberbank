package other;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataExtractor {

	/*
	 * Метод получает тестовые данные из файла в виде коллекции строк
	 */
    public static Collection<String[]> getTestData(String fileName) throws IOException {
        List<String[]> records = new ArrayList<String[]>();
        String record;
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        while ((record = file.readLine()) != null) {
            String[] fields = record.split(",");
            records.add(fields);
        }
        file.close();
        return records;
    }
}
