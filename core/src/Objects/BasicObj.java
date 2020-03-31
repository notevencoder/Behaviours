package Objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public abstract class BasicObj {
    protected Body body;
    protected BodyDef bdef;
    protected FixtureDef fdef;
    protected World world;
    protected Vector2 position;

    protected Vector2 currentVelocity;

    public abstract void defineObject();


}
