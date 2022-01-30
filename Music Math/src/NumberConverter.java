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
		window.setSize(1100,900);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.gray);
		window.setLayout(new FlowLayout());
		bin = window.getContentPane();
	
		
		buttonPan = new JPanel();
		buttonPan.setBackground(Color.black);
		bin.add(buttonPan);
		
		playButton = new JButton("Play Melody");
		playButton.setFocusPainted(false);
		playButton.addActionListener(bHandler);
		buttonPan.add(playButton);
		
		
		inputPan = new JPanel();
		inputPan.setBackground(Color.white);
		bin.add(inputPan);
		
		tempoPan = new JPanel();
		tempoPan.setBackground(Color.white);
		bin.add(tempoPan);
		bin.add(buttonPan);
		
        textField = new JTextField(20);

       
        textField2 = new JTextField(10);
        
        textField3 = new JTextField(5);

        label = new JLabel("Enter input as positive integers seperated by commas");
        label2 = new JLabel("Major, Minor, or Chromatic?");
        label3 = new JLabel("Enter tempo (Recomended between 60 and 250)");

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
            } 
            catch (Exception e) {
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
				 int time = Integer.parseInt(input3);
				 time = (60 * 1000)/time;
				 ArrayList<String> output = melodyMaker(input, input2);
				 for (String note : output) {
					 se.setFile(".//res//"+note+".wav");
					 se.play();
					 try {
						 TimeUnit.MILLISECONDS.sleep(time);
					
					 } 
					 catch (InterruptedException e) {
						 e.printStackTrace();
					 }
				 }
			 }
			 else {
				String input = textField.getText();
				String input2 = textField2.getText();
				ArrayList<String> output = melodyMaker(input, input2);
				String melody = "";
				for (String note : output) {
					melody+= note+" ";
				}
	            label.setText(melody);
			 }
		 }
	 }
	public static void main(String[] args) {
		NumberConverter converter = new NumberConverter();
	}
}




