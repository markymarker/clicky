package coffee.virus.clicky.ui.simple;

import coffee.virus.clicky.Scorecard;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;


class DisplayArea {

	private Scorecard scorecard;

	private JPanel container;
	private JTextArea display;


	public DisplayArea(){
	}


	public JPanel init(){
		container = new JPanel();
		container.setPreferredSize(new java.awt.Dimension(200, 100));
		container.setBackground(new Color(30, 30, 30));

		display = new JTextArea(4, 12);
		display.setEnabled(false);
		display.setBackground(new Color(80, 80, 90));
		//display.setForeground(new Color(180, 180, 220));
		container.add(display);

		update();
		return container;
	}

	public void setScorecard(Scorecard s){
		scorecard = s;
	}

	public void update(){
		if(scorecard == null) return;

		display.setText("\n" +
			"Points: " + (scorecard.clicks + scorecard.assists) + "\n" +
			"Assists: " + scorecard.assists + ", Ticks: " + scorecard.ticks + "\n"
		);
	}

}

