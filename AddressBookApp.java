import java.util.Scanner;

public class AddressBookApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AddressBook book = new AddressBook();
        String filename = "contacts.txt";

        book.loadFromFile(filename);

        while (true) {
            System.out.println("\n------ Address Book Menu ------");
            System.out.println("1. Add Contact");
            System.out.println("2. Search Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Update Contact");
            System.out.println("5. Display Contacts");
            System.out.println("6. Save and Exit");
            System.out.print("Choose an option (1-6): ");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone (10 digits): ");
                    String phone = scanner.nextLine();
                    book.addContact(name.trim(), phone.trim());
                    break;
                case "2":
                    System.out.print("Enter name to search: ");
                    name = scanner.nextLine();
                    book.searchContact(name.trim());
                    break;
                case "3":
                    System.out.print("Enter name to delete: ");
                    name = scanner.nextLine();
                    book.deleteContact(name.trim());
                    break;
                case "4":
                    System.out.print("Enter name to update: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new phone number: ");
                    phone = scanner.nextLine();
                    book.updateContact(name.trim(), phone.trim());
                    break;
                case "5":
                    book.displayContacts();
                    break;
                case "6":
                    book.saveToFile(filename);
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select between 1 and 6.");
            }
        }
    }
}
