import React from "react";
import Navbar from "../components/Navbar";
import SortingVisualizer from "../components/SortingVisualizer";

const Home = () => {
  return (
    <div>
      <Navbar />
      <div className="container mx-auto p-4">
        <h2 className="text-2xl font-bold text-center">Sorting Visualizer</h2>
        <SortingVisualizer />
      </div>
    </div>
  );
};

export default Home;
