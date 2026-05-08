import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";

function BiblesPage() {
  const [bibles, setBibles] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function fetchBibles() {
      try {
        const res = await fetch(`${process.env.REACT_APP_API_URL}/api/bibles`);
        const data = await res.json();
        setBibles(data || []);
      } catch (e) {
        console.error("Unable to fetch bibles", e);
      } finally {
        setLoading(false);
      }
    }
    fetchBibles();
  }, []);

  return (
    <div className="container mt-4" style={{ minHeight: "100vh", padding: "20px" }}>
      <h2 className="text-center mb-4">Piiblid</h2>

      {loading ? (
        <p className="text-center">Laen...</p>
      ) : bibles.length === 0 ? (
        <p className="text-center">Piibleid ei leitud.</p>
      ) : (
        <table className="table table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>Keel</th>
              <th>Versioon</th>
            </tr>
          </thead>
          <tbody>
            {bibles.map((bible) => (
              <tr key={bible.bibleId}>
                <td>{bible.bibleId}</td>
                <td>{bible.language}</td>
                <td>{bible.version || "-"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default BiblesPage;
