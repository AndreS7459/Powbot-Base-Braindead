package Main;

import Main.Framework.Task;
import Main.Framework.Utils;
import Tasks.*;
import com.google.common.eventbus.Subscribe;
import org.powbot.api.*;
import org.powbot.api.Random;
import org.powbot.api.event.GameObjectActionEvent;
import org.powbot.api.event.MessageEvent;
import org.powbot.api.event.RenderEvent;
import org.powbot.api.event.TickEvent;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.*;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.api.script.paint.TrackSkillOption;
import org.powbot.mobile.SettingsManager;
import org.powbot.mobile.ToggleId;
import org.powbot.mobile.drawing.Rendering;
import org.powbot.mobile.script.ScriptManager;

import java.awt.event.ItemListener;
import java.util.*;



@ScriptConfiguration.List(
        {
                @ScriptConfiguration(
                        name = "checkbox name",
                        description = "This will show a checkbox",
                        optionType = OptionType.BOOLEAN
                ),
                @ScriptConfiguration(
                        name = "string name",
                        description = "this will give a list of strings to chose from",
                        allowedValues = {"Shark", "Manta ray"},
                        optionType = OptionType.STRING
                ),
                @ScriptConfiguration(
                        name = "int name",
                        description = "get an int value",
                        optionType = OptionType.INTEGER,
                        defaultValue = "60"
                ),
                @ScriptConfiguration(
                        name = "tile name",
                        description = "gets a tile location selected by user",
                        optionType = OptionType.TILE
                ),
        }
)

@ScriptManifest(name = "Base", description = "Base", version = "1")
public class Script extends AbstractScript {


    // task shit
    ArrayList<Task> tasks = new ArrayList<Task>();
    public static Task currentTask = null;


    // initialize shit
    public static boolean StopScript = false;


    // menu shit
    public static boolean CheckboxName;
    public static String StringName;
    public static int IntName;
    public static Tile TileName;



    @Override
    public void onStart() {


        Paint paint = PaintBuilder.newBuilder()
                .x(50)
                .y(70)
                .addString("Task: ", () -> {
                    if (currentTask == null) return "Setting up";
                    return currentTask.name;
                })
                .addString("String name: ", () -> StringName)
                .trackSkill(Skill.Agility)
                .build();
        addPaint(paint);

        // get the input from the menu
        CheckboxName = getOption("checkbox name");
        StringName = getOption("string name");
        IntName = getOption("int name");
        TileName = getOption("tile name");


        // add tasks
        tasks.add(new Example("Example (Task name)"));


    }

    // runs every 1ms
    @Override
    public void poll() {
    }

    // runs every tick
    @Subscribe
    public void onTick(TickEvent tickEvent)
    {
        System.out.println("[DEBUG] New Tick");


        if (StopScript)
            ScriptManager.INSTANCE.stop();

        for (Task task : tasks)
        {
            if (task.validate())
            {
                currentTask = task;
                task.execute();
            }
        }

    }



    public static void main(String[] args) {
        // Start your script with this function. Make sure your device is connected via ADB, and only one is connected
        new Script().startScript();
    }
}
