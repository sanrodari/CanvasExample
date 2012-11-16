package com.example.canvasexample;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.results)
public class ResultsActivity extends Activity {

	@Extra("upperScore")
	int upperScore;
	
	@Extra("lowerScore")
	int lowerScore;
	
	@ViewById
	TextView results;
	
	@AfterViews
	void setResult() {
		results.setText("The final score. You: " + lowerScore + " And: " + upperScore);
	}
	
	@Click
	public void replay() {
		finish();
		
		Intent intent = new Intent(this, MainActivity_.class);
		startActivity(intent);
	}
	
	@Click
	public void finishGame() {
		finish();
	}

}
