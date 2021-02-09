import javax.swing.*;
import java.awt.*;
import java.util.Random;

public  class Start extends JPanel {
    public static Scheme[][] scheme_array = new Scheme[8][8];
    static JFrame frame;
    public static Graphics gs;
    Image img1, img2;

    public void paint(Graphics g) {
        gs = g;
        addElements(g, false);
    }

    /**
     * Dobavq kvadratchetata v dyskata
     * @param g
     * @param modes
     */
    public static void addElements(Graphics g, boolean modes) {
        if (modes) {
            frame.repaint();
        }
        int x = 0;
        int y = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (scheme_array[i][j].baseModel.state == 1) {
                    g.setColor(Color.yellow);
                    g.fillRect(x, y, 70, 70);
                    g.setColor(Color.black);
                    g.drawRect(x, y, 70, 70);
                    g.setColor(Color.BLUE);
                    g.drawString("X", x + 35, y + 35);
                }
                    else if (scheme_array[i][j].baseModel.final_status == 1) {
                    g.setColor(Color.yellow);
                    g.fillRect(x, y, 70, 70);
                    ;
                    g.setColor(Color.black);
                    g.drawRect(x, y, 70, 70);
                    g.setColor(Color.red);
                    g.drawString("?", x + 35, y + 35);
                }
                    else {
                    g.setColor(scheme_array[i][j].baseModel.color);
                    g.fillRect(x, y, 70, 70);
                    g.setColor(Color.black);
                    g.drawRect(x, y, 70, 70);
                }
                Scheme model = scheme_array[i][j];
                model.start_y = y;
                model.start_x = x;
                model.end_x = x + 70;
                model.end_y = y + 70;
                scheme_array[i][j] = model;
                x += 70;

            }
            y += 70;
            x = 0;
        }

    }

    /**
     * Syobshtenie pri pobeda
     */
    public static void finishWinner()
    {
        int a=JOptionPane.showConfirmDialog(null,"You Win. Another game?");
        if(a==JOptionPane.YES_OPTION){
            refresh();
        }
    }

    /**
     * Obnovqva dyskata
     */
    private static void refresh() {
        scheme_array = new Scheme[8][8];
        importInformation();
        enterBomb();
        enterBabaQgaHouse();
        addElements(gs, true);


    }

    /**
     * Syobshtenie pri zaguba
     */
    public static void finishLoser()
    {
        int a=JOptionPane.showConfirmDialog(null,"You Lose. Another game?");
        if(a==JOptionPane.YES_OPTION){
            refresh();
        }

    }

    /**
     * Stypvane v kyshtata na baba Qga
     */
    private static void enterBabaQgaHouse() {
        Random rand = new Random();
        for (int i = 0 ; i < 8 ; i++)
        {
            int gen = rand.nextInt(50);
            for (int j = 0 ;j  < 8 ; j++)
            {
                if (scheme_array[i][j].baseModel.getClass().getName() == "GPS") {
                    if (gen < 25) {
                        scheme_array[i][j].baseModel.baba = 1;
                        return;
                    }
                }
            }
        }
    }

    /**
     * Stypvane v bomba
     */
    private static void enterBomb() {
        Random rand = new Random();
        int maxGEn = 0;
        for (int i = 0 ; i < 8 ; i++)
        {
            int gen = rand.nextInt(50);
            for (int j = 0 ;j  < 8 ; j++)
            {
                if (scheme_array[i][j].baseModel.getClass().getName() == "UnknownTerritory") {
                    if (gen < 25){
                        scheme_array[i][j].bomb = 1;
                        maxGEn++;
                    }
                }
                if (maxGEn == 5)
                {return;}
            }
        }
    }
    public static void main(String[] args) {

        createJFrame();
        refresh();
    }

    /**
     * Syzdavane na panela
     */
    private static void createJFrame() {
        frame = new JFrame();
        frame.setSize(600, 600);
        frame.getContentPane().add(new Start());
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    /**
     * Importva vidovete kvadratcheta
     */
    private static void importInformation() {
        createStart();
        createNoMove();
        createGpsFree();
        createUnknownPlace();
    }

    /**
     * Syzdava neizsledvana teritoriq
     */
    private static void createUnknownPlace() {
        for(int i = 0 ; i < 8 ; i++)
        {
            for(int j = 0 ; j < 8 ; j++) {
                if (scheme_array[i][j] == null) {
                    scheme_array[i][j] = new Scheme(0, 0, 0, 0, new UnknownTerritory(Color.orange));
                }
            }
        }
    }

    /**
     * Syzdava GPS kvadratchetata
     */
    private static void createGpsFree() {
        Random rand = new Random();
        int max = 8;
        for (int i = 0 ; i < max ; i++)
        {
            int x = rand.nextInt(8);
            int y = rand.nextInt(8);
            if (scheme_array[x][y] == null)
            {
                scheme_array[x][y] = new Scheme(0,0,0,0, new GPS(Color.green));
            }
            else
            {
                max++;
            }
        }
    }

    /**
     * Syzdava neprohodimata teritoriq
     */
    private static void createNoMove() {
        Random rand = new Random();
        int max = 8;
        for (int i = 0 ; i < max ; i++)
        {
            int x = rand.nextInt(8);
            int y = rand.nextInt(8);
            if (scheme_array[x][y] == null)
            {
                scheme_array[x][y] = new Scheme(0,0,0,0, new NoPlace(Color.gray));
            }
            else
            {
                max++;
            }
        }
    }

    /**
     * Syzdava kvadratcheto ot koeto startira igrata
     */
    private static void createStart () {
        scheme_array[0][7] = new Scheme(0, 0, 0, 0, new StartCoordinates(Color.yellow));
    }
}