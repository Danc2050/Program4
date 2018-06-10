/*
Our Super class. This gives the sub-classes Races, Crowds, and Entertainment its data members
 */
public abstract class Event {
    //Data fields
    protected String type;//Type of event.
    protected String [] keywords;//Keywords that event has.
    protected String time;//Time of event.
    protected String name;//Name of event.
    protected String cost;//Cost of event.

    //Comparing a single event...No recursion.
    protected Event retrieve(String name){
        if(this.name != null){
        if(this.name.compareTo(name) == 0)
            return this;}
        return null;
    }
    protected int keywordMatch(String keyword, String [] keywords) { return 0;}
    //Overrided / Dynamically bound function.
    abstract protected int displayList(Event LLL, String find);

    public Event(){
        this.type = null;
        this.keywords = new String[4];
        for(int i = 0; i < 4; ++i)//Note: Allocating the single keyword array does not work in Java. Set each index.
            this.keywords[i] = null;
        this.time = null;
        this.name = null;
        this.cost = null;
    }
}
