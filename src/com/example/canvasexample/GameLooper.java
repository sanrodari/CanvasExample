package com.example.canvasexample;

public class GameLooper {

	private Runnable runnable;
	private boolean playing;

	public GameLooper(Runnable runnable) {
		this.runnable = runnable;
	}
	
	public void play() {
		if(!playing) {
			playing = true;
			while (playing) {
				runnable.run();
			}
		}
	}
	
	public void pause() {
		playing = false;
	}

}
