package edu.rosehulman.campusgolf;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HoleTitleAdapter extends ArrayAdapter<Integer> {

	public HoleTitleAdapter(Context context, int textViewResourceId, Integer[] objects) {
		super(context, textViewResourceId, objects);
	}

	// TODO: Part C) Override getView
	// - Call super.getView(position, convertView, parent) to get the text view with text (I called mine holeTitleTextView instead of just view)
	// - Change the text to be "Hole #" + (position+1) + " - Par " + getItem(position)
	// return holeTitleTextView;
}
