import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWindowAdapter extends WindowAdapter {

    /**
     * Metoda umozliwiajaca zamykanie okna "Przyciskiem X"
     * @param e eeeeeeeeeee
     */
    public void windowClosing(WindowEvent e) { System.exit(0); }
}
