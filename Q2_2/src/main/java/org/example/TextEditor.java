package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
public class TextEditor  extends JFrame {
    private JTextArea textArea;
    private JButton saveButton;

    public TextEditor() {
        setTitle("Text Editor");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create text component
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea); // Add scrolling
        saveButton = new JButton("Save");

        // Set layout and add components
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);

        // Add save button action listener
        saveButton.addActionListener(new SaveButtonListener());
    }
    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // check input whether is empty
            if (textArea.getText().trim().isEmpty()) {
                DialogHelper.showWarningDialog(TextEditor.this,
                        "The text area is empty. Please enter some content before saving.");
                return;
            }

            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(TextEditor.this);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    FileValidator.validatePermissions(file);
                    saveToFile(file);
                } catch (IOException ex) {
                    DialogHelper.showErrorDialog(TextEditor.this, ex.getMessage());
                }
            } else {
                DialogHelper.showWarningDialog(TextEditor.this, "File save cancelled.");
            }
        }

        // Method to save the text to target file
        private void saveToFile(File file) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                DialogHelper.showInfoDialog(TextEditor.this, "File saved successfully.");
            } catch (IOException ex) {
                DialogHelper.showErrorDialog(TextEditor.this,
                        "Error saving file: " + ex.getMessage());
            }
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TextEditor editor = new TextEditor();
            editor.setVisible(true);
        });}
}
