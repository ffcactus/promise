import React from 'react';
import { storiesOf } from '@storybook/react';
import StyledDesktop from '../../../components/platform/desktop/StyledDesktop';

storiesOf('Platform/Desktop', module)
  .add('default', () => {
    return <StyledDesktop />;
  })
  .add('empty', () => <StyledDesktop />);
