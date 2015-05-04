package oyakov.model.type;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

/**
 * Created by oyakovlev on 30.03.2015.
 */
public class Quard implements Renderable {

    private final Vertex[] vertices = new Vertex[4];
    private List<Point3D> listVertices = new ArrayList<>();
    Color color;

    public Quard(Vertex v0, Vertex v1, Vertex v2, Vertex v3) {
        vertices[0] = v0;
        vertices[1] = v1;
        vertices[2] = v2;
        vertices[3] = v3;
    }

    public void loadDefaultPoints() {
        listVertices.clear();
        listVertices.add(vertexToPoint3D(vertices[0]));
        listVertices.add(vertexToPoint3D(vertices[1]));
        listVertices.add(vertexToPoint3D(vertices[2]));
        listVertices.add(vertexToPoint3D(vertices[3]));
    }

    public Quard(Quard copy) {
        vertices[0] = copy.vertices[0];
        vertices[1] = copy.vertices[1];
        vertices[2] = copy.vertices[2];
        vertices[3] = copy.vertices[3];
    }

    @Override
    public void renderSelf(GL context, GLU glUtils, DrawType type) {
      
        switch (type) {
            case LINE:
                context.glBegin(GL.GL_LINE_LOOP);
                context.glLineWidth(2.0f);
                context.glColor3f(1.0f, 0.0f, 0.0f);
                context.glVertex3f(vertices[0].x, vertices[0].y, vertices[0].z);
                context.glColor3f(0.0f, 1.0f, 0.0f);
                context.glVertex3f(vertices[1].x, vertices[1].y, vertices[1].z);
                context.glColor3f(0.0f, 0.0f, 1.0f);
                context.glVertex3f(vertices[2].x, vertices[2].y, vertices[2].z);
                context.glColor3f(1.0f, 0.0f, 1.0f);
                context.glVertex3f(vertices[3].x, vertices[3].y, vertices[3].z);
                context.glEnd();
                break;
            case FILL:
                context.glBegin(GL.GL_QUADS);
                context.glColor3f(1.0f, 0.0f, 0.0f);
                context.glVertex3f(vertices[0].x, vertices[0].y, vertices[0].z);
                context.glColor3f(0.0f, 1.0f, 0.0f);
                context.glVertex3f(vertices[1].x, vertices[1].y, vertices[1].z);
                context.glColor3f(0.0f, 0.0f, 1.0f);
                context.glVertex3f(vertices[2].x, vertices[2].y, vertices[2].z);
                context.glColor3f(1.0f, 0.0f, 1.0f);
                context.glVertex3f(vertices[3].x, vertices[3].y, vertices[3].z);
                context.glEnd();
        }

    }

    @Override
    public void renderSelfByPoints(GL context, GLU glUtils, DrawType type) {
          if(color == null) {
            color = new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat());
        }
        switch (type) {
            case LINE:
                context.glLineWidth(1.7f);
                context.glColor3f(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255);
                context.glBegin(GL.GL_LINE_LOOP);
                context.glVertex3f(listVertices.get(0).x, listVertices.get(0).y, listVertices.get(0).z);
                context.glVertex3f(listVertices.get(1).x, listVertices.get(1).y, listVertices.get(1).z);
                context.glVertex3f(listVertices.get(2).x, listVertices.get(2).y, listVertices.get(2).z);
                context.glVertex3f(listVertices.get(3).x, listVertices.get(3).y, listVertices.get(3).z);
                context.glEnd();
                context.glLineWidth(0.4f);
                break;
            case FILL:
               context.glColor3f(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255);
                context.glBegin(GL.GL_QUADS);
                context.glVertex3f(listVertices.get(0).x, listVertices.get(0).y, listVertices.get(0).z);
                context.glVertex3f(listVertices.get(1).x, listVertices.get(1).y, listVertices.get(1).z);
                context.glVertex3f(listVertices.get(2).x, listVertices.get(2).y, listVertices.get(2).z);
                context.glVertex3f(listVertices.get(3).x, listVertices.get(3).y, listVertices.get(3).z);
                context.glEnd();
        }
    }

    public enum DrawType {

        FILL,
        LINE;
    }

    @Override
    public Renderable mirrorX() {
        Vertex v0 = new Vertex(vertices[0]),
                v1 = new Vertex(vertices[1]),
                v2 = new Vertex(vertices[2]),
                v3 = new Vertex(vertices[3]);

        v0.x = -v0.x;
        v1.x = -v1.x;
        v2.x = -v2.x;
        v3.x = -v3.x;

        return new Quard(v0, v1, v2, v3);
    }

    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append(vertices[0]);
        strb.append(System.getProperty("line.separator"));
        strb.append(vertices[1]);
        strb.append(System.getProperty("line.separator"));
        strb.append(vertices[2]);
        strb.append(System.getProperty("line.separator"));
        strb.append(vertices[3]);
        return strb.toString();
    }

    @Override
    public Renderable copy() {
        return new Quard(this);
    }

    @Override
    public List<Point3D> getIntersectionPoints(Point3D p, Point3D normal) {
        List<Point3D> res = new ArrayList<>();
        List<MyLine> cubeLines = getQuardLines();
        for (MyLine line : cubeLines) {
            Point3D resPoint = intersectFlatAndLine(line.first, line.second.substract(line.first).normalize(), p, normal);
            if (resPoint != null) {
                if (((resPoint.x <= line.second.x && resPoint.x >= line.first.x)
                        || (resPoint.x >= line.second.x && resPoint.x <= line.first.x))
                        && ((resPoint.y <= line.second.y && resPoint.y >= line.first.y)
                        || (resPoint.y >= line.second.y && resPoint.y <= line.first.y))
                        && ((resPoint.z <= line.second.z && resPoint.z >= line.first.z)
                        || (resPoint.z >= line.second.z && resPoint.z <= line.first.z))) {
                    res.add(resPoint);
                }
            }
        }
        return res;
    }

    public static Point3D intersectFlatAndLine(Point3D q, Point3D U, Point3D o, Point3D N) {
        float tetta = o.substract(q).dotProduct(N) / U.dotProduct(N);
        Point3D c = q.add(U.multiply(tetta));
        if (c == null) {
            System.out.println("C = null");
            return null;
        }
        if (c.x == Float.NaN) {
            return null;
        }
        return c;
    }

    private List<MyLine> getQuardLines() {
        List<MyLine> res = new ArrayList<>();
        if (!listVertices.isEmpty()) {
            res.add(new MyLine(listVertices.get(0), listVertices.get(1)));
            res.add(new MyLine(listVertices.get(1), listVertices.get(2)));
            res.add(new MyLine(listVertices.get(2), listVertices.get(3)));
            res.add(new MyLine(listVertices.get(3), listVertices.get(0)));
        }
        return res;
    }

    public class MyLine {

        public Point3D first;
        public Point3D second;

        public MyLine(Point3D first, Point3D second) {
            this.first = first;
            this.second = second;
        }
    }

    private static Point3D vertexToPoint3D(Vertex vert) {
        return new Point3D(vert.x, vert.y, vert.z);
    }

    public void rorateXAxis(double angle) {
        for (Point3D p : listVertices) {
            MatrixOperations.rotPoint(p, angle);
        }
    }

    public void translateXCoor(double value) {
        for (Point3D p : listVertices) {
            p.x += value;
        }
    }

    public void translateYCoor(double value) {
        for (Point3D p : listVertices) {
            p.y += value;
        }
    }

    public void translateZCoor(double value) {
        for (Point3D p : listVertices) {
            p.z += value;
        }
    }

    public void rotateAroundAxis(Point3D axis, double angle) {
        double[][] matr = MatrixOperations.createRotateMatrixAroundAxis(axis, angle);
        for (Point3D p : listVertices) {
            double[][] pointMatrix = new double[][]{{p.x, p.y, p.z}};
            pointMatrix = MatrixOperations.transpose(pointMatrix);
            pointMatrix = MatrixOperations.multiply(matr, pointMatrix);
            p.x = (float) pointMatrix[0][0];
            p.y = (float) pointMatrix[1][0];
            p.z = (float) pointMatrix[2][0];
        }
    }
}
