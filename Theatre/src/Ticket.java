import java.util.ArrayList;


public class Ticket {
    private  int row;
    private  int seat;
    private  double price;
    private  Person person;

    public Ticket(int row, int seat, double price, Person person) {   //parameterised constructor
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;

    }

    // Getters for the attributes
    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }
    public Person getPerson() {
        return person;
    }
    public static void print(ArrayList<Ticket> list) {
        double totPrice=0;   //to calculate total price of tickets

        for (Ticket ticket: list) {
            totPrice+= ticket.getPrice();
            System.out.println("Person name: " + ticket.getPerson().getName());
            System.out.println("Person surname: " + ticket.getPerson().getSurname());
            System.out.println("Person email: " + ticket.getPerson().getEmail());
            System.out.println("Row: " + ticket.getRow());
            System.out.println("Seat: " + ticket.getSeat());
            System.out.println("Price: £" + ticket.getPrice());
            System.out.println();
        }
       System.out.println("The total cost for tickets is "+"£"+ totPrice);
    }
    public static ArrayList<Ticket> getTicketList() {
        return Theatre.ticketList;
    }   //getter for ticket list

    public static void setSortedList(ArrayList<Ticket> sortedList) {
        Ticket.sortedList = sortedList;
    }
    public static ArrayList<Ticket> ticketSort() {   //selection sort used to sort tickets

        ArrayList<Ticket> sortedList = getTicketList();
        int n = sortedList.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (sortedList.get(j).getPrice() < sortedList.get(minIndex).getPrice()) {
                    minIndex = j;
                }
            }

                Ticket temp = sortedList.get(i);
                sortedList.set(i, sortedList.get(minIndex));
                sortedList.set(minIndex, temp);
                setSortedList(sortedList);

        } return sortedList;
    }
    static ArrayList<Ticket> sortedList = new ArrayList<>();

}
