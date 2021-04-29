package coffee.virus.clicky.ui.simple;

import coffee.virus.clicky.Scorecard;
import coffee.virus.clicky.Interacter;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;


class ControlArea {

	private Scorecard scorecard;
	private Interacter interacter;

	private JPanel container;


	public ControlArea(){
	}


	public JPanel init(){
		container = new JPanel();
		container.setPreferredSize(new java.awt.Dimension(200, 100));
		container.setBackground(new Color(50, 30, 30));

		return container;
	}

	public void setScorecard(Scorecard s){
		scorecard = s;
	}

	public void setInteracter(Interacter i){
		interacter = i;
	}

}

