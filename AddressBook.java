import java.io.*;

public class AddressBook {
    private Contact head;

    public void addContact(String name, String phone) {
        Contact newContact = new Contact(name, phone);
        if (head == null) {
            head = newContact;
        } else {
            Contact current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newContact;
        }
    }

    public void searchContact(String name) {
        Contact current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                System.out.println("Found: " + current.name + " - " + current.phone);
                return;
            }
            current = current.next;
        }
        System.out.println("Contact not found.");
    }

    public void deleteContact(String name) {
        if (head == null) {
            System.out.println("Address book is empty.");
            return;
        }

        if (head.name.equalsIgnoreCase(name)) {
            head = head.next;
            System.out.println("Contact deleted.");
            return;
        }

        Contact current = head;
        while (current.next != null && !current.next.name.equalsIgnoreCase(name)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            System.out.println("Contact deleted.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    public void displayContacts() {
        Contact current = head;
        if (current == null) {
            System.out.println("No contacts found.");
            return;
        }

        System.out.println("Contacts:");
        while (current != null) {
            System.out.println("Name: " + current.name + ", Phone: " + current.phone);
            current = current.next;
        }
    }

    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            Contact current = head;
            while (current != null) {
                writer.write(current.name + "," + current.phone + "\n");
                current = current.next;
            }
            System.out.println("Contacts saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    addContact(parts[0], parts[1]);
                }
            }
            System.out.println("Contacts loaded from file.");
        } catch (IOException e) {
            System.out.println("No saved contacts found (first run).");
        }
    }
}
