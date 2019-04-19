import React from 'react';
import { storiesOf } from '@storybook/react';
import Home from '../../../components/platform/home/Home';

storiesOf('Platform/Desktop', module)
  .add('default', () => {
    return <Home />;
  })
  .add('empty', () => <Home />);
