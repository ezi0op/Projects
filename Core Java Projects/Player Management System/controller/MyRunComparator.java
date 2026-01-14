package controller;

import java.util.Comparator;

import model.Player;

public class MyRunComparator implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		// TODO Auto-generated method stub
		return o1.getRuns()-o2.getRuns();
	}

	
}
