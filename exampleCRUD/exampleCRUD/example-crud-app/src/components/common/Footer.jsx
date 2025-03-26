import React from "react";

const FooterComponent = () => {
  return (
    <footer
      className="footer"
      style={{
        position: "fixed",
        bottom: 0,
        left: 0,
        right: 0,
        backgroundColor: "#000000", // Pure black background
        color: "#ffffff", // White text
        padding: "1rem",
        textAlign: "center",
        boxShadow: "0 -2px 5px rgba(0,0,0,0.1)",
        zIndex: 100,
        fontSize: "0.9rem",
      }}
    >
      <span>
        UniLink Dev | All Right Reserved &copy; {new Date().getFullYear()}
      </span>
    </footer>
  );
};

export default FooterComponent;
