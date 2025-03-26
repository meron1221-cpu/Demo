import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./HomePage.css";

const HomePage = ({ isLoggedIn }) => {
  const navigate = useNavigate();

  useEffect(() => {
    if (!isLoggedIn) {
      navigate("/home");
    }
  }, [isLoggedIn, navigate]);

  useEffect(() => {
    const elements = document.querySelectorAll(".animate-on-load");
    elements.forEach((el, index) => {
      el.style.animationDelay = `${index * 0.2}s`;
    });
  }, []);

  return (
    <div className="home-page">
      {/* Hero Section */}
      <header className="hero-section">
        <div className="hero-content animate-on-load">
          <h1>User Management System</h1>
          <h2>Admin Dashboard</h2>
          <p className="hero-description">
            Efficiently manage user accounts, roles, and permissions with our
            comprehensive administration tools designed for system
            administrators.
          </p>
          <button
            className="cta-button"
            onClick={() => navigate("/admin/user-management")}
          >
            Manage Users
          </button>
        </div>
      </header>

      {/* Features Section */}
      <section className="features-section">
        <div className="container">
          <div className="features-grid">
            <div className="feature-card animate-on-load">
              <div className="feature-icon">
                <i className="fas fa-users-cog"></i>
              </div>
              <h3>User Management</h3>
              <p>
                Create, view, update, and delete user accounts with full
                administrative control and access logs.
              </p>
            </div>

            <div className="feature-card animate-on-load">
              <div className="feature-icon">
                <i className="fas fa-user-shield"></i>
              </div>
              <h3>Role Control</h3>
              <p>
                Assign and modify user roles (Admin/User) to control system
                access and permissions.
              </p>
            </div>

            <div className="feature-card animate-on-load">
              <div className="feature-icon">
                <i className="fas fa-chart-bar"></i>
              </div>
              <h3>Activity Monitoring</h3>
              <p>
                Track user activities and system access patterns with
                comprehensive logging and reporting.
              </p>
            </div>

            <div className="feature-card animate-on-load">
              <div className="feature-icon">
                <i className="fas fa-database"></i>
              </div>
              <h3>Data Security</h3>
              <p>
                Enterprise-grade security with encrypted data storage and secure
                authentication protocols.
              </p>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
};

export default HomePage;
