import React from 'react';
import LoginDialog from './LoginDialog';

class Desktop extends React.Component {
  render() {
    return (
      <div
        style={{
          ...this.props.style,
          position: 'absolute',
          top: '0px',
          right: '0px',
          bottom: '0px',
          left: '0px'
        }}
      >
        <LoginDialog />
      </div>
    );
  }
}

export default Desktop;
