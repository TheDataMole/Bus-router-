import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BusRoutes extends JFrame{
	
	private static final long serialVersionUID = 1L;

	static JFrame frame;
	
	static JButton pathB, stopB, routeB;
	
	static JLabel pathL, stopL, routeL, pathA, stopA, routeA;
	
	static JTextField pathFStart, pathFEnd, stopF, routeF;

	public static void main(String[] args) {
		frame = new JFrame("Bus Routes");
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		//1. shortest path
		
		pathL = new JLabel("Find fastest route \n");
		pathFStart = new JTextField(20);
		pathFEnd = new JTextField(20);
		pathB = new JButton("Search");
		pathA = new JLabel("Please enter your location and destination");
		
		JPanel pathPanel = new JPanel();
		String paths = "Error??";
		JButton pathExit = new JButton("Go Back");
		
		pathExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.remove(pathPanel);
				frame.add(p);
				frame.setVisible(true);
			}	
		});
		JLabel pathLabel = new JLabel(paths);
		
		pathPanel.add(pathExit);
		
		routeB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String start = pathFStart.getText();
				String end = pathFEnd.getText();
				
				paths = shortestPath(start, end);
				
				pathPanel.add(pathLabel);
				frame.setVisible(false);
				frame.remove(p);
				frame.add(pathPanel);
				frame.setVisible(true);
			}	
		});
	
		//2. search stops
		
		stopL = new JLabel("Find stop details \n");
		stopF = new JTextField(20);
		stopB = new JButton("Search");
		stopA = new JLabel("Please enter a stop name");
		
		JPanel stopPanel = new JPanel();
		
		String stops = "Error??";
		JButton stopExit = new JButton("Go Back");
		
		stopExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.remove(stopPanel);
				frame.add(p);
				frame.setVisible(true);
			}	
		});
		JLabel stopLabel = new JLabel(stops);	
		
		stopB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = stopF.getText();
				
				stops = searchStops(name);
				stopPanel.add(stopLabel);
				stopPanel.add(stopExit);
				
				frame.setVisible(false);
				frame.remove(p);
				frame.add(stopPanel);
				frame.setVisible(true);
			}	
		});
		
		//3. search by arrival time
		
		routeL = new JLabel("Find routes arriving at a given time\n");
		routeF = new JTextField(20);
		routeB = new JButton("Search");
		routeA = new JLabel("Please enter a time");
		
		JPanel routePanel = new JPanel();
		String routes = "Error??";
		JButton routeExit = new JButton("Go Back");
		
		routeExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.remove(routePanel);
				frame.add(p);
				frame.setVisible(true);
			}	
		});
		JLabel routeLabel = new JLabel(routes);
			
		routeB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String time = routeF.getText();
				
				routes = searchByArrivalTime(time);
				routePanel.add(routeLabel);
				routePanel.add(routeExit);
				
				frame.setVisible(false);
				frame.remove(p);
				frame.add(routePanel);
				frame.setVisible(true);
			}	
		});
		
		//building starting panel
		
		p.add(pathL);
		p.add(pathFStart);
		p.add(pathFEnd);
		p.add(pathB);
		p.add(pathA);
		
		p.add(stopL);
		p.add(stopF);
		p.add(stopB);
		p.add(stopA);
		
		p.add(routeL);
		p.add(routeF);
		p.add(routeB);
		p.add(routeA);
		
		frame.add(p);
		
		frame.setSize(300,300);
		
		frame.setVisible(true);
	}
}
