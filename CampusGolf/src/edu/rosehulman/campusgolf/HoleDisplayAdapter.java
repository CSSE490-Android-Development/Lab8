package edu.rosehulman.campusgolf;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HoleDisplayAdapter extends ArrayAdapter<Hole> {
	
	private ScoreCardActivity mScoreCard;
	
	public HoleDisplayAdapter(ScoreCardActivity scoreCardContext, int textViewResourceId, Hole[] holes) {
		super(scoreCardContext, textViewResourceId, holes);
		this.mScoreCard = scoreCardContext;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView HoleView = (TextView) super.getView(position, convertView, parent);
		Hole current = getItem(position);
		int strokes = current.getPlayerStrokes();
		int offPar = current.getOffPar();
		int par = current.getPar();
		if (strokes == 0) {
			HoleView.setText("-");
			if (mScoreCard.getCurrentHole() == position) {
				HoleView.setBackgroundDrawable(mScoreCard.getResources().getDrawable(R.drawable.selected_32));
			}
		} else if (strokes - par <= -2) {
			HoleView.setBackgroundDrawable(mScoreCard.getResources().getDrawable(R.drawable.eagle_32));
			HoleView.setTextColor(Color.RED);
		} else if (strokes - par == -1) {
			HoleView.setBackgroundDrawable(mScoreCard.getResources().getDrawable(R.drawable.birdie_32));
			HoleView.setTextColor(Color.RED);
		} else if (strokes - par == 0) {
			HoleView.setBackgroundDrawable(mScoreCard.getResources().getDrawable(R.drawable.par_32));
			HoleView.setTextColor(Color.BLACK);
		} else if (strokes - par == 1) {
			HoleView.setBackgroundDrawable(mScoreCard.getResources().getDrawable(R.drawable.bogey_32));
			HoleView.setTextColor(Color.BLUE);
		} else if (strokes - par >= 2) {
			HoleView.setBackgroundDrawable(mScoreCard.getResources().getDrawable(R.drawable.double_32));
			HoleView.setTextColor(Color.BLUE);
		}
		return HoleView;
	}
	// TODO: Override getView
	// - Call super.getView(position, convertView, parent) to get the text view with text (I called mine holeTextView instead of just view)
	// - Get the Hole via this.getItem(position);
	// - Get the strokes and offPar values from the hole
	// - Set the background of the text view and color as follows:
	// If strokes == 0
	// Set the text to - instead of 0
	// If position == this.mScoreCard.getCurrentHole()
	// Set the text background to R.drawable.selected_32
	// If strokes != 0
	// If offPar value < -1
	// background to R.drawable.eagle_32
	// text color RED
	// If offPar value == -1
	// background to R.drawable.birdie_32
	// text color RED
	// If offPar value == 0
	// background to R.drawable.par_32
	// text color BLACK
	// If offPar value == 1
	// background to R.drawable.bogey_32
	// text color BLUE
	// If offPar value > 1
	// background to R.drawable.double_32
	// text color BLUE
	
	// return holeTextView;
}
