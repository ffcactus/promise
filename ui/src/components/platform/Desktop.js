import React from 'react';

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
        <p>Desktop</p>
      </div>
    );
  }
}

export default Desktop;
