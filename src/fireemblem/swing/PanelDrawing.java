package fireemblem.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelDrawing extends JPanel {
    
    public enum drawingType {
        circle
    }
    
    private final Color color;
    private final drawingType type;
    private final int width;
    private final int height;
    
    public PanelDrawing (Color color, drawingType type, int width, int height) {
        this.color = color;
        this.type = type;
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        switch (this.type) {
            case circle:
                g.setColor(this.color);
                g.drawOval(0, 0, this.width, this.height);
                break;
        }
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setSize(this.width, this.height);
    }
    
}
