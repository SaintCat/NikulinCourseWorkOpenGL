package oyakov.controllers;

import com.sun.opengl.util.GLUT;
import java.util.List;
import oyakov.model.type.Renderable;
import oyakov.runtime.ConfParmSubsystem;
import oyakov.runtime.GLSubsystem;

import java.util.logging.Logger;
import javax.media.opengl.GL;
import static javax.media.opengl.GL.GL_COLOR_BUFFER_BIT;
import static javax.media.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static javax.media.opengl.GL.GL_DEPTH_TEST;
import static javax.media.opengl.GL.GL_LEQUAL;
import static javax.media.opengl.GL.GL_MODELVIEW;
import static javax.media.opengl.GL.GL_NICEST;
import static javax.media.opengl.GL.GL_PERSPECTIVE_CORRECTION_HINT;
import static javax.media.opengl.GL.GL_PROJECTION;
import static javax.media.opengl.GL.GL_SMOOTH;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import oyakov.model.type.MatrixOperations;
import oyakov.model.type.MeshType;
import oyakov.model.type.Point3D;
import oyakov.model.type.Quard;
import oyakov.model.type.Quard.DrawType;

/**
 * Created by oyakovlev on 28.03.2015.
 */
public class GLController implements GLEventListener {

    private static final Logger log = Logger.getLogger(GLController.class.getName());
    ConfParmSubsystem.AppContext appContext = ConfParmSubsystem.getInstance().getCtxt();

    private GLU glUtils;
    private Point3D leftBack;
    private Point3D leftFront;
    private Point3D rightBack;
    private Point3D rightFront;
    private Point3D currentView = new Point3D(0, -10, 20);

    /**
     * Called back immediately after the OpenGL context is initialized. Can be
     * used to perform one-time initialization. Run only once.
     */
    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL glContext = glAutoDrawable.getGL();
        glUtils = new GLU();
        glContext.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        glContext.glClearDepth(1.0f);      // set clear depth value to farthest
        glContext.glEnable(GL_DEPTH_TEST); // enables depth testing
        glContext.glDepthFunc(GL_LEQUAL);  // the type of depth test to do
        glContext.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best perspective correction
        glContext.glShadeModel(GL_SMOOTH); // blends colors nicely, and smoothes out lighting
    }
    private float angle = 0;
    private GLU glu = new GLU();
    
    /**
     * Called back by the animator to perform rendering.
     */
    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        currentView = new Point3D(0,10,-40);
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0, 10, -20);
        gl.glRotatef(-60, 1, 0, 0);
       
       Utils.rorateAroundAxis(currentView, new Point3D(1,0,0), Math.toRadians(60));
        gl.glTranslatef(appContext.cameraOffsetX, appContext.cameraOffsetY, appContext.cameraOffsetZ);
        gl.glRotatef(appContext.cameraAngleX, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(appContext.cameraAngleY, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(appContext.cameraAngleZ, 0.0f, 0.0f, 1.0f);

        try {
            drawAxes(gl);
            drawClipPlane(gl);
            render(gl);
            drawClipPlane(gl);
            gl.glFlush();
        } catch (Exception ex) {
            //TODO: Custom exception type
            log.info("Couldn't load geometry");
            ex.printStackTrace();
        }
    }

    private void render(GL gl) {
        gl.glPushMatrix();
        gl.glEnable(GL.GL_CLIP_PLANE1);
        Renderable cuboid1 = null, cuboid2, cuboi1Copy, cuboid2Copy, cuboid3, cuboid3copy;
        cuboid1 = GLSubsystem.getInstance().getEntity("cuboid1", MeshType.QUARD);
        cuboid1.loadDefaultPoints();
        cuboi1Copy = GLSubsystem.getInstance().copy("cuboid1copy", cuboid1);
        cuboid2 = GLSubsystem.getInstance().copy("cuboid2", cuboid1);
        cuboid2.loadDefaultPoints();
        cuboid2Copy = GLSubsystem.getInstance().copy("cuboid2copy", cuboid2);
        cuboid3 = GLSubsystem.getInstance().copy("cuboid3", cuboid2);
        cuboid3.loadDefaultPoints();
        cuboid3copy = GLSubsystem.getInstance().copy("cuboid3copy", cuboid2);
        Point3D first = appContext.firstPoint;
        Point3D second = appContext.secondPoint;
        Point3D vector = Utils.getNormalVectorByMatrix(getKefByPoints(first, second, leftBack));
        vector = vector.normalize();
        Utils.rorateAroundAxis(vector, first.substract(second).normalize(), Math.toRadians(appContext.clipPlaneRotateAngle));

        double equation[] = Utils.getMatrixByPointAndNormalVectors(first, vector);
        float nfN = currentView.substract(first).dotProduct(vector);
        if (nfN < 0) {
            equation = Utils.getMatrixByPointAndNormalVectors(first, vector.multiply(-1));

        }

        gl.glClipPlane(GL.GL_CLIP_PLANE1, equation, 0);
        gl.glPushMatrix();
        cuboid1.rotateAroundAxis(new Point3D(0, 0, 1), Math.toRadians(appContext.firstCubeAngle));
        List<Point3D> ress = cuboid1.getIntersectionPoints(first, vector);
        cuboid1.renderSelfByPoints(gl, glUtils, DrawType.FILL);
        gl.glDisable(GL.GL_CLIP_PLANE1);
        cuboid1.renderSelfByPoints(gl, glUtils, DrawType.LINE);
        drawPoints(ress, gl);
        gl.glEnable(GL.GL_CLIP_PLANE1);
        gl.glPopMatrix();

        gl.glPushMatrix();
        cuboid2.rotateAroundAxis(new Point3D(0, 1, 0), Math.toRadians(90));
        Point3D firstCopy = new Point3D(first);
        Point3D normalCopy = new Point3D(vector);
        cuboid2.rotateAroundAxis(new Point3D(1, 0, 0), Math.toRadians(appContext.secondCubeAngle));
        cuboid2.translateXCoor(4);
        cuboid2.translateZCoor(2);

        cuboid2.rotateAroundAxis(new Point3D(0, 0, 1), Math.toRadians(appContext.firstCubeAngle));

        ress = cuboid2.getIntersectionPoints(firstCopy, normalCopy);
        cuboid2.renderSelfByPoints(gl, glUtils, DrawType.FILL);
        gl.glDisable(GL.GL_CLIP_PLANE1);
        cuboid2.renderSelfByPoints(gl, glUtils, DrawType.LINE);
        drawPoints(ress, gl);
        gl.glDisable(GL.GL_CLIP_PLANE1);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glEnable(GL.GL_CLIP_PLANE1);

//        
        cuboid3.translateZCoor(-2);
        cuboid3.rotateAroundAxis(new Point3D(1, 0, 0), Math.toRadians(appContext.secondCubeAngle));
        cuboid3.rotateAroundAxis(new Point3D(1, 0, 0), Math.toRadians(appContext.thirdCubeAngle));
        cuboid3.translateZCoor(2);
        cuboid3.translateXCoor(8);
        cuboid3.rotateAroundAxis(new Point3D(0, 0, 1), Math.toRadians(appContext.firstCubeAngle));
        ress = cuboid3.getIntersectionPoints(firstCopy, normalCopy);
        cuboid3.renderSelfByPoints(gl, glUtils, DrawType.FILL);
        gl.glDisable(GL.GL_CLIP_PLANE1);
        cuboid3.renderSelfByPoints(gl, glUtils, DrawType.LINE);
        drawPoints(ress, gl);
        gl.glDisable(GL.GL_CLIP_PLANE1);
        gl.glPopMatrix();
    }

    /**
     * Call-back handler for window re-size event. Also called when the drawable
     * is first set to visible.
     */
    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {
        GL glContext = glAutoDrawable.getGL();

        if (height == 0) {
            height = 1;
        }

        float aspect = (float) width / height;

        // Set the view port (display area) to cover the entire window
        glContext.glViewport(0, 0, width, height);

        // Setup perspective projection, with aspect ratio matches viewport
        glContext.glMatrixMode(GL_PROJECTION);  // choose projection matrix
        glContext.glLoadIdentity();             // reset projection matrix
        glUtils.gluPerspective(45.0, aspect, 0.01, 100.0); // fovy, aspect, zNear, zFar

        // Enable the model-view transform
        glContext.glMatrixMode(GL_MODELVIEW);
        glContext.glLoadIdentity(); // reset
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void drawAxes(GL gl) {
        gl.glBegin(GL.GL_LINES);
        gl.glLineWidth(2);
        gl.glColor3f(1, 0, 0);
        gl.glVertex3f(0, 0, 0);
        gl.glVertex3f(0, 0, 100);
        gl.glEnd();
        gl.glBegin(GL.GL_LINES);
        gl.glLineWidth(2);
        gl.glColor3f(0, 1, 0);
        gl.glVertex3f(0, 0, 0);
        gl.glVertex3f(0, 100, 0);
        gl.glEnd();
        gl.glBegin(GL.GL_LINES);
        gl.glLineWidth(2);
        gl.glColor3f(0, 0, 1);
        gl.glVertex3f(0, 0, 0);
        gl.glVertex3f(100, 0, 0);
        gl.glEnd();
    }

    private void drawClipPlane(GL gl) {
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glDepthMask(false);
        ConfParmSubsystem.AppContext appContext = ConfParmSubsystem.getInstance().getCtxt();
        Point3D first = appContext.firstPoint;
        Point3D second = appContext.secondPoint;
        gl.glColor3f(0, 0, 1);
        gl.glPointSize(10);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex3f(first.x, first.y, first.z);
        gl.glVertex3f(second.x, second.y, second.z);
        gl.glEnd();
        gl.glLineWidth(1.6f);
        gl.glColor3f(0, 0.8f, 0.3f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(first.x, first.y, first.z);
        gl.glVertex3f(second.x, second.y, second.z);
        gl.glEnd();
        gl.glLineWidth(1.0f);

        Point3D vect = first.substract(second).normalize();
        Point3D vect2 = first.substract(second).normalize();
        System.out.println(vect2);
        float tmpX = vect2.x;
        vect2.x = vect2.y;
        vect2.y = -tmpX;

        leftBack = first.add(vect2.multiply(-20));
        leftBack.z = first.z;
        leftFront = first.add(vect2.multiply(20));
        leftFront.z = first.z;
        rightBack = second.add(vect2.multiply(-20));
        rightBack.z = second.z;
        rightFront = second.add(vect2.multiply(20));
        rightFront.z = second.z;

        gl.glPushMatrix();
        gl.glTranslatef(second.x, second.y, second.z);
        gl.glRotatef(appContext.clipPlaneRotateAngle, vect.x, vect.y, vect.z);
        gl.glTranslatef(-second.x, -second.y, -second.z);

//        gl.glBegin(GL.GL_POINTS);
//        gl.glVertex3f(leftBack.x, leftBack.y, leftBack.z);
//        gl.glVertex3f(leftFront.x, leftFront.y, leftFront.z);
//        gl.glVertex3f(rightBack.x, rightBack.y, rightBack.z);
//        gl.glVertex3f(rightFront.x, rightFront.y, rightFront.z);
//        gl.glEnd();
        gl.glColor3f(0, 0, 1);
        float grid2x2[] = new float[]{leftBack.x, leftBack.y, leftBack.z, leftFront.x, leftFront.y, leftFront.z,
            rightBack.x, rightBack.y, rightBack.z, rightFront.x, rightFront.y, rightFront.z};
        gl.glEnable(GL.GL_MAP2_VERTEX_3);
        gl.glMap2f(GL.GL_MAP2_VERTEX_3,
                0.0f, 1.0f, /* U ranges 0..1 */
                3, /* U stride, 3 floats per coord */
                2, /* U is 2nd order, ie. linear */
                0.0f, 1.0f, /* V ranges 0..1 */
                2 * 3, /* V stride, row is 2 coords, 3 floats per coord */
                2, /* V is 2nd order, ie linear */
                grid2x2, 0);  /* control points */

        gl.glMapGrid2f(
                5, 0.0f, 1.0f,
                5, 0.0f, 1.0f);
        gl.glEvalMesh2(GL.GL_LINE,
                0, 5, /* Starting at 0 mesh 5 steps (rows). */
                0, 5);  /* Starting at 0 mesh 6 steps (columns). */


        gl.glLineWidth(5.0f);
//        gl.glRotatef(appContext.clipPlaneRotateAngle += 0.2, res.x, res.y, res.z);
        gl.glBegin(GL.GL_LINE_LOOP);

        gl.glColor4f(1f, 0f, 0f, 0.1f);

//        gl.glTranslated(0, 0, first.z);
        gl.glVertex3f(leftBack.x, leftBack.y, first.z);
        gl.glVertex3f(leftFront.x, leftFront.y, first.z);
//        gl.glTranslated(0, 0, -first.z);
//        gl.glTranslated(0, 0, second.z);
        gl.glVertex3f(rightFront.x, rightFront.y, second.z);
        gl.glVertex3f(rightBack.x, rightBack.y, second.z);

//         gl.glTranslated(0, 0, -second.z);
        gl.glEnd();
        gl.glLineWidth(1.0f);
//        gl.glTranslatef(first.x, first.y, first.z);
//        gl.glTranslatef(first.x, first.y, first.z);
        gl.glRotatef(-appContext.clipPlaneRotateAngle, vect.x, vect.y, vect.z);
        gl.glPopMatrix();
//        gl.glRotatef(-appContext.clipPlaneRotateAngle, 0, 0.5f, 0.5f);
//        gl.glTranslatef(-first.x, -first.y, -first.z);
//        gl.glRotatef(-appContext.clipPlaneRotateAngle, res.x, res.y, res.z);

        gl.glDepthMask(true);
    }

    public double[] getKefByPoints(Point3D a, Point3D b, Point3D c) {
        double[] arr = new double[4];
        arr[0] = 0;
        arr[1] = 0;
        arr[2] = 0;
        arr[3] = 0;
//        double[][] A = new double[][]{{1, a.y, a.z}, {1, b.y, b.z}, {1, c.y, c.z}};
//        double[][] B = new double[][]{{a.x, 1, a.z}, {b.x, 1, b.z}, {c.x, 1, c.z}};
//        double[][] C = new double[][]{{a.x, a.y, 1}, {b.x, b.y, 1}, {c.x, c.y, 1}};
//        double[][] D = new double[][]{{a.z, a.y, a.x}, {b.y, b.y, b.x}, {c.y, c.y, c.x}};
//        arr[0] = (float) MatrixOperations.det(MatrixOperations.transpose(A));
//        arr[1] = (float) MatrixOperations.det(MatrixOperations.transpose(B));
//        arr[2] = (float) MatrixOperations.det(MatrixOperations.transpose(C));
//        arr[3] = (float) MatrixOperations.det(MatrixOperations.transpose(D));
//        return arr;
//        double k2 = a.x - b.x;
//
//        if (k2 == 0) {
//            return arr;
//        }

        //-------------------
        arr[0] = a.y * (b.z - c.z) + b.y * (c.z - a.z) + c.y * (a.z - b.z);
        arr[1] = a.z * (b.x - c.x) + b.z * (c.x - a.x) + c.z * (a.x - b.x);
        arr[2] = a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y);
        arr[3] = -(a.x * (b.y * c.z - c.y * b.z) + b.x * (c.y * a.z - a.y * c.z)
                + c.x * (a.y * b.z - b.y * a.z));
        return arr;

    }

    private float POINT_RADIUS = 0.2f;

    private void drawPoints(List<Point3D> ress, GL gl) {
        gl.glPushMatrix();
        gl.glColor3f(1, 0, 0);
        gl.glBegin(GL.GL_POINTS);
        gl.glPointSize(2.0f);
        for (Point3D p : ress) {
            gl.glVertex3f(p.x, p.y, p.z);
        }
        gl.glPointSize(1.0f);
        gl.glEnd();
        gl.glColor3f((float) 191f / 255f, (float) 193f / 255f, (float) 24f / 255f);
        gl.glBegin(GL.GL_POLYGON);
        for (Point3D p : ress) {
            gl.glVertex3f(p.x, p.y, p.z);
        }
        gl.glEnd();
        gl.glPopMatrix();
    }

    public static void rorateAroundAxis(Point3D p, Point3D axis, double angle) {
        double[][] matr = MatrixOperations.createRotateMatrixAroundAxis(axis, angle);
        double[][] pointMatrix = new double[][]{{p.x, p.y, p.z}};
        pointMatrix = MatrixOperations.transpose(pointMatrix);
        pointMatrix = MatrixOperations.multiply(matr, pointMatrix);
        p.x = (float) pointMatrix[0][0];
        p.y = (float) pointMatrix[1][0];
        p.z = (float) pointMatrix[2][0];
    }

}
