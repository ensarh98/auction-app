import React from "react";
import "../css/setPrices.css";
import ArrowBackIosIcon from "@mui/icons-material/ArrowBackIos";
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";
import { Link } from "react-router-dom";

export default function AboutProduct(props) {
  return (
    <div className="price-wrapper">
      <div className="group160ab">
        <span className="sellerTextab">BECOME SELLER</span>
      </div>
      <div className="frame204ab">
        <span className="myAccountTextab">MY ACCOUNT</span>
        <span className="slashTextab">/</span>
        <span className="becomeSellerTextab">BECOME SELLER</span>
      </div>

      <div className="frame434ab">
        <div className="reactangle66ab">
          <div className="frame178ab">
            <span className="setPriceTextab">SET PRICES</span>
          </div>
        </div>
      </div>

      <div className="frame179ab">
        <div className="frame275ab">
          <div className="inputPriceab">
            <span className="yourStartPriceab">Your start Price</span>{" "}
            <div className="frame234ab">
              <div className="frame236ab">
                <div className="frame235ab">
                  <span className="dollarText">$</span>
                  <span className="line13ab"></span>
                </div>
              </div>
              <input
                className="reactangle38ab"
                type="number"
                name="startPrice"
                value={props.startPrice}
                onChange={(e) => props.handleSetStartPrice(e.target.value)}
              />
            </div>
          </div>
          <div className="frame274ab">
            <div className="frame238ab">
              <div className="inputStartDate">
                <span className="startDateText">Start date</span>
                <div className="frame237abs">
                  <input
                    type="date"
                    name="startDate"
                    className="frame40ab"
                    value={props.startDate}
                    onChange={(e) => props.handleSetStartDate(e.target.value)}
                  ></input>
                </div>
              </div>
              <div className="inputEndDate">
                <span className="endDateText">End date</span>
                <div className="frame237abe">
                  <input
                    type="date"
                    name="endDate"
                    className="frame40ab"
                    value={props.endDate}
                    onChange={(e) => props.handleSetEndDate(e.target.value)}
                  ></input>
                </div>
              </div>
            </div>
            <span className="pricesTextDescAb">
              The auction will be automatically closed when the end time comes.
              The highest bid will win the auction.
            </span>
          </div>

          <div className="frame456ab">
            <Link to="/aboutProduct" className="backButtonab">
              <ArrowBackIosIcon />
              <span className="backButtonText">BACK</span>
            </Link>
            <Link to="/address" className="nextButtonab">
              <span className="nextButtonText">NEXT</span>
              <ArrowForwardIosIcon />
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}
