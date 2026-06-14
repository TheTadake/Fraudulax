package system.fraude;

import java.time.LocalDate;

/**
 * @brief  Classe  représentant une fraude d'usage d'une anti-sèche.
 */
public class FraudePapier extends Fraude{
    public static final String type = "PAPIER";

    /**
     * @brief Constructeur de la classe FraudePapier.
     * @param date
     * @param contenu
     * @param descriptionTextuelle
     */
    public FraudePapier(LocalDate date, String contenu, String descriptionTextuelle){
        super(date,contenu,descriptionTextuelle);
    }
}
