import java.awt.*;

public class Enigma {

    /**
     * funkcja podaje alias numeryczny koloru
     * @param kolor kolor typu Color
     * @return litera-alias
     */
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

    /**
     * funkcja na podstawie aliasu literowego zwraca kolor typu Color
     * @param kolor alias literowy koloru
     * @return kolor Color
     */
    public Color odkoduj_kolor(String kolor){

        return switch (kolor) {
            case "B" -> Color.BLUE;
            case "R" -> Color.RED;
            case "O" -> Color.ORANGE;
            case "Y" -> Color.YELLOW;
            case "P" -> Color.PINK;
            default -> Color.GREEN;
        };
    }

    /**
     * funkcja dekoduje wiadomości od serwera dla klienta i wykonuje ruchy
     * @param response wiadomość
     * @param frame plansza gracza
     */
    public void koloruj(String response, Ramka frame){

        // MOVEXX,XX,YY,XX,YY,K,A
        StringBuilder x1 = new StringBuilder();
        StringBuilder y1 = new StringBuilder();
        StringBuilder x2 = new StringBuilder();
        StringBuilder y2 = new StringBuilder();
        String kolor = "";
        int n=4;

        while(response.charAt(n)!=','){
            x1.append(response.charAt(n));
            n++;
        }
        n++;

        while(response.charAt(n)!=','){
            y1.append(response.charAt(n));
            n++;
        }
        n++;

        while(response.charAt(n)!=','){
            x2.append(response.charAt(n));
            n++;
        }
        n++;

        while(response.charAt(n)!=','){
            y2.append(response.charAt(n));
            n++;
        }
        n++;
        kolor = kolor + response.charAt(n);

        frame.panelGry.messMoveSer(Integer.parseInt(x1.toString()),Integer.parseInt(y1.toString()),Integer.parseInt(x2.toString()),Integer.parseInt(y2.toString()),odkoduj_kolor(kolor));
    }


    /**
     * funkcja na podstawie ilości graczy i indeksu gracza określa, jaki kolor ma dany gracz
     * @param numerek indeks gracza
     * @param ilosc ilość graczy
     * @return kolor gracza
     */
    public Color kolorgracza(char numerek, int ilosc){
        switch (ilosc){
            case 2: {
                if(numerek == '1'){
                    return Color.PINK;
                }
                else{
                    return Color.BLUE;
                }
            }
            case 3: {
                if(numerek == '1'){
                    return Color.PINK;
                }
                else if (numerek == '2'){
                    return Color.YELLOW;
                }
                else{
                    return Color.RED;
                }
            }
            case 4: {
                if(numerek == '1'){
                    return Color.ORANGE;
                }
                else if (numerek == '2'){
                    return Color.YELLOW;
                }
                else if (numerek == '3'){
                    return Color.RED;
                }
                else {
                    return Color.GREEN;
                }
            }
            case 6: {
                if(numerek == '1'){
                    return Color.PINK;
                }
                else if (numerek == '2'){
                    return Color.ORANGE;
                }
                else if (numerek == '3'){
                    return Color.YELLOW;
                }
                else if (numerek == '4'){
                    return Color.BLUE;
                }
                else if (numerek == '5'){
                    return Color.RED;
                }
                else {
                    return Color.GREEN;
                }
            }
            default:
                return Color.BLACK;
        }
    }


    /**
     * funkcja określająca alias literowy koloru gracza na podstawie jego indeksu i ilości graczy
     * @param numerek indeks gracza
     * @param ilosc ilość graczy
     * @return alias
     */
    public char idgracza(char numerek, int ilosc){
        switch (ilosc){
            case 2: {
                if(numerek == '1'){
                    return 'P';
                }
                else{
                    return 'B';
                }
            }
            case 3: {
                if(numerek == '1'){
                    return 'P';
                }
                else if (numerek == '2'){
                    return 'Y';
                }
                else{
                    return 'R';
                }
            }
            case 4: {
                if(numerek == '1'){
                    return 'O';
                }
                else if (numerek == '2'){
                    return 'Y';
                }
                else if (numerek == '3'){
                    return 'R';
                }
                else {
                    return 'G';
                }
            }
            case 6: {
                if(numerek == '1'){
                    return 'P';
                }
                else if (numerek == '2'){
                    return 'O';
                }
                else if (numerek == '3'){
                    return 'Y';
                }
                else if (numerek == '4'){
                    return 'B';
                }
                else if (numerek == '5'){
                    return 'R';
                }
                else {
                    return 'G';
                }
            }
            default:
                return 'x';
        }
    }

    public int set_desktop_x(char num){
        if(num == '1' || num == '4'){
            return 30;
        }
        else if(num == '2' || num == '5'){
            return 520;
        }
        else{
            return 1010;
        }
    }
    public int set_desktop_y(char num){
        if(num == '1' || num == '2' || num == '3'){
            return 0;
        }
        else{
            return 435;
        }
    }


}

