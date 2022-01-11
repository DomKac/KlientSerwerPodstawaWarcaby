import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.Scanner;

class Ramka extends JFrame {

    PanelGry panelGry;
    JTextField which_player;
    JButton pass;


    /**
     * konstrukcja ramki dla każdego usera
     * @param liczba_graczy liczba graczy
     * @param id id gracza
     * @param markergracza oznaczenie, jaki kolor ma dany gracz
     * @throws Exception ex
     */
    Ramka(int liczba_graczy, char id, Color markergracza, int desktopx, int desktopy) throws Exception {
        super("Chinskie Warcaby, Gracz " + id + " ");
        setBounds(desktopx,desktopy,465,426);
        addWindowListener(new MyWindowAdapter());
        setLayout(new BorderLayout());

        panelGry = new PanelGry(liczba_graczy, markergracza);
        this.add(panelGry,BorderLayout.CENTER);

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
                InstrukcjaRamka nowa_instrukcja = new InstrukcjaRamka();
                nowa_instrukcja.setVisible(true);
            }
        };

        JButton instrukcja = new JButton("Instrukcja");
        down_panel.add(instrukcja);
        instrukcja.addActionListener(open_instruction);

        pass = new JButton("PASS");
        down_panel.add(pass);

        setResizable(true);

    }


}


