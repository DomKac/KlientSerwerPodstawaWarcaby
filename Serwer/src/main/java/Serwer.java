import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.Executors;

//uruchomienie serwera dla wybranej liczby osób działa

/**
 * klasa główna serwera
 */
public class Serwer {

    ArrayList<Game.Player> players;
    Game.Player player1, player2, player3, player4, player5, player6;

    /**
     * Funkcja do startowania wątków graczy i dolączania ich klientów do serwera
     * @param how_many_players ilość graczy
     * @throws IOException nic
     */
    public Serwer (int how_many_players) throws IOException {

        players = new ArrayList<>();

        try (var listener = new ServerSocket(58901))
        {
            System.out.println("Warcaby Server for " + how_many_players + " osob is Running...");
            var pool = Executors.newFixedThreadPool(how_many_players);

            while (true)
            {
                Game game = new Game();


                switch (how_many_players) {
                    case 2 -> {
                        player1 = game.new Player(listener.accept(), 1, 0);
                        pool.execute(player1);
                        players.add(player1);
                        game.setPlayer(players.get(0));
                        game.setCountOfPlayers(how_many_players);
                        player2 = game.new Player(listener.accept(), 2, 0);
                        pool.execute(player2);
                        players.add(player2);
                        game.setPlayer(players.get(1));
                        game.setCountOfPlayers(how_many_players);
                        game.setTabOfPlayers(players);
                    }
                    case 3 -> {
                        player1 = game.new Player(listener.accept(), 1, 0);
                        pool.execute(player1);
                        players.add(player1);
                        game.setPlayer(players.get(0));
                        game.setCountOfPlayers(how_many_players);
                        player2 = game.new Player(listener.accept(), 2, 0);
                        pool.execute(player2);
                        players.add(player2);
                        game.setPlayer(players.get(1));
                        game.setCountOfPlayers(how_many_players);
                        player3 = game.new Player(listener.accept(), 3, 0);
                        pool.execute(player3);
                        players.add(player3);
                        game.setPlayer(players.get(2));
                        game.setCountOfPlayers(how_many_players);
                        game.setTabOfPlayers(players);
                    }
                    case 4 -> {
                        player1 = game.new Player(listener.accept(), 1, 0);
                        pool.execute(player1);
                        players.add(player1);
                        game.setPlayer(players.get(0));
                        game.setCountOfPlayers(how_many_players);
                        player2 = game.new Player(listener.accept(), 2, 0);
                        pool.execute(player2);
                        players.add(player2);
                        game.setPlayer(players.get(1));
                        game.setCountOfPlayers(how_many_players);
                        player3 = game.new Player(listener.accept(), 3, 0);
                        pool.execute(player3);
                        players.add(player3);
                        game.setPlayer(players.get(2));
                        game.setCountOfPlayers(how_many_players);
                        player4 = game.new Player(listener.accept(), 4, 0);
                        pool.execute(player4);
                        players.add(player4);
                        game.setPlayer(players.get(3));
                        game.setCountOfPlayers(how_many_players);
                        game.setTabOfPlayers(players);
                    }
                    case 6 -> {
                        player1 = game.new Player(listener.accept(), 1, 0);
                        pool.execute(player1);
                        players.add(player1);
                        game.setPlayer(players.get(0));
                        game.setCountOfPlayers(how_many_players);
                        player2 = game.new Player(listener.accept(), 2, 0);
                        pool.execute(player2);
                        players.add(player2);
                        game.setPlayer(players.get(1));
                        game.setCountOfPlayers(how_many_players);
                        player3 = game.new Player(listener.accept(), 3, 0);
                        pool.execute(player3);
                        players.add(player3);
                        game.setPlayer(players.get(2));
                        game.setCountOfPlayers(how_many_players);
                        player4 = game.new Player(listener.accept(), 4, 0);
                        pool.execute(player4);
                        players.add(player4);
                        game.setPlayer(players.get(3));
                        game.setCountOfPlayers(how_many_players);
                        player5 = game.new Player(listener.accept(), 5, 0);
                        pool.execute(player5);
                        players.add(player5);
                        game.setPlayer(players.get(4));
                        game.setCountOfPlayers(how_many_players);
                        player6 = game.new Player(listener.accept(), 6, 0);
                        pool.execute(player6);
                        players.add(player6);
                        game.setPlayer(players.get(5));
                        game.setCountOfPlayers(how_many_players);
                        game.setTabOfPlayers(players);
                    }
                }
            }
        }
    }

    //startowanie serwera

    /**
     * główna funkcja do uruchomienia menu wyboru graczy
     * @param args nic
     * @throws IOException nic
     */
    public static void main(String[] args) throws IOException
    {
        //utworzenie okna
        JFrame frame=new JFrame("Prosze podac ilosc graczy:");
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4,1));
        int[] options ={2,3,4,6};
        //dodanie przyciskow
        for(int i=0;i<options.length;i++)
        {
            final int j=i;
            JButton button=new JButton(String.valueOf(options[i]));
            button.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent actionEvent)
                {
                    try
                    {
                        frame.setVisible(false);
                        //utworzenie serwera dla odpowiedniej liczby graczy
                        System.out.println("startowanie");
                        Serwer server = new Serwer(options[j]);//uruchomienie serwera dla danej liczby graczy
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            frame.getContentPane().add(button);
            frame.setVisible(true);
        }
    }
}
