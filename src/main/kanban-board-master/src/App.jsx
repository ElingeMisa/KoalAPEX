import { useEffect } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import "./App.css";
import Navbar from "../components/Navbar/Navbar";
import Home from "../components/Pages/Home";
import Taskboards from "../components/Pages/Taskboards";
import KPIs from "../components/Pages/KPIs";
import useLocalStorage from "use-local-storage";
import "../bootstrap.css";

function App() {
  const defaultDark = window.matchMedia(
    "(prefers-colors-scheme: dark)"
  ).matches;
  const [theme, setTheme] = useLocalStorage(
    "theme",
    defaultDark ? "dark" : "light"
  );

  const switchTheme = () => {
    setTheme(theme === "light" ? "dark" : "light");
  };

  useEffect(() => {
    document.body.setAttribute("data-theme", theme);
  }, [theme]);

  return (
    <Router>
      <div className="App" data-theme={theme}>
        <Navbar switchTheme={switchTheme} theme={theme} />
        <div className="app_outer">
          <Routes>
            <Route path="/" element={<Home/>} />
            <Route path="/taskboards" element={<Taskboards/>} />
            <Route path="/kpis" element={<KPIs/>} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;