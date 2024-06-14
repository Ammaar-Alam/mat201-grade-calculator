let currentStep = "step1";
let selectedCategory = "";
let dayNumber = "";

function startStep(category) {
  selectedCategory = category;
  document.getElementById("step1").style.display = "none";
  document.getElementById("step2").style.display = "block";
  if (selectedCategory === "inClass") {
    document.getElementById("step2Heading").innerText = "Enter the Lecture Number";
  } else if (selectedCategory === "pset") {
    document.getElementById("step2Heading").innerText = "Enter the PSET Number";
  } else if (selectedCategory === "midterm") {
    document.getElementById("step2Heading").innerText = "Enter the Midterm Number";
  }
  currentStep = "step2";
}

function proceedStep2() {
  dayNumber = document.getElementById("dayNumber").value;
  if (!dayNumber) {
    alert("Please enter a valid number");
    return;
  }
  document.getElementById("step2").style.display = "none";
  document.getElementById("step3").style.display = "block";
  currentStep = "step3";
}

async function submitPoints() {
  const earnedPoints = document.getElementById("earnedPoints").value;
  if (!earnedPoints) {
    alert("Please enter the points earned");
    return;
  }

  const data = {
    category: selectedCategory,
    dayNumber,
    earnedPoints,
  };

  const response = await fetch("/update-grades", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  const result = await response.json();
  document.getElementById("step3").style.display = "none";
  document.getElementById("result").style.display = "block";
  document.getElementById("resultText").innerText = result.message;
  currentStep = "result";
}

function restart() {
  document.getElementById("step1").style.display = "block";
  document.getElementById("result").style.display = "none";
  currentStep = "step1";
}
