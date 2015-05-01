package oyakov.model.type;

/**
 * Created by oyakovlev on 30.03.2015.
 */
public class Vertex {
    public float x, y, z,
            u, v;

    public Vertex() {}

    public Vertex(Vertex proto) {
        x = proto.x;
        y = proto.y;
        z = proto.z;
        u = proto.u;
        v = proto.v;
    }

    public Vertex(float x, float y, float z, float u, float v) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.u = u;
        this.v = v;
    }

    public String toString() {
        return "[" + x + ", " + y + ", " + z + "]" + " [" + u + ", " + v + "]";
    }

}
