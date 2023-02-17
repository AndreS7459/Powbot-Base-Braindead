package Main.Framework;

import Main.Script;
import org.powbot.api.Tile;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Objects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Constants {

    // place things that dont change (constants) in here e.g safespot tile or item id's
    public static int AgsID = 11802;
    // manually entered tile location
    Tile safespot = new Tile(500, 1322, 0);
    // gets tile selected by user from the gui
    Tile safespot2 = Script.TileName.tile();

}
