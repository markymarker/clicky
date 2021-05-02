package coffee.virus.clicky.ui.spicy;

import coffee.virus.clicky.Scorecard;
import coffee.virus.clicky.Interacter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JPanel;


class ControlArea {

	private SpicyUI ui;

	private JPanel container;
	private JButton bClick;


	public ControlArea(SpicyUI ui){
		this.ui = ui;
	}


	public JPanel init(){
		Color bgColor = new Color(50, 30, 30);

		container = new JPanel();
		container.setPreferredSize(new java.awt.Dimension(220, 250));
		container.setBackground(bgColor);
		container.setLayout(new BorderLayout());

		bClick = new JButton(ui.actionDude.ACTION_CLICK);
		bClick.setPreferredSize(new Dimension(80, 60));

		JPanel group;

		group = new JPanel();
		group.setBackground(bgColor);
		group.add(bClick);
		container.add(group, BorderLayout.NORTH);

		group = new JPanel();
		group.setBackground(bgColor);
		group.add(new JButton(ui.actionDude.ACTION_DEBUG_DUMP));
		group.add(new JButton(ui.actionDude.ACTION_PURCHASE_0));
		group.add(new JButton(ui.actionDude.ACTION_PURCHASE_1));
		group.add(new JButton(ui.actionDude.ACTION_PURCHASE_2));
		group.add(new JButton(ui.actionDude.ACTION_PURCHASE_3));
		group.add(new JButton(ui.actionDude.ACTION_PURCHASE_4));
		group.add(new JButton(ui.actionDude.ACTION_PURCHASE_5));
		container.add(group, BorderLayout.CENTER);

		return container;
	}

	/**
	 * Get the position of this display object relative to its parent.
	 *
	 * @param p The point to store the location into
	 */
	void whereYouAt(Point p){
		container.getLocation(p);
	}

	/**
	 * Get the position of the click button.
	 * The position will be given relative to this display object's coordinate
	 * space.
	 *
	 * @param p The point to store the location into
	 */
	void whereClickButton(Point p, Dimension d){
		bClick.getLocation(p);
		bClick.getSize(d);
	}

}

