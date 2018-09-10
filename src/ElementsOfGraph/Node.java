package ElementsOfGraph;

import java.awt.*;

import static java.lang.Math.sqrt;

/**
 * Created by Lemba on 10.09.2018.
 */
public class Node {
    private Color colorOfNode;
    private boolean isActive = false;
    private boolean isEntered = false;
    private boolean visited = false;
    private Point point;
    private String name;

    public Node(String name, int x, int y) {
        colorOfNode = Color.BLACK;
        point=new Point();
        this.point.x = x;
        this.point.y = y;
        this.name = name;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
        if (active)
            colorOfNode = Color.RED;/////
        else
            colorOfNode = Color.BLACK;
    }

    public int overlapWithCursor(int posX, int posY) {
        return (int)sqrt(posX - point.x) * (posX -point.x ) + (posY - point.y ) * (posY - point.y );
    }
    public String getName() {
        return name;
    }
}
