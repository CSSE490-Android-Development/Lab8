package edu.rosehulman.campusgolf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

public class StrokeEntryActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stroke_entry_activity);
		
		Intent passedIntent = getIntent();
		String playerName = passedIntent.getStringExtra(ScoreCardActivity.KEY_PLAYER_NAME);
		int holePar = passedIntent.getIntExtra(ScoreCardActivity.KEY_CURRENT_HOLE_PAR, 4);

		TextView titleTextView = (TextView) findViewById(R.id.title_text_view);
		titleTextView.setText( getString(R.string.enter_score_for,playerName));
		
		// Make array of holes 1 through 9
		Hole[] possibleScores = new Hole[9];
		for(int i=0 ; i<9 ; i++) {
			possibleScores[i] = new Hole(holePar, i+1);
		}
		
		final StrokeEntryHoleAdapter holeScoreEntryAdapter = new StrokeEntryHoleAdapter(this, R.layout.stroke_entry_hole_text_view, possibleScores);
		final GridView enterHoleStrokesGridView = (GridView) findViewById(R.id.enter_hole_strokes_grid_view);
		enterHoleStrokesGridView.setAdapter(holeScoreEntryAdapter);
		enterHoleStrokesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Toast.makeText(EnterPlayerStrokesForHoleActivity.this, "Clicked" + (position+1), Toast.LENGTH_SHORT).show();
				Intent dataComingBack = new Intent();
				dataComingBack.putExtra(ScoreCardActivity.KEY_PLAYER_SCORE_PRESSED, holeScoreEntryAdapter.getItem(position).getPlayerStrokes());
				StrokeEntryActivity.this.setResult(RESULT_OK, dataComingBack);
				finish();
			}
		});
	}	
}
