import React, { useEffect, useState } from "react";

const SortingHistory = () => {
  const [history, setHistory] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/history/user123") // Replace with actual user ID
      .then((response) => response.json())
      .then((data) => setHistory(data))
      .catch((error) => console.error("❌ Error fetching history:", error));
  }, []);

  return (
    <div className="p-6">
      <h2 className="text-2xl font-bold text-center mb-4">Sorting History</h2>
      <table className="table-auto w-full border-collapse border border-gray-300">
        <thead>
          <tr className="bg-gray-200">
            <th className="border p-2">Algorithm</th>
            <th className="border p-2">Sorted Array</th>
            <th className="border p-2">Date</th>
          </tr>
        </thead>
        <tbody>
          {history.map((entry, index) => (
            <tr key={index} className="text-center">
              <td className="border p-2">{entry.algorithm}</td>
              <td className="border p-2">{JSON.stringify(entry.sortedArray)}</td>
              <td className="border p-2">{new Date(entry.timestamp).toLocaleString()}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default SortingHistory;
