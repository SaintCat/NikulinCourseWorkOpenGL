package oyakov.model.type;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import oyakov.model.type.Quard.DrawType;


/**
 * Created by oyakovlev on 30.03.2015.
 */
public interface Renderable {
    void renderSelf(GL context, GLU glUtils, DrawType type);

    Renderable mirrorX();
    
    Renderable copy();
}
