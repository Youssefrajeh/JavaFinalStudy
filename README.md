# ☕ Java Quiz Master

An interactive, visually stunning Java quiz application covering Units 1-3 of Java programming concepts.

## 🎯 Features

- **23 Interactive Questions** covering Java fundamentals
- **Beautiful Modern UI** with gradient backgrounds and animations
- **Real-time Feedback** with visual indicators for correct/incorrect answers
- **Progress Tracking** with animated progress bar
- **Unit-based Organization** with color-coded badges
- **Responsive Design** with custom-styled components
- **Score Calculation** with grade-based results

## 📚 Topics Covered

### Unit 1-3: Interfaces & Lambda Expressions
- Default and static methods
- Functional interfaces (SAM)
- Lambda expressions
- @FunctionalInterface annotation

### Unit 1-4: Exception Handling
- Exception hierarchy
- Checked vs unchecked exceptions
- Try-catch-finally blocks
- Custom exceptions

### Unit 2: MVC Pattern & JDBC
- Model-View-Controller architecture
- Java Database Connectivity
- JDBC drivers and statements
- ResultSet handling

### Unit 3: Multithreading
- Thread creation and lifecycle
- Thread safety and synchronization
- Thread pools and executors
- GUI threading with Swing

## 🛠️ Requirements

- **Java 8 or higher** (JDK 1.8+)
- **Git** for version control
- **Any modern IDE** (IntelliJ IDEA, Eclipse, VS Code)

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/Youssefrajeh/JavaFinalStudy.git
cd JavaFinalStudy
```

### 2. Compile the Application
```bash
javac JavaQuiz.java
```

### 3. Run the Quiz
```bash
java JavaQuiz
```

## 🎨 Customization

### Adding New Questions
Edit the `initializeQuestions()` method in `JavaQuiz.java`:

```java
questions.add(new Question(
    "Your question here?",
    new String[]{"Option A", "Option B", "Option C", "Option D"},
    0, // Correct answer index (0-3)
    "Unit X-Y" // Unit identifier
));
```

### Changing Colors
Modify the color scheme constants in the `JavaQuiz` class:

```java
private final Color PRIMARY_COLOR = new Color(63, 81, 181);    // Indigo
private final Color SECONDARY_COLOR = new Color(103, 58, 183); // Deep Purple
private final Color ACCENT_COLOR = new Color(255, 193, 7);     // Amber
```

## 📁 Project Structure

```
JavaFinalStudy/
├── JavaQuiz.java          # Main application file
├── Java_Quiz_Study_Guide.md  # Study guide with all questions
├── README.md              # This file
├── .gitignore            # Git ignore file
├── LICENSE               # License file
└── docs/                 # Documentation (optional)
    └── screenshots/      # Application screenshots
```

## 🔧 Development

### Building from Source
1. Ensure you have Java 8+ installed
2. Clone the repository
3. Compile: `javac JavaQuiz.java`
4. Run: `java JavaQuiz`

### IDE Setup
- **IntelliJ IDEA**: Open the project folder directly
- **Eclipse**: Import as Java project
- **VS Code**: Install Java extension pack

## 📸 Screenshots

The application features:
- Gradient backgrounds with modern card design
- Animated feedback for answers
- Progress tracking with visual indicators
- Unit-based question organization
- Professional results display

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Commit changes: `git commit -am 'Add feature'`
4. Push to branch: `git push origin feature-name`
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Java Swing framework for the GUI
- Study materials from INFO-3136 Mobile Development course
- Community feedback and suggestions

## 📞 Support

If you encounter any issues or have questions:
1. Check the existing issues
2. Create a new issue with detailed description
3. Include your Java version and operating system

---

**Happy Learning! ☕📚**
