import React from 'react';
import ReactModal from 'react-modal';
import Logo from '../platform/images/Logo.png';

const loginStyle = {
  content: {
    top: '50%',
    left: '50%',
    right: 'auto',
    bottom: 'auto',
    marginRight: '-50%',
    transform: 'translate(-50%, -50%)',
    backgroundColor: null,
    border: 0,
    color: 'white',
    textAlign: 'center',
    fontSize: '17px',
    textRendering: 'optimizeLegibility',
    animation: 'fade-in 500ms ease-in-out',
    fontWeight: '400',
    lineWeight: '1.47',
    fontFamily: `"SF Pro Text","SF Pro Icons","Helvetica Neue","Helvetica","Arial",sans-serif`
  },
  overlay: {
    backgroundColor: null
  }
};

class LoginDialog extends React.Component {
  render() {
    ReactModal.setAppElement('#root');
    return (
      <ReactModal
        style={loginStyle}
        isOpen={true}
        shouldReturnFocusAfterClose={false}
        shouldCloseOnEsc={false}
        contentLabel="Login Dialog"
      >
        <img src={Logo} alt="Logo" style={{ width: '100px' }} />
        <h1>Sign in to Promise</h1>
        <input type="text" />
      </ReactModal>
    );
  }
}

export default LoginDialog;
