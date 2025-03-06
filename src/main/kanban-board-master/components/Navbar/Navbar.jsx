import React, { useState } from "react";
import { Link, useLocation } from "react-router-dom";
import "./Navbar.css";
import NavbarItem from "./NavbarItem";
import logo from "../../src/images/mini_koalapex.png";

export default function Navbar(props) {
  const location = useLocation();
  const [selectedItem, setSelectedItem] = useState(location.pathname);

  const navbarItems = [
    { icon: "home", label: "Home", path: "/" },
    { icon: "tasks", label: "Taskboards", path: "/taskboards" },
    { icon: "file", label: "KPIs", path: "/kpis" },
    // Add more items here
  ];

  const handleItemClick = (path) => {
    setSelectedItem(path);
  };

  return (
    <div className="navbar">
      <div className="navbar-header">
        <h3>
          <img src={logo} alt="logo"/>
          MOCK-UP
        </h3>
        <h6>KoalAPEX</h6>
      </div>
      {navbarItems.map((item, index) => (
        item.path ? (
          <Link to={item.path} key={index} onClick={() => handleItemClick(item.path)}>
            <NavbarItem icon={item.icon} label={item.label} isSelected={selectedItem === item.path} />
          </Link>
        ) : (
          <NavbarItem
            key={index}
            icon={item.icon}
            label={item.label}
            onClick={item.onClick}
            isSelected={selectedItem === item.path}
          />
        )
      ))}

      <div className="theme-switch-wrapper">
        <input
          type="checkbox"
          className="checkbox"
          id="checkbox"
          style={{ transition: "all 200ms" }}
          onChange={props.switchTheme}
          checked={props.theme === "dark"}
        />
        <label htmlFor="checkbox" className="label">
          <i className="fas fa-moon fa-sm"></i>
          <i className="fas fa-sun fa-sm"></i>
          <div className="ball" />
        </label>
      </div>
    </div>
  );
}