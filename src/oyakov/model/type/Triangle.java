package oyakov.model.type;

import java.util.List;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import oyakov.model.type.Quard.DrawType;

/**
 * Created by oyakovlev on 30.03.2015.
 */
public class Triangle implements Renderable {

    private final Vertex[] vertices = new Vertex[3];

    public Triangle(Vertex v0, Vertex v1, Vertex v2) {
        vertices[0] = v0;
        vertices[1] = v1;
        vertices[2] = v2;
    }

    public Triangle(Triangle copy) {
        vertices[0] = copy.vertices[0];
        vertices[1] = copy.vertices[1];
        vertices[2] = copy.vertices[2];
    }

    @Override
    public void renderSelf(GL context, GLU glUtils, DrawType type) {
        context.glBegin(GL.GL_TRIANGLES);
        context.glColor3f(1.0f, 0.0f, 0.0f);
        context.glVertex3f(vertices[0].x, vertices[0].y, vertices[0].z);
        context.glColor3f(0.0f, 1.0f, 0.0f);
        context.glVertex3f(vertices[1].x, vertices[1].y, vertices[1].z);
        context.glColor3f(0.0f, 0.0f, 1.0f);
        context.glVertex3f(vertices[2].x, vertices[2].y, vertices[2].z);
        context.glEnd();
    }

    @Override
    public Renderable mirrorX() {
        Vertex v0 = new Vertex(vertices[0]),
                v1 = new Vertex(vertices[1]),
                v2 = new Vertex(vertices[2]);

        v0.x = -v0.x;
        v1.x = -v1.x;
        v2.x = -v2.x;

        return new Triangle(v0, v1, v2);
    }

    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append(vertices[0]);
        strb.append(System.getProperty("line.separator"));
        strb.append(vertices[0]);
        strb.append(System.getProperty("line.separator"));
        strb.append(vertices[0]);

        return strb.toString();
    }

    @Override
    public Renderable copy() {
        return new Triangle(this);
    }

    @Override
    public List<Point3D> getIntersectionPoints(Point3D p, Point3D normal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadDefaultPoints() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rorateXAxis(double angle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void translateXCoor(double value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void translateYCoor(double value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void translateZCoor(double value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rotateAroundAxis(Point3D axis, double angle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void renderSelfByPoints(GL context, GLU glUtils, DrawType type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
