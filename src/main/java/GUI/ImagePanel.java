package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by yaroslav on 13.11.2014.
 */
public class ImagePanel extends JPanel {

    String imageFile = "E:/back_main1.jpg";

    public ImagePanel(){
        super();
    }

    public ImagePanel(String image){
        super();
        this.imageFile = image;
    }

    public ImagePanel(LayoutManager layout){
        super(layout);
    }

    public void paintComponent(Graphics g){
        ImageIcon imageicon = new ImageIcon(String.valueOf(new File(imageFile)));
        Image image = imageicon.getImage();
        super.paintComponent(g);

        if (image != null)
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}