package edu.rosehulman.campusgolf;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.Toast;

public class ScoreCardActivity extends Activity {

	// TODO: Part C) Create a member variable for the hole title Gallery (called mine mHoleTitleGallery)
	private Gallery mHoleTitleGallery;
	// TODO: Part B) Create a member variable for the GolferRoundAdapter (called mine mGolferRoundsAdapter)
	private GolferRoundAdapter mGolferRoundAdapter;
	private ArrayList<GolferRound> mGolferRounds = new ArrayList<GolferRound>();
	private static final int REQUEST_CODE_ENTER_STROKES = 0;
	public static final String KEY_PLAYER_NAME = "KEY_PLAYER_NAME";
	public static final String KEY_CURRENT_HOLE_PAR = "KEY_CURRENT_HOLE_PAR";
	public static final String KEY_PLAYER_SCORE_PRESSED = "KEY_PLAYER_SCORE_PRESSED";
	private int mStrokeEntryForPosition = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_card_activity);

		Intent passedIn = getIntent();
		String[] golferNames = passedIn.getStringArrayExtra(EnterGolferNamesActivity.KEY_GOLFER_NAMES);
		if (golferNames == null) {
			golferNames = new String[3];
			golferNames[0] = "Dave";
			golferNames[1] = "Bob";
			golferNames[2] = "Rich";
		}
		int[] coursePars = passedIn.getIntArrayExtra(EnterGolferNamesActivity.KEY_COURSE_PARS);
		if (coursePars == null) {
			int[] fakePars = {3,4,5,3,4,5,3,4,5,4,4,4,4,4,4,4,4,4};
			coursePars = fakePars;
		}
		// Create the GolferRounds using the names and pars
		for(int i=0 ; i<golferNames.length ; i++) {
			this.mGolferRounds.add(new GolferRound(golferNames[i], coursePars));
		}
		addFakeScores();  // TODO: Part D) Remove after testing is complete

		// TODO: Part B) Create a new GolferRoundAdapter using the golfer_round_view resource, player_name_text_view text view id, and the mGolferRounds ArrayList
		// Set that adapter to the mGolferRoundsAdapter member variable
		mGolferRoundAdapter = new GolferRoundAdapter(this, R.layout.golfer_round_view, R.id.player_name_text_view, mGolferRounds);

		// TODO: Part B) Capture the ListView
		final ListView GolfListView = (ListView) findViewById(R.id.golfer_rounds_list_view);
		
		// TODO: Part B) Set the adapter for the ListView as the mGolferRoundsAdapter
		GolfListView.setAdapter(mGolferRoundAdapter);
		
		// TODO: Part B) setOnItemClickListener to call the function pressedPlayer(position)
		GolfListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				pressedPlayer(position);
			}
		});

		// Convert the coursePars int[] to a Integer[] so it can be used with an ArrayAdapter
		Integer[] courseParsIntegers = new Integer[coursePars.length];
		for (int i=0 ; i<coursePars.length ; i++) {
			courseParsIntegers[i] = coursePars[i];
		}
		
		// TODO: Part C) Capture the hole_title_gallery and set it to the mHoleTitleGallery member variable
		mHoleTitleGallery = (Gallery) findViewById(R.id.hole_title_gallery);
		
		// TODO: Part C) Create a new HoleTitleAdapter using the hole_title_text_view resource and the courseParsIntegers Integer[]
		final HoleTitleAdapter holeAdapter = new HoleTitleAdapter(this, R.layout.hole_title_text_view, courseParsIntegers);
		
		// TODO: Part C) setAdapter for the mHoleTitleGallery to your HoleTitleAdapter
		mHoleTitleGallery.setAdapter(holeAdapter);
		
		// TODO: Part C) For the Hole Title Gallery setOnItemSelectedListener
		mHoleTitleGallery.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			// TODO: Part C) onItemSelected should display a Toast saying "Moved to hole " + (position+1)
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(ScoreCardActivity.this, "Moved to hole " + (position + 1), Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		// onNothingSelected can do nothing

		
		// TODO: Part D) onItemSelected needs to call ScoreCardActivity.this.mGolferRoundsAdapter.notifyDataSetChanged()
	}

	private void addFakeScores() {
		for (int golferPosition=0 ; golferPosition<this.mGolferRounds.size() ; golferPosition++) {
			for (int hole = 9 ; hole<GolferRound.NUM_HOLES ; hole++) {
				this.mGolferRounds.get(golferPosition).setHoleStrokes(hole, golferPosition+3);	
			}
		}
	}

	public int getCurrentHole() {
	// Part C) Use the mHoleTitleGallery to return the selected item position
		return mHoleTitleGallery.getSelectedItemPosition();
	}

	public void pressedPlayer(int playerPosition) {
		
		//Toast.makeText(this, "Player (" + playerPosition + ") " + mGolferRounds.get(playerPosition).getName() + " On Hole: " + getCurrentHole(),Toast.LENGTH_SHORT).show();
		// Part C) Change to a different toast then to enterScoreForPlayer()
		//Toast.makeText(this, "Player " + playerPosition+ " On Hole " + getCurrentHole(),Toast.LENGTH_SHORT).show();
		// enterScoreForPlayer(playerPosition);
		// Note: This was intentionally decoupled just in case pressedPlayer every wanted to do more than just call enterScoreForPlayer
		enterScoreForPlayer(playerPosition);
	}

	// TODO: Part C) Uncomment when you are ready to enter scores
	public void enterScoreForPlayer(int playerPosition) {
		int currentHole = this.getCurrentHole();
		this.mStrokeEntryForPosition = playerPosition;
		Intent enterPlayerStrokesForHoleIntent = new Intent(this, StrokeEntryActivity.class);
		enterPlayerStrokesForHoleIntent.putExtra(KEY_PLAYER_NAME, mGolferRounds.get(playerPosition).toString());
		enterPlayerStrokesForHoleIntent.putExtra(KEY_CURRENT_HOLE_PAR, mGolferRounds.get(playerPosition).getHolePar(currentHole));
		startActivityForResult(enterPlayerStrokesForHoleIntent, REQUEST_CODE_ENTER_STROKES);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case REQUEST_CODE_ENTER_STROKES:
			if ( resultCode == RESULT_OK) {
				int strokesPressed = data.getIntExtra(KEY_PLAYER_SCORE_PRESSED, 0);
				int currentHole = this.getCurrentHole();
				mGolferRounds.get(mStrokeEntryForPosition).setHoleStrokes(currentHole, strokesPressed);
				ScoreCardActivity.this.mGolferRoundAdapter.notifyDataSetChanged();
				//Log.d("OP", "Result ok");
			} else {
				Log.d("OP", "Result not ok");
			}		
			break;
		default:
			Log.d("OP", "Unknown activity");
			break;
		}
	}
}
