package redcoder.greedysnake;

import javax.swing.*;

public final class ImageResource {

    public static final ImageIcon HEAD_UP = new ImageIcon(ImageResource.class.getClassLoader().getResource("up.png"));
    public static final ImageIcon HEAD_DOWN = new ImageIcon(ImageResource.class.getClassLoader().getResource("down.png"));
    public static final ImageIcon HEAD_LEFT = new ImageIcon(ImageResource.class.getClassLoader().getResource("left.png"));
    public static final ImageIcon HEAD_RIGHT = new ImageIcon(ImageResource.class.getClassLoader().getResource("right.png"));
    public static final ImageIcon BODY = new ImageIcon(ImageResource.class.getClassLoader().getResource("body.png"));
    public static final ImageIcon FOOD = new ImageIcon(ImageResource.class.getClassLoader().getResource("food.png"));
}
