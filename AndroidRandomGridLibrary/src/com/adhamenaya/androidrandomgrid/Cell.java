/* Random grid layout
 * ==================================================
 * By: Adham M. Enaya
 * Email: a.it@hotmail.com
 */
package com.adhamenaya.androidrandomgrid;

public class Cell {

	public int id;
	public int coorX;
	public int coorY;
	public double x1; // left
	public double y1; // top
	public double x2; // right
	public double y2; // bottom
	public boolean isOccupied = false;
	public CellView cv;

	void setCellView(CellView cv) {
		this.cv = cv;
	}
}
