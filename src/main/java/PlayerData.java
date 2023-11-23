import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
class PlayerData {
    @XmlElement
    private String player;
    @XmlElement
    private String sex;
    @XmlElement
    private String nationality;
    @XmlElement
    private String born;
    @XmlElement
    private String died;
    @XmlElement
    private String position;

    @XmlElement
    private String national_team;
    @XmlElementWrapper(name = "teams")
    @XmlElement(name = "team")
    private List<String> teams;



    public String getPlayer() {
        return player;
    }

    public void setUp(String player, String sex, String nationality, String position, String born, String died, String national_team ) {
        this.player = player;
        this.sex = sex;
        this.nationality = nationality;
        this.position = position;
        this.born = born;
        this.died = died;
        this.national_team = national_team;

    }
    public void setTeams(List<String> teams) {
        this.teams = teams;
    }

    public List<String> getTeams() {
        return teams;
    }
    public String getSex(){
        return sex;
    }
    public String getNationality(){
        return nationality;
    }
    public String getPosition(){
        return position;
    }
    public String getBorn(){
        return born;
    }
    public String getDied(){
        return died;
    }

    public PlayerData() {
    }

    @Override
    public String toString() {
        return "PlayerData{" +
                "player='" + player + '\'' +
                ", sex='" + sex + '\'' +
                ", nationality='" + nationality + '\'' +
                ", position='" + position + '\'' +
                ", born='" + born + '\'' +
                ", died='" + died + '\'' +
                ", teams=" + teams +
                '}';
    }


}