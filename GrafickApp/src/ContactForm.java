import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactForm extends JFrame {

    private JTextField name_field, surname_field;
    private JRadioButton male, female;
    private JCheckBox checkBox;

    public  ContactForm() {
        super("Contact form");
        super.setBounds(200, 200, 350, 200);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = super.getContentPane();
        container.setLayout(new GridLayout(5, 2, 2, 3));

        JLabel name = new JLabel("Your name: ");
        name_field = new JTextField("", 1);

        JLabel surname = new JLabel("Your surname: ");
        surname_field = new JTextField("", 1);

        container.add(name);
        container.add(name_field);
        container.add(surname);
        container.add(surname_field);

        male = new JRadioButton("Man");
        female = new JRadioButton("Women");

         male.setSelected(true);
         ButtonGroup group = new ButtonGroup();
         group.add(male);
         group.add(female);

        container.add(male);
        container.add(female);

        checkBox = new JCheckBox("Do you agree?", true);
        container.add(checkBox);

        JButton btn = new JButton("Send");
        container.add(btn);

        btn.addActionListener(new ButtonEventManager());
    }

    class ButtonEventManager implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){
            String name = name_field.getText();
            String surname = surname_field.getText();

            String isMale = "Man";

            if(!male.isSelected())
                isMale = "Women";

            boolean isCheck = checkBox.isSelected();

            JOptionPane.showMessageDialog(null, "Name: " + name + "\nSurname: " + surname +
                    "\nGender: " + isMale + "\nAgreed: " + (isCheck ? "Yes" : "No"), "Form Details", JOptionPane.PLAIN_MESSAGE);

        }
    }

}
