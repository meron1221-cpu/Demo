import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import UserService from "../service/UserService";
import "./UserManagementPage.css"; // Create this CSS file

function UserManagementPage() {
  const [users, setUsers] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      setIsLoading(true);
      const token = localStorage.getItem("token");
      const response = await UserService.getAllUsers(token);
      setUsers(response.ourUserLists);
    } catch (error) {
      console.error("Error fetching users:", error);
    } finally {
      setIsLoading(false);
    }
  };

  const deleteUser = async (userId) => {
    try {
      const confirmDelete = window.confirm(
        "Are you sure you want to delete this user?"
      );
      if (confirmDelete) {
        const token = localStorage.getItem("token");
        await UserService.deleteUser(userId, token);
        fetchUsers();
      }
    } catch (error) {
      console.error("Error deleting user:", error);
    }
  };

  return (
    <div className="user-management-container fade-in">
      <div className="header-section">
        <h2 className="page-title">👥 Users Hub </h2>
        <Link to="/register" className="add-user-btn">
          ➕ Add User
        </Link>
      </div>

      {isLoading ? (
        <div className="loading-spinner">🌀 Loading...</div>
      ) : (
        <div className="table-container">
          <table className="users-table">
            <thead>
              <tr className="table-header">
                <th>#️⃣ ID</th>
                <th>👤 Name</th>
                <th>✉️ Email</th>
                <th>🛠️ Actions</th>
              </tr>
            </thead>
            <tbody>
              {users.map((user) => (
                <tr key={user.id} className="table-row hover-effect">
                  <td>{user.id}</td>
                  <td>{user.name}</td>
                  <td>{user.email}</td>
                  <td className="actions-cell">
                    <Link
                      to={`/update-user/${user.id}`}
                      className="action-btn update-btn"
                    >
                      ✏️ Edit
                    </Link>
                    <button
                      onClick={() => deleteUser(user.id)}
                      className="action-btn delete-btn"
                    >
                      🗑️ Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default UserManagementPage;
