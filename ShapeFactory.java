/**
 * Load class contructor
 * @Author sumdu
 * @version 1.0.1
 */
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
public class ShapeFactory { 
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

    public BasicStroke stroke = new BasicStroke(3.0f); 
    /**
     * класс Paint
     * объявление метода цвета фигуры
     */
    public Paint paint ; 
    /**
     *
     * width
     * объявление метода с размером ширины фигуры
     */
    public int width = 25;
    
    /**
     * height
     * объявление метода с размером высоты фигуры
     */
    public int height = 25;
/**
 * параметр переключения типа фигуры
 * @param shape_type параметр переключения типа фигуры
 * @throws Error сообщение что тип не поддерж
 * 
 */
    public ShapeFactory(int shape_type) {
        switch (shape_type / 8) { //переключение фигур 8/10/20
            case 1: {
                this.shape = ShapeFactory.createStar(3, new Point(0, 0), (double)this.width / 2.0, (double)this.width / 2.0);
                break;
            }
            case 3: {
                this.shape = ShapeFactory.createStar(5, new Point(0, 0), (double)this.width / 2.0, (double)this.width / 4.0);
                break;
            }
            case 5: {
                this.shape = new Rectangle2D.Double((double)(-this.width) / 2.0, (double)(-this.height) / 2.0, this.width, this.height);
                break;
            }
            case 7: {
                GeneralPath path = new GeneralPath();
                double tmp_height = Math.sqrt(2.0) / 2.0 * (double)this.height;
                path.moveTo((double)(-this.width / 2), -tmp_height);
                path.lineTo(0.0, -tmp_height);
                path.lineTo((double)(this.width / 2), tmp_height);
                path.closePath();
                this.shape = path;
                break;
            }
            case 9: {
                this.shape = new Arc2D.Double((double)(-this.width) / 2.0, (double)(-this.height) / 2.0, this.width, this.height, 30.0, 300.0, 2);
                break;
            }
            default: {
                throw new Error("type is nusupported");
            }
        }
        switch (shape_type / 10) { //переключение цвета фигур и заливка градиентом
            case 1: {
                this.stroke = new BasicStroke(3.0f);
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                this.stroke = new BasicStroke(7.0f);
                break;
            }
            case 7: {
                this.paint = new GradientPaint(-this.width, -this.height, Color.white, this.width, this.height, Color.gray, true);
                break;
            }
            case 8: {
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
    private static Shape createStar(int arms, Point center, double rOuter, double rInner) {
        double angle = Math.PI / (double)arms;
        GeneralPath path = new GeneralPath();
        for (int i = 0; i < 2 * arms; ++i) {
            double r = (i & 1) == 0 ? rOuter : rInner;
            Point2D.Double p = new Point2D.Double((double)center.x + Math.cos((double)i * angle) * r, (double)center.y + Math.sin((double)i * angle) * r);
            if (i == 0) {
                path.moveTo(p.getX(), p.getY());
                continue;
            }
            path.lineTo(p.getX(), p.getY());
        }
        path.closePath();
        return path;
    }
}

