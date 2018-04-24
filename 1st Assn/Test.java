import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Test {    //main class of the program

    private Frame myFrame;
    private Label topLabel;
    private Label bottomLabel1;
    private Label bottomlabel2;
    private Label errorMessage;
    private Label message;
    private Panel panel1;
    private Panel panel2;


    public Test(){   //constructor of the test class
        makeGui();
    }

    public void makeGui(){    //definition of GUI function

        myFrame = new Frame("Assn");
        myFrame.setBackground(Color.CYAN);
        myFrame.setSize(600,500);
        myFrame.setLayout(new GridLayout(8,1));

        myFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){ //closes the window on pressing the cross button
                System.exit(0);
            }
        });

        topLabel = new Label();
        topLabel.setAlignment(Label.CENTER);
        message = new Label();
        message.setAlignment(Label.CENTER);
        errorMessage = new Label();
        errorMessage.setAlignment(Label.CENTER);
        bottomLabel1 = new Label();
        bottomLabel1.setAlignment(Label.CENTER);
        bottomlabel2 = new Label();
        bottomlabel2.setAlignment(Label.CENTER);

        panel1 = new Panel();
        panel1.setLayout(new FlowLayout());
        panel2 = new Panel();
        panel2.setLayout(new FlowLayout());

        myFrame.add(topLabel);
        myFrame.add(panel1);
        myFrame.add(bottomLabel1);
        myFrame.add(bottomlabel2);
        myFrame.add(message);
        myFrame.add(errorMessage);
        myFrame.add(panel2);
        myFrame.setVisible(true);
        topLabel.setText("click to browse files");
    }

    public static String checkOS() {  //check for the OS type
        String store = "";
        String OS = System.getProperty("os.name").toLowerCase();
        if(OS.indexOf("win") >= 0){
            store = "/root/Desktop/";
        } else if(OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 ){
            store = "/home/";
        } else  if(OS.indexOf("mac") >= 0){
            store = "/home/";
        } else{
            return null;
        }
        return store;
    }
    public static String getExtension(String fileName){  //return the filename selected by the user

                    String extension = "";
                int i = fileName.lastIndexOf('.');
                if (i > 0) {
                    extension = fileName.substring(i+1);// to get part of string substring() is used. Here (i+1) is the begining index of the substring.
                }
                return extension;
    }
    private void action() {

        Button button1 = new Button("BROWSE");  //creation of button1
        myFrame.add(button1);
        panel1.add(button1);
        Button button2 = new Button("SAVED FILES");
        myFrame.add(button2);
        panel2.add(button2);

        FileDialog fileDialog = new FileDialog(myFrame);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fileDialog.setVisible(true);

                //storing path of selected file in fileName
                String fileName = fileDialog.getDirectory() + fileDialog.getFile();

                //splitting of files
                try {
                              split(fileName);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
