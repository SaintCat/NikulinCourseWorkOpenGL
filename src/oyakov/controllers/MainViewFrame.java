/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oyakov.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.media.opengl.GLCanvas;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import oyakov.runtime.ConfParmSubsystem;

/**
 *
 * @author ROMAN
 */
public class MainViewFrame extends javax.swing.JFrame {

    ConfParmSubsystem.AppContext appContext = ConfParmSubsystem.getInstance().getCtxt();

    /**
     * Creates new form TestFrame
     */
    public MainViewFrame() {
        initComponents();
        firstX.setModel(new SpinnerNumberModel(-10, -2000, 2000, 0.2));
        firstY.setModel(new SpinnerNumberModel(0, -2000, 2000, 0.2));
        firstZ.setModel(new SpinnerNumberModel(0, -2000, 2000, 0.2));
        secondX.setModel(new SpinnerNumberModel(10, -2000, 2000, 0.2));
        secondY.setModel(new SpinnerNumberModel(0, -2000, 2000, 0.2));
        secondZ.setModel(new SpinnerNumberModel(0, -2000, 2000, 0.2));
        jSpinner1.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (((Integer) jSpinner1.getValue()) % 360 == 0) {
                    jSpinner1.setValue(0);
                }
                appContext.firstCubeAngle = (Integer) jSpinner1.getValue();
            }
        });

        jSpinner2.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (((Integer) jSpinner2.getValue()) % 360 == 0) {
                    jSpinner2.setValue(0);
                }
                appContext.secondCubeAngle = (Integer) jSpinner2.getValue();
            }
        });

        jSpinner3.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (((Integer) jSpinner3.getValue()) % 360 == 0) {
                    jSpinner3.setValue(0);
                }
                appContext.thirdCubeAngle = (Integer) jSpinner3.getValue();
            }
        });
        jSpinner4.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (((Integer) jSpinner4.getValue()) % 360 == 0) {
                    jSpinner4.setValue(0);
                }
                appContext.clipPlaneRotateAngle = (Integer) jSpinner4.getValue();
            }
        });
        firstX.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                appContext.firstPoint.x = (float)((double)  firstX.getValue());
            }
        });
        firstY.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                appContext.firstPoint.y = (float)((double) firstY.getValue());
            }
        });
        firstZ.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                appContext.firstPoint.z = (float)((double)  firstZ.getValue());
            }
        });

        secondX.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                appContext.secondPoint.x = (float)((double)  secondX.getValue());
            }
        });
        secondY.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                appContext.secondPoint.y = (float)((double) secondY.getValue());
            }
        });
        secondZ.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                appContext.secondPoint.z = (float)((double) secondZ.getValue());
            }
        });

        Timer timer = new Timer(50, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (firstLeft.isSelected()) {
                    jSpinner1.setValue(((Integer) jSpinner1.getValue()) - 1);
                }
                if (firstRight.isSelected()) {
                    jSpinner1.setValue(((Integer) jSpinner1.getValue()) + 1);
                }
                if (secondLeft.isSelected()) {
                    jSpinner2.setValue(((Integer) jSpinner2.getValue()) - 1);
                }
                if (secondRight.isSelected()) {
                    jSpinner2.setValue(((Integer) jSpinner2.getValue()) + 1);
                }
                if (ThirdLeft.isSelected()) {
                    jSpinner3.setValue(((Integer) jSpinner3.getValue()) - 1);
                }
                if (thirdRight.isSelected()) {
                    jSpinner3.setValue(((Integer) jSpinner3.getValue()) + 1);
                }
                if (planeLeft.isSelected()) {
                    jSpinner4.setValue(((Integer) jSpinner4.getValue()) - 1);
                }
                if (planeRight.isSelected()) {
                    jSpinner4.setValue(((Integer) jSpinner4.getValue()) + 1);
                }
                if(cameraLeftX.getModel().isPressed()) {
                    appContext.cameraOffsetX -=0.5;
                }
                if(cameraRightX.getModel().isPressed()) {
                    appContext.cameraOffsetX +=0.5;
                }
                  if(cameraLeftY.getModel().isPressed()) {
                    appContext.cameraOffsetY -=0.5;
                }
                if(cameraRightY.getModel().isPressed()) {
                    appContext.cameraOffsetY +=0.5;
                }
                if(cameraLeftZ.getModel().isPressed()) {
                    appContext.cameraOffsetZ -=0.5;
                }
                if(cameraRightZ.getModel().isPressed()) {
                    appContext.cameraOffsetZ +=0.5;
                }
                
                 if(angleLeftX.getModel().isPressed()) {
                    appContext.cameraAngleX -=0.5;
                }
                if(angleRightX.getModel().isPressed()) {
                    appContext.cameraAngleX +=0.5;
                }
                 if(angleLeftY.getModel().isPressed()) {
                    appContext.cameraAngleY -=0.5;
                }
                if(angleRightY.getModel().isPressed()) {
                    appContext.cameraAngleY +=0.5;
                }
                 if(angleLeftZ.getModel().isPressed()) {
                    appContext.cameraAngleZ -=0.5;
                }
                if(angleRightZ.getModel().isPressed()) {
                    appContext.cameraAngleZ +=0.5;
                }
            }
        });
        timer.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        firstLeft = new javax.swing.JToggleButton();
        firstRight = new javax.swing.JToggleButton();
        secondRight = new javax.swing.JToggleButton();
        secondLeft = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        thirdRight = new javax.swing.JToggleButton();
        ThirdLeft = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        planeRight = new javax.swing.JToggleButton();
        planeLeft = new javax.swing.JToggleButton();
        jLabel4 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jSpinner3 = new javax.swing.JSpinner();
        jSpinner4 = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        firstX = new javax.swing.JSpinner();
        firstY = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cameraLeftX = new javax.swing.JButton();
        cameraRightX = new javax.swing.JButton();
        cameraLeftY = new javax.swing.JButton();
        cameraRightY = new javax.swing.JButton();
        cameraRightZ = new javax.swing.JButton();
        cameraLeftZ = new javax.swing.JButton();
        angleRightZ = new javax.swing.JButton();
        angleLeftZ = new javax.swing.JButton();
        angleLeftY = new javax.swing.JButton();
        angleRightY = new javax.swing.JButton();
        angleLeftX = new javax.swing.JButton();
        angleRightX = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        secondX = new javax.swing.JSpinner();
        jLabel15 = new javax.swing.JLabel();
        secondY = new javax.swing.JSpinner();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        firstZ = new javax.swing.JSpinner();
        secondZ = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setText("Вращать первый кубоид");

        firstLeft.setText("<<");
        firstLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstLeftActionPerformed(evt);
            }
        });

        firstRight.setText(">>");
        firstRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstRightActionPerformed(evt);
            }
        });

        secondRight.setText(">>");
        secondRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                secondRightActionPerformed(evt);
            }
        });

        secondLeft.setText("<<");
        secondLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                secondLeftActionPerformed(evt);
            }
        });

        jLabel2.setText("Вращать второй кубоид");

        thirdRight.setText(">>");
        thirdRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thirdRightActionPerformed(evt);
            }
        });

        ThirdLeft.setText("<<");
        ThirdLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThirdLeftActionPerformed(evt);
            }
        });

        jLabel3.setText("Вращать третий кубоид");

        planeRight.setText(">>");
        planeRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planeRightActionPerformed(evt);
            }
        });

        planeLeft.setText("<<");
        planeLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planeLeftActionPerformed(evt);
            }
        });

        jLabel4.setText("Вращать плоскость");

        jLabel5.setText("Первая точка плоскости");

        firstX.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), null, null, Float.valueOf(1.0f)));
        firstX.setRequestFocusEnabled(false);

        firstY.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), null, null, Float.valueOf(1.0f)));

        jLabel6.setText("X");

        jLabel7.setText("Y");

        jLabel8.setText("Z");

        jLabel9.setText("Вторая точка плоскости");

        cameraLeftX.setText("<<");
        cameraLeftX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cameraLeftXActionPerformed(evt);
            }
        });

        cameraRightX.setText(">>");

        cameraLeftY.setText("<<");

        cameraRightY.setText(">>");

        cameraRightZ.setText(">>");

        cameraLeftZ.setText("<<");

        angleRightZ.setText(">>");

        angleLeftZ.setText("<<");

        angleLeftY.setText("<<");

        angleRightY.setText(">>");

        angleLeftX.setText("<<");
        angleLeftX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                angleLeftXActionPerformed(evt);
            }
        });

        angleRightX.setText(">>");

        jLabel12.setText("Камера");

        jLabel13.setText("Вращение сцены");

        jLabel14.setText("X");

        secondX.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), null, null, Float.valueOf(1.0f)));
        secondX.setRequestFocusEnabled(false);

        jLabel15.setText("Y");

        secondY.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), null, null, Float.valueOf(1.0f)));

        jLabel16.setText("Z");

        jLabel17.setText("X");

        jLabel18.setText("Y");

        jLabel19.setText("Z");

        jLabel20.setText("X");

        jLabel21.setText("Y");

        jLabel22.setText("Z");

        firstZ.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), null, null, Float.valueOf(1.0f)));

        secondZ.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), null, null, Float.valueOf(1.0f)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(secondX, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(secondY, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(secondZ, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSpinner2, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(secondLeft)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(secondRight)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(planeLeft)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(planeRight))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ThirdLeft)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(thirdRight)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(firstLeft)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(firstRight))))
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(angleLeftX, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cameraLeftX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cameraLeftZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cameraLeftY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cameraRightX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cameraRightY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cameraRightZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(angleRightX, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(angleLeftY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(angleLeftZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(angleRightY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(angleRightZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(firstX, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(firstY, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(firstZ, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(firstLeft)
                            .addComponent(firstRight)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(secondLeft)
                            .addComponent(secondRight)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ThirdLeft)
                            .addComponent(thirdRight)
                            .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(planeLeft)
                            .addComponent(planeRight)
                            .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(firstX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(firstY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(firstZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(secondX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(secondY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(secondZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cameraLeftX)
                            .addComponent(cameraRightX)
                            .addComponent(jLabel17))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cameraLeftY)
                            .addComponent(cameraRightY)
                            .addComponent(jLabel18))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cameraLeftZ)
                            .addComponent(cameraRightZ)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(angleLeftX)
                            .addComponent(angleRightX)
                            .addComponent(jLabel20))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(angleLeftY)
                            .addComponent(angleRightY)
                            .addComponent(jLabel21))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(angleLeftZ)
                            .addComponent(angleRightZ)
                            .addComponent(jLabel22))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void firstLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstLeftActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstLeftActionPerformed

    private void firstRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstRightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstRightActionPerformed

    private void secondRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_secondRightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_secondRightActionPerformed

    private void secondLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_secondLeftActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_secondLeftActionPerformed

    private void thirdRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thirdRightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thirdRightActionPerformed

    private void ThirdLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThirdLeftActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ThirdLeftActionPerformed

    private void planeRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planeRightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_planeRightActionPerformed

    private void planeLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planeLeftActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_planeLeftActionPerformed

    private void cameraLeftXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cameraLeftXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cameraLeftXActionPerformed

    private void angleLeftXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_angleLeftXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_angleLeftXActionPerformed

    public void setGLCanvas(GLCanvas canvas) {
        jPanel1.add(canvas);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton ThirdLeft;
    private javax.swing.JButton angleLeftX;
    private javax.swing.JButton angleLeftY;
    private javax.swing.JButton angleLeftZ;
    private javax.swing.JButton angleRightX;
    private javax.swing.JButton angleRightY;
    private javax.swing.JButton angleRightZ;
    private javax.swing.JButton cameraLeftX;
    private javax.swing.JButton cameraLeftY;
    private javax.swing.JButton cameraLeftZ;
    private javax.swing.JButton cameraRightX;
    private javax.swing.JButton cameraRightY;
    private javax.swing.JButton cameraRightZ;
    private javax.swing.JToggleButton firstLeft;
    private javax.swing.JToggleButton firstRight;
    private javax.swing.JSpinner firstX;
    private javax.swing.JSpinner firstY;
    private javax.swing.JSpinner firstZ;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JToggleButton planeLeft;
    private javax.swing.JToggleButton planeRight;
    private javax.swing.JToggleButton secondLeft;
    private javax.swing.JToggleButton secondRight;
    private javax.swing.JSpinner secondX;
    private javax.swing.JSpinner secondY;
    private javax.swing.JSpinner secondZ;
    private javax.swing.JToggleButton thirdRight;
    // End of variables declaration//GEN-END:variables
}
