import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Executors;

//uruchomienie serwera dla wybranej liczby osób działa

public class Serwer {

    static ArrayList<Game.Player> players;
    static Game.Player gracz1, gracz2, gracz3, gracz4, gracz5, gracz6;

    public Serwer (int ile_gra) throws IOException {

        players = new ArrayList<Game.Player>();

        try (var listener = new ServerSocket(58901))
        {
            System.out.println("Warcaby Server for " + ile_gra + " osob is Running...");
            var pool = Executors.newFixedThreadPool(ile_gra);

            while (true)
            {
                Game game = new Game();


                switch (ile_gra)
                {
                    case 2:
                        gracz1 = game.new Player(listener.accept(), 1);
                        pool.execute(gracz1);
                        players.add(gracz1);
                        game.set(players.get(0));
                        game.numOf(ile_gra);
                        gracz2 = game.new Player(listener.accept(), 2);
                        pool.execute(gracz2);
                        players.add(gracz2);
                        game.set(players.get(1));
                        game.numOf(ile_gra);
                        game.tab(players);
                        break;

                    case 3:
                        gracz1 = game.new Player(listener.accept(), 1);
                        pool.execute(gracz1);
                        players.add(gracz1);
                        game.set(players.get(0));
                        game.numOf(ile_gra);
                        gracz2 = game.new Player(listener.accept(), 2);
                        pool.execute(gracz2);
                        players.add(gracz2);
                        game.set(players.get(1));
                        game.numOf(ile_gra);
                        gracz3 = game.new Player(listener.accept(), 3);
                        pool.execute(gracz3);
                        players.add(gracz3);
                        game.set(players.get(2));
                        game.numOf(ile_gra);
                        game.tab(players);
                        break;
                    case 4:
                        gracz1 = game.new Player(listener.accept(), 1);
                        pool.execute(gracz1);
                        players.add(gracz1);
                        game.set(players.get(0));
                        game.numOf(ile_gra);
                        gracz2 = game.new Player(listener.accept(), 2);
                        pool.execute(gracz2);
                        players.add(gracz2);
                        game.set(players.get(1));
                        game.numOf(ile_gra);
                        gracz3 = game.new Player(listener.accept(), 3);
                        pool.execute(gracz3);
                        players.add(gracz3);
                        game.set(players.get(2));
                        game.numOf(ile_gra);
                        gracz4 = game.new Player(listener.accept(), 4);
                        pool.execute(gracz4);
                        players.add(gracz4);
                        game.set(players.get(3));
                        game.numOf(ile_gra);
                        game.tab(players);
                        break;

                    case 6:
                        gracz1 = game.new Player(listener.accept(), 1);
                        pool.execute(gracz1);
                        players.add(gracz1);
                        game.set(players.get(0));
                        game.numOf(ile_gra);
                        gracz2 = game.new Player(listener.accept(), 2);
                        pool.execute(gracz2);
                        players.add(gracz2);
                        game.set(players.get(1));
                        game.numOf(ile_gra);
                        gracz3 = game.new Player(listener.accept(), 3);
                        pool.execute(gracz3);
                        players.add(gracz3);
                        game.set(players.get(2));
                        game.numOf(ile_gra);
                        gracz4 = game.new Player(listener.accept(), 4);
                        pool.execute(gracz4);
                        players.add(gracz4);
                        game.set(players.get(3));
                        game.numOf(ile_gra);
                        gracz5 = game.new Player(listener.accept(), 5);
                        pool.execute(gracz5);
                        players.add(gracz5);
                        game.set(players.get(4));
                        game.numOf(ile_gra);
                        gracz6 = game.new Player(listener.accept(), 6);
                        pool.execute(gracz6);
                        players.add(gracz6);
                        game.set(players.get(5));
                        game.numOf(ile_gra);
                        game.tab(players);
                        break;
                }


            }
        }
    }

    //startowanie serwera
    public static void main(String[] args) throws IOException
    {
        //utworzenie okna
        JFrame frame=new JFrame("Ile osób chce grać w to gówno?");
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4,1));
        int options[] ={2,3,4,6};
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
