package main;

import java.util.Map;

import algorithms.DominantSet;

import files.DataFilesInterface;
import graphs.Graph;

public class Prog {
	
	public static void main(String[] args) {
		
		String graphsTypes[] = {"array","list","matrix"};
		String filesNames[] = {"datas\\T1_50_100_0.txt"};
		DataFilesInterface data = new DataFilesInterface();
		data.readData(filesNames[0]);
		Map<String, Graph> graphs = data.generateGraphs(graphsTypes);
		DominantSet set = new DominantSet();
		
		set.greedMethod(graphs.get("array"), 0.2, 10);
		
		System.out.println("FIM");
	}

}
