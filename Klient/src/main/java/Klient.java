import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.io.PrintWriter;
import java.net.Socket;

public class Klient {

    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    public Ramka frame;




/////////////////////////////////////////////////////
    public ActionListener wyb_pionek = new ActionListener() {

        public boolean wybrano_piona = true; // pomaga określić czy trzeba wybrać pionka czy ruszyć pionka
        // true -> kliknięcie pola_planszy wybiera pionka którego chcemy ruszyć
        // false -> kilknięcie pola_planszy stawia wcześniej wybranego piona na wybrane miejsce

        Color kolor_piona;
        int currentX;
        int currentY;
        int previousX;
        int previousY;
        final Enigma enigma = new Enigma();


        @Override
        public void actionPerformed(ActionEvent e) {

            String coordinates = ((JComponent) e.getSource()).getName();
            System.out.println(coordinates);
            currentX = frame.get_current_X(coordinates);
            currentY = frame.get_current_Y(coordinates);


            if(wybrano_piona){


                if(frame.pola_planszy[currentX][currentY].getBackground() != Color.WHITE){



                    kolor_piona = frame.pola_planszy[currentX][currentY].getBackground();
                    frame.check_ALL(currentX, currentY);
                    previousX = currentX;
                    previousY = currentY;

                    wybrano_piona = false;

                    System.out.println();
                }
                else{
                    System.out.println("Wybierz kolorowe pole ");

                }
            }
            else{
                if(frame.pola_planszy[currentX][currentY].getBackground() == Color.GRAY) {
                    System.out.println("Teraz nalezy wybrac gdzie sie ruszyc");
                    frame.pola_planszy[currentX][currentY].setBackground(kolor_piona);
                    frame.pola_planszy[previousX][previousY].setBackground(Color.WHITE);
                    frame.clear_grey();

                    out.println("MOVE" + previousX + "," + previousY + "," + currentX + "," + currentY + "," + enigma.koduj_kolor(kolor_piona));
                    //klient.mover(previousX, previousY, currentX, currentY, kolor_piona);
                    //sender(previousX, previousY, currentX, currentY, kolor_piona);


                    if(frame.check_ENDGAME()){
                        System.out.println("KONIEC!");
                        System.exit(0);
                    }
                    wybrano_piona = true;
                }
                else if (previousX == currentX && previousY == currentY){
                    frame.clear_grey();
                    wybrano_piona = true;
                }
                else{
                    System.out.println("zle pole debilu");
                }
            }
        }
    };


    //////////////////////////////////////////
    public Klient(String serverAddress) throws Exception {

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
            frame = new Ramka(ilosc, num);
            frame.dodaj_wlasciwosci_guzikom(wyb_pionek);
            frame.setVisible(true);
            Enigma enigma2 = new Enigma();


           //switch (ilosc){
           //    case 2:
           //        players.add
           //}

            while (in.hasNextLine()) {
                response = in.nextLine();
                System.out.println("startpetli");

                if(response.startsWith("MESSAGE")){
                    System.out.println(response);

                    out.println("DUPA");//zamiast dupy, wysyła koordynaty


                }
                else if(response.startsWith("MOVE")){ //serwer wysłał wiadomość o ruchu jakiegoś gracza
                    System.out.println(response); //musimy skopiować ten ruch u nas
                    enigma2.koloruj(response, frame);



                }
                else if(response.startsWith("SKIP")) {
                    System.out.println("ajwaj");
                }

            }
            out.println("QUIT");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("koniec");
            //socket.close();
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
        Klient client = new Klient(args[0]);
        //client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //client.frame.setSize(320, 320);
        //client.frame.setVisible(true);
        //client.frame.setResizable(false);

        client.play();

        //client.frame = new Ramka(4 );
        //client.frame.setVisible(true);
    }
}
