import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MemoryGame extends JFrame implements ActionListener
{
    // Core game play objects
    private Board gameBoard;
    private FlippableCard prevCard1 = null, prevCard2 = null;

    // Labels to display game info
    private JLabel errorLabel, timerLabel, counterLabel, matchesMadeLabel;

    // layout objects: Views of the board and the label area
    private JPanel boardView, labelView;

    // Record keeping counts and times
    private int clickCount = 0, gameTime = 0, errorCount = 0, pairsFound = 0;
    private int happyFaceId = 13; // id of happy face card

    // Game timer: will be configured to trigger an event every second
    private Timer gameTimer;

    public MemoryGame()
    {
        // Call the base class constructor
        super("The Life of Brent Seales - Memory Game");

        // Allocate the interface elements
        JButton restart = new JButton("Restart");
        JButton quit = new JButton("Quit");
        timerLabel = new JLabel("Timer: " + gameTime);
        errorLabel = new JLabel("Errors: " + errorCount);
        counterLabel = new JLabel("Guesses Made: " + clickCount);
        matchesMadeLabel = new JLabel("Matches Made: " + pairsFound);

        // restart action listener
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        // quit action listener
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // timer action listener
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameTime++;
                System.out.println(gameTime);
                timerLabel.setText("Timer: " + gameTime);
            }
        });
        gameTimer.start(); // start timer

        // Allocate two major panels to hold interface
        labelView = new JPanel();  // used to hold labels
        boardView = new JPanel();  // used to hold game board

        // get the content pane, onto which everything is eventually added
        Container c = getContentPane();

        // Setup the game board with cards
        gameBoard = new Board(25, this);

        // Add the game board to the board layout area
        boardView.setLayout(new GridLayout(5, 5, 2, 0));
        gameBoard.fillBoardView(boardView);

        // Add required interface elements to the "label" JPanel
        labelView.setLayout(new GridLayout(1, 4, 2, 2));
        labelView.add(quit);
        labelView.add(restart);
        labelView.add(timerLabel);
        labelView.add(errorLabel);
        labelView.add(counterLabel);
        labelView.add(matchesMadeLabel);

        // Both panels should now be individually layed out
        // Add both panels to the container
        c.add(labelView, BorderLayout.NORTH);
        c.add(boardView, BorderLayout.SOUTH);

        setSize(745, 500);
        setVisible(true);
    }

    /* Handle anything that gets clicked and that uses MemoryGame as an
     * ActionListener */
    public void actionPerformed(ActionEvent e)
    {
        // Get the currently clicked card from a click event
        FlippableCard currentCard = (FlippableCard)e.getSource();
        if (prevCard1 == null) { // if selecting the initial first card
            prevCard1 = currentCard;
            currentCard.showFront();
            if (prevCard1.id() == happyFaceId) {
                prevCard1.removeActionListener(this);
                prevCard1 = null;
            }
        }
        else if (prevCard2 == null) { // if selecting the second card
            prevCard2 = currentCard;
            currentCard.showFront();
            if (prevCard1.id() == happyFaceId) { // if first card is happy face
                prevCard1.removeActionListener(this);
                prevCard1 = prevCard2;
                prevCard2 = null;
            }
        }
        else{ // select a third card
            if (prevCard1.id() == prevCard2.id()) { // if cards are the same
                prevCard1.removeActionListener(this); // remove action listeners
                prevCard2.removeActionListener(this);
                clickCount++; // increment number of guesses
                counterLabel.setText("Guesses made: " + clickCount);
                pairsFound++; // increment number of matches
                matchesMadeLabel.setText("Matches Made: " + pairsFound);
                prevCard1 = currentCard; // select next card
                currentCard.showFront();
                prevCard2 = null;
            }
            else { // if cards are different
                if (prevCard2.id() == happyFaceId) { // if second card is happy face
                    prevCard2.removeActionListener(this);
                    prevCard1.hideFront();
                    clickCount++; // increment number of guesses
                    counterLabel.setText("Guesses made: " + clickCount);
                    errorCount++; // increment number of errors
                    errorLabel.setText("Errors: " + errorCount);
                }

                else { // neither card is happy face
                    prevCard1.hideFront();
                    prevCard2.hideFront();
                    clickCount++; // increment number of guesses
                    counterLabel.setText("Guesses made: " + clickCount);
                    errorCount++; // increment number of errors
                    errorLabel.setText("Errors: " + errorCount);
                }
                prevCard1 = currentCard;
                currentCard.showFront();
                prevCard2 = null;
            }
        }
    }

    // set game to original settings
    private void restartGame()
    {
        pairsFound = 0; // set private variables to 0
        gameTime = 0;
        clickCount = 0;
        errorCount = 0;
        prevCard1 = null;
        prevCard2 = null;
        timerLabel.setText("Timer: 0");
        errorLabel.setText("Errors: 0");
        counterLabel.setText("Guesses Made: 0");
        matchesMadeLabel.setText("Matches Made: 0");
        gameBoard = new Board(25, this); // generate a new board
        // Clear the boardView and have the gameBoard generate a new layout
        boardView.removeAll();
        gameBoard.fillBoardView(boardView);
    }

    public static void main(String args[])
    {
        MemoryGame M = new MemoryGame();
        M.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
    }
}