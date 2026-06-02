package system.fraude;

import java.time.LocalDate;

public class FraudeIAG extends  Fraude {
    public static final String type = "IAG";

    public FraudeIAG(LocalDate date, String contenu, String description) {
        super(date, contenu, description);
    }

}
