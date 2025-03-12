import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RPSFrame extends JFrame implements Strategy {
    final int ROCK = 0;
    final int PAPER = 1;
    final int SCISSORS = 2;
    Random rng = new Random();
    boolean atLeastOnce = false;
    public RPSFrame() {
        // setting dimensions and position
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setLocation(screenSize.width / 4, screenSize.height / 4);
        setSize(400, 400);
        // panels
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.cyan);
        // elements
        ImageIcon rockIcon = new ImageIcon("src/rock.png");
        ImageIcon paperIcon = new ImageIcon("src/paper.png");
        ImageIcon scissorsIcon = new ImageIcon("src/scissors.png");
        JButton rockButton = new JButton(rockIcon);
        rockButton.setText("Rock");
        rockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("the rock button was pressed");
            }
        });
        JButton paperButton = new JButton(paperIcon);
        paperButton.setText("Paper");
        paperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("the paper button was pressed");
            }
        });
        JButton scissorsButton = new JButton(scissorsIcon);
        scissorsButton.setText("Scissors");
        scissorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("the scissors button was pressed");
            }
        });
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        // add to panels
        container.add(buttonsPanel);
        buttonsPanel.add(rockButton);
        buttonsPanel.add(paperButton);
        buttonsPanel.add(scissorsButton);
        buttonsPanel.add(quitButton);
        // add to frame
        add(container);
        // meta
        setVisible(true);
        setTitle("Rock, Paper, Scissors");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public int determineMove() {
        int roll;
        int FirstRoll = rng.nextInt(9);
        if (FirstRoll == 0) {
            //  cheat >:) - 10% chance to pick based on what the player did
        } else {
            if (!atLeastOnce) {
                atLeastOnce = true;
                roll = rng.nextInt(2);
            } else {
                roll = rng.nextInt(3);
            }
        }

        // 0 least used - win against the least used play
        // 1 most used - win against the most used play
        // 2 random - self-explanatory
        // 3 last used - use the symbol the player used last (don't call this first)

        return move;
    }

    public int RockMove(){

    }
}
