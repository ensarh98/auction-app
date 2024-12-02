import React from "react";
import "../css/aboutProduct.css";
import { Link } from "react-router-dom";

export default function AboutProduct(props) {
  const handlePhotoChange = (e) => {
    const file = e.target.files[0];
    props.handleSetPhoto(file);
  };

  return (
    <div>
      <div className="group160sp">
        <span className="sellerTextsp">BECOME SELLER</span>
      </div>
      <div className="frame204sp">
        <span className="myAccountText">MY ACCOUNT</span>
        <span className="slashText">/</span>
        <span className="becomeSellerText">BECOME SELLER</span>
      </div>

      <div className="product-wrapper">
        <div className="frame434sp">
          <div className="reactangle66">
            <div className="frame178sp">
              <span className="addItemTextsp">
                DETAIL INFORMATION ABOUT PRODUCT
              </span>
            </div>
          </div>
        </div>

        <div className="frame179sp">
          <div className="frame435sp">
            <div className="inputItemsp">
              <span className="whatDoYouSellText">What do you sell?</span>
              <input
                className="frame230sp"
                type="text"
                name="name"
                value={props.name}
                onChange={(e) => props.handleSetName(e.target.value)}
              />
              <div className="wordsCharactersText">
                2-5 words (60 characters)
              </div>
            </div>
            <div className="frame227sp">
              <div className="dropdownCategory">
                <select
                  defaultValue="category"
                  className="frame40sp"
                  name="category"
                  onChange={(e) => props.handleSetCategory(e.target.value)}
                >
                  <option disabled value="category">
                    Select Category
                  </option>
                  <option value="clothes">Clothes</option>
                </select>
              </div>
            </div>

            <div className="frame229sp">
              <div className="textArea">
                <span className="descriptionTextsp">Description</span>
                <textarea
                  id="description"
                  name="description"
                  className="frame228sp"
                  value={props.description}
                  onChange={(e) => props.handleSetDescription(e.target.value)}
                ></textarea>
                <div className="wordsCharactersDescText">
                  2-5 words (60 characters)
                </div>
              </div>
            </div>
            <label className="inputPhoto" htmlFor="file">
              <div className="frame233sp">
                <div className="frame231sp">
                  <label className="frame232sp">
                    <span className="uploadPhotosText">Upload Photos</span>
                    <span className="orJustDragAndDrop">
                      or just drag and drop
                    </span>
                  </label>
                  <span className="AddAtLeast3Photos">
                    + Add at least 3 photos
                  </span>
                </div>
                <input
                  type="file"
                  name="file"
                  id="file"
                  className="uploadPhotosInput"
                  onChange={handlePhotoChange}
                />
              </div>
            </label>
            <Link to="/setPrices" className="frame230abb">
              <div className="nextButtonsap">
                <span className="nextTextab">NEXT</span>
              </div>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}
