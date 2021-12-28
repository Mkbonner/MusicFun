import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
//import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NumberConverter {
	JFrame window;
	Container bin;
	JPanel buttonPan;
	JButton playButton;
	//String clickSound;
	ButtonHandler bHandler = new ButtonHandler();
	SoundEffect se = new SoundEffect();
	JTextField textField;
	JTextField textField2;
	JTextField textField3;
	JLabel label;
	JLabel label2;
	JLabel label3;
	JButton okButton;
	JPanel inputPan;
	JPanel tempoPan;
	
	public NumberConverter () {
		window = new JFrame();
		window.setSize(900,900);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.gray);
		window.setLayout(new FlowLayout());
		bin = window.getContentPane();
	
		
		buttonPan = new JPanel();
		buttonPan.setBounds(100,100,200,50);
		buttonPan.setBackground(Color.black);
		bin.add(buttonPan);
		
		playButton = new JButton("Play Melody");
		playButton.setFocusPainted(false);
		playButton.addActionListener(bHandler);
		buttonPan.add(playButton);
		
		//clickSound = ".//res//converted_Katie'sSong.wav";
		
		inputPan = new JPanel();
		inputPan.setBounds(300,300,300,300);
		inputPan.setBackground(Color.white);
		bin.add(inputPan);
		
		tempoPan = new JPanel();
		tempoPan.setBounds(100,100,100,100);
		tempoPan.setBackground(Color.white);
		bin.add(tempoPan);
		
        textField = new JTextField();
        textField.setBounds(200,200,200,200);
        
        textField2 = new JTextField();
        textField2.setBounds(200,200,200,200);
        
        textField3 = new JTextField();
        textField3.setBounds(200,200,200,200);

        label = new JLabel("Enter input as positive integers seperated by commas");
        label2 = new JLabel("Major, Minor, or Chromatic?");
        label3 = new JLabel("Enter tempo (Recomended between 60 and 200)");

        okButton = new JButton("OK");
        okButton.addActionListener(bHandler);
        inputPan.add(textField);
        inputPan.add(label);
        inputPan.add(textField2);
        inputPan.add(label2);
        tempoPan.add(textField3);
        tempoPan.add(label3);
        inputPan.add(okButton);
        window.setVisible(true);

	}
	
	 public ArrayList<String> melodyMaker(String preNumbers, String type) {
		int numbers[];
		int i;
		String[] stringNumbers = preNumbers.replaceAll(" ", "").split(",");
		numbers = new int[stringNumbers.length];
		for (int l = 0; l < stringNumbers.length; l++) {
            
            try {
                numbers[l] = Integer.parseInt(stringNumbers[l]);
            } catch (Exception e) {
                System.out.println("Unable to convert to integer: " + e.getMessage());
            }
        }
		ArrayList<String> melody = new ArrayList<String>();
		if (type.equals("Major")) {
		for (i = 0; i < numbers.length; i++) {
			int note = numbers[i] % 7;
			if (note == 0) {
				melody.add("C");
			}
			else if (note == 1) {
				melody.add("D");
			}
			else if (note == 2) {
				melody.add("E");
			}
			else if (note == 3) {
				melody.add("F");
			}
			else if (note == 4) {
				melody.add("G");
			}
			else if (note == 5) {
				melody.add("A");
			}
			else if (note == 6) {
				melody.add("B");
			}
			else {
				melody.add("oops");
			}
			}
		}
		else if (type.equals("Minor")) {
			for (i = 0; i < numbers.length; i++) {
				int note = numbers[i] % 7;
				if (note == 0) {
					melody.add("A");
				}
				else if (note == 1) {
					melody.add("B");
				}
				else if (note == 2) {
					melody.add("C");
				}
				else if (note == 3) {
					melody.add("D");
				}
				else if (note == 4) {
					melody.add("E");
				}
				else if (note == 5) {
					melody.add("F");
				}
				else if (note == 6) {
					melody.add("G");
				}
				else {
					melody.add("oops");
				}
				}
		}
			else {
				for (i = 0; i < numbers.length; i++) {
					int note = numbers[i] % 12;
					if (note == 0) {
						melody.add("C");
					}
					else if (note == 1) {
						melody.add("C#");
					}
					else if (note == 2) {
						melody.add("D");
					}
					else if (note == 3) {
						melody.add("D#");
					}
					else if (note == 4) {
						melody.add("E");
					}
					else if (note == 5) {
						melody.add("F");
					}
					else if (note == 6) {
						melody.add("F#");
					}
					else if (note == 7) {
						melody.add("G");
					}
					else if (note == 8) {
						melody.add("G#");
					}
					else if (note == 9) {
						melody.add("A");
					}
					else if (note == 10) {
						melody.add("A#");
					}
					else if (note == 11) {
						melody.add("B");
					}
					else {
						melody.add("oops");
					}
					}
				}
		return melody;
			}
		
	 public class SoundEffect {
		 Clip clip;
		 
		 public void setFile(String soundFileName) {
			 try {
			 File file = new File(soundFileName);
			 AudioInputStream sound = AudioSystem.getAudioInputStream(file);
			 clip = AudioSystem.getClip();
			 clip.open(sound);
		
			 }
			 catch(Exception e) {
				 
			 } 
		 }
		 
		 public void play() {
			 clip.setFramePosition(0);
			 clip.start();
		 }
		 
	 }
	 
	 public class ButtonHandler implements ActionListener{
		 public void actionPerformed(ActionEvent event) {
			 if (event.getSource() == playButton) {
				 
			String input = textField.getText();
			String input2 = textField2.getText();
			String input3 = textField3.getText();
			//System.out.println(input3);
			int time = Integer.parseInt(input3);
			//System.out.println(time);
			time = (60 * 1000)/time;
			//System.out.println(time);
	                //System.out.println("Input: " + input);
			ArrayList<String> output = melodyMaker(input, input2);
			for (String note : output) {
				se.setFile(".//res//"+note+".wav");
				se.play();
				try {
					TimeUnit.MILLISECONDS.sleep(time);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 
			 
			 }
			 else {
				 String input = textField.getText();
				 String input2 = textField2.getText();
	                //System.out.println("Input: " + input);
				 	ArrayList<String> output = melodyMaker(input, input2);
				 	String melody = "";
				 	for (String note : output) {
				 		//if (output.get(output.size()-1)!= note) {
						melody+= note+" ";
				 		//}
				 		//else {
				 		//	melody+= note;
				 		//}
					}
	                label.setText(melody);
			 }
		 }
	 }

	public static void main(String[] args) {
NumberConverter converter = new NumberConverter();
//Scanner scanner = new Scanner(System.in);
//System.out.println("How many notes long would you like your melody?");
//int length = scanner.nextInt();
//int[] numbers = new int[length];
//for (int i = 0; i < length; i++) {
//	System.out.println("Enter a number");
//	numbers[i] = scanner.nextInt();
//	scanner.nextLine();
//}
//System.out.println("Major, Minor, or Chromatic?");
//String type = scanner.nextLine();
//ArrayList<String> output = converter.melodyMaker(numbers, type);
//System.out.println(output);
//scanner.close();
	}
}




