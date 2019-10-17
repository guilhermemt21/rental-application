import React from "react";
import "./PropertyCard.scss";

const PropertyCard = props => (
  <div>
    <div className="single-photo">
      <img className="cover" src={props.urls.thumb} alt="" />
    </div>
    <div className="property-details">
      <div className="property-type">{props.type}</div>
      <div className="property-address margin-top-10">{props.address}</div>
      <div className="property-neighbourhood">{props.neighbourhood}</div>
      <div className="flex jc-space-between margin-top-10 margin-bottom-10">
        <div className="property-size">{props.size} ft²</div>
        <div className="property-bedrooms">{props.bedrooms} bedrooms</div>
      </div>
      <div className="property-rent-price margin-top-10">$ {props.price}</div>
    </div>
  </div>
);

export default PropertyCard;
