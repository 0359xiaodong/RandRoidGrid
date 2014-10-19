/* Random grid layout
 * ==================================================
 * By: Adham M. Enaya
 * Email: a.it@hotmail.com
 */
package com.adhamenaya.androidrandomgrid;

import java.util.ArrayList;
import java.util.Arrays;

public class BoxesOrder {

	String pieces[] = { "small_square", "big_square", "vertical", "horizontal" };
	String board[] = { "", "", "", "", "", "", "", "" };

	ArrayList<ArrayList<ArrayList<Integer>>> boxesAll = new ArrayList<ArrayList<ArrayList<Integer>>>();
	int N = 0;

	static String result = "";

	public BoxesOrder() {

	}

	public ArrayList<ArrayList<Integer>> getPattern() {
		run(0, board);
		ArrayList<ArrayList<Integer>> list = getRandomOrder();
		return list;

	}

	private boolean isPossible(String piece, int spot, String[] board) {
		if (piece.equals("small_square")) {
			return board[spot].equals("");
		}
		if (piece.equals("big_square")) {
			return spot < 3 && board[spot].equals("")
					&& board[spot + 1].equals("") && board[spot + 4].equals("")
					&& board[spot + 5].equals("");
		}
		if (piece.equals("vertical")) {
			return spot < 4 && board[spot].equals("")
					&& board[spot + 4].equals("");
		}
		if (piece.equals("horizontal")) {
			return spot % 4 < 3 && board[spot].equals("")
					&& board[spot + 1].equals("");
		}
		return false;
	}

	private String[] insert(String piece, int spot, String[] board2) {
		board = Arrays.copyOf(board2, board2.length);
		if (piece.equals("small_square")) {
			board[spot] = "o";
		}
		if (piece.equals("big_square")) {
			board[spot] = "#";
			board[spot + 1] = "#";
			board[spot + 4] = "#";
			board[spot + 5] = "#";

		}
		if (piece.equals("vertical")) {
			board[spot] = board[spot + 4] = "|";

		}
		if (piece.equals("horizontal")) {
			board[spot] = board[spot + 1] = "-";

		}
		return board;
	}

	public boolean isValid(String[] borad) {
		for (int i = 0; i < board.length; ++i) {
			if (board[i].equals("")) {
				return false;
			}
		}
		return true;
	}

	public void run(int spot, String[] board) {
		if (spot == 8) {
			if (isValid(board)) {
				N++;
				boxesAll.add(parse(board));
			}
			return;
		}

		for (int i = 0; i < pieces.length; ++i) {
			String piece = pieces[i];

			if (isPossible(piece, spot, board)) {
				run(spot + 1, insert(piece, spot, board));
			}
		}

		if (!board[spot].equals("")) {
			run(spot + 1, board);
		}
	}

	public String join(ArrayList<Integer> list) {

		String str = "";
		for (int i : list) {
			str += i;
		}

		return str;
	}

	private ArrayList<ArrayList<Integer>> getRandomOrder() {

		int n = (int) (Math.random() * 89);
		return boxesAll.get(n);
	}

	private ArrayList<ArrayList<Integer>> parse(String[] board) {
		boolean[] occupy = { false, false, false, false, false, false, false,
				false };
		ArrayList<ArrayList<Integer>> boxes = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < board.length; i++) {
			if (!occupy[i]) {
				if (board[i].equals("o")) {
					ArrayList<Integer> cell = new ArrayList<Integer>();
					cell.add(i);
					boxes.add(cell);
					occupy[i] = true;
				} else if (board[i].equals("#")) {
					ArrayList<Integer> cell = new ArrayList<Integer>();
					occupy[i] = true;
					occupy[i + 1] = true;
					occupy[i + 4] = true;
					occupy[i + 5] = true;
					cell.add(i);
					cell.add(i + 1);
					cell.add(i + 4);
					cell.add(i + 5);
					boxes.add(cell);
				}
				if (board[i].equals("|")) {
					ArrayList<Integer> cell = new ArrayList<Integer>();
					occupy[i] = true;
					occupy[i + 4] = true;
					cell.add(i);
					cell.add(i + 4);
					boxes.add(cell);
				}
				if (board[i].equals("-")) {
					ArrayList<Integer> cell = new ArrayList<Integer>();
					occupy[i] = true;
					occupy[i + 1] = true;
					cell.add(i);
					cell.add(i + 1);
					boxes.add(cell);
				}
			}
		}
		return boxes;

	}
	
}
