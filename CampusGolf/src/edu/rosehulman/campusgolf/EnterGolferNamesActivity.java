package edu.rosehulman.campusgolf;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class EnterGolferNamesActivity extends Activity {

	private ArrayList<String> mGolferNames = new ArrayList<String>();
	public static final String KEY_GOLFER_NAMES = "KEY_GOLFER_NAMES";
	public static final String KEY_COURSE_PARS = "KEY_COURSE_PARS";
	private int[] mCoursePars = {3,4,5,3,4,5,3,4,5,4,4,4,4,4,4,4,4,4};  // Hardcoded for this lab
	private ArrayAdapter<String> mGolferNamesAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enter_golfer_names_activity);

		// TODO: Part A) Capture the ListView
		final ListView golferNameListView = (ListView) findViewById(R.id.golfer_names_list_view);
		final EditText golferNameEditText = (EditText) findViewById(R.id.golfer_name_edit_text);
		final Button beginRoundButton = (Button) findViewById(R.id.begin_round_button);		
		// TODO: Part A) Create an ArrayAdapter for the mGolferNames ArrayList using the golfer_names_text_view resources
		mGolferNamesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mGolferNames);
		// TODO: Part A) Bind the adapter to the list view
		golferNameListView.setAdapter(mGolferNamesAdapter);

		golferNameEditText.setOnKeyListener(new View.OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
						// Perform action on key press
						EnterGolferNamesActivity.this.mGolferNames.add(golferNameEditText.getText().toString());
						// TODO: Part A) Notify the adapter that the data set changed
						mGolferNamesAdapter.notifyDataSetChanged();
						golferNameEditText.setText("");
						// Hide the soft keyboard after the name (optional)
						InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(golferNameEditText.getWindowToken(), 0);
						return true;	
					}
				}
				return false;
			}
		});
		beginRoundButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Start a new activity.  Pass in the array list of names but as a String array not an ArrayList
				Intent scoreCardIntent = new Intent(EnterGolferNamesActivity.this, ScoreCardActivity.class);
				String[] golferNamesArray = new String[EnterGolferNamesActivity.this.mGolferNames.size()];
				EnterGolferNamesActivity.this.mGolferNames.toArray(golferNamesArray);
				scoreCardIntent.putExtra(KEY_GOLFER_NAMES, golferNamesArray);
				scoreCardIntent.putExtra(KEY_COURSE_PARS, EnterGolferNamesActivity.this.mCoursePars);
				startActivity(scoreCardIntent);
			}
		});
	}
}