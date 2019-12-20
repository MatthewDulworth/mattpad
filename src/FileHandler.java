import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Stream;


public class FileHandler
{
    // ------------------------------------------------------
    // Member Vars
    // ------------------------------------------------------
    private static File currentFile;
    private static String lastSaveSnapshot = "";
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
                lastSaveSnapshot = textArea.getText();
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
    // GUI Methods
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
            fileToSave = new File(filepath);
        }
        return fileToSave;
    }

    private static File getOpenFileFromChooser()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open File");
        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("txt files (*.txt)", "txt");
        fileChooser.setFileFilter(txtFilter);

        int result = fileChooser.showOpenDialog(null);
        File fileToOpen = null;

        if(result == JFileChooser.APPROVE_OPTION)
        {
            fileToOpen = fileChooser.getSelectedFile();
        }
        return fileToOpen;
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
    // Open File
    // ------------------------------------------------------
    public static void openFile()
    {
        // if the document has been changed since the last save
        if(docIsEdited())
        {
            // if the save file dialog or the save is not canceled or closed
            if(showSaveFileDialog())
            {
                openTheFile();
            }
        }
        else
        {
            openTheFile();
        }
    }

    private static void openTheFile()
    {
        // get a file from the chooser then set the working file to that;
        currentFile = getOpenFileFromChooser();

        // set the text area's content and the last save to that files contents
        String content = readFileIntoString(currentFile);
        textArea.setText(content);
        lastSaveSnapshot = content;
    }


    // ------------------------------------------------------
    // New File
    // ------------------------------------------------------
    public static void newFile(){}


    // ------------------------------------------------------
    // Utility
    // ------------------------------------------------------
    public static void setTextArea(JTextArea textArea)
    {
        FileHandler.textArea = textArea;
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

    public static String readFileIntoString(File file)
    {
        StringBuilder strBuild = new StringBuilder();

        try(Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8)){
            stream.forEach(s -> strBuild.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return strBuild.toString();
    }

    public static boolean docIsEdited()
    {
        return !textArea.getText().equals(lastSaveSnapshot);
    }

    public static String getFileName()
    {
        if(currentFile != null)
        {
            return currentFile.getName();
        }
        else
        {
            return "";
        }
    }

}
