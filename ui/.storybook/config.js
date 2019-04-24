import React from "react";
import { configure, addDecorator } from "@storybook/react";
import { Provider } from "react-redux";
import { MemoryRouter } from "react-router-dom";
import configureStore from "../src/configureStore";
const req = require.context("../src/stories", true, /\.stories\.js$/);

const store = configureStore();

addDecorator(story => (
  <Provider store={store}>
    <MemoryRouter initialEntries={["/"]}>{story()}</MemoryRouter>
  </Provider>
));

function loadStories() {
  req.keys().forEach(filename => req(filename));
}

configure(loadStories, module);
