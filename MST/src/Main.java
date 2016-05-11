import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		File input = new File("input.txt");
		File output = new File("output.txt");
		PrintWriter printer = new PrintWriter(output);
		ArrayList<Vertex> vList = new ArrayList<>();

		Scanner sc = new Scanner(input);
		int[][] matrix = null;

		int counter = 0;

		while (sc.hasNextLine()) {
			String word = sc.nextLine();
			if (counter == 0) {
				matrix = new int[Integer.parseInt(word)][Integer.parseInt(word)];
				for (int i = 0; i < Integer.parseInt(word); i++) {
					vList.add(new Vertex(String.valueOf(i)));
				}
				counter++;
				continue;
			}

			StringTokenizer tokenizer = new StringTokenizer(word, " ");

			for (int i = 0; i < matrix.length; i++) {
				matrix[counter - 1][i] = Integer.parseInt(tokenizer.nextToken());
				
			}
counter++;
		}

		for (int i = 0; i < vList.size(); i++) {
			for (int j = i; j < vList.size(); j++) {
				if (matrix[i][j] != 0) {
					Edge e = new Edge(vList.get(i), vList.get(j), matrix[i][j]);
					vList.get(i).newEdge(e);
					vList.get(j).newEdge(e);
				}
			}
		}

		int totalCost=0;
		ArrayList<Edge> MSTlist = new ArrayList<>();
		HashMap<Vertex, Integer> vertices = new HashMap<>();
		vertices.put(vList.get(0), 0);
		MSTlist = MST(vertices, new ArrayList<Edge>());
		int[][] m = new int[vList.size()][vList.size()];
		for(int i=0; i<vList.size(); i++){
			for(int j=0; j<vList.size(); j++){
				m[i][j]=0;
			}
		}
		for(Edge e : MSTlist){
			m[Integer.parseInt(e.end1.getName())][Integer.parseInt(e.end2.getName())]=1;
			m[Integer.parseInt(e.end2.getName())][Integer.parseInt(e.end1.getName())]=1;
			totalCost+=e.getWeight();
			
		}
		
		printer.println(totalCost);
		for(int i=0; i<m.length; i++){
			for(int j=0; j<m.length; j++){
				printer.print(m[i][j]+" ");
			}
			printer.println();
		}
		printer.close();

	}

	public static ArrayList<Edge> MST(HashMap<Vertex, Integer> hash,
			ArrayList<Edge> edgLst) {
		int temp = Integer.MAX_VALUE;
		Edge minE = null;
		for (Vertex v : hash.keySet()) {
			for (Edge e : v.edgeList) {
				if (e.weight < temp) {
					if (!hash.containsKey(e.opposite(v))) {
						minE = e;
						temp = e.weight;
					}
				}
			}
		}
		if (minE == null)
			return edgLst;
		edgLst.add(minE);
		hash.put(minE.end1, 0);
		hash.put(minE.end2, 0);
		minE.removeEdge();
		return MST(hash, edgLst);

	}

}
