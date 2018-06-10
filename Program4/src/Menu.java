//This class holds our menu.
public class Menu extends Utility{
    public int Menu(){
        System.out.println("\n[1] Display keys. \n[2] Display all events that match a keyword\n[3] Retrieve related events by keyword\n[4] Retrieve a single event \n"
                + "[5] Remove a keyword \n[6] Remove all keywords from a tree \n[7] Exit");
        int choice = input.nextInt();
        input.nextLine();
        return choice;
    }
}
