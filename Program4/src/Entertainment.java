/*
Entertainment is a derived class from Event. The difference that Entertainment carries is that it has a alcohol string.
This string indicates whether alochol is present at this event or not.
 */
public class Entertainment extends Event {

    protected String alcohol;

    public Entertainment(){
        super();
        alcohol = null;
    }
    public Entertainment(String t_time, String t_name, String t_cost, String t_type, String [] t_keywords, String args){
        this.time = t_time;
        this.name = t_name;
        this.cost = t_cost;
        this.type = t_type;
        this.alcohol = args;
        for (int a = 0; a < 4; ++a)
            this.keywords[a] = t_keywords[a];//4,5,6,7*/
    }

    protected int keywordMatch(String keyword, String [] keywords) {
        if(keyword.contains(keywords[0]) || keyword.contains(keywords[1]) || keyword.contains(keywords[2]) || keyword.contains(keywords[3]))
           return 1;
        return 0;
    }
    protected int displayList(Event LLL, String find){
        if(LLL.name == null)
            return 0;
        System.out.println("Time: " + this.time + "Name: " + this.name + "Cost: " + this.cost + "Type: " + this.type + "Alcohol: " + this.alcohol + "Keywords: " + this.keywords[0] + "" + this.keywords[1] + "" + this.keywords[2] + "" + this.keywords[3]);
        return 1;
    }
}
