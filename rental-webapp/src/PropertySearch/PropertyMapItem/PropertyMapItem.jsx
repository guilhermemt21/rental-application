import React, {Component} from "react";
import "./PropertyMapItem.scss";
import houseIcon from "../../assets/house.png";

export default class PropertyMapItem extends Component {
  render() {
    const isHovered = this.props.$hover || this.props.hoveredAtTable;

    return isHovered ? (
      <div className="property-map-item hover">
        <div className="property-type">{this.props.property.type}</div>
        <div className="property-address margin-top-5 margin-bottom-5">
          {this.props.property.address}
        </div>
        <div className="property-neighbourhood">
          {this.props.property.neighbourhood}
        </div>
        <div className="flex jc-space-between margin-top-5 margin-bottom-5">
          <div className="property-size">{this.props.property.size} ft²</div>
          <div className="property-bedrooms">
            {this.props.property.bedrooms} bedrooms
          </div>
        </div>
        <div className="property-rent-price margin-top-5">$ {this.props.property.price}</div>
      </div>
    ) : (
      <img src={houseIcon} width="20"/>
    );
  }
}
