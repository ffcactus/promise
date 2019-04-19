import React from 'react';
import PropTypes from 'prop-types';
import { Route, Switch } from 'react-router'; // react-router v4/v5
import { ConnectedRouter } from 'connected-react-router';
import { ThemeProvider } from 'styled-components';
import Home from './components/platform/home/Home';
import LoginDialog from './components/platform/login/LoginDialog';
import Server from './components/app/server/Server';
import Wallpaper from './components/platform/home/Wallpaper';

const theme = {
  backgroundColor: 'red',
  color: 'green'
};

const App = ({ history }) => {
  return (
    <ThemeProvider theme={theme}>
      <Wallpaper>
        <ConnectedRouter history={history}>
          <Switch>
            <Route exact path="/" render={() => <Home />} />
            <Route path="/login" render={() => <LoginDialog />} />
            <Route path="/server" render={() => <Server />} />
            <Route render={() => <div>Miss</div>} />
          </Switch>
        </ConnectedRouter>
      </Wallpaper>
    </ThemeProvider>
  );
};

App.propTypes = {
  history: PropTypes.object
};

export default App;
