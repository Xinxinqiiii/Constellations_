import javax.print.DocFlavor.URL;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.util.Scanner;

public class MapNavigationApp {
    private static final String GOOGLE_MAPS_API_KEY = "YOUR_GOOGLE_MAPS_API_KEY";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	JFrame frame = new JFrame("Direction and location search");
        	
        	
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            
            String[] directions = {"North", "South", "East", "West"};
            JComboBox<String> directionComboBox = new JComboBox<>(directions);
            panel.add(new JLabel("Select direction:"));
            panel.add(directionComboBox);
            
            JTextField locationTextField = new JTextField(20);
            panel.add(new JLabel("Entry point:"));
            panel.add(locationTextField);
            
            JButton searchButton = new JButton(" Search ");
            panel.add(locationTextField);
            panel.add(searchButton);
            
            JLabel resultLabel = new JLabel();
            panel.add(resultLabel);

            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    String selectedDirection = (String) directionComboBox.getSelectedItem();
                    String location = locationTextField.getText();

                  
                    String result = "The direction you selected is " + selectedDirection + "，The location you are looking for is：" + location;
                    resultLabel.setText(result);
                }
            });
            
            frame.add(panel);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 300);
            frame.setVisible(true);
            JFrame frame1 = new JFrame("URL Dialog Example");
            JButton showURLButton = new JButton("https://www.google.com");
            

            
            JTextArea resultTextArea = new JTextArea(10, 30);
            JScrollPane scrollPane = new JScrollPane(resultTextArea);
            resultTextArea.setEditable(false);

            searchButton.addActionListener(new ActionListener() {
                @Override
                 public void actionPerformed(ActionEvent e) {
                	
                	showURLDialog();
                	String selectedDirection = (String) directionComboBox.getSelectedItem();
                	String location = locationTextField.getText();
                	
                	 
//                    String destination = locationTextField.getText();
//
//                    if (!destination.isEmpty()) {
//                    	
//                        String directionsURL = "https://www.google.com/maps/search/?api=1&parameters\n"+
//                                "origin=your_current_location" + 
//                                "&destination=" + destination +
//                                "&key=" + GOOGLE_MAPS_API_KEY;
//
//                        try {
//                            URL url = new URL(directionsURL);
//                            try (InputStream inputStream = ((directionsURL) url).openStream();
//                            		Scanner scanner = new Scanner(inputStream)) {
//                                String json = scanner.useDelimiter("\\A").next();
//                                
//                                resultTextArea.setText(json);
//                            } catch (IOException ex) {
//                                ex.printStackTrace();
//                            }
//                        } catch (MalformedURLException ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//                }
                	
                    if (selectedDirection.equals("North") || selectedDirection.equals("South") ||
                    		selectedDirection.equals("East") || selectedDirection.equals("West")) {
                    		String result = "The direction you selected is " + selectedDirection +
                    		". The location you are looking for is: " + location;
                    		resultLabel.setText(result);
                    		
                    		
                    		JTabbedPane tabbedPane = new JTabbedPane();
                    		if (selectedDirection.equals("East")) {
         
                    			addTab(tabbedPane, "East", "file:///Users/xq/eclipse-workspace/CSC236/HackNJIT/East.html");
                    			frame.add(tabbedPane);
                    			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setSize(800, 600);
                                frame.setVisible(true);
                    			
                    		}
                    		else if (selectedDirection.equals("West")) {
                    			
                    			addTab(tabbedPane, "West", "West.html");
                    			frame.add(tabbedPane);
                    			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setSize(800, 600);
                                frame.setVisible(true);
                    		} 
                    		else if (selectedDirection.equals("North")) {
                    			
                    			addTab(tabbedPane, "North", "North.html");
                    			frame.add(tabbedPane);
                    			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setSize(800, 600);
                                frame.setVisible(true);                    		
                            }
                    		else if (selectedDirection.equals("South")) {
                    			
                    			addTab(tabbedPane, "South", "South.html");
                    			frame.add(tabbedPane);
                    			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setSize(800, 600);
                                frame.setVisible(true); 
                    		}
                    		} else {
                    		resultLabel.setText("Invalid direction. Please select North, South, East, or West.");
                    		resultTextArea.setText("");
                    		}
                    		}

				private void showURLDialog() {
					 String url = "https://www.google.com/maps/@40.7338349,-74.1834752,15z?entry=ttu"; 
				     String message = "<html><a href=\"" + url + "\">Map Location</a></html";

				        JEditorPane editorPane = new JEditorPane("text/html", message);
				        editorPane.setEditable(false);
				        editorPane.addHyperlinkListener(new HyperlinkListener() {
				            @Override
				            public void hyperlinkUpdate(HyperlinkEvent e) {
				                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
				                    try {
				                        Desktop.getDesktop().browse(new URI(e.getURL().toString()));
				                    } catch (Exception ex) {
				                        ex.printStackTrace();
				                    }
				                }
				            }
				        });
				        JOptionPane.showMessageDialog(null, editorPane, "Click This link", JOptionPane.INFORMATION_MESSAGE);

				}
            });
            frame.add(showURLButton);
            frame.add(panel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setVisible(true);
            
        });
    }
    private static void addTab(JTabbedPane tabbedPane, String title, String filename) {
        try {
            File htmlFile = new File(filename);
            String htmlContent = new String(Files.readAllBytes(htmlFile.toPath()));
            JEditorPane editorPane = new JEditorPane();
            editorPane.setContentType("text/html");
            editorPane.setText(htmlContent);
            JScrollPane scrollPane = new JScrollPane(editorPane);
            tabbedPane.addTab(title, scrollPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

