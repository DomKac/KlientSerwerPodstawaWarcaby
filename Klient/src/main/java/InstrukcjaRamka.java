import javax.swing.*;
import java.awt.*;

class InstrukcjaRamka extends JFrame {

    String tresc = "Ramka kazdego gracza zawiera 4 czesci: \n" +
            "- Na samej gorze ramki znajduje sie Pasek z naspisem 'Tura gracza'." +
            "  Jego kolor informuje o kolorze gracza, ktory w tym momencie wykonuje swoj ruch. \n" +
            "- Na dole znajduja sie dwa przyciski: 'Instrukcja' i 'PASS'.\n" +
            "  Przycisk 'PASS' odpowiada za pasowanie tury;\n" +
            "  Przycisk 'Instrukcja' otwiera okno z instrukcja\n" +
            "- Glowna czesc okna kazdego gracza stanowi plansza w ksztalcie szescioramiennej gwiazdy, na ktorej bedzie odbywac sie rozgrywka. \n" +
            "- Paski po bokach planszy oznaczaja kolor pionkow gracza.\n\n" +
            "ZASADY \n"+
            "1. Gracz wybiera pole swojego koloru. \n" +
            "2. Po kliknieciu w pionka wyswitlaja sie mozliwe dla ruchy do wykonania ktore sa\n" +
            "   oznaczone kolorem szarym.\n" +
            "3. Klikajac na jedno z szarych pol wybrany pionek zostaje tam przeniesiony.\n" +
            "4. Mozliwe jest spasowanie tury klikajac guzik 'PASS' na dole okna\n" +
            "5. Mozliwe jest wykonywanie ruchow tylko we wlasnej turze";


    /**
     * klasa do tworzenia instrukcji gry w dodatkowym okienku pod guzikiem
     */
    InstrukcjaRamka(){
        super("Instrukcja");
        JTextArea text = new JTextArea();
        setBounds(200,200,820,320);
        text.setEditable(false);
        text.setBackground(Color.WHITE);
        text.append(tresc);
        this.add(text);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
}
