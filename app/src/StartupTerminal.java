import java.io.File;
import java.io.IOException;

public class StartupTerminal {
    static String javaHome = System.getProperty("java.home");
    static String javacPath = javaHome + File.separator + "bin" + File.separator + "javac";

    public static void main(String[] args) {
        try {

            String jarPath = "out/artifacts/app_jar/app.jar";

            String osName = System.getProperty("os.name").toLowerCase();
            ProcessBuilder runBuilder = new ProcessBuilder();

            if (osName.contains("win")) {
                runBuilder = new ProcessBuilder( "cmd.exe", "/c", "start", "java", "-jar", jarPath);
            } else if (osName.contains("linux")) {
                runBuilder.command("xfce4-terminal", "--command", "java -jar " + jarPath);
            } else if (osName.contains("mac")) {
                runBuilder = new ProcessBuilder();
            } else {
                System.err.println("Unsupported operating system: " + osName);
                System.exit(1); // Exit with error code
            }


            runBuilder.start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}