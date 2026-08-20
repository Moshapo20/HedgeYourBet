/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 23031702
 */

import javax.swing.*;
import java.awt.*;

public class HedgeYourBet extends JFrame {

    // Question number
    int questionNumber = 0;

    // Player's total score
    int score = 0;

    // Questions
    String[] questions = {
        "Which language is mainly used for Android development?",
        "Which data type stores whole numbers in Java?",
        "Which symbol is used to end a Java statement?",
        "Which keyword is used to create a class in Java?",
        "Which loop is best when you know how many times to repeat?"
    };

    // Correct answers
    // 0 = first checkbox
    // 1 = second checkbox
    // 2 = third checkbox
    int[] correctAnswers = {
        0,
        1,
        2,
        0,
        1
    };

    // Answer choices
    String[][] answers = {
        {"Java", "Python", "HTML"},
        {"String", "int", "double"},
        {".", ":", ";"},
        {"class", "new", "public"},
        {"while", "for", "do-while"}
    };

    // GUI components
    JLabel questionLabel;
    JLabel scoreLabel;

    JCheckBox checkBox1;
    JCheckBox checkBox2;
    JCheckBox checkBox3;

    JButton submitButton;

    // Panel for checkboxes
    JPanel answersPanel;

    public HedgeYourBet() {

        // Window title
        setTitle("Hedge Your Bet Quiz");

        // Window size
        setSize(500, 350);

        // Close program when window closes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Put window in the center
        setLocationRelativeTo(null);

        // Create main panel
        JPanel panel = new JPanel();

        // Arrange components vertically
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // ---------------- QUESTION ----------------

        questionLabel = new JLabel();

        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ---------------- CHECKBOXES ----------------

        checkBox1 = new JCheckBox();
        checkBox2 = new JCheckBox();
        checkBox3 = new JCheckBox();

        // Create panel for checkboxes
        answersPanel = new JPanel();

        answersPanel.setLayout(
            new BoxLayout(answersPanel, BoxLayout.Y_AXIS)
        );

        // Align checkboxes to the left
        checkBox1.setAlignmentX(Component.LEFT_ALIGNMENT);
        checkBox2.setAlignmentX(Component.LEFT_ALIGNMENT);
        checkBox3.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add checkboxes to answer panel
        answersPanel.add(checkBox1);
        answersPanel.add(checkBox2);
        answersPanel.add(checkBox3);

        // Give answer panel a fixed width
        answersPanel.setPreferredSize(
            new Dimension(300, 75)
        );

        answersPanel.setMaximumSize(
            new Dimension(300, 75)
        );

        // ---------------- SUBMIT BUTTON ----------------

        submitButton = new JButton("Submit Answer");

        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ---------------- SCORE ----------------

        scoreLabel = new JLabel("Score: 0");

        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ---------------- ADD COMPONENTS ----------------

        panel.add(Box.createVerticalStrut(20));

        panel.add(questionLabel);

        panel.add(Box.createVerticalStrut(20));

        panel.add(answersPanel);

        panel.add(Box.createVerticalStrut(20));

        panel.add(submitButton);

        panel.add(Box.createVerticalStrut(20));

        panel.add(scoreLabel);

        // Add panel to window
        add(panel);

        // Display first question
        showQuestion();

        // Submit button
        submitButton.addActionListener(
            e -> checkAnswer()
        );
    }

    // Displays the current question
    void showQuestion() {

        questionLabel.setText(
            "Question " + (questionNumber + 1)
            + ": "
            + questions[questionNumber]
        );

        checkBox1.setText(
            answers[questionNumber][0]
        );

        checkBox2.setText(
            answers[questionNumber][1]
        );

        checkBox3.setText(
            answers[questionNumber][2]
        );

        // Clear selections
        checkBox1.setSelected(false);
        checkBox2.setSelected(false);
        checkBox3.setSelected(false);
    }

    // Checks the player's answer
    void checkAnswer() {

        int selectedCount = 0;

        // Count selected boxes
        if (checkBox1.isSelected()) {
            selectedCount++;
        }

        if (checkBox2.isSelected()) {
            selectedCount++;
        }

        if (checkBox3.isSelected()) {
            selectedCount++;
        }

        // Check whether correct answer was selected
        boolean correct = false;

        if (correctAnswers[questionNumber] == 0
                && checkBox1.isSelected()) {

            correct = true;
        }

        if (correctAnswers[questionNumber] == 1
                && checkBox2.isSelected()) {

            correct = true;
        }

        if (correctAnswers[questionNumber] == 2
                && checkBox3.isSelected()) {

            correct = true;
        }

        // Calculate points
        if (correct) {

            if (selectedCount == 1) {

                score += 5;

            }
            else if (selectedCount == 2) {

                score += 2;

            }
            else if (selectedCount == 3) {

                score += 1;
            }
        }

        // Update score
        scoreLabel.setText(
            "Score: " + score
        );

        // Move to next question
        questionNumber++;

        // Check if quiz is finished
        if (questionNumber < 5) {

            showQuestion();

        }
        else {

            showFinalResult();
        }
    }

    // Displays final result
    void showFinalResult() {

        String message;

        if (score > 21) {

            message = "Fantastic!";

        }
        else if (score > 15) {

            message = "Very good";

        }
        else {

            message = "OK";
        }

        JOptionPane.showMessageDialog(
            this,
            "Quiz finished!\n\n"
            + "Your score: "
            + score
            + " / 25\n\n"
            + message
        );

        // Disable button after quiz
        submitButton.setEnabled(false);
    }

    // Main method
    public static void main(String[] args) {

        HedgeYourBet quiz =
            new HedgeYourBet();

        quiz.setVisible(true);
    }
}