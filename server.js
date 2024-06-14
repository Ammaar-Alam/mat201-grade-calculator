const express = require("express");
const path = require("path");
const bodyParser = require("body-parser");
const { exec } = require("child_process");
const fs = require("fs");

const app = express();
app.use(bodyParser.json());
app.use(express.static(path.join(__dirname, "public")));

app.post("/update-grades", (req, res) => {
  const { category, dayNumber, earnedPoints } = req.body;

  function updateCSV() {
    if (category === "inClass") {
      const lectures = fs
        .readFileSync(path.join(__dirname, "server/lectures.csv"))
        .toString();
      const lines = lectures.split("\n");
      const updatedLines = lines.map((line) => {
        if (line.startsWith(dayNumber + ",")) {
          const parts = line.split(",");
          parts[4] = earnedPoints; // assuming earned points is the fifth column
          return parts.join(",");
        }
        return line;
      });
      fs.writeFileSync(
        path.join(__dirname, "server/lectures.csv"),
        updatedLines.join("\n"),
      );
    } else if (category === "pset") {
      const psets = fs
        .readFileSync(path.join(__dirname, "server/psets.csv"))
        .toString();
      const lines = psets.split("\n");
      const updatedLines = lines.map((line, index) => {
        if (index == dayNumber) {
          const parts = line.split(" ");
          parts[0] = earnedPoints; // assuming earned points is the first column
          return parts.join(" ");
        }
        return line;
      });
      fs.writeFileSync(
        path.join(__dirname, "server/psets.csv"),
        updatedLines.join("\n"),
      );
    } else if (category === "midterm") {
      const grades = fs
        .readFileSync(path.join(__dirname, "server/grades.csv"))
        .toString();
      const lines = grades.split("\n");
      lines[3] = `0 ${earnedPoints} 0`; // Assuming midterm info is on the fourth line
      fs.writeFileSync(path.join(__dirname, "server/grades.csv"), lines.join("\n"));
    }
  }

  updateCSV();

  exec("bash server/compile_and_run.sh", (error, stdout, stderr) => {
    if (error) {
      console.error(`Error executing script: ${error.message}`);
      return res.status(500).json({ message: "Internal Server Error" });
    }
    if (stderr) {
      console.error(`Error in script: ${stderr}`);
      return res.status(500).json({ message: "Internal Server Error" });
    }
    res.json({ message: stdout });
  });
});

app.get("*", (req, res) => {
  res.sendFile(path.join(__dirname + "/public/index.html"));
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
