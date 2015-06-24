package com.lurenx.jfx.snake.ui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class PromptPane {
	private Stage promptStage;
	private String title;
	private String prompt;
	public PromptPane(String title, String prompt){
		this.title = title;
		this.prompt = prompt;
	}

	/**
	 * ��ʾ����
	 * @param primaryStage
	 */
	public void actionClose(Stage primaryStage){
		if(promptStage != null) {
			promptStage.show();
			return;
		}
		promptStage = new Stage();
		
		promptStage.setTitle(title);
		promptStage.setResizable(false);
		promptStage.initOwner(primaryStage);
		Group root = addPromptStage(primaryStage);
		Scene scene = new Scene(root, 300, 80);
		scene.getStylesheets().add("css/login.css");
		promptStage.setScene(scene);
		promptStage.show();
	}

	/**
	 * ��ʾ
	 * @param promptStage2
	 * @param primaryStage
	 * @return
	 */
	private Group addPromptStage(Stage primaryStage) {
		Group root = new Group();
		FlowPane labelPane = new FlowPane();
		labelPane.setLayoutX(20);
		labelPane.setLayoutY(10);
		Label label = new Label(prompt);
		labelPane.getChildren().add(label);
		
		FlowPane btnPane = new FlowPane();
		Button sureBtn = new Button("ȷ��");
		Button cancelBtn = new Button("ȡ��");
		
		btnPane.setLayoutX(105);
		btnPane.setLayoutY(50);
		btnPane.setHgap(10);
		btnPane.getChildren().add(sureBtn);
		btnPane.getChildren().add(cancelBtn);
		root.getChildren().add(labelPane);
		root.getChildren().add(btnPane);
		addBtnListener(sureBtn, cancelBtn);
		return root;
	}

	/**
	 * ��Ӱ�ť����
	 * @param sureBtn
	 * @param cancelBtn
	 */
	private void addBtnListener(Button sureBtn, Button cancelBtn) {
		sureBtn.setOnMouseClicked(event -> {
			MouseButton button = event.getButton();
			if(MouseButton.PRIMARY.equals(button)) {
				System.exit(0);
			}
		});
		cancelBtn.setOnMouseClicked(event -> {
			MouseButton button = event.getButton();
			if(MouseButton.PRIMARY.equals(button)) {
				promptStage.close();
			}
		});
	}
}
