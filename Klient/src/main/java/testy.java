import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class testy {

    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public testy(String serverAddress) throws Exception {

        socket = new Socket(serverAddress, 58901);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);

    }

    public void play() throws Exception {
        try {
            var response = in.nextLine();
            var num = response.charAt(9);
            var ilosc = Character.getNumericValue(response.charAt(0));
            System.out.println("Witaj graczu o numerze: " + num);
            System.out.println("ilość graczy wynosi: " + ilosc);

           //switch (ilosc){
           //    case 2:
           //        players.add
           //}

            while (in.hasNextLine()) {
                response = in.nextLine();

            }
            out.println("QUIT");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("koniec");
            socket.close();
            //frame.dispose();
        }
    }

  //static class Square extends JPanel {
  //    JLabel label = new JLabel();

  //    public Square() {
  //        setBackground(Color.white);
  //        setLayout(new GridBagLayout());
  //        label.setFont(new Font("Arial", Font.BOLD, 40));
  //        add(label);
  //    }

  //    public void setText(char text) {
  //        label.setForeground(text == 'X' ? Color.BLUE : Color.RED);
  //        label.setText(text + "");
  //    }
  //}

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Pass the server IP as the sole command line argument");
            return;
        }
        testy client = new testy(args[0]);
        //client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //client.frame.setSize(320, 320);
        //client.frame.setVisible(true);
        //client.frame.setResizable(false);
        client.play();
    }
}
