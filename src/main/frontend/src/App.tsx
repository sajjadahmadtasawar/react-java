import React, { FC } from 'react';
import { Switch, Route, Link } from "react-router-dom";
import { QueryClientProvider, QueryClient } from "react-query";
import { ReactQueryDevtools } from "react-query/devtools";

import 'styles/styles.css';
import 'styles/App.css';

import Sidebar from 'components/Layout/Sidebar';
import Login from 'components/Auth/Login';
import Dashbaord from 'components/Layout/Dashboard';
import NavbarLayout from 'components/Layout/NavbarLayout';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
    },
  },
});


const App: FC = () => {
  return (
    <>
      <QueryClientProvider client={queryClient}>
        <NavbarLayout />
        <div id="layoutSidenav">
          <Sidebar />
          <div id="layoutSidenav_content">
            <main>
              <div className="container-fluid">
                <Switch>
                  <Route exact path="/login" component={Login} />
                  <Route exact path="/" component={Dashbaord} />
                </Switch>
              </div>
            </main>
          </div>
        </div>
        <ReactQueryDevtools initialIsOpen={false} />
      </QueryClientProvider>
    </>
  );
};

export default App;
