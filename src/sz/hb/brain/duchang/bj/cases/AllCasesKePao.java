package sz.hb.brain.duchang.bj.cases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import sz.hb.common.util.MapUtilities;

/**
 * @author hubin [hu.bin@msn.com]
 */

// DO notice every comment, or you will never understand
public class AllCasesKePao {

	private static LinkedList<Integer> woNiuXiPaiJi = new LinkedList<Integer>();

	// index 0 is flag, oops.
	private static int[] yiTuoPai = new int[12];

	private static StringBuilder stringBuilderOutPut = new StringBuilder(
			"No Display stringBuilderOutPut!");

	private static HashMap<String, Integer> holdCaseMap = new HashMap<String, Integer>();
	private static int tmpSumHolder;

	public static void main(String[] args) {
		rrrrr();
		// tttttt();
	}

	private static void rrrrr() {
		gaoYiDiePai();
		holdCaseMap.clear();
		while (true) {
			initYiTuoPai();
			youXiJinXingYiCi();
			if (woNiuXiPaiJi.size() <= 60) {
				break;
			}
		}// end while
		MapUtilities.sortByValue(holdCaseMap);
		List<Entry<String, Integer>> list = MapUtilities
				.sortByValue(holdCaseMap);
		int tmpIdx = 0;
		
		for (Entry<String, Integer> entry : list) {
			System.out.println(++tmpIdx+", combo," + entry.getKey() + "occurs,"
					+ entry.getValue());
		}
		// dispOneMap(holdCaseMap);
	}

	private static void tttttt() {
		yiTuoPai = new int[] { 0, 2, 1, 8, 1, 0, 0, 0, 0, 0, 0 };
		int ttwe = calcSum();
		System.out.println(ttwe);
		Arrays.sort(yiTuoPai, 1, yiTuoPai.length);
		for (int i = 1; i < yiTuoPai.length; i++) {
			System.out.println(yiTuoPai[i]);
		}
	}

	private static void gaoYiDiePai() {
		for (int i = 1; i <= 52 * 18000; i++) {
			woNiuXiPaiJi.addLast(i);
		}
		Collections.shuffle(woNiuXiPaiJi);
	}

	private static void initYiTuoPai() {
		for (int i = 0; i < yiTuoPai.length; i++) {
			yiTuoPai[i] = 0;
		}
	}

	private static void youXiJinXingYiCi() {
		while (calcSum() < 21) {
			for (int i = 1; i < yiTuoPai.length; i++) {
				if (yiTuoPai[i] == 0) {
					yiTuoPai[i] = yaoYiZhangPai();
					break;
				}
			}// end for
		}// end while

		if (calcSum() == 21) {
			if (!holdCaseMap.containsKey(makeOutPutString())) {
				holdCaseMap.put(makeOutPutString(), new Integer(1));
			} else {
				holdCaseMap.put(makeOutPutString(), new Integer(holdCaseMap
						.get(makeOutPutString()).intValue() + 1));
			}
		}
	}

	private static int calcSum() {
		tmpSumHolder = 0;
		yiTuoPai[0] = 0;
		for (int i = 1; i < yiTuoPai.length; i++) {
			if (yiTuoPai[i] == 0) {
				continue;
			}
			switch (yiTuoPai[i]) {
			case 1:
				if (tmpSumHolder > 10) {
					tmpSumHolder += 1;
				} else {
					tmpSumHolder += 11;
					yiTuoPai[0] = yiTuoPai[0] + 1;
				}
				break;
			case 2:
				tmpSumHolder += 2;
				break;
			case 3:
				tmpSumHolder += 3;
				break;
			case 4:
				tmpSumHolder += 4;
				break;
			case 5:
				tmpSumHolder += 5;
				break;
			case 6:
				tmpSumHolder += 6;
				break;
			case 7:
				tmpSumHolder += 7;
				break;
			case 8:
				tmpSumHolder += 8;
				break;
			case 9:
				tmpSumHolder += 9;
				break;
			case 10:
				tmpSumHolder += 10;
				break;
			case 11:
				tmpSumHolder += 10;
				break;
			case 12:
				tmpSumHolder += 10;
				break;
			case 13:
				tmpSumHolder += 10;
				break;
			case 0:
				break;
			default:
				System.out.println("Never Display 016!");
				break;
			}// end switch
		}// end for loop
		while (tmpSumHolder > 21) {
			if (yiTuoPai[0] == 0) {
				break;
			} else {
				tmpSumHolder -= 10;
				yiTuoPai[0] = yiTuoPai[0] - 1;
			}
		}// end while
		return tmpSumHolder;
	}

	private static int yaoYiZhangPai() {
		if (woNiuXiPaiJi.size() >= 16) {
			return woNiuXiPaiJi.removeFirst().intValue() % 13 + 1;
		} else {
			System.out.println("mei you pai le!");
			System.exit(0);
		}
		return 0;
	}

	private static String makeOutPutString() {
		stringBuilderOutPut.delete(0, stringBuilderOutPut.length());
		Arrays.sort(yiTuoPai, 1, yiTuoPai.length);
		for (int i = 1; i < yiTuoPai.length; i++) {
			if (yiTuoPai[i] != 0) {
				stringBuilderOutPut.append("[").append(yiTuoPai[i]).append("]")
						.append(" ");
			}
		}
		stringBuilderOutPut.append(" -- > [").append(calcSum()).append("]");
		return stringBuilderOutPut.toString();
	}

	private static void dispOneMap(HashMap<String, Integer> paraMap) {
		for (Map.Entry<String, Integer> entry : paraMap.entrySet()) {
			System.out.println("combo," + entry.getKey() + "occurs,"
					+ entry.getValue());
		}
	}

}
