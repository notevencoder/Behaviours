package Screens;

import Objects.Actor;
import Objects.Target;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.game.Behaviours;

public class SteeringBehaviour implements Screen {

    private Viewport port;
    private OrthographicCamera gamecam;
    private  Game game;
    private Actor actor;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Target target;
    private boolean followActor, updateActor;

    public SteeringBehaviour(Behaviours game){
        followActor = true;
        updateActor = true;
        this.game = game;
        b2dr = new Box2DDebugRenderer();
        gamecam = new OrthographicCamera();
        port = new FitViewport( Behaviours.WIDTH, Behaviours.HEIGHT, gamecam);
        world = new World(new Vector2(0, 0), false);

        target = new Target(this,0,0);

        gamecam.position.set(new Vector2(Behaviours.WIDTH / 2 ,Behaviours.HEIGHT / 2) , 0);
        actor = new Actor(this, 100, 123);

    }

    public void handleInput(){
        if (Gdx.input.isTouched()) {



            //System.out.println(actor.getPosition().x + " " + actor.getPosition().y);
            System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)){
            gamecam.zoom -= 0.2f;
        }if (Gdx.input.isKeyJustPressed(Input.Keys.S)){
            gamecam.zoom += 0.2f ;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)){
            actor.getBody().setLinearVelocity(-10000,0);

        }if (Gdx.input.isKeyJustPressed(Input.Keys.D)){
            actor.getBody().setLinearVelocity(10000,0);
        }if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            gamecam.position.x += 10;
        } if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            gamecam.position.x -= 10;
        } if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            gamecam.position.y += 10;
        } if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            gamecam.position.y -= 10;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT)){
            followActor = followActor ? false: true;


        }if (Gdx.input.isKeyJustPressed(Input.Keys.ALT_LEFT)){

            updateActor = updateActor ? false: true;

        } if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            actor.getBody().applyLinearImpulse(new Vector2(0, 100) , actor.getBody().getPosition(), true);
        } if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            actor.getBody().setAwake(false);
        } if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)){
            System.out.println(actor.getPosition().x + " " + actor.getPosition().y);
        }
    }

    @Override
    public void show() {

    }




    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f,0.3f,0.4f,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();

        world.step(1/60f,6,2);
        actor.update(updateActor);
        gamecam.update();

        if (followActor)
            gamecam.position.set(new Vector3(actor.getPosition(),0));





        b2dr.render(world, gamecam.combined);
    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public float getDistance(Vector2 v1, Vector2 v2){
        return (v1.x - v2.x)*(v1.x - v2.x) + (v1.y - v2.y)*(v1.y - v2.y);
    }

    public World getWorld(){
        return  world;

    }

    public OrthographicCamera getCamera(){
        return gamecam;
    }

    public Target getTarget() {
        return target;
    }
}
