import axios from "axios";

export default (history: any = null) => {
  const API_URL = "http://localhost:8181/api";

  let headers = {};

  if (localStorage.user) {
    headers = { 'Content-Type': 'application/json', Authorization: `Bearer ${localStorage.user.accessToken}` };
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
      history.push('/login')
      return Promise.reject(error);
    }
  );

  return axiosInstance;
};
