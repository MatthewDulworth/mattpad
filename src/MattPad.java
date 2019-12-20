import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;

@SuppressWarnings("DuplicatedCode")
public class MattPad extends JFrame
{
    // ------------------------------------------------------
    // Member Vars
    // ------------------------------------------------------
    private JFrame frame;
    private JMenuBar menuBar;
    private JPanel contentPane;
    private JTextArea textArea;

    // ------------------------------------------------------
    // Constructor
    // ------------------------------------------------------
    public MattPad()
    {
        frame = new JFrame("");

        setupMenuBar();
        setupContentPane();

        FileHandler.setTextArea(textArea);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400, 400));
        frame.pack();
        frame.setVisible(true);
    }

    // ------------------------------------------------------
    // Menu Bar
    // ------------------------------------------------------
    private void setupMenuBar()
    {
        menuBar = new JMenuBar();

        setupFileMenu();
        setupEditMenu();
        setupUndoButtons();

        frame.setJMenuBar(menuBar);
    }

    private void setupFileMenu()
    {
        JMenu fileMenu = new JMenu("File");

        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem newItem = new JMenuItem("New");

        saveItem.addActionListener(e -> {
            FileHandler.save();
            frame.setTitle(FileHandler.getFileName());
        });
        openItem.addActionListener(e -> {
            FileHandler.openFile();
            frame.setTitle(FileHandler.getFileName());
        });
        newItem.addActionListener(e -> {
            FileHandler.newFile();
            frame.setTitle(FileHandler.getFileName());
        });

        fileMenu.add(saveItem);
        fileMenu.add(openItem);
        fileMenu.add(newItem);

        menuBar.add(fileMenu);
    }

    private void setupEditMenu()
    {
        JMenu editMenu = new JMenu("Edit");

        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");
        JMenuItem printItem = new JMenuItem("Print");

        cutItem.addActionListener(e -> textArea.cut());
        copyItem.addActionListener(e -> textArea.copy());
        pasteItem.addActionListener(e -> textArea.paste());
        printItem.addActionListener(e -> {
            try {
                textArea.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        });

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.add(printItem);

        menuBar.add(editMenu);
    }

    private void setupUndoButtons()
    {
        JButton undoButton = new JButton("Undo");
        JButton redoButton = new JButton("Redo");

        menuBar.add(undoButton);
        menuBar.add(redoButton);
    }

    // ------------------------------------------------------
    // Content Pane
    // ------------------------------------------------------
    private void setupContentPane()
    {
        contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout());

        setupTextArea();

        frame.setContentPane(contentPane);
    }

    private void setupTextArea()
    {
        textArea = new JTextArea(30, 40);
        textArea.setLineWrap(true);

        textArea.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { onEdit(); }
            public void removeUpdate(DocumentEvent e) { onEdit(); }
            public void changedUpdate(DocumentEvent e) { onEdit(); }
        });

        JScrollPane scrollPane = new JScrollPane(textArea);

        contentPane.add(scrollPane);
    }

    private void onEdit()
    {
        if(FileHandler.docIsEdited())
        {
            frame.setTitle(FileHandler.getFileName() + " -edited");
        }
        else
        {
            frame.setTitle(FileHandler.getFileName());
        }
    }


    // ------------------------------------------------------
    // Main Method
    // ------------------------------------------------------
    public static void main(String[] args)
    {
        new MattPad();
    }
}
