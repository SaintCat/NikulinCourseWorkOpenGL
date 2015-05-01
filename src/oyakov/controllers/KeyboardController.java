package oyakov.controllers;

import oyakov.runtime.ConfParmSubsystem;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

/**
 * Created by oyakovlev on 28.03.2015.
 */
public class KeyboardController implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        ConfParmSubsystem.AppContext appContext = ConfParmSubsystem.getInstance().getCtxt();

        switch(e.getKeyCode()) {
            case VK_W:
                appContext.cameraAngleX+=1.0f;
                break;
            case VK_A:
                appContext.cameraAngleY-=1.0f;
                break;
            case VK_S:
                appContext.cameraAngleX-=1.0f;
                break;
            case VK_D:
                appContext.cameraAngleY+=1.0f;
                break;
            case VK_Q:
                appContext.cameraAngleZ-=1.0f;
                break;
            case VK_E:
                appContext.cameraAngleZ+=1.0f;
                break;
            case VK_LEFT:
                appContext.cameraOffsetX-=1.0f;
                break;
            case VK_RIGHT:
                appContext.cameraOffsetX+=1.0f;
                break;
            case VK_UP:
                appContext.cameraOffsetY+=1.0f;
                break;
            case VK_DOWN:
                appContext.cameraOffsetY-=1.0f;
                break;
            case VK_PAGE_UP:
                appContext.cameraOffsetZ+=1.0f;
                break;
            case VK_PAGE_DOWN:
                appContext.cameraOffsetZ-=1.0f;
                break;
            case VK_I:
                if(appContext.leftWingAngle < 0.0f)
                    appContext.leftWingAngle+=1.0f;
                break;
            case VK_O:
                if(-appContext.leftWingAngle + appContext.rightWingAngle == 180.0f) {
                    if(appContext.rightWingAngle > 0.0f) {
                        appContext.leftWingAngle-=1.0f;
                        appContext.rightWingAngle-=1.0f;
                    }
                } else {
                    appContext.leftWingAngle-=1.0f;
                }
                break;
            case VK_K:
                if(-appContext.leftWingAngle + appContext.rightWingAngle == 180.0f) {
                    if(appContext.leftWingAngle < 0.0f) {
                        appContext.leftWingAngle+=1.0f;
                        appContext.rightWingAngle+=1.0f;
                    }
                } else {
                    appContext.rightWingAngle+=1.0f;
                }
                break;
            case VK_L:
                if(appContext.rightWingAngle > 0.0f)
                    appContext.rightWingAngle-=1.0f;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
