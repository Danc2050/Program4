import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/*
This class represents our BST. Each BST has a list (LLL) which holds all related items that match the current node's
keyword.
This file holds the majority of the functions used in main -- e.g., retrieve, display, insert, etc. Traversal operations
are also represented by go____ and connect____ functions.
 */
public class BST extends Utility {

    //Data members
    protected String keyword;
    protected BST left;
    protected BST right;
    protected lll LLL;

    //Protected Functions
    protected BST goLeft() {
        return left;
    }

    protected BST goRight() {
        return right;
    }

    protected void connectLeft(BST connect) {
        this.left = connect;
    }

    protected void connectRight(BST connect) {
        this.right = connect;
    }

    public int readKeys() {
        String fileName = "src/keywords.txt";
        try {
            FileReader file = new FileReader(fileName);
            BufferedReader in = new BufferedReader(file);
            String line = in.readLine();
            String[] columns = line.split(",");//Read in all lines of file...
            int i = 0;
            while (i != 31) {//There are 37 keywords we read in...so in order to not have an exception, we set the loop to equal 37.
                insert(this, columns[i], 0);
                ++i;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException ex) {
            System.out.println("Error reading file.");
        } catch (Exception exc) {
            System.out.println("General exception: " + exc);
        }
        return 1;
    }

    //Traversal to insert keys... Columns is the item to be inserted into the empty keyword string.
    protected BST insert(BST root, String columns, int move) {
        if (this.keyword == null) {
            this.keyword = columns;
            return this;
        }
        //Decide where to traverse based on the value of the new keyword (columns) to be entered in.
        move = root.keyword.compareToIgnoreCase(columns);
        if (move > 0)//Traverse left.
        {
            if (root.left == null) {
                root.left = new BST(columns);
                return root.left;
            } else {
                insert(root.goLeft(), columns, move);
            }
        }
        if (move < 0) {
            if (root.right == null) {
                root.right = new BST(columns);
                return root.right;
            } else
                insert(root.goRight(), columns, move);
        }
        return this;
    }

    protected Event createEvent(String t_time, String t_name, String t_cost, String t_type, String[] t_keywords, String t_differentVar) {//num indicates number of times we tried to call this function.
        Event race = new Races(t_time, t_name, t_cost, t_type, t_keywords, t_differentVar);
        Event entertainment = new Entertainment(t_time, t_name, t_cost, t_type, t_keywords, t_differentVar);
        Event crowds = new Crowds(t_time, t_name, t_cost, t_type, t_keywords, t_differentVar);
        //Return event depending on event type.
        if (t_type.equals("Race")) {
            return race;
        }
        if (t_type.equals("Crowds")) {
            return crowds;
        }
        if (t_type.equals("Entertainment")) {
            return entertainment;
        }
        return null;
    }

    //Adds to a LLL if a match is found.
    protected int traverseToAddE(BST root, String[] keywords, Event newEvent) {//Traverse to add an event...
        if (root != null) {
            if (newEvent.keywordMatch(root.keyword, keywords) == 1) {//A match was found.
                if (root.LLL == null) {//Starting the beginning of our list.
                    root.LLL = new lll();
                    root.LLL.ref = newEvent;
                    return 1;
                } else {//else keep traversing to add to the list with the match.
                    root.LLL.addToLLL(root.LLL, newEvent);
                }
            }
            if (root.right != null) {
                traverseToAddE(root.goRight(), keywords, newEvent);
            }
            if (root.left != null)
                traverseToAddE(root.goLeft(), keywords, newEvent);
        }
        return 0;
    }
    //Reads from events.txt file and stores data into the nodes that match the keywords of each event.
    public int readEvents() {
        String fileName = "src/events.txt";
        String[] keywords = new String[4];//Keywords that event has.
        try {
            FileReader file = new FileReader(fileName);
            BufferedReader in = new BufferedReader(file);
            String line = "s";
            String[] columns = null;
            while (line != null) {
                line = in.readLine();
                if (line != null) {
                    columns = line.split("#");//Read in all lines of file and seperate by delimiter.
                    //Assign delimited lines with the array index.
                    for (int a = 0; a < 4; ++a)//Easier to pass in keywords than column[5]...etc.
                        keywords[a] = columns[4 + a];//4,5,6,7
                    //Add to a new event and add to the tree...createEvent determines what event to create based on type
                    Event temp = createEvent(columns[0], columns[1], columns[2], columns[3], keywords, columns[8]);
                    traverseToAddE(this, keywords, temp);//Search to place the event in other matching node(s) in the tree.
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException ex) {
            System.out.println("Error reading file.");
        } catch (Exception exc) {
            System.out.println("General exception: " + exc);
        }
        return 1;
    }

    protected int displayKeys(BST root) {
        //In order traversal.
        if (root.left != null) {
            displayKeys(root.goLeft());
        }
        if (keyword == null) {
            System.out.println("Tree is null.");
            return 0;
        } else {
            System.out.println(root.keyword);
        }

        if (root.right != null) {
            displayKeys(root.goRight());
        }
        return 1;
    }

    //Display's a particular keyword if it exists in the list. Wrapper.
    public int displayList() {
        System.out.println("\nEnter a keyword to find all events that match it.");
        String find = input.nextLine();
        BST tempThis = this;
        return displayList(tempThis, find);
    }

    //Recursive function for above.
    protected int displayList(BST root, String find) {
        if (root == null) {
            return 0;
        }
        if (root.LLL != null && root.keyword.contains(find)) {
            System.out.println("Displaying list with keyword: " + find);
            return root.LLL.displayList(root.LLL, find);
        } else {
            if (root.right != null)
                displayList(root.goRight(), find);
            if (root.left != null)
                displayList(root.goLeft(), find);
        }
        return 1;
    }

    //Copy constructor used to create a new key.
    public BST(String key) {
        this.keyword = key;
        this.left = this.right = null;
        this.LLL = null;
    }

    public BST() {
        this.keyword = null;
        this.left = this.right = null;
        this.LLL = null;
    }

    //Retrieves a LLL, which is a list of related items (since they share the same keyword).
    public lll retrieveRelated(BST root, String keyword) {//Retrieves the head of the list of a related keyword.
        if (root != null) {
            int cmp = keyword.compareTo(root.keyword);
            if (root.keyword.contains(keyword)) {
                System.out.println("\nMatch found.");
                return root.LLL;
            }
            if (cmp < 0) {
                return retrieveRelated(root.goLeft(), keyword);
            }
            if (cmp > 0) {
                return retrieveRelated(root.goRight(), keyword);
            }

            if (root.LLL != null)
                return root.LLL;
            else
                return null;
        }
        return null;
    }

    //Wrapper to call the retrieve function in our LLL.
    public Event retrieveWrapper(BST root, String name) {
        if (root == null)
            return null;
        //In order traversal/retrieval.
        Event ret = retrieveWrapper(root.goRight(), name);
        if (root.LLL != null)
            ret = root.LLL.retrieve(root.LLL, name);
        if (ret != null){
            return ret;
        }
        ret = retrieveWrapper(root.goLeft(), name);
        return ret;
    }
}
