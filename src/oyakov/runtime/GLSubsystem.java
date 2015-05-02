package oyakov.runtime;

import com.sun.opengl.util.FPSAnimator;
import oyakov.controllers.GLController;
import oyakov.controllers.KeyboardController;
import oyakov.model.type.FSEntityLoader;
import oyakov.model.type.Renderable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.media.opengl.GLCanvas;
import oyakov.controllers.MainViewFrame;
import oyakov.model.type.MeshType;

/**
 * Created by oyakovlev on 30.03.2015.
 */
public class GLSubsystem {

    private static GLSubsystem instance;

    public static GLSubsystem getInstance() {
        if (instance == null) {
            instance = new GLSubsystem();
        }
        return instance;
    }

    private GLSubsystem() {
    }

    private MainViewFrame frame;
    private FPSAnimator animator;

    private FSEntityLoader fsEntityLoader;

    private Map<String, Renderable> entityCache;

    private static final Logger log = Logger.getLogger(GLSubsystem.class.getName());

    public void initialize() {
        GLCanvas canvas = new GLCanvas();
        canvas.setPreferredSize(
                new Dimension(
                        800,
                        600
                )
        );
        canvas.addKeyListener(new KeyboardController());
        canvas.addGLEventListener(new GLController());

        canvas.setFocusable(true);
        canvas.requestFocus();

        animator = new FPSAnimator(canvas, 60, true);

//        frame = new JFrame(ConfParmSubsystem.getInstance().getCtxt().getTITLE());
        frame = new MainViewFrame();
//        frame.getContentPane().add(canvas);
        frame.setGLCanvas(canvas);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread() {
                    @Override
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }.start();
            }
        });

        fsEntityLoader = new FSEntityLoader();
        entityCache = new HashMap<>();
    }

    public void activate() {
        frame.pack();
        frame.setVisible(true);

        animator.start();
    }

    public Renderable getEntity(String key, MeshType type) {
        if (entityCache.containsKey(key)) {
            return entityCache.get(key);
        } else {
            try {
                Renderable e = fsEntityLoader.loadEntity(key, type);
                entityCache.put(key, e);
                return e;
            } catch (IOException ioe) {
                log.info("Failed to load entity...");
                return null;
            }
        }
    }

    public Renderable mirrorX(String key, Renderable prototype) {
        if (entityCache.containsKey(key)) {
            return entityCache.get(key);
        } else {
            Renderable reflection = prototype.mirrorX();
            entityCache.put(key, reflection);
            return reflection;
        }
    }

    public Renderable copy(String key, Renderable prototype) {
        if (entityCache.containsKey(key)) {
            return entityCache.get(key);
        } else {
            Renderable copy = prototype.copy();
            entityCache.put(key, copy);
            return copy;
        }
    }
    
}
