/*
Races is a derived class from Event. The difference that Races carries is that it has a difficulty member.
 */
public class Races extends Event {
    protected String difficulty;//difficulty of race.

    //Functions
    public Races(){
        difficulty = null;
    }
    public Races(String t_time, String t_name, String t_cost, String t_type, String [] t_keywords, String args){
        super();
        this.time = t_time;
        this.name = t_name;
        this.cost = t_cost;
        this.type = t_type;
        this.difficulty = args;
        for (int a = 0; a < 4; ++a)
            this.keywords[a] = t_keywords[a];//4,5,6,7*/
    }

    protected int keywordMatch(String keyword, String [] keywords) {
        if(keyword.contains(keywords[0]) || keyword.contains(keywords[1]) || keyword.contains(keywords[2]) || keyword.contains(keywords[3]))
            return 1;
        return 0;
    }

    protected int displayList(Event LLL, String find){
        if(LLL == null)
            return 0;
        System.out.println("Time: " + this.time + "Name: " + this.name + "Cost: " + this.cost + "Type: " + this.type + "Difficulty: " + this.difficulty + "Keywords: " + this.keywords[0] + ", " + this.keywords[1] + ", " + this.keywords[2] + ", " + this.keywords[3]);
        return 1;
    }
}

