import React from "react";

class AddProperty extends React.Component {
  constructor(props) {
    super(props);
    this.initialState = {
      id: '',
      type: '',
      address: '',
      neighbourhood: '',
      size: '',
      bedrooms: '',
      price: ''
    };

    if (props.property) {
      this.state = props.property;
    } else {
      this.state = this.initialState;
    }

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    const name = event.target.name;
    const value = event.target.value;

    this.setState({
      [name]: value
    })
  }

  handleSubmit() {
    this.props.onFormSubmit(this.state);
    this.setState(this.initialState);
  }

  render() {

    let pageTitle;
    if (this.state.id) {
      pageTitle = <h5>Edit Property</h5>
    } else {
      pageTitle = <h5>Add Property</h5>
    }

    return (
      <div className="property-upsert">
        {pageTitle}


        <form onSubmit={this.handleSubmit}>
          <div className="flex ai-end">
            <div className="margin-right-20">
              <div>Type</div>
              <input id="type" type="text" name="type" value={this.state.type} onChange={this.handleChange} placeholder="Property Type"/>
            </div>
            <div className="margin-right-20">
              <div>Address</div>
              <input id="address" type="text" name="address" value={this.state.address} onChange={this.handleChange} placeholder="Property address"/>
            </div>
            <div className="margin-right-20">
              <div>Neighbourhood</div>
              <input id="neighbourhood" type="text" name="neighbourhood" value={this.state.neighbourhood} onChange={this.handleChange} placeholder="Neighbourhood"/>
            </div>
            <div className="margin-right-20">
              <div>Size</div>
              <input id="size" type="text" name="size" value={this.state.size} onChange={this.handleChange} placeholder="Property size"/>
            </div>
            <div className="margin-right-20">
              <div>Bedrooms</div>
              <input id="bedrooms" type="text" name="bedrooms" value={this.state.bedrooms} onChange={this.handleChange} placeholder="Property bedrooms"/>
            </div>
            <div className="margin-right-20">
              <div>Price</div>
              <input id="price" type="text" name="price" value={this.state.price} onChange={this.handleChange} placeholder="Property price"/>
            </div>
            <div>
              <input type="hidden" name="id" value={this.state.id}/>
              <div id="save-property-btn" className="button" onClick={this.handleSubmit}>Save</div>
            </div>
          </div>
        </form>
      </div>
    )
  }
}

export default AddProperty;