/*
The lll class is a representation of all events that match a certain keyword.

The lll class holds an event reference and a next pointer to itself to indicate another event does or does not exist in
the list.
 */
public class lll {

    //Members
    protected Event ref;//a reference to an event...
    protected lll next;//Reference to the next node in our list.
    //Methods
    protected lll getNext(){
        return next;
    }
    public int addToLLL(lll LLL, Event newEvent){
        if(LLL == null)
            return 0;
        if(LLL.next == null){
            LLL.next = new lll();
            LLL.next.ref = newEvent;return 1;
        }
        return addToLLL(LLL.getNext(), newEvent);
    }

    protected int displayList(lll LLL, String find){
        if(ref == null || LLL == null)
            return 0;
        LLL.ref.displayList(ref, find);
        LLL.displayList(LLL.next, find);
        return 1;
    }

    protected Event retrieve(lll LLL, String name) {
        if (ref == null || LLL == null)
            return null;
        Event retEvent = LLL.ref.retrieve(name);//Check with Event's retrieve function if an event matches what the user entered in.
        if (retEvent != null) {//If yes, then return.{
            System.out.println("\nPrinting returned event: " + retEvent.name);
            return retEvent;
        }
        return LLL.retrieve(LLL.getNext(), name);
    }

    public lll(){
        ref = null;
        next = null;
    }
}
