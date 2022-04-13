package mindows.ui;

import arc.input.*;
import arc.math.geom.*;
import arc.scene.event.*;
import arc.scene.style.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.ui.*;

public class WindowTable extends Table{
    public TextureRegionDrawable icon;

    protected float dX = 0, dY = 0;

    public WindowTable(){
        top();
        titleBar();
        table(t -> {
            t.top().left();
            t.labelWrap(() -> "" + Time.time).top().left().grow();
        }).height(600).growX().top().left();
    }

    protected void titleBar(){
        table(t -> {
            t.table(Tex.buttonEdge1, b -> {
                b.top().left();
                b.image(icon).size(20f).padLeft(15).top().left();
                b.pane(Styles.nonePane, p -> {
                    p.top().left();
                    p.labelWrap(name).padLeft(20).top().left().get().setAlignment(Align.topLeft);
                }).top().left().height(40f).growX().get().setScrollingDisabled(true, true);
            }).maxHeight(40f).grow();
            t.table(Tex.buttonEdge3, b -> {
                b.button(Icon.cancel, Styles.emptyi, () -> visible(() -> false));
            }).maxHeight(40f).width(80f).growY();

            t.touchable = Touchable.enabled;
            t.addListener(new InputListener(){
                float lastX, lastY;
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, KeyCode button) {
                    Vec2 v = t.localToStageCoordinates(Tmp.v1.set(x, y));
                    lastX = v.x;
                    lastY = v.y;
                    toFront();
                    return true;
                }

                @Override
                public void touchDragged(InputEvent event, float x, float y, int pointer) {
                    Vec2 v = t.localToStageCoordinates(Tmp.v1.set(x, y));

                    dX = v.x - lastX;
                    dY = v.y - lastY;
                    lastX = v.x;
                    lastY = v.y;
                }
            });
        }).top().height(40f).growX();
        row();
    }

    @Override
    public void act(float delta){
        if(parent != null){
            parent.addChild(this);
        }

        setPosition(x + dX, y + dY);

        super.act(delta);

        invalidateHierarchy();
    }

}
