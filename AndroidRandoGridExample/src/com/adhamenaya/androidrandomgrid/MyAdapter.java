/* Random grid layout
 * ==================================================
 * By: Adham M. Enaya
 * Email: a.it@hotmail.com
 */
package com.adhamenaya.androidrandomgrid;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final ArrayList<String> values;

	public MyAdapter(Context context, ArrayList<String> values) {
		super(context, R.layout.row_item, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.row_item, parent, false);
		TextView tv = (TextView) rowView.findViewById(R.id.tvTitle);
		tv.setText(String.valueOf(values.get(position)));

		return rowView;
	}
}
