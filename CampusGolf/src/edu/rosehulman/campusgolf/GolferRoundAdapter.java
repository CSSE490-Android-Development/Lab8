package edu.rosehulman.campusgolf;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

public class GolferRoundAdapter extends ArrayAdapter<GolferRound> {
	
	ScoreCardActivity mContext;
	
	public GolferRoundAdapter(ScoreCardActivity scoreCardContext, int resource, int textViewResourceId, ArrayList<GolferRound> golferRounds) {
		super(scoreCardContext, resource, textViewResourceId, golferRounds);
		mContext = scoreCardContext;
		
	}
	
	// TODO: Override getView
	// Part B) Easy part - The two text views
	// - Make a pointer (reference) for the GolferRoundView (called mine roundView)
	// - Either create a new GolferRoundView or cast the convert view
	// - getPlayerNameTextView and set the text to getItem(position).getName()
	// - getScoreTextView and set the text to ""+getItem(position).getRoundOffPar()
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		GolferRoundView roundView = null;
		if (convertView == null) {
			roundView = new GolferRoundView(mContext);
		} else {
			roundView = (GolferRoundView) convertView;
		}
		roundView.getPlayerNameTextView().setText(getItem(position).getName());
		roundView.getScoreTextView().setText(getItem(position).getRoundOffPar(mContext) + "");
		return roundView;
	}
	// Part D) Hard part - The GridView of hole display scores
	// - Get a reference to the GridView for this row using the GolferRoundView's getHoleScoresGridView()
	// - Get the data for the HoleDisplayAdapter using the getRoundHoleArray() method on this row's GolferRound (get the GolferRound using getItem(position) )
	// - Create a HoleDisplayAdapter using that Hole array and the hole_display_text_view resource
	// - Set the adapter on roundView.getHoleScoresGridView() to your HoleDisplayAdapter
	// Part D) Modify the GridView and make it clickable
	// - Add this code to allow the ListView OnItemClickListener to still fire in addition to the GridView listener
	// roundView.getHoleScoresGridView().setFocusable(false);
	// roundView.getHoleScoresGridView().setFocusableInTouchMode(false);
	// - Add this code to have the GridView call pressedPlayer as well.
	// final int listPosition = position;
	// roundView.getHoleScoresGridView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
	// @Override
	// public void onItemClick(AdapterView<?> parent, View view, int gridPosition, long id) {
	// mScoreCard.pressedPlayer(listPosition);
	// }
	// });
	
	// Part B) return roundView;
}
