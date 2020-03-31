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
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Behaviours;

public class SteeringBehaviour implements Screen {

    private Viewport port;
    private OrthographicCamera gamecam;
    private  Game game;
    private Actor actor;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Array<Target> targets;
    private boolean followActor;

    public SteeringBehaviour(Behaviours game){
        followActor = false;
        this.game = game;
        targets = new Array<Target>();
        b2dr = new Box2DDebugRenderer();
        gamecam = new OrthographicCamera();
        port = new FitViewport(Behaviours.WIDTH,Behaviours.HEIGHT,gamecam);
        world = new World(new Vector2(0,0), false);
        actor = new Actor(this, 0,0);
        targets.add(new Target(this, 300,120));

    }

    public void handleInput(){
        if (Gdx.input.isTouched()) {

            actor.applyForce();

            System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());

        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.W)){
            gamecam.zoom -= 0.2f;
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.S)){
            gamecam.zoom += 0.2f;
        }else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            gamecam.position.x += 10;
        }else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            gamecam.position.x -= 10;
        }else if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            gamecam.position.y += 10;
        }else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            gamecam.position.y -= 10;
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            followActor = followActor ? false: true;
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            actor.getBody().setAwake(false);



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

    public World getWorld(){
        return  world;

    }

    public OrthographicCamera getCamera(){
        return gamecam;
    }

    public Array<Target> getTargets() {
        return targets;
    }
}
