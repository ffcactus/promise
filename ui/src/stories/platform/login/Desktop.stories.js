import React from 'react';
import { storiesOf } from '@storybook/react';
import Login from '../../../components/platform/login/Login';

storiesOf('Platform/Login', module).add('default', () => {
  return <Login />;
});
