import React from "react";
import "../css/header.css";
import FacebookIcon from "@mui/icons-material/Facebook";
import InstagramIcon from "@mui/icons-material/Instagram";
import TwitterIcon from "@mui/icons-material/Twitter";
import { Link } from "react-router-dom";

export default function Header(props) {
  return (
    <header className="header">
      <div className="frame_369">
        <span className="akar">
          <FacebookIcon />
        </span>
        <span className="akar">
          <InstagramIcon />
        </span>
        <span className="akar">
          <TwitterIcon />
        </span>
      </div>
      <div className="frame1">
        <Link
          to={"/login"}
          className="loginText"
          onClick={() => props.onClickLogin()}
        >
          Login
        </Link>
        <span className="orText">or</span>
        <Link
          to={"/register"}
          className="createText"
          onClick={() => props.onClickRegister()}
        >
          Create an account
        </Link>
      </div>
      <div className="group161"></div>
      <div className="frame185h">
        <div className="frame176">
          <div className="frame108">
            <span className="auction-app-logo"></span>
          </div>
        </div>
      </div>
    </header>
  );
}
