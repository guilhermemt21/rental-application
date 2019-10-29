import axios from "axios";
import Immutable from "immutable";

axios.defaults.baseURL = process.env.REACT_APP_API_ENDPOINT;

const PropertySearchApi = {
  searchProperties: function (params, orderBy) {
    return axios.get('/api/property/list/', {params: params}).then(res => {
      const propertiesList = Immutable.List(res.data.properties);
      const sortedList = propertiesList.sortBy((property)=> property.price);

      if (orderBy === 'lowest') {
        return sortedList;
      } else {
        return sortedList.reverse();
      }
    });
  }
};

export default PropertySearchApi