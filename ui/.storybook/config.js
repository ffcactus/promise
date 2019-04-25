import React from "react";
import { ThemeProvider } from "styled-components";
import { configure, addDecorator } from "@storybook/react";
import { Provider } from "react-redux";
import { MemoryRouter } from "react-router-dom";
import configureStore from "../src/configureStore";
import defaultTheme from "../src/Theme";
const req = require.context("../src/stories", true, /\.stories\.js$/);

const store = configureStore();

addDecorator(story => (
  <Provider store={store}>
    <ThemeProvider theme={defaultTheme}>
      <MemoryRouter initialEntries={["/"]}>{story()}</MemoryRouter>
    </ThemeProvider>
  </Provider>
));

function loadStories() {
  req.keys().forEach(filename => req(filename));
}

configure(loadStories, module);
