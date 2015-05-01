package oyakov.runtime;
import java.util.logging.Logger;

class RuntimeLauncher {

    private static Logger log;

    public static void main(String[] args) {
        ConfParmSubsystem confParmSubsystem = ConfParmSubsystem.getInstance();
        confParmSubsystem.initialize();

        log = Logger.getLogger(RuntimeLauncher.class.getName());
        log.info("ConfParmLauncher has been initialized");

        GLSubsystem glSubsystem = GLSubsystem.getInstance();
        glSubsystem.initialize();
        glSubsystem.activate();

        log.info("GLSubsystem has been initialized");
    }
}
