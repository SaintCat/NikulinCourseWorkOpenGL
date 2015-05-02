package oyakov.model.type;

import java.util.List;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import oyakov.model.type.Quard.DrawType;

/**
 * Created by oyakovlev on 30.03.2015.
 */
public interface Renderable {

    void renderSelf(GL context, GLU glUtils, DrawType type);

    
    void renderSelfByPoints(GL context, GLU glUtils, DrawType type);
    
    Renderable mirrorX();

    Renderable copy();

    public List<Point3D> getIntersectionPoints(Point3D p, Point3D normal);

    public void loadDefaultPoints();
    
    public void rorateXAxis(double angle);

    public void translateXCoor(double value);

    public void translateYCoor(double value);

    public void translateZCoor(double value);

    public void rotateAroundAxis(Point3D axis, double angle);
}
