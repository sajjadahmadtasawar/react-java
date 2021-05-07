import React from 'react';
import ReactDOM from 'react-dom';
import { Router } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import App from './App';
import { history } from "helpers/history";

require('dotenv').config()

ReactDOM.render(
  <Router history={history}>
    <ToastContainer autoClose={2000} />
    <App />
  </Router>,
  document.getElementById('root')
);
