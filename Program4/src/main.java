/*
Daniel Connelly
06/01/2018

This class file holds our main and is where the majority of classes are called from. The program creates a tree of BST
type, creates a class that holds a root that points to that tree (Tree rem) and then inserts keywords and events into
the tree (readkeys and readEvents respectively). Other objects are then created that are used (e.g., newLLL to retrieve)
and a switch statement allows a user to control what options are chosen.
 */
public class main extends Utility {

    public static void main(String[] args) {
        Menu menu = new Menu();
        BST tree = new BST();
        //Read in text files.
        Tree rem = new Tree(tree);//A pointer to the root of our tree, used for deletion.
        tree.readKeys();//Adds keys to tree.
        tree.readEvents();//Adds event to each LLL in the tree.
        Event retEvent = null;
        lll newLLL = null;
        //Switch statement for user.
        int x = 0;
        do {
            x = menu.Menu();
            switch (x) {
                case 1:
                    tree.displayKeys(tree);
                    break;
                case 2:
                    tree.displayList();
                    break;
                case 3://Find a certain keyword.
                    System.out.println("\nEnter a related keyword.");
                    String retKey = input.nextLine();
                    newLLL = tree.retrieveRelated(tree, retKey);
                    System.out.println("\nRetrieved list with the following inside it: ");
                    if(newLLL != null)
                        newLLL.displayList(newLLL, "");
                    else
                        System.out.println("\nNo such keyword exists.");
                    break;
                case 4://Still working on this...
                    System.out.println("\nEnter a name of an event to retrieve.");
                    String name = input.nextLine();
                    retEvent = tree.retrieveWrapper(tree, name);
                    System.out.println("\nRetrieved event: ");
                    if(retEvent != null)
                        retEvent.displayList(retEvent, " ");
                    else
                        System.out.println("\nNo such event exists.");
                    break;
                case 5:
                    System.out.println("\nEnter a keyword to remove from the database.");
                    String removeKey = input.nextLine();
                    rem.remove(tree, removeKey);//Note: we use a pointer to our BST in case our tree is NULL.
                    break;
                case 6://Remove all.
                    tree = null;
                    tree = new BST();
                    break;
                case 7:
                    System.out.println("\nProgram ending...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nError. Enter number 1-7");
                    break;
            }
            x = 0;
        } while (x != 7);
    }
}
