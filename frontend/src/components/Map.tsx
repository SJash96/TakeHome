import React, { useCallback, useEffect, useState } from "react";
import {
  Circle,
  GoogleMap,
  InfoWindow,
  Marker,
  Polygon,
  useJsApiLoader,
} from "@react-google-maps/api";
import LoadingDisplay from "./LoadingDisplay";
import "../styles/mapStyle.css";

interface Props {
  data: any;
  isDots?: boolean;
}

const Map = (props: Props) => {
  const [center, setCenter] = useState<any>({
    lat: 32.776665,
    lng: -96.796989,
  });
  const [openWindow, setOpenWindow] = useState(false);
  const [windowData, setWindowData] = useState<any>(null);

  const { isLoaded } = useJsApiLoader({
    id: "google-map-script",
    googleMapsApiKey: "AIzaSyAO8OYzLyQrnizNA_n3uEBXlQQ3AlhOgQQ",
  });

  const containerStyle = {
    width: "500px",
    height: "500px",
  };

  const polyMouseOver = (spatObj: any, e: any) => {
    setWindowData({
      data: spatObj,
      lat: e.latLng.lat((data: any) => {
        return data;
      }),
      lng: e.latLng.lng((data: any) => {
        return data;
      }),
    });
    setOpenWindow(true);
  };

  return (
    <>
      <div className="spacer">
        {isLoaded ? (
          <GoogleMap
            mapContainerStyle={containerStyle}
            center={center}
            zoom={10}
            onTilesLoaded={() => setCenter(null)}
          >
            {props.data.map((obj: any) =>
              props.isDots ? (
                <>
                  <Circle
                    key={obj.key}
                    center={{
                      lat: obj.spatialObj.coordinates[0].lat,
                      lng: obj.spatialObj.coordinates[0].lng,
                    }}
                    radius={1}
                  />
                </>
              ) : (
                <Polygon
                  key={obj.key}
                  paths={obj.spatialObj.coordinates}
                  onMouseDown={(e) => polyMouseOver(obj, e)}
                />
              )
            )}
            {openWindow && (
              <InfoWindow
                position={{
                  lat: windowData.lat,
                  lng: windowData.lng,
                }}
                onCloseClick={() => setOpenWindow(false)}
              >
                <div>
                  <div>Total Income: {windowData.data.income}</div>
                  <div>Total Population: {windowData.data.population}</div>
                </div>
              </InfoWindow>
            )}
          </GoogleMap>
        ) : (
          <LoadingDisplay />
        )}
      </div>
    </>
  );
};

export default Map;
