package Work;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * word frequency count
 * 
 * @author yufengwen 2018年1月5日
 */

public class Word_Frequency_Count {
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				// 改变了o1,o2的顺序,也就改变了排序的顺序
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public static void main(String[] args) {
		File file = new File("/Users/yufengwen/Desktop/竹子/3.txt");
		BufferedReader reader = null;

		Map<String, Integer> map = new HashMap<String, Integer>();
		int line = 1;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;

			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				if (map.get(tempString) == null) {
					map.put(tempString, 1);
				} else {
					map.put(tempString, map.get(tempString) + 1);
				}

				System.out.println(line + " " + tempString);

				line++;
			}
			reader.close();
			System.out.println("*************" + (line - 1) + "******************");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

		map = sortByValue(map);

		Set set = map.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry mapentry = (Map.Entry) iterator.next();
			System.out.println(mapentry.getValue() + "\t" + mapentry.getKey() + "\t" + (int) mapentry.getValue() * 1.0 / line);
		}

	}

}
