import java.io.*;

public class AddressBook {
    private Contact head;

    public void addContact(String name, String phone) {
        if (name == null || name.isEmpty() || phone == null || phone.isEmpty()) {
            System.out.println("Name or phone cannot be empty.");
            return;
        }
        if (!phone.matches("\\d{10}")) {
            System.out.println("Invalid phone number format. Enter 10 digits only.");
            return;
        }
        if (isDuplicate(name)) {
            System.out.println("Contact with this name already exists.");
            return;
        }

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
        System.out.println("Contact added successfully.");
    }

    private boolean isDuplicate(String name) {
        Contact current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return true;
            }
            current = current.next;
        }
        return false;
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

    public boolean updateContact(String name, String newPhone) {
        if (newPhone == null || newPhone.isEmpty() || !newPhone.matches("\\d{10}")) {
            System.out.println("Invalid phone number. Enter 10 digits.");
            return false;
        }

        Contact current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                current.phone = newPhone;
                System.out.println("Contact updated.");
                return true;
            }
            current = current.next;
        }
        System.out.println("Contact not found.");
        return false;
    }

    public void displayContacts() {
        if (head == null) {
            System.out.println("No contacts found.");
            return;
        }

        System.out.println("\nAll Contacts:");
        Contact current = head;
        while (current != null) {
            System.out.println("Name: " + current.name + " | Phone: " + current.phone);
            current = current.next;
        }
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            Contact current = head;
            while (current != null) {
                writer.write(current.name + "," + current.phone);
                writer.newLine();
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
                    addContact(parts[0].trim(), parts[1].trim());
                }
            }
            System.out.println("Contacts loaded from file.");
        } catch (IOException e) {
            System.out.println("No previous contact file found. Starting fresh.");
        }
    }
}
