export default class FetchData {
  static grabAllData = async () => {
    const data = await fetch("http://localhost:8080/AllDataFront");
    const resp = await data.json();
    return resp;
  };

  static grabCentroidData = async () => {
    const data = await fetch("http://localhost:8080/CentroidDataFront");
    const resp = await data.json();
    return resp;
  };

  static grabCentroidDotsData = async () => {
    const data = await fetch("http://localhost:8080/CentroidDataDotsFront");
    const resp = await data.json();
    return resp;
  };
}
