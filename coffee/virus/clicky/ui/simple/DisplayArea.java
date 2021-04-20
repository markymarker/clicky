package coffee.virus.clicky.ui.simple;

import coffee.virus.clicky.Scorecard;

import javax.swing.JPanel;


class DisplayArea extends JPanel {

	private Scorecard scorecard;


	public DisplayArea(){
		super();
	}


	public void setScorecard(Scorecard s){
		scorecard = s;
	}

}

