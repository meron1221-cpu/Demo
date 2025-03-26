import React from "react";
import { Link } from "react-router-dom";
import UserService from "../service/UserService";

function Navbar() {
  const isAuthenticated = UserService.isAuthenticated();
  const isAdmin = UserService.isAdmin();

  const handleLogout = () => {
    const confirmDelete = window.confirm(
      "Are you sure you want to logout this user?"
    );
    if (confirmDelete) {
      UserService.logout();
    }
  };

  return (
    <nav>
      <ul>
        {/* Always show Home link when authenticated */}
        {isAuthenticated && (
          <li>
            <Link to="/home">Home</Link>
          </li>
        )}

        {/* Show UniLink Dev when not authenticated */}
        {!isAuthenticated && (
          <li>
            <Link to="/">UniLink Dev</Link>
          </li>
        )}

        {/* About Us link - visible to everyone */}
        <li>
          <Link to="/about">About Us</Link>
        </li>

        {/* Existing authenticated links */}
        {isAuthenticated && (
          <li>
            <Link to="/profile">Profile</Link>
          </li>
        )}

        {/* Admin-only link */}
        {isAdmin && (
          <li>
            <Link to="/admin/user-management">User Management</Link>
          </li>
        )}

        {/* Logout link */}
        {isAuthenticated && (
          <li>
            <Link to="/" onClick={handleLogout}>
              Logout
            </Link>
          </li>
        )}
      </ul>
    </nav>
  );
}

export default Navbar;
