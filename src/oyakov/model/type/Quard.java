package oyakov.model.type;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

/**
 * Created by oyakovlev on 30.03.2015.
 */
public class Quard implements Renderable {

    private final Vertex[] vertices = new Vertex[4];

    public Quard(Vertex v0, Vertex v1, Vertex v2, Vertex v3) {
        vertices[0] = v0;
        vertices[1] = v1;
        vertices[2] = v2;
        vertices[3] = v3;
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
}
