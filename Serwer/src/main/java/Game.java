import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * klasa do podpinania klientów
 */
public class Game {

    int number_of_players; //ilość graczy
    Player player; //gracz
    ArrayList<Player> players; //tablica graczy od 0 do 5, więc players.get(0) to player o id 1
    Random random = new Random(); //określenie pierwszego gracza


    /**
     * funkcja do zwracania tablicy z graczami
     * @param players gracze
     */
    public void setTabOfPlayers(ArrayList<Player> players)
    {
        this.players = players;
    }

    /**
     * funkcja do zwracania gracza
     * @param player gracz
     */
    public void setPlayer(Player player)
    {
        this.player = player;
    }

    /**
     * funkcja do zwracania ilości graczy na podstwie wyboru z menu
     * @param number_of_players ilość graczy
     */
    public void setCountOfPlayers(int number_of_players)
    {
        this.number_of_players = number_of_players;
    }


    /**
     * wątek gracza
     */
    public class Player implements Runnable {
        int player_id; //numer gracza od 1 do 6
        Socket socket;
        Scanner input;
        PrintWriter output;


        int win = 0;
        int currentplayer = 1; //czyja jest kolejka
        final MusicPlayer mp3 = new MusicPlayer();

        /**
         * ustawienie portu dla gracza i jego identyfikator
         * @param socket port
         * @param player_id id gracza
         * @param win informacja czy gracz już wygrał
         */
        public Player(Socket socket, int player_id, int win) throws IOException {
            this.socket = socket;
            this.player_id = player_id;
            this.win = win;
        }




        @Override
        public void run() {
            try {
                System.out.println("ilosc graczy: " + number_of_players);
                setup();
                processCommands();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * funkcja startowa do ustalenia gracza początkowego
         * @throws IOException nic
         */
        public void setup() throws IOException {

            currentplayer = random.nextInt(number_of_players)+1;

            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);

            output.println(number_of_players + "WELCOME " + player_id); //0 i 9


            if (player_id < number_of_players) {
                output.println("MESSAGE Waiting for opponent to connect");
            } else {
                System.out.println("The game has started");
                mp3.playSound("b1git.wav"); //muzyka w tle
                System.out.println("Player " + currentplayer + " starts");

                for(int i = 0; i < number_of_players; i++){
                    players.get(i).currentplayer = currentplayer;
                    players.get(i).output.println("TURN" + players.get(i).currentplayer);
                    players.get(i).output.println("MESSAGE_Player " + players.get(i).currentplayer + " Turn");
                }
            }
        }

        /**
         * przetwarzanie komend od graczy i wysyłka komend do nich
         */
        public void processCommands(){

            while (input.hasNextLine()){
                String command = input.nextLine();

                if(command.startsWith("MOVE"))
                {//ruch z actionlistenera
                    if(currentplayer == player_id && player_id == color_symbol_to_player(command.charAt(command.length()-1)))
                    {

                        for(int i = 0; i < number_of_players; i++)
                        {
                            players.get(i).output.println(command);
                        }
                        turnGiver();
                    }
                    else
                    {
                        players.get(player_id - 1).output.println("NOT");
                    }
                }
                else if (command.startsWith("SKIP"))
                {
                    if(currentplayer == player_id)
                    {
                        turnGiver();
                    }
                    else
                    {
                        players.get(player_id - 1).output.println("NOT");
                    }
                }

                else if (command.startsWith("WINNER"))
                {//poprawne
                    players.get(Character.getNumericValue(command.charAt(6))-1).win = 1; //serwer ustawia graczowi parametr informujący o zwyięstwie
                    System.out.println("the winner is" + players.get(Character.getNumericValue(command.charAt(6))-1).player_id);
                }
            }
        }

        /**
         * funkcja do wysyłania klientom informacji o kolejce graczy
         */
        public void turnGiver()
        {
            if(currentplayer < number_of_players)
            {
                for (int i = 0; i < number_of_players; i++)
                {
                    players.get(i).currentplayer++;
                    players.get(i).output.println("TURN" + (players.get(i).currentplayer));
                }
            }
            else
            {
                for (int i = 0; i < number_of_players; i++)
                {
                    players.get(i).currentplayer = 1;
                    players.get(i).output.println("TURN" + (players.get(i).currentplayer));
                }
            }
        }

        /**
         * funkcja na podstawie oznaczenia koloru gracza zwraca wartość numeryczną tego koloru
         * @param color_symbol alias literowy koloru
         * @return alias numeryczny koloru
         */
        public int color_symbol_to_player(char color_symbol){

            switch (number_of_players){

                case 2: {
                    if(color_symbol == 'P'){
                        return 1;
                    }
                    else{
                        return 2;
                    }
                }
                case 3: {
                    if(color_symbol == 'P'){
                        return 1;
                    }
                    else if (color_symbol == 'Y'){
                        return 2;
                    }
                    else{
                        return 3;
                    }
                }
                case 4: {
                    if(color_symbol == 'O'){
                        return 1;
                    }
                    else if (color_symbol == 'Y'){
                        return 2;
                    }
                    else if (color_symbol == 'R'){
                        return 3;
                    }
                    else {
                        return 4;
                    }
                }
                case 6: {
                    if(color_symbol == 'P'){
                        return 1;
                    }
                    else if (color_symbol == 'O'){
                        return 2;
                    }
                    else if (color_symbol == 'Y'){
                        return 3;
                    }
                    else if (color_symbol == 'B'){
                        return 4;
                    }
                    else if (color_symbol == 'R'){
                        return 5;
                    }
                    else {
                        return 6;
                    }
                }
                default:
                    return 69;
            }
        }
    }
}
