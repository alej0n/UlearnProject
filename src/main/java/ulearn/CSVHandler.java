package ulearn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVHandler {

    public List<Grants> readToObj() {
        String path = "src\\main\\java\\ulearn\\Гранты.csv";
        List<Grants> result = new ArrayList<>();

        try (var br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                // вызываем метод который вернет нам массив из 5 элементов
                var dataGrants = splitData(line);
                // создаём объект заполняя его данными
                result.add(new Grants(dataGrants));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

     private String[] splitData(String line) {
        if (line.contains("\"")) {
            // Список для хранения элементов
            List<String> elementos = new ArrayList<>();

            // Использовать регулярное выражение для поиска элементов, заключенных в кавычки
            Pattern pattern = Pattern.compile("\"([^\"]*?)\"|([^,]+)");
            Matcher matcher = pattern.matcher(line);

            // Выполнить итерацию и добавить элементы в список
            while (matcher.find()) {
                String elemento = matcher.group(1); // Получить группу в кавычках
                if (elemento == null) {
                    elemento = matcher.group(2); // Получить группу без кавычек
                }
                elementos.add(elemento.trim());
            }

            // Проверить и при необходимости добавить значение по умолчанию
            if (elementos.size() < 6) {
                elementos.add("0");
            }
            return elementos.toArray(new String[6]);
        }
        return line.split(",");
    }
}
