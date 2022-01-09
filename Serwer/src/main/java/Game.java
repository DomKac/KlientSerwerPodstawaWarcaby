import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Game {

    int number_of_players, numbers;
    Player player;
    ArrayList<Player> players;

    public void tab(ArrayList<Player> players){
        this.players = players;
    }


    public void set(Player player)
    {
        this.player = player;
    }

    public void numOf(int numbers){
        this.numbers = numbers;
    }

    class Player implements Runnable {
        int num; //numer gracza
        Player opponent;
        Socket socket;
        Scanner input;
        PrintWriter output;
        int currentplayer;

        public Player(Socket socket, int num) {
            this.socket = socket;
            this.num = num;
        }

        @Override
        public void run() {
            try {
                setup();
                //processCommands();
            } catch (Exception e) {
                e.printStackTrace();
            }// finally {
            //    if (opponent != null && opponent.output != null) {
            //        opponent.output.println("OTHER_PLAYER_LEFT");
            //    }
            //    try {
            //        socket.close();
            //    } catch (IOException e) {
            //    }
            //}
        }

        private void setup() throws IOException {
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("ilość graczuw: " + numbers);

            output.println(numbers + "WELCOME " + num); //0 i 9

            if (num < numbers) {
                output.println("MESSAGE Waiting for opponent to connect");
            } else {
                currentplayer = 1;
                System.out.println("The game has started");
                System.out.println("Player " + currentplayer + " starts");

                for(int i = 0; i < numbers; i++){
                    System.out.println("mess0");
                    players.get(i).currentplayer = currentplayer;
                    System.out.println("mess1");
                    players.get(i).output.println("TURN " + players.get(i).currentplayer);
                    System.out.println("mess2");
                    players.get(i).output.println("MESSAGE Player " + players.get(i).currentplayer + " Turn");
                    System.out.println("mess3");
                }
            }
        }

        /*
        private void processComands(){

            while (input.hasNextLine()){
                String command = input.nextLine();

                if(command.startsWith("MOVE")){
                    //wykonaj ruch xd
                }
                else if (command.startsWith("SKIP")){
                    //skip
                }

            }
        }
        */
    }
}
