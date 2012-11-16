package com.example.canvasexample;

import android.graphics.Canvas;

public class GameData implements Paintable {
	
	private Bar upperBar;
	private Bar lowerBar;
	
	private Ball ball;
	private Score score;
	
	private CanvasGame context;
	
	private boolean isOver = false;
	private boolean isCancelled = false;
	private OnFinish onFinish;
	
	public GameData(CanvasGame contextParam, OnFinish onFinish) {
		context = contextParam;
		this.onFinish = onFinish;
		
		ball = new Ball(context);
		score = new Score(context);
		
		upperBar = new AiBar(context.getUpperBarX(), context.getUpperBarY(), context, ball);
		lowerBar = new Bar(context.getLowerBarX(), context.getLowerBarY(), context);
	}

	@Override
	public void paint(Canvas canvas) {
		upperBar.paint(canvas);
		lowerBar.paint(canvas);
		
		ball.paint(canvas);
		
		score.paint(canvas);
	}

	@Override
	public void transform() {
		upperBar.transform();
		lowerBar.transform();
		
		ball.transform();
	}
	
	public void checkCollisions() {
		boolean collideUpper = upperBar.getRect().intersect(ball.getRect());
		boolean collideLower = lowerBar.getRect().intersect(ball.getRect());
		if(collideUpper) {
			ball.collideUpper();
			ball.transform();
		}
		else if(collideLower) {
			ball.collideLower();
			ball.transform();
		}
		
	}

	public void pressLeft() {
		lowerBar.setvX( -GameParams.BAR_SPEED);
	}

	public void pressRight() {
		lowerBar.setvX(GameParams.BAR_SPEED);
	}
	
	public void endGame() {
		isOver = true;
		
		onFinish.onFinish();
	}
	
	public void cancelGame() {
		isCancelled = true;
	}
	
	public void addPoint(String bar) {
		score.addPoint(bar);
		
		if(
			score.getScoreLower() >= GameParams.POINTS_TO_WIN ||
			score.getScoreUpper() >= GameParams.POINTS_TO_WIN
		) {
			endGame();
		}
	}
	
	public interface OnFinish {
		void onFinish();
	}
	
	public int getScoreUpper() {
		return score.getScoreUpper();
	}

	public int getScoreLower() {
		return score.getScoreLower();
	}

	public boolean isOver() {
		return isOver;
	}

	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

}
