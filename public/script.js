document.addEventListener("DOMContentLoaded", () => {
  document.getElementById("gradeForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const earnedInClass = document.getElementById("earnedInClass").value;
    const possibleInClass = document.getElementById("possibleInClass").value;
    const earnedPSET = document.getElementById("earnedPSET").value;
    const possiblePSET = document.getElementById("possiblePSET").value;
    const earnedMidterm = document.getElementById("earnedMidterm").value;

    const grades = {
      earnedInClass,
      possibleInClass,
      earnedPSET,
      possiblePSET,
      earnedMidterm,
    };

    try {
      const response = await fetch("/calculate-grades", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(grades),
      });

      const result = await response.text();
      document.getElementById("result").innerText = `Your overall grade: ${result}`;
    } catch (error) {
      console.error("Error:", error);
      document.getElementById("result").innerText = "Error calculating grade.";
    }
  });
});
