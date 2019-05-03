import React from 'react';
import { storiesOf } from '@storybook/react';
import Home from '../../../components/platform/home/Home';
import Wallpaper from '../../../components/platform/home/Wallpaper';
import {
  AppCollectionDiv,
  IconDiv
} from '../../../components/platform/home/Home';

storiesOf('Platform/Desktop', module)
  .add('icons less then a line', () => {
    return (
      <Wallpaper>
        <AppCollectionDiv>
          <IconDiv>App1</IconDiv>
          <IconDiv>App2</IconDiv>
          <IconDiv>App3</IconDiv>
        </AppCollectionDiv>
      </Wallpaper>
    );
  })
  .add('icons more then a line', () => {
    return (
      <Wallpaper>
        <AppCollectionDiv>
          <IconDiv>App1</IconDiv>
          <IconDiv>App2</IconDiv>
          <IconDiv>App3</IconDiv>
          <IconDiv>App4</IconDiv>
          <IconDiv>App5</IconDiv>
          <IconDiv>App6</IconDiv>
          <IconDiv>App7</IconDiv>
          <IconDiv>App8</IconDiv>
          <IconDiv>App9</IconDiv>
          <IconDiv>App10</IconDiv>
          <IconDiv>App11</IconDiv>
          <IconDiv>App12</IconDiv>
        </AppCollectionDiv>
      </Wallpaper>
    );
  });
