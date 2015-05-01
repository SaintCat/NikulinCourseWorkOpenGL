package oyakov.model.type;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

/**
 * Created by oyakovlev on 30.03.2015.
 */
public class FSEntityLoader {

    private static final Logger log = Logger.getLogger(FSEntityLoader.class.getName());

    public FSEntityLoader() {

    }

    public Entity loadEntity(String source, MeshType type) throws IOException {
        BufferedReader in = null;
        List<Vertex> vertices = new ArrayList<>();
        List<Renderable> triangles = new ArrayList<>();

        try {
            in = new BufferedReader(new InputStreamReader(FSEntityLoader.class.getResourceAsStream("/oyakov/model/type/" + source)));

            String line;

            log.info("Started parsing geometry from source: " + source);

            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("T:")) {
                    line = line.substring(2);
                    StringTokenizer strTok = new StringTokenizer(line, ",", false);
                    if (strTok.countTokens() == 5) {
                        float x, y, z, u, v;
                        try {
                            x = Float.parseFloat(strTok.nextToken());
                            y = Float.parseFloat(strTok.nextToken());
                            z = Float.parseFloat(strTok.nextToken());
                            u = Float.parseFloat(strTok.nextToken());
                            v = Float.parseFloat(strTok.nextToken());

                            vertices.add(new Vertex(x, y, z, u, v));
                        } catch (NumberFormatException nfe) {
                            log.info("Malformed line: error while parsing line");
                            throw new IOException("Couldn't parse geometry...");
                        }
                    } else {
                        log.info("Malformed line: wrong number of tokens");
                        throw new IOException("Couldn't parse geometry...");
                    }
                }
            }

            log.info("Successfully parsed geometry from source: " + source);

            switch (type) {
                case TRIANGLE:
                    if (vertices.size() % 3 != 0) {
                        log.info("Malformed geometry: couldn't be expressed as a set of trangles");
                        throw new IOException("File has wrong number of lines");
                    }
                    break;
                case QUARD:
                    if (vertices.size() % 4 != 0) {
                        log.info("Malformed geometry: couldn't be expressed as a set of trangles");
                        throw new IOException("File has wrong number of lines");
                    }
                    break;
            }

            log.info("Forming an Entity...");

            switch (type) {
                case TRIANGLE:
                    for (int i = 0; i < vertices.size() / 3; i++) {
                        Triangle t = new Triangle(vertices.get(3 * i),
                                vertices.get(3 * i + 1),
                                vertices.get(3 * i + 2));
                        triangles.add(t);
                    }
                    break;
                case QUARD:
                    for (int i = 0; i < vertices.size() / 4; i++) {
                        Quard t = new Quard(vertices.get(4 * i),
                                vertices.get(4 * i + 1),
                                vertices.get(4 * i + 2),
                                vertices.get(4 * i + 3));
                        triangles.add(t);
                    }
                    break;
            }

        } finally {
            if (in != null) {
                in.close();
            }
        }

        return new Entity(triangles);
    }
}
