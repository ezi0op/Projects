package controller;

import java.util.Comparator;

import model.Player;

public class MyWicketDescComparator implements Comparator<Player>{

	@Override
	public int compare(Player o1, Player o2) {
		// TODO Auto-generated method stub
		return o2.getWickets()-o1.getWickets();
	}

}
