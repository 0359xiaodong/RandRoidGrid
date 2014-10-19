/* Random grid layout
 * ==================================================
 * By: Adham M. Enaya
 * Email: a.it@hotmail.com
 */
package com.adhamenaya.androidrandomgrid;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

 
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create instant of the RandoGrid
		AndroidRandomGrid mRandomGrid = new AndroidRandomGrid();

		// Get the reference to the layout that will display the RandomGrid
		LinearLayout mLayout = (LinearLayout) findViewById(R.id.lytBoard);

		// Create static list of objects to display
		ArrayList<String> mValues = new ArrayList<String>();

		for (int i = 0; i < 20; i++) {
			mValues.add(String.valueOf(i + 1));
		}

		// Set the data to the adapter
		MyAdapter mAdapater = new MyAdapter(getApplicationContext(), mValues);

		// Set the adapter to the layout
		mRandomGrid.setAdapter(mAdapater, mValues);

		// Display the layout after setting the data
		mRandomGrid.show(mLayout, this);

	}
}
