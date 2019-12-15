import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener
{
    // ------------------------------------------------------
    // Member Vars
    // ------------------------------------------------------
    private JFrame frame;
    private JMenuBar menuBar;

    // ------------------------------------------------------
    // Constructor
    // ------------------------------------------------------
    public Window()
    {
        frame = new JFrame("mattpad");

        setupMenuBar();

        frame.setJMenuBar(menuBar);
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // ------------------------------------------------------
    // Setup Menus
    // ------------------------------------------------------
    private void setupMenuBar()
    {
        menuBar = new JMenuBar();

        setupFileMenu();
        setupEditMenu();
        setupUndoButtons();
    }

    private void setupFileMenu()
    {
        JMenu fileMenu = new JMenu("File");

        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem newItem = new JMenuItem("New");

        saveItem.addActionListener(this);
        openItem.addActionListener(this);
        newItem.addActionListener(this);

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

        cutItem.addActionListener(this);
        copyItem.addActionListener(this);
        pasteItem.addActionListener(this);
        printItem.addActionListener(this);

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
    // Action Performed
    // ------------------------------------------------------
    public void actionPerformed(ActionEvent e) {

    }
}
