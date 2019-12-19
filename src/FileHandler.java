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
    public static boolean saveAs()
    {
        currentFile = getSaveFileFromChooser();
        if(currentFile != null)
        {
            save();
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean save()
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
                    assert fileWriter != null;
                    fileWriter.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
            return true;
        }
        else
        {
            return saveAs();
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
            String filename = makeFileTXT(fileChooser.getSelectedFile().getName());
            String filepath = fileChooser.getSelectedFile().getParent() + '/' + filename;
            System.out.println(filepath);
            fileToSave = new File(filepath);
        }
        return fileToSave;
    }

    private static String makeFileTXT(String filename)
    {
        if(filename.contains(".txt"))
        {
            return filename;
        }
        else if(filename.contains("."))
        {
            char[] nameArray = new char[filename.length()];

            for(int i=0; i<filename.length(); i++)
            {
                if(filename.charAt(i) != '.')
                {
                    nameArray[i] = filename.charAt(i);
                }
                else
                {
                    break;
                }
            }

            StringBuilder str = new StringBuilder();
            for(char c: nameArray)
            {
                if(c != '\u0000')
                {
                    str.append(c);
                }
            }
            str.append(".txt");

            return str.toString();
        }
        else
        {
            filename = filename + (".txt");
            return filename;
        }
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
        System.out.println(showSaveFileDialog());
    }

    private static boolean showSaveFileDialog()
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
            return save();
        }

        return (input == 1);
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
