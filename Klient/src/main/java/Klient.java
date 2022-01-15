import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * klasa dla klienta z podłączeniem do serwera
 */
public class Klient {
//elo
    private final Scanner in;
    private final PrintWriter out;
    public Ramka frame;
    public boolean my_turn = false; //informacja od serwera, czy dany gracz ma teraz swoją turę
    final MusicClient mp3 = new MusicClient();
    boolean one_time_win = true; //dzięki temu komunikat o zwycięztwie pójdzie tylko raz xd

    /**
     * konstruktor klienta
     * @param serverAddress ipv4
     * @throws Exception e
     */
    public Klient(String serverAddress) throws Exception
    {
        Socket socket = new Socket(serverAddress, 58901);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
    }

/////////////////////////////////////////////////////
    public ActionListener choose_your_pawn = new ActionListener() {

        public boolean pawn_clicked = true; // pomaga określić czy trzeba wybrać pionka czy ruszyć pionka
        // true -> kliknięcie pola_planszy wybiera pionka którego chcemy ruszyć
        // false -> kilknięcie pola_planszy stawia wcześniej wybranego piona na wybrane miejsce
        Color pawn_color, original_color;
        int currentX;
        int currentY;
        int previousX;
        int previousY;
        final Enigma enigma = new Enigma();

        @Override
        public void actionPerformed(ActionEvent e) { //obsługa ruchu

            String coordinates = ((JComponent) e.getSource()).getName();
            currentX = frame.game_panel.get_current_X(coordinates);
            currentY = frame.game_panel.get_current_Y(coordinates);

            if(pawn_clicked){


                if(frame.game_panel.playable_boardfields[currentX][currentY].getBackground() == frame.game_panel.color){

                    mp3.playSound("markpiona.wav");
                    pawn_color = frame.game_panel.playable_boardfields[currentX][currentY].getBackground();
                    original_color = pawn_color;
                    frame.game_panel.playable_boardfields[currentX][currentY].setBackground(pawn_color.darker());
                    frame.game_panel.check_ALL(currentX, currentY);
                    previousX = currentX;
                    previousY = currentY;

                    pawn_clicked = false;

                    System.out.println();
                }
                else{
                    System.out.println("Wybierz kolorowe pole ");
                }
            }
            else{
                if(frame.game_panel.playable_boardfields[currentX][currentY].getBackground() == Color.GRAY) {

                    //|| frame.panelGry.pola_planszy[currentX][currentY].getBackground() == Color.WHITE //god mode

                    System.out.println("Teraz nalezy wybrac gdzie sie ruszyc");
                    frame.game_panel.clear_grey();

                    if(my_turn){
                        mp3.playSound("koniecruchu.wav");
                        out.println("MOVE" + previousX + "," + previousY + "," + currentX + "," + currentY + "," + enigma.codePlayerColor(pawn_color));
                        my_turn = false;
                    }

                    pawn_clicked = true;
                }
                else if (previousX == currentX && previousY == currentY){
                    frame.game_panel.clear_grey();
                    pawn_clicked = true;
                }
                else{
                    System.out.println("zle pole");
                }
                frame.game_panel.playable_boardfields[currentX][currentY].setBackground(original_color);

            }
        }
    };

    /**
     * obsługa pominięcia kolejki
     */
    public ActionListener skiper = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(my_turn){
                out.println("SKIP");
                my_turn = false;
            }
        }
    };

    //////////////////////////////////////////

    public void play() throws Exception {
        try {
            var response = in.nextLine();
            var literal_id = response.charAt(9);
            Enigma enigma2 = new Enigma();
            var count_of_clients = Character.getNumericValue(response.charAt(0));
            char literal_color = enigma2.getPlayerId(literal_id, count_of_clients);
            System.out.println("Witaj graczu o numerze: " + literal_id + " i kolorze: " + literal_color);
            System.out.println("ilość graczy wynosi: " + count_of_clients);

            frame = new Ramka(count_of_clients, literal_id, enigma2.getPlayerColor(literal_id, count_of_clients), enigma2.set_desktop_x(literal_id), enigma2.set_desktop_y(literal_id));
            frame.game_panel.add_funcionality_for_fields(choose_your_pawn);
            frame.pass.addActionListener(skiper);
            frame.setVisible(true);

            while (in.hasNextLine()) {
                response = in.nextLine();

                if(response.startsWith("MESSAGE")){
                    System.out.println(response);

                    if(response.charAt(15) == literal_id){
                        my_turn = true;
                    }
                }

                else if(response.startsWith("TURN")){

                    System.out.println("teraz jest tura gracza o numerze: " + response.charAt(4));
                    frame.which_player.setBackground(enigma2.getPlayerColor(response.charAt(4), count_of_clients));
                    if(response.charAt(4) == literal_id){
                        my_turn = true;
                        System.out.println("twoja tura");
                        if(frame.game_panel.check_win_any(literal_color)){
                            System.out.println("KONIEC!");
                            out.println("SKIP");
                        }
                    }
                }
                else if(response.startsWith("MOVE")){ //serwer wysłał wiadomość o ruchu jakiegoś gracza
                    System.out.println(response); //musimy skopiować ten ruch u nas

                    enigma2.resolveMessageAndPerform(response, frame);// oddtworzenie ruchu gracza u nas
                    if(frame.game_panel.check_win_any(literal_color) && one_time_win){
                        System.out.println("KONIEC! WYGRALES");
                        mp3.playSound("epicwin.wav");
                        out.println("WINNER" + literal_id);
                        frame.setVisible(false);
                        one_time_win = false;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("koniec");
            //socket.close();
            //frame.dispose();
        }
    }

    /**
     * uruchomienie klienta
     * @param args ipv4
     * @throws Exception ex
     */
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Podaj IPv4 komputera przy odpalaniu programu");
            return;
        }
        Klient client = new Klient(args[0]);
        client.play();
    }
}
