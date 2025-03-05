import React from "react";
import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import SortingVisualizer from "./components/SortingVisualizer";
import SortingHistory from "./components/SortingHistory";

function App() {
  return (
    <Router>
      <div className="p-4">
        <nav className="mb-4">
          <Link to="/" className="mr-4 text-blue-500">Home</Link>
          <Link to="/history" className="text-blue-500">Sorting History</Link>
        </nav>
        <Routes>
          <Route path="/" element={<SortingVisualizer />} />
          <Route path="/history" element={<SortingHistory />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
