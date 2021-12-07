
package views;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;

//form class for a form with 2 fields
public class emailForm extends JPanel {
    JTextField emailAddress;
    JTextArea emailBody;
    JButton sendButton;
    JLabel errorLabel;

    public emailForm() {
        sendButton = new JButton("Send");
        setLayout(null);

    }

    public void displayEmail(int width, int height) {
        this.removeAll();
        JLabel title = new JLabel("Contact form");
        title.setBounds(110, (int) (0.05 * height), (int) (0.12 * width), (int) (0.05 * height));
        add(title);
        JLabel addressLabel = new JLabel("Email Address");
        addressLabel.setBounds(10, (int) (0.1 * height), (int) (0.1 * width), (int) (0.025 * height));
        emailAddress = new JTextField();
        emailAddress.setBounds(110, (int) (0.1 * height), (int) (0.3 * width), (int) (0.025 * height));
        JLabel bodyLabel = new JLabel("Body");
        bodyLabel.setBounds(10, (int) (0.2 * height), (int) (0.05 * width), (int) (0.05 * height));
        emailBody = new JTextArea();
        emailBody.setBounds(110, (int) (0.2 * height), (int) (0.4 * width), (int) (0.4 * height));

        JLabel errorLabel = new JLabel();
        sendButton.setBounds(110, (int) (0.61 * height), (int) (0.1 * width), (int) (0.025 * height));
        errorLabel.setBounds(110, (int) (0.67 * height), (int) (0.1 * width), (int) (0.05 * height));

        add(addressLabel);
        add(emailAddress);
        add(bodyLabel);
        add(emailBody);
        add(sendButton);
        add(errorLabel);

    }

    public void displayError(String error) {
        errorLabel.setText(error);
        repaint();
    }

    public String getEmail() {
        return emailAddress.getText();
    }

}
