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

export default class SettingsApp extends React.Component {
  render() {
    return (
      <Background>
        <div>
          <nav>
            <Link to="/">Home</Link>
          </nav>
        </div>
      </Background>
    );
  }
}
