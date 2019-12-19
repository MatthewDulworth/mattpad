import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("DuplicatedCode")
public class FileHandler
{
    // ------------------------------------------------------
    // Member Vars
    // ------------------------------------------------------
    private static File currentFile;
    private static JTextArea textArea;

    // ------------------------------------------------------
    // Save Methods
    // ------------------------------------------------------
    public static void saveAs()
    {
        currentFile = getSaveFileFromChooser();
        if(currentFile != null)
        {
            save();
        }
    }

    public static void save()
    {
        FileWriter fileWriter = null;

        if(currentFile != null)
        {
            try
            {
                fileWriter = new FileWriter(currentFile);
                fileWriter.write(textArea.getText());
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
            saveAs();
        }
    }


    // ------------------------------------------------------
    // Get File Dialogs
    // ------------------------------------------------------
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


    // ------------------------------------------------------
    // Open File
    // ------------------------------------------------------
    public static void openFile()
    {
        showSaveFileDialog();
    }

    private static void showSaveFileDialog()
    {
        String[] options = {"Save","Don't Save" ,"Cancel"};
        int input = JOptionPane.showOptionDialog(
                null,
                "Save work before opening new file?",
                "Save work?",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        if(input == 0)
        {
            save();
        }
    }


    // ------------------------------------------------------
    // New File
    // ------------------------------------------------------
    public static void newFile()
    {

    }

    // ------------------------------------------------------
    // Setter
    // ------------------------------------------------------
    public static void setTextArea(JTextArea textArea)
    {
        FileHandler.textArea = textArea;
    }
}
