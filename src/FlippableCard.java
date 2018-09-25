import javax.swing.*;
import java.awt.*;

public class FlippableCard extends JButton
{
    // Resource loader
    private ClassLoader loader = getClass().getClassLoader();

    // Card front icon
    private Icon front;
    // Card back image

    // upload back image and scale to correct size
    private ImageIcon originalBack = new ImageIcon(loader.getResource("res/Back.jpg"));
    private Image originalBackImage = originalBack.getImage();
    private Image newBackImage = originalBackImage.getScaledInstance(130,115, java.awt.Image.SCALE_SMOOTH);
    private ImageIcon back = new ImageIcon(newBackImage);

    // ID + Name
    private int id;
    private String customName;

    // Default constructor
    public FlippableCard() {
        super();
    }

    // Constructor with card front initialization
    public FlippableCard(ImageIcon frontImage)
    {
        super();
        front = frontImage;
        super.setIcon(front);
    }

    // Set the image used as the front of the card
    public void setFrontImage(ImageIcon frontImage) {
        front = frontImage;
    }

    // Card flipping functions
    public void showFront() {
        /* To-Do: Show the card front */
        super.setIcon(front);
    }

    public void hideFront() {
        /* To-Do: Show the card back  */
        super.setIcon(back);
    }

    // Metadata: ID number
    public int id() {
        return id;
    }

    public void setID(int i) {
        id = i;
    }

    // Metadata: Custom name
    public String customName() {
        return customName;
    }

    public void setCustomName(String s) {
        customName = s;
    }
}