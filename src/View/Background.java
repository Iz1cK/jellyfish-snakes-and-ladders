package View;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Background extends JComponent {

    private ImageIcon backgroundImage; // Change the type to ImageIcon
    private Component blur;
    
    public Background() {
        // Load the dice.png image
        backgroundImage = new ImageIcon(getClass().getResource("Untitled_Artwork.png"));
    }

    // Rest of your methods...

    @SuppressWarnings("unused")
	private void createImage() {
        // Modify the creation of the bufferedImage
        if (backgroundImage != null) {
            int width = getWidth();
            int height = getHeight();
            if (width > 0 && height > 0) {
                BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = bufferedImage.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
           //     Rectangle rec = getAutoSize(backgroundImage); // Pass backgroundImage instead of image
              //  g2.drawImage(backgroundImage.getImage(), rec.x, rec.y, rec.width, rec.height, null);
                if (blur != null) {
                    // Remove the blurred image creation
                }
                g2.dispose();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        // Modify the painting of the component
        if (backgroundImage != null) {
            Graphics2D g2 = (Graphics2D) grphcs.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
            g2.dispose();
        }
        super.paintComponent(grphcs);
    }

    // Rest of your class...
}
