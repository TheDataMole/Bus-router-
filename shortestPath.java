import java.io.File;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class shortestPath {
	
	int stop_id;
	ArrayList<Edge> mapList[];
	int speeds[] = new int[3];
	PriorityQueue<Node> queue = new PriorityQueue<Node>();
	double distance[];
	
	void readFile(String file) throws Exception {
		Scanner scanner = new Scanner(new File(file));
		
		if (!scanner.hasNextInt()) {
			return;
		}
		
		stop_id = scanner.nextInt();
		mapList = new ArrayList[stop_id];
		for (int i = 0; i < stop_id; i++) {
			mapList[i] = new ArrayList();
			
		}
		distance = new double[stop_id];
		
		int times = scanner.nextInt();
		while (times > 0) {
			times--;
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			double time_to_stop = a - b;
			mapList[a].add(new Edge(a, b, time_to_stop));
		}
	}
}

class Edge{
	public int u, v;
    public double length;
    public Edge(int u, int v, double length) {
    	this.u = u;
    	this.v = v;
    	this.length = length;
    }
}
    
class Node implements Comparable<Node>{
    public int u;
    public double time;
    public Node(int u, double time) {
         this.u = u;
         this.time = time;
    }
    public int compareTo(Node node) {
         if (time < node.time) {
              return -1; 
          }
          if (time > node.time) {
              return 1; 
          }
          return 0; 
    }
}