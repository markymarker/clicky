package coffee.virus.clicky.ui.simple;

import coffee.virus.clicky.Scorecard;
import coffee.virus.clicky.Interacter;

import javax.swing.JButton;
import javax.swing.JPanel;


class ControlArea extends JPanel {

	private Scorecard scorecard;
	private Interacter interacter;


	public ControlArea(){
		super();
	}


	public void setScorecard(Scorecard s){
		scorecard = s;
	}

	public void setInteracter(Interacter i){
		interacter = i;
	}

}

