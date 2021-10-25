import axios, { AxiosResponse } from "axios";
import { FormFields } from "./CreateFile";

const client = axios.create({
  baseURL: "http://localhost:8080/create/",
});
export const CreatePictureService = {
  async createPicture(newPicture: FormFields): Promise<AxiosResponse< FormFields>> {
    return axios({
      method: "GET",
      url: "http://localhost:8080/create/",
      headers: {},
      params: {input: newPicture}
    }) as unknown as AxiosResponse<FormFields, any>;
  }
};

export default CreatePictureService;
