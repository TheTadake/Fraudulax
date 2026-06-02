package system.fraude;

import java.time.LocalDate;

public class FraudePapier extends Fraude{
    public static final String type = "PAPIER";
    public FraudePapier(LocalDate date, String contenu, String descriptionTextuelle){
        super(date,contenu,descriptionTextuelle);
    }
}
