import React, { Component } from 'react';
import Loadable from 'react-loadable';
import './App.css';

const LoadableApps = Loadable.Map({
  loader: {
    App1: () => import('./components/App1'),
    App2: () => import('./components/App2'),
    App3: () => import('./components/App3'),
    App4: () => import('./components/App4'),
    App5: () => import('./components/App5')
  },
  loading() {
    return <div>Loading...</div>;
  },
  render(loaded, props) {
    let App1 = loaded.App1.default;
    let App2 = loaded.App2.default;
    let App3 = loaded.App3.default;
    let App4 = loaded.App4.default;
    let App5 = loaded.App5.default;
    return (
      <div>
        <App1 />
        <App2 />
        <App3 />
        <App4 />
        <App5 />
      </div>
    );
  }
});

class App extends Component {
  render() {
    return (
      <div className="App">
        <p>React loadable</p>
        <LoadableApps />
      </div>
    );
  }
}

export default App;
