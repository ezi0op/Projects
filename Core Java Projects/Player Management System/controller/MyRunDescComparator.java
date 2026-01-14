package controller;

import java.util.Comparator;

import model.Player;

public class MyRunDescComparator implements Comparator<Player> {
	@Override
	public int compare(Player o1, Player o2) {
		// TODO Auto-generated method stub
		return o2.getRuns() - o1.getRuns();
	}
}
