package gradle.tooling.sysprop;

import java.io.File;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

/**
 *
 * @author Laszlo Kishalmi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("my.prop", "its there");
        ProjectConnection conn = GradleConnector.newConnector().forProjectDirectory(new File(".")).connect();
        BuildLauncher build = conn.newBuild();
        build.setStandardError(System.err);
        build.setStandardOutput(System.out);
        build.withArguments("-b", "test.gradle");
        build.forTasks("checkProperty");
        build.run();
        conn.close();
    }
    
}
