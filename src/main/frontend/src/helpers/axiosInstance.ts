import axios from 'axios';

const axiosInstance = (history: any = null) => {
  const API_URL = 'http://localhost:3000';

  let headers = {};

  if (localStorage.bruker) {
    headers = {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${localStorage.token}`,
    };
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
      if (error.response.status === 401) {
        window.location.href = 'logginn';
      }

      return Promise.reject(error.response.data);
    }
  );

  return axiosInstance;
};

export default axiosInstance;
