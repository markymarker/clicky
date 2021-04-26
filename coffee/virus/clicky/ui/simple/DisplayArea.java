package coffee.virus.clicky.ui.simple;

import coffee.virus.clicky.Scorecard;

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
		container.setPreferredSize(new java.awt.Dimension(200, 200));
		container.setBackground(java.awt.Color.RED);

		display = new JTextArea(3, 12);
		display.setEnabled(false);
		container.add(display);

		update();
		return container;
	}

	public void setScorecard(Scorecard s){
		scorecard = s;
	}

	public void update(){
		if(scorecard == null) return;

		display.setText(
			"Points: " + (scorecard.clicks + scorecard.assists) + "\n" +
			"Assists: " + scorecard.assists + ", Ticks: " + scorecard.ticks
		);
	}

}

