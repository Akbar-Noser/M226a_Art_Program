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
import CreatePictureService from "./CreatePictureService";

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
  layout: string;
  evenSpacing: boolean;
  stripeThickness: number;
  layers: number[];
}

const layerNames: string[] = [];

export default function CreateFile() {
  const { setCurrentPath } = useContext(NavContext);
  setCurrentPath("/create");

  const defaultFormValues = {
    colorScheme: "",
    amountOfShades: 0,
    layout: "",
    evenSpacing: false,
    stripeThickness: 0,
    layers: [],
  };

  const [layoutRenderOption, setLayoutRenderOption] = useState("");
  const [schemeRenderOption, setSchemeRenderOption] = useState("");

  return (
    <Container className="form" maxWidth="sm">
      <Formik
        initialValues={defaultFormValues}
        onSubmit={(values: FormFields, { setSubmitting }) => {
          console.log(values);
          let layers: number[] = [];
          // eslint-disable-next-line array-callback-return
          layerNames.map((layerName) => {
            console.log(layerName);
            document.getElementsByName(layerName).forEach((layer) => {
              let value = (layer as HTMLInputElement).value;
              console.log(layer);
              console.log((layer as HTMLInputElement).value);
              layers.push(parseInt(value));
            });
          });
          let picture = {
            colorScheme: values.colorScheme,
            amountOfShades: values.amountOfShades,
            layout: values.layout,
            evenSpacing: values.evenSpacing,
            stripeThickness: values.stripeThickness,
            layers: layers,
          };
          console.log(picture);
          CreatePictureService.createPicture(picture)
            .then((fileName) => CreatePictureService.getPicture(fileName.data))
            .then((response) => {
              console.log(response.data)
              const url = window.URL.createObjectURL(new Blob([response.data], {type: "image/png"}));
              const link = document.createElement('a');
              link.href = url;
              link.setAttribute('download', "MyImage.png");
              document.body.appendChild(link);
              link.click();
            })
            .catch((response) => console.log(response));
          setSubmitting(false);
        }}
      >
        {({ isSubmitting, handleChange }) => {
          return (
            <Form className="form-content">
              <FormControl className="radio-group" component="fieldset">
                <FormLabel component="legend">Color Scheme</FormLabel>
                <RadioGroup
                  row
                  aria-label="scheme"
                  name="colorScheme"
                  onChange={handleChange}
                >
                  <FormControlLabel
                    value={ANALOGOUS_OPTION}
                    control={
                      <Radio
                        value={ANALOGOUS_OPTION}
                        onClick={() => setSchemeRenderOption(ANALOGOUS_OPTION)}
                      />
                    }
                    label="Analogous"
                  />
                  <FormControlLabel
                    value={TETRADIC_OPTION}
                    control={
                      <Radio
                        value={TETRADIC_OPTION}
                        onClick={() => setSchemeRenderOption(TETRADIC_OPTION)}
                      />
                    }
                    label="Tetradic"
                  />
                  <FormControlLabel
                    value={TRIADIC_OPTION}
                    control={
                      <Radio
                        value={TRIADIC_OPTION}
                        onClick={() => setSchemeRenderOption(TRIADIC_OPTION)}
                      />
                    }
                    label="Triadic"
                  />
                  <FormControlLabel
                    value={COMPLEMENTARY_OPTION}
                    control={
                      <Radio
                        value={COMPLEMENTARY_OPTION}
                        onClick={() =>
                          setSchemeRenderOption(COMPLEMENTARY_OPTION)
                        }
                      />
                    }
                    label="Complementary"
                  />
                </RadioGroup>
              </FormControl>
              {schemeConditionalRendering(schemeRenderOption, handleChange)}
              <FormControl className="radio-group" component="fieldset">
                <FormLabel component="legend">Layout</FormLabel>
                <RadioGroup
                  row
                  aria-label="layout"
                  name="layout"
                  onChange={handleChange}
                >
                  <FormControlLabel
                    value={TEMPLATE_OPTION}
                    control={
                      <Radio
                        value={TEMPLATE_OPTION}
                        onClick={() => setLayoutRenderOption(TEMPLATE_OPTION)}
                      />
                    }
                    label="Color Template"
                  />
                  <FormControlLabel
                    value={STRIPES_OPTION}
                    control={
                      <Radio
                        value={STRIPES_OPTION}
                        onClick={() => setLayoutRenderOption(STRIPES_OPTION)}
                      />
                    }
                    label="Stripes"
                  />
                  <FormControlLabel
                    value={DIAMOND_OPTION}
                    control={
                      <Radio
                        value={DIAMOND_OPTION}
                        onClick={() => setLayoutRenderOption(DIAMOND_OPTION)}
                      />
                    }
                    label="Diamonds"
                  />
                </RadioGroup>
              </FormControl>
              {layoutConditionalRendering(layoutRenderOption, handleChange)}
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

function schemeConditionalRendering(
  option: string,
  handleChange: {
    (e: React.ChangeEvent<any>): void;
    <T = string | React.ChangeEvent<any>>(
      field: T
    ): T extends React.ChangeEvent<any>
      ? void
      : (e: string | React.ChangeEvent<any>) => void;
  }
) {
  if (option === ANALOGOUS_OPTION) {
    return <AnalogousRendering handleChange={handleChange} />;
  }
}

interface Change {
  handleChange: {
    (e: React.ChangeEvent<any>): void;
    <T = string | React.ChangeEvent<any>>(
      field: T
    ): T extends React.ChangeEvent<any>
      ? void
      : (e: string | React.ChangeEvent<any>) => void;
  };
}

function AnalogousRendering({ handleChange }: Change) {
  return (
    <TextField
      className="input-field"
      label="Amount of Shades"
      name="amountOfShades"
      variant="filled"
      onChange={handleChange}
    />
  );
}

function layoutConditionalRendering(
  option: string,
  handleChange: {
    (e: React.ChangeEvent<any>): void;
    <T = string | React.ChangeEvent<any>>(
      field: T
    ): T extends React.ChangeEvent<any>
      ? void
      : (e: string | React.ChangeEvent<any>) => void;
  }
) {
  if (option === DIAMOND_OPTION) {
    return <DiamondOptions handleChange={handleChange} />;
  }
  if (option === STRIPES_OPTION) {
    return <StripeOptions handleChange={handleChange} />;
  }

  return <></>;
}

function StripeOptions({ handleChange }: Change) {
  return (
    <>
      <FormControlLabel
        className="input-field"
        control={
          <Switch name="evenSpacing" onChange={handleChange} defaultChecked />
        }
        label="Even Spacing"
      />
      <TextField
        className="input-field"
        label="Stripe Thickness (pixel)"
        name="stripeThickness"
        variant="filled"
        onChange={handleChange}
      />
    </>
  );
}

function DiamondOptions({ handleChange }: Change) {
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
            {GenerateTableContent(diamondLayerAmount, handleChange).map(
              (row) => row
            )}
          </TableBody>
        </Table>
      </TableContainer>
    </>
  );
}

const GenerateTableContent = (
  amountOfLayers: number,
  handleChange: {
    (e: React.ChangeEvent<any>): void;
    <T = string | React.ChangeEvent<any>>(
      field: T
    ): T extends React.ChangeEvent<any>
      ? void
      : (e: string | React.ChangeEvent<any>) => void;
  }
) => {
  let tableContents: JSX.Element[] = [];
  while (layerNames.length !== 0) {
    layerNames.pop();
  }
  for (let index = 1; index <= amountOfLayers; index++) {
    tableContents.push(TableRowLayer(index, handleChange));
  }
  return tableContents;
};

const TableRowLayer = (
  index: number,
  handleChange: {
    (e: React.ChangeEvent<any>): void;
    <T = string | React.ChangeEvent<any>>(
      field: T
    ): T extends React.ChangeEvent<any>
      ? void
      : (e: string | React.ChangeEvent<any>) => void;
  }
) => {
  return (
    <TableRow>
      <TableCell className="table-cell" align="center">
        {index}
      </TableCell>
      <TableCell className="table-cell" align="center">
        <TextField
          className="input-field"
          name={formLayerName(index)}
          variant="standard"
          onChange={handleChange}
        />
      </TableCell>
    </TableRow>
  );
};

const formLayerName = (index: number) => {
  let layerName = "layer".concat(String(index));
  layerNames.push(layerName);
  return layerName;
};
