package com.example.canvasexample;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

public class CanvasGame extends View {
	
	private GameData gameData;

	public CanvasGame(Context context) {
		super(context);
		
		setFocusable(true);
	}
	
	public CanvasGame(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		setFocusable(true);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if(gameData != null){
			gameData.paint(canvas);
		}
	}

	public GameData getGameData() {
		return gameData;
	}
	
	public void setGameData(GameData gameData) {
		this.gameData = gameData;
		invalidate();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
			gameData.pressLeft();
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
        	gameData.pressRight();
            return true;
        }

		return super.onKeyDown(keyCode, event);
	}
	
	public void addPoint(String bar) {
		gameData.addPoint(bar);
	}
	
	public int getUpperBarX() {
		return getWidth() / 2;
	}
	public int getUpperBarY() {
		return (int) ( (1 / 10.0) * getHeight() );
	}

	public int getLowerBarX() {
		return getWidth() / 2;
	}
	public int getLowerBarY() {
		return (int) ( (8.5 / 10.0) * getHeight() );
	}

	public int getMaximumX() {
		return getWidth();
	}

	public int getMaximumY() {
		return getHeight();
	}
	
}
