import axiosInstance from "helpers/axiosInstance";

const ROOT_URL = `/api`;

export async function Create(payload: object, url: string, history: any = null) {
  try {
    return await axiosInstance(history).post(`${ROOT_URL}/${url}/`, payload);
  } catch (error) {
    return error
  }
}

export async function Update(id: number, payload: object, url: string, history: any = null) {
  try {
    return await axiosInstance(history).patch(`${ROOT_URL}/${url}/${id}/`, payload);
  } catch (error) {
    return error
  }
}

export async function Delete(id: number, url: string, history: any = null) {
  try {
    return await axiosInstance(history).delete(`${ROOT_URL}/${url}/${id}/`);
  } catch (error) {
    return error
  }
}
