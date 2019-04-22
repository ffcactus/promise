import React from 'react';
import { storiesOf } from '@storybook/react';
import { Provider } from 'react-redux';
import { Link, MemoryRouter } from 'react-router-dom';
import Home from '../../../components/platform/home/Home';

storiesOf('Platform/Desktop', module)
  .addDecorator(story => (
    <MemoryRouter initialEntries={['/']}>{story()}</MemoryRouter>
  ))
  .add('default', () => {
    return <Home />;
  });
