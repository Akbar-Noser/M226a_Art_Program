import React, { useContext } from "react";
import AddBoxIcon from "@mui/icons-material/AddBox";
import EditIcon from "@mui/icons-material/Edit";
import "./css/Home.css";
import { Card, Container, Paper } from "@mui/material";
import { Link } from "react-router-dom";
import { NavContext } from "./NavProvider";

export default function Home() {
  const { setCurrentPath } = useContext(NavContext);
  setCurrentPath("/");
  return (
    <Container
      style={{ display: "flex", justifyContent: "space-evenly" }}
      maxWidth="lg"
    >
      <Link className="element-link" to="/create">
        <Paper className="element-paper" elevation={24}>
          <AddBoxIcon className="navigation-icon" fontSize="large" />
        </Paper>
      </Link>
      <Link className="element-link" to="/edit">
        <Paper className="element-paper" elevation={24}>
          <EditIcon
            id="edit-icon"
            className="navigation-icon"
            fontSize="large"
          />
        </Paper>
      </Link>
    </Container>
  );
}
