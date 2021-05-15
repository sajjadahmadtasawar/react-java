import { FC } from "react";
import { Switch, Route } from "react-router-dom";
import { QueryClientProvider, QueryClient } from "react-query";
import { ReactQueryDevtools } from "react-query/devtools";

import "styles/styles.css";
import "styles/App.css";

import Sidebar from "components/Layout/Sidebar";
import Dashbaord from "components/Layout/Dashboard";
import NavbarLayout from "components/Layout/NavbarLayout";
import Logginn from "components/Auth/Logginn";
import ProsjektListe from "components/Prosjekt/Prosjekt.liste";
import ProsjektOpprett from "components/Prosjekt/Prosjekt.Opprett";
import ProsjektEndre from "components/Prosjekt/Prosjekt.Endre";

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
                  <Route exact path="/logginn" component={Logginn} />
                  <Route exact path="/" component={Dashbaord} />
                  <Route exact path="/prosjekter" component={ProsjektListe} />
                  <Route
                    exact
                    path="/prosjekter/opprett"
                    component={ProsjektOpprett}
                  />
                  <Route
                    exact
                    path="/prosjekter/endre/:id"
                    component={ProsjektEndre}
                  />
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
