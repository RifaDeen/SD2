import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Theatre {

    static int[] Row1 = new int[12];
    static int[] Row2 = new int[16];
    static int[] Row3 = new int[20];
    public static ArrayList<Ticket> ticketList = new ArrayList<>();  //array list to store all the tickets

    public static int[] row_array(int row) { //method to select row array in other functions
        switch (row) {
            case 1:
                return Row1;
            case 2:
                return Row2;
            case 3:
                return Row3;
        } 
       return new int[0];
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the New Theatre!!");

        while (true) {
            System.out.println("""
                    ----------------------------------------------------------------------
                    Please select an option:\s
                    1) Buy a ticket\s
                    2) Print seating area\s
                    3) Cancel ticket\s
                    4) List available seats\s
                    5) Save to file\s
                    6) Load from file\s
                    7) Print ticket information and total price\s
                    8) Sort tickets by price\s
                         0) Quit\s
                    ----------------------------------------------------------------------""");
            try {
                Scanner inp = new Scanner(System.in);
                System.out.println("Enter Option: ");
                int choose = inp.nextInt();

                switch (choose) {
                    case 0:   //0)terminates
                        System.out.println("Program exit, have a nice day!");
                        break;
                    case 1:   //1) Buy a ticket
                        buy_ticket();
                        continue;
                    case 2:   //2) Print seating area
                        print_seating_area();
                        continue;
                    case 3:   //3)Cancel ticket
                        cancel_ticket();
                        continue;
                    case 4:   //4)List available tickets
                        show_available();
                        continue;
                    case 5:   //5)Save to file
                        save();
                        continue;
                    case 6:   //6)Load from file
                        load();
                        continue;
                    case 7:   //7)Print ticket information and total price
                        show_tickets_info();
                        continue;
                    case 8:   //8)Sort tickets by price
                        sort_tickets();
                        continue;
                    default:
                        System.out.println("Invalid Option.");
                        continue;
                }   break;
            } catch (Exception e) {
                System.out.println("Invalid Characters entered, Please enter the correct integer option");
            }
        }
    }

    public static void buy_ticket() {   //method to buy ticket: task 3  (option 1)
        while (true) {
            try {
                Scanner inp = new Scanner(System.in);
                System.out.println("Enter row number(1-3): ");
                int rowNo = inp.nextInt();
                if (rowNo < 1 || rowNo > 3) {
                    System.out.println("Error: This row number does not exist.Please select 1-3.");
                    continue;
                }

                System.out.println("Enter seat number(1-"+row_array(rowNo).length+"): ");
                int seatNo = inp.nextInt();
                if (seatNo < 1 || seatNo > row_array(rowNo).length) {
                    System.out.println("Error: This seat number does not exist.Please select "+1+"-"+row_array(rowNo).length+".");
                } else {
                    int[] seatArray = row_array(rowNo);  //call seat array method to select array for validations and booking
                    if (seatArray[seatNo - 1] == 1) {   //to check if occupied already
                        System.out.println("This seat is already taken, please try again.");
                        break;
                    } else {

                        System.out.println("Enter person's name: ");
                        String name = inp.next();
                        System.out.println("Enter person's surname: ");
                        String surname = inp.next();
                        System.out.println("Enter person's email: ");
                        String email = inp.next();
                        System.out.println("Enter ticket price: ");
                        double price = inp.nextDouble();

                        Person person = new Person(name, surname, email);    //to create person object and ticket object
                        Ticket ticket = new Ticket(rowNo, seatNo, price, person);
                        ticketList.add(ticket);
                        seatArray[seatNo - 1] = 1;    //set seat as booked
                        System.out.println("\nTicket booked for row " + rowNo + ", seat " + seatNo + ".");
                    } break;
                }
            } catch (Exception e) {  //exception handling for non integer values
                System.out.println("Invalid characters entered, please re-enter.");
            }
        }
    }

    public static void print_seating_area() {         //method for task 4: seating area (option 2)

        System.out.println(" ");
        System.out.println("    ***********");
        System.out.println("    *  STAGE  *");
        System.out.println("    ***********");
        for (int i = 1; i <= 3; i++) {               //to print all 3 arrays
            int[] seatArray = row_array(i);
            if (i == 1) {                                //to align rows
                System.out.print("    ");
            } else if (i == 2) {
                System.out.print("  ");
            }
            for (int j = 0; j < seatArray.length; j++) {
                if (j == seatArray.length / 2) {           //to separate the seats in half
                    System.out.print(" ");
                }if (seatArray[j] == 0) {
                    System.out.print("O");
                } else {
                    System.out.print("X");
                }
            } System.out.println();
        }
    }

    public static void cancel_ticket() {    //method to cancel ticket: task 5   (option 3)
        while (true) {
            try {
                Scanner inp = new Scanner(System.in);
                System.out.println("Enter row number(1-3): ");
                int rowNo = inp.nextInt();
                if (rowNo < 1 || rowNo > 3) {
                    System.out.println("Error: This row number does not exist.Please select 1-3.");
                    continue;
                }
                System.out.println("Enter seat number(1-"+row_array(rowNo).length+"): ");
                int seatNo = inp.nextInt();
                if (seatNo < 1 || seatNo > row_array(rowNo).length) {
                    System.out.println("Error: This seat number does not exist.Please select "+1+"-"+row_array(rowNo).length+".");
                } else {
                    int[] seatArray = row_array(rowNo);  //call seat array method to select array for validations
                    if (seatArray[seatNo - 1] == 0) {   //to check if seat is available
                        System.out.println("This seat is already available.");
                    } else {
                        Ticket cancel;      //remove cancelled ticket from ticket arraylist
                        for (Ticket ticket : ticketList) {
                            if (ticket.getRow() == rowNo && ticket.getSeat() == seatNo) {
                                cancel = ticket;
                                ticketList.remove(cancel);
                                seatArray[seatNo - 1] = 0;
                                System.out.println("\nTicket for row " + rowNo + ", seat " + seatNo + " is now cancelled.");
                                break;
                            }
                        }
                    }
                } break;
            } catch (Exception e) {  //exception handling for non integer values
                System.out.println("Invalid characters entered, please re-enter.");
            }
        }
    }

    public static void show_available() {     //method for task 6 (option 4)
        int[] seatArray;
        for (int i = 1; i <= 3; i++) {   //to print all 3 arrays
            seatArray = row_array(i);
            System.out.print("Seats available in row " + i + " : ");
            for (int j = 0; j < seatArray.length; j++) {
                if (seatArray[j] == 0) {
                    System.out.print((j + 1) + " ");
                }
            } System.out.println(" \n ");
        }
    }

    public static void save() {
        try {
            FileWriter fileWrite = new FileWriter("theatre.txt");   //file writer class to write data

            for (int i : Row1) {
                fileWrite.write(i +" ");   //writes values in rows
            }
            fileWrite.write("\n");
            for (int i : Row2) {
                fileWrite.write( i+" ");
            }
            fileWrite.write("\n");
            for (int i : Row3) {
                fileWrite.write(i +" ");
            }

            fileWrite.close(); //to close the file to close connection between code and external file
            System.out.println("File written successful");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        try {
            File file = new File("theatre.txt");
            Scanner text = new Scanner(file);    //scanner to read file contents into arrays

            for (int i = 0; i < Row1.length; i++) {
                Row1[i] = Integer.parseInt(text.next());  //convert string contents in file to integers
            }
            for (int i = 0 ;i < Row2.length; i++) {
                Row2[i] = Integer.parseInt(text.next());
            }
            for (int i = 0; i < Row3.length; i++) {
                Row3[i] = Integer.parseInt(text.next());
            }

            text.close();   //close scanner obj
            System.out.println("File loaded successfully.");

           System.out.print("Row 1:");   //prints rows info to check if values loaded from file
            for(int i : Row1) {
                System.out.print( " "+i );
            }
            System.out.println(" ");
            System.out.print("Row 2:");
            for(int i : Row2) {
                System.out.print(" "+i );
            }
            System.out.println(" ");
            System.out.print("Row 3:");
            for(int i : Row3) {
                System.out.print(" "+ i);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void show_tickets_info(){   //call the print method from Ticket class (menu option 7)
        Ticket.print(ticketList);
    }

    public static void sort_tickets(){    //sorts the tickets according to the price...code implementation in Ticket class (menu option 8)
        Ticket.ticketSort();
        Ticket.print(Ticket.sortedList);
    }
}
