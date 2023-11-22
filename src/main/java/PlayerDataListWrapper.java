import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "players")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerDataListWrapper {

    @XmlElement(name = "player")
    private List<PlayerData> playerDataList;

    public PlayerDataListWrapper(){
        playerDataList = new ArrayList<>();
    }

    public List<PlayerData> getPlayerDataList() {
        return playerDataList;
    }

    public void setPlayerDataList(List<PlayerData> playerDataList) {
        this.playerDataList = playerDataList;
    }
}
