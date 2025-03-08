import React, { useState, useEffect } from "react";

const SortingHistory = () => {
  const [history, setHistory] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchSortingHistory();
  }, []);

  const fetchSortingHistory = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/history/all");
      if (!response.ok) {
        throw new Error("Failed to fetch sorting history");
      }
      const data = await response.json();
      setHistory(data);
    } catch (error) {
      console.error("Error fetching history:", error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="p-6">
      <h2 className="text-2xl font-bold text-center mb-4">Sorting History</h2>

      {loading ? (
        <p className="text-center">Loading...</p>
      ) : history.length === 0 ? (
        <p className="text-center">No sorting history available.</p>
      ) : (
        <table className="w-full border-collapse border border-gray-300">
          <thead>
            <tr className="bg-gray-200">
              <th className="border border-gray-300 px-4 py-2">Algorithm</th>
              <th className="border border-gray-300 px-4 py-2">Original Array</th>
              <th className="border border-gray-300 px-4 py-2">Sorted Array</th>
              <th className="border border-gray-300 px-4 py-2">Time Taken (ms)</th>
              <th className="border border-gray-300 px-4 py-2">Timestamp</th>
            </tr>
          </thead>
          <tbody>
            {history.map((entry, index) => (
              <tr key={index} className="text-center">
                <td className="border border-gray-300 px-4 py-2">{entry.algorithm}</td>
                <td className="border border-gray-300 px-4 py-2">
                  {JSON.stringify(entry.originalArray)}
                </td>
                <td className="border border-gray-300 px-4 py-2">
                  {JSON.stringify(entry.sortedArray)}
                </td>
                <td className="border border-gray-300 px-4 py-2">{entry.timeTaken}</td>
                <td className="border border-gray-300 px-4 py-2">
                  {new Date(entry.timestamp).toLocaleString()}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default SortingHistory;
