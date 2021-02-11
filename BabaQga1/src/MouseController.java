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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

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
    public void mousePressed(MouseEvent e) {
        if (mode == 1) {
            x = e.getX() - 10;
            y = e.getY() - 10;
            mode = 2;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((Start.scheme_array[i][j].start_x <= x && Start.scheme_array[i][j].end_x >= x) &&
                            (Start.scheme_array[i][j].start_y <= y && Start.scheme_array[i][j].end_y >= y)) {
                        if (Start.scheme_array[i][j].baseModel.state == 0) {

                            if(!guardColorMode(i, j))
                            {
                                return;
                            }
                            mode = 2;
                            first_i = i;
                            first_j = j;
                            isOK++;
                            return;
                        }
                    }
                }
            }
        } else if (mode == 2) {
            int x_s = e.getX() - 10;
            int y_s = e.getY() - 10;
            for (int i = 0; i < 8; i++) {
                if (isOK > 1) {
                    break;
                }
                for (int j = 0; j < 8; j++) {
                    if ((Start.scheme_array[i][j].start_x <= x_s && Start.scheme_array[i][j].end_x >= x_s) &&
                            (Start.scheme_array[i][j].start_y <= y_s && Start.scheme_array[i][j].end_y >= y_s)) {
                        second_i = i;
                        second_j = j;
                        isOK++;
                    }
                }
            }
            if (Start.scheme_array[second_i][second_j].baseModel.state != 1) {
                isOK--;
                mode = 2;
                Start.scheme_array[first_i][first_j].baseModel.state = 0;
                return;
            } else {
                Start.scheme_array[second_i][second_j].baseModel.final_status = 1;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        Start.scheme_array[i][j].baseModel.state = 0;
                    }
                }
                mode = 1;
                Start.addElements(Start.gs, true);
                if (checkBoxSituation(second_i, second_j)) {
                    Start.finishLoser();
                } else {
                    if (checkWinner(second_i, second_j)) {
                        Start.finishWinner();
                    }
                }
            }
        }
    }

    /**
     * Proverqva za pobeditel
     * @param second_i
     * @param second_j
     * @return
     */
    private boolean checkWinner(int second_i, int second_j) {
        if (Start.scheme_array[second_i][second_j].baseModel.baba == 1) {
            return true;
        }
        return false;
    }

    /**
     * Proverqva vida kutiika
     * @param second_i
     * @param second_j
     * @return
     */
    private boolean checkBoxSituation(int second_i, int second_j) {
        int max = 0;
        if (second_i + 1 < 8) {
            if (checkBlueBox(second_i + 1, second_j, 1)) {
                max++;
            }
        }
        if (second_i - 1 > -1) {
            if (checkBlueBox(second_i - 1, second_j, 1)) {
                max++;
            }
        }
        if (second_j + 1 < 8) {
            if (checkBlueBox(second_i, second_j + 1, 1)) {
                max++;
            }
        }
        if (second_j - 1 > -1) {
            if (checkBlueBox(second_i, second_j - 1, 1)) {
                max++;
            }
        }
        if (max >= 3) {
            return true;
        }
        return false;
    }


    /**
     * Proverqva sinq kutiika
     * @param second_i
     * @param i
     * @param i1
     * @return
     */
    private boolean checkBlueBox(int second_i, int i, int i1) {
        if ( Start.scheme_array[second_i][second_j].bomb == 1)
        {
            return  true;
        }
        else
        {
            return false;
        }
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