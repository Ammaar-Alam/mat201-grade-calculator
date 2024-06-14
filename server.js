const express = require("express");
const http = require("http");
const path = require("path");
const { exec } = require("child_process");

const app = express();
const server = http.createServer(app);

app.use(express.static(path.join(__dirname, "public")));
app.use(express.json());

app.post("/update-grades", (req, res) => {
  const { category, dayNumber, earnedPoints } = req.body;
  console.log("Received request to update grades:", req.body);

  // Run the Java program to update grades
  const command = `bash server/compile_and_run.sh ${category} ${dayNumber} ${earnedPoints}`;
  exec(command, (error, stdout, stderr) => {
    if (error) {
      console.error(`Error executing script: ${error.message}`);
      console.error(`Script stderr: ${stderr}`);
      res.json({ success: false, message: "Internal Server Error" });
      return;
    }
    if (stderr) {
      console.error(`Script stderr: ${stderr}`);
      res.json({ success: false, message: "Internal Server Error" });
      return;
    }
    console.log(`Script stdout: ${stdout}`);
    res.json({ success: true, message: "Grades updated successfully" });
  });
});

const PORT = process.env.PORT || 3000;
server.listen(PORT, () => console.log(`Server is running on port ${PORT}`));
