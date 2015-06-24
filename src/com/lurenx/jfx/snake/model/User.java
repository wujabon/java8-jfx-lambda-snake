package com.lurenx.jfx.snake.model;
/**
 * ”√ªß
 * @author wujabon
 *
 */
public class User {
	private long id;
	private String username;
	private String nickname;
	private String password;
	private int loginTime;
	private int totalScore;
	private int todayScore;
	private int registTime;
	private int level;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(int loginTime) {
		this.loginTime = loginTime;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public int getTodayScore() {
		return todayScore;
	}
	public void setTodayScore(int todayScore) {
		this.todayScore = todayScore;
	}
	public int getRegistTime() {
		return registTime;
	}
	public void setRegistTime(int registTime) {
		this.registTime = registTime;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

}
