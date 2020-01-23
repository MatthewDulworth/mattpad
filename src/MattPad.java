import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;

public class MattPad
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

        FileHandler.init(textArea, frame);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400, 400));
        frame.pack();
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if(FileHandler.checkSaves())
                {
                    frame.dispose();
                    System.exit(0);
                }
            }
        });
    }

    // ------------------------------------------------------
    // Menu Bar
    // ------------------------------------------------------
    private void setupMenuBar()
    {
        menuBar = new JMenuBar();

        setupFileMenu();
        setupEditMenu();
        //setupUndoButtons();

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
        contentPane.setLayout(new BorderLayout());

        setupTextArea();

        frame.setContentPane(contentPane);
    }

    private void setupTextArea()
    {
        textArea = new JTextArea(30, 40);
        textArea.setLineWrap(true);

        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { onEdit(); }
            @Override public void removeUpdate(DocumentEvent e) { onEdit(); }
            @Override public void changedUpdate(DocumentEvent e) { onEdit(); }
        });

        JScrollPane scrollPane = new JScrollPane(textArea);

        contentPane.add(scrollPane, BorderLayout.CENTER);
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
        // look and feel adapted from https://stackoverflow.com/questions/36128291/how-to-make-a-swing-application-have-dark-nimbus-theme-netbeans
        UIManager.put( "control", new Color( 128, 128, 128) );
        UIManager.put( "info", new Color(128,128,128) );
        UIManager.put( "nimbusBase", new Color( 18, 30, 49) );
        UIManager.put( "nimbusAlertYellow", new Color( 248, 187, 0) );
        UIManager.put( "nimbusDisabledText", new Color( 128, 128, 128) );
        UIManager.put( "nimbusFocus", new Color(115,164,209) );
        UIManager.put( "nimbusGreen", new Color(176,179,50) );
        UIManager.put( "nimbusInfoBlue", new Color( 66, 139, 221) );
        UIManager.put( "nimbusLightBackground", new Color( 18, 30, 49) );
        UIManager.put( "nimbusOrange", new Color(191,98,4) );
        UIManager.put( "nimbusRed", new Color(169,46,34) );
        UIManager.put( "nimbusSelectedText", new Color( 255, 255, 255) );
        UIManager.put( "nimbusSelectionBackground", new Color( 104, 93, 156) );
        UIManager.put( "text", new Color( 230, 230, 230) );
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (javax.swing.UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new MattPad();
    }
}
