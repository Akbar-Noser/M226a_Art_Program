import axios, { AxiosResponse } from "axios";
import { FormFields } from "./CreateFile";

const client = axios.create({
  baseURL: "http://localhost:8080/create/"
});
export const CreatePictureService = {
  async createPicture(newPicture: FormFields): Promise<AxiosResponse<string>> {
    return client.post("", newPicture);
  },
  async getPicture(fileName: string): Promise<AxiosResponse<ArrayBuffer>> {
    return client.get(fileName, {responseType: 'blob'});
  }
};

export default CreatePictureService;
