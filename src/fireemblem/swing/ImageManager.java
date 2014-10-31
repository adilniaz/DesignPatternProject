package fireemblem.swing;

import java.awt.image.BufferedImage;

public class ImageManager {
    
    private BufferedImage image;
    private int displayTime;
    private int deplacement;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(int displayTime) {
        this.displayTime = displayTime;
    }

    public int getDeplacement() {
        return deplacement;
    }

    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }
    
    
    
}
