import React from "react";
import { FaGithub, FaLinkedin, FaEnvelope } from "react-icons/fa";
import MeronPhoto from "../../assets/teams/IMG_5336.PNG";
import ElsaPhoto from "../../assets/teams/elsa.jpg";
import MihretPhoto from "../../assets/teams/mercy.jpg";
import "./AboutUs.css";

const AboutUs = () => {
  const teamMembers = [
    {
      name: "Meron",
      role: "Frontend Developer",
      photo: MeronPhoto,
      github: "https://github.com/meron1221-cpu",
      linkedin: "https://www.linkedin.com/in/meron-nisrane-1882b629b",
      email: "mailto:meronnisrane@gmail.com",
    },
    {
      name: "Elsa",
      role: "Backend Developer",
      photo: ElsaPhoto,
      github: "https://github.com/elsaabera",
      linkedin: "https://www.linkedin.com/in/elsa-abera-068bb6337",
      email: "mailto:elsa@example.com",
    },
    {
      name: "Mihret",
      role: "UI/UX Designer",
      photo: MihretPhoto,
      github: "https://github.com/MhiretKiros",
      linkedin: "https://www.linkedin.com/in/mhret-kiros-8aa2ba332",
      email: "mailto:kirosmhret97@gmail.com",
    },
  ];

  return (
    <div className="about-us-page">
      <section className="about-hero">
        <div className="hero-content">
          <h1 className="hero-title animate-text">Our Team</h1>
          <p className="hero-subtitle animate-text">Meet the teamMembers</p>
        </div>
      </section>

      <section className="team-section">
        <div className="container">
          <h2 className="section-title">Friends who debug life together</h2>
          <div className="team-grid">
            {teamMembers.map((member, index) => (
              <div className="team-card" key={index}>
                <div className="member-photo-container">
                  <img
                    src={member.photo}
                    alt={member.name}
                    className="member-photo"
                    loading="lazy"
                  />
                  <div className="social-links">
                    <a
                      href={member.github}
                      target="_blank"
                      rel="noopener noreferrer"
                      className="social-icon"
                    >
                      <FaGithub />
                    </a>
                    <a
                      href={member.linkedin}
                      target="_blank"
                      rel="noopener noreferrer"
                      className="social-icon"
                    >
                      <FaLinkedin />
                    </a>
                    <a href={member.email} className="social-icon">
                      <FaEnvelope />
                    </a>
                  </div>
                </div>
                <div className="member-info">
                  <h3 className="member-name">{member.name}</h3>
                  <p className="member-role">{member.role}</p>
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>
    </div>
  );
};

export default AboutUs;
