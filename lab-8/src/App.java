import java.util.Scanner;

public class App {
        public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }   

    public static void main(String[] args) throws Exception {
        DataStorage<Integer> storage = new DataStorage<Integer>();

        Scanner in = new Scanner(System.in);
        String input = "default";
        boolean quit = false;

        while (!quit)
        {
            System.out.println("Current storage:");
            storage.Print();
            System.out.println();

            System.out.println("1) Add");
            System.out.println("2) Remove");
            System.out.println("3) Find");
            System.out.println("0) Exit");
            System.out.println();
            
            input = System.console().readLine();

            switch (input) {
    
                case "1": {
                    System.out.print("Value: ");
                    int value = in.nextInt();
                    clearScreen();
                    try {
                        storage.Add(value);
                    } catch (Exception e) {
                        System.out.println("DataStorageOverflowException has thrown! Value has not been added.\n");
                    }
                    break;
                }

                case "2": {
                    clearScreen();
                    try {
                        storage.Remove();
                    } catch (Exception e) {
                        System.out.println(
                                "DataStorageEmptyException has thrown! Cannot remove value because storage is empty.\n");
                    }
                    break;
                }
                
                case "3": {
                    System.out.print("Value: ");
                    int value = in.nextInt();
                    clearScreen();
                    try {
                        int index = storage.Find(value);
                        if (index == -1) {
                            System.out.println("No such value.\n");
                        } else {
                            System.out.println("Value found at index " + index + ".\n");
                        }
                    } catch (Exception e) {
                        System.out.println(
                                "DataStorageEmptyException has thrown! Cannot find value because storage is empty.\n");
                    }
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
    }
}
