import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;

public class RPSFrame extends JFrame implements Strategy {
    final int ROCK = 0;
    final int PAPER = 1;
    final int SCISSORS = 2;
    String outcomeMessage;
    int cpuMove;
    int usedLast;
    int rockUsed = 0;
    int paperUsed = 0;
    int scissorsUsed = 0;
    int playerScore = 0;
    int cpuScore = 0;
    int tiesTotal = 0;
    Random rng = new Random();
    boolean atLeastOnce = false;
    public RPSFrame() {
        // setting dimensions and position
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setLocation(screenSize.width / 4, screenSize.height / 4);
        setSize(400, 400);

        // panels
        JPanel container = new JPanel(new BorderLayout());
        JPanel scorePanel = new JPanel(new BorderLayout());
        JPanel playerScoreHold = new JPanel();
        JPanel cpuScoreHold = new JPanel();
        JPanel buttonsPanel = new JPanel();
        JPanel matchPanel = new JPanel(new BorderLayout(0, 0));
        JPanel subMatchPlayer = new JPanel();
        JPanel subMatchCpu = new JPanel();
        playerScoreHold.setBackground(Color.darkGray);
        cpuScoreHold.setBackground(Color.darkGray);
        matchPanel.setBorder(new EmptyBorder(50, 20, 50, 20));
        scorePanel.setBackground(Color.darkGray);
        buttonsPanel.setPreferredSize(new Dimension(buttonsPanel.getWidth(), 85));
        buttonsPanel.setAlignmentY(2f);
        buttonsPanel.setBackground(Color.cyan);
        buttonsPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        matchPanel.setBackground(Color.pink);
        subMatchPlayer.setBackground(Color.pink);
        subMatchCpu.setBackground(Color.pink);

        // elements
        Font scoreFont = new Font("Arial", Font.BOLD, 36);
        Font matchFont = new Font("Arial", Font.BOLD, 24);
        Font outcomeFont = new Font("Arial", Font.BOLD, 18);
        JLabel playerScoreLabel = new JLabel("Player score");
        playerScoreLabel.setForeground(Color.lightGray);
        JLabel cpuScoreLabel = new JLabel("Computer score");
        cpuScoreLabel.setForeground(Color.lightGray);
        JLabel playerScoreDisplay = new JLabel(String.valueOf(playerScore));
        playerScoreDisplay.setForeground(Color.cyan);
        playerScoreDisplay.setFont(scoreFont);
        JLabel tiesTotalDisplay = new JLabel(String.valueOf(tiesTotal), SwingConstants.CENTER);
        tiesTotalDisplay.setForeground(Color.lightGray);
        tiesTotalDisplay.setFont(scoreFont);
        JLabel cpuScoreDisplay = new JLabel(String.valueOf(cpuScore));
        cpuScoreDisplay.setForeground(Color.red);
        cpuScoreDisplay.setFont(scoreFont);
        JLabel playerMoveLabel = new JLabel("You picked ");
        playerMoveLabel.setFont(matchFont);
        JLabel playerMoveDisplay = new JLabel("");
        playerMoveDisplay.setFont(matchFont);
        playerMoveDisplay.setForeground(Color.blue);
        JLabel cpuMoveLabel = new JLabel("Computer picks ");
        cpuMoveLabel.setFont(matchFont);
        JLabel cpuMoveDisplay = new JLabel("");
        cpuMoveDisplay.setFont(matchFont);
        cpuMoveDisplay.setForeground(Color.red);
        JLabel outcomeLabel = new JLabel("", SwingConstants.CENTER);
        outcomeLabel.setFont(outcomeFont);
        outcomeLabel.setForeground(Color.blue);
        ImageIcon rockIcon = new ImageIcon("src/rock.png");
        ImageIcon paperIcon = new ImageIcon("src/paper.png");
        ImageIcon scissorsIcon = new ImageIcon("src/scissors.png");
        JButton rockButton = new JButton(rockIcon);
        rockButton.setText("Rock");
        rockButton.addActionListener(_ -> {
            playerMoveDisplay.setText("ROCK");
            cpuMove = determineMove(ROCK);
            cpuMoveDisplay.setText(whatMove(cpuMove));
            rockUsed++;
            usedLast = ROCK;
            playerScoreDisplay.setText(String.valueOf(playerScore));
            cpuScoreDisplay.setText(String.valueOf(cpuScore));
            tiesTotalDisplay.setText(String.valueOf(tiesTotal));
            outcomeLabel.setText(outcomeMessage);
        });
        JButton paperButton = new JButton(paperIcon);
        paperButton.setText("Paper");
        paperButton.addActionListener(_ -> {
            playerMoveDisplay.setText("PAPER");
            cpuMove = determineMove(PAPER);
            cpuMoveDisplay.setText(whatMove(cpuMove));
            paperUsed++;
            usedLast = PAPER;
            playerScoreDisplay.setText(String.valueOf(playerScore));
            cpuScoreDisplay.setText(String.valueOf(cpuScore));
            tiesTotalDisplay.setText(String.valueOf(tiesTotal));
            outcomeLabel.setText(outcomeMessage);
        });
        JButton scissorsButton = new JButton(scissorsIcon);
        scissorsButton.setText("Scissors");
        scissorsButton.addActionListener(_ -> {
            playerMoveDisplay.setText("SCISSORS");
            cpuMove = determineMove(SCISSORS);
            cpuMoveDisplay.setText(whatMove(cpuMove));
            scissorsUsed++;
            usedLast = SCISSORS;
            playerScoreDisplay.setText(String.valueOf(playerScore));
            cpuScoreDisplay.setText(String.valueOf(cpuScore));
            tiesTotalDisplay.setText(String.valueOf(tiesTotal));
            outcomeLabel.setText(outcomeMessage);
        });
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(_ -> System.exit(0));

        // JPanelception
        container.add(buttonsPanel, BorderLayout.SOUTH);
        container.add(scorePanel, BorderLayout.NORTH);
        container.add(matchPanel, BorderLayout.CENTER);
        scorePanel.add(playerScoreHold, BorderLayout.WEST);
        scorePanel.add(tiesTotalDisplay, BorderLayout.CENTER);
        scorePanel.add(cpuScoreHold, BorderLayout.EAST);
        playerScoreHold.add(playerScoreDisplay);
        playerScoreHold.add(playerScoreLabel);
        cpuScoreHold.add(cpuScoreLabel);
        cpuScoreHold.add(cpuScoreDisplay);
        matchPanel.add(subMatchPlayer, BorderLayout.NORTH);
        matchPanel.add(subMatchCpu, BorderLayout.CENTER);
        matchPanel.add(outcomeLabel, BorderLayout.SOUTH);
        subMatchPlayer.add(playerMoveLabel);
        subMatchPlayer.add(playerMoveDisplay);
        subMatchCpu.add(cpuMoveLabel);
        subMatchCpu.add(cpuMoveDisplay);
        /*
        matchPanel.add(playerMoveLabel, BorderLayout.NORTH);
        matchPanel.add(playerMoveDisplay, BorderLayout.NORTH);                  // atp use different panels for the match display
        matchPanel.add(cpuMoveLabel, BorderLayout.CENTER);


         */
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
    public int determineMove(int playerMove) {
        int roll;
        int move;
        int FirstRoll = rng.nextInt(10);
        if (FirstRoll == 0) {
            move = cheat(playerMove);
        } else {
            if (!atLeastOnce) {
                atLeastOnce = true;
                roll = rng.nextInt(3);
            } else {
                roll = rng.nextInt(4);
            }
            if (roll == 0) {
                move = leastUsed(rockUsed, paperUsed, scissorsUsed);
            } else if (roll == 1) {
                move = mostUsed(rockUsed, paperUsed, scissorsUsed);
            } else if (roll == 2) {
                move = mystery();
            } else {
                move = usedLast(usedLast);
            }
        }
        didPlayerWin(playerMove, move);
        return move;
    }

    @Override
    public int cheat(int playerMove) {
        int move;
        if (playerMove == ROCK || playerMove == PAPER) {
            move = playerMove + 1;
        } else {
            move = ROCK;
        }
        System.out.println("I SMITE YE!");
        System.out.println(move);
        return move;
    }

    @Override
    public int leastUsed(int rockUsed, int paperUsed, int scissorsUsed) {
        int move;
        int leastUsed;
        if (rockUsed == paperUsed) {
            move = rng.nextInt(3);
        } else if (rockUsed == scissorsUsed) {
            move = rng.nextInt(3);
            if (move == 1) {
                move++;
            }
        } else if (paperUsed == scissorsUsed) {
            move = rng.nextInt(2) + 1;
        } else {
            leastUsed = Math.min(rockUsed, Math.min(paperUsed, scissorsUsed));
            if (leastUsed == rockUsed) {
                move = ROCK;
            } else if (leastUsed == paperUsed) {
                move = PAPER;
            } else {
                move = SCISSORS;
            }
        }

        System.out.println("least used: " + move);
        return move;
    }

    @Override
    public int mostUsed(int rockUsed, int paperUsed, int scissorsUsed) {
        int move;
        int mostUsed = Math.max(rockUsed, Math.max(paperUsed, scissorsUsed));
        if (mostUsed == rockUsed) {
            move = ROCK;
        } else if (mostUsed == paperUsed) {
            move = PAPER;
        } else {
            move = SCISSORS;
        }
        System.out.println("most used: " + move);
        return move;
    }

    @Override
    public int mystery() {
        int move = rng.nextInt(3);
        System.out.println("random: " + move);
        return move;
    }

    @Override
    public int usedLast(int usedLast) {
        System.out.println("last used: " + usedLast);
        return usedLast;
    }

    @Override
    public int didPlayerWin(int playerMove, int computerMove) {
        int outcome = 0;
        if ((playerMove == ROCK && computerMove == SCISSORS) || (playerMove == PAPER && computerMove == ROCK) || (playerMove == SCISSORS && computerMove == PAPER)) {
            outcome = 1;
            outcomeMessage = "You win this round!";
            playerScore++;
        } else if ((playerMove == ROCK && computerMove == PAPER) || (playerMove == PAPER && computerMove == SCISSORS) || (playerMove == SCISSORS && computerMove == ROCK)) {
            outcome = 2;
            outcomeMessage = "The computer wins this round!";
            cpuScore++;
        } else {
            outcomeMessage = "It's a tie!";
            tiesTotal++;
        }
        return outcome;
    }

    @Override
    public String whatMove(int move) {
        if (move == ROCK) {
            return "ROCK";
        } else if (move == PAPER) {
            return "PAPER";
        } else return "SCISSORS";
    }
}
