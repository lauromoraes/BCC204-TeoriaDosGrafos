package main;

import java.util.Map;

import algorithms.MinimumCostDominantSet;

import files.DataFilesInterface;
import graphs.Graph;

public class Prog {
	
	public static void main(String[] args) {
		
		String graphsTypes[] = {"array","list","matrix"};
		String filesNames[] = {
				"datas\\T1_50_100_0.txt",
				"datas\\T1_50_250_0.txt",
				"datas\\T1_50_1000_0.txt",
				"datas\\T1_100_250_0.txt",
				"datas\\T1_100_1000_0.txt",
				"datas\\T1_100_2000_0.txt",
				"datas\\T1_150_250_0.txt",
				"datas\\T1_150_1000_0.txt",
				"datas\\T1_150_2000_0.txt",
				"datas\\T1_200_250_0.txt",
				"datas\\T1_200_1000_0.txt",
				"datas\\T1_200_2000_0.txt",
				"datas\\T1_300_1000_0.txt",
				"datas\\T1_300_2000_0.txt",
				"datas\\T1_300_5000_0.txt",
				//"datas\\T2_50_50_0.txt",
				"datas\\T2_50_100_0.txt",
				"datas\\T2_50_250_0.txt",
				"datas\\T2_50_1000_0.txt",
				"datas\\T2_100_250_0.txt",
				//"datas\\T2_100_500_0.txt",
				"datas\\T2_100_1000_0.txt",
				"datas\\T2_100_2000_0.txt",
				"datas\\T2_150_250_0.txt",
				"datas\\T2_150_1000_0.txt",
				"datas\\T2_150_2000_0.txt",
				"datas\\T2_200_250_0.txt",
				"datas\\T2_200_1000_0.txt",
				"datas\\T2_200_2000_0.txt",
				"datas\\T2_300_1000_0.txt",
				"datas\\T2_300_2000_0.txt",
				"datas\\T2_300_5000_0.txt"
		};
		int bests_knows[] = {
				406,
				181,
				40,
				659,
				220,
				106,
				1251,
				466,
				250,
				2008,
				728,
				424,
				1482,
				856,
				409,
				121,
				215,
				107,
				238,
				557,
				567,
				269,
				639,
				958,
				310,
				650,
				1043,
				736,
				1109,
				2004
		};
		for(int i=0; i<filesNames.length; i++) {
			DataFilesInterface data = new DataFilesInterface();
			data.readData(filesNames[i]);
			Map<String, Graph> graphs = data.generateGraphs(graphsTypes);
	//		DominantSet set = new DominantSet();
	//		set.greedMethod(graphs.get("array"), 0.2, 1, 10);
			int t = 0;
			int t_max = 10000;
			MinimumCostDominantSet min_dom_set = new MinimumCostDominantSet(graphs.get("array"), 1, t);
			while( t++ < t_max ) {
				min_dom_set.setSeed(t);
				min_dom_set.constructGreed(0.25);
				//min_dom_set.localSearch();
				//System.out.println( t + " = " + min_dom_set.getCost() );
			}
			System.out.println( ">>> BEST (" + filesNames[i] + ") = " + bests_knows[i]);
			System.out.println( ">>> FINDED = " + min_dom_set.getBestCost() );
			System.out.println( ">>> DIFF = " + (min_dom_set.getBestCost() -  bests_knows[i]) );
			System.out.println( ">>> % = " + ( ((min_dom_set.getBestCost() * 100) /  bests_knows[i]) - 100) );
		}
		System.out.println("FIM");
	}

}
