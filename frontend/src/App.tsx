import { useState } from 'react';
import './App.css';
import { Forecast } from './types';

function App() {
  const [city, setCity] = useState('London');
  const [forecast, setForecast] = useState<Forecast[]>([]);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [selfLink, setSelfLink] = useState('');

  const fetchForecast = async () => {
    setLoading(true);
    setError('');
    setForecast([]);
    try {
      const res = await fetch(`/weatherForecast?city=${city}`);
      const body = await res.json();

      if (!res.ok) {
        setError(body.error || 'Unknown error');
      } else {
        setForecast(body.forecast);
        setSelfLink(body._links?.self?.href || '');
      }
    } catch (err) {
      setError('Service unavailable');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="App">
      <h2>3-Day Weather Forecast</h2>
      <input value={city} onChange={(e) => setCity(e.target.value)} placeholder="Enter city" />
      <button onClick={fetchForecast}>Fetch</button>

      {loading && <p>Loading...</p>}
      {error && 
       <>
          <table>
              <thead>
                <tr> 
                  <th> Error Status </th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td style={{ color: 'red', textAlign: 'center' }}>{error}</td>
                </tr>
              </tbody>
            </table>
       </>
      }

      {forecast.length > 0 && (
        <>
          <table>
            <thead>
              <tr>
                <th>Date</th>
                <th>High (K)</th>
                <th>Low (K)</th>
                <th>Message</th>
              </tr>
            </thead>
            <tbody>
              {forecast.map((f, i) => (
                <tr key={i}>
                  <td style={{ textAlign: 'right' }}>{f.date}</td>
                  <td style={{ textAlign: 'right' }}>{f.tempHigh}</td>
                  <td style={{ textAlign: 'right' }}>{f.tempLow}</td>
                  <td>{f.message}</td>
                </tr>
              ))}
            </tbody>
          </table>
          {selfLink && (
            <p>
              <small>Source: <a href={selfLink}>{selfLink}</a></small>
            </p>
          )}
        </>
      )}
    </div>
  );
}

export default App;