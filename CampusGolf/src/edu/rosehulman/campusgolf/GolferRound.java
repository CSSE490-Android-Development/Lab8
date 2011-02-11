package edu.rosehulman.campusgolf;

public class GolferRound {

	public static final int NUM_HOLES = 18;
	private String mGolferName;
	private Hole[] mPlayerHoles;

	public GolferRound(String name, int[] coursePars) {
		this.mGolferName = name;
		if (coursePars == null) {
			int[] defaultPars = {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4};
			coursePars = defaultPars;
		}
		this.mPlayerHoles = new Hole[NUM_HOLES];
		for (int i=0 ; i<NUM_HOLES ; i++) {
			this.mPlayerHoles[i] = new Hole(coursePars[i]);
		}
	}
	public void setHoleStrokes(int hole, int strokes) {
		this.mPlayerHoles[hole].setPlayerStrokes(strokes);
	}
	public int getHoleOffPar(int hole) {
		return this.mPlayerHoles[hole].getOffPar();
	}
	public int getHoleStrokes(int hole) {
		return this.mPlayerHoles[hole].getPlayerStrokes();
	}
	public int getHolePar(int hole) {
		return this.mPlayerHoles[hole].getPar();
	}
	public int getRoundStrokes() {
		return getStrokeTotalForHoles(0, 18);
	}
	public int getRoundOffPar() {
		return getOffParTotalForHoles(0, 18);
	}
	public int getFrontNineStrokes() {
		return getStrokeTotalForHoles(0, 9);
	}
	public int getFronNineOffPar() {
		return getOffParTotalForHoles(0, 9);
	}
	public int getBackNineStrokes() {
		return getStrokeTotalForHoles(9, 18);
	}
	public int getBackNineOffPar() {
		return getOffParTotalForHoles(9, 18);
	}
	public int getOffParTotalForHoles(int startHole, int endHole) {  // Exclusive end value 
		int roundScore = 0;
		for (int hole = startHole; hole<endHole; hole++) {
			if (this.getHoleStrokes(hole) != 0) {
				roundScore += this.getHoleOffPar(hole);
			}
		}
		return roundScore;
	}
	public int getStrokeTotalForHoles(int startHole, int endHole) {  // Exclusive end value
		int strokeTotal = 0;
		for (int hole = startHole; hole<endHole; hole++) {
			if (this.getHoleStrokes(hole) != 0) {
				strokeTotal += this.getHoleStrokes(hole);
			}
		}
		return strokeTotal;
	}
	public Hole[] getRoundHoleArray() {
		return this.mPlayerHoles;
	}
	public String getName() {
		return this.mGolferName;
	}
	@Override
	public String toString() {
		return this.mGolferName;
	}
}
