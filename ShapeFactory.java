/**
 * Load class contructor
 * @Author sumdu
 * @version 1.0.1
 */
package lab3;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
     * ShapeFactory
     * начало обьявления нового класса ShapeFactory
     */
public class ShapeFactory
{
    /**
     * 
     * Shape
     *  объявление метода фигуры
     */
    public Shape shape;
    /**
     * BasicStroke
     * объявление метода толщины линий
     */

    public BasicStroke stroke;
    /**
     * класс Paint
     * объявление метода цвета фигуры
     */
    public Paint paint;
    /**
     *
     * width
     * объявление метода с размером ширины фигуры
     */
    public int width;
    /**
     * height
     * объявление метода с размером высоты фигуры
     */
    public int height;
    
    
    
    enum ShapeType {
        SHEST,
        STAR,
        KVADR,
        TREUG,
        PACMAN
    }
    
    enum ShapeVid {
        TOL3,
        NONE, 
        TOL7,
        GRADIENT,
        RED
    }
    
    /**
 * параметр переключения типа фигуры
 * @param shape_type параметр переключения типа фигуры
 * @param shape_vid параметр переключения заливки, цвета и толщины
 * @throws Error сообщение что тип не поддерж
 * 
 */
    public ShapeFactory(final ShapeType shape_type, final ShapeVid shape_vid) {
        this.width = 25;
        this.height = 25;
        this.stroke = new BasicStroke(3.0f);
        switch (shape_type) {
            case SHEST: {
                this.shape = createStar(3, new Point(0, 0), this.width / 2.0, this.width / 2.0);
                break;
            }
            case STAR: {
                this.shape = createStar(5, new Point(0, 0), this.width / 2.0, this.width / 4.0);
                break;
            }
            case KVADR: {
                this.shape = new Rectangle2D.Double(-this.width / 2.0, -this.height / 2.0, this.width, this.height);
                break;
            }
            case TREUG: {
                final GeneralPath path = new GeneralPath();
                final double tmp_height = Math.sqrt(2.0) / 2.0 * this.height;
                path.moveTo(-this.width / 2, -tmp_height);
                path.lineTo(0.0, -tmp_height);
                path.lineTo(this.width / 2, tmp_height);
                path.closePath();
                this.shape = path;
                break;
            }
            case PACMAN: {
                this.shape = new Arc2D.Double(-this.width / 2.0, -this.height / 2.0, this.width, this.height, 30.0, 300.0, 2);
                break;
            }
            default: {
                throw new Error("type is nusupported");
            }
        }
        switch (shape_vid) {
            case TOL3: {
                this.stroke = new BasicStroke(3.0f);
                break;
            }
            case NONE: {
                break;
            }
            case TOL7: {
                this.stroke = new BasicStroke(7.0f);
                break;
            }
            case  GRADIENT: {
                this.paint = new GradientPaint((float)(-this.width), (float)(-this.height), Color.white, (float)this.width, (float)this.height, Color.gray, true);
                break;
            }
            case RED: {
                this.paint = Color.red;
                break;
            }
            default: {
                throw new Error("type is nusupported");
            }
        }
    }
    
    /**
      * метод для создания звезды	   
      * @param int arms количество краев	    
      * @param Point center Центр звезды	    
      * @param double rOuter Длина края	     
      * @param double rInner Расстояние между центром и краем	     
      * @return Shape 	    
    */  
    private static Shape createStar(final int arms, final Point center, final double rOuter, final double rInner) {
        final double angle = 3.141592653589793 / arms;
        final GeneralPath path = new GeneralPath();
        for (int i = 0; i < 2 * arms; ++i) {
            final double r = ((i & 0x1) == 0x0) ? rOuter : rInner;
            final Point2D.Double p = new Point2D.Double(center.x + Math.cos(i * angle) * r, center.y + Math.sin(i * angle) * r);
            if (i == 0) {
                path.moveTo(p.getX(), p.getY());
            }
            else {
                path.lineTo(p.getX(), p.getY());
            }
        }
        path.closePath();
        return path;
    }
}
