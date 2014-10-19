/* Random grid layout
 * ==================================================
 * By: Adham M. Enaya
 * Email: a.it@hotmail.com
 */
package com.adhamenaya.androidrandomgrid;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;

public class AndroidRandomGrid {

	private ArrayList<Cell> cells = new ArrayList<Cell>();
	private BoxesOrder boxes = new BoxesOrder();
	private int noOfCells = 4;
	private double d = 0.0;
	private int counter = 0, coorX = 0, coorY = 0;
	private int filledCount = 0;
	private ArrayAdapter<String> ad;
	private List<String> data;
	private ArrayList<ArrayList<Integer>> patterns;

	public void setAdapter(ArrayAdapter<String> ad, ArrayList<String> data) {
		this.data = data;
		this.ad = ad;
	}

	public Cell getCell(double x, double y) {
		for (int i = 0; i < cells.size(); i++) {
			if (x > cells.get(i).x1 && y > cells.get(i).y1
					&& x < cells.get(i).x2 && y < cells.get(i).y2) {
				return cells.get(i);
			}
		}
		return null;
	}

	public int getCount() {
		if (patterns != null)
			return patterns.size();
		else
			return -1;
	}

	public double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	public void show(ViewGroup parent, Context cx) {
		do {
			FrameLayout f = new FrameLayout(cx);
			parent.addView(f);
			f.setPadding(1, 1, 1, 0);
			display(f, cx);
		} while (filledCount < data.size());
	}

	private void calculateAllCells(Context cx) {

		DisplayMetrics displaymetrics = new DisplayMetrics();
		((Activity) cx).getWindowManager().getDefaultDisplay()
				.getMetrics(displaymetrics);
		int width = displaymetrics.widthPixels;
		d = width / noOfCells;

		for (int y = 0; y < d * 2; y += d) { // h
			coorX = 0;

			for (int x = 0; x <= width - d; x += d) { // w

				Cell cell = new Cell();
				cell.x1 = x;
				cell.y1 = y;

				cell.x2 = x + d;
				cell.y2 = y + d;

				cell.id = counter;
				cell.coorX = coorX;
				cell.coorY = coorY;

				cells.add(cell);
				counter++;
				coorX++;

			}

			coorY++;
		}

	}

	private void display(FrameLayout lyt, Context cx) {
		counter = coorX = coorY = 0;

		calculateAllCells(cx);

		patterns = boxes.getPattern();

		ArrayList<Box> bx = new ArrayList<Box>();
		for (int i = 0; i < patterns.size(); i++) {

			ArrayList<Cell> boxCells = new ArrayList<Cell>();

			for (int j = 0; j < patterns.get(i).size(); j++) {
				Cell c = cells.get(patterns.get(i).get(j));
				boxCells.add(c);
			}
			bx.add(getBox(boxCells));

		}

		for (int i = 0; i < bx.size(); i++) {
			CellView cv = new CellView(cx);

			View v = ad.getView(filledCount, null, lyt);
			cv.addView(v);

			FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
					(int) bx.get(i).width, (int) bx.get(i).height);

			params.setMargins((int) bx.get(i).x1, (int) bx.get(i).y1, 0, 0);

			lyt.addView(cv, params);
			cells.get(i).setCellView(cv);
			filledCount++;
			if (filledCount >= data.size())
				return;
		}
		cells.clear();

	}

	private Box getBox(ArrayList<Cell> cells) {

		double maxX = 0;
		double maxY = 0;
		double minX = 10000;
		double minY = 10000;

		for (int i = 0; i < cells.size(); i++) {
			if (cells.get(i).x1 < minX)
				minX = cells.get(i).x1;
			if (cells.get(i).y1 < minY)
				minY = cells.get(i).y1;

			if (cells.get(i).x2 > maxX)
				maxX = cells.get(i).x2;
			if (cells.get(i).y2 > maxY)
				maxY = cells.get(i).y2;
		}

		Box bx = new Box();
		bx.x1 = minX;
		bx.y1 = minY;
		bx.x2 = maxX;
		bx.y2 = maxY;

		bx.width = bx.x2 - bx.x1;
		bx.height = bx.y2 - bx.y1;

		return bx;

	}

}
