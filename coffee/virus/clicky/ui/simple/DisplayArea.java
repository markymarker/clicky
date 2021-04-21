package coffee.virus.clicky.ui.simple;

import coffee.virus.clicky.Scorecard;

import javax.swing.JPanel;


class DisplayArea {

	private Scorecard scorecard;

	private JPanel container;


	public DisplayArea(){
	}


	public JPanel init(){
		container = new JPanel();
		container.setPreferredSize(new java.awt.Dimension(200, 200));
		container.setBackground(java.awt.Color.RED);

		return container;
	}

	public void setScorecard(Scorecard s){
		scorecard = s;
	}

}

