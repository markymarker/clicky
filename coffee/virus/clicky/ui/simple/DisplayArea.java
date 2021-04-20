package coffee.virus.clicky.ui.simple;

import coffee.virus.clicky.Scorecard;

import javax.swing.JPanel;


class DisplayArea extends JPanel {

	private Scorecard scorecard;

	private JPanel container;


	public DisplayArea(){
	}


	public void init(){
		container = new JPanel();
	}

	public void setScorecard(Scorecard s){
		scorecard = s;
	}

}

