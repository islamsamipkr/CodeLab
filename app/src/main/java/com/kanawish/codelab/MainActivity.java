package com.kanawish.codelab;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * This is an example being built for Intro-to-Android CodeLabs
 *
 * TODO: I'm looking into using (this)[https://github.com/Abnaxos/pegdown-doclet] to format the javadoc
 *
 * It uses examples and tutorials from various sources.
 *
 * ## 1. Theming and retro-compatible Material design
 *
 * - [Android Dev post](http://android-developers.blogspot.ca/2014/10/appcompat-v21-material-design-for-pre.html)
 * - [Chris Banes](https://chris.banes.me/2014/10/17/appcompat-v21/)
 *
 *
 */
public class MainActivity extends ActionBarActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Assigning the toolbar to "ActionBar duty".
		Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
		setSupportActionBar(toolbar);

		Button button = (Button) findViewById(R.id.simpleButton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "simpleButton click listener was called.");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
