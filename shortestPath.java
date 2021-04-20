import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class shortestPath {
	
	int stop_id;
	int adjMatrix[][] = new int[3][3];
	double distance[];
	String stop_times;
	String transfers;

	shortestPath(String stop_times, String transfers){
		try {
			readFile(stop_times, transfers);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		createMap();

	}

	void createMap(){
		
	}

	
	void readFile(String file, String file1) throws FileNotFoundException  {

		int departureStop;
		int arrivalStop;
		int tripId = 0;
		int previousTripId = 0;
		int weight = 1;
		File stopTimes = new File (stop_times);
		Scanner scanner = new Scanner(stopTimes);
		scanner.nextLine();
		String line;

		while(scanner.hasNextLine()){
			line = scanner.nextLine();
			Scanner currentLine = new Scanner(line);
			currentLine.useDelimiter(",");

			tripId = currentLine.nextInt();
			arrivalStop = currentLine.nextInt();

			if(previousTripId == tripId){
				adjMatrix[departureStop][arrivalStop] = weight;
			}



		}
		
		if (!scanner.hasNextInt()) {
			return;
		}
		
		
		
	}

	public static void main(String[] args) {
		shortestPath map = new shortestPath("stop_times.txt", "transfers.txt");
	}
}
/*
 class Edge{
	public int u, v;
    public double length;
    public Edge(int u, int v, double length) {
    	this.u = u;
    	this.v = v;
    	this.length = length;
    }
}
    
class Stop implements Comparable<Stop>{
    public int u;
    public double time;
    public Stop(int u, double time) {
         this.u = u;
         this.time = time;
    }
    public int compareTo(Stop stop) {
         if (time < stop.time) {
              return -1; 
          }
          if (time > stop.time) {
              return 1; 
          }
          return 0; 
    }
}*/