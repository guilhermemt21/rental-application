import React from "react";
import PropertyAdminApi from "./PropertyAdminApi";
import AddProperty from "./AddProperty";
import "./PropertyAdmin.scss";

class PropertyList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      properties: [],
      response: {}
    }
  }

  componentDidMount() {
    PropertyAdminApi.listProperty().then(result => {
      this.setState({
        properties: result.data,
        isAddProperty: false,
        isEditProperty: false
      });
    });
  }

  deleteProperty(propertyId) {
    PropertyAdminApi.deleteProperty(propertyId).then(() => {
      PropertyAdminApi.listProperty().then(result => {
        this.setState({
          properties: result.data,
          isAddProperty: false,
          isEditProperty: false
        });
      });
    });
  }


  editProperty = property => {
    PropertyAdminApi.editProperty(property).then(() => {
      PropertyAdminApi.listProperty().then(result => {
        this.setState({
          properties: result.data,
          isAddProperty: false,
          isEditProperty: false
        });
      });
    });
  };

  initEditProperty = property => {
    this.setState({
      property: property,
      isEditProperty: true
    });
  };

  render() {
    const {properties} = this.state;


    let propertyForm;
    if (this.state.isEditProperty) {
      propertyForm = <AddProperty onFormSubmit={this.editProperty} property={this.state.property}/>
    }

    return (
      <div className="property-list">
        <h4>Property List</h4>

        {propertyForm}

        <table>
          <thead>
          <tr>
            <th>Type</th>
            <th>Address</th>
            <th>Neighbourhood</th>
            <th>Size</th>
            <th>Bedrooms</th>
            <th>Price</th>
            <th style={{width: '100px'}}>Action</th>
          </tr>
          </thead>
          <tbody>
          {properties.map(property => (
            <tr key={property.id}>
              <td>{property.type}</td>
              <td>{property.address}</td>
              <td>{property.neighbourhood}</td>
              <td>{property.size}</td>
              <td>{property.bedrooms}</td>
              <td>{property.price}</td>
              <td>
                <div className="flex">
                  <div className="button margin-right-20" onClick={() => this.deleteProperty(property.id)}>Delete</div>
                  <div className="button" onClick={() => this.initEditProperty(property)}>Edit</div>
                </div>
              </td>
            </tr>
          ))}
          </tbody>
        </table>
      </div>
    )
  }
}

export default PropertyList;