import React, { useState } from "react";
import "../css/headerPage.css";
import FacebookIcon from "@mui/icons-material/Facebook";
import InstagramIcon from "@mui/icons-material/Instagram";
import TwitterIcon from "@mui/icons-material/Twitter";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router";
import Button from "@mui/material/Button";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import PopupState, { bindTrigger, bindMenu } from "material-ui-popup-state";

export default function HeaderPage(props) {
  const navigate = useNavigate();
  const [activeLink, setActiveLink] = useState("");

  const handleLinkClick = (link) => {
    setActiveLink(link);
  };

  const handleLogout = () => {
    fetch("http://localhost:8080/logout", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Logout failed");
        } else {
          console.log(response);
          return response;
        }
      })
      .then((data) => {
        props.setLoggedIn(true);
        props.setUser(null);
        navigate("/login");
      })
      .catch((error) => console.error(error));
  };
  return (
    <div className="frame170hp">
      <div className="frame168hp">
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
            <div className="createText">
              {"Hi, " + props.user.firstName + " " + props.user.lastName}
            </div>
          </div>
        </header>
        <div className="frame108hp">
          <Link
            to="/"
            className="auction-app-logo-hp"
            onClick={() => handleLinkClick("")}
          ></Link>
          <div className="frame107hp">
            <div style={{ position: "relative" }}>
              <input
                type="search"
                className="searchBar"
                placeholder="Try enter: Shoes"
              />
              <div
                style={{
                  position: "absolute",
                  top: "50%",
                  transform: "translateY(-50%)",
                  right: "10px",
                }}
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="24"
                  height="24"
                  viewBox="0 0 24 24"
                >
                  <path
                    fill="currentColor"
                    d="M9.5 3A6.5 6.5 0 0 1 16 9.5c0 1.61-.59 3.09-1.56 4.23l.27.27h.79l5 5l-1.5 1.5l-5-5v-.79l-.27-.27A6.516 6.516 0 0 1 9.5 16A6.5 6.5 0 0 1 3 9.5A6.5 6.5 0 0 1 9.5 3m0 2C7 5 5 7 5 9.5S7 14 9.5 14S14 12 14 9.5S12 5 9.5 5Z"
                  />
                </svg>
              </div>
            </div>
            <div className="frame16">
              <Link
                to="/"
                className={`homeText ${activeLink === "home" ? "active" : ""}`}
                onClick={() => handleLinkClick("home")}
              >
                HOME
              </Link>
              <Link
                to="/shop"
                className={`shopText ${activeLink === "shop" ? "active" : ""}`}
                onClick={() => handleLinkClick("shop")}
              >
                SHOP
              </Link>
              <PopupState variant="popover" popupId="demo-popup-menu">
                {(popupState) => (
                  <React.Fragment>
                    <Button
                      variant="text"
                      {...bindTrigger(popupState)}
                      className={`myaccountText ${
                        activeLink === "account" ? "active" : ""
                      }`}
                      //onClick={() => handleLinkClick("account")}
                    >
                      MY ACCOUNT
                    </Button>
                    <Menu {...bindMenu(popupState)}>
                      <MenuItem onClick={popupState.close}>
                        <Link to={"/account"} className="linkItem">
                          Profile
                        </Link>
                      </MenuItem>
                      <MenuItem onClick={popupState.close}>
                        <Link to={"/aboutProduct"} className="linkItem">
                          Become Seller
                        </Link>
                      </MenuItem>
                      <MenuItem onClick={popupState.close}>Your Bids</MenuItem>
                      <MenuItem onClick={popupState.close}>Wishlist</MenuItem>
                      <MenuItem onClick={popupState.close}>Settings</MenuItem>
                      <MenuItem
                        onClick={() => {
                          popupState.close();
                          handleLogout();
                        }}
                      >
                        Logout
                      </MenuItem>
                    </Menu>
                  </React.Fragment>
                )}
              </PopupState>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
