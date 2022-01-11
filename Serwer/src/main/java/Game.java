import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class Game {

    int numbers; //ilość graczy
    Player player; //gracz
    ArrayList<Player> players; //tablica graczy
    Random random = new Random(); //określenie pierwszego gracza


    /**
     * funkcja do zwracania tablicy z graczami
     * @param players gracze
     */
    public void tab(ArrayList<Player> players){
        this.players = players;
    }

    /**
     * funkcja do zwracania gracza
     * @param player gracz
     */
    public void set(Player player)
    {
        this.player = player;
    }

    /**
     * funkcja do zwracania ilości graczy na podstwie wyboru z menu
     * @param numbers ilość graczy
     */
    public void numOf(int numbers){
        this.numbers = numbers;
    }


    class Player implements Runnable {
        int num; //numer gracza
        Socket socket;
        Scanner input;
        PrintWriter output;
        int win = 0;
        int currentplayer; //czyja jest kolejka
        final MusicPlayer mp3 = new MusicPlayer();

        /**
         * ustawienie portu dla gracza i jego identyfikator
         * @param socket port
         * @param num id gracza
         */
        public Player(Socket socket, int num, int win) {
            this.socket = socket;
            this.num = num;
            this.win = win;
        }



        @Override
        public void run() {
            try {
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
        private void setup() throws IOException {
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("ilość graczuw: " + numbers);
            currentplayer = random.nextInt(numbers)+1;

            output.println(numbers + "WELCOME " + num); //0 i 9

            if (num < numbers) {
                output.println("MESSAGE Waiting for opponent to connect");
            } else {
                System.out.println("The game has started");
                mp3.playSound("b1lowdb.wav"); //muzyka w tle
                System.out.println("po dzwienku");
                System.out.println("Player " + currentplayer + " starts");

                for(int i = 0; i < numbers; i++){
                    System.out.println("mess0");
                    players.get(i).currentplayer = currentplayer;
                    System.out.println("mess1");
                    players.get(i).output.println("TURN" + players.get(i).currentplayer);
                    System.out.println("mess2");
                    players.get(i).output.println("MESSAGE_Player " + players.get(i).currentplayer + " Turn");
                    System.out.println("mess3");
                }
            }
        }

        /**
         * przetwarzanie komend od graczy i wysyłka komend do nich
         */
        private void processCommands(){

            while (input.hasNextLine()){
                String command = input.nextLine();

                if(command.startsWith("MOVE")){//ruch z actionlistenera
                    if(currentplayer == num && num == color_symbol_to_player(command.charAt(command.length()-1))){
                        System.out.println("dostano");

                        for(int i = 0; i < numbers; i++){
                            players.get(i).output.println(command);
                        }
                        przekazywanieKolejki();
                    }
                    else{
                        players.get(num - 1).output.println("NOT");
                    }
                }
                else if (command.startsWith("SKIP")){
                    if(currentplayer == num){
                        System.out.println("dostano");
                        przekazywanieKolejki();
                    }
                    else{
                        players.get(num - 1).output.println("NOT");
                    }
                }

                else if (command.startsWith("WINNER")){//poprawne
                    players.get(Character.getNumericValue(command.charAt(6))-1).win = 1;
                    System.out.println("the winner is" + players.get(Character.getNumericValue(command.charAt(6))-1).num );
                }
            }
        }

        /**
         * funkcja do wysyłania klientom informacji o kolejce graczy
         */
        public void przekazywanieKolejki() {
            if(currentplayer < numbers){
                for (int i = 0; i < numbers; i++){
                    players.get(i).currentplayer++;
                    players.get(i).output.println("TURN" + (players.get(i).currentplayer));
                }
            }
            else{
                for (int i = 0; i < numbers; i++){
                    players.get(i).currentplayer = 1;
                    players.get(i).output.println("TURN" + (players.get(i).currentplayer));
                }
            }
        }

////////////////////////////////////////////////////////////////////////////koniec


        /**
         * funkcja na podstawie oznaczenia koloru gracza zwraca wartość numeryczną tego koloru
         * @param color_symbol alias literowy koloru
         * @return alias numeryczny koloru
         */
        public int color_symbol_to_player(char color_symbol){

            switch (numbers){

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
