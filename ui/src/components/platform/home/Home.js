import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const Background = styled.div`
  position: absolute;
  top: 0px;
  right: 0px;
  bottom: 0px;
  left: 0px;
  background: linear-gradient(
    to bottom,
    rgba(7, 42, 78, 1) 0%,
    rgba(10, 48, 85, 1) 6%,
    rgba(19, 66, 106, 1) 15%,
    rgba(35, 94, 137, 1) 24%,
    rgba(60, 127, 166, 1) 33%,
    rgba(80, 146, 178, 1) 39%,
    rgba(100, 163, 184, 1) 44%,
    rgba(128, 180, 182, 1) 51%,
    rgba(151, 192, 177, 1) 56%,
    rgba(178, 201, 166, 1) 62%,
    rgba(194, 202, 155, 1) 66%,
    rgba(213, 199, 137, 1) 71%,
    rgba(228, 189, 118, 1) 76%,
    rgba(238, 168, 92, 1) 84%,
    rgba(240, 150, 75, 1) 92%,
    rgba(236, 126, 57, 1) 98%,
    rgba(235, 120, 53, 1) 100%
  );
`;

const EmptyIconDiv = styled.div`
  height: 0px;
  width: 160px;
`;

class AppCollectionProxy extends React.Component {
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
const AppCollectionDiv = styled(AppCollectionProxy)`
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

const IconDiv = styled.div`
  height: 180px;
  width: 160px;
  align-self: center;
`;

export default class Home extends React.Component {
  render() {
    return (
      <Background>
        <AppCollectionDiv>
          <IconDiv>
            <Link to="/event">Event</Link>
          </IconDiv>
          <IconDiv>
            <Link to="/settings">Settings</Link>
          </IconDiv>
          <IconDiv>
            <Link to="/logout">Logout</Link>
          </IconDiv>
        </AppCollectionDiv>
      </Background>
    );
  }
}

export { AppCollectionDiv, IconDiv };
