package edu.rosehulman.campusgolf;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StrokeEntryHoleAdapter extends ArrayAdapter<Hole> {

	private StrokeEntryActivity mEnterScoresActivity;

	public StrokeEntryHoleAdapter(StrokeEntryActivity enterScoresActivity, int resource, Hole[] holes) {
		super(enterScoresActivity, resource, holes);
		this.mEnterScoresActivity = enterScoresActivity;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView holeTextView = (TextView) super.getView(position, convertView, parent);
		Hole hole = this.getItem(position);
		int strokes = hole.getPlayerStrokes();
		if (strokes >= 9) {
			holeTextView.setText("9+");
		}

		Resources res = this.mEnterScoresActivity.getResources();
		Drawable backgroundImage = res.getDrawable(R.drawable.par_64);

		int offPar = hole.getOffPar();
		if (offPar < -2 ) {
			holeTextView.setTextColor(Color.RED);
		} else if (offPar == -2 ) {
			backgroundImage = res.getDrawable(R.drawable.eagle_64);
			holeTextView.setTextColor(Color.RED);
		} else if (offPar == -1 ) {
			backgroundImage = res.getDrawable(R.drawable.birdie_64);
			holeTextView.setTextColor(Color.RED);
		} else if (offPar == 0 ) {
			backgroundImage = res.getDrawable(R.drawable.par_64);
			holeTextView.setTextColor(Color.BLACK);
		} else if (offPar == 1 ) {
			backgroundImage = res.getDrawable(R.drawable.bogey_64);
			holeTextView.setTextColor(Color.BLUE);
		} else if (offPar == 2 ) {
			backgroundImage = res.getDrawable(R.drawable.double_64);
			holeTextView.setTextColor(Color.BLUE);
		} else {
			holeTextView.setTextColor(Color.BLUE);
		}
		holeTextView.setBackgroundDrawable(backgroundImage);
		return holeTextView;
	}
}
