import React, { Component } from "react";
import axios from "axios";
import InfiniteScroll from "react-infinite-scroll-component";
import PropertyCard from "./PropertyCard/PropertyCard";
import PropertyMapItem from "./PropertyMapItem/PropertyMapItem";
import "./PropertySearch.scss";
import GoogleMapReact from "google-map-react";
import PropertyFilterPanel from "./PropertyFilterPanel/PropertyFilterPanel";

export class PropertySearch extends Component {
  constructor() {
    super();
    this.state = {
      properties: [],
      propertiesGrid: [],
      count: 9,
      start: 0,
      displayingFilters: false,
      orderBy: "highest",
      minLatitude: 49.199825,
      maxLatitude: 49.199825,
      minLongitude: -122.968626,
      maxLongitude: -122.968626,
      hasMorePropertiesToLoad: true,
      filterProperties: {}
    };
  }

  static defaultProps = {
    center: {
      lat: 49.199825,
      lng: -122.968626
    },
    zoom: 13
  };

  componentDidMount() {
    this.loadPropertiesFromMap();
  }

  loadPropertiesFromMap = () => {
    const {
      orderBy,
      minLatitude,
      minLongitude,
      maxLatitude,
      maxLongitude,
      filterProperties
    } = this.state;

    let params = {
      minLatitude: minLatitude,
      maxLatitude: maxLatitude,
      minLongitude: minLongitude,
      maxLongitude: maxLongitude,
      bedroomsCount: filterProperties.bedroomsCount,
      minPrice: filterProperties.minPrice,
      maxPrice: filterProperties.maxPrice,
      minSize: filterProperties.minSize,
      maxSize: filterProperties.maxSize
    };

    axios
      .get(process.env.REACT_APP_API_ENDPOINT + "/api/property/list/", {
        params: params
      })
      .then(res => {
        let sortedProperties = [];
        if (orderBy === "lowest") {
          sortedProperties = res.data.properties.sort((a, b) =>
            a.price > b.price ? 1 : b.price > a.price ? -1 : 0
          );
        } else {
          sortedProperties = res.data.properties.sort((a, b) =>
            a.price > b.price ? -1 : b.price > a.price ? 1 : 0
          );
        }
        let properties = [...sortedProperties];
        this.setState(
          {
            properties: properties,
            propertiesGrid: [],
            start: 0
          },
          this.loadNextPageProperties
        );
      });
  };

  loadNextPageProperties = () => {
    const { count, start, properties, propertiesGrid } = this.state;
    for (var i = start; i < properties.length && i < count + start; i = i + 3) {
      propertiesGrid.push(properties.slice(i, i + 3));
    }
    this.setState({
      propertiesGrid: propertiesGrid,
      start: start + count,
      hasMorePropertiesToLoad: properties.length > start + count
    });
  };

  applyFilterHandler = filterProperties => {
    this.setState(
      { filterProperties: filterProperties, displayingFilters: false },
      this.loadPropertiesFromMap
    );
  };

  orderBy = orderBy => {
    this.setState({ orderBy: orderBy }, this.loadPropertiesFromMap);
  };

  toggleFilter = () => {
    const { displayingFilters } = this.state;
    this.setState({ displayingFilters: !displayingFilters });
  };

  _onChange = obj => {
    this.setState(
      {
        minLatitude: obj.marginBounds.se.lat,
        maxLatitude: obj.marginBounds.ne.lat,
        minLongitude: obj.marginBounds.sw.lng,
        maxLongitude: obj.marginBounds.se.lng
      },
      this.loadPropertiesFromMap
    );
  };

  onMouseEnterPropertyCard = card => {
    this.setState({
      propertyIdHoveredAtTable: card.id
    });
  };

  onMouseLeavePropertyCard = () => {
    this.setState({
      propertyIdHoveredAtTable: null
    });
  };

  render() {
    const {
      hasMorePropertiesToLoad,
      properties,
      propertiesGrid,
      orderBy,
      displayingFilters,
      propertyIdHoveredAtTable
    } = this.state;

    return (
      <div className="property-search">
        <div className="header">
          <h4>Unique Rental Experience</h4>
        </div>
        <div className="properties-content">
          <div className="properties-map">
            <div style={{ height: "100%", width: "100%" }}>
              <GoogleMapReact
                bootstrapURLKeys={{
                  key: process.env.REACT_APP_GOOGLE_MAPS_KEY
                }}
                onChange={this._onChange}
                margin={[20, 40, 60, 40]}
                defaultCenter={this.props.center}
                defaultZoom={this.props.zoom}
              >
                {properties.map(property => (
                  <PropertyMapItem
                    key={property.id}
                    lat={property.latitude}
                    lng={property.longitude}
                    property={property}
                    hoveredAtTable={property.id === propertyIdHoveredAtTable}
                  ></PropertyMapItem>
                ))}
              </GoogleMapReact>
            </div>
          </div>

          <div className="properties-listing">
            <div className="flex ai-center jc-space-between properties-listing-context">
              <span>
                Properties to rent on <b>Vancouver</b>
              </span>
              <div className="flex">
                <div className="button" onClick={() => this.toggleFilter()}>
                  Filter
                </div>
                <div className="button margin-left-10">Alert</div>
              </div>
            </div>

            <div
              className="flex ai-center jc-space-between properties-listing-context"
              style={{ display: displayingFilters ? "none" : "flex" }}
            >
              <div>
                <b>{properties.length}</b> Properties found
              </div>
              <div className="flex ai-center">
                <span>Order by</span>
                <div
                  className={
                    orderBy === "lowest"
                      ? "button active margin-left-10 margin-right-10"
                      : "button margin-left-10 margin-right-10"
                  }
                  onClick={() => this.orderBy("lowest")}
                >
                  Lowest price
                </div>
                <div
                  className={orderBy === "highest" ? "button active" : "button"}
                  onClick={() => this.orderBy("highest")}
                >
                  Highest price
                </div>
              </div>
            </div>

            <div
              style={{
                display: displayingFilters ? "block" : "none",
                height: "100%"
              }}
            >
              <PropertyFilterPanel
                applyFilterHandler={this.applyFilterHandler}
              ></PropertyFilterPanel>
            </div>

            <div
              className="properties-listing-results"
              style={{ display: displayingFilters ? "none" : "block" }}
            >
              <InfiniteScroll
                dataLength={propertiesGrid.length}
                next={this.loadNextPageProperties}
                height={500}
                hasMore={hasMorePropertiesToLoad}
                loader={<h4>Loading...</h4>}
              >
                {this.state.propertiesGrid.map((propertiesRow, index) => (
                  <div
                    className="properties-row flex jc-space-evenly"
                    key={index}
                  >
                    {propertiesRow.map(property => (
                      <div
                        className="property-card"
                        key={property.id}
                        onMouseEnter={() =>
                          this.onMouseEnterPropertyCard(property)
                        }
                        onMouseLeave={this.onMouseLeavePropertyCard}
                      >
                        <PropertyCard
                          key={property.id}
                          {...property}
                        ></PropertyCard>
                      </div>
                    ))}
                  </div>
                ))}
              </InfiniteScroll>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default PropertySearch;
