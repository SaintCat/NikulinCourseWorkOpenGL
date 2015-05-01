package oyakov.model.type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import oyakov.model.type.Quard.DrawType;

/**
 * Created by oyakovlev on 31.03.2015.
 */
public class Entity implements Renderable {

    private List<Renderable> geometry;

    public Entity() {
    }

    public Entity(List<Renderable> geometry) {
        this.geometry = geometry;
    }

    @Override
    public void renderSelf(GL context, GLU glUtils, DrawType type) {
        geometry.forEach(e -> e.renderSelf(context, glUtils, type));
    }

    @Override
    public Renderable mirrorX() {

        List<Renderable> reflection = geometry.stream().map(Renderable::mirrorX).collect(Collectors.toList());

        return new Entity(reflection);
    }

    @Override
    public Renderable copy() {
        List<Renderable> result = new ArrayList<>();
        geometry.stream().forEach((r) -> {
            result.add(r.copy());
        });
        return new Entity(result);
    }
}
