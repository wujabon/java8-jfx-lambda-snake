package com.lurenx.jfx.snake.ui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SnakeRegistUI {
	private static SnakeRegistUI snakeRegistUI;
	private Stage registStage;
	
	private SnakeRegistUI(){}
	
	public static SnakeRegistUI getIns() {
		if(snakeRegistUI == null) {
			snakeRegistUI = new SnakeRegistUI();
		}
		return snakeRegistUI;
	}
	
	/**
	 * 创建注册面板
	 * @param primaryStage
	 */
	public void openRegistPane(Stage primaryStage) {
		if(registStage != null) {
			registStage.show();
			return;
		}
		registStage = new Stage();
		
		registStage.setTitle("注册");
		registStage.setResizable(false);
		registStage.initOwner(primaryStage);
		Group root = addRegistStage(primaryStage);
		Scene scene = new Scene(root, 200, 200);
		scene.getStylesheets().add("css/login.css");
		registStage.setScene(scene);
		registStage.show();
	}

	/**
	 * 添加注册界面
	 * @param registStage
	 * @return
	 */
	public Group addRegistStage(Stage primaryStage) {
		//输入面板
		GridPane pane = new GridPane();
		Label label = new Label("用户名");
		TextField field = new TextField("输入用户名");
		Label nickname = new Label("昵称");
		TextField nicknameField = new TextField("输入昵称");
		Label psdLabel = new Label("密码");
		PasswordField psdField = new PasswordField();
		Label rePsdLabel = new Label("确认密码");
		PasswordField rePsdField = new PasswordField();
		Button registBtn = new Button("确定");
		Button loginBtn = new Button("跳转登陆");
		
		//将控件加入面板
		pane.add(label, 0, 0);
		pane.add(field, 1, 0);
		pane.add(nickname, 0, 1);
		pane.add(nicknameField, 1, 1);
		pane.add(psdLabel, 0, 2);
		pane.add(psdField, 1, 2);
		pane.add(rePsdLabel, 0, 3);
		pane.add(rePsdField, 1, 3);
		/*
		 * pane.addRow(2,loginBtn, registBtn); pane.
		 */
		// pane.add(registBtn, 1, 2);
		pane.setVgap(10);
		pane.getStyleClass().add("grid");

		addListener(registStage, primaryStage, loginBtn,registBtn);
		
		// 按钮面板
		FlowPane pane2 = new FlowPane();
		pane2.getChildren().addAll(registBtn, loginBtn);
		pane2.setLayoutX(60);
		pane2.setLayoutY(150);
		pane2.setHgap(5);

		// 提示面板
		FlowPane pane3 = new FlowPane();
		pane3.setLayoutX(50);
		pane3.setLayoutY(175);
		pane3.setHgap(5);
		// pane3.getStyleClass().add("prompt");
		Label promptLabel = new Label();
		promptLabel.getStyleClass().add("prompt");
		pane3.getChildren().add(promptLabel);
		
		//添加监听
		
		//添加到组
		Group root = new Group();
		root.getChildren().add(pane);
		root.getChildren().add(pane2);
		root.getChildren().add(pane3);
		
		return root;
	}

	/**
	 * 添加按钮监听
	 * @param registStage
	 * @param primaryStage
	 * @param loginBtn
	 * @param registBtn
	 */
	private void addListener(Stage registStage, Stage primaryStage, Button loginBtn, Button registBtn) {
		loginBtn.setOnMouseClicked(event ->{
			MouseButton button = event.getButton();
			if(MouseButton.PRIMARY.equals(button)){
				registStage.hide();
				SnakeLoginUI.getIns().openLoginPane(primaryStage);
			}
		});
		
	}

}
