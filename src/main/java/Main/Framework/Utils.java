package Main.Framework;

import Main.Script;
import org.powbot.api.rt4.*;

public class Utils {



    public static Player getLocalPlayer()
    {
        return Players.local();
    }

    public static int getLocalHealth()
    {
        return Combat.health();
    }



}
