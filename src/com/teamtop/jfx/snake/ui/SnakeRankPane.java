package com.teamtop.jfx.snake.ui;


import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/**
 * rank pane
 * @author wujabon
 *
 */
public class SnakeRankPane {
	private static SnakeRankPane snakeRankPane;
	private SnakeRankPane(){}
	
	private Stage rankStage;
	public static SnakeRankPane getIns() {
		if(snakeRankPane == null) {
			snakeRankPane = new SnakeRankPane();
		}
		return snakeRankPane;
	}

	public void openRank(Stage primaryStage) {
		rankStage = new Stage();
		rankStage.setTitle("玩家排行");
		rankStage.setResizable(false);
		rankStage.initOwner(primaryStage);
		rankStage.setScene(initScene(primaryStage));
		rankStage.show();
	}

	private Scene initScene(Stage primaryStage) {
		GridPane root = new GridPane();
		Label num = new Label("名次");
		Label name = new Label("昵称");
		Label score = new Label("分数");
		num.getStyleClass().add("title");
		name.getStyleClass().add("title");
		score.getStyleClass().add("title");
		root.add(num, 0, 0);
		root.add(name, 1, 0);
		root.add(score, 2, 0);
		int initScore = 50000;
		Random random = new Random();
		for(int i = 1; i <= 10; i ++){
			root.add(new Label(i + ""), 0, i);
			root.add(new Label("player_" + i), 1, i);
			root.add(new Label(initScore + ""), 2, i);
			initScore -= random.nextInt(150);
		}
		root.setHgap(25);
		root.setAlignment(Pos.CENTER);
//		root.setGridLinesVisible(true);
		Scene scene = new Scene(root, 200, 160);
		scene.getStylesheets().add("css/rank.css");
		return scene;
	}
}
