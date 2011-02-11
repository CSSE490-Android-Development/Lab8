package edu.rosehulman.campusgolf;

public class Hole {

	private int mPar;
	private int mPlayerStrokes;
	public Hole() {
		this(0,0);
	}
	public Hole(int par) {
		this(par,0);
	}
	public Hole(int par, int playerStrokes) {
		mPar = par;
		mPlayerStrokes = playerStrokes;
	}
	public void setPar(int par) {
		mPar = par;
	}
	public void setPlayerStrokes(int playerStrokes) {
		mPlayerStrokes = playerStrokes;
	}
	public int getPar() {
		return mPar;
	}
	public int getPlayerStrokes() {
		return mPlayerStrokes;
	}
	public int getOffPar() {
		return mPlayerStrokes - mPar;
	}
	@Override
	public String toString() {
		return "" + mPlayerStrokes;
	}
}
