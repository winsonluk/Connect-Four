import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class mainFrame extends JFrame
{
    public int count;

    /*
     * Takes in current game progress (in 2D array) and returns color and coordinates to place disc (ONLY if space is available)
     */
    public int[] drop(int[][]josh, int buttonNum, int count, int[][]xCoords, int[][]yCoords)
    {
        int[] jim = new int[3];
        for(int i = josh.length - 1; i >= 0; i--)
        {

            if(josh[i][buttonNum - 1] == 0)
            {
                if(count%2 == 1)
              {
                josh[i][buttonNum - 1] = 1; //1 refers to black disc
                jim[0] = 1;
                jim[1] = xCoords[i][buttonNum - 1];
                jim[2] = yCoords[i][buttonNum - 1];

                return jim;
              }
              else
              {
                josh[i][buttonNum - 1] = 2; //2 refers to red disc
                jim[0] = 2;
                jim[1] = xCoords[i][buttonNum - 1];
                jim[2] = yCoords[i][buttonNum - 1];

                return jim;
                }
            }

        }
        int[] jill = {0,0,0};
        return jill;
    }

    public mainFrame(String title)
    {
        super(title); //Title of the window
        setLayout(new BorderLayout());
        JLayeredPane lp = getLayeredPane(); //Implements JLayeredPane lp as the frame

        /*
         * Array of disc values (0 = empty, 1 = black, 2 = red)
         */
        int[][] jack = {{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0},{0,0,0,0,0,0,0}};

        /*
         * x and y coordinates of disc openings
         */
        int[][] xCoords = {{225,352,479,606,733,860,985},
                           {230,357,479,606,731,856,980},
                           {237,357,482,606,730,852,975},
                           {240,360,485,605,725,850,970},
                           {243,362,487,605,725,847,965},
                           {248,365,487,605,724,844,960}};

        int[][] yCoords = {{111,111,111,111,111,111,111},
                           {225,223,222,222,223,223,223},
                           {335,333,334,330,334,332,332},
                           {445,443,440,439,440,438,438},
                           {548,548,545,543,545,542,542},
                           {648,646,645,642,642,641,641}};

        /*
         * Wooden background
         */
        BufferedImage backgroundImage = null;
        try
        {
            backgroundImage = ImageIO.read(new File("c4.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        JLabel background = new JLabel(new ImageIcon(backgroundImage));

        /*
         * Red disc
         */
        BufferedImage redDisc = null;
        try
        {
            redDisc = ImageIO.read(new File("redDisc.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        JLabel redD = new JLabel(new ImageIcon(redDisc));

        /*
         * Black disc
         */
        BufferedImage blackDisc = null;
        try
        {
            blackDisc = ImageIO.read(new File("blackDisc.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        JLabel blackD = new JLabel(new ImageIcon(blackDisc));
        JTextField typingArea = new JTextField(20);
        typingArea.addKeyListener(new KeyListener() {
          public void keyTyped(KeyEvent e)
          {
            magic(e);
          }
          public void keyPressed(KeyEvent e) {}
          public void keyReleased(KeyEvent e) {}
          protected void magic(KeyEvent e)
          {
              char id = e.getKeyChar();
              if(id == 'b' || id == 'B')
              {
                  JOptionPane.showMessageDialog(lp, "Black Wins!");
                  System.out.println("-------------------------");
                  System.out.println("WINNER: BLACK");
                  System.out.println("NUMBER OF TURNS: " + count);
                  System.out.println("1 = BLACK");
                  System.out.println("2 = RED");
                  System.out.println("-------------------------");
                  for (int i = 0; i < jack.length; i++)
                  {
                      for (int j = 0; j < jack[0].length; j++)
                      {
                          System.out.print(jack[i][j] + "   ");
                      }
                      System.out.println("");
                  }
              }
              else if (id == 'r' || id == 'R')
              {
                  JOptionPane.showMessageDialog(lp, "Red Wins!");
                  System.out.println("-------------------------");
                  System.out.println("WINNER: RED");
                  System.out.println("NUMBER OF TURNS: " + count);
                  System.out.println("1 = BLACK");
                  System.out.println("2 = RED");
                  System.out.println("-------------------------");
                  for (int i = 0; i < jack.length; i++)
                  {
                      for (int j = 0; j < jack[0].length; j++)
                      {
                          System.out.print(jack[i][j] + "   ");
                      }
                      System.out.println("");
                  }
              }
          }
        });

        /*
         * Dropper buttons
         */
                            JButton button1 = new JButton();
                            button1.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e)
                                {
                                    count++;
                                    int[] jake = drop(jack, 1, count, xCoords, yCoords);
                                    if (jake[0] == 1)
                                    {
                                        BufferedImage blackDisc = null;
                            try
                            {
                                blackDisc = ImageIO.read(new File("blackDisc.png"));
                            }
                            catch (IOException a)
                            {
                                a.printStackTrace();
                            }
                            JLabel blackD = new JLabel(new ImageIcon(blackDisc));
                            blackD.setBounds(jake[1],jake[2],100,88);
                            lp.add(blackD, new Integer(4));
                            typingArea.requestFocusInWindow();
                                    }

                                    else if (jake[0] == 2)
                                    {
                                        BufferedImage redDisc = null;
                            try
                            {
                                redDisc = ImageIO.read(new File("redDisc.png"));
                            }
                            catch (IOException a)
                            {
                                a.printStackTrace();
                            }
                            JLabel redD = new JLabel(new ImageIcon(redDisc));
                            redD.setBounds(jake[1],jake[2],100,86);
                            lp.add(redD, new Integer(4));
                            typingArea.requestFocusInWindow();
                                    }
                                    else if (jake[0] == 0)
                                    {
                                        count--;
                                        typingArea.requestFocusInWindow();
                                    }
                                }
                            });


                            JButton button2 = new JButton();
                            button2.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e)
                                {
                                   count++;
                                    int[] jake = drop(jack, 2, count, xCoords, yCoords);
                                    if (jake[0] == 1)
                                    {
                                        BufferedImage blackDisc = null;
                            try 
                            {
                                blackDisc = ImageIO.read(new File("blackDisc.png"));
                            } 
                            catch (IOException a) 
                            {
                                a.printStackTrace();
                            }
                            JLabel blackD = new JLabel(new ImageIcon(blackDisc));
                            blackD.setBounds(jake[1],jake[2],100,88);
                            lp.add(blackD, new Integer(4));
                            typingArea.requestFocusInWindow();
                                    }

                                    else if (jake[0] == 2)
                                    {
                                        BufferedImage redDisc = null;
                            try
                            {
                                redDisc = ImageIO.read(new File("redDisc.png"));
                            }
                            catch (IOException a)
                            {
                                a.printStackTrace();
                            }
                            JLabel redD = new JLabel(new ImageIcon(redDisc));
                            redD.setBounds(jake[1],jake[2],100,86);
                            lp.add(redD, new Integer(4));
                            typingArea.requestFocusInWindow();
                                    }
                                    else if (jake[0] == 0)
                                    {
                                        count--;
                                        typingArea.requestFocusInWindow();
                                    }
                                }
                            });


                            JButton button3 = new JButton();
                            button3.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e)
                                {
                                    count++;
                                    int[] jake = drop(jack, 3, count, xCoords, yCoords);
                                    if (jake[0] == 1)
                                    {
                                        BufferedImage blackDisc = null;
                            try
                            {
                                blackDisc = ImageIO.read(new File("blackDisc.png"));
                            }
                            catch (IOException a)
                            {
                                a.printStackTrace();
                            }
                            JLabel blackD = new JLabel(new ImageIcon(blackDisc));
                            blackD.setBounds(jake[1],jake[2],100,88);
                            lp.add(blackD, new Integer(4));
                            typingArea.requestFocusInWindow();
                                    }

                                    else if (jake[0] == 2)
                                    {
                                        BufferedImage redDisc = null;
                            try
                            {
                                redDisc = ImageIO.read(new File("redDisc.png"));
                            }
                            catch (IOException a)
                            {
                                a.printStackTrace();
                            }
                            JLabel redD = new JLabel(new ImageIcon(redDisc));
                            redD.setBounds(jake[1],jake[2],100,86);
                            lp.add(redD, new Integer(4));
                            typingArea.requestFocusInWindow();
                                    }
                                    else if (jake[0] == 0)
                                    {
                                        count--;
                                        typingArea.requestFocusInWindow();
                                    }
                                }
                            });


                            JButton button4 = new JButton();
                            button4.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e)
                                {
                                    count++;
                                    int[] jake = drop(jack, 4, count, xCoords, yCoords);
                                    if (jake[0] == 1)
                                    {
                                        BufferedImage blackDisc = null;
                            try
                            {
                                blackDisc = ImageIO.read(new File("blackDisc.png"));
                            }
                            catch (IOException a)
                            {
                                a.printStackTrace();
                            }
                            JLabel blackD = new JLabel(new ImageIcon(blackDisc));
                            blackD.setBounds(jake[1],jake[2],100,88);
                            lp.add(blackD, new Integer(4));
                            typingArea.requestFocusInWindow();
                                    }

                                    else if (jake[0] == 2)
                                    {
                                        BufferedImage redDisc = null;
                            try
                            {
                                redDisc = ImageIO.read(new File("redDisc.png"));
                            }
                            catch (IOException a)
                            {
                                a.printStackTrace();
                            }
                            JLabel redD = new JLabel(new ImageIcon(redDisc));
                            redD.setBounds(jake[1],jake[2],100,86);
                            lp.add(redD, new Integer(4));
                            typingArea.requestFocusInWindow();
                                    }
                                    else if (jake[0] == 0)
                                    {
                                        count--;
                                        typingArea.requestFocusInWindow();
                                    }
                                }
                            });


                            JButton button5 = new JButton();
                            button5.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e)
                                {
                                  count++;
                                    int[] jake = drop(jack, 5, count, xCoords, yCoords);
                                    if (jake[0] == 1)
                                    {
                                        BufferedImage blackDisc = null;
                            try
                            {
                                blackDisc = ImageIO.read(new File("blackDisc.png"));
                            }
                            catch (IOException a)
                            {
                                a.printStackTrace();
                            }
                            JLabel blackD = new JLabel(new ImageIcon(blackDisc));
                            blackD.setBounds(jake[1],jake[2],100,88);
                            lp.add(blackD, new Integer(4));
                            typingArea.requestFocusInWindow();
                                    }

                                    else if (jake[0] == 2)
                                    {
                                        BufferedImage redDisc = null;
                            try
                            {
                                redDisc = ImageIO.read(new File("redDisc.png"));
                            }
                            catch (IOException a)
                            {
                                a.printStackTrace();
                            }
                            JLabel redD = new JLabel(new ImageIcon(redDisc));
                            redD.setBounds(jake[1],jake[2],100,86);
                            lp.add(redD, new Integer(4));
                            typingArea.requestFocusInWindow();
                                    }
                                    else if (jake[0] == 0)
                                    {
                                        count--;
                                        typingArea.requestFocusInWindow();
                                    }
                                }
                            });


                            JButton button6 = new JButton();
                            button6.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e)
                                {
                                  count++;
                                    int[] jake = drop(jack, 6, count, xCoords, yCoords);
                                    if (jake[0] == 1)
                                    {
                                        BufferedImage blackDisc = null;
                            try
                            {
                                blackDisc = ImageIO.read(new File("blackDisc.png"));
                            }
                            catch (IOException a)
                            {
                                a.printStackTrace();
                            }
                            JLabel blackD = new JLabel(new ImageIcon(blackDisc));
                            blackD.setBounds(jake[1],jake[2],100,88);
                            lp.add(blackD, new Integer(4));
                            typingArea.requestFocusInWindow();
                                    }

                                    else if (jake[0] == 2)
                                    {
                                        BufferedImage redDisc = null;
                            try
                            {
                                redDisc = ImageIO.read(new File("redDisc.png"));
                            }
                            catch (IOException a)
                            {
                                a.printStackTrace();
                            }
                            JLabel redD = new JLabel(new ImageIcon(redDisc));
                            redD.setBounds(jake[1],jake[2],100,86);
                            lp.add(redD, new Integer(4));
                            typingArea.requestFocusInWindow();
                                    }
                                    else if (jake[0] == 0)
                                    {
                                        count--;
                                        typingArea.requestFocusInWindow();
                                    }
                                }
                            });


                            JButton button7 = new JButton();
                            button7.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e)
                                {
                                    count++;
                                    int[] jake = drop(jack, 7, count, xCoords, yCoords);
                                    if (jake[0] == 1)
                                    {
                                        BufferedImage blackDisc = null;
                            try
                            {
                                blackDisc = ImageIO.read(new File("blackDisc.png"));
                            }
                            catch (IOException a)
                            {
                                a.printStackTrace();
                            }
                            JLabel blackD = new JLabel(new ImageIcon(blackDisc));
                            blackD.setBounds(jake[1],jake[2],100,88);
                            lp.add(blackD, new Integer(4));
                            typingArea.requestFocusInWindow();
                                    }

                                    else if (jake[0] == 2)
                                    {
                                        BufferedImage redDisc = null;
                            try
                            {
                                redDisc = ImageIO.read(new File("redDisc.png"));
                            }
                            catch (IOException a)
                            {
                                a.printStackTrace();
                            }
                            JLabel redD = new JLabel(new ImageIcon(redDisc));
                            redD.setBounds(jake[1],jake[2],100,86);
                            lp.add(redD, new Integer(4));
                            typingArea.requestFocusInWindow();
                                    }
                                    else if (jake[0] == 0)
                                    {
                                        count--;
                                        typingArea.requestFocusInWindow();
                                    }
                                }

                            });


        /*
         * Make dropper buttons wooden
         */
        BufferedImage buttonImage = null;
        try
        {
            buttonImage = ImageIO.read(new File("wood.png"));
            button1.setIcon(new ImageIcon(buttonImage));
            button2.setIcon(new ImageIcon(buttonImage));
            button3.setIcon(new ImageIcon(buttonImage));
            button4.setIcon(new ImageIcon(buttonImage));
            button5.setIcon(new ImageIcon(buttonImage));
            button6.setIcon(new ImageIcon(buttonImage));
            button7.setIcon(new ImageIcon(buttonImage));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        /*
         * ConnectFour board (transparent)
         */
        BufferedImage coverImage = null;
        try
        {
            backgroundImage = ImageIO.read(new File("cover.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        JLabel cover = new JLabel(new ImageIcon(backgroundImage));

        /*
         * Pepe
         */
        BufferedImage pepeImage = null;
        try
        {
            pepeImage = ImageIO.read(new File("pepe.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        JLabel pepe = new JLabel(new ImageIcon(pepeImage));


        /*
         * Add background, buttons, and board (respectively, in layered order)
         */
        background.setBounds(0,0,1310,800);
        lp.add(background, new Integer(1));
        button1.setBounds(240,10,70,40);
        lp.add(button1, new Integer(2));
        button2.setBounds(365,10,70,40);
        lp.add(button2, new Integer(2));
        button3.setBounds(490,10,70,40);
        lp.add(button3, new Integer(2));
        button4.setBounds(615,10,70,40);
        lp.add(button4, new Integer(2));
        button5.setBounds(740,10,70,40);
        lp.add(button5, new Integer(2));
        button6.setBounds(865,10,70,40);
        lp.add(button6, new Integer(2));
        button7.setBounds(990,10,70,40);
        lp.add(button7, new Integer(2));
        cover.setBounds(0,32,1310,800);
        lp.add(cover, new Integer(3));
        lp.add(typingArea);

        pepe.setBounds(450,100,408,660);
        //lp.add(pepe, new Integer(2));
    }
}
