import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Home from "./components/home";
import Register from "./components/register";
import Login from "./components/login";
import Header from "./components/header";
import Footer from "./components/footer";
import HeaderPage from "./components/headerPage";
import MyAccount from "./components/myAccount";
import SetPrices from "./components/setPrices";
import AboutProduct from "./components/aboutProduct";
import Address from "./components/address";
import Shop from "./components/shop";

function App() {
  // About product
  const [name, setName] = useState("");
  const [category, setCategory] = useState("");
  const [description, setDescription] = useState("");
  const [file, setFile] = useState(null);
  // Set prices
  const [startPriceS, setStartPrice] = useState("");
  const [startDateS, setStartDate] = useState("");
  const [endDateS, setEndDate] = useState("");
  // Address
  const [address, setAddress] = useState("");

  function handleClickDone(e) {
    e.preventDefault();
    let subcategoryId = 1;
    let userId = user.id;
    let startPrice = parseFloat(startPriceS);
    let startDate = new Date(Date.parse(startDateS));
    let endDate = new Date(Date.parse(endDateS));

    let item = {
      name,
      description,
      address,
      startPrice,
      startDate,
      endDate,
      subcategoryId,
      userId,
    };

    setName("");
    setCategory("");
    setDescription("");
    setStartPrice("");
    setStartDate("");
    setEndDate("");
    setAddress("");

    fetch("http://localhost:8080/items", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        ...item,
        startDate: item.startDate.getTime(),
        endDate: item.endDate.getTime(),
      }),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Incorrect item insertion.");
        } else {
          return response.json();
        }
      })
      .then((data) => {
        const formData = new FormData();
        formData.append("file", file);
        fetch("http://localhost:8080/items/" + data.toString() + "/photo", {
          method: "POST",
          body: formData,
        }).then((res) => window.open("/shop"));
      })
      .catch((error) => console.error(error));
  }

  const handleSetName = (name) => {
    setName(name);
  };

  const handleSetCategory = (category) => {
    setCategory(category);
  };

  const handleSetDescription = (description) => {
    setDescription(description);
  };

  const handleSetPhoto = (photo) => {
    setFile(photo);
  };

  const handleSetStartPrice = (startPrice) => {
    setStartPrice(startPrice);
  };

  const handleSetStartDate = (startDate) => {
    setStartDate(startDate);
  };

  const handleSetEndDate = (endDate) => {
    setEndDate(endDate);
  };

  const handleSetAddress = (address) => {
    setAddress(address);
  };

  const [loggedIn, setLoggedIn] = useState(() => {
    const storedLoggedIn = localStorage.getItem("loggedIn");
    return storedLoggedIn ? JSON.parse(storedLoggedIn) : false;
  });

  const [user, setUser] = useState(() => {
    const storedUser = localStorage.getItem("user");
    return storedUser ? JSON.parse(storedUser) : null;
  });

  useEffect(() => {
    localStorage.setItem("user", JSON.stringify(user));
  }, [user]);

  useEffect(() => {
    localStorage.setItem("loggedIn", JSON.stringify(loggedIn));
  }, [loggedIn]);

  function handleClickLogin() {
    setLoggedIn(true);
  }

  function handleClickRegister() {
    setLoggedIn(false);
  }

  return (
    <div className="page">
      <Router>
        {user ? (
          <HeaderPage user={user} setLoggedIn={setLoggedIn} setUser={setUser} />
        ) : (
          <Header
            onClickLogin={handleClickLogin}
            onClickRegister={handleClickRegister}
          />
        )}

        <Routes>
          {user ? (
            <Route path="/" element={<Home />}></Route>
          ) : loggedIn ? (
            <Route
              path="/login"
              element={<Login setUser={setUser} setLoggedIn={setLoggedIn} />}
            />
          ) : (
            <Route
              path="/register"
              element={
                <Register
                  setLoggedIn={setLoggedIn}
                  handleClickLogin={handleClickLogin}
                />
              }
            />
          )}

          <Route path="/account" element={<MyAccount user={user} />} />
          <Route path="/shop" element={<Shop user={user} />} />
          <Route
            path="/aboutProduct"
            element={
              <AboutProduct
                handleSetName={handleSetName}
                handleSetDescription={handleSetDescription}
                handleSetCategory={handleSetCategory}
                handleSetPhoto={handleSetPhoto}
                name={name}
                description={description}
                category={category}
                file={file}
              />
            }
          />
          <Route
            path="/setPrices"
            element={
              <SetPrices
                handleSetStartPrice={handleSetStartPrice}
                handleSetStartDate={handleSetStartDate}
                handleSetEndDate={handleSetEndDate}
                startPrice={startPriceS}
                startDate={startDateS}
                endDate={endDateS}
              />
            }
          />
          <Route
            path="/address"
            element={
              <Address
                handleSetAddress={handleSetAddress}
                address={address}
                handleClickDone={handleClickDone}
              />
            }
          />
        </Routes>

        <Footer />
      </Router>
    </div>
  );
}

export default App;
