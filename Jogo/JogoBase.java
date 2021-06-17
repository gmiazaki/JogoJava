import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

class JogoBase extends JFrame  {
  Image img;
  Image carro1;

  int  x1 =  0; // Pos Primeiro carro
  int  y2 = 0; // Pos Segundo carro
int aux =0;
  Desenho des = new Desenho();
 Animacao animacao = new Animacao();

  class Desenho extends JPanel {

    public boolean up;
    public double speed = 0.1;

    Desenho() {
      try {
       setPreferredSize(new Dimension(1280, 600));
       setResizable(false);
        img = ImageIO.read(new File("pista.png"));
        carro1 = ImageIO.read(new File("carro1.png"));
    
      } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "A imagem não pode ser carregada!\n" + e, "Erro",
            JOptionPane.ERROR_MESSAGE);
        System.exit(1);
      }
    }

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(img, 0, 300, 1280, 100, this);
      g.drawImage(carro1, x1, 300, 25, 25, this);
      Toolkit.getDefaultToolkit().sync();
      
    }

 
    

    
  }
 class Animacao  implements KeyListener {
  double mult =1;
  
    @Override
    public  void keyPressed(KeyEvent e){

      new Thread(){
        @Override
        public void run() {

          if (e.getKeyCode() == KeyEvent.VK_X){
            velocidade(); 
                 des.repaint();
               }
              
        }

      }.start();

      if (e.isShiftDown()){
      mult += 5;
     

    }
   
  }
  
  
    
    @Override
    public void keyReleased(KeyEvent e){
      
      if (e.getKeyCode() == KeyEvent.VK_X)
      pontadesc();
    }

    @Override
    public void keyTyped(KeyEvent e){
    
    }

    public void dorme(){
      try {
        Thread.sleep(50);
      }catch (InterruptedException e){
        e.printStackTrace();
      }
    }
    public void velocidade (){ 
    if(mult < 2){
      mult += 1;
    }  
    if (x1 < 1280){
      x1 += 1 + mult;
      System.out.print(x1);
    }
  }
    
    public void pontadesc (){
      new Thread(){
        @Override
        public void run (){
         while(mult > 0){
            mult-= 1;
            if (x1 < 1280){
              x1 += 1 + mult;
              
            }
          des.repaint();
          dorme();
        }
            
          }



        
      
      }.start();

    }    
    
 
}
    
   
  
   




  JogoBase() {
    super("Trabalho");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    add(des);
    pack();
    setVisible(true);
    addKeyListener(animacao);
 
    
  }

    
    
    
    


   

  

  static public void main(String[] args) {
    JogoBase f = new JogoBase();
  
 
  }

}
