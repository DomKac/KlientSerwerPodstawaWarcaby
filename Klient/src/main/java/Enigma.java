import java.awt.*;

public class Enigma {

    public String koduj_kolor(Color kolor){

        if(kolor == Color.BLUE){
            return "B";
        }
        else if(kolor == Color.RED){
            return "R";
        }
        else if(kolor == Color.ORANGE){
            return "O";
        }
        else if(kolor == Color.YELLOW){
            return "Y";
        }
        else if(kolor == Color.PINK){
            return "P";
        }
        else {
            return "G";
        }
    }

    public Color odkoduj_kolor(String kolor){

        if(kolor.equals("B")){
            return Color.BLUE;
        }
        else if(kolor.equals("R")){
            return Color.RED;
        }
        else if(kolor.equals("O")){
            return Color.ORANGE;
        }
        else if(kolor.equals("Y")){
            return Color.YELLOW;
        }
        else if(kolor.equals("P")){
            return Color.PINK;
        }
        else {
            return Color.GREEN;
        }
    }

    public void koloruj(String response, Ramka frame){

        // MOVEXX,XX,YY,XX,YY,K,
        String x1 = "";
        String y1 = "";
        String x2 = "";
        String y2 = "";
        String kolor = "";
        int n=4;

        while(response.charAt(n)!=','){
            x1 = x1 + response.charAt(n);
            n++;
        }
        n++;

        while(response.charAt(n)!=','){
            y1 = y1 + response.charAt(n);
            n++;
        }
        n++;

        while(response.charAt(n)!=','){
            x2 = x2 + response.charAt(n);
            n++;
        }
        n++;

        while(response.charAt(n)!=','){
            y2 = y2 + response.charAt(n);
            n++;
        }
        n++;

        kolor = kolor + response.charAt(n);

        frame.messMoveSer(Integer.parseInt(x1),Integer.parseInt(y1),Integer.parseInt(x2),Integer.parseInt(y2),odkoduj_kolor(kolor));
    }


}

