package mindows;

import arc.*;
import mindows.ui.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;

public class Mindows extends Mod{
    public Mindows(){
        Events.on(ContentInitEvent.class, e -> {
            Windows.load();
        });

        Events.on(ClientLoadEvent.class, e -> {
            WindowManager.init();
        });
    }
}
