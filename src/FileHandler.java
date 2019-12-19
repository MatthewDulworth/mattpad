import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("DuplicatedCode")
public class FileHandler
{
    private static File currentFile;

    public static boolean saveFileExists()
    {
        return (currentFile != null);
    }

    public static void saveAs(String contents)
    {
        currentFile = getSaveFileFromChooser();
        save(contents);
    }

    public static void save(String contents)
    {
        FileWriter fileWriter = null;

        if(saveFileExists())
        {
            try
            {
                fileWriter = new FileWriter(currentFile);
                fileWriter.write(contents);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    fileWriter.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            saveAs(contents);
        }
    }

    private static File getSaveFileFromChooser()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save As");
        int result = fileChooser.showSaveDialog(null);
        File fileToSave = null;

        if(result == JFileChooser.APPROVE_OPTION)
        {
            fileToSave = fileChooser.getSelectedFile();
        }
        return fileToSave;
    }

    private static File getOpenFileFromChooser()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save As");
        int result = fileChooser.showSaveDialog(null);
        File fileToSave = null;

        if(result == JFileChooser.APPROVE_OPTION)
        {
            fileToSave = fileChooser.getSelectedFile();
            System.out.println(fileToSave.getAbsolutePath());
        }
        return fileToSave;
    }

    public static void openFile()
    {

    }

    public static void newFile()
    {

    }
}
