public class Scheme {
    int start_x;
    int start_y;
    int end_x;
    int end_y;
    BaseModel baseModel;
    int bomb ;
    public Scheme(int start_x , int start_y , int end_x , int end_y , BaseModel baseModel)
    {
        this.start_x = start_x;
        this.start_y = start_y;
        this.end_x = end_x;
        this.end_y = end_y;
        this.baseModel = baseModel;
        this.bomb = 0;
    }
}