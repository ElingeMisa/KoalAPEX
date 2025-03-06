import React from "react";

export default function NavbarItem({ icon, label, onClick, isSelected }) {
  return (
    <div className={`navbar-item ${isSelected ? "selected" : ""}`} onClick={onClick}>
      <i className={`fas fa-${icon} fa-sm`}></i>
      <span>{label}</span>
    </div>
  );
}