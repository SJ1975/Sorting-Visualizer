import React, { useState, useEffect } from "react";

const SortingVisualizer = () => {
  const [array, setArray] = useState([]);
  const [sorting, setSorting] = useState(false);
  const [speed, setSpeed] = useState(100);
  const [arraySize, setArraySize] = useState(20);
  const [algorithm, setAlgorithm] = useState("bubble");
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

    try {
      const response = await fetch(`http://localhost:8080/api/sorting/${algorithm}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ array }),
      });

      if (!response.ok) {
        throw new Error("Failed to sort array");
      }

      const sortedArray = await response.json();
      setArray(sortedArray);

     // Save sorting history in MongoDB
    await fetch("http://localhost:8080/api/history/save", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        userId: "user123", // Replace with actual logged-in user ID when authentication is added
        algorithm: algorithm,
        sortedArray: sortedArray,
      }),
    });

      console.log("Sorting history saved!");
    } catch (error) {
      console.error("Sorting error:", error);
    }

    setSorting(false);
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
          <option value="bubble">Bubble Sort</option>
          <option value="selection">Selection Sort</option>
          <option value="insertion">Insertion Sort</option>
          <option value="merge">Merge Sort</option>
          <option value="quick">Quick Sort</option>
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
