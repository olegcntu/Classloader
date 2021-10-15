package tree;

import classLoad.Lab1;
import obj.*;
import obj.Package;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainManu extends JFrame {
    public static MainManu frame;
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

    public MainManu(String title) {
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
                Lab1 l = new Lab1();
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
                DefaultMutableTreeNode sn=(DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent();
                textArea1.setText(sn.getUserObject().toString());
            }
        });
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Thread thread = new Thread(){

            @Override
            public void run(){
                frame = new MainManu("Classloader");
                frame.setVisible(true);
            }
        } ;
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

        DefaultMutableTreeNode packeArr = null;
        DefaultMutableTreeNode filesesArr = null;
        DefaultMutableTreeNode classesArr = null;
        DefaultMutableTreeNode valuesArr = null;
        DefaultMutableTreeNode mhetodsArr = null;


        for (Package p : pack) {
            packeArr = new DefaultMutableTreeNode(p.toString());
            root.add(packeArr);

            for (MyFiles itFl : p.getFiles()) {
                filesesArr = new DefaultMutableTreeNode(itFl.toString());
                packeArr.add(filesesArr);
            }

            for (Classes itCl : p.getClases()) {
                classesArr = new DefaultMutableTreeNode(itCl.toString());
                packeArr.add(classesArr);

                for (Mhetods itMet : itCl.getMhetod()) {
                    valuesArr = new DefaultMutableTreeNode(itMet.toString());
                    classesArr.add(valuesArr);
                }
                for (Values itVel : itCl.getValue()) {
                    mhetodsArr = new DefaultMutableTreeNode(itVel.toString());
                    classesArr.add(mhetodsArr);
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
