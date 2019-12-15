import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener
{
    // ------------------------------------------------------
    // Member Vars
    // ------------------------------------------------------
    JFrame frame;
    JTextArea textArea;
    JMenuBar menuBar;

    // ------------------------------------------------------
    // Constructor
    // ------------------------------------------------------
    Window()
    {
        frame = new JFrame("mattpad");
        textArea = new JTextArea();
        menuBar = new JMenuBar();



        setupMenu();

        frame.setJMenuBar(menuBar);
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // ------------------------------------------------------
    // Menu
    // ------------------------------------------------------
    private void setupMenu()
    {
        // File Menu
        JMenu fileMenu = new JMenu("File");

        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem openItem = new JMenuItem("Save");
        JMenuItem newItem = new JMenuItem("Save");

        saveItem.addActionListener(this);




        // Edit Menu



        menuBar.add(fileMenu);
    }

    // ------------------------------------------------------
    // Action Performed
    // ------------------------------------------------------
    public void actionPerformed(ActionEvent e) {

    }
}
