import React from "react";
import "../css/address.css";
import ArrowBackIosIcon from "@mui/icons-material/ArrowBackIos";
import { Link } from "react-router-dom";

export default function Address(props) {
  return (
    <div className="address-wrapper">
      <div className="group160ab">
        <span className="sellerTextab">BECOME SELLER</span>
      </div>
      <div className="frame204ab">
        <span className="myAccountTextab">MY ACCOUNT</span>
        <span className="slashTextab">/</span>
        <span className="becomeSellerTextab">BECOME SELLER</span>
      </div>

      <div className="frame434ls">
        <div className="reactangle66ls">
          <div className="frame178ls">
            <span className="locationAndShippingTextls">ADDRESS</span>
          </div>
        </div>
      </div>
      <div className="frame179ls">
        <div className="frame275ls">
          <div className="inputAddressls">
            <span className="yourStartPricels">Address</span>{" "}
            <input
              className="reactangle38ls"
              type="text"
              name="address"
              onChange={(e) => props.handleSetAddress(e.target.value)}
            />
          </div>

          <div className="frame456ls">
            <Link to="/setPrices" className="backButtonls">
              <ArrowBackIosIcon />
              <span className="backButtonText">BACK</span>
            </Link>
            <Link className="nextButtonls" onClick={props.handleClickDone}>
              <span className="nextButtonText">DONE</span>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}
