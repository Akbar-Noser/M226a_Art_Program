import {
  Container,
  FormControl,
  FormControlLabel,
  FormLabel,
  Radio,
  RadioGroup,
  TextField,
} from "@mui/material";
import { Form, Formik } from "formik";
import React, { useContext, useState } from "react";
import { NavContext } from "./NavProvider";
import "./css/Form.css";

export default function CreateFile() {
  const { setCurrentPath } = useContext(NavContext);
  setCurrentPath("/");

  interface FormFields {};

  const [renderOption, setRenderOption] = useState("");

  return (
    <Container className="form" maxWidth="sm">
      <Formik initialValues={{}} onSubmit={() => {}}>
        {({ isSubmitting }) => {
            return( <Form className="form-content" >
            <FormControl component="fieldset">
              <FormLabel component="legend">Layout</FormLabel>
              <RadioGroup row aria-label="layout" name="layout-selection-group">
                <FormControlLabel
                  value="template"
                  control={<Radio onClick={() => setRenderOption("template")} />}
                  label="Color Template"
                />
                <FormControlLabel
                  value="stripes"
                  control={<Radio onClick={() => setRenderOption("stripes")} />}
                  label="Stripes"
                />
                <FormControlLabel
                  value="diamond"
                  control={<Radio onClick={() => setRenderOption("diamond")}/>}
                  label="Diamonds"
                />
              </RadioGroup>
            </FormControl>
            
          </Form>);
        }}
      </Formik>
    </Container>
  );
}
