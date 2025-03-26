import React, { useState, useEffect } from "react";
import UserService from "../service/UserService";
import { Link } from "react-router-dom";
import "./ProfilePage.css";

function ProfilePage() {
  const [profileInfo, setProfileInfo] = useState({});
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    fetchProfileInfo();
    initRippleEffects();
  }, []);

  const fetchProfileInfo = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await UserService.getYourProfile(token);
      setProfileInfo(response.ourUser);
      setIsLoading(false);
    } catch (error) {
      console.error("Error fetching profile information:", error);
      setIsLoading(false);
    }
  };

  const initRippleEffects = () => {
    const buttons = document.querySelectorAll(".ripple-effect");
    buttons.forEach((button) => {
      button.addEventListener("click", function (e) {
        const x = e.clientX - e.target.getBoundingClientRect().left;
        const y = e.clientY - e.target.getBoundingClientRect().top;

        const ripple = document.createElement("span");
        ripple.className = "ripple";
        ripple.style.left = `${x}px`;
        ripple.style.top = `${y}px`;
        this.appendChild(ripple);
        setTimeout(() => ripple.remove(), 600);
      });
    });
  };

  return (
    <div
      className={`profile-page-container ${isLoading ? "loading" : "loaded"}`}
    >
      <h2 className="profile-header">Profile</h2>

      <div className="profile-card">
        {/* Profile Info */}
        <div className="profile-info-group">
          <span className="profile-label">Name:</span>
          <p className="profile-value">{profileInfo.name || "Loading..."}</p>
        </div>

        <div className="profile-info-group">
          <span className="profile-label">Email:</span>
          <p className="profile-value">{profileInfo.email || "Loading..."}</p>
        </div>

        <div className="profile-info-group">
          <span className="profile-label">City:</span>
          <p className="profile-value">{profileInfo.city || "Loading..."}</p>
        </div>

        {/* Achievement Badges */}
        <div className="badges-container">
          <div className="badge gold">🏆 Top Contributor</div>
          <div className="badge silver">⭐ Active Member</div>
        </div>

        {profileInfo.role === "ADMIN" && (
          <Link
            to={`/update-user/${profileInfo.id}`}
            className="update-button ripple-effect"
          >
            Update Profile
          </Link>
        )}
      </div>
    </div>
  );
}

export default ProfilePage;
