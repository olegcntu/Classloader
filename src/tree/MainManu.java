package tree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainManu extends JFrame {
    private static MainManu frame;
    private JPanel mainPanel;
    private JButton button1;
    private String fileName;
    private JLabel Label1;
    private JLabel Label2;
    private JTree tree;


    public MainManu(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.button1.setSize(200, 200);
        this.button1.setLocation(100, 100);
        DefaultMutableTreeNode root=null;
        tree.setModel((new JTree(root)).getModel());

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileName = getFileName("*.jar");
                System.out.println(fileName);
                Label1.setText(fileName);
                workTree();
            }
        });

    }

    public static void main(String[] args) {
        frame = new MainManu("Classloader");
        frame.setVisible(true);


    }


    String getFileName(String filter) {
        FileDialog fileDialog = new FileDialog(frame);
        fileDialog.setMode(FileDialog.LOAD);
        fileDialog.setFile(filter);
        fileDialog.setVisible(true);
        String dr = fileDialog.getDirectory();
        String fn = fileDialog.getFile();
        if (dr == null || fn == null)
            return null;
        return dr + fn;
    }

    public void workTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(worNormalName(fileName));

        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vegetables");
        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");
        root.add(vegetableNode);
        root.add(fruitNode);
        tree.setModel((new JTree(root)).getModel());
    }

    public String worNormalName(String path){
        String st[]=path.split("\\\\");
        return st[st.length-1];
    }

}
