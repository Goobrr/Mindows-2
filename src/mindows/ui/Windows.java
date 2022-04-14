package mindows.ui;

import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.gen.*;

public class Windows {
    public static void load(){
        new Window(Icon.box, "test-window", t -> {
            t.labelWrap(() -> t.parent.x + ", " + t.parent.y).top().right().growX();
            t.row();
            t.labelWrap(() -> t.parent.getWidth() + ", " + t.parent.getHeight()).top().right().growX();
            t.row();
            t.labelWrap(() -> "T: " + Time.time).top().right().growX();
        });
    }
}
