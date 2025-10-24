import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OnlineFeedbackForm extends JFrame {
    private JRadioButton[] stars;
    private ButtonGroup starGroup;
    private JTextArea commentsArea;
    private JButton submitButton;

    public OnlineFeedbackForm() {
        setTitle("Online Feedback Form");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Rating Panel
        JPanel ratingPanel = new JPanel(new FlowLayout());
        ratingPanel.setBorder(BorderFactory.createTitledBorder("Rate our service (1-5 stars)"));
        stars = new JRadioButton[5];
        starGroup = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            stars[i] = new JRadioButton(String.valueOf(i + 1));
            starGroup.add(stars[i]);
            ratingPanel.add(stars[i]);
        }

        // Comments Panel
        JPanel commentsPanel = new JPanel(new BorderLayout());
        commentsPanel.setBorder(BorderFactory.createTitledBorder("Comments"));
        commentsArea = new JTextArea(5, 30);
        commentsPanel.add(new JScrollPane(commentsArea), BorderLayout.CENTER);

        // Submit Button
        submitButton = new JButton("Submit");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);

        add(ratingPanel, BorderLayout.NORTH);
        add(commentsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
    }

    private void handleSubmit() {
        int rating = 0;
        for (int i = 0; i < 5; i++) {
            if (stars[i].isSelected()) {
                rating = i + 1;
                break;
            }
        }
        String comments = commentsArea.getText().trim();
        if (rating == 0) {
            JOptionPane.showMessageDialog(this, "Please select a rating.");
            return;
        }
        JOptionPane.showMessageDialog(this, "Thank you for your feedback!\nRating: " + rating + " star(s)\nComments: " + comments);
        starGroup.clearSelection();
        commentsArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OnlineFeedbackForm().setVisible(true);
            }
        });
    }
}
