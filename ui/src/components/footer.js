import React from "react";
import "../css/footer.css";

export default function Footer() {
  return (
    <footer>
      <div className="frame34">
        <span className="getInTouch">GET IN TOUCH</span>
        <div className="frame32">
          <div className="frame35">
            <span className="callUsAt">Call Us at</span>
            <span className="phoneNum">+123 797-567-2535</span>
          </div>
          <span className="support">support@auction.com</span>
          <div className="frame36">
            <div className="frame369"></div>
          </div>
        </div>
      </div>
      <div className="frame33">
        <span className="auction">AUCTION</span>
        <div className="frame32">
          <span className="aboutUs">About Us</span>
          <span className="termsAndCond">Terms and Conditions</span>
          <span className="privacyAndPolicy">Privacy and Policy</span>
        </div>
      </div>
    </footer>
  );
}
