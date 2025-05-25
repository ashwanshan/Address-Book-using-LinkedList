import java.util.Scanner;

public class AddressBookApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AddressBook book = new AddressBook();
        String filename = "contacts.txt";

        book.loadFromFile(filename); // Load contacts at start

        while (true) {
            System.out.println("\nAddress Book Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. Search Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Display Contacts");
            System.out.println("5. Save and Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = scanner.nextLine();
                    book.addContact(name, phone);
                    break;
                case 2:
                    System.out.print("Enter name to search: ");
                    name = scanner.nextLine();
                    book.searchContact(name);
                    break;
                case 3:
                    System.out.print("Enter name to delete: ");
                    name = scanner.nextLine();
                    book.deleteContact(name);
                    break;
                case 4:
                    book.displayContacts();
                    break;
                case 5:
                    book.saveToFile(filename); // Save before exit
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
