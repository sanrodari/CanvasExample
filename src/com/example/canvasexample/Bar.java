package com.example.canvasexample;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Bar implements Paintable {

	private int x;
	private int y;
	
	private int vX;
	private int vY;
	
	private Rect rect = new Rect();
	private Paint paint = new Paint();
	private CanvasGame context;
	
	public Bar(int x, int y, CanvasGame context) {
		this.context = context;
		
		this.y = y;
		this.x = x;
	}
	
	@Override
	public void paint(Canvas canvas) {
		canvas.drawRect(getRect(), paint);
	}
	
	public Rect getRect() {
		rect.set(x, y, x + GameParams.BAR_WIDTH, y + GameParams.BAR_HEIGHT);
		return rect;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void transform() {
		int newCoordinate = x + vX;
		if(newCoordinate + GameParams.BAR_WIDTH > context.getMaximumX() || newCoordinate < 0) {
			newCoordinate = x;
		}
		x = newCoordinate;
		
		newCoordinate = y + vY;
		if(newCoordinate > context.getMaximumY() || newCoordinate < 0) {
			newCoordinate = y;
		}
		y = newCoordinate;
	}

	public int getvX() {
		return vX;
	}

	public void setvX(int vX) {
		this.vX = vX;
	}

	public int getvY() {
		return vY;
	}

	public void setvY(int vY) {
		this.vY = vY;
	}

}
