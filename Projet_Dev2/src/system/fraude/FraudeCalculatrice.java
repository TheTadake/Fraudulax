package system.fraude;

import java.time.LocalDate;

public class FraudeCalculatrice extends Fraude {
    public static final String type = "CALCULATRICE";

    public FraudeCalculatrice(LocalDate date, String contenu, String descriptionTextuelle){
        super(date,contenu,descriptionTextuelle);
    }
}
