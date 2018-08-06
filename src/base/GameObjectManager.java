package base;

import game.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameObjectManager {

    static public GameObjectManager instance = new GameObjectManager();

    private List<GameObject> list;
    private List<GameObject> tempList;

    private GameObjectManager() {
        this.list = new ArrayList<>();
        this.tempList = new ArrayList<>();
    }

    public GameObject getPlayer() {
        Player player = null;
        for (GameObject gameObject:this.list) {
            if (gameObject instanceof Player) {
                player = (Player)gameObject;
            }
        }
        return player;
    }

    public void add(GameObject gameObject) {
        this.tempList.add(gameObject);
    }

    public void runAll() {
        this.list.forEach(gameObject -> gameObject.run());
        this.list.addAll(this.tempList);
        this.tempList.clear();
    }

    public void renderAll(Graphics graphics) {
        this.list.forEach(gameObject -> gameObject.render(graphics));
    }
}
