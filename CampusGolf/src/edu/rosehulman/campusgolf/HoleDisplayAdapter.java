package edu.rosehulman.campusgolf;

import android.widget.ArrayAdapter;

public class HoleDisplayAdapter extends ArrayAdapter<Hole> {

	private ScoreCardActivity mScoreCard;

	public HoleDisplayAdapter(ScoreCardActivity scoreCardContext, int textViewResourceId, Hole[] holes) {
		super(scoreCardContext, textViewResourceId, holes);
		this.mScoreCard = scoreCardContext;
	}
	
	// TODO: Override getView
	// - Call super.getView(position, convertView, parent) to get the text view with text (I called mine holeTextView instead of just view)
	// - Get the Hole via this.getItem(position);
	// - Get the strokes and offPar values from the hole
	// - Set the background of the text view and color as follows:
	//  If strokes == 0
	//     Set the text to - instead of 0
	//     If position == this.mScoreCard.getCurrentHole()
	//        Set the text background to R.drawable.selected_32
	//  If strokes != 0
	//     If offPar value < -1
	//        background to R.drawable.eagle_32
	//        text color RED
	//     If offPar value == -1
	//        background to R.drawable.birdie_32
	//        text color RED
	//     If offPar value == 0
	//        background to R.drawable.par_32
	//        text color BLACK
	//     If offPar value == 1
	//        background to R.drawable.bogey_32
	//        text color BLUE
	//     If offPar value > 1
	//        background to R.drawable.double_32
	//        text color BLUE
	
	//	return holeTextView;
}
