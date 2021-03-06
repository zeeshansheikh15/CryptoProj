package finalproject;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageHiding extends JFrame implements ActionListener
{
 BufferedImage hostImage;
 BufferedImage hostImage2;
 BufferedImage secretImage;

 JPanel controlPanel;
 JPanel imagePanel;


 JButton chosehost1;
 JButton chosehost2;
 JButton choosesecret;
 JButton encrypt;
 JButton decrypt;
JFileChooser fc;

BufferedImage img = null;

 ImageCanvas hostCanvas;
 ImageCanvas hostCanvas2;
 ImageCanvas secretCanvas;

 

 public void actionPerformed(ActionEvent event)
 {
  Object source = event.getSource();

  if (source == chosehost1)
  {
    fc = new JFileChooser();
   // fc.setCurrentDirectory(new java.io.File("C:\\Users\\zeesh\\Documents\\NetBeansProjects\\finalproject"));
    fc.setDialogTitle("cggfh");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & GIF Images", "jpg", "gif", "png");
    fc.setFileFilter(filter);
    if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
    {
        try {
            hostImage = ImageIO.read(fc.getSelectedFile());
             hostCanvas.setImage(hostImage);
             hostCanvas.repaint();
        } catch (IOException ex) {
            Logger.getLogger(ImageHiding.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    System.out.print(fc.getSelectedFile().getAbsolutePath());
  }
  else if (source == chosehost2)
  {
  fc = new JFileChooser();
 //   fc.setCurrentDirectory(new java.io.File("C:\\Users\\zeesh\\Documents\\NetBeansProjects\\finalproject"));
    fc.setDialogTitle("cggfh");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & GIF Images", "jpg", "gif", "png");
    fc.setFileFilter(filter);
    if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
    {
        try {
            hostImage2 = ImageIO.read(fc.getSelectedFile());
             hostCanvas2.setImage(hostImage2);
             hostCanvas2.repaint();
          
        } catch (IOException ex) {
            Logger.getLogger(ImageHiding.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    System.out.print(fc.getSelectedFile().getAbsolutePath());
  }
  else if (source == choosesecret)
  {
  fc = new JFileChooser();
 //   fc.setCurrentDirectory(new java.io.File("C:\\Users\\zeesh\\Documents\\NetBeansProjects\\finalproject"));
    fc.setDialogTitle("cggfh");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG & GIF Images", "jpg", "gif", "png");
    fc.setFileFilter(filter);
    if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
    {
        try {
            secretImage = ImageIO.read(fc.getSelectedFile());
              secretCanvas.setImage(secretImage);
        secretCanvas.repaint();
        } catch (IOException ex) {
            Logger.getLogger(ImageHiding.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    System.out.print(fc.getSelectedFile().getAbsolutePath());
  }else
      if (source == encrypt)
  {
  
       int encodeBits = 4;
        int encodeByteMask = (int)(Math.pow(2, encodeBits)) - 1 << (8 - encodeBits);
         int encodeMask = (encodeByteMask << 24) | (encodeByteMask << 16) | (encodeByteMask << 8) | encodeByteMask;
          int decodeByteMask = ~(encodeByteMask >>> (8 - encodeBits)) & 0xFF;
            int hostMask = (decodeByteMask << 24) | (decodeByteMask << 16) | (decodeByteMask << 8) | decodeByteMask;
  
   BufferedImage host1;
 BufferedImage host2;
  BufferedImage secret; 
   host1 = hostImage;
   host2 = hostImage2;
   secret = secretImage;
   int[] host1RGB = host1.getRGB(0, 0, host1.getWidth(null), host1.getHeight(null), null, 0, host1.getWidth(null));
  int[] host2RGB = host2.getRGB(0, 0, host2.getWidth(null), host2.getHeight(null), null, 0, host2.getWidth(null));
  int[] secretRGB = secret.getRGB(0, 0, secretImage.getWidth(null), secretImage.getHeight(null), null, 0, secretImage.getWidth(null));
  System.out.println("secret rgb"+Integer.toBinaryString(secretRGB[3]));
  int hostMask2 = hostMask >>> encodeBits;


 for (int j = 0; j < secretRGB.length; j++)
  {
   int encodeDatax = (secretRGB[j] & encodeMask) >>> (8 - encodeBits);
   host1RGB[j] = (host1RGB[j] & hostMask) | (encodeDatax);
  }
host1.setRGB(0, 0, host1.getWidth(null), host1.getHeight(null), host1RGB, 0, host1.getWidth(null));
int[] host11RGB = host1.getRGB(0, 0, host1.getWidth(null), host1.getHeight(null), null, 0, host1.getWidth(null));
System.out.println(" host1 rgb"+Integer.toBinaryString(host11RGB[3]));
for (int j = 0; j < secretRGB.length; j++)
  {
      int encodeDatay = (secretRGB[j] & ~hostMask);
        host2RGB[j] = (host2RGB[j] & hostMask) | (encodeDatay);
  }

host2.setRGB(0, 0, host2.getWidth(null), host2.getHeight(null), host2RGB, 0, host2.getWidth(null));
int[] host22RGB = host2.getRGB(0, 0, host2.getWidth(null), host2.getHeight(null), null, 0, host2.getWidth(null));
System.out.println(" host2 rgb"+Integer.toBinaryString(host22RGB[3]));
try {
    ImageIO.write(host1, "png", new File("enchostmsb.png"));
    ImageIO.write(host2, "png", new File("enchostlsb.png"));
} catch (IOException ex) {
    Logger.getLogger(ImageHiding.class.getName()).log(Level.SEVERE, null, ex);
}
  }
else if (source == decrypt)
  {
  
       int encodeBits = 4;
        int encodeByteMask = (int)(Math.pow(2, encodeBits)) - 1 << (8 - encodeBits);
         int encodeMask = (encodeByteMask << 24) | (encodeByteMask << 16) | (encodeByteMask << 8) | encodeByteMask;
          int decodeByteMask = ~(encodeByteMask >>> (8 - encodeBits)) & 0xFF;
            int hostMask = (decodeByteMask << 24) | (decodeByteMask << 16) | (decodeByteMask << 8) | decodeByteMask;
  
   BufferedImage host1;
 BufferedImage host2;
  BufferedImage secret; 
   host1 = hostImage;
   host2 = hostImage2;
   secret = secretImage;
   int[] host1RGB = host1.getRGB(0, 0, host1.getWidth(null), host1.getHeight(null), null, 0, host1.getWidth(null));
   System.out.println(" host1 rgb"+Integer.toBinaryString(host1RGB[3]));
  int[] host2RGB = host2.getRGB(0, 0, host2.getWidth(null), host2.getHeight(null), null, 0, host2.getWidth(null));
  System.out.println(" host2 rgb"+Integer.toBinaryString(host2RGB[3]));
  int[] secretRGB = secret.getRGB(0, 0, secret.getWidth(null), secret.getHeight(null), null, 0, secret.getWidth(null));

  int hostMask2 = hostMask >>> encodeBits;

 for (int j = 0; j < secretRGB.length; j++)
  {
    int encodeDatax = (host1RGB[j] & ~hostMask) << (8 - encodeBits);
   secretRGB[j] = (secretRGB[j] & hostMask2) | (encodeDatax);
  }
secret.setRGB(0, 0, secret.getWidth(null), secret.getHeight(null), secretRGB, 0, secret.getWidth(null));

for (int j = 0; j < secretRGB.length; j++)
  {
  int encodeDatay = (host2RGB[j] & ~hostMask);
   secretRGB[j] = (secretRGB[j] & hostMask) | (encodeDatay);
  }

secret.setRGB(0, 0, secret.getWidth(null), secret.getHeight(null), secretRGB, 0, secret.getWidth(null));
int[] secretttRGB = secret.getRGB(0, 0, secret.getWidth(null), secret.getHeight(null), null, 0, secret.getWidth(null));
System.out.println(" secretttRGB rgb"+Integer.toBinaryString(secretttRGB[3]));
try {
    ImageIO.write(secret, "png", new File("secdec.png"));
    } catch (IOException ex) {
    Logger.getLogger(ImageHiding.class.getName()).log(Level.SEVERE, null, ex);
}
 }
 }
 

 public ImageHiding()
 {
     try {
         GridBagLayout layout = new GridBagLayout();
         GridBagConstraints gbc = new GridBagConstraints();
         this.setTitle("Image Hiding Demo");
         
         Container container = this.getContentPane();
         
         this.setLayout(layout);
         
         
         
         
         chosehost1 = new JButton("choose host 1");
         chosehost1.addActionListener(this);
         
         
         chosehost2 = new JButton("choose host 2");
         chosehost2.addActionListener(this);
         
         choosesecret = new JButton("choose secret image");
         choosesecret.addActionListener(this);
         
         encrypt = new JButton("encrypt");
         encrypt.addActionListener(this);
         
         decrypt = new JButton("decrypt");
         decrypt.addActionListener(this);
         
         gbc.weightx = 1.0;
         layout.setConstraints(chosehost1, gbc);
         this.add(chosehost1);
         
         gbc.weightx = 1.0;
         layout.setConstraints(chosehost2, gbc);
         this.add(chosehost2);
         
         gbc.weightx = 1.0;
         layout.setConstraints(choosesecret, gbc);
         this.add(choosesecret);
         
         
         gbc.weightx = 2.0;
         layout.setConstraints(encrypt, gbc);
         this.add(encrypt);
         
         gbc.weightx = 2.0;
         layout.setConstraints(decrypt, gbc);
         this.add(decrypt);
         
         GridBagLayout imageGridbag = new GridBagLayout();
         GridBagConstraints imageGBC = new GridBagConstraints();
         
         imagePanel = new JPanel();
         imagePanel.setLayout(imageGridbag);
         
         JLabel hostImageLabel = new JLabel("Host image:");
         JLabel hostImageLabel2 = new JLabel("Host image2:");
         JLabel secretImageLabel = new JLabel("Secret image:");
         
         imagePanel.add(hostImageLabel);
         imagePanel.add(hostImageLabel2);
         
         imageGBC.gridwidth = GridBagConstraints.REMAINDER;
         imageGridbag.setConstraints(secretImageLabel, imageGBC);
         imagePanel.add(secretImageLabel);
         img = ImageIO.read(new File("host_image2.png"));
         hostCanvas = new ImageCanvas(img);
         hostCanvas2 = new ImageCanvas(img);
         secretCanvas = new ImageCanvas(img);
         
         imagePanel.add(hostCanvas);
         imagePanel.add(hostCanvas2);
         imagePanel.add(secretCanvas);
         
         gbc.gridwidth = GridBagConstraints.REMAINDER;
         layout.setConstraints(imagePanel, gbc);
         this.add(imagePanel);
         
         
         
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.pack();
         
         this.setVisible(true);
     } catch (IOException ex) {
         Logger.getLogger(ImageHiding.class.getName()).log(Level.SEVERE, null, ex);
     }
 }

 public static void main(String[] args)
 {
  ImageHiding frame = new ImageHiding();
  frame.setVisible(true);
 }

 public class ImageCanvas extends JPanel
 { 
  Image img;

  public void paintComponent(Graphics g)
  {
   g.drawImage(img, 0, 0, this);
  }

  public void setImage(Image img)
  {
   this.img = img;
  }

  public ImageCanvas(Image img)
  {
   this.img = img;

   this.setPreferredSize(new Dimension(img.getWidth(this), img.getHeight(this)));
  }
 }
}


