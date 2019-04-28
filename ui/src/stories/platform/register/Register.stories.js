import React from 'react';
import { storiesOf } from '@storybook/react';
import Wallpaper from '../../../components/platform/home/Wallpaper';
import RegisterDialog from '../../../components/platform/register/RegisterDialog';
import RegisterSuccessDialog from '../../../components/platform/register/RegisterSuccessDialog';

storiesOf('Platform/Register', module)
  .add('RegisterDialog', () => {
    return (
      <Wallpaper>
        <RegisterDialog />
      </Wallpaper>
    );
  })
  .add('ReigsterSuccessDialog', () => {
    return (
      <Wallpaper>
        <RegisterSuccessDialog />
      </Wallpaper>
    );
  });
