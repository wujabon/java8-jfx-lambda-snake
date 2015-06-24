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
/**
 * ̰���ǵ�½��
 * @author wujabon
 *
 */
public class SnakeLoginUI {
	private static SnakeLoginUI snakeLoginUI;
	private Stage loginStage;
	private SnakeLoginUI(){}
	
	public static SnakeLoginUI getIns() {
		if(snakeLoginUI == null) {
			snakeLoginUI = new SnakeLoginUI();
		}
		return snakeLoginUI;
	}

	/**
	 * �򿪵�½���
	 * @param primaryStage
	 */
	public void openLoginPane(Stage primaryStage) {
		if(loginStage != null) {
			loginStage.show();
			return;
		}
		loginStage = new Stage();
		
		loginStage.setTitle("��½");
		loginStage.setResizable(false);
		loginStage.initOwner(primaryStage);
		Group root = addLoginStage(loginStage, primaryStage);
		Scene scene = new Scene(root, 200, 150);
		scene.getStylesheets().add("css/login.css");
		loginStage.setScene(scene);
		loginStage.show();
		
	}

	/**
	 * ���ӵ�½���
	 * @return
	 */
	private Group addLoginStage(final Stage loginStage, final Stage primaryStage) {
		//�������
		GridPane pane = new GridPane();
		Label label = new Label("�û���");
		Label psdLabel = new Label("����");
		TextField field = new TextField();
		PasswordField psdField = new PasswordField();
		Button loginBtn = new Button("��½");
		Button registBtn = new Button("ע��");
		field.setEditable(true);
		pane.add(label, 0, 0);
		pane.add(field, 1, 0);
		pane.add(psdLabel, 0, 1);
		pane.add(psdField, 1, 1);
		/*pane.addRow(2,loginBtn, registBtn);
		pane.*/
		//pane.add(registBtn, 1, 2);
		pane.setVgap(15);
		pane.getStyleClass().add("grid");
		
		//��ť���
		FlowPane pane2 = new FlowPane();
		pane2.getChildren().addAll(loginBtn, registBtn);
		pane2.setLayoutX(60);
		pane2.setLayoutY(90);
		pane2.setHgap(5);
		
		//��ʾ���
		FlowPane pane3 = new FlowPane();
		pane3.setLayoutX(50);
		pane3.setLayoutY(120);
		pane3.setHgap(15);
		//pane3.getStyleClass().add("prompt");
		Label promptLabel = new Label();
		promptLabel.getStyleClass().add("prompt");
		pane3.getChildren().add(promptLabel);

		addListener(loginStage, primaryStage, registBtn, loginBtn, promptLabel);
		
		Group root = new Group();
		root.getChildren().add(pane);
		root.getChildren().add(pane2);
		root.getChildren().add(pane3);
		return root;
	}

	/**
	 * ��Ӱ�ť����
	 * @param loginStage ��½����
	 * @param primaryStage ������
	 * @param registBtn ע�ᰴť
	 * @param loginBtn ��½��ť
	 * @param promptLabel ��ʾ����
	 */
	private void addListener(final Stage loginStage, final Stage primaryStage,
			Button registBtn, Button loginBtn, Label promptLabel) {
		registBtn.setOnMouseClicked((event) -> {
			MouseButton button = event.getButton();
			if(MouseButton.PRIMARY.equals(button)){
				loginStage.hide();
				SnakeRegistUI.getIns().openRegistPane(primaryStage);
			}
		});
		loginBtn.setOnMouseClicked(event ->{
			MouseButton button = event.getButton();
			if(MouseButton.PRIMARY.equals(button)){
				
			}
		});
	}
}
