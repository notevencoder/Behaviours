package com.mygdx.game;

import Screens.SteeringBehaviour;
import Tools.ScreenManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Behaviours extends Game {
	public final static float WIDTH = 500, HEIGHT = 500;
	private ScreenManager manager;
	@Override
	public void create () {

		setScreen(new SteeringBehaviour(this));
	}

	public void update(){




	}

	@Override
	public void render () {
		update();
		super.render();
	}
	
	@Override
	public void dispose () {

	}
}
