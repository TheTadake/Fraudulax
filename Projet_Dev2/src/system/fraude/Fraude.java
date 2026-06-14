package system.fraude;

import java.time.LocalDate;
import java.util.Objects;
/**
 * @brief Classe abstraite représentant une fraude.
 * @details Cette classe contient les informations relatives à une fraude, telles que la date, le contenu et la description.
 */
public abstract class Fraude {
    private LocalDate date;
    private String contenu;
    private String description;

    /**
     * @brief Constructeur de la classe Fraude.
     * @param date
     * @param contenu
     * @param description
     */
    public Fraude(LocalDate date, String contenu, String description) {
        this.date = date;
        this.contenu = contenu;
        this.description = description;
    }

    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date) {this.date = date;}

    public String getContenu() {return contenu;}
    public void setContenu(String contenu) {this.contenu = contenu;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Fraude fraude = (Fraude) o;
        return Objects.equals(date, fraude.date) && Objects.equals(contenu, fraude.contenu) && Objects.equals(description, fraude.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, contenu, description);
    }

    @Override
    public String toString() {
        return "Fraude  :" +"\n"+
                "date : " + date +"\n"+
                "contenu : " + contenu +"\n"+
                "description : " + description+"\n";

    }
}


