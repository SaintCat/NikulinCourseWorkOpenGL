package oyakov.controllers;

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
import oyakov.model.type.Quard.DrawType;

/**
 * Created by oyakovlev on 28.03.2015.
 */
public class GLController implements GLEventListener {

    private static final Logger log = Logger.getLogger(GLController.class.getName());

    private GLU glUtils;
    private Point3D leftBack;

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

    /**
     * Called back by the animator to perform rendering.
     */
    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        ConfParmSubsystem.AppContext appContext = ConfParmSubsystem.getInstance().getCtxt();
        gl.glTranslatef(0, 5, -20);
        gl.glRotatef(-60, 1, 0, 0);
        gl.glTranslatef(appContext.cameraOffsetX, appContext.cameraOffsetY, appContext.cameraOffsetZ);
        gl.glRotatef(appContext.cameraAngleX, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(appContext.cameraAngleY, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(appContext.cameraAngleZ, 0.0f, 0.0f, 1.0f);

        try {
            drawAxes(gl);
            drawClipPlane(gl);
            gl.glEnable(GL.GL_CLIP_PLANE1);
            Renderable cuboid1, cuboid2, cuboi1Copy, cuboid2Copy, cuboid3, cuboid3copy;
            Point3D first = appContext.firstPoint;
            Point3D second = appContext.secondPoint;
            Point3D vector = Utils.getNormalVectorByMatrix(getKefByPoints(first, second, leftBack));
            vector = vector.normalize();
            Utils.rorateAroundAxis(vector, first.substract(second).normalize(), Math.toRadians(appContext.clipPlaneRotateAngle));

            double equation[] = Utils.getMatrixByPointAndNormalVectors(first, vector);
            gl.glBegin(GL.GL_LINES);
            gl.glVertex3f(first.x, first.y, first.z);
            gl.glVertex3f(first.x + vector.x * 5, first.y + vector.y * 5, first.z + vector.z * 5);
            gl.glEnd();
            gl.glClipPlane(GL.GL_CLIP_PLANE1, equation, 0);

            cuboid1 = GLSubsystem.getInstance().getEntity("cuboid1", MeshType.QUARD);
            cuboi1Copy = GLSubsystem.getInstance().copy("cuboid1copy", cuboid1);
            gl.glRotatef(appContext.firstCubeAngle, 0.0f, 0.0f, 1.0f);
            cuboid1.renderSelf(gl, glUtils, DrawType.FILL);
            gl.glDisable(GL.GL_CLIP_PLANE1);
            cuboi1Copy.renderSelf(gl, glUtils, DrawType.LINE);
            gl.glEnable(GL.GL_CLIP_PLANE1);
//            gl.glRotatef(-angle, 0.0f, 0.0f, 1.0f);

            cuboid2 = GLSubsystem.getInstance().copy("cuboid2", cuboid1);
            cuboid2Copy = GLSubsystem.getInstance().copy("cuboid2copy", cuboid2);
            gl.glRotatef(90, 0.0f, 1.0f, 0.0f);
            gl.glTranslatef(-2, 0, 4);
            gl.glRotatef(appContext.secondCubeAngle, 0.0f, 0.0f, 1.0f);

//            gl.glTranslatef(-2, -2, -2);
//            gl.glRotatef(angle += 0.1, -1.0f, 0.0f, 0.0f);
//            gl.glTranslatef(2, 2, 2);
            cuboid2.renderSelf(gl, glUtils, DrawType.FILL);

            gl.glDisable(GL.GL_CLIP_PLANE1);
            cuboid2Copy.renderSelf(gl, glUtils, DrawType.LINE);
            gl.glTranslatef(2, 0, -4);
            gl.glEnable(GL.GL_CLIP_PLANE1);

            gl.glRotatef(-90, 0.0f, 1.0f, 0.0f);

            gl.glTranslatef(8, 0, 0);
            cuboid3 = GLSubsystem.getInstance().copy("cuboid3", cuboid2);
            cuboid3copy = GLSubsystem.getInstance().copy("cuboid3copy", cuboid2);

            gl.glTranslatef(0, 0, 2);
            gl.glRotatef(appContext.thirdCubeAngle, 1.0f, 0.0f, 0.0f);
            gl.glTranslatef(0, 0, -2);
            cuboid3.renderSelf(gl, glUtils, DrawType.FILL);
            gl.glDisable(GL.GL_CLIP_PLANE1);
            cuboid3copy.renderSelf(gl, glUtils, DrawType.LINE);

//            gl.glRotatef(90, 0.0f, 1.0f, 0.0f);
//            gl.glRotatef(-angle, 0.0f, 0.0f, 1.0f);
//            gl.glRotatef(-90, 0.0f, 1.0f, 0.0f);
//            gl.glTranslatef(-8, 0, 0);
            gl.glEnable(GL.GL_CLIP_PLANE1);
            gl.glRotatef(-angle, 0.0f, 0.0f, 1.0f);
            gl.glDisable(GL.GL_CLIP_PLANE1);

//            gl.glRotatef(angle += 0.1, +1.0f, 0.0f, 0.0f);
//            gl.glRotatef(90, 0.0f, 1.0f, 0.0f);
//            gl.glRotatef(angle, 0.0f, 0.0f, -1.0f);
//            gl.glRotatef(angle, 0.0f, 0.0f, -1.0f);
//            left_wing = GLSubsystem.getInstance().getEntity("left_wing", MeshType.TRIANGLE);
//            gl.glTranslatef(0.0f, 1.0f, 0.0f);
//            gl.glRotatef(appContext.leftWingAngle, 0.0f, 0.0f, 1.0f);
//            left_wing.renderSelf(gl, glUtils);
//            gl.glRotatef(appContext.leftWingAngle, 0.0f, 0.0f, -1.0f);
            //right_wing = GLSubsystem.getInstance().mirrorX("right_wing", left_wing);
//            gl.glRotatef(appContext.rightWingAngle, 0.0f, 0.0f, 1.0f);
//            right_wing.renderSelf(gl, glUtils);
//            gl.glRotatef(appContext.rightWingAngle, 0.0f, 0.0f, -1.0f);
        } catch (Exception ex) {
            //TODO: Custom exception type
            log.info("Couldn't load geometry");
            log.info(ex.getMessage());
        }
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
        glUtils.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar

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
        ConfParmSubsystem.AppContext appContext = ConfParmSubsystem.getInstance().getCtxt();
        Point3D first = appContext.firstPoint;
        Point3D second = appContext.secondPoint;
        gl.glPointSize(10);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex3f(first.x, first.y, first.z);
        gl.glEnd();

        gl.glBegin(GL.GL_POINTS);
        gl.glVertex3f(second.x, second.y, second.z);
        gl.glEnd();

        Point3D vect = first.substract(second).normalize();
        Point3D vect2 = first.substract(second).normalize();
        System.out.println(vect2);
        float tmpX = vect2.x;
        vect2.x = vect2.y;
        vect2.y = -tmpX;

        Point3D vect3 = new Point3D(vect2);
//        float tmpX2 = vect2.x;
//        vect2.x = vect2.y;
//        vect2.y = tmpX2;
        gl.glBegin(GL.GL_LINES);
        gl.glColor3f(1f, 0f, 0f);
//        gl.glVertex3f(first.x, first.y, first.z);
//        gl.glVertex3f(first.x + vect.x * 10, first.y + vect.y * 10, first.z + vect.z * 10);
        gl.glVertex3f(first.x, first.y, first.z);
        gl.glVertex3f(first.x + vect2.x * 10, first.y + vect2.y * 10, first.z);

        gl.glVertex3f(first.x, first.y, first.z);
        gl.glVertex3f(first.x + vect3.x * 10, first.y + vect3.y * 10, first.z + vect3.z * 10);
        gl.glEnd();
        leftBack = first.add(vect2.multiply(-20));
        leftBack.z = first.z;
        Point3D leftFront = first.add(vect2.multiply(20));
        leftFront.z = first.z;
        Point3D rightBack = second.add(vect2.multiply(-20));
        rightBack.z = second.z;
        Point3D rightFront = second.add(vect2.multiply(20));
        rightFront.z = second.z;

        gl.glPushMatrix();
        gl.glTranslatef(second.x, second.y, second.z);
        gl.glRotatef(appContext.clipPlaneRotateAngle, vect.x, vect.y, vect.z);
        gl.glTranslatef(-second.x, -second.y, -second.z);

        gl.glBegin(GL.GL_POINTS);
        gl.glVertex3f(leftBack.x, leftBack.y, leftBack.z);
        gl.glVertex3f(leftFront.x, leftFront.y, leftFront.z);
        gl.glVertex3f(rightBack.x, rightBack.y, rightBack.z);
        gl.glVertex3f(rightFront.x, rightFront.y, rightFront.z);
        gl.glEnd();

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

        gl.glColor3f(1f, 0f, 0f);

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
        System.out.println("Rotate first on angle: " + -appContext.clipPlaneRotateAngle + " aroung "
                + +vect.x + " " + vect.y + " " + vect.z);
//        gl.glRotatef(-appContext.clipPlaneRotateAngle, 0, 0.5f, 0.5f);
//        gl.glTranslatef(-first.x, -first.y, -first.z);
//        gl.glRotatef(-appContext.clipPlaneRotateAngle, res.x, res.y, res.z);
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
        double k2 = a.x - b.x;

        if (k2 == 0) {
            return arr;
        }

        //-------------------
        arr[0] = a.y * (b.z - c.z) + b.y * (c.z - a.z) + c.y * (a.z - b.z);
        arr[1] = a.z * (b.x - c.x) + b.z * (c.x - a.x) + c.z * (a.x - b.x);
        arr[2] = a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y);
        arr[3] = -(a.x * (b.y * c.z - c.y * b.z) + b.x * (c.y * a.z - a.y * c.z)
                + c.x * (a.y * b.z - b.y * a.z));
        return arr;

    }

}
