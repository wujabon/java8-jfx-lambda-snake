package com.lurenx.jfx.snake.ui;


import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import com.lurenx.jfx.snake.model.Direction;
import com.lurenx.jfx.snake.model.Food;
import com.lurenx.jfx.snake.model.Location;
import com.lurenx.jfx.snake.model.Snake;
import com.lurenx.jfx.snake.service.SnakeController;
/**
 * primary pane
 * @author Administrator
 *
 */
public class SnakeUI extends Application {
	public static final int WIDTH = 500;
	public static final int HEIGTH = 350;
	private static GraphicsContext gc;
	private static Snake snake;
	
	private static Food food;
	
	private ScheduledFuture future;
	private ScheduledThreadPoolExecutor executor;
	//private User user;
	private PromptPane closePane;
	
	private static int score = 0;
	private static final int GRID_SIZE = 10;
	
	public static final Color[] color = new Color[]{Color.ORANGE, Color.GRAY};
	private Stage primaryStage;
	
	public static Food getFood() {
		return food;
	}

	/**
	 * 设置食物
	 * @param inFood
	 */
	public static void setFood(Food inFood) {
		int orgScore = 0;
		if(food == null || food.getScore() == 0) {
			orgScore = 10;
		}else {
			orgScore = food.getScore();
			orgScore = orgScore + orgScore / 5;
		}
		SnakeUI.food = inFood;
		food.setScore(orgScore);
		//score = food.setScore(score);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("贪吃蛇");
		primaryStage = stage;
		closePane = new PromptPane("是否退出", "是否退出游戏贪吃蛇？");
		//添加按钮
		addIcon();
		stage.show();
		stage.setOnCloseRequest(event ->{
			event.consume();
			close();
		});
		
	}
	
	/**
	 * 开始跑蛇
	 */
	private void snakeStart() {
		initSnake();
		Canvas canvas = new Canvas(WIDTH, HEIGTH);
		Group root = new Group();
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		//addIcon();
		Scene scene = new Scene(root, WIDTH, HEIGTH);
		scene.setFill(Color.BLACK);
		primaryStage.setScene(scene);
		scene.setOnKeyPressed((event) ->{
			KeyCode code = event.getCode();
			controller(code);
		});
		timer();
	}

	/**
	 * 添加各类图标
	 */
	private void addIcon() {
		Group root = new Group();
		Scene scene = new Scene(root, WIDTH, HEIGTH);
		scene.getStylesheets().add("css/stage.css");
		scene.setFill(Color.BLACK);

		//logo
		FlowPane pane = new FlowPane();
		Label logo = new Label();
		pane.setLayoutX(100);
		pane.setLayoutY(20);
		pane.getChildren().add(logo);
		logo.getStyleClass().add("menu");
		
		Button start = new Button();
		pane.getChildren().add(start);
		start.getStyleClass().add("start");
		
		Button login = new Button();
		pane.getChildren().add(login);
		login.getStyleClass().add("login");
		
		Button register = new Button();
		pane.getChildren().add(register);
		register.getStyleClass().add("register");
		
		Button rank = new Button();
		pane.getChildren().add(rank);
		rank.getStyleClass().add("rank");
		
		Button exit = new Button();
		pane.getChildren().add(exit);
		exit.getStyleClass().add("exit");
		root.getChildren().add(pane);
		
		setListener(start, rank, exit, login, register);
		
		primaryStage.setScene(scene);
		
		//Background bg = new Background(new BackgroundImage(new Image("snakeIcon.png"), null, null, new BackgroundPosition(), size))
		//button.setBackground(value);
	}

	/**
	 * 设置监听
	 * @param start
	 * @param rank
	 * @param exit
	 * @param login
	 * @param register
	 */
	private void setListener(Button start, Button rank, Button exit, Button login, Button register) {
		start.setOnMouseClicked(event ->{
			snakeStart();
		});
		login.setOnMouseClicked(event ->{
			SnakeLoginUI.getIns().openLoginPane(primaryStage);
		});
		register.setOnMouseClicked(event ->{
			SnakeRegistUI.getIns().openRegistPane(primaryStage);
		});
		rank.setOnMouseClicked(event ->{
			SnakeRankPane.getIns().openRank(primaryStage);
		});
		exit.setOnMouseClicked(event -> {
			closePane.actionClose(primaryStage);
		});
	}

	/**
	 * 退出
	 */
	private void close() {
		closePane.actionClose(primaryStage);
		//System.exit(0);
	}

	/**
	 * 初始化蛇
	 */
	private void initSnake() {
		snake = SnakeController.getIns().getSnake();
	}

	/**
	 * 方向控制
	 * @param code
	 */
	private void controller(KeyCode code) {
		SnakeController snakeController = SnakeController.getIns();
		Direction direction;
		switch(code) {
		case UP:
			direction = Direction.NORTH;
			break;
		case RIGHT:
			direction = Direction.EAST;
			break;
		case DOWN:
			direction = Direction.SOUTH;
			break;
		case LEFT:
			direction = Direction.WEST;
			break;
		default:
			return;
		}
		snakeController.changeDirection(direction);
	}

	/**
	 * 定时执行
	 */
	private void timer() {
		executor = new ScheduledThreadPoolExecutor(1);
		int delayTime = 1000 - snake.getSpeed() *100;
		future = executor.scheduleWithFixedDelay(() ->{
			try{
				rePaintScene();
				if(food!=null) {
					paintFood();
				}else{
					randomFood();
				}
				runSnake();
				paintSnake();
				updateScore();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}, 0, delayTime, TimeUnit.MILLISECONDS);
	}

	/**
	 * 蛇跑起来
	 */
	private void runSnake() {
		LinkedList<Location> body = snake.getBody();
		int size = body.size();
		int prex = 0;
		int prey = 0;
		for(int i = 0; i < size; i ++) {
			Location loc = body.get(i);
			int x = loc.getPosx();
			int y = loc.getPosy();
			setLocation(loc, i == 0, prex, prey);
			prex = x;
			prey = y;
		}
		//判断是否死亡
		
		Location head = body.get(0);
		if(head.equals(food.getLocaiton())) {
			Location tail = new Location(prex, prey);
			body.add(tail);
			int speed = snake.getSpeed();
			speed = body.size() / 5 - 1;
			snake.setSpeed(speed);
			score += food.getScore();
			randomFood();
			updateScore();
		}
		size = body.size();
		for(int i = 1; i < size; i ++) {
			Location loc = body.get(i);
			if(loc.equals(head)) {
				//死亡
				dead();
				return;
			}
		}
	}
	
	/**
	 * 更新分数
	 */
	private void updateScore() {
		Platform.runLater(() ->{
			gc.setFill(Color.GRAY);
			gc.setFont(Font.font("Impact",15));
			gc.fillText("score:" + score, 5, 25);
		}) ;
	}

	/**
	 * 死亡
	 */
	private void dead() {
		future.cancel(true);
		executor.shutdown();
		Platform.runLater(() ->{
			gc.setFill(Color.RED);
			gc.setFont(Font.font("Impact",80));
			gc.fillText("DEAD", 170, 150);
			gc.setFill(Color.WHITE);
			gc.setFont(Font.font("Impact",20));
			gc.fillText("score:" + score, 210, 170);
		}) ;
	}

	/**
	 * 设置位置
	 * @param loc
	 * @param isHead
	 * @param prex
	 * @param prey
	 */
	private void setLocation(Location loc, boolean isHead, int prex, int prey) {
		if(isHead) {
			Location newLoc = SnakeController.getIns().calNewLocation(loc.getPosx(), loc.getPosy(), snake.getDirection());
			int newX = newLoc.getPosx();
			int newY = newLoc.getPosy();
			if(newX < 0 || newX > WIDTH || newY < 0 || newY > HEIGTH) {
				//dead
				dead();
				return;
			}
			loc.setPosx(newX);
			loc.setPosy(newY);
		}else {
			loc.setPosx(prex);
			loc.setPosy(prey);
		}
		
	}

	/**
	 * 绘制蛇体
	 */
	private void paintSnake() {
		if(snake == null) return;
		LinkedList<Location> body = snake.getBody();
		int size = body.size();
		Location loc;
		for(int i = 0; i < size; i ++) {
			loc = body.get(i);
			paintSnakeBody(loc.getPosx(), loc.getPosy());
		}
	}

	/**
	 * 绘制点
	 * @param posx
	 * @param posy
	 */
	private void paintSnakeBody(int posx, int posy) {
		Platform.runLater(() -> {
			gc.setFill(Color.WHITE);
			gc.fillRoundRect(posx, posy, GRID_SIZE, GRID_SIZE, 2, 2);
		});
		
	}

	/**
	 * 重画背景
	 */
	private void rePaintScene() {
		Platform.runLater(() -> {
			gc.setFill(Color.BLACK);
			gc.fillRect(0, 0, WIDTH, HEIGTH);
		});
	}
	
	/**
	 * 画食物
	 */
	private void paintFood() {
		int colorIndex = food.getColorIndex() % 2;
		food.setColorIndex(colorIndex + 1);
		Location thisLocation = food.getLocaiton();
		gcFill(colorIndex, thisLocation);
	}

	/**
	 * 随机生成食物
	 */
	private void randomFood() {
		Random random = new Random();
		Food food = new Food();
		food.setColorIndex(0);
		Location local = new Location();
		int x = random.nextInt(WIDTH) / 10 * 10;
		int y = random.nextInt(HEIGTH) / 10 * 10;
		local.setPosx(x);
		local.setPosy(y);
		food.setLocaiton(local);
		setFood(food);
		paintFood();
	}

	/**
	 * 填充食物
	 * @param colorIndex
	 * @param loc
	 */
	private void gcFill(int colorIndex, Location loc) {
		Platform.runLater(() -> {
			gc.setFill(color[colorIndex]);
			gc.fillRect(loc.getPosx(), loc.getPosy(), GRID_SIZE, GRID_SIZE);
		});
	}

	/**
	 * 增加食物
	 * @param x
	 * @param y
	 */
	public static void addAFood(int x, int y) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				gc.setFill(Color.ORANGE);
				gc.fillRect(x, y, 10, 10);
			}
			
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
