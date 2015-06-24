package com.teamtop.jfx.snake.model;


public class Food {
	private Location locaiton;
	private int colorIndex;
	private int score;
	public Location getLocaiton() {
		return locaiton;
	}
	public void setLocaiton(Location locaiton) {
		this.locaiton = locaiton;
	}
	public int getColorIndex() {
		return colorIndex;
	}
	public void setColorIndex(int colorIndex) {
		this.colorIndex = colorIndex;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

}
