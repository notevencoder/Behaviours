package Objects;

import Screens.SteeringBehaviour;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Actor extends BasicObj{

    private Vector2 desiredVelocity;
    private SteeringBehaviour screen;
    private ShapeRenderer shapes;
    private float width, height, x, y;
    private CircleShape shape = new CircleShape();
    private Vector2 center;
    private float rad;

    public Actor(SteeringBehaviour screen, float x, float y){
        this.screen = screen;
        rad = 4;
        this.width = 20;
        this.height = 40;

        this.x = x;
        this.y = y;
        this.world = screen.getWorld();

        defineObject();
        center = new Vector2(x,y);

        currentVelocity = new Vector2(0,0);
        desiredVelocity = currentVelocity;

    }

    @Override
    public void defineObject() {
        bdef = new BodyDef();
        bdef.position.set(x,y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        fdef = new FixtureDef();
        fdef.restitution = 1;

        shape.setRadius(rad);
        fdef.shape = shape;
        body.createFixture(fdef);
    }

    public void update(){
        center.x = body.getPosition().x; //+ rad;
        center.y = body.getPosition().y; //+ rad;





    }


    public void applyForce(){

        body.applyLinearImpulse( getPosition().x + 100, getPosition().y + 100, getPosition().x, getPosition().y, true);

    }







    public Vector2 getPosition(){
        return body.getPosition();
    }
    public Body getBody(){
        return body;
    }
    public Vector2 getCenter(){
        return center;
    }
    public CircleShape getShape() {
        return shape;
    }
}
