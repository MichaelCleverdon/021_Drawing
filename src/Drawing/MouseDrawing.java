/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Drawing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 *
 * @author compsci
 */
public class MouseDrawing extends JFrame{
    
    JMenuBar mainMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenu canvasMenu = new JMenu("Canvas");
    JMenu penMenu = new JMenu("Pen");
    JMenuItem newMenuItem = new JMenuItem("New");
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    
    JMenuItem blackCanvas = new JMenuItem("Black");
    JMenuItem blueCanvas = new JMenuItem("Blue");
    JMenuItem yellowCanvas = new JMenuItem("Yellow");
    JMenuItem pinkCanvas = new JMenuItem("Pink");
    JMenuItem whiteCanvas = new JMenuItem("White");
    
    JMenuItem smallPen = new JMenuItem("Small");
    JMenuItem mediumPen = new JMenuItem("Medium");
    JMenuItem largePen = new JMenuItem("Large");
    
    JPanel drawPanel = new JPanel();
    JLabel leftColorLabel = new JLabel();
    JLabel rightColorLabel = new JLabel();
    JPanel colorPanel = new JPanel();
    JLabel[] colorLabel = new JLabel[16];
    Graphics2D g2D;
    double xPrevious, yPrevious;
    Color drawColor, leftColor, rightColor;
    
    
   

public MouseDrawing(){

setTitle("Artistic Creations by Michael March 2018");

setResizable(false);
addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent e)
    {
        exitForm(e);
    }

   
});
getContentPane().setLayout(new GridBagLayout());


//Build Menu
setJMenuBar(mainMenuBar);

fileMenu.setMnemonic('F');
mainMenuBar.add(fileMenu);
mainMenuBar.setBackground(Color.decode("#95ffd6"));
newMenuItem.setBackground(Color.decode("#70e682"));
fileMenu.add(newMenuItem);

fileMenu.addSeparator();
exitMenuItem.setBackground(Color.decode("#fbb8ff"));
fileMenu.add(exitMenuItem);

newMenuItem.addActionListener(new ActionListener(){
   
    @Override
    public void actionPerformed(ActionEvent e) {
        newMenuItemActionPerformed(e);
        
    }

    

   
}); 
/*
exitMenuItem.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
})*/
exitMenuItem.addActionListener(new ActionListener()  {
    @Override
    public void actionPerformed(ActionEvent e) {
        exitMenuItemActionPerformed(e);
    }
 
});
// black, blue, yellow, pink, white
canvasMenu.setMnemonic('C');
mainMenuBar.add(canvasMenu);
blackCanvas.setBackground(Color.decode("#999999"));
canvasMenu.add(blackCanvas);

blackCanvas.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e) {
        blackCanvasActionPerformed(e);
        
    } 
}); 
canvasMenu.addSeparator();
blueCanvas.setBackground(Color.decode("#5555FF"));
canvasMenu.add(blueCanvas);
blueCanvas.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e) {
        blueCanvasActionPerformed(e);
        
    } 

    
}); 
canvasMenu.addSeparator();
yellowCanvas.setBackground(Color.decode("#ffe766"));
canvasMenu.add(yellowCanvas);
yellowCanvas.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e) {
        yellowCanvasActionPerformed(e);
        
    } 

    
}); 
canvasMenu.addSeparator();
pinkCanvas.setBackground(Color.decode("#ffc0cb"));
canvasMenu.add(pinkCanvas);
pinkCanvas.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e) {
        pinkCanvasActionPerformed(e);
        
    } 

    
}); 
canvasMenu.addSeparator();
whiteCanvas.setBackground(Color.decode("#FFFFFF"));
canvasMenu.add(whiteCanvas);
whiteCanvas.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e) {
        whiteCanvasActionPerformed(e);  
    } 
}); 

penMenu.setMnemonic('P');
mainMenuBar.add(penMenu);
//smallPen.setBackground(Color.decode("#70e682"));
penMenu.add(smallPen);
fileMenu.addSeparator();
penMenu.add(mediumPen);
fileMenu.addSeparator();
penMenu.add(largePen);

smallPen.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e) {
        smallPenActionPerformed(e);  
    } 
}); 
mediumPen.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e) {
        mediumPenActionPerformed(e);  
    } 
}); 
largePen.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e) {
        largePenActionPerformed(e);  
    } 
}); 
////////////////////////
//Making Drawing Panel//
////////////////////////
drawPanel.setPreferredSize(new Dimension(1000, 800));
drawPanel.setBackground(Color.black);
GridBagConstraints gbc = new GridBagConstraints();
gbc.gridx = 0;
gbc.gridy = 0;
gbc.gridheight = 2;
gbc.insets = new Insets(10,10,10,10);
getContentPane().add(drawPanel, gbc);
 drawPanel.addMouseListener(new MouseAdapter(){
 public void mousePressed(MouseEvent e){
 drawPanelMousePressed(e);
 }

    

 public void mouseReleased(MouseEvent e){
 drawPanelMouseReleased(e);
 }

    
 });
drawPanel.addMouseMotionListener(new MouseMotionAdapter(){
public void mouseDragged(MouseEvent e){
drawPanelMouseDragged(e);
}

    
});
///////////////////////
//Adding Color Labels//
///////////////////////
leftColorLabel.setPreferredSize(new Dimension(40,40));
leftColorLabel.setOpaque(true);
gbc = new GridBagConstraints();
gbc.gridx = 1;
gbc.gridy = 0;
gbc.anchor = GridBagConstraints.NORTH;
gbc.insets = new Insets(10,5,10,10);
getContentPane().add(leftColorLabel,gbc);

rightColorLabel.setPreferredSize(new Dimension(40,40));
rightColorLabel.setOpaque(true);
gbc = new GridBagConstraints();
gbc.gridx = 2;
gbc.gridy = 0;
gbc.anchor = GridBagConstraints.NORTH;
gbc.insets = new Insets(10,5,10,10);
getContentPane().add(rightColorLabel,gbc);

colorPanel.setPreferredSize( new Dimension(80,320));
colorPanel.setBorder(BorderFactory.createTitledBorder("Colors"));
gbc = new GridBagConstraints();
gbc.gridx = 1;
gbc.gridy = 1;
gbc.gridwidth=2;
gbc.anchor = GridBagConstraints.NORTH;
gbc.insets = new Insets(10,10,10,10);
getContentPane().add(colorPanel,gbc);


colorPanel.setLayout(new GridBagLayout());


int j = 0;

for (int i = 0; i<16; i++){
colorLabel[i] = new JLabel();
colorLabel[i].setPreferredSize(new Dimension(30,30));
colorLabel[i].setOpaque(true);
gbc = new GridBagConstraints();
gbc.gridx=j;
gbc.gridy = i-j*8;
colorPanel.add(colorLabel[i], gbc);
if (i == 7)
        {
        j++;
        }
colorLabel[i].addMouseListener(new MouseAdapter(){
public void mousePressed(MouseEvent e){
colorMousePressed(e);

}

   

});

}
//Light Colors
//Red
colorLabel[0].setBackground(Color.decode("#FF6666"));
//Blue
colorLabel[1].setBackground(Color.decode("#66CCFF"));
//Green
colorLabel[2].setBackground(Color.decode("#00ff66"));
//Purple
colorLabel[3].setBackground(Color.decode("#bd3cc9"));
//Pink
colorLabel[4].setBackground(Color.decode("#FF99CC"));
//Yellow
colorLabel[5].setBackground(Color.decode("#FFFF66"));
//Orange
colorLabel[6].setBackground(Color.decode("#FF8C69"));
//Teal
colorLabel[7].setBackground(Color.decode("#42f4df"));

//Dark Colors
//Red
colorLabel[8].setBackground(Color.decode("#ba1d1d"));
//Blue
colorLabel[9].setBackground(Color.decode("#254baa"));
//Green
colorLabel[10].setBackground(Color.decode("#4f9b45"));
//Purple
colorLabel[11].setBackground(Color.decode("#9933CC"));
//Pink
colorLabel[12].setBackground(Color.decode("#ff54dc"));
//Yellow
colorLabel[13].setBackground(Color.decode("#e8e404"));
//Orange
colorLabel[14].setBackground(Color.decode("#ce8900"));
//Teal
colorLabel[15].setBackground(Color.decode("#268e82"));
leftColor = colorLabel[0].getBackground();
leftColorLabel.setBackground(leftColor);
rightColor = colorLabel[15].getBackground();
rightColorLabel.setBackground(rightColor);

pack();
Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
setBounds((int) (0.5 * (screenSize.width-getWidth())), (int) (0.5 * (screenSize.height-getHeight())), getWidth(), getHeight());

g2D = (Graphics2D) drawPanel.getGraphics();
}

    /**
     *
     * @param args
     */
    public static void main(String ... args){
 new MouseDrawing().setVisible(true);
}

    private void setBounds(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void exitForm(WindowEvent e) {
        g2D.dispose();
        System.exit(0);
    }
    
     private void colorMousePressed(MouseEvent e) {
         Component clickedColor = e.getComponent();
         
         Toolkit.getDefaultToolkit().beep();
         if (e.getButton() == MouseEvent.BUTTON1){
         leftColor = clickedColor.getBackground();
         leftColorLabel.setBackground(leftColor);
         
         }
         else if (e.getButton() == MouseEvent.BUTTON3){
         rightColor = clickedColor.getBackground();
         rightColorLabel.setBackground(rightColor);
         };
    }
    private void newMenuItemActionPerformed(ActionEvent e) {
        int response;
        response = JOptionPane.showConfirmDialog(null, "Are you sure you want to erase the current drawing?","New Drawing Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION){
       g2D.setColor(drawPanel.getBackground());
       g2D.fillRect(0,0,drawPanel.getWidth(),drawPanel.getHeight());
        }
    }
    private void exitMenuItemActionPerformed(ActionEvent e) {
      int response;
      response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the drawing program?", "Exit Program", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
      if (response == JOptionPane.NO_OPTION){
          return;
      }
      
      else{
      exitForm(null);
       }
    }
    
    ////////////////////////
    //Canvas Color Buttons//
    ////////////////////////
    private void blackCanvasActionPerformed(ActionEvent e) {
        drawPanel.setBackground(Color.black);
    }
    private void blueCanvasActionPerformed(ActionEvent e) {
       drawPanel.setBackground(Color.blue);
    }
    private void yellowCanvasActionPerformed(ActionEvent e) {
        drawPanel.setBackground(Color.yellow);
    }
    private void pinkCanvasActionPerformed(ActionEvent e) {
        drawPanel.setBackground(Color.pink);
    }
    private void whiteCanvasActionPerformed(ActionEvent e) {
        drawPanel.setBackground(Color.white);
    }
    
    
    ////////////////////
    //Pen Size Buttons//
    ////////////////////
    private void largePenActionPerformed(ActionEvent e) {
       g2D.setStroke(new BasicStroke(10));
    }
    private void mediumPenActionPerformed(ActionEvent e) {
     g2D.setStroke(new BasicStroke(5));
    }
      private void smallPenActionPerformed(ActionEvent e) {
          g2D.setStroke(new BasicStroke(1));
    }
    
    
    
    
    
    ///////////////////
    //Drawing methods//
    ///////////////////
    private void drawPanelMousePressed(MouseEvent e) {
      if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3){
      xPrevious = e.getX();
      yPrevious = e.getY();
      if(e.getButton() == MouseEvent.BUTTON1){
      drawColor = leftColor;
      }
      else{
      drawColor = rightColor;
        }
      }
      
    }
    private void drawPanelMouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3){
        
   Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, e.getX(), e.getY());
       g2D.setPaint(drawColor);
       g2D.draw(myLine);
        }
    }
    private void drawPanelMouseDragged(MouseEvent e) {
       Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, e.getX(), e.getY());
       g2D.setPaint(drawColor);
       g2D.draw(myLine);
       xPrevious = e.getX();
       yPrevious = e.getY();
       System.out.println("Mouse x,y = " + xPrevious +", "+yPrevious);
    }
}
 

