package implementations.views;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Image {
    
    public final static String defaut = "";
    
    public Image () {
        
    }
    
    public URL getUrlImage (String nom) {
        return this.getUrlImage(nom, defaut);
    }
    
    public InputStream getInputStreamImage (String nom) {
        return this.getInputStreamImage(nom, defaut);
    }
    
    public URL getUrlImage (String nom, String type) {
        nom = this.formatString(nom);
        URL url = null;
        Extension[] extensions = Extension.values();
        for (Extension extension : extensions) {
            url = getClass().getResource(type+nom+"."+extension.name());
            if (url != null) {
                return url;
            }
        }
        if (url == null && !defaut.equals(type)) {
            return this.getUrlImage(nom, defaut);
        }
        return url;
    }
    
    public InputStream getInputStreamImage (String nom, String type) {
        nom = this.formatString(nom);
        InputStream stream = null;
        Extension[] extensions = Extension.values();
        for (Extension extension : extensions) {
            stream = getClass().getResourceAsStream(type+nom+"."+extension.name());
            if (stream != null) {
                return stream;
            }
        }
        if (stream == null && !defaut.equals(type)) {
            return this.getInputStreamImage(nom, defaut);
        }
        return stream;
    }
    
    public boolean aImage (String nom) {
        return this.getInputStreamImage(nom) != null;
    }
    
    public boolean aImage (String nom, String type) {
        return this.getInputStreamImage(nom, type) != null;
    }
    
    public BufferedImage getImage (String nom) {
        return this.getImage(this.getInputStreamImage(nom));
    }
    
    public BufferedImage getImage (String nom, String type) {
        return this.getImage(this.getInputStreamImage(nom, type));
    }
    
    public BufferedImage getImage (String nom, int width, int height) {
        return this.getImage(this.getInputStreamImage(nom), width, height);
    }
    
    public BufferedImage getImage (String nom, int width, int height, String type) {
        return this.getImage(this.getInputStreamImage(nom, type), width, height);
    }
    
    public BufferedImage getImageWidth(String nom, int width) {
        return this.getImageWidth(this.getInputStreamImage(nom), width);
    }
    
    public BufferedImage getImageWidth(String nom, int width, String type) {
        return this.getImageWidth(this.getInputStreamImage(nom, type), width);
    }
    
    public BufferedImage getImageHeigth(String nom, int height) {
        return this.getImageHeight(this.getInputStreamImage(nom), height);
    }
    
    public BufferedImage getImageHeigth(String nom, int height, String type) {
        return this.getImageHeight(this.getInputStreamImage(nom, type), height);
    }
    
    public BufferedImage getImageWidthOrHeigth(String nom, int width, int height) {
        return this.getImageWidthOrHeight(this.getInputStreamImage(nom), width, height);
    }
    
    public BufferedImage getImageWidthOrHeigth(String nom, int width, int height, String type) {
        return this.getImageWidthOrHeight(this.getInputStreamImage(nom, type), width, height);
    }
    
    public static BufferedImage scaleImage(BufferedImage source, int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(source, 0, 0, width, height, null);
        g.dispose();
        return img;
    }
    
    public static ImageIcon scaleImage(ImageIcon source, int width, int height) {
        ImageIcon icon = new ImageIcon(source.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT));
        return icon;
    }
    
    //avec un facteur (<1 pour rétrécir, >1 pour agrandir):
    public static BufferedImage scaleImage(final BufferedImage source, final double factor) {
        int width = (int) (source.getWidth(null) * factor);
        int height = (int) (source.getHeight(null) * factor);
        return scaleImage(source, width, height);
    }
    
    //avec une taille en pixels (=hauteur si portrait, largeur si paysage):
    public static BufferedImage scaleImage(BufferedImage source, int size) {
        int width = source.getWidth(null);
        int height = source.getHeight(null);
        double f;
        if (width < height) {//portrait
            f = (double) height / (double) width;
            width = (int) (size / f);
            height = size;
        } else {//paysage
            f = (double) width / (double) height;
            width = size;
            height = (int) (size / f);
        }
        return scaleImage(source, width, height);
    }
    
    //redimensionne la taille en fonction de la nouvelle largeur donnée
    public static BufferedImage scaleImageByWidth(BufferedImage source, int width) {
        return scaleImage(source, width, ((source.getHeight(null) * width) / source.getWidth(null)));
    }
    
    public static ImageIcon scaleImageByWidth(ImageIcon source, int width) {
        ImageIcon icon = new ImageIcon(source.getImage().getScaledInstance(width, ((source.getIconHeight() * width) / source.getIconWidth()), 
                java.awt.Image.SCALE_DEFAULT));
        return icon;
    }
    
    //redimensionne la taille en fonction de la nouvelle hauteur donnée
    public static BufferedImage scaleImageByHeight(BufferedImage source, int height) {
        return scaleImage(source, ((source.getWidth(null) * height) / source.getHeight(null)), height);
    }
    
    //redimensionne la taille en fonction de la nouvelle hauteur donnée
    public static BufferedImage scaleImageByWidthOrHeight(BufferedImage source, int width, int height) {
        int widthImg = source.getWidth(null);
        int heightImg = source.getHeight(null);
        float widthProportionnel = ((float)widthImg / (float)width);
        float heightProportionnel = ((float)heightImg / (float)height);
        if (widthProportionnel == heightProportionnel) {
            return scaleImageByHeight(source, height);
        } else if (widthProportionnel < heightProportionnel) {
            return scaleImageByHeight(source, height);
        } else {
            return scaleImageByWidth(source, width);
        }
    }
    
    protected BufferedImage getImage (URL url) {
        BufferedImage img = null;
        File fichier;
        fichier = new File(url.getFile());
        try {
            img = ImageIO.read(fichier);
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }
    
    protected BufferedImage getImage (InputStream stream) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(stream);
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }
    
    protected ImageIcon getImageIcon (URL url) {
        return new ImageIcon(url);
    }
    
    protected ImageIcon getImageIcon (InputStream stream) {
        try {  
            OutputStream os = new FileOutputStream(stream.toString()+".gif");  
            try {  
                byte[] buffer = new byte[4096];  
                for (int n; (n = stream.read(buffer)) != -1; )   
                    os.write(buffer, 0, n);
            } catch (IOException ex) {
                Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
            } finally { 
                try {
                    os.close();
                } catch (IOException ex) {
                    Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        } finally { 
            try {
                stream.close();
            } catch (IOException ex) {
                Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new ImageIcon(stream.toString()+".gif");
    }
    
    protected BufferedImage getImage (URL url, int width, int height) {
        return Image.scaleImage(this.getImage(url), width, height);
    }
    
    protected BufferedImage getImage (InputStream stream, int width, int height) {
        return Image.scaleImage(this.getImage(stream), width, height);
    }
    
    protected ImageIcon getImageIcon (URL url, int width, int height) {
        return Image.scaleImage(this.getImageIcon(url), width, height);
    }
    
    protected BufferedImage getImageWidth(URL url, int width) {
        return Image.scaleImageByWidth(this.getImage(url), width);
    }
    
    protected BufferedImage getImageWidth(InputStream stream, int width) {
        return Image.scaleImageByWidth(this.getImage(stream), width);
    }
    
    protected ImageIcon getImageIconWidth (URL url, int width) {
        return Image.scaleImageByWidth(this.getImageIcon(url), width);
    }
    
    protected ImageIcon getImageIconWidth (InputStream stream, int width) {
        return Image.scaleImageByWidth(this.getImageIcon(stream), width);
    }
    
    protected BufferedImage getImageHeight(URL url, int heigth) {
        return Image.scaleImageByHeight(this.getImage(url), heigth);
    }
    
    protected BufferedImage getImageHeight(InputStream stream, int heigth) {
        return Image.scaleImageByHeight(this.getImage(stream), heigth);
    }
    
    protected BufferedImage getImageWidthOrHeight(URL url, int width, int height) {
        return Image.scaleImageByWidthOrHeight(this.getImage(url), width, height);
    }
    
    protected BufferedImage getImageWidthOrHeight(InputStream stream, int width, int height) {
        return Image.scaleImageByWidthOrHeight(this.getImage(stream), width, height);
    }
    
    protected BufferedImage getImageWidthEtHeight(URL url, int width, int height) {
        return Image.scaleImage(this.getImage(url), width, height);
    }
    
    protected BufferedImage getImageWidthEtHeight(InputStream stream, int width, int height) {
        return Image.scaleImage(this.getImage(stream), width, height);
    }
    
    protected String formatString (String nom) {
        return nom;
    }
    
}
