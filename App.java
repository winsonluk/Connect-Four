import javax.swing.*;
public class App
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable(){
            public void run()
            {
                JFrame frame = new mainFrame("ConnectFour");
                frame.setSize(1310, 820);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }            
}
