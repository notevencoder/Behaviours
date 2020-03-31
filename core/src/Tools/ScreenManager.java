package Tools;

import Screens.SteeringBehaviour;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.Behaviours;

import java.util.HashMap;

public class ScreenManager {
    private Game game;
    private Screen currentScreen;
    private HashMap<String, Screen> screens;


    public ScreenManager(Behaviours game){
        this.game = game;
        currentScreen = new SteeringBehaviour(game);
        screens.put("Steering", currentScreen);

        //setScreen("Steering");
    }


    public void setScreen(String name){
            currentScreen = screens.get(name);

    }

    public Screen getCurrentScreen(){
        return  currentScreen;

    }

}
