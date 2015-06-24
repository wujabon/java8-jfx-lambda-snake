package com.lurenx.jfx.snake.model;

import java.util.LinkedList;
/**
 * 蛇
 * @author wujabon
 *
 */
public class Snake {
	//id
	private long id;
	//速度
	private int speed;
	//方向
	private Direction direction;
	//蛇身
	private LinkedList<Location> body;
	public Snake(long id, int speed, Direction direction) {
		super();
		this.id = id;
		this.speed = speed;
		this.direction = direction;
	}
	public Snake() {
		super();
	}
	public Snake(long id, int speed, Direction direction,
			LinkedList<Location> body) {
		super();
		this.id = id;
		this.speed = speed;
		this.direction = direction;
		this.body = body;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public LinkedList<Location> getBody() {
		return body;
	}
	public void setBody(LinkedList<Location> body) {
		this.body = body;
	}

}
