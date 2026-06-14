package system.fraude;

import java.time.LocalDate;

/**
 * @brief  Classe  représentant une fraude d'usage à la calculatrice.
 */
public class FraudeCalculatrice extends Fraude {
    public static final String type = "CALCULATRICE";

    /**
     * @brief Constructeur de la classe FraudeCalculatrice.
     * @param date
     * @param contenu
     * @param descriptionTextuelle
     */
    public FraudeCalculatrice(LocalDate date, String contenu, String descriptionTextuelle){
        super(date,contenu,descriptionTextuelle);
    }
}
