package fireemblem.swing;

import java.awt.Component;
import java.awt.image.BufferedImage;

public class ViewComponent {
    
    private BufferedImage image;
    private Component component;
    private IGraphicalObject graphicalObject;
    
    public ViewComponent () {
        this.image = null;
        this.component = null;
        this.graphicalObject = null;
    }
    
    public ViewComponent (BufferedImage image) {
        this.image = image;
    }
    
    public ViewComponent (Component component) {
        this.component = component;
    }
    
    public ViewComponent (IGraphicalObject graphicalObject) {
        this.graphicalObject = graphicalObject;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public Component getComponent() {
        return this.component;
    }

    public void setGraphicalObject(IGraphicalObject graphicalObject) {
        this.graphicalObject = graphicalObject;
    }

    public IGraphicalObject getGraphicalObject() {
        return this.graphicalObject;
    }
    
}
