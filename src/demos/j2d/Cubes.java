/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demos.j2d;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

/**
 *
 * @author ROMAN
 */
public class Cubes {

    static void createFirstCube(GL gl, float rtri, float val) {
        gl.glTranslatef(
                0, 0.0f, -35); // Move the triangle
        gl.glRotatef(rtri,
                0.0f, 1.0f, 0.0f);
        gl.glBegin(GL.GL_QUADS);
        //drawing triangle in all dimentions
        //front

        gl.glColor3f(
                1.0f, 0.0f, 0.0f); // Red
        gl.glVertex3f(
                10.0f, 10.0f, 10.0f); // Top
        gl.glColor3f(
                0.0f, 1.0f, 0.0f); // Green
        gl.glVertex3f(
                10f, 10.0f, -10f); // Left
        gl.glColor3f(
                0.0f, 0.0f, 1.0f); // Blue
        gl.glVertex3f(
                -10f, 10f, 10f); // Right)
        gl.glColor3f(
                1.0f, 0.0f, 1.0f); // Blue
        gl.glVertex3f(
                -10f, 10f, -10f); // Right)

        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(
                1.0f, 0.0f, 0.0f); // Red
        gl.glVertex3f(
                10.0f, 10.0f, 10.0f); // Top
        gl.glColor3f(
                0.0f, 1.0f, 0.0f); // Green
        gl.glVertex3f(
                10f, 10.0f, -10f); // Left
        gl.glColor3f(
                0.0f, 0.0f, 1.0f); // Blue
        gl.glVertex3f(
                10f, -10f, -10f); // Right)
        gl.glColor3f(
                1.0f, 0.0f, 1.0f); // Blue
        gl.glVertex3f(
                10f, -10f, 10f); // Right)
//
////right
//        gl.glColor3f(
//                1.0f, 0.0f, 0.0f);
//        gl.glVertex3f(
//                1.0f, 2.0f, 0.0f); // Top
//        gl.glColor3f(
//                0.0f, 0.0f, 1.0f);
//        gl.glVertex3f(
//                1.0f, -1.0f, 1.0f); // Left
//        gl.glColor3f(
//                0.0f, 1.0f, 0.0f);
//        gl.glVertex3f(
//                1.0f, -1.0f, -1.0f); // Right
////left
//        gl.glColor3f(
//                1.0f, 0.0f, 0.0f);
//        gl.glVertex3f(
//                1.0f, 2.0f, 0.0f); // Top 
//        gl.glColor3f(
//                0.0f, 1.0f, 0.0f);
//        gl.glVertex3f(
//                1.0f, -1.0f, -1.0f); // Left
//        gl.glColor3f(
//                0.0f, 0.0f, 1.0f);
//        gl.glVertex3f(
//                -1.0f, -1.0f, -1.0f); // Right
////top
//        gl.glColor3f(
//                0.0f, 1.0f, 0.0f);
//        gl.glVertex3f(
//                1.0f, 2.0f, 0.0f); // Top 
//
//        gl.glColor3f(
//                0.0f, 0.0f, 1.0f);
//        gl.glVertex3f(
//                -1.0f, -1.0f, -1.0f); // Left
//        gl.glColor3f(
//                0.0f, 1.0f, 0.0f);
//        gl.glVertex3f(
//                -1.0f, -1.0f, 1.0f); // Right
        gl.glEnd(); // Done Drawing 3d triangle (Pyramid)
    }
}
