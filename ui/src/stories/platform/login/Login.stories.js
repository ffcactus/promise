import React from 'react';
import { storiesOf } from '@storybook/react';
import { Provider } from 'react-redux';
import { Login } from '../../../components/platform/login/Login';
import Wallpaper from '../../../components/platform/home/Wallpaper';

storiesOf('Platform/Login', module).add('default', () => {
  return (
    <Provider>
      <Wallpaper>
        <Login />
      </Wallpaper>
    </Provider>
  );
});
