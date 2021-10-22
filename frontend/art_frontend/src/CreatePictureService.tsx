import axios, { AxiosResponse } from "axios";
import { FormFields } from "./CreateFile";

const client = axios.create({
  baseURL: "http://localhost:8080/create/",
});
export const ProductService = {
  async addProduct(newPost: FormFields): Promise<AxiosResponse< FormFields>> {
    return client
    .post("", newPost);
  }
};

export default ProductService;
