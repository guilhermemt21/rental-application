import React, {Component} from "react";
import PropertyList from "./PropertyList";
import AddProperty from "./AddProperty";
import PropertyAdminApi from "./PropertyAdminApi";
import "./PropertyAdmin.scss";

export class PropertyAdmin extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isAddProperty: false,
      error: null,
      response: {},
      property: {},
      isEditProperty: false
    };
    this.onFormSubmit = this.onFormSubmit.bind(this);
  }

  onCreate() {
    this.setState({isAddProperty: true});
  }

  onFormSubmit(data) {
    PropertyAdminApi.createProperty(data).then(result => {
      this.setState({
        response: result,
        isAddProperty: false,
        isEditProperty: false
      });
    });
  }

  render() {

    let propertyForm;
    if (this.state.isAddProperty || this.state.isEditProperty) {
      propertyForm = <AddProperty onFormSubmit={this.onFormSubmit}/>
    }

    return (
      <div className="property-admin">

        <div className="content-holder">
          <h2 className="align-center">Property management</h2>
          <div className="flex">
            {!this.state.isAddProperty && <div className="button" onClick={() => this.onCreate()} id="add-property-btn">Add Property</div>}
          </div>
        </div>


        <div className="content-holder margin-top-20">
          {!this.state.isAddProperty && <PropertyList editProperty={this.editProperty}/>}
          { propertyForm }
        </div>
      </div>
    );
  }
}

export default PropertyAdmin;