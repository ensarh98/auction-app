import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router";
import "../css/register.css";
import { Link } from "react-router-dom";

export default function Register(props) {
  const navigate = useNavigate();
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [users, setUsers] = useState([]);

  const handleSubmit = (e) => {
    e.preventDefault();

    const user = { firstName, lastName, email, password };

    setFirstName("");
    setLastName("");
    setEmail("");
    setPassword("");

    fetch("http://localhost:8080/register", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(user),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data === null) {
          props.setLoggedIn(false);
          navigate("/register");
        } else {
          props.setLoggedIn(true);
          navigate("/login");
        }
      })
      .catch((error) => console.error(error));
  };

  return (
    <div className="register-form-wrapper">
      <form className="frame184r" onSubmit={handleSubmit}>
        <div className="frame180r">
          <div className="frame178r">
            <h5 className="registerTitle">REGISTER</h5>
          </div>

          <div className="frame179R">
            <div className="frame268">
              <div className="inputFirstName">
                <span className="labelText">First name</span>
                <input
                  type="text"
                  name="firstName"
                  value={firstName}
                  onChange={(e) => setFirstName(e.target.value)}
                  className="frame239"
                  placeholder="John"
                  required
                />
              </div>
              <div className="inputLastName">
                <span className="labelText">Last name</span>
                <input
                  type="text"
                  name="lastName"
                  value={lastName}
                  onChange={(e) => setLastName(e.target.value)}
                  className="frame239"
                  placeholder="Doe"
                  required
                />
              </div>
              <div className="inputEmail">
                <span className="labelText">Enter Email</span>
                <input
                  type="email"
                  name="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  className="frame239"
                  placeholder="user@domain.com"
                  required
                />
              </div>
              <div className="inputPassword">
                <span className="labelText">Password</span>
                <input
                  type="password"
                  name="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  className="frame239"
                  placeholder="********"
                  required
                />
              </div>
            </div>

            <button type="submit" className="ctaReg">
              <span className="regButton">REGISTER</span>
            </button>

            <div className="frame185r">
              <div className="cta">
                <span className="image6"></span>
                <span className="loginWithGmail">Signup with Gmail</span>
              </div>
            </div>
            <div className="ctaFbR">
              <span className="image6Fb"></span>
              <span className="loginWithFb">Signup with Facebook</span>
            </div>
          </div>

          <div className="frame186">
            <span className="alreadyText">Already have an account?</span>
            <Link
              className="loginTextRegister"
              onClick={() => props.handleClickLogin()}
              to={"/login"}
            >
              Login
            </Link>
          </div>
        </div>
      </form>
    </div>
  );
}
