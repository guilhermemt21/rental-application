import axios from "axios";

axios.defaults.baseURL = process.env.REACT_APP_API_ENDPOINT;

const PropertyAdminApi = {
  listProperty: function () {
    return axios.get('/api/property/all/');
  },

  createProperty: function (property) {
    return axios.post('/api/property/create', property);
  },

  editProperty: function (property) {
    return axios.put('/api/property/edit/', property);
  },

  deleteProperty: function (propertyId) {
    return axios.delete('/api/property/delete/' + propertyId);
  }

};

export default PropertyAdminApi