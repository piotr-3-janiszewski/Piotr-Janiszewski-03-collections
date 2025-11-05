package uj.wmii.pwj.collections;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DefaultBattleshipGenerator implements BattleshipGenerator {

	private static int randInt(int maxInt) {
		return ThreadLocalRandom.current().nextInt(maxInt);
	}

	private static class Vec {
		public int x;
		public int y;
		public Vec (int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Vec)) return false;
			Vec v = (Vec) o;
			return x == v.x && y == v.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

	}

	boolean surroundingsClear(List<Vec> spacesFilled, Vec toCheck) {
		boolean collides = false;

		for (int i = -1; i <= 1 && !collides; i++)
			for (int j = -1; j <= 1 && !collides; j++)
				collides |= spacesFilled.contains(new Vec(toCheck.x + i, toCheck.y + j));

		return !collides;
	}
	
	public String generateMap() {
		List<Vec> spacesFilled = new ArrayList<Vec>();

		int[] ships_by_masts = new int[]{4, 3, 2, 1};

		for (int i = 0; i < ships_by_masts.length; i++) {
			while (ships_by_masts[i] > 0) {
				int masts = i + 1;
				
				List<Vec> nextBoat = new ArrayList<Vec>();
				List<Vec> vicinity = new ArrayList<Vec>();
				vicinity.add(new Vec(randInt(10), randInt(10)));
				
				while (vicinity.size() > 0 && masts > nextBoat.size()) {
					Vec pointToCheck = vicinity.remove(randInt(vicinity.size()));
					
					if (
					!nextBoat.contains(pointToCheck) && 
					pointToCheck.x >= 0 && 
					pointToCheck.x < 10 && 
					pointToCheck.y >= 0 && 
					pointToCheck.y < 10 && 
					surroundingsClear(spacesFilled, pointToCheck)
					) {
						nextBoat.add(pointToCheck);
						
						for (int dx = -1; dx <= 1; dx++)
							for (int dy = -1; dy <= 1; dy++)
								if ((dx != 0 || dy != 0) && (dx == 0 || dy == 0))
									vicinity.add(new Vec(pointToCheck.x + dx, pointToCheck.y + dy));
					}
				}

				if (nextBoat.size() == masts) {
					spacesFilled.addAll(nextBoat);
					ships_by_masts[i]--;
				}
			}
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				if (spacesFilled.contains(new Vec(i, j)))
					result.append("#");
				else
					result.append(".");
			}

		return result.toString();
	}
}
