import React, { useState, useEffect } from "react";

const SortingVisualizer = () => {
  const [array, setArray] = useState([]);
  const [sorting, setSorting] = useState(false);
  const [speed, setSpeed] = useState(100);
  const [arraySize, setArraySize] = useState(20);
  const [algorithm, setAlgorithm] = useState("Bubble");
  const backendURL = "http://localhost:8080"; // Backend URL

  useEffect(() => {
    generateArray();
  }, [arraySize]);

  // Generate a new random array
  const generateArray = () => {
    if (sorting) return;
    const newArray = Array.from({ length: arraySize }, () => Math.floor(Math.random() * 100));
    setArray(newArray);
  };

  // Send array to backend for sorting
  const handleSort = async () => {
    if (sorting) return;
    setSorting(true);

    const originalArray = [...array]; // Store original array before sorting
    const startTime = performance.now(); // Track sorting start time

    try {
      const response = await fetch(`${backendURL}/api/sorting/{algorithm}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ array: array, algorithm: algorithm }) // ✅ Corrected request format
      });

      if (!response.ok) {
        throw new Error(`Failed to sort array: ${response.statusText}`);
      }

      const data = await response.json();
      const sortedArray = data.sortedArray; // ✅ Extract sorted array from response
      setArray(sortedArray); // ✅ Update frontend with sorted array

      const endTime = performance.now();
      const timeTaken = Math.round(endTime - startTime);

      console.log("Sorting successful:", sortedArray);

      // Save sorting history in MongoDB
      await saveSortingHistory(originalArray, sortedArray, timeTaken);
    } catch (error) {
      console.error("Sorting error:", error);
    }

    setSorting(false);
  };

  // Save Sorting History to Backend
  const saveSortingHistory = async (originalArray, sortedArray, timeTaken) => {
    try {
      const response = await fetch(`${backendURL}/api/history/save`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          userId: "user123", // Replace with actual user ID (when authentication is added)
          algorithm: algorithm,
          originalArray: originalArray,
          sortedArray: sortedArray,
          timeTaken: timeTaken,
          timestamp: new Date().toISOString(),
        }),
      });

      if (!response.ok) {
        throw new Error("Failed to save sorting history");
      }

      console.log("Sorting history saved successfully!");
    } catch (error) {
      console.error("Error saving sorting history:", error);
    }
  };

  return (
    <div className="p-6">
      <h2 className="text-2xl font-bold text-center mb-4">Sorting Visualizer</h2>

      {/* Controls */}
      <div className="flex flex-wrap justify-center gap-4 mb-6">
        <button onClick={generateArray} disabled={sorting} className="bg-blue-500 text-white px-4 py-2 rounded">
          Generate Array
        </button>

        {/* Algorithm Selection Dropdown */}
        <select
          value={algorithm}
          onChange={(e) => setAlgorithm(e.target.value)}
          className="bg-gray-200 px-4 py-2 rounded"
          disabled={sorting}
        >
          <option value="Bubble">Bubble Sort</option>
          <option value="Selection">Selection Sort</option>
          <option value="Insertion">Insertion Sort</option>
          <option value="Merge">Merge Sort</option>
          <option value="Quick">Quick Sort</option>
        </select>

        <button onClick={handleSort} disabled={sorting} className="bg-green-500 text-white px-4 py-2 rounded">
          Start Sorting
        </button>
      </div>

      {/* Speed and Array Size Controls */}
      <div className="text-center mb-6">
        <label className="mr-2">Speed:</label>
        <input
          type="range"
          min="10"
          max="500"
          value={speed}
          onChange={(e) => setSpeed(Number(e.target.value))}
          className="mr-6"
          disabled={sorting}
        />
        <label className="mr-2">Array Size:</label>
        <input
          type="range"
          min="5"
          max="50"
          value={arraySize}
          onChange={(e) => setArraySize(Number(e.target.value))}
          disabled={sorting}
        />
      </div>

      {/* Sorting Bars */}
      <div className="flex gap-2 justify-center">
        {array.map((num, idx) => (
          <div
            key={idx}
            className="w-6 text-white text-center transition-all duration-300 bg-blue-500"
            style={{ height: `${num * 3}px` }}
          >
            {num}
          </div>
        ))}
      </div>
    </div>
  );
};

export default SortingVisualizer;
