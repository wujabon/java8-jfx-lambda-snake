package com.lurenx.jfx.snake.model;

public class Location {
	//x×ø±ê
	private int posx;
	//y×ø±ê
	private int posy;
	public Location(){}
	public Location(int posx, int posy) {
		this.posx = posx;
		this.posy = posy;
	}
	public int getPosx() {
		return posx;
	}
	public void setPosx(int posx) {
		this.posx = posx;
	}
	public int getPosy() {
		return posy;
	}
	public void setPosy(int posy) {
		this.posy = posy;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + posx;
		result = prime * result + posy;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (posx != other.posx)
			return false;
		if (posy != other.posy)
			return false;
		return true;
	}

}
