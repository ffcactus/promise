import React from 'react';
import { storiesOf } from '@storybook/react';
import Input from '../../../components/platform/widgets/Input';

storiesOf('Platform/widgets/Input', module)
  .add('size 1em', () => {
    return <Input placeholder="size 1em" size="1em" />;
  })
  .add('size 2em', () => {
    return <Input placeholder="size 2em" size="2em" />;
  });
