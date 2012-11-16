package com.example.canvasexample;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Score implements Paintable {
	
	private int scoreUpper = 0;
	private int scoreLower = 0;
	
	private CanvasGame context;
	private Paint paint = new Paint();
	
	public Score(CanvasGame context) {
		this.context = context;
	}

	@Override
	public void paint(Canvas canvas) {
		int contextHeight = context.getHeight();
		
		canvas.drawText("And: " + scoreUpper, 0, 3F * contextHeight / 8, paint);
		canvas.drawText("You: " + scoreLower, 0, 5F * contextHeight / 8, paint);
	}

	@Override
	public void transform() { }

	public void addPoint(String bar) {
		if(bar.equals("UPPER_BAR")){
			scoreUpper ++;
		}
		else if(bar.equals("LOWER_BAR")) {
			scoreLower ++;
		}
	}

	public int getScoreUpper() {
		return scoreUpper;
	}

	public void setScoreUpper(int scoreUpper) {
		this.scoreUpper = scoreUpper;
	}

	public int getScoreLower() {
		return scoreLower;
	}

	public void setScoreLower(int scoreLower) {
		this.scoreLower = scoreLower;
	}

}
