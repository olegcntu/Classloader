package tree;

import classLoad.SearchOfClass;
import obj.*;
import obj.Package;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainMenu extends JFrame {
    public static MainMenu frame;
    private JPanel mainPanel;
    private JButton button1;
    private String fileName;
    private JLabel Label1;
    private JLabel Label2;
    private JTree tree;
    private JButton getInfoButton;
    private JTextArea textArea1;
    private JTextField textField1;
    private ArrayList<Package> pack;

    public MainMenu(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.button1.setSize(200, 200);
        this.button1.setLocation(100, 100);
        DefaultMutableTreeNode root = null;
        tree.setModel((new JTree(root)).getModel());

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileName = getFileName("*.jar");
                System.out.println(fileName);
                Label1.setText(fileName);
                SearchOfClass l = new SearchOfClass();
                try {
                    pack = l.Work(fileName);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                workTree(pack);
            }
        });

        getInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode sn = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
                textArea1.setText(sn.getUserObject().toString());
            }
        });
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        Thread thread = new Thread(() -> {
            frame = new MainMenu("Classloader");
            frame.setVisible(true);
        });
        thread.start();

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

    public void workTree(ArrayList<Package> pack) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(worNormalName(fileName));

        DefaultMutableTreeNode packageArr = null;
        DefaultMutableTreeNode filesArr = null;
        DefaultMutableTreeNode classesArr = null;
        DefaultMutableTreeNode valuesArr = null;
        DefaultMutableTreeNode methodsArr = null;


        for (Package p : pack) {
            packageArr = new DefaultMutableTreeNode(p.toString());
            root.add(packageArr);

            for (MyFiles itFl : p.getFiles()) {
                filesArr = new DefaultMutableTreeNode(itFl.toString());
                packageArr.add(filesArr);
            }

            for (Classes itCl : p.getClasses()) {
                classesArr = new DefaultMutableTreeNode(itCl.toString());
                packageArr.add(classesArr);

                for (Methods itMet : itCl.getMethod()) {
                    valuesArr = new DefaultMutableTreeNode(itMet.toString());
                    classesArr.add(valuesArr);
                }
                for (Values itVel : itCl.getValue()) {
                    methodsArr = new DefaultMutableTreeNode(itVel.toString());
                    classesArr.add(methodsArr);
                }
            }
        }

        tree.setModel((new JTree(root)).getModel());
    }

    public String worNormalName(String path) {
        String st[] = path.split("\\\\");
        return st[st.length - 1];
    }

}
