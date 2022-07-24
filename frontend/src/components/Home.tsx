import React, { useEffect, useState } from "react";
import FetchData from "../Http/FetchData";
import LoadingDisplay from "./LoadingDisplay";
import Map from "./Map";
import "../styles/centerStyle.css";

const Home = () => {
  const [allData, setAllData] = useState([]);
  const [allCentroidData, setAllCentroidData] = useState([]);
  const [allCentroidDotsData, setallCentroidDotsData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [onError, setOnError] = useState(false);

  const fetchData = () => {
    setIsLoading(true);
    FetchData.grabAllData()
      .then((data) => {
        setAllData(data);
      })
      .then(() => setIsLoading(false))
      .catch((err) => {
        setOnError(true);
      })
      .then(() => setIsLoading(false));
  };
  const fetchCentroidData = () => {
    setIsLoading(true);
    FetchData.grabCentroidData()
      .then((data) => {
        setAllCentroidData(data);
      })
      .then(() => setIsLoading(false))
      .catch((err) => {
        setOnError(true);
      })
      .then(() => setIsLoading(false));
  };

  const fetchCentroidDotsData = () => {
    setIsLoading(true);
    FetchData.grabCentroidDotsData()
      .then((data) => {
        setallCentroidDotsData(data);
      })
      .then(() => setIsLoading(false))
      .catch((err) => {
        setOnError(true);
      })
      .then(() => {
        setIsLoading(false);
      });
  };

  useEffect(() => {
    if (!isLoading && allData != []) {
      fetchData();
    }
    if (!isLoading && allCentroidData != []) {
      fetchCentroidData();
    }
    if (!isLoading && allCentroidDotsData != []) {
      fetchCentroidDotsData();
    }
  }, []);

  return (
    <div className="screenCenter">
      {isLoading ? (
        <LoadingDisplay />
      ) : onError ? (
        <p>Uh Oh! ERROR</p>
      ) : (
        <>
          <Map data={allData} />
          <Map data={allCentroidData} />
          <Map data={allCentroidDotsData} isDots={true} />
        </>
      )}
    </div>
  );
};

export default Home;
