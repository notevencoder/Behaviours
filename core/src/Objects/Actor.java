package Objects;

import Screens.SteeringBehaviour;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Behaviours;

public class Actor extends BasicObj{

    private Vector2 desiredVelocity;
    private SteeringBehaviour screen;
    private ShapeRenderer shapes;
    private float width, height, x, y;
    private CircleShape shape = new CircleShape();
    private float MaxSpeed;

    private float rad;

    public Actor(SteeringBehaviour screen, float x, float y){
        this.screen = screen;

        rad = 8;

        MaxSpeed = 300;
        this.x = x;
        this.y = y;
        this.world = screen.getWorld();

        defineObject();


        desiredVelocity = new Vector2(0,0);
        actualVelocity = new Vector2(0,0);

    }

    @Override
    public void defineObject() {
        bdef = new BodyDef();
        bdef.position.set(x,y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        fdef = new FixtureDef();
        fdef.restitution = 2;

        shape.setRadius(rad);
        fdef.shape = shape;
        body.createFixture(fdef);
    }

    public void update(boolean f){
        Target t = screen.getTarget();
        System.out.println(body.getPosition().dst(t.getPosition()));

        if (f){

            desiredVelocity = t.getPosition().sub(body.getPosition());
            actualVelocity = body.getLinearVelocity().add(desiredVelocity.limit(MaxDesiredSpreed));
            actualVelocity.limit(MaxDesiredSpreed);
            body.applyLinearImpulse(actualVelocity, body.getPosition(),true);

        }else if (f) body.setLinearVelocity(0,0);
//        System.out.println("current " + currentVelocity.x + "/" + currentVelocity.y);
//        System.out.println("actual " +actualVelocity.x + "/" + actualVelocity.y);
//        System.out.println("desired "  +desiredVelocity.x + "/" + desiredVelocity.y);
//        System.out.println(screen.getTarget().getPosition().x +  "/" + screen.getTarget().getPosition().y   );



    }


    public void applyForce(){

    }







    public Vector2 getPosition(){
        return body.getPosition();
    }
    public Body getBody(){
        return body;
    }

    public CircleShape getShape() {
        return shape;
    }
}
