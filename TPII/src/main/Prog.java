package main;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import algorithms.DominantSet;

import files.DataFilesInterface;
import graphs.Graph;

public class Prog {
	
	public static void main(String[] args) {
		
		Map<Integer, String> tree = new TreeMap<Integer, String>();
		LinkedList<Map.Entry<Integer,String>> list = new LinkedList<Map.Entry<Integer,String>>(); 
		tree.put(2, "Dois");
		//list.add(tree.get(2));
		
		
		String graphsTypes[] = {"array","list","matrix"};
		String filesNames[] = {"datas\\T1_50_100_0.txt"};
		DataFilesInterface data = new DataFilesInterface();
		data.readData(filesNames[0]);
		Map<String, Graph> graphs = data.generateGraphs(graphsTypes);
		DominantSet set = new DominantSet();
		
		set.greedMethod(graphs.get("array"), 0.3, 10);
		
		System.out.println("FIM");
	}

}
