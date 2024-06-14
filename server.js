const express = require("express");
const path = require("path");
const { exec } = require("child_process");
const app = express();

// Serve the static files from the public directory
app.use(express.static(path.join(__dirname, "public")));

// Endpoint to calculate grades
app.post("/calculate-grades", (req, res) => {
  exec("bash server/compile_and_run.sh", (error, stdout, stderr) => {
    if (error) {
      console.error(`Error executing script: ${error.message}`);
      return res.status(500).send("Internal Server Error");
    }
    if (stderr) {
      console.error(`Error in script: ${stderr}`);
      return res.status(500).send("Internal Server Error");
    }
    res.send(stdout);
  });
});

// For handling unknown routes
app.get("*", (req, res) => {
  res.sendFile(path.join(__dirname + "/public/index.html"));
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
