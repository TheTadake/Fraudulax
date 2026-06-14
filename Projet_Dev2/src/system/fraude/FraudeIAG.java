package system.fraude;

import java.time.LocalDate;
/**
 * @brief  Classe  représentant une fraude d'usage d'une IAG local.
 */
public class FraudeIAG extends  Fraude {
    public static final String type = "IAG";

    /**
     * @brief Constructeur de la classe FraudeIAG.
     * @param date
     * @param contenu
     * @param description
     */
    public FraudeIAG(LocalDate date, String contenu, String description) {
        super(date, contenu, description);
    }

}
