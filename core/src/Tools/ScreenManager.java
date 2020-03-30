package Tools;

import Screens.SteeringBehaviour;
import com.badlogic.gdx.Screen;

import java.util.HashMap;

public class ScreenManager {
    private Screen currentScreen;
    private HashMap<String, Screen> screens;


    public ScreenManager(){
        screens.put("Steering", new SteeringBehaviour());
        setScreen("Steering");
    }


    public void setScreen(String name){
            currentScreen = screens.get(name);

    }

    public Screen getCurrentScreen(){
        return  currentScreen;

    }

}
