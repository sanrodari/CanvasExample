package com.example.canvasexample;

import android.graphics.Rect;

public class AiBar extends Bar {
	
	private Ball ball;

	public AiBar(int x, int y, CanvasGame context, Ball ball) {
		super(x, y, context);
		
		this.ball = ball;
	}
	
	@Override
	public void transform() {
		calculateVx();
		
		super.transform();
	}
	
	private void calculateVx() {
		Rect rect = getRect();
		
		if(ball.getX() > rect.centerX()) {
			setvX(GameParams.BAR_SPEED / 2);
		}
		else {
			setvX(-GameParams.BAR_SPEED / 2);
		}
		
	}

}
