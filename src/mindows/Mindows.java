package mindows;

import arc.*;
import arc.scene.*;
import mindows.ui.*;
import mindustry.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.fragments.*;

public class Mindows extends Mod{

    public Mindows(){
        Events.on(ClientLoadEvent.class, e -> {
            new Fragment(){
                @Override
                public void build(Group parent){
                    parent.addChild(new WindowTable());
                }
            }.build(Vars.ui.hudGroup);
        });
    }

    @Override
    public void loadContent(){
    }

}
