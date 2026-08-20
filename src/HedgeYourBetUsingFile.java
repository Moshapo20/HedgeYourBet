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
import java.io.*;

public class HedgeYourBetUsingFile extends JFrame {

    // Question number
    int questionNumber = 0;

    // Current score
    int score = 0;

    // Previous score
    int previousScore = 0;

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
    JLabel previousScoreLabel;
    JLabel scoreLabel;

    JCheckBox checkBox1;
    JCheckBox checkBox2;
    JCheckBox checkBox3;

    JButton submitButton;
    JButton playAgainButton;

    // Panel containing question and answers
    JPanel contentPanel;

    public HedgeYourBetUsingFile() {

        // Window title
        setTitle("Hedge Your Bet Quiz");

        // Window size
        setSize(500, 450);

        // Close program when window closes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center window
        setLocationRelativeTo(null);

        // Read previous score from file
        readPreviousScore();

        // =====================================================
        // MAIN PANEL
        // =====================================================

        JPanel panel = new JPanel();

        panel.setLayout(
            new BoxLayout(panel, BoxLayout.Y_AXIS)
        );

        // =====================================================
        // PREVIOUS SCORE
        // =====================================================

        previousScoreLabel =
            new JLabel(
                "Previous Score: " + previousScore
            );

        previousScoreLabel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // CONTENT PANEL
        // =====================================================
        // This keeps the question and checkboxes aligned.

        contentPanel = new JPanel();

        contentPanel.setLayout(
            new BoxLayout(
                contentPanel,
                BoxLayout.Y_AXIS
            )
        );

        // Set the width of the content area
        contentPanel.setPreferredSize(
            new Dimension(440, 180)
        );

        contentPanel.setMaximumSize(
            new Dimension(440, 180)
        );

        // =====================================================
        // QUESTION
        // =====================================================

        questionLabel = new JLabel();

        // Align question to the left
        questionLabel.setAlignmentX(
            Component.LEFT_ALIGNMENT
        );

        // =====================================================
        // CHECKBOXES
        // =====================================================

        checkBox1 = new JCheckBox();
        checkBox2 = new JCheckBox();
        checkBox3 = new JCheckBox();

        // Align all checkboxes to the left
        checkBox1.setAlignmentX(
            Component.LEFT_ALIGNMENT
        );

        checkBox2.setAlignmentX(
            Component.LEFT_ALIGNMENT
        );

        checkBox3.setAlignmentX(
            Component.LEFT_ALIGNMENT
        );

        // Add question
        contentPanel.add(
            questionLabel
        );

        // Space between question and answers
        contentPanel.add(
            Box.createVerticalStrut(15)
        );

        // Add checkboxes
        contentPanel.add(
            checkBox1
        );

        contentPanel.add(
            checkBox2
        );

        contentPanel.add(
            checkBox3
        );

        // =====================================================
        // SUBMIT BUTTON
        // =====================================================

        submitButton =
            new JButton("Submit Answer");

        submitButton.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // PLAY AGAIN BUTTON
        // =====================================================

        playAgainButton =
            new JButton("Play Again");

        playAgainButton.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        // Hide Play Again button at the beginning
        playAgainButton.setVisible(false);

        // =====================================================
        // CURRENT SCORE
        // =====================================================

        scoreLabel =
            new JLabel("Current Score: 0");

        scoreLabel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // ADD COMPONENTS TO MAIN PANEL
        // =====================================================

        panel.add(
            Box.createVerticalStrut(20)
        );

        panel.add(
            previousScoreLabel
        );

        panel.add(
            Box.createVerticalStrut(20)
        );

        // Add question and checkboxes
        panel.add(
            contentPanel
        );

        panel.add(
            Box.createVerticalStrut(20)
        );

        panel.add(
            submitButton
        );

        panel.add(
            Box.createVerticalStrut(10)
        );

        panel.add(
            playAgainButton
        );

        panel.add(
            Box.createVerticalStrut(20)
        );

        panel.add(
            scoreLabel
        );

        // Add main panel to window
        add(panel);

        // =====================================================
        // DISPLAY FIRST QUESTION
        // =====================================================

        showQuestion();

        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        submitButton.addActionListener(
            e -> checkAnswer()
        );

        playAgainButton.addActionListener(
            e -> startNewGame()
        );
    }

    // =========================================================
    // READ PREVIOUS SCORE
    // =========================================================

    void readPreviousScore() {

        try {

            File file =
                new File("score.txt");

            // Check if file exists
            if (file.exists()) {

                BufferedReader reader =
                    new BufferedReader(
                        new FileReader(file)
                    );

                String line =
                    reader.readLine();

                if (line != null) {

                    previousScore =
                        Integer.parseInt(line);
                }

                reader.close();
            }

        }
        catch (IOException e) {

            previousScore = 0;

        }
        catch (NumberFormatException e) {

            previousScore = 0;
        }
    }

    // =========================================================
    // DISPLAY QUESTION
    // =========================================================

    void showQuestion() {

        questionLabel.setText(
            "Question "
            + (questionNumber + 1)
            + ": "
            + questions[questionNumber]
        );

        // Set answer choices
        checkBox1.setText(
            answers[questionNumber][0]
        );

        checkBox2.setText(
            answers[questionNumber][1]
        );

        checkBox3.setText(
            answers[questionNumber][2]
        );

        // Clear previous selections
        checkBox1.setSelected(false);
        checkBox2.setSelected(false);
        checkBox3.setSelected(false);
    }

    // =========================================================
    // CHECK ANSWER
    // =========================================================

    void checkAnswer() {

        int selectedCount = 0;

        // Count selected checkboxes
        if (checkBox1.isSelected()) {
            selectedCount++;
        }

        if (checkBox2.isSelected()) {
            selectedCount++;
        }

        if (checkBox3.isSelected()) {
            selectedCount++;
        }

        // Determine if the correct answer was selected
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

        // =====================================================
        // AWARD POINTS
        // =====================================================

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

        // Update current score
        scoreLabel.setText(
            "Current Score: " + score
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

    // =========================================================
    // FINAL RESULT
    // =========================================================

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

        // Save current score
        saveScore();

        // =====================================================
        // IMPORTANT FIX
        // Update previous score immediately
        // =====================================================

        previousScore = score;

        previousScoreLabel.setText(
            "Previous Score: " + previousScore
        );

        // Show result
        JOptionPane.showMessageDialog(
            this,
            "Quiz finished!\n\n"
            + "Your score: "
            + score
            + " / 25\n\n"
            + message
        );

        // Disable Submit button
        submitButton.setEnabled(false);

        // Show Play Again button
        playAgainButton.setVisible(true);
    }

    // =========================================================
    // START NEW GAME
    // =========================================================

    void startNewGame() {

        // Reset question number
        questionNumber = 0;

        // Reset current score
        score = 0;

        // Update current score
        scoreLabel.setText(
            "Current Score: 0"
        );

        // Keep previous score
        previousScoreLabel.setText(
            "Previous Score: " + previousScore
        );

        // Enable Submit button
        submitButton.setEnabled(true);

        // Hide Play Again button
        playAgainButton.setVisible(false);

        // Show first question
        showQuestion();
    }

    // =========================================================
    // SAVE SCORE TO FILE
    // =========================================================

    void saveScore() {

        try {

            FileWriter writer =
                new FileWriter("score.txt");

            writer.write(
                Integer.toString(score)
            );

            writer.close();

        }
        catch (IOException e) {

            JOptionPane.showMessageDialog(
                this,
                "Error saving score."
            );
        }
    }

    // =========================================================
    // MAIN METHOD
    // =========================================================

    public static void main(String[] args) {

        HedgeYourBetUsingFile quiz =
            new HedgeYourBetUsingFile();

        quiz.setVisible(true);
    }
}