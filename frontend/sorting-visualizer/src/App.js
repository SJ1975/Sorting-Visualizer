import React, { useState } from "react";
import SortingVisualizer from "./components/SortingVisualizer";
import SortingHistory from "./components/SortingHistory";

const App = () => {
  const [showHistory, setShowHistory] = useState(false);

  return (
    <div className="p-6">
      <h1 className="text-3xl font-bold text-center mb-6">Sorting Visualizer</h1>

      <div className="flex justify-center gap-4 mb-6">
        <button onClick={() => setShowHistory(false)} className="bg-blue-500 text-white px-4 py-2 rounded">
          Sorting Visualizer
        </button>

        <button onClick={() => setShowHistory(true)} className="bg-green-500 text-white px-4 py-2 rounded">
          Sorting History
        </button>
      </div>

      {showHistory ? <SortingHistory /> : <SortingVisualizer />}
    </div>
  );
};

export default App;
