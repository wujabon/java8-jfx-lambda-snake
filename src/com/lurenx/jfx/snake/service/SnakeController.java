package com.lurenx.jfx.snake.service;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

import com.lurenx.jfx.snake.model.Direction;
import com.lurenx.jfx.snake.model.Location;
import com.lurenx.jfx.snake.model.Snake;

public class SnakeController {
	private Snake snake;
	private static final int INIT_SIZE = 5;
	private static final Direction INIT_DIRECTION = Direction.WEST; 
	private static Location INIT_LOCATION = new Location(200, 100);
	private static SnakeController snakeController;
	private static AtomicLong ID_INCREMENT = new AtomicLong(0);
	private static final int INIT_SPEED = 5;
	private static final int BASIC_SIZE = 10;
	private SnakeController() {
		
	}
	public static SnakeController getIns() {
		if(snakeController == null) {
			snakeController = new SnakeController();
		}
		return snakeController;
	}
	
	public Snake getSnake() {
		if(snake == null) {
			long id = ID_INCREMENT.getAndIncrement();
			snake = new Snake(id,INIT_SPEED,INIT_DIRECTION);
			LinkedList<Location> body = new LinkedList<Location>();
			for(int i = 0; i < INIT_SIZE; i ++) {
				int x = INIT_LOCATION.getPosx() + i * BASIC_SIZE;
				int y = INIT_LOCATION.getPosy();
				Location location = new Location(x, y);
				body.addLast(location);
				snake.setBody(body);
			}
		}
		return snake;
	}

	public void changeDirection(Direction direction) {
		if(snake != null) {
			snake.setDirection(direction);
		}
	}
	
	public Location calNewLocation(int x, int y, Direction direction) {
		switch(direction) {
		case NORTH:
			y -= BASIC_SIZE;
			break;
		case EAST:
			x += BASIC_SIZE;
			break;
		case SOUTH:
			y += BASIC_SIZE;
			break;
		case WEST:
			x -= BASIC_SIZE;
			break;
		}
		return new Location(x, y);
	}
}
