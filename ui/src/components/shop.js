import React, { useEffect, useState } from "react";
import "../css/shop.css";
import GridOnIcon from "@mui/icons-material/GridOn";
import MenuIcon from "@mui/icons-material/Menu";
import MonetizationOnOutlinedIcon from "@mui/icons-material/MonetizationOnOutlined";

export default function Shop() {
  const [items, setItems] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/items")
      .then((response) => response.json())
      .then((data) => {
        setItems(data);
      });
  }, []);

  return (
    <div className="frame174sh">
      <div className="frame176sh">
        <div className="frame173sh">
          <div className="frame273sh">
            <div className="frame14sh">
              <div className="frame11sh">
                <div className="frame13sh">
                  <span className="productCategoriesTextSh">
                    PRODUCT CATEGORIES
                    <div className="frame12sh">
                      <div className="frame18sh">
                        <span className="categoryTextSh">Men</span>
                      </div>
                      <div className="frame45sh">
                        <input
                          className="checkboxSubcategory"
                          type="checkbox"
                        />
                        <span className="frame45TextSh">Clothes</span>
                      </div>
                    </div>
                  </span>
                </div>
              </div>
            </div>
            <div className="frame201sh">
              <div className="frame102sh">
                <div className="frame41sh">
                  <select
                    defaultValue="category"
                    className="frame40sh"
                    name="sort"
                  >
                    <option disabled value="category" className="dropdownText">
                      Default Sorting
                    </option>
                    <option value="clothes">Clothes</option>
                  </select>
                </div>
                <div className="frame87sh">
                  <div className="frame451sh">
                    <GridOnIcon className="gridVectorSh" />
                    <span className="gridTextSh">Grid</span>
                  </div>
                  <div className="frame452sh">
                    <MenuIcon className="listVectorSh" />
                    <span className="listTextSh">List</span>
                  </div>
                </div>
              </div>
              <div className="frame202sh">
                {items.map((item) => (
                  <div className="productSh" key={item.id}>
                    <div className="frame4sh">
                      <img
                        className="reactangle10sh"
                        src={`http://localhost:8080/items/${item.id}/photo`}
                        alt={`Slika item ${item.photoId}`}
                      />
                    </div>
                    <div className="frame5sh">
                      <div className="frame197sh">
                        <span className="titleItemText">{item.name}</span>
                        <span className="descriptionItemTextSh">
                          {item.description}{" "}
                        </span>
                      </div>
                      <span className="startFromTextSh">
                        Start From ${item.startPrice.toFixed(2)}
                      </span>
                      <div className="frame200sh">
                        <div className="frame199sh">
                          <span className="bidItemTextSh">Bid</span>
                          <MonetizationOnOutlinedIcon />
                        </div>
                      </div>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
