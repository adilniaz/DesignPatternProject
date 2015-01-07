package implementations.views;

import java.awt.Component;
import java.awt.image.BufferedImage;

public class ComponentView {
    
    private BufferedImage image;
    private Component component;
    private IGraphicalObject graphicalObject;
    
    public ComponentView () {
        this.image = null;
        this.component = null;
        this.graphicalObject = null;
    }
    
    public ComponentView (BufferedImage image) {
        this.image = image;
    }
    
    public ComponentView (Component component) {
        this.component = component;
    }
    
    public ComponentView (IGraphicalObject graphicalObject) {
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
