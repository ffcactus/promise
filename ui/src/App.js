import React from 'react';
import PropTypes from 'prop-types';
import { Route, Switch } from 'react-router'; // react-router v4/v5
import { ConnectedRouter } from 'connected-react-router';
import { ThemeProvider } from 'styled-components';
import Home from './components/platform/home/Home';
import Login from './components/platform/login/Login';
import Server from './components/app/server/Server';
import Wallpaper from './components/platform/home/Wallpaper';
import PrivateRoute from './components/platform/widgets/PrivateRoute';
import defaultTheme from './Theme';
import Register from './components/platform/register/Register';

const App = ({ history }) => {
  return (
    <ThemeProvider theme={defaultTheme}>
      <Wallpaper>
        <ConnectedRouter history={history}>
          <Switch>
            <PrivateRoute exact path="/" component={Home} />
            <Route path="/login" component={Login} />
            <Route path="/register" component={Register} />
            <PrivateRoute path="/server" component={Server} />
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
