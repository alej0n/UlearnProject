package ulearn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteHandler {

    private Connection conn;
    private Statement stmt;

    public SQLiteHandler() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:grants.db");
        stmt = conn.createStatement();
        addTable();
    }

    public void addTable() throws SQLException {
        var createTableSqlQuery = """
            CREATE TABLE IF NOT EXISTS grants (
             name_company text not null PRIMARY KEY,
             adress text not null,
             amount real not null,
             fiscal_year integer not null,
             type_business text not null,
             number_jobs integer not null
            );""";

        stmt.execute(createTableSqlQuery);
    }

    public void add(Grants grants) throws SQLException {
        var addSqlQuery = """
            INSERT OR IGNORE INTO grants(
            name_company, adress, amount, fiscal_year, type_business, number_jobs
            ) VALUES (?,?,?,?,?,?)
            """;
        PreparedStatement preparedStatement = conn.prepareStatement(addSqlQuery);
        preparedStatement.setString(1, grants.getNameCompany());
        preparedStatement.setString(2, grants.getAdress());
        preparedStatement.setDouble(3, grants.getAmount());
        preparedStatement.setInt(4, grants.getFiscalYear());
        preparedStatement.setString(5, grants.getTypeBusiness());
        preparedStatement.setInt(6, grants.getNumberJobs());
        preparedStatement.executeUpdate();
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return stmt.executeQuery(query);
    }
}