package ulearn;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.jfree.data.category.DefaultCategoryDataset;

public class Main {

    private static List<Grants> Grants;
    private static final List<Double> salonBarbershopTypeBusiness = new ArrayList<>();
    private static SQLiteHandler sql;

    public static void main(String[] main) throws IOException, SQLException {
        // заполняем лист с данными с помощью метода readToObj
        Grants = new CSVHandler().readToObj();
        // создаем эксемляр класса обработчика SQL
        sql = new SQLiteHandler();
        // идём по листу с классами Grants
        for (var i : Grants) {
            // добавляем объект в БД
            sql.add(i);
            if (i.getTypeBusiness().contains("Salon/Barbershop")) {
                // для второго задания сохраним гранты из типа бизнеза "Salon/Barbershop"
                salonBarbershopTypeBusiness.add(i.getAmount());
            }
        }
        // выводим данные из БД в консоль
        var rs = sql.executeQuery("select * from grants");
        var col = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            for (var i = 1; i <= col; i++) {
                System.out.print(rs.getString(i) + " ");
            }
            System.out.println();
        }
        Task1();
        Task2();
        Task3();
    }

    // Постройте график по среднему количеству землетрясений для каждого года
    private static void Task1() throws IOException {
        var dataset = new DefaultCategoryDataset();
        var grantsByYear = new HashMap<Integer, Integer>();

        // собираем данные о размере гранта
        for (var i : Grants) {
            var year = i.getFiscalYear();
            if (grantsByYear.containsKey(year)) {
                grantsByYear.put(year, grantsByYear.get(year) + 1);
            } else {
                grantsByYear.put(year, 1);
            }
        }

        // сортируем hashmap grantsByYear по ключу, чтобы иметь порядок вида
        var map = grantsByYear.entrySet()
            .stream().sorted(Entry.comparingByKey()).collect(
                Collectors.toMap(Entry::getKey, Entry::getValue,
                    (e1, e2) -> e1, LinkedHashMap::new));

        // заполняем датасет для графика
        for (var key : map.keySet()) {
            dataset.addValue(map.get(key), "year", key);
        }

        // создаём и сохраняем график
        ChartCreator.create("Chart of jobs for each year",
            "Year",
            "Number of Jobs",
            dataset,
            "src\\main\\java\\ulearn\\Chart.jpg");

        System.out.println("\nTask1 - The graph has been successfully created.");
    }

    private static void Task2() {
        System.out.println("Task2 - The average grant size for the type of business \"Salon/Barbershop\": "
            + salonBarbershopTypeBusiness.stream().mapToDouble(a -> a).average().getAsDouble()
        );
    }

    private static void Task3() throws SQLException {
        var result = """ 
            SELECT type_business, number_jobs FROM grants
            WHERE amount <= '55000'
            ORDER BY number_jobs DESC
            """;

        var rs = sql.executeQuery(result);
        System.out.println(String.format(
            "Task3 - The type of business that provided the most jobs where the grant amount does not exceed $55,000.00 is %s, the jobs numbers that offers is %s.",
            rs.getString(1), rs.getString(2))
        );
    }
}
