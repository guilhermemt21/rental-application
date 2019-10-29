import React from "react";
import {isAuthenticated} from "./auth";
import {PropertySearch} from "./PropertySearch/PropertySearch";
import {PropertyAdmin} from "./PropertyAdmin/PropertyAdmin";
import {BrowserRouter, Route, Switch, Redirect} from "react-router-dom";

const PrivateRoute = ({component: Component, ...rest}) => (
  <Route
    {...rest}
    render={props =>
      isAuthenticated() ? (
        <Component {...props} />
      ) : (
        <Redirect to={{ pathname: "/", state: { from: props.location } }} />
      )
    }
  />
);

const Routes = () => (
  <BrowserRouter>
    <Switch>
      <Route exact path="/" component={() => <PropertySearch></PropertySearch>}/>
      <PrivateRoute path="/admin" component={() => <PropertyAdmin></PropertyAdmin>}/>
    </Switch>
  </BrowserRouter>
);

export default Routes;