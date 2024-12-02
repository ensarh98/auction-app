import React, { useState } from "react";
import { useNavigate } from "react-router";
import "../css/login.css";

export default function Login(props) {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    const user = { email, password };

    setEmail("");
    setPassword("");

    let loginUrl = new URL("http://localhost:8080/login");
    let loginParams = { username: user.email, password: user.password };
    loginUrl.search = new URLSearchParams(loginParams);

    fetch(loginUrl, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Authentication failed");
        } else {
          return response.json();
        }
      })
      .then((data) => {
        props.setLoggedIn(true);
        props.setUser(data);
        navigate("/");
      })
      .catch((error) => console.error(error));
  };
  return (
    <div className="login-form-wrapper">
      <form className="frame184l" onSubmit={handleSubmit}>
        <div className="frame180l">
          <div className="frame178l">
            <h5 className="loginTitle">LOGIN</h5>
          </div>

          <div className="frame179l">
            <div className="frame270">
              <div className="inputEmailL">
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
              <div className="frame269">
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
                <div className="frame181">
                  <label>
                    <input type="checkbox" />
                    Remember me
                  </label>
                </div>
              </div>
            </div>

            <div className="frame271">
              <button type="submit" className="ctaLog">
                <span className="logButton">LOGIN</span>
              </button>
              <div className="frame185l">
                <div className="ctaGmailL">
                  <span className="loginWithGmail">Signup with Gmail</span>
                  <span className="imageGmail"></span>
                </div>

                <div className="ctaFbL">
                  <span className="loginWithFb">Signup with Facebook</span>
                  <span className="image6Fbl"></span>
                </div>
              </div>
            </div>
          </div>

          <span className="forgotPassword">Forgot password?</span>
        </div>
      </form>
    </div>
  );
}
