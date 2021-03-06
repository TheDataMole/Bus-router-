import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class shortestPath {
	
	int stop_id;
	static int adjMatrix[][] = new int[13000][13000];
	String stopTimes;
	String transfers;

	public shortestPath(String stopTimes, String transfers){
		
		try {
			readFile(stopTimes, transfers);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		createMap();
		

	}

	public void createMap()  {
		for(int i = 0 ; i < adjMatrix.length; i ++){
			for(int j = 0; j < adjMatrix[i].length; j++){
				if(i == j){
					adjMatrix[i][j] = 0;
				}
				else{
					adjMatrix[i][j] = -1;
				}
			}
		}
	}

	

	
	 public static void readFile(String stopTimes, String transfers) throws FileNotFoundException  {

		int departureStop = 0;
		int arrivalStop = 0;
		int tripId = 0;
		int previousTripId = 0;
		int weight = 1;
		int travelType;
		int minTravelTime;

		
		

		File stop_times = new File (stopTimes);
		Scanner scanner = new Scanner(stop_times);
		scanner.nextLine();
		String line;

		while(scanner.hasNextLine()){
			line = scanner.nextLine();
			Scanner currentLine = new Scanner(line);
			currentLine.useDelimiter(",");
			
			previousTripId = tripId;
			tripId = currentLine.nextInt();
			String arrivalTime = currentLine.next();
			String departureTime = currentLine.next();
			departureStop = arrivalStop;
			arrivalStop = currentLine.nextInt();
			if(previousTripId == tripId){
				adjMatrix[departureStop][arrivalStop] = weight;
			}

			currentLine.close();

		}
		scanner.close();
		
		File transferData = new File(transfers);
		scanner = new Scanner(transferData);
		scanner.nextLine();

		while(scanner.hasNextLine()){
			line = scanner.nextLine();
			Scanner currentLine = new Scanner(line);
			currentLine.useDelimiter(",");
			

			departureStop = currentLine.nextInt();
			arrivalStop = currentLine.nextInt();
			travelType = currentLine.nextInt();

			if(travelType == 0){
				adjMatrix[departureStop][arrivalStop] = 2;

			}
			else if(travelType == 2){
				minTravelTime = currentLine.nextInt();
				adjMatrix[departureStop][arrivalStop] = minTravelTime/100;
				
			}


			currentLine.close();

		}
		scanner.close();

		//findShortestPath(departureStop, arrivalStop);
		
		
	}

	public static void main(String[] args) {
		shortestPath map = new shortestPath("stop_times.txt", "transfers.txt");
		map.createMap();
		try{
			readFile("stop_times.txt", "transfers.txt");
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
		map.findShortestPath(1477, 1394);
		
		
		


	}

	//using dijkstra
	public String findShortestPath(int departureStop, int arrivalStop){
		String route;
		int visited[] = new int[adjMatrix.length];
		int currentStop;
		int numStopsVisited;
		double distance[] = new double[adjMatrix.length];
		int edge[] = new int[adjMatrix.length];

		

		visited[departureStop] = 1;
		distance[departureStop] = 0;
		currentStop = departureStop;
		numStopsVisited = 0;

		

		while(numStopsVisited < distance.length){
			

			//mark stop as visited and relax edges from the stop
			for(int i = 0; i < adjMatrix[currentStop].length; i++){
				if (visited[i] == 0){
					relaxEdges(currentStop, i, distance, edge);


				}
				
			}
			visited[currentStop] = 1;

			//find next stop with shortest distnace that has not been visited yet
			double shortestDistance = Integer.MAX_VALUE;
			for(int j = 0; j < distance.length; j++){
				if(visited[j] != 1 && shortestDistance > distance[j]){

					currentStop = j;
					shortestDistance = distance[j];
					//System.out.println(shortestDistance);
				}
			}
			numStopsVisited++;

		}
		
		route = returnRoute(departureStop, arrivalStop, distance, edge);
		return route;
		
	

	}

	private static String returnRoute(int departureStop, int arrivalStop, double [] distance, int[] edge) {
		String route = "---";
		while(departureStop != arrivalStop){
			route = "---" + edge[arrivalStop] + route;
			arrivalStop = edge[arrivalStop];
			//System.out.println(edge[arrivalStop]);

		}
		route = route + "---" + arrivalStop;
		
		route = distance[arrivalStop] + " from " + departureStop + " to " + arrivalStop + " via" + route;
		return route;
	}

	

	//code from Ivana's slides to relax edges from a node/stop
	private static void relaxEdges(int departureStop, int arrivalStop, double[] distance, int[] edge){
		if (distance[arrivalStop] > distance[departureStop] + adjMatrix[departureStop][arrivalStop]){
			distance[arrivalStop] = distance[departureStop] + adjMatrix[departureStop][arrivalStop];
			edge[arrivalStop] = departureStop;
		}
	}
}
