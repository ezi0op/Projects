package controller;

import java.util.*;
import model.Player;

public class MyWicketComparator implements Comparator<Player>{
	@Override
	public int compare(Player o1, Player o2) {
		// TODO Auto-generated method stub
		return o1.getWickets()-o2.getWickets();
	}
}
