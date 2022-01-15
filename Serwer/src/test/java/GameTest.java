import org.testng.annotations.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void numof_test(){
        Game game = new Game();
        game.setCountOfPlayers(6);
        assertEquals(6,game.number_of_players);
    }

    @Test
    public void przekazywanieKolejki_test() throws IOException {

        Socket socket = new Socket();
        Game game = new Game();
        ArrayList<Game.Player> p = new ArrayList<>();


        Game.Player player1 = game.new Player(socket, 1, 0);
        Game.Player player2 = game.new Player(socket, 2, 0);
        Game.Player player3 = game.new Player(socket, 3, 0);
        Game.Player player4 = game.new Player(socket, 4, 0);
        Game.Player player5 = game.new Player(socket, 5, 0);
        Game.Player player6 = game.new Player(socket, 6, 0);

        p.add(player1);
        p.add(player2);
        p.add(player3);
        p.add(player4);
        p.add(player5);
        p.add(player6);

        game.setTabOfPlayers(p);
        game.setCountOfPlayers(6);

        //public void turnGiver() {
        if (player1.currentplayer < game.number_of_players) {
            for (int i = 0; i < game.number_of_players; i++) {
                game.players.get(i).currentplayer++;
                //game.players.get(i).output.println("TURN" + (game.players.get(i).currentplayer));
            }
        } else {
            for (int i = 0; i < game.number_of_players; i++) {
                game.players.get(i).currentplayer = 1;
                //game.players.get(i).output.println("TURN" + (game.players.get(i).currentplayer));
            }
        }
        //  }

        assertEquals(2,player1.currentplayer);
        assertEquals(2,player2.currentplayer);
        assertEquals(2,player3.currentplayer);
        assertEquals(2,player4.currentplayer);
        assertEquals(2,player5.currentplayer);
        assertEquals(2,player6.currentplayer);

    }

    @Test
    public void color_symbol_to_player_test() throws IOException {

        Game game = new Game();
        Socket socket = new Socket();
        Game.Player player = game.new Player(socket, 1, 0);

        game.setCountOfPlayers(6);
        assertEquals(player.color_symbol_to_player('P'),1);
        assertEquals(player.color_symbol_to_player('O'),2);
        assertEquals(player.color_symbol_to_player('Y'),3);
        assertEquals(player.color_symbol_to_player('B'),4);
        assertEquals(player.color_symbol_to_player('R'),5);
        assertEquals(player.color_symbol_to_player('G'),6);
    }

    @Test
    public void tab_test() throws IOException {
        Game game = new Game();
        ArrayList<Game.Player> p = new ArrayList<>();
        Socket socket = new Socket();

        Game.Player player1 = game.new Player(socket, 1, 0);
        Game.Player player2 = game.new Player(socket, 2, 0);
        Game.Player player3 = game.new Player(socket, 3, 0);
        Game.Player player4 = game.new Player(socket, 4, 0);
        Game.Player player5 = game.new Player(socket, 5, 0);
        Game.Player player6 = game.new Player(socket, 6, 0);

        p.add(player1);
        p.add(player2);
        p.add(player3);
        p.add(player4);
        p.add(player5);
        p.add(player6);

        game.setTabOfPlayers(p);

        assertEquals(game.players,p);

    }

    @Test
    public void set_test() throws IOException {
        Game game = new Game();
        Socket socket = new Socket();

        Game.Player player1 = game.new Player(socket,1,0);
        game.setPlayer(player1);

        assertEquals(game.player,player1);
    }
}

