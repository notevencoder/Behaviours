package Objects;

import Screens.SteeringBehaviour;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import javafx.stage.Screen;

public class Target extends BasicObj {

    private SteeringBehaviour screen;
    private float x, y;

    public Target(SteeringBehaviour screen, int x, int y) {
        this.screen = screen;
        this.x = x;
        this.y = y;
        this.world = screen.getWorld();
        defineObject();
    }

    @Override
    public void defineObject() {
        bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(new Vector2(10,10));

        fdef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(30);
        fdef.shape = circleShape;
        fdef.isSensor = true;
        body = world.createBody(bdef);

        body.createFixture(fdef);


    }
}
