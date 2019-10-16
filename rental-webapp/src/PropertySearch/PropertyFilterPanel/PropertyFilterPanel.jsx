import React, { Component } from "react";
import Slider from "rc-slider";
import "./PropertyFilterPanel.scss";
import "rc-slider/assets/index.css";

const createSliderWithTooltip = Slider.createSliderWithTooltip;
const Range = createSliderWithTooltip(Slider.Range);

export default class PropertyFilterPanel extends Component {
  constructor() {
    super();
    this.state = {
      minPriceRange: 0,
      maxPriceRange: 50000,
      minSizeRange: 0,
      maxSizeRange: 2000,
      filterProperties: {
        minPrice: 0,
        maxPrice: 50000,
        minSize: 0,
        maxSize: 2000,
        bedroomsCount: null
      }
    };
  }

  onPriceChanged = interval => {
    const { filterProperties } = this.state;
    filterProperties.minPrice = interval[0];
    filterProperties.maxPrice = interval[1];
    this.setState({
      filterProperties
    });
  };

  onSizeChanged = interval => {
    const { filterProperties } = this.state;
    filterProperties.minSize = interval[0];
    filterProperties.maxSize = interval[1];
    this.setState({
      filterProperties
    });
  };

  bedroomsCountChanged = count => {
    const { filterProperties } = this.state;
    filterProperties.bedroomsCount = count;
    this.setState({
      filterProperties
    });
  };

  render() {
    const {
      minSizeRange,
      maxSizeRange,
      minPriceRange,
      maxPriceRange,
      filterProperties
    } = this.state;

    const applyFilterHandler = this.props.applyFilterHandler;

    return (
      <div className="properties-listing-filter">
        <div className="filter-section">
          <span>PRICE</span>
          <div className="margin-top-10 align-center">
            <span>
              ${filterProperties.minPrice} - ${filterProperties.maxPrice}
            </span>
          </div>
          <div className="rc-slider-container">
            <Range
              min={minPriceRange}
              max={maxPriceRange}
              defaultValue={[minPriceRange, maxPriceRange]}
              onAfterChange={this.onPriceChanged}
              tipFormatter={value => `$ ${value}`}
              tipProps={{ prefixCls: "rc-slider-tooltip" }}
            />
          </div>
        </div>
        <div className="filter-section">
          <span>BEDROOMS</span>
          <div className="flex ai-center filter-options">
            <div
              className={
                filterProperties.bedroomsCount === 1
                  ? "clickable-action selected"
                  : "clickable-action"
              }
              onClick={() => this.bedroomsCountChanged(1)}
            >
              1+
            </div>
            <div
              className={
                filterProperties.bedroomsCount === 2
                  ? "clickable-action selected"
                  : "clickable-action"
              }
              onClick={() => this.bedroomsCountChanged(2)}
            >
              2+
            </div>
            <div
              className={
                filterProperties.bedroomsCount === 3
                  ? "clickable-action selected"
                  : "clickable-action"
              }
              onClick={() => this.bedroomsCountChanged(3)}
            >
              3+
            </div>
            <div
              className={
                filterProperties.bedroomsCount === 4
                  ? "clickable-action selected"
                  : "clickable-action"
              }
              onClick={() => this.bedroomsCountChanged(4)}
            >
              4+
            </div>
          </div>
        </div>
        <div className="filter-section">
          <span>SIZE</span>
          <div className="margin-top-10 align-center">
            <span>
              {filterProperties.minSize} ft² - {filterProperties.maxSize} ft²
            </span>
          </div>
          <div className="rc-slider-container">
            <Range
              min={minSizeRange}
              max={maxSizeRange}
              defaultValue={[minSizeRange, maxSizeRange]}
              onAfterChange={this.onSizeChanged}
              tipFormatter={value => `${value} ft²`}
              tipProps={{ prefixCls: "rc-slider-tooltip" }}
            />
          </div>
        </div>
        <div className="flex ai-center">
          <div
            className="apply-filter"
            onClick={() => applyFilterHandler(filterProperties)}
          >Apply filter</div>
        </div>
      </div>
    );
  }
}
