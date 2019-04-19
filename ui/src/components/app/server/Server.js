import React from 'react';
import styled from 'styled-components';

const Box = styled.div`
  color: ${props => props.theme.color};
  background-color: ${props => props.theme.backgroundColor};
`;

export default class Server extends React.Component {
  render() {
    return <Box>Hello Theme!</Box>;
  }
}
