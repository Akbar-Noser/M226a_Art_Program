import React from "react";
import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Navbar from "./Navbar";
import NavProvider from "./NavProvider";
import CreateFile from "./CreateFile";
import EditFile from "./EditFile";
import Home from "./Home";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <NavProvider>
          <Switch>
            <Route exact path="/create"><CreateFile/></Route>
            <Route exact path="/edit"><EditFile/></Route>
            <Route exact path="/"><Home/></Route>
          </Switch>
        </NavProvider>
      </BrowserRouter>
    </div>
  );
}

export default App;
