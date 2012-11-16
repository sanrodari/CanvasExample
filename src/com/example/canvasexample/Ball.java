package com.example.canvasexample;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Ball implements Paintable {

	private int x;
	private int y;
	
	private int vX;
	private int vY;
	
	private Paint paint = new Paint();
	private Rect rect = new Rect();
	private Random random = new Random();
	
	private CanvasGame context;
	
	public Ball(CanvasGame context) {
		this.context = context;

		resetBall();
	}
	
	@Override
	public void paint(Canvas canvas) {
		canvas.drawCircle(x, y, GameParams.BALL_RADIUS, paint);
	}
	
	public Rect getRect() {
		rect.set(
				x - GameParams.BALL_RADIUS,
				y - GameParams.BALL_RADIUS,
				x + GameParams.BALL_RADIUS,
				y + GameParams.BALL_RADIUS);
		
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
		int newCoordinate = y + vY;
		// Si hay punto.
		if(newCoordinate > context.getMaximumY() || newCoordinate < 0) {
			
			// Si hace punto la barra superior.
			if(newCoordinate > context.getMaximumY()) {
				context.addPoint("UPPER_BAR");
			}
			// Si hace punto la barra inferior.
			else if(newCoordinate < 0) {
				context.addPoint("LOWER_BAR");
			}
			
			resetBall();
		}
		// Si no hay punto se calulca la x también.
		else {
			y = newCoordinate;
			
			newCoordinate = x + vX;
			if(newCoordinate > context.getMaximumX() || newCoordinate < 0) {
				vX = -vX;
				newCoordinate = x;
			}
			x = newCoordinate;
		}
	}
	
	private void resetBall() {
		calculateInitialSpeed();
		
		int contextWidth = context.getWidth();
		int contextHeight = context.getHeight();
		
		int percentageX = random.nextInt(20) + 40;
		int percentageY = random.nextInt(20) + 40;
		
		x = (int) (percentageX / 100.0 * contextWidth);
		y = (int) (percentageY / 100.0 * contextHeight);
	}

	private void calculateInitialSpeed() {
		vX = GameParams.INITIAL_BALL_SPEED_X * (random.nextBoolean() ? 1 : -1);
		vY = GameParams.INITIAL_BALL_SPEED_Y * (random.nextBoolean() ? 1 : -1);
		
		vX = (int) (((random.nextInt(5) + 6) / 10.0) * vX);
		vY = (int) (((random.nextInt(5) + 6) / 10.0) * vY);
	}

	public void collideUpper() {
		vX = (random.nextInt(10) + 5) * (random.nextBoolean() ? 1 : -1);
		vY = random.nextInt(10) + 5;
	}
	
	public void collideLower() {
		vX = (random.nextInt(10) + 5) * (random.nextBoolean() ? 1 : -1);
		vY = -(random.nextInt(10) + 5);
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
