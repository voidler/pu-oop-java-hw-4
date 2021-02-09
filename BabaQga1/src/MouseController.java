import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseController extends JComponent implements MouseListener {
    int mode = 1;
    int x = 0;
    int y = 0;
    int isOK = 0;
    int first_i = 0;
    int first_j = 0;
    int second_i = 0;
    int second_j = 0;

    private boolean guardColorMode(int i_s, int j_s) {
        int max = 0 ;
        if (i_s + 1 < 8) {
            if(changeColor(i_s + 1, j_s, 1))
            {
                max++;
            }
        }
        if (i_s - 1 > -1) {
            if(changeColor(i_s - 1, j_s, 1))
            {
                max++;
            }
        }
        if (j_s + 1 < 8) {
            if(changeColor(i_s, j_s + 1, 1))
            {
                max++;
            }
        }
        if (j_s - 1 > -1) {
            if(changeColor(i_s, j_s - 1, 1)
            )
            {
                max++;
            }
        }
        Start.addElements(Start.gs, true);
        if (max == 0) {
            Start.finishLoser();
            return false;
        }
        return true;

    }

    private boolean changeColor(int i_s, int j_s, int i1) {
        if (Start.scheme_array[i_s][j_s].baseModel.getClass().getName() == "NoPlace") {
            return false;
        } else {
            if (Start.scheme_array[i_s][j_s].baseModel.final_status == 1) {
                return false;
            }
            Start.scheme_array[i_s][j_s].baseModel.state = 1;
            return true;
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
