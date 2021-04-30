package coffee.virus.clicky.ui.simple;

import coffee.virus.clicky.Scorecard;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;


class DisplayArea {

	private SimpleUI ui;

	private JPanel container;
	private JTextArea display;


	public DisplayArea(SimpleUI ui){
		this.ui = ui;
	}


	public JPanel init(){
		container = new JPanel();
		container.setPreferredSize(new java.awt.Dimension(200, 100));
		container.setBackground(new Color(30, 30, 30));

		display = new JTextArea(4, 12);
		display.setEnabled(false);
		display.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		display.setBackground(new Color(32, 32, 42));
		//display.setForeground(new Color(180, 180, 220));
		container.add(display);

		update();
		return container;
	}

	public void update(){
		if(ui.scorecard == null) return;

		display.setText("\n" +
			"Points: " + (ui.scorecard.clicks + ui.scorecard.assists) + "\n" +
			"Assists: " + ui.scorecard.assists + ", Ticks: " + ui.scorecard.ticks + "\n" +
			"Items: " + ui.scorecard.getItemCount() + "\n"
		);
	}

}

