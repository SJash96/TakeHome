import React, { useCallback, useEffect, useState } from "react";
import {
  Circle,
  GoogleMap,
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
  const { isLoaded } = useJsApiLoader({
    id: "google-map-script",
    googleMapsApiKey: "AIzaSyAO8OYzLyQrnizNA_n3uEBXlQQ3AlhOgQQ",
  });

  const containerStyle = {
    width: "500px",
    height: "500px",
  };

  const center = {
    lat: 32.776665,
    lng: -96.796989,
  };

  return (
    <>
      <div className="spacer">
        {isLoaded ? (
          <GoogleMap
            mapContainerStyle={containerStyle}
            center={center}
            zoom={10}
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
                <Polygon key={obj.key} paths={obj.spatialObj.coordinates} />
              )
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
