package fireemblem.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelImage extends JPanel {

    private BufferedImage image;
    private ImageIcon image2;
    private int width;
    private int height;

    public PanelImage() {
        this.image = null;
    }

    public PanelImage(BufferedImage image) {
        this.image = image;
    }

    public PanelImage(BufferedImage image, ImageIcon imageIcon) {
        this.image = image;
        this.image2 = imageIcon;
    }

    public PanelImage(BufferedImage image, int width, int height) {
        this.image = Image.scaleImage(image, width, height);
    }

    public PanelImage(BufferedImage image, int width, int height, ImageIcon imageIcon) {
        this.image = Image.scaleImage(image, width, height);
        this.image2 = Image.scaleImage(imageIcon, width, height);
    }

    public PanelImage(ImageIcon imageIcon, int width, int height) {
        this.image2 = imageIcon;
        this.width = width;
        this.height = height;
    }

    public PanelImage(BufferedImage image, final double factor) {
        this.image = Image.scaleImage(image, factor);
    }

    public PanelImage(BufferedImage image, int size) {
        this.image = Image.scaleImage(image, size);
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (this.image2 != null) {
            if (this.image != null) {
                this.setSize(new Dimension(this.image.getWidth(), this.image.getHeight()));
                this.setPreferredSize(new Dimension(this.image.getWidth(), this.image.getHeight()));
                JLabel label = new JLabel(this.image2);
                this.add(label);
                this.revalidate();
            } else {
                g.drawImage(this.image2.getImage(), 0, 0, this.width, this.height, this);
                this.setSize(new Dimension(this.width, this.height));
                this.setPreferredSize(new Dimension(this.width, this.height));
                this.revalidate();
            }
        } else {
            if (this.image != null) {
                g.drawImage(this.image, ((this.getWidth() / 2) - (this.image.getWidth() / 2)), 0, this);
                this.setSize(new Dimension(this.image.getWidth(), this.image.getHeight()));
                this.setPreferredSize(new Dimension(this.image.getWidth(), this.image.getHeight()));
                this.revalidate();
            }
        }
    }

    public BufferedImage getBufferedImage() {
        return this.image;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.image = bufferedImage;
        repaint();
    }

    public void setBufferedImage(BufferedImage bufferedImage, int width, int height) {
        this.image = Image.scaleImage(bufferedImage, width, height);
        repaint();
    }
}
