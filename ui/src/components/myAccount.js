import React, { useState, useEffect } from "react";
import "../css/myAccount.css";
import AddIcon from "@mui/icons-material/Add";
import SettingsOutlinedIcon from "@mui/icons-material/SettingsOutlined";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import MonetizationOnOutlinedIcon from "@mui/icons-material/MonetizationOnOutlined";
import ListOutlinedIcon from "@mui/icons-material/ListOutlined";
import PersonIcon from "@mui/icons-material/Person";
import { useNavigate } from "react-router";

export default function MyAccount(props) {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [dayOfDate, setDayOfDate] = useState("");
  const [monthOfDate, setMonthOfDate] = useState("");
  const [yearOfDate, setYearOfDate] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const navigate = useNavigate();

  function handleAddItem() {
    navigate("/AboutProduct");
  }

  const [user, setUser] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/users/" + props.user.id.toString())
      .then((response) => response.json())
      .then((data) => {
        setUser(data);
        setFirstName(data.firstName);
        setLastName(data.lastName);
        setEmail(data.email);
        data.phone && setPhoneNumber(data.phone);
      });
  }, []);

  function handleSubmit() {}

  return (
    <div className="account-wrapper">
      <div className="frame450">
        <div className="frame450m">
          <div className="ctaButton" onClick={handleAddItem}>
            <AddIcon className="vectorPlus" />
            <span className="addItemText">ADD ITEM</span>
          </div>
          <div className="tabSettings">
            <SettingsOutlinedIcon className="tabIcon" />
            <span className="settingsText">Settings</span>
          </div>
          <div className="tabWishList">
            <FavoriteBorderIcon className="tabIcon" />
            <span className="wishlistText">Wishlist</span>
          </div>
          <div className="tabBids">
            <MonetizationOnOutlinedIcon className="tabIcon" />
            <span className="bidsText">Bids</span>
          </div>
          <div className="tabSeller">
            <ListOutlinedIcon className="tabIcon" />
            <span className="sellerText">Seller</span>
          </div>
          <div className="tabProfile">
            <PersonIcon className="tabIcon" />
            <span className="profileText">Profile</span>
          </div>
        </div>

        <form className="frame449" onSubmit={handleSubmit}>
          <div className="frame447">
            <div className="frame441">
              <div className="frame380">
                <div className="frame379">
                  <div className="frame378">
                    <div className="frame377">
                      <div className="frame376">
                        <span className="pInformationText">
                          Personal Information
                        </span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="frame440">
                <div className="frame423">
                  <div className="group162">
                    <div className="image8"></div>
                  </div>
                  <div className="ctaChangePhoto">
                    <span className="goText">Change Photo</span>
                  </div>
                </div>
                <div className="frame415">
                  <div className="inputF">
                    <span className="fnText">First Name</span>
                    <input
                      className="frame239"
                      type="text"
                      name="firstName"
                      value={firstName}
                      onChange={(e) => setFirstName(e.target.value)}
                      placeholder="John"
                      required
                    />
                  </div>
                  <div className="inputF">
                    <span className="fnText">Last Name</span>
                    <input
                      className="frame239"
                      type="text"
                      name="lastName"
                      value={lastName}
                      onChange={(e) => setLastName(e.target.value)}
                      placeholder="Doe"
                      required
                    />
                  </div>
                  <div className="inputF">
                    <span className="fnText">Email Address</span>
                    <input
                      className="frame239"
                      type="email"
                      name="email"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                      placeholder="user@domain.com"
                      required
                    />
                  </div>
                  <div className="frame438">
                    <div className="dateInputDD">
                      <span className="fnText">Date of Birth</span>
                      <input
                        className="frame239d"
                        placeholder="DD"
                        type="number"
                        value={dayOfDate}
                        onChange={(e) => setDayOfDate(e.target.value)}
                        min="1"
                        max="31"
                        required
                      />
                    </div>
                    <div className="dateInputMM">
                      <input
                        className="frame239d"
                        placeholder="MM"
                        type="number"
                        value={monthOfDate}
                        onChange={(e) => setMonthOfDate(e.target.value)}
                        min={1}
                        max={12}
                        required
                      />
                    </div>
                    <div className="dateInputYYYY">
                      <input
                        className="frame239d"
                        placeholder="YYYY"
                        type="number"
                        value={yearOfDate}
                        onChange={(e) => setYearOfDate(e.target.value)}
                        min="1800"
                        max="2500"
                        required
                      />
                    </div>
                  </div>
                  <div className="inputPhoneNumber">
                    <span className="phoneNumberText">Phone Number</span>
                    <div className="frame439">
                      <input
                        type="text"
                        className="frame239Phone"
                        name="phoneNumber"
                        value={phoneNumber}
                        onChange={(e) => setPhoneNumber(e.target.value)}
                        placeholder="+32534231564"
                        required
                      />
                      <div className="frame240">
                        <span className="notVerified">Not verified</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <button className="ctaSaveInfo">
            <div className="saveInfoButton" type="submit">
              SAVE INFO
            </div>
          </button>
        </form>
      </div>
    </div>
  );
}
