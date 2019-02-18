import React from 'react';
import { storiesOf } from '@storybook/react';
import Button from '../../../components/platform/widgets/Button';

storiesOf('Platform/widgets/Button', module)
  .add('default', () => {
    return <Button>Button</Button>;
  })
  .add('primary', () => {
    return <Button primary>Button</Button>;
  });
