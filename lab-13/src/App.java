import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class App {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws Exception {
        String url = "jdbc:sqlite:C:\\Users\\mi\\Desktop\\Study\\3 course\\Java\\Projects\\lab-13\\local.db";
        Connection connection = DriverManager.getConnection(url);

        DbHandler dbHandler = new DbHandler(connection);

        String input;
        Scanner in = new Scanner(System.in);
        in.useDelimiter("\\n");
        boolean quit = false;

        while (!quit) {
            dbHandler.showWholeTable();
            System.out.println();

            System.out.println("1) Add");
            System.out.println("2) Remove");
            System.out.println("0) Exit");
            System.out.println();

            input = in.next();
            switch (input) {
                case "1": {
                    String name, about;
                    int age;
                    clearScreen();
                    System.out.print("Name: ");
                    name = in.next();
                    System.out.print("Age: ");
                    age = in.nextInt();
                    System.out.print("About (type \"no\" if there is nothing to say): ");
                    about = in.next();
                    dbHandler.addRow(name, age, about);
                    System.out.println("Added successfully.\n");
                    break;
                }

                case "2": {
                    clearScreen();
                    System.out.print("Row id: ");
                    int id = in.nextInt();
                    dbHandler.removeRow(id);
                    System.out.println("Deleted successfully.\n");
                    break;
                }

                case "0": {
                    quit = true;
                    in.close();
                    break;
                }

                default: {
                    clearScreen();
                    break;
                }
            }
        }

        connection.close();
    }
}
