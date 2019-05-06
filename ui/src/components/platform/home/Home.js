import React from 'react';
import styled from 'styled-components';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import Wallpaper from './Wallpaper';

const EmptyIconDiv = styled.div`
  height: 0px;
  width: 160px;
`;

class AppCollection extends React.Component {
  constructor(props) {
    super(props);
    this.unknownWidthDiv = React.createRef();
    this.state = {
      needEmptyIconDiv: false
    };
  }

  componentDidMount() {
    const totalWidth = this.unknownWidthDiv.current.offsetWidth;
    if (totalWidth < 160 * this.props.children.length) {
      this.setState({ needEmptyIconDiv: true });
    }
  }

  render() {
    if (this.state.needEmptyIconDiv) {
      return (
        <div ref={this.unknownWidthDiv} className={this.props.className}>
          {this.props.children}
          <EmptyIconDiv />
          <EmptyIconDiv />
          <EmptyIconDiv />
          <EmptyIconDiv />
          <EmptyIconDiv />
          <EmptyIconDiv />
          <EmptyIconDiv />
          <EmptyIconDiv />
          <EmptyIconDiv />
          <EmptyIconDiv />
        </div>
      );
    } else {
      return (
        <div ref={this.unknownWidthDiv} className={this.props.className}>
          {this.props.children}
        </div>
      );
    }
  }
}

/**
 * Represents the area that shows all the application icons.
 */
const StyledAppCollection = styled(AppCollection)`
  position: absolute;
  top: 50%;
  left: 50%;
  right: auto;
  bottom: auto;
  margin-right: -50%;
  transform: translate(-50%, -50%);
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-items: center;
  justify-content: space-between;
  max-height: 80%;
  max-width: 80%;
`;

/**
 * Represents the App icon and name.
 */
class AppComponent extends React.Component {
  render() {
    return (
      <div className={this.props.className}>
        <div id="app-icon">
          <Link to={this.props.uri}>
            <img src={this.props.img} alt={this.props.appName} />
          </Link>
        </div>
        <div id="app-name">
          <span>{this.props.appName}</span>
        </div>
      </div>
    );
  }
}

AppComponent.propTypes = {
  uri: PropTypes.string,
  img: PropTypes.string,
  appName: PropTypes.string
};

const StyledAppComponent = styled(AppComponent)`
  height: 180px;
  width: 160px;
  display: flex;
  flex-direction: column;
  align-items: center;
  align-self: center;
  color: white;

  & img {
    width: 120px;
  }
`;

const contactsIcon = require('./images/contacts_icon@2x.png');
const dateIcon = require('./images/date_icon@2x.png');
const findIcon = require('./images/find_icon@2x.png');
const friendsIcon = require('./images/fmf_icon@2x.png');
const driveIcon = require('./images/icloud_drive_icon@2x.png');
const keynoteIcon = require('./images/keynote_icon@2x.png');
const mailIcon = require('./images/mail_icon@2x.png');
const notesIcon = require('./images/notes_icon@2x.png');
const numbersIcon = require('./images/numbers_icon@2x.png');
const pagesIcon = require('./images/pages_icon@2x.png');
const photosIcon = require('./images/photos_icon@2x.png');
const remindersIcon = require('./images/reminders_icon@2x.png');
const settingsIcon = require('./images/settings_icon@2x.png');

/**
 * Represents the whole home area.
 */
export default class Home extends React.Component {
  render() {
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
  }
}

export { StyledAppCollection, StyledAppComponent };
