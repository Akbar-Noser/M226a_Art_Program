import { AppBar, Box, IconButton, Toolbar, Typography } from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import React, { useContext } from "react";
import { NavContext } from "./NavProvider";
import { Link } from "react-router-dom";
import "./css/Navigation.css";

export default function Navbar() {
  const { currentPath } = useContext(NavContext);

  const underlineCurrentSite = (linkPath : string) => {
    return linkPath === currentPath ? {textDecoration: "underline", textDecorationThickness: "3px", textUnderlineOffset: "2px"} : {}
  }

  return (
    <Box  sx={{flexGrow: 1}}>
      <AppBar className="navigation" position="static">
        <Toolbar variant="regular">
          <IconButton
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
          >
            <MenuIcon />
          </IconButton>
          <Link className="nav-link" to="/">
            <Typography className="nav-element" style={underlineCurrentSite("/")} variant="h6" color="inherit" component="div">
              Create
            </Typography>
          </Link>
          <Link className="nav-link" to="/edit">
            <Typography className="nav-element" style={underlineCurrentSite("/edit")} variant="h6" color="inherit" component="div">
              Edit
            </Typography>
          </Link>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
