import {
  Button,
  Container,
  FormControl,
  FormControlLabel,
  FormLabel,
  Radio,
  RadioGroup,
  Switch,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  TextField,
} from "@mui/material";
import { Form, Formik } from "formik";
import React, { useContext, useState } from "react";
import { NavContext } from "./NavProvider";
import "./css/Form.css";
import "./css/table/Table.css";

const DIAMOND_OPTION = "diamond";
const STRIPES_OPTION = "stripes";
const TEMPLATE_OPTION = "template";
const ANALOGOUS_OPTION = "analogous";
const TETRADIC_OPTION = "tetradic";
const TRIADIC_OPTION = "triadic";
const COMPLEMENTARY_OPTION = "complementary";

export interface FormFields {
  colorScheme: string;
  amountOfShades: number;
  layout:string;
  evenSpacing:boolean;
  stripeThickness:number;
  layers:number[];
}

export default function CreateFile() {
  const { setCurrentPath } = useContext(NavContext);
  setCurrentPath("/create");



  const defaultFormValues = {
    colorScheme: "",
    amountOfShades: 0,
    layout: "",
    evenSpacing: false,
    stripeThickness: 0,
    layers: []
  }

  const [layoutRenderOption, setLayoutRenderOption] = useState("");
  const [schemeRenderOption, setSchemeRenderOption] = useState("");

  return (
    <Container className="form" maxWidth="sm">
      <Formik initialValues={defaultFormValues} onSubmit={(values: FormFields, {setSubmitting}) => {
        
      }}>
        {({ isSubmitting }) => {
          return (
            <Form className="form-content">
              <FormControl className="radio-group" component="fieldset">
                <FormLabel component="legend">Color Scheme</FormLabel>
                <RadioGroup
                  row
                  aria-label="scheme"
                  name="color_scheme_selection_group"
                >
                  <FormControlLabel
                    value={ANALOGOUS_OPTION}
                    control={
                      <Radio
                        onClick={() => setSchemeRenderOption(ANALOGOUS_OPTION)}
                      />
                    }
                    label="Analogous"
                  />
                  <FormControlLabel
                    value={TETRADIC_OPTION}
                    control={
                      <Radio
                        onClick={() => setSchemeRenderOption(TETRADIC_OPTION)}
                      />
                    }
                    label="Tetradic"
                  />
                  <FormControlLabel
                    value={TRIADIC_OPTION}
                    control={
                      <Radio
                        onClick={() => setSchemeRenderOption(TRIADIC_OPTION)}
                      />
                    }
                    label="Triadic"
                  />
                  <FormControlLabel
                    value={COMPLEMENTARY_OPTION}
                    control={
                      <Radio
                        onClick={() =>
                          setSchemeRenderOption(COMPLEMENTARY_OPTION)
                        }
                      />
                    }
                    label="Complementary"
                  />
                </RadioGroup>
              </FormControl>
              {schemeConditionalRendering(schemeRenderOption)}
              <FormControl className="radio-group" component="fieldset">
                <FormLabel component="legend">Layout</FormLabel>
                <RadioGroup
                  row
                  aria-label="layout"
                  name="layout_selection_group"
                >
                  <FormControlLabel
                    value={TEMPLATE_OPTION}
                    control={
                      <Radio
                        onClick={() => setLayoutRenderOption(TEMPLATE_OPTION)}
                      />
                    }
                    label="Color Template"
                  />
                  <FormControlLabel
                    value={STRIPES_OPTION}
                    control={
                      <Radio
                        onClick={() => setLayoutRenderOption(STRIPES_OPTION)}
                      />
                    }
                    label="Stripes"
                  />
                  <FormControlLabel
                    value={DIAMOND_OPTION}
                    control={
                      <Radio
                        onClick={() => setLayoutRenderOption(DIAMOND_OPTION)}
                      />
                    }
                    label="Diamonds"
                  />
                </RadioGroup>
              </FormControl>
              {layoutConditionalRendering(layoutRenderOption)}
              <div style={{ textAlign: "center" }}>
                <Button
                  id="submit"
                  type="submit"
                  style={{
                    backgroundColor: "rgba(23, 33, 33, 0.466)",
                    marginTop: "5%",
                  }}
                  variant="contained"
                >
                  Create
                </Button>
              </div>
            </Form>
          );
        }}
      </Formik>
    </Container>
  );
}

function schemeConditionalRendering(option: string) {
  if (option === ANALOGOUS_OPTION) {
    return <AnalogousRendering />;
  }
}

function AnalogousRendering() {
  return (
    <TextField
      className="input-field"
      label="Amount of Shades"
      name="amount_of_shades"
      variant="filled"
    />
  );
}

function layoutConditionalRendering(option: string) {
  if (option === DIAMOND_OPTION) {
    return <DiamondOptions />;
  }
  if (option === STRIPES_OPTION) {
    return <StripeOptions />;
  }

  return <></>;
}

function StripeOptions() {
  return (
    <>
      <FormControlLabel
        className="input-field"
        control={<Switch defaultChecked />}
        label="Even Spacing"
      />
      <TextField
        className="input-field"
        label="Stripe Thickness (pixel)"
        name="stripe_thickness"
        variant="filled"
      />
    </>
  );
}

function DiamondOptions() {
  const [diamondLayerAmount, setDiamondLayerAmount] = useState(0);
  return (
    <>
      <TextField
        className="input-field"
        label="Amount of layers"
        name="diamond_side_length"
        variant="filled"
        helperText="layers get generated from smallest number to largest"
        onChange={(event) =>
          setDiamondLayerAmount(parseInt(event.target.value))
        }
      />
      <TableContainer
        sx={{ maxHeight: "30vh", maxWidth: "80vw", margin: "auto" }}
      >
        <Table className="table" style={{ marginTop: "5%" }} stickyHeader>
          <TableHead>
            <TableRow>
              <TableCell className="table-title" align="center">
                Layer
              </TableCell>
              <TableCell className="table-title" align="center">
                Sidelength (pixel)
              </TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {GenerateTableContent(diamondLayerAmount).map((row) => row)}
          </TableBody>
        </Table>
      </TableContainer>
    </>
  );
}

const GenerateTableContent = (amountOfLayers: number) => {
  let tableContents: JSX.Element[] = [];
  for (let index = 1; index <= amountOfLayers; index++) {
    tableContents.push(TableRowLayer(index));
  }
  return tableContents;
};

const TableRowLayer = (index: number) => {
  return (
    <TableRow>
      <TableCell className="table-cell" align="center">
        {index}
      </TableCell>
      <TableCell className="table-cell" align="center">
        <TextField
          className="input-field"
          name={"layer_".concat(String(index))}
          variant="standard"
        />
      </TableCell>
    </TableRow>
  );
};
