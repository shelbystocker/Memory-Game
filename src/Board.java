import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;

public class Board
{
    // Array to hold board cards
    private FlippableCard cards[];
    private ActionListener memoryGameAL;
    // Resource loader
    private ClassLoader loader = getClass().getClassLoader();

    public Board(int size, ActionListener AL)
    {
        // Allocate and configure the game board: an array of cards
        cards = new FlippableCard[size];
        memoryGameAL = AL;
        // Fill the Cards array
        int imageIdx = 1;
        for (int i = 0; i < size; i++) {

            // Load the front image from the resources folder
            String imgPath = "res/pic" + imageIdx + ".jpg";
            ImageIcon originalImageIcon = new ImageIcon(loader.getResource(imgPath));
            Image originalImage = originalImageIcon.getImage();
            Image newImage = originalImage.getScaledInstance(130,115, java.awt.Image.SCALE_SMOOTH);
            ImageIcon newImageIcon = new ImageIcon(newImage);

            FlippableCard c = new FlippableCard(newImageIcon); // Setup one card at a time
            c.setID(imageIdx); // set card ID to its file number
            c.addActionListener(AL); // add action listener for each card
            c.hideFront(); // start them off with their back showing

            cards[i] = c; // add them to the array

            //We only want two cards to have the same image, so change the index on every odd i
            if(i % 2 != 0){
                imageIdx++;  // get ready for the next pair of cards
            }

        }
        this.randomizeArray(cards); // randomize card order
    }

    // function used to randomize the array of pictures
    private void randomizeArray(FlippableCard[] cards) {
        // shuffle pictures, used Fisher-Yates Shuffle
        // CITATION: https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
        for (int i = 24; i>1; i--) {
            int indexToSwitch = (int)(Math.random()*12);
            FlippableCard temp = cards[i];
            cards[i] = cards[indexToSwitch];
            cards[indexToSwitch] = temp;
        }
    } // end of randomizeArray()


    public void fillBoardView(JPanel view)
    {
        for (FlippableCard c : cards) {
            view.add(c);
        }
    }
}
