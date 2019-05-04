import React from 'react';
import { storiesOf } from '@storybook/react';
import Wallpaper from '../../../components/platform/home/Wallpaper';
import {
  StyledAppCollection,
  StyledAppComponent
} from '../../../components/platform/home/Home';

const contactsIcon = require('../../../components/platform/home/images/contacts_icon@2x.png');
const dateIcon = require('../../../components/platform/home/images/date_icon@2x.png');
const findIcon = require('../../../components/platform/home/images/find_icon@2x.png');
const friendsIcon = require('../../../components/platform/home/images/fmf_icon@2x.png');
const driveIcon = require('../../../components/platform/home/images/icloud_drive_icon@2x.png');
const keynoteIcon = require('../../../components/platform/home/images/keynote_icon@2x.png');
const mailIcon = require('../../../components/platform/home/images/mail_icon@2x.png');
const notesIcon = require('../../../components/platform/home/images/notes_icon@2x.png');
const numbersIcon = require('../../../components/platform/home/images/numbers_icon@2x.png');
const pagesIcon = require('../../../components/platform/home/images/pages_icon@2x.png');
const photosIcon = require('../../../components/platform/home/images/photos_icon@2x.png');
const remindersIcon = require('../../../components/platform/home/images/reminders_icon@2x.png');
const settingsIcon = require('../../../components/platform/home/images/settings_icon@2x.png');

storiesOf('Platform/Desktop', module)
  .add('icons less then a line', () => {
    return (
      <Wallpaper>
        <StyledAppCollection>
          <StyledAppComponent uri="/event" img={dateIcon} appName="Data" />
          <StyledAppComponent uri="/event" img={findIcon} appName="Find" />
          <StyledAppComponent uri="/event" img={driveIcon} appName="Drive" />
          <StyledAppComponent uri="/event" img={mailIcon} appName="Mail" />
          <StyledAppComponent uri="/event" img={notesIcon} appName="Notes" />
        </StyledAppCollection>
      </Wallpaper>
    );
  })
  .add('icons more then a line', () => {
    return (
      <Wallpaper>
        <StyledAppCollection>
          <StyledAppComponent
            uri="/event"
            img={contactsIcon}
            appName="Contacts"
          />
          <StyledAppComponent uri="/event" img={dateIcon} appName="Data" />
          <StyledAppComponent uri="/event" img={findIcon} appName="Find" />
          <StyledAppComponent
            uri="/event"
            img={friendsIcon}
            appName="Friends"
          />
          <StyledAppComponent uri="/event" img={driveIcon} appName="Drive" />
          <StyledAppComponent
            uri="/event"
            img={keynoteIcon}
            appName="Keynote"
          />
          <StyledAppComponent uri="/event" img={mailIcon} appName="Mail" />
          <StyledAppComponent uri="/event" img={notesIcon} appName="Notes" />
          <StyledAppComponent
            uri="/event"
            img={numbersIcon}
            appName="Numbers"
          />
          <StyledAppComponent uri="/event" img={pagesIcon} appName="Pages" />
          <StyledAppComponent uri="/event" img={photosIcon} appName="Photos" />
          <StyledAppComponent
            uri="/event"
            img={remindersIcon}
            appName="Reminders"
          />
          <StyledAppComponent
            uri="/event"
            img={settingsIcon}
            appName="Settings"
          />
        </StyledAppCollection>
      </Wallpaper>
    );
  });
