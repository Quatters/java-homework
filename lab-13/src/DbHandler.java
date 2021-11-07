import java.sql.*;

public class DbHandler {
    private Connection connection;
    private Statement statement;

    public DbHandler(Connection connection) throws SQLException {
        this.connection = connection;
        statement = connection.createStatement();
    }

    public void showWholeTable() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM person");

        showHeader();
        while (rs.next()) {
            System.out.printf("%5s",rs.getInt("id"));
            System.out.printf("%15s", rs.getString("name"));
            System.out.printf("%10s", rs.getInt("age"));
            System.out.printf("%50s", rs.getString("about") + '\n');
        }
    }

    public void addRow(String name, int age, String about) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO person (name, age, about) VALUES (?, ?, ?)");
        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setString(3, about);
        ps.executeUpdate();
    }

    public void removeRow(int rowId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("DELETE FROM person WHERE id = ?");
        ps.setInt(1, rowId);
        ps.executeUpdate();
    }

    private void showHeader() {
        System.out.printf("%5s", "id");
        System.out.printf("%15s", "name");
        System.out.printf("%10s", "age");
        System.out.printf("%50s", "about\n");
    }

    protected void finalize() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
