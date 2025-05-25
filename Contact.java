public class Contact {
    String name;
    String phone;
    Contact next;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.next = null;
    }
}
