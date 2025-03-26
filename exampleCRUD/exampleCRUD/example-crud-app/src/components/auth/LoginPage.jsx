import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import UserService from "../service/UserService";
import "./LoginPage.css";
import logo from "../../assets/graphic-designer.png";

function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [isHovering, setIsHovering] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const userData = await UserService.login(email, password);
      if (userData.token) {
        localStorage.setItem("token", userData.token);
        localStorage.setItem("role", userData.role);
        navigate("/home");
      } else {
        setError(userData.message);
      }
    } catch (error) {
      console.log(error);
      setError(error.message);
      setTimeout(() => {
        setError("");
      }, 5000);
    }
  };

  return (
    <div className="login-container">
      <div className="login-box">
        <img
          src={logo}
          alt="UsersHub Logo"
          className={`logo ${isHovering ? "pulse" : ""}`}
          onMouseEnter={() => setIsHovering(true)}
          onMouseLeave={() => setIsHovering(false)}
        />
        <h1 className="welcome-text">Welcome to UsersHub!</h1>
        <p className="subtitle">Keep your data safe</p>
        {error && <p className="error-message animate-shake">{error}</p>}
        <form onSubmit={handleSubmit} className="login-form">
          <div className="form-group">
            <label className="input-label hover-float">Email</label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="login-input hover-grow"
            />
          </div>
          <div className="form-group">
            <label className="input-label hover-float">Password</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="login-input hover-grow"
            />
          </div>
          <button type="submit" className="login-button hover-bounce">
            LOGIN
          </button>
        </form>
      </div>
    </div>
  );
}

export default LoginPage;
