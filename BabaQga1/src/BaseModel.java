import java.awt.*;

public class BaseModel {
    Color color ;
    int state ;
    int final_status = 0;
    int baba ;
    public BaseModel(Color color)
    {
        this.color = color;
    }
    public BaseModel(Color color, int state )
    {
        this.color = color;
        this.state = state;
    }
}