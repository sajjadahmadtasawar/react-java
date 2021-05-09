import axios from "axios";

const axiosInstance =  (history: any = null) => {
  const API_URL = "http://localhost:8181/api";

  let headers = {};

  if (localStorage.bruker) {
    headers = { 
      'Content-Type': 'application/json', 
      Authorization: `Bearer ${localStorage.token}` };
  }

  const axiosInstance = axios.create({
    baseURL: API_URL,
    headers,
  });

  axiosInstance.interceptors.response.use(
    (response) =>
      new Promise((resolve, reject) => {
        resolve(response);
      }),
    (error) => {
      history.push('/logginn')
      return Promise.reject(error);
    }
  );

  return axiosInstance;
}

export default axiosInstance
