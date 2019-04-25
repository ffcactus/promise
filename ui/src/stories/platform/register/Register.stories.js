import React from 'react';
import { storiesOf } from '@storybook/react';
import Wallpaper from '../../../components/platform/home/Wallpaper';
import Register from '../../../components/platform/register/Register';

storiesOf('Platform/Register', module).add('default', () => {
  return (
    <Wallpaper>
      <Register />
    </Wallpaper>
  );
});
