package coffee.virus.clicky.ui.simple;

import coffee.virus.clicky.Scorecard;
import coffee.virus.clicky.Interacter;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;


class ControlArea {

	private SimpleUI ui;

	private JPanel container;


	public ControlArea(SimpleUI ui){
		this.ui = ui;
	}


	public JPanel init(){
		container = new JPanel();
		container.setPreferredSize(new java.awt.Dimension(220, 120));
		container.setBackground(new Color(50, 30, 30));

		container.add(new JButton(ui.actionDude.ACTION_CLICK));
		container.add(new JButton(ui.actionDude.ACTION_DEBUG_DUMP));
		container.add(new JButton(ui.actionDude.ACTION_PURCHASE_1));
		container.add(new JButton(ui.actionDude.ACTION_PURCHASE_2));
		container.add(new JButton(ui.actionDude.ACTION_PURCHASE_3));

		return container;
	}

}

