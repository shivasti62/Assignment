
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
public class FilePlot extends Frame {
    int width=640;
    int height=480;
    int limit=100; 
    public FilePlot(){
      super("Graphs");
      prepareGUI();
    }

    public static void main(String[] args){
      FilePlot  plot = new FilePlot();  
      plot.setVisible(true);
    }

    private void prepareGUI(){
      setSize(width,height);
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });
       
    }    

    @Override
    public void paint(Graphics g) {
      //Setting up graph
      int x=0,prev_y=0,new_y=0,dy=0,prediction=0,divs=10;
      g.setColor(Color.RED);
      g.translate(50,height-50);
      g.drawLine(-50,0,width,0);   
      g.drawLine(0,height,0,-height+50);
      g.setColor(Color.BLUE);
      g.drawLine(0,-limit,width-50,-limit);
      g.drawString("Size limit",10,-limit-5);
      g.setColor(Color.RED);
      Font font = new Font("Serif", Font.PLAIN, 20);
      g.drawString("Time",200,20);
      g.drawString("Size",-40,-(height-50)/2); 

      //File operations
      File f=new File("a.txt");
      BufferedWriter bw=null;
      FileWriter fw=null;
      
      try{
        fw=new FileWriter("b.txt");
        bw=new BufferedWriter(fw);
      }
      catch(IOException e){}
      //worker loop
      while(true){
        
        try{
           Thread.sleep(10000); //wait 10 seconds
        }
        catch(InterruptedException ie){}
        try{     
          prev_y=new_y;
          new_y=(int)f.length();
        
          bw.write(new_y+"");
          bw.newLine();
          bw.flush();
          g.drawOval(x,-new_y,5,5);
          g.fillOval(x,-new_y,5,5);

          if(x==0){
            x+=divs;continue;
          }
          if(new_y>=limit){ //If size limit is reached
            g.clearRect(400,10,160,50);
            g.drawString("Prediction: Reached",400,20);
          }
          else{
            dy=new_y-prev_y;
            if(dy<=0)prediction=0;
            else {
              prediction=x-divs-(prev_y-limit)*divs/dy;
            }
            if(prediction==0)
            { 
              g.clearRect(400,10,160,50);
              g.drawString("Prediction:Never",400,20);

            }
            else{
          
              g.clearRect(400,10,160,150);
              g.drawString("Prediction: At "+prediction+"th second",400,20);
              g.setColor(Color.BLACK);
              g.drawOval(prediction,0,5,5);       
              g.setColor(Color.RED);
            }
          }
          x+=divs;

          System.out.println("Just plotting!");
        }
        catch(IOException io){System.out.println("IOException");}

        }
    }
  
}
