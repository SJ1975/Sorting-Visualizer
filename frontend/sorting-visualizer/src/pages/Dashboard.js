import React from "react";
import Navbar from "../components/Navbar";

const Dashboard = () => {
  return (
    <div>
      <Navbar />
      <div className="container mx-auto p-4">
        <h2 className="text-2xl font-bold text-center">Sorting History</h2>
        <p className="text-center">User sorting history will be displayed here.</p>
      </div>
    </div>
  );
};

export default Dashboard;
