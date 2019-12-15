import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("DuplicatedCode")
public class Application extends JFrame
{
    // ------------------------------------------------------
    // Member Vars
    // ------------------------------------------------------
    private JFrame frame;
    private JMenuBar menuBar;
    private JPanel contentPane;

    // ------------------------------------------------------
    // Constructor
    // ------------------------------------------------------
    public Application()
    {
        frame = new JFrame("mattpad");

        setupMenuBar();
        setupContentPane();

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

        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
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

        cutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        copyItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        pasteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        printItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        JTextArea textArea = new JTextArea(30, 40);
        textArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(textArea);

        contentPane.add(scrollPane);
    }

    // ------------------------------------------------------
    // Main Method
    // ------------------------------------------------------
    public static void main(String args[])
    {
        new Application();
    }
}
