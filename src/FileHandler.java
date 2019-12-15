import sun.lwawt.macosx.CSystemTray;

import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler
{
    private static String HOME = System.getProperty("user.home");
    private static Path currentFile;
    private static JTextArea textArea;

    public static void Save()
    {
        if(currentFile != null)
        {
            textArea.getText();
        }
    }

    public static void Load(String filepath)
    {
        Save();
        currentFile = Paths.get(filepath);
    }

    public static void New()
    {
        Save();
        currentFile = Paths.get("file.txt");
    }
}
