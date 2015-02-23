package com.kanawish.codelab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kanawish.codelab.model.User;

import java.util.List;

class UsersAdapter extends ArrayAdapter<User> {

	public UsersAdapter(Context context, List<User> users) {
		super(context, 0, users);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Get the data item for this position
		User user = getItem(position);

		// Check if an existing view is being reused, otherwise inflate the view
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
		}

		// Lookup view for data population
		TextView firstName = (TextView) convertView.findViewById(android.R.id.text1);
		TextView lastName = (TextView) convertView.findViewById(android.R.id.text2);

		// Populate the data into the template view using the data object
		firstName.setText(user.getFirstName());
		lastName.setText(user.getLastName());

		// Return the completed view to render on screen
		return convertView;
	}
}