package edu.rosehulman.campusgolf;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GolferRoundView extends RelativeLayout {

	private TextView mPlayerNameTextView;
	private TextView mScoreTextView;
	private GridView mHoleScoresGridView;

	public GolferRoundView(Context context) {
		super(context);
		LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
		layoutInflater.inflate(R.layout.golfer_round_view, this);	
		this.mPlayerNameTextView = (TextView) findViewById(R.id.player_name_text_view);
		this.mScoreTextView = (TextView) findViewById(R.id.score_text_view);
		this.mHoleScoresGridView = (GridView) findViewById(R.id.hole_scores_grid_view);
	}

	public TextView getPlayerNameTextView() {
		return mPlayerNameTextView;
	}

	public void setPlayerNameTextView(TextView mPlayerNameTextView) {
		this.mPlayerNameTextView = mPlayerNameTextView;
	}

	public TextView getScoreTextView() {
		return mScoreTextView;
	}

	public void setScoreTextView(TextView mScoreTextView) {
		this.mScoreTextView = mScoreTextView;
	}

	public GridView getHoleScoresGridView() {
		return mHoleScoresGridView;
	}

	public void setHoleScoresGridView(GridView mHoleScoresGridView) {
		this.mHoleScoresGridView = mHoleScoresGridView;
	}
}
