import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

public class Manager extends JFrame implements Observer {

	private JButton addContact = new JButton("Add Contact");
	private ContactForm contactForm = new ContactForm();
	private JTable table;
	private DefaultTableModel model;

	public Manager() {

		setTitle("Contact Manager Application For JWD");
		setBounds(100, 100, 800, 500);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addToolBar();

		contactForm.addObserver(this);

		String data[][] = { { "James ", "Gosling", "jgosline@sun.com" },
				{ "Jonathan", "Schwartz", "jswartz@sun.com" } };
		String col[] = { "First Name", "Last Name", "Email" };

		model = new DefaultTableModel(data, col);
		table = new JTable(model);

		JScrollPane pane = new JScrollPane(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		getContentPane().add(pane);

		pack();

		setVisible(true);
	}

	private void addToolBar() {

		JToolBar toolbar = new JToolBar("Toolbar", JToolBar.HORIZONTAL);
		addContact.setIcon(new ImageIcon("add.png"));

		toolbar.add(addContact);

		initListeners();

		getContentPane().add(toolbar, BorderLayout.NORTH);

	}

	private void initListeners() {

		addContact.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!contactForm.isVisible()) {
					contactForm.cleanFormDetails();
					contactForm.setVisible(true);
				}
			}
		});
	}

	@Override
	public void update(Contact contact) {
		
		if (table != null) {
			
			model.insertRow(
					1,
					new Object[] { contact.getFirstName(),
							contact.getLastName(), contact.getEmail() });

			System.out.println("a new record was added");
			
		}
	}
}
