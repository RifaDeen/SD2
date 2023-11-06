import java.util.ArrayList;

public class Person {
    private String name;
    private String surname;
    private String email;
    private ArrayList<Ticket> ticketList;

    public Person(String name, String surname, String email) {   //constructor
        this.name = name;
        this.surname = surname;
        this.email = email;
        ticketList = new ArrayList<>();
    }

    // Getters for the attributes
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }
}
