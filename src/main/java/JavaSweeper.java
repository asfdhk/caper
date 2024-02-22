import game.Box;
import game.Coord;
import game.Game;
import game.Ranges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class JavaSweeper extends JFrame {


    private Game game;
    private JLabel label;
    private final int COLS = 9;
    private final  int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 80;
    private JPanel jPanel;

    public static void main(String[] args) {
        new JavaSweeper().setVisible(true); //запускає програму
    }

    private JavaSweeper(){
        game = new Game(COLS,ROWS,BOMBS);
        game.start();
        setImage();
        initLabel();
        initPanel();
        initFrame();
    }

    public void initLabel(){
        label = new JLabel("Welcome");
        add(label,BorderLayout.SOUTH);
    }

    private void initPanel(){

        jPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for(Coord coord : Ranges.getAllCoords()){
                    g.drawImage((Image) game.getBox(coord).image,
                            coord.x * IMAGE_SIZE,coord.y*IMAGE_SIZE,this);
                }

            }
        };

        jPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX()/IMAGE_SIZE;
                int y = e.getY()/IMAGE_SIZE;
                Coord coord = new Coord(x,y);
                if(e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(coord);
                if(e.getButton() == MouseEvent.BUTTON3)//це права кнопка
                    game.pressRightButton(coord);
                if(e.getButton() == MouseEvent.BUTTON2)
                    game.start();
                label.setText(getMessage());
                jPanel.repaint();
            }
        });

        jPanel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE,
                                            Ranges.getSize().y * IMAGE_SIZE));// розміри
        add(jPanel);

    }

    private String getMessage(){
        switch (game.getState()){
            case PLAYED: return "Think twice";
            case BOMBED: return "You lose!!!";
            case WINNER: return "WIN!!!";
            default: return "W";//ніколи не виконається
        }
    }

    private void initFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//закриває програму при нажиманні на хркстик
        setTitle("Java Sweeper");
        setResizable(false);
        setVisible(true);
        pack();//мінімальни розмір вікна (замежить від його наповнення)
        setLocationRelativeTo(null);//щоб вікно було по середині
        setIconImage(getImage("icon"));
    }

    private void setImage(){
        for(game.Box box : Box.values() ){
            box.image = getImage(box.name());
        }
    }

    private Image getImage(String name){
        String fileName = "img/" + name.toLowerCase() + ".jpg";
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return  icon.getImage();
    }
}

