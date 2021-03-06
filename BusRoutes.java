import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;

public class BusRoutes extends JFrame{
	
	private static final long serialVersionUID = 1L;

	static JFrame frame;
	
	static JButton pathB, stopB, routeB;
	
	static JLabel pathL, stopL, routeL, pathA, stopA, routeA;
	
	static JTextField pathFStart, pathFEnd, stopF, routeF;

	public static void main(String[] args) {
		if(frame == null) {
			frame = new JFrame("Bus Routes");
		}
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		//1. shortest path
		
		pathL = new JLabel("Find fastest route \n");
		pathFStart = new JTextField(20);
		pathFEnd = new JTextField(20);
		pathB = new JButton("Search");
		pathA = new JLabel("Please enter your location and destination IDs");
		
		JPanel pathPanel = new JPanel();
		
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
		
		
		pathPanel.add(pathExit);
		
		pathB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String start = pathFStart.getText();
				String end = pathFEnd.getText();
				String paths = "Error??";
				BusRouter x = new BusRouter();
				int startID;
				int endID;
				JLabel pathLabel = new JLabel(paths);
				
				if(start != null && end != null) {
					try {
						startID = Integer.parseInt(start);
						endID = Integer.parseInt(end);
					}
					catch (NumberFormatException nfe){
						pathA.setText("Please enter the Stops' IDs");
					}
				
					paths = x.shortestPath(startID, endID);
					pathLabel.setText(paths);
					pathPanel.add(pathLabel);
					frame.setVisible(false);
					frame.remove(p);
					frame.add(pathPanel);
					frame.setVisible(true);
				}
				else {
					pathA.setText("Please enter 2 IDs");
				}
			}	
		});
	
		//2. search stops
		
		stopL = new JLabel("Find stop details \n");
		stopF = new JTextField(20);
		stopB = new JButton("Search");
		stopA = new JLabel("Please enter a stop name");
		
		JPanel stopPanel = new JPanel();
		
		
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
			
		
	stopB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = stopF.getText();
				String stops = "Error??";
				JLabel stopLabel = new JLabel(stops);
				BusRouter x = new BusRouter();
				if(name==null) {
					stopA.setText("Please enter a stop name above");
				}
				else {
				
				stops = x.TST.search(name);
				
				stopLabel.setText(stops);
				stopPanel.add(stopLabel);
				stopPanel.add(stopExit);
				
				frame.setVisible(false);
				frame.remove(p);
				frame.add(stopPanel);
				frame.setVisible(true);
				}
			}	
		});
		
		//3. search by arrival time
		
		routeL = new JLabel("Find routes arriving at a given time\n");
		routeF = new JTextField(20);
		routeB = new JButton("Search");
		routeA = new JLabel("Please enter a time");
		
		JPanel routePanel = new JPanel();
		
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
		
		routeB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String time = routeF.getText();
				String routes = "Error??";
				JLabel routeLabel = new JLabel(routes);
				
				//check if time is in hh:mm:ss	
				
				
				if (time!="") {
					BusRouter bus = new BusRouter();
					try {
						routes = bus.searchByArrivalTime(time);
					} catch (FileNotFoundException e1) {
						routes = "File not found";
					}
					
					if (routes=="Invalid input") {
						routeA.setText("Please enter a time in HH:MM:SS format");
					}
					else {
						
						routeLabel.setText(routes);
						routePanel.add(routeLabel);
						routePanel.add(routeExit);
						
						frame.setVisible(false);
						frame.remove(p);
						frame.add(routePanel);
						frame.setVisible(true);
					}
				}
				else {
					routeA.setText("Please enter a time above");
				}
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
