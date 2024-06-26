# MAT201 Grade Calculator

## Overview

The MAT201 Grade Calculator is a web application designed to help students keep track of their grades in various categories such as in-class work, PSETs, and midterms. The application allows users to input grades, and it calculates and displays their current standing in the course.

## Features

- **User-Friendly Interface**: Simple and intuitive UI for entering and updating grades.
- **Grade Calculation**: Automatically calculates grades for in-class work, PSETs, and midterms.
- **Real-Time Updates**: Updates grades and displays the results immediately after submission.
- **Room Management**: Users can create and join rooms, which can be password-protected.

## Directory Structure

```
mat201-grade-calculator/
├── node_modules/
├── out/
├── public/
│   ├── index.html
│   ├── script.js
│   └── styles.css
├── server/
│   ├── libs/
│   │   └── algs4.jar
│   ├── compile_and_run.sh
│   ├── GradeCalculator.java
│   ├── grades.csv
│   ├── LectureInfo.java
│   ├── lectures.csv
│   ├── PsetInfo.java
│   └── psets.csv
├── .gitignore
├── package.json
└── server.js
```

## Getting Started

### Prerequisites

- Node.js and npm
- Java Development Kit (JDK)
- Git

### Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/mat201-grade-calculator.git
   cd mat201-grade-calculator
   ```

2. **Install Dependencies**
   ```bash
   npm install
   ```

3. **Ensure Java Libraries**
   Make sure `algs4.jar` is in the `server/libs` directory.

### Running the Application

1. **Start the Server**
   ```bash
   npm start
   ```

2. **Access the Application**
   Open your browser and go to `http://localhost:3000`

## Usage

1. **Navigate to the Homepage**
   Open `http://localhost:3000` in your web browser.

2. **Select Grade Category**
   Click on the button for the grade category you want to update (In-Class Work, PSET, Midterm).

3. **Enter Details**
   - For in-class work, enter the lecture number and points earned.
   - For PSETs, enter the PSET number and points earned.
   - For midterms, enter the midterm number and points earned.

4. **Submit Grades**
   Click the submit button to update your grades. The application will display your updated grades and current standing.

## Files and Scripts

### `index.html`
The main HTML file for the user interface.

### `script.js`
Contains the client-side JavaScript for handling user interactions and sending requests to the server.

### `styles.css`
Contains the CSS for styling the web application.

### `server.js`
The main server file using Node.js and Express. Handles incoming requests and serves static files.

### `compile_and_run.sh`
A shell script for compiling and running the Java grade calculator.

### `GradeCalculator.java`
The main Java class that performs grade calculations.

### `LectureInfo.java`
A Java class that represents lecture information.

### `PsetInfo.java`
A Java class that represents PSET information.

### `grades.csv`, `lectures.csv`, `psets.csv`
CSV files that store the grades and information about lectures and PSETs.

## Troubleshooting

### Common Issues

- **Server Not Starting**: Ensure all dependencies are installed and the `algs4.jar` file is in the correct location.
- **Compilation Errors**: Check the paths in `compile_and_run.sh` and ensure the Java files are in the `server` directory.
- **Internal Server Error**: Check the server logs for detailed error messages.

### Debugging Tips

- **Check Logs**: Always check the console and server logs for errors.
- **Verify Paths**: Ensure all file paths in scripts and imports are correct.
- **Manual Compilation**: Try compiling and running the Java files manually to identify issues.

## Contributing

1. **Fork the Repository**
2. **Create a Branch**
   ```bash
   git checkout -b feature-branch
   ```
3. **Commit Changes**
   ```bash
   git commit -m "Description of changes"
   ```
4. **Push to Branch**
   ```bash
   git push origin feature-branch
   ```
5. **Create a Pull Request**

## License

This project is licensed under the MIT License.

---
