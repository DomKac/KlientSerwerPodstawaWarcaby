import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * klasa tworząca całe okienko z gui dla użytkownika
 */
class Ramka extends JFrame {

    PanelGry game_panel;
    JTextField which_player;
    JButton pass;

    /**
     * konstruktor klasy ramka
     * @param count_of_players liczba graczy
     * @param id numer gracza
     * @param player_mark kolor gracza
     * @param desktopx wspólrzedna x na ekranie
     * @param desktopy współrzedna y  na ekranie
     * @throws Exception e
     */
    Ramka(int count_of_players, char id, Color player_mark, int desktopx, int desktopy) throws Exception {
        super("Chinskie Warcaby, Gracz " + id + " ");
        setBounds(desktopx,desktopy,465,426);
        addWindowListener(new MyWindowAdapter());
        setLayout(new BorderLayout());

        game_panel = new PanelGry(count_of_players, player_mark);
        this.add(game_panel,BorderLayout.CENTER);

        JPanel up_panel = new JPanel();
        up_panel.setLayout(new GridLayout(1,2));
        this.add(up_panel, BorderLayout.PAGE_START);
        JPanel down_panel = new JPanel();
        up_panel.setLayout(new GridLayout(1,2));
        this.add(down_panel, BorderLayout.PAGE_END);

        which_player = new JTextField(); //pole z kolorem gracza, czyjego jest tura
        which_player.setEditable(false);
        which_player.setText("Tura gracza: ");
        which_player.setHorizontalAlignment(JTextField.CENTER);
        which_player.setBackground(Color.WHITE);
        up_panel.add(which_player);

        ActionListener open_instruction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                InstrukcjaRamka new_info = new InstrukcjaRamka();
                new_info.setVisible(true);
            }
        };

        JButton info = new JButton("Instrukcja");
        down_panel.add(info);
        info.addActionListener(open_instruction);

        pass = new JButton("PASS");
        down_panel.add(pass);

        setResizable(true);

    }


}


