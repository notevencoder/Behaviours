package Objects;

import Screens.SteeringBehaviour;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.game.Behaviours;
import javafx.stage.Screen;

public class Target extends BasicObj {

    private SteeringBehaviour screen;
    private float x, y , rad;

    public Target(SteeringBehaviour screen, int x, int y) {
        this.screen = screen;
        this.x = x;
        this.y = y;
        rad = 30;
        this.world = screen.getWorld();
        defineObject();

    }

    @Override
    public void defineObject() {
        bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(new Vector2(x,y));

        fdef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(rad);
        fdef.shape = circleShape;
        fdef.isSensor = true;

        body = world.createBody(bdef);
        body.createFixture(fdef);


    }

    public Vector2 getPosition(){
        return body.getPosition();

    }

    public float getRad() {
        return rad;
    }
    public void  update(){
        body.applyForceToCenter(0,0,true );
    }
}
