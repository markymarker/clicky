package coffee.virus.clicky.ui.spicy;

import coffee.virus.clicky.Scorecard;
import coffee.virus.clicky.Interacter;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;


class ControlArea {

	private SpicyUI ui;

	private JPanel container;


	public ControlArea(SpicyUI ui){
		this.ui = ui;
	}


	public JPanel init(){
		container = new JPanel();
		container.setPreferredSize(new java.awt.Dimension(220, 140));
		container.setBackground(new Color(50, 30, 30));

		container.add(new JButton(ui.actionDude.ACTION_CLICK));
		container.add(new JButton(ui.actionDude.ACTION_DEBUG_DUMP));
		container.add(new JButton(ui.actionDude.ACTION_PURCHASE_0));
		container.add(new JButton(ui.actionDude.ACTION_PURCHASE_1));
		container.add(new JButton(ui.actionDude.ACTION_PURCHASE_2));
		container.add(new JButton(ui.actionDude.ACTION_PURCHASE_3));
		container.add(new JButton(ui.actionDude.ACTION_PURCHASE_4));
		container.add(new JButton(ui.actionDude.ACTION_PURCHASE_5));

		return container;
	}

}

