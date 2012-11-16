package com.example.canvasexample;

import android.app.Activity;
import android.content.Intent;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;

@Fullscreen
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
	
	private GameData gameData;
	private GameLooper gameLooper;

	@ViewById
	CanvasGame canvasGame;
	
	@AfterViews
	public void startGame() {
		initAndPlayGame();
	}

	@Background
	void initAndPlayGame() {
		while (canvasGame.getWidth() == 0) {
			sleep();
		}
		
		gameData = new GameData(canvasGame, new GameData.OnFinish() {
			public void onFinish() {
				navigateToResults();
			}
		});
		setGameData();
		
		gameLooper = new GameLooper(new Runnable() {
			public void run() {
				transformGameData();
				
				setGameData();
				sleep();
				
				if(gameData.isOver() || gameData.isCancelled()) {
					gameLooper.pause();
				}
			}
		});
		
		gameLooper.play();
	}
	
	private void navigateToResults() {
		finish();
		
		Intent intent = new Intent(this, ResultsActivity_.class);
		intent.putExtra("lowerScore", gameData.getScoreLower());
		intent.putExtra("upperScore", gameData.getScoreUpper());
		
		startActivity(intent);
	}

	@Background
	void playGame() {
		if(gameLooper != null) {
			gameLooper.play();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		playGame();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		gameLooper.pause();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		gameData.cancelGame();
	}

	@UiThread
	void setGameData() {
		canvasGame.setGameData(gameData);
	}

	private void sleep() {
		try {
			Thread.sleep(GameParams.SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void transformGameData() {
		gameData.transform();
		gameData.checkCollisions();
	}

}
