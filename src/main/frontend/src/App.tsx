import { FC } from 'react';
import { Switch, Route } from 'react-router-dom';
import { QueryClientProvider, QueryClient } from 'react-query';
import { ReactQueryDevtools } from 'react-query/devtools';

import 'styles/styles.css';
import 'styles/App.css';

import Sidebar from 'components/Layout/Sidebar';
import Dashbaord from 'components/Layout/Dashboard';
import NavbarLayout from 'components/Layout/NavbarLayout';
import Liste from 'components/Prosjekt/views/Liste';
import Opprett from 'components/Prosjekt/views/Opprett';
import Endre from 'components/Prosjekt/views/Endre';
import SkjemaListe from 'components/Skjema/views/Skjema.liste';
import SkjemaOpprett from 'components/Skjema/views/Skjema.Opprett';
import SkjemaEndre from 'components/Skjema/views/Skjema.Endre';
import Logginn from 'components/auth/Logginn';
import ProsjektLederListe from 'components/ProsjektLeder/views/Liste';
import IntervjuObjektListe from 'components/IntervjueObjekt/views/Liste';

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
                  <Route exact path="/prosjekter" component={Liste} />
                  <Route exact path="/prosjekter/opprett" component={Opprett} />
                  <Route exact path="/prosjekter/endre/:id" component={Endre} />
                  <Route exact path="/skjemaer" component={SkjemaListe} />
                  <Route
                    exact
                    path="/prosjektLeder"
                    component={ProsjektLederListe}
                  />
                  <Route
                    exact
                    path="/skjemaer/opprett"
                    component={SkjemaOpprett}
                  />
                  <Route
                    exact
                    path="/skjemaer/endre/:id"
                    component={SkjemaEndre}
                  />
                  <Route exact path="/utvalg" component={SkjemaListe} />
                  <Route exact path="/io_sok" component={IntervjuObjektListe} />
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
