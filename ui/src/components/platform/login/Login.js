import React from 'react';
import { connect } from 'react-redux';
import ReactModal from 'react-modal';
import Logo from './images/Logo.png';
import styled from 'styled-components';
import ReactModalAdapter from '../widgets/StyledModal';
import { login } from './Action';
import Button from '../widgets/Button';

const StyledModal = styled(ReactModalAdapter).attrs({
  overlayClassName: 'Overlay',
  modalClassName: 'Modal'
})`
  /* Portal styles here (though usually you will have none) */

  .Overlay {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: transparent;
  }

  .Modal {
    top: 50%;
    left: 50%;
    right: auto;
    bottom: auto;
    margin-right: -50%;
    transform: translate(-50%, -50%);

    position: absolute;
    overflow: hidden;

    outline: none;
    background-color: transparent;
    border: 0px;
    color: white;
    text-align: center;
    font-size: 17px;
    text-rendering: optimizeLegibility;
    animation: fade-in 500ms ease-in-out;
    font-weight: normal;
    font-family: 'SF Pro Text', 'SF Pro Icons', 'Helvetica Neue', 'Helvetica',
      'Arial', sans-serif;
  }
`;

const LoginInput = styled.input`
  display: inline-block;
  height: 42px;
  width: 80%;
  box-sizing: border-box;
  border: 0;
  border-radius: 6px;
  margin-bottom: 5px;
  padding-left: 15px;
  font-size: 17px;
  line-height: 1.29;
  font-weight: normal;
  font-family: -apple-system, BlinkMacSystemFont, system-ui;
  vertical-align: top;
  text-align: left;
`;

const LoginButton = styled(Button)`
  display: block;
  margin: 0 auto;
`;

class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      hostname: window.location.hostname,
      username: '',
      password: ''
    };
    this.OnHostnameChange = this.OnHostnameChange.bind(this);
    this.OnUsernameChange = this.OnUsernameChange.bind(this);
    this.OnPasswordChange = this.OnPasswordChange.bind(this);
    this.OnSubmit = this.OnSubmit.bind(this);
  }

  OnHostnameChange(e) {
    e.preventDefault();
    this.setState({ hostname: e.target.value });
  }

  OnUsernameChange(e) {
    e.preventDefault();
    this.setState({ username: e.target.value });
  }

  OnPasswordChange(e) {
    e.preventDefault();
    this.setState({ password: e.target.value });
  }

  OnSubmit(e) {
    e.preventDefault();
    // If we can't find a next path after login, we go to root.
    const from = this.props.location.state
      ? this.props.location.state.from
      : '/';
    this.props.dispatch(
      login(this.state.hostname, this.state.username, this.state.password, from)
    );
  }

  render() {
    ReactModal.setAppElement('#root');
    console.info(this.props.theme);
    return (
      <StyledModal
        isOpen={true}
        shouldReturnFocusAfterClose={false}
        shouldCloseOnEsc={false}
        contentLabel="Login Dialog"
      >
        <img src={Logo} alt="Logo" style={{ width: '100px' }} />
        <h1>Sign in to Promise</h1>
        <LoginInput />
        <LoginInput type="password" />
        <LoginButton value="Login" onClick={this.OnSubmit}>
          Login
        </LoginButton>
      </StyledModal>
    );
  }
}

function mapStateToProps(state) {
  const { session } = state;
  return { session };
}

export default connect(mapStateToProps)(Login);
