package mindows;

import arc.*;
import arc.struct.*;
import arc.util.*;
import mindows.ui.*;
import mindustry.*;
import mindustry.ui.*;

public class WindowManager {
    public static Seq<Window> windows = new Seq<>();

    public static void init(){
        Log.info(windows.size);

        Vars.ui.hudGroup.fill(t -> {
            t.name = "Windows";
            for(Window window : windows){
                t.add(window);
            }
        });

        Vars.ui.hudGroup.fill(t -> {
            t.center().right();
            t.table(Core.atlas.drawable("mindows-2-sidebar"), b -> {
                b.name = "Window Buttons";
                b.right();

                for(Window window : windows){
                    b.button(window.icon, Styles.emptyi, () -> {
                        window.toggle();

                        // Disabling the parent's layout fixes issues with updating elements inside windows.
                        // However, it also disables the layout of all its children, so we need to re-enable them.
                        window.parent.setLayoutEnabled(false);
                        window.setLayoutEnabled(true);
                        for(Window w : windows){
                            w.setLayoutEnabled(true);
                        }
                    }).disabled(window.shown)
                        .size(40f)
                        .tooltip(tt -> {
                            tt.setBackground(Styles.black6);
                            tt.label(() -> Core.bundle.get(window.name)).pad(2f);
                        });
                    b.row();
                }
            }).right();
        });
    }

    public static int register(Window window){
        windows.add(window);
        return windows.size - 1;
    }
}
