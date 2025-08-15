import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import javax.swing.Timer;

public class JavaQuiz extends JFrame {
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup buttonGroup;
    private JButton nextButton;
    private JLabel scoreLabel;
    private JLabel progressLabel;
    private JProgressBar progressBar;
    private JPanel mainPanel;
    private Timer animationTimer;
    
    // Color scheme
    private final Color PRIMARY_COLOR = new Color(63, 81, 181);    // Indigo
    private final Color SECONDARY_COLOR = new Color(103, 58, 183); // Deep Purple
    private final Color ACCENT_COLOR = new Color(255, 193, 7);     // Amber
    private final Color SUCCESS_COLOR = new Color(76, 175, 80);    // Green
    private final Color ERROR_COLOR = new Color(244, 67, 54);      // Red
    private final Color BACKGROUND_COLOR = new Color(250, 250, 250); // Light Gray
    private final Color CARD_COLOR = Color.WHITE;
    private final Color TEXT_COLOR = new Color(33, 33, 33);
    
    public JavaQuiz() {
        initializeQuestions();
        setupGUI();
        displayQuestion();
    }
    
    private void initializeQuestions() {
        questions = new ArrayList<>();
        
        // Unit 1-3: Interfaces & Lambda Expressions
        questions.add(new Question(
            "What new types of methods were added to interfaces in JDK 1.8?",
            new String[]{"Default and static methods", "Private and protected methods", "Abstract and final methods", "Synchronized and native methods"},
            0,
            "Unit 1-3"
        ));
        
        questions.add(new Question(
            "Why were default methods introduced in interfaces?",
            new String[]{"To make interfaces faster", "To allow adding new methods without breaking existing implementations", "To replace abstract classes", "To enable multiple inheritance"},
            1,
            "Unit 1-3"
        ));
        
        questions.add(new Question(
            "What is a functional interface (SAM)?",
            new String[]{"An interface with no methods", "An interface with multiple abstract methods", "An interface with exactly one abstract method", "An interface with only static methods"},
            2,
            "Unit 1-3"
        ));
        
        questions.add(new Question(
            "What annotation is used to declare a functional interface?",
            new String[]{"@Functional", "@FunctionalInterface", "@Lambda", "@SingleMethod"},
            1,
            "Unit 1-3"
        ));
        
        // Unit 1-4: Exceptions
        questions.add(new Question(
            "What is the root class of all exceptions and errors in Java?",
            new String[]{"Exception", "Error", "Throwable", "RuntimeException"},
            2,
            "Unit 1-4"
        ));
        
        questions.add(new Question(
            "What are the two main subclasses of Throwable?",
            new String[]{"Exception and RuntimeException", "Error and Exception", "Checked and Unchecked", "IOException and NullPointerException"},
            1,
            "Unit 1-4"
        ));
        
        questions.add(new Question(
            "What is the difference between checked and unchecked exceptions?",
            new String[]{"Checked are faster than unchecked", "Checked must be declared or handled, unchecked don't need to be", "Unchecked are more serious than checked", "There is no difference"},
            1,
            "Unit 1-4"
        ));
        
        // Unit 2-1: MVC Pattern
        questions.add(new Question(
            "What does MVC stand for?",
            new String[]{"Model-View-Controller", "Method-Variable-Class", "Main-View-Control", "Multi-Version-Concurrency"},
            0,
            "Unit 2-1"
        ));
        
        questions.add(new Question(
            "What is the responsibility of the Model in MVC?",
            new String[]{"Handle user input", "Manage data, logic, and rules", "Present data to the user", "Control the flow between components"},
            1,
            "Unit 2-1"
        ));
        
        questions.add(new Question(
            "What is the 'Oreo' MVC implementation?",
            new String[]{"Model and View communicate directly", "Controller sits between Model and View", "All components are independent", "View controls the Model"},
            1,
            "Unit 2-1"
        ));
        
        // Unit 2-2 & 2-3: JDBC
        questions.add(new Question(
            "What does JDBC stand for?",
            new String[]{"Java Database Connection", "Java Data Base Connectivity", "Java Database Connectivity", "Java Dynamic Base Connection"},
            2,
            "Unit 2-2"
        ));
        
        questions.add(new Question(
            "Which JDBC driver type is not supported after JDK 1.7?",
            new String[]{"Type 2: Native-API", "Type 3: Network Protocol", "Type 4: Thin driver", "Type 1: JDBC-ODBC Bridge"},
            3,
            "Unit 2-2"
        ));
        
        questions.add(new Question(
            "What is a PreparedStatement used for?",
            new String[]{"Execute static SQL queries", "Execute parameterized SQL queries", "Create database connections", "Handle query results"},
            1,
            "Unit 2-2"
        ));
        
        // Unit 3-1: Threads
        questions.add(new Question(
            "What are two ways to create an object that runs on its own thread?",
            new String[]{"Extend Thread or implement Runnable", "Use synchronized or volatile", "Call start() or run()", "Use ExecutorService or ThreadPool"},
            0,
            "Unit 3-1"
        ));
        
        questions.add(new Question(
            "Why should you never call run() directly on a thread?",
            new String[]{"It will throw an exception", "It will execute on the current thread, not start a new one", "It's deprecated", "It causes memory leaks"},
            1,
            "Unit 3-1"
        ));
        
        questions.add(new Question(
            "What are the five thread life cycle states?",
            new String[]{"Start, Run, Wait, Stop, End", "New, Runnable, Running, Waiting/Blocked, Terminated", "Created, Active, Sleeping, Dead, Finished", "Init, Execute, Pause, Resume, Exit"},
            1,
            "Unit 3-1"
        ));
        
        // Unit 3-2: Thread Safety
        questions.add(new Question(
            "What is the 'lost update' problem?",
            new String[]{"When threads can't find shared data", "When two threads update shared data and one overwrites the other's changes", "When updates are delayed", "When data is corrupted"},
            1,
            "Unit 3-2"
        ));
        
        questions.add(new Question(
            "What does volatile do?",
            new String[]{"Makes variables thread-safe", "Ensures a variable is read from main memory, not thread cache", "Prevents deadlocks", "Synchronizes method access"},
            1,
            "Unit 3-2"
        ));
        
        questions.add(new Question(
            "What is deadlock?",
            new String[]{"When a thread stops running", "When two or more threads wait indefinitely for each other's resources", "When threads run too slowly", "When memory runs out"},
            1,
            "Unit 3-2"
        ));
        
        // Unit 3-4: Thread Pools
        questions.add(new Question(
            "What is a thread pool?",
            new String[]{"A collection of thread objects", "A group of pre-created threads that can be reused for tasks", "A synchronized data structure", "A type of thread scheduler"},
            1,
            "Unit 3-4"
        ));
        
        questions.add(new Question(
            "What is the difference between shutdown() and shutdownNow()?",
            new String[]{"No difference", "shutdown() stops immediately, shutdownNow() waits", "shutdown() waits for current tasks, shutdownNow() stops immediately", "shutdownNow() is deprecated"},
            2,
            "Unit 3-4"
        ));
        
        // Unit 3-5: GUI Threads
        questions.add(new Question(
            "What thread does a Swing program run on?",
            new String[]{"Main thread", "Event Dispatch Thread (EDT)", "Worker thread", "Background thread"},
            1,
            "Unit 3-5"
        ));
        
        questions.add(new Question(
            "Are Swing components thread-safe?",
            new String[]{"Yes, always", "No, must update on EDT", "Only JButton and JLabel", "Depends on the component"},
            1,
            "Unit 3-5"
        ));
        
        questions.add(new Question(
            "What is a SwingWorker thread used for?",
            new String[]{"Creating GUI components", "Running long tasks in background while keeping UI responsive", "Handling mouse events", "Managing window operations"},
            1,
            "Unit 3-5"
        ));
        
        // Shuffle questions for variety
        Collections.shuffle(questions);
    }
    
    private void setupGUI() {
        setTitle("☕ Java Quiz Master - Units 1-3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Set custom icon
        try {
            setIconImage(createQuizIcon());
        } catch (Exception e) {
            // Fallback if icon creation fails
        }
        
        // Main container with gradient background
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                // Gradient background
                GradientPaint gradient = new GradientPaint(
                    0, 0, PRIMARY_COLOR,
                    0, getHeight(), SECONDARY_COLOR
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);
        
        // Header panel with modern design
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Center panel with card design
        JPanel centerPanel = createCenterPanel();
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Bottom panel with styled button
        JPanel bottomPanel = createBottomPanel();
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private Image createQuizIcon() {
        BufferedImage icon = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = icon.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw coffee cup icon
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillRoundRect(4, 8, 20, 20, 8, 8);
        g2d.setColor(new Color(160, 82, 45));
        g2d.fillRoundRect(6, 10, 16, 16, 6, 6);
        
        // Handle
        g2d.setStroke(new BasicStroke(2));
        g2d.drawArc(20, 12, 8, 8, -90, 180);
        
        g2d.dispose();
        return icon;
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        // Title with icon
        JLabel titleLabel = new JLabel("☕ Java Quiz Master", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        // Progress info panel
        JPanel progressPanel = new JPanel(new GridLayout(2, 2, 20, 5));
        progressPanel.setOpaque(false);
        
        progressLabel = new JLabel("Question 1 of " + questions.size(), JLabel.LEFT);
        progressLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressLabel.setForeground(Color.WHITE);
        
        scoreLabel = new JLabel("Score: 0/" + questions.size(), JLabel.RIGHT);
        scoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        scoreLabel.setForeground(ACCENT_COLOR);
        
        // Progress bar
        progressBar = new JProgressBar(0, questions.size());
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setString("0%");
        progressBar.setForeground(ACCENT_COLOR);
        progressBar.setBackground(new Color(255, 255, 255, 100));
        progressBar.setBorder(BorderFactory.createEmptyBorder());
        
        JLabel unitLabel = new JLabel("Units 1-3 Coverage", JLabel.CENTER);
        unitLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        unitLabel.setForeground(new Color(255, 255, 255, 200));
        
        progressPanel.add(progressLabel);
        progressPanel.add(scoreLabel);
        progressPanel.add(progressBar);
        progressPanel.add(unitLabel);
        
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(progressPanel, BorderLayout.CENTER);
        
        return headerPanel;
    }
    
    private JPanel createCenterPanel() {
        JPanel outerPanel = new JPanel();
        outerPanel.setOpaque(false);
        outerPanel.setLayout(new BorderLayout());
        outerPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        
        // Card panel with rounded corners and shadow effect
        JPanel cardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Drop shadow
                g2d.setColor(new Color(0, 0, 0, 50));
                g2d.fillRoundRect(5, 5, getWidth() - 5, getHeight() - 5, 20, 20);
                
                // Card background
                g2d.setColor(CARD_COLOR);
                g2d.fillRoundRect(0, 0, getWidth() - 5, getHeight() - 5, 20, 20);
            }
        };
        cardPanel.setOpaque(false);
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Question area
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        questionLabel.setForeground(TEXT_COLOR);
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        
        // Options panel with custom radio buttons
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 0, 15));
        optionsPanel.setOpaque(false);
        
        buttonGroup = new ButtonGroup();
        optionButtons = new JRadioButton[4];
        
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = createStyledRadioButton();
            buttonGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }
        
        cardPanel.add(questionLabel, BorderLayout.NORTH);
        cardPanel.add(optionsPanel, BorderLayout.CENTER);
        outerPanel.add(cardPanel, BorderLayout.CENTER);
        
        return outerPanel;
    }
    
    private JRadioButton createStyledRadioButton() {
        JRadioButton button = new JRadioButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Background
                if (isSelected()) {
                    g2d.setColor(new Color(PRIMARY_COLOR.getRed(), PRIMARY_COLOR.getGreen(), PRIMARY_COLOR.getBlue(), 30));
                } else if (getModel().isRollover()) {
                    g2d.setColor(new Color(PRIMARY_COLOR.getRed(), PRIMARY_COLOR.getGreen(), PRIMARY_COLOR.getBlue(), 15));
                } else {
                    g2d.setColor(new Color(245, 245, 245));
                }
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                
                // Border
                if (isSelected()) {
                    g2d.setColor(PRIMARY_COLOR);
                    g2d.setStroke(new BasicStroke(2));
                } else {
                    g2d.setColor(new Color(200, 200, 200));
                    g2d.setStroke(new BasicStroke(1));
                }
                g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
                
                // Custom radio button indicator
                int indicatorSize = 16;
                int x = 15;
                int y = (getHeight() - indicatorSize) / 2;
                
                if (isSelected()) {
                    g2d.setColor(PRIMARY_COLOR);
                    g2d.fillOval(x, y, indicatorSize, indicatorSize);
                    g2d.setColor(Color.WHITE);
                    g2d.fillOval(x + 4, y + 4, indicatorSize - 8, indicatorSize - 8);
                } else {
                    g2d.setColor(new Color(200, 200, 200));
                    g2d.drawOval(x, y, indicatorSize, indicatorSize);
                }
                
                // Text
                FontMetrics fm = g2d.getFontMetrics();
                g2d.setColor(TEXT_COLOR);
                g2d.drawString(getText(), x + indicatorSize + 15, y + (indicatorSize + fm.getAscent()) / 2);
            }
        };
        
        button.setOpaque(false);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setPreferredSize(new Dimension(0, 50));
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));
        
        nextButton = new JButton("Next Question →") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Button background with gradient
                GradientPaint gradient;
                if (getModel().isPressed()) {
                    gradient = new GradientPaint(0, 0, ACCENT_COLOR.darker(), 0, getHeight(), ACCENT_COLOR);
                } else if (getModel().isRollover()) {
                    gradient = new GradientPaint(0, 0, ACCENT_COLOR.brighter(), 0, getHeight(), ACCENT_COLOR);
                } else {
                    gradient = new GradientPaint(0, 0, ACCENT_COLOR, 0, getHeight(), ACCENT_COLOR.darker());
                }
                
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                
                // Text
                FontMetrics fm = g2d.getFontMetrics();
                g2d.setColor(Color.WHITE);
                int textX = (getWidth() - fm.stringWidth(getText())) / 2;
                int textY = (getHeight() + fm.getAscent()) / 2;
                g2d.drawString(getText(), textX, textY);
            }
        };
        
        nextButton.setPreferredSize(new Dimension(200, 50));
        nextButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nextButton.setBorder(BorderFactory.createEmptyBorder());
        nextButton.setOpaque(false);
        nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nextButton.addActionListener(e -> checkAnswer());
        
        bottomPanel.add(nextButton);
        return bottomPanel;
    }
    
    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question current = questions.get(currentQuestionIndex);
            
            // Enhanced question display with unit badge
            String unitBadge = getUnitBadge(current.getUnit());
            questionLabel.setText("<html><div style='text-align: center; padding: 10px;'>" + 
                                 unitBadge + "<br><br>" + 
                                 "<span style='font-size: 18px; line-height: 1.4;'>" + current.getQuestion() + "</span>" +
                                 "</div></html>");
            
            String[] options = current.getOptions();
            for (int i = 0; i < optionButtons.length; i++) {
                optionButtons[i].setText(" " + (char)('A' + i) + ") " + options[i]);
                optionButtons[i].setSelected(false);
                // Reset any previous styling
                optionButtons[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                optionButtons[i].setBackground(CARD_COLOR);
            }
            
            // Update progress
            progressLabel.setText("Question " + (currentQuestionIndex + 1) + " of " + questions.size());
            progressBar.setValue(currentQuestionIndex);
            int percentage = (int) ((double) currentQuestionIndex / questions.size() * 100);
            progressBar.setString(percentage + "%");
            
            // Update button text
            if (currentQuestionIndex == questions.size() - 1) {
                nextButton.setText("🏁 Finish Quiz");
            } else {
                nextButton.setText("Next Question →");
            }
            
            // Fade-in animation
            animateQuestionDisplay();
        }
    }
    
    private String getUnitBadge(String unit) {
        String badgeColor;
        String emoji;
        
        switch (unit) {
            case "Unit 1-3":
                badgeColor = "#FF6B6B";
                emoji = "🔗";
                break;
            case "Unit 1-4":
                badgeColor = "#4ECDC4";
                emoji = "⚠️";
                break;
            case "Unit 2-1":
                badgeColor = "#45B7D1";
                emoji = "🏗️";
                break;
            case "Unit 2-2":
            case "Unit 2-4":
                badgeColor = "#96CEB4";
                emoji = "🗄️";
                break;
            case "Unit 3-1":
            case "Unit 3-2":
            case "Unit 3-4":
            case "Unit 3-5":
                badgeColor = "#FECA57";
                emoji = "🧵";
                break;
            default:
                badgeColor = "#DDA0DD";
                emoji = "📚";
        }
        
        return "<span style='background-color: " + badgeColor + "; color: white; padding: 5px 10px; " +
               "border-radius: 15px; font-size: 12px; font-weight: bold;'>" + 
               emoji + " " + unit + "</span>";
    }
    
    private void animateQuestionDisplay() {
        // Simple fade-in effect for the question panel
        final float[] alpha = {0.0f};
        Timer fadeTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha[0] += 0.05f;
                if (alpha[0] >= 1.0f) {
                    alpha[0] = 1.0f;
                    ((Timer) e.getSource()).stop();
                }
                repaint();
            }
        });
        fadeTimer.start();
    }
    
    private void checkAnswer() {
        int selectedOption = -1;
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                selectedOption = i;
                break;
            }
        }
        
        if (selectedOption == -1) {
            showCustomMessage("⚠️ Please select an answer!", "No Selection", ERROR_COLOR);
            return;
        }
        
        Question current = questions.get(currentQuestionIndex);
        boolean isCorrect = selectedOption == current.getCorrectAnswer();
        
        // Visual feedback for correct/incorrect answer
        animateAnswerFeedback(selectedOption, isCorrect);
        
        if (isCorrect) {
            score++;
            showCustomMessage("🎉 CORRECT ANSWER!", "✓ You Got It Right!", SUCCESS_COLOR);
        } else {
            String correctOption = (char)('A' + current.getCorrectAnswer()) + ") " + 
                                 current.getOptions()[current.getCorrectAnswer()];
            showCustomMessage("❌ WRONG ANSWER!\n\nThe correct answer is:\n" + correctOption + 
                            "\n\n💡 Study this topic more!", 
                            "✗ Incorrect Answer", ERROR_COLOR);
        }
        
        scoreLabel.setText("Score: " + score + "/" + questions.size());
        currentQuestionIndex++;
        
        // Update progress bar
        progressBar.setValue(currentQuestionIndex);
        int percentage = (int) ((double) currentQuestionIndex / questions.size() * 100);
        progressBar.setString(percentage + "%");
        
        if (currentQuestionIndex < questions.size()) {
            Timer delayTimer = new Timer(1500, e -> {
                displayQuestion();
                ((Timer) e.getSource()).stop();
            });
            delayTimer.start();
        } else {
            Timer delayTimer = new Timer(1500, e -> {
                showFinalResults();
                ((Timer) e.getSource()).stop();
            });
            delayTimer.start();
        }
    }
    
    private void animateAnswerFeedback(int selectedIndex, boolean isCorrect) {
        // Enhanced visual feedback for answers
        Color feedbackColor = isCorrect ? SUCCESS_COLOR : ERROR_COLOR;
        String feedbackText = isCorrect ? "✓ CORRECT!" : "✗ WRONG!";
        
        // Create a temporary feedback label that appears over the selected answer
        JLabel feedbackLabel = new JLabel(feedbackText, JLabel.CENTER);
        feedbackLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        feedbackLabel.setForeground(Color.WHITE);
        feedbackLabel.setOpaque(true);
        feedbackLabel.setBackground(feedbackColor);
        feedbackLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        // Position the feedback label over the selected answer
        JPanel parentPanel = (JPanel) optionButtons[selectedIndex].getParent();
        parentPanel.setLayout(null); // Temporarily use absolute positioning
        parentPanel.add(feedbackLabel);
        
        // Calculate position to center over the selected button
        int buttonX = optionButtons[selectedIndex].getX();
        int buttonY = optionButtons[selectedIndex].getY();
        int buttonWidth = optionButtons[selectedIndex].getWidth();
        int buttonHeight = optionButtons[selectedIndex].getHeight();
        
        feedbackLabel.setBounds(buttonX + buttonWidth/2 - 50, buttonY + buttonHeight/2 - 15, 100, 30);
        
        // Animate the feedback label
        Timer pulseTimer = new Timer(100, new ActionListener() {
            int pulseCount = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pulseCount < 8) {
                    // Pulse effect with the feedback label
                    if (pulseCount % 2 == 0) {
                        feedbackLabel.setBackground(feedbackColor);
                        optionButtons[selectedIndex].setBackground(feedbackColor);
                    } else {
                        feedbackLabel.setBackground(feedbackColor.darker());
                        optionButtons[selectedIndex].setBackground(CARD_COLOR);
                    }
                    pulseCount++;
                    repaint();
                } else {
                    // Remove feedback label and restore layout
                    parentPanel.remove(feedbackLabel);
                    parentPanel.setLayout(new GridLayout(4, 1, 0, 15));
                    optionButtons[selectedIndex].setBackground(CARD_COLOR);
                    ((Timer) e.getSource()).stop();
                    repaint();
                }
            }
        });
        pulseTimer.start();
        
        // Also highlight the correct answer if wrong
        if (!isCorrect) {
            Question current = questions.get(currentQuestionIndex);
            int correctIndex = current.getCorrectAnswer();
            highlightCorrectAnswer(correctIndex);
        }
    }
    
    private void highlightCorrectAnswer(int correctIndex) {
        // Highlight the correct answer with a green border and checkmark
        Timer highlightTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionButtons[correctIndex].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(SUCCESS_COLOR, 3),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
                ((Timer) e.getSource()).stop();
            }
        });
        highlightTimer.start();
    }
    
    private void showCustomMessage(String message, String title, Color backgroundColor) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        
        JPanel messagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradient background
                GradientPaint gradient = new GradientPaint(
                    0, 0, backgroundColor.brighter(),
                    0, getHeight(), backgroundColor
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        messagePanel.setLayout(new BorderLayout());
        
        JLabel messageLabel = new JLabel("<html><div style='text-align: center; padding: 20px;'>" + 
                                       message.replace("\n", "<br>") + "</div></html>");
        messageLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        
        JButton okButton = new JButton("Continue") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                
                FontMetrics fm = g2d.getFontMetrics();
                g2d.setColor(backgroundColor.darker());
                int textX = (getWidth() - fm.stringWidth(getText())) / 2;
                int textY = (getHeight() + fm.getAscent()) / 2;
                g2d.drawString(getText(), textX, textY);
            }
        };
        okButton.setPreferredSize(new Dimension(100, 35));
        okButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        okButton.setBorder(BorderFactory.createEmptyBorder());
        okButton.setOpaque(false);
        okButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        okButton.addActionListener(e -> dialog.dispose());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(okButton);
        
        messagePanel.add(messageLabel, BorderLayout.CENTER);
        messagePanel.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.add(messagePanel);
        dialog.setVisible(true);
    }
    
    private void showFinalResults() {
        double percentage = (double) score / questions.size() * 100;
        String grade;
        String emoji;
        String message;
        Color gradeColor;
        
        if (percentage >= 90) {
            grade = "A";
            emoji = "🏆";
            message = "Outstanding Performance!";
            gradeColor = new Color(76, 175, 80);
        } else if (percentage >= 80) {
            grade = "B";
            emoji = "🎯";
            message = "Great Job!";
            gradeColor = new Color(139, 195, 74);
        } else if (percentage >= 70) {
            grade = "C";
            emoji = "👍";
            message = "Good Effort!";
            gradeColor = new Color(255, 193, 7);
        } else if (percentage >= 60) {
            grade = "D";
            emoji = "📖";
            message = "Keep Studying!";
            gradeColor = new Color(255, 152, 0);
        } else {
            grade = "F";
            emoji = "💪";
            message = "Practice Makes Perfect!";
            gradeColor = new Color(244, 67, 54);
        }
        
        showFinalResultsDialog(score, questions.size(), percentage, grade, emoji, message, gradeColor);
    }
    
    private void showFinalResultsDialog(int score, int total, double percentage, String grade, 
                                      String emoji, String message, Color gradeColor) {
        JDialog resultsDialog = new JDialog(this, "Quiz Results", true);
        resultsDialog.setSize(500, 400);
        resultsDialog.setLocationRelativeTo(this);
        resultsDialog.setResizable(false);
        
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradient background
                GradientPaint gradient = new GradientPaint(
                    0, 0, PRIMARY_COLOR,
                    0, getHeight(), SECONDARY_COLOR
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        
        // Results card
        JPanel cardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Drop shadow
                g2d.setColor(new Color(0, 0, 0, 50));
                g2d.fillRoundRect(5, 5, getWidth() - 5, getHeight() - 5, 25, 25);
                
                // Card background
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth() - 5, getHeight() - 5, 25, 25);
            }
        };
        cardPanel.setOpaque(false);
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        // Header
        JLabel headerLabel = new JLabel(emoji + " Quiz Complete!", JLabel.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        headerLabel.setForeground(PRIMARY_COLOR);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Results panel
        JPanel resultsPanel = new JPanel(new GridLayout(4, 1, 0, 15));
        resultsPanel.setOpaque(false);
        
        JLabel scoreLabel = new JLabel("Score: " + score + "/" + total, JLabel.CENTER);
        scoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        scoreLabel.setForeground(TEXT_COLOR);
        
        JLabel percentageLabel = new JLabel(String.format("%.1f%%", percentage), JLabel.CENTER);
        percentageLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        percentageLabel.setForeground(gradeColor);
        
        JLabel gradeLabel = new JLabel("Grade: " + grade, JLabel.CENTER);
        gradeLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        gradeLabel.setForeground(gradeColor);
        
        JLabel messageLabel = new JLabel(message, JLabel.CENTER);
        messageLabel.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        messageLabel.setForeground(TEXT_COLOR);
        
        resultsPanel.add(scoreLabel);
        resultsPanel.add(percentageLabel);
        resultsPanel.add(gradeLabel);
        resultsPanel.add(messageLabel);
        
        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonsPanel.setOpaque(false);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        JButton retakeButton = createStyledButton("🔄 Take Again", SUCCESS_COLOR);
        retakeButton.addActionListener(e -> {
            resultsDialog.dispose();
            restartQuiz();
        });
        
        JButton exitButton = createStyledButton("🚪 Exit", ERROR_COLOR);
        exitButton.addActionListener(e -> System.exit(0));
        
        buttonsPanel.add(retakeButton);
        buttonsPanel.add(exitButton);
        
        cardPanel.add(headerLabel, BorderLayout.NORTH);
        cardPanel.add(resultsPanel, BorderLayout.CENTER);
        cardPanel.add(buttonsPanel, BorderLayout.SOUTH);
        
        mainPanel.add(cardPanel, BorderLayout.CENTER);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        resultsDialog.add(mainPanel);
        resultsDialog.setVisible(true);
    }
    
    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                Color color = backgroundColor;
                if (getModel().isPressed()) {
                    color = backgroundColor.darker();
                } else if (getModel().isRollover()) {
                    color = backgroundColor.brighter();
                }
                
                g2d.setColor(color);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                
                FontMetrics fm = g2d.getFontMetrics();
                g2d.setColor(Color.WHITE);
                int textX = (getWidth() - fm.stringWidth(getText())) / 2;
                int textY = (getHeight() + fm.getAscent()) / 2;
                g2d.drawString(getText(), textX, textY);
            }
        };
        
        button.setPreferredSize(new Dimension(130, 40));
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setOpaque(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    private void restartQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        Collections.shuffle(questions);
        scoreLabel.setText("Score: 0/" + questions.size());
        progressBar.setValue(0);
        progressBar.setString("0%");
        nextButton.setText("Next Question →");
        displayQuestion();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JavaQuiz().setVisible(true);
            }
        });
    }
}

class Question {
    private String question;
    private String[] options;
    private int correctAnswer;
    private String unit;
    
    public Question(String question, String[] options, int correctAnswer, String unit) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.unit = unit;
    }
    
    public String getQuestion() { return question; }
    public String[] getOptions() { return options; }
    public int getCorrectAnswer() { return correctAnswer; }
    public String getUnit() { return unit; }
}
