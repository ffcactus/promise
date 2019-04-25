import React from 'react';
import { connect } from 'react-redux';
import ReactModal from 'react-modal';
import LogoIcon from './images/Logo.png';
import LoginIcon from './images/Login.png';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import ReactModalAdapter from '../widgets/StyledModal';
import { login } from './Action';

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

const Title = styled.h1`
  font-size: 21px;
`;

const LoginInput = styled.input`
  display: block;
  height: 36px;
  width: 18em;
  box-sizing: border-box;
  border: 0;
  margin-bottom: 1px;
  padding-left: 15px;
  font-size: 17px;
  line-height: 1.29;
  font-weight: normal;
  font-family: -apple-system, BlinkMacSystemFont, system-ui;
  /* vertical-align: top; */
  text-align: left;
`;

const UsernameInput = styled(LoginInput)`
  border-top-left-radius: 6px;
  border-top-right-radius: 6px;
`;

const PasswordInput = styled(LoginInput)`
  border-bottom-left-radius: 6px;
  border-bottom-right-radius: 6px;
  background-image: url(${LoginIcon});
  background-repeat: no-repeat;
  background-position: 98% 50%;
  background-size: 30px;
  padding-right: 40px; /* so that the input won't overlay the login icon. */
`;

const HelpArea = styled.div`
  margin-top: 20px;
  display: flex;
  flex-direction: row;
`;

const ForgotPasswordDiv = styled.div`
  width: 50%;
`;

const RegisterDiv = styled.div`
  width: 50%;
`;

const ForgotPasswordLink = styled(Link)`
  color: white;
  margin-right: 10px;
  float: right;
`;

const RegisterLink = styled(Link)`
  color: white;
  margin-left: 10px;
  float: left;
`;

class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      hostname: window.location.hostname,
      username: '',
      password: '',
      port: 3000
    };
    this.OnUsernameChange = this.OnUsernameChange.bind(this);
    this.OnPasswordChange = this.OnPasswordChange.bind(this);
    this.OnSubmit = this.OnSubmit.bind(this);
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
      login(
        this.state.hostname,
        this.state.port,
        this.state.username,
        this.state.password,
        from
      )
    );
  }

  render() {
    ReactModal.setAppElement('#root');
    return (
      <StyledModal
        isOpen={true}
        shouldReturnFocusAfterClose={false}
        shouldCloseOnEsc={false}
        contentLabel="Login Dialog"
      >
        <img src={LogoIcon} alt="Logo" style={{ width: '100px' }} />
        <Title>Sign in to Promise</Title>
        <form>
          <UsernameInput
            id="username"
            placeholder="username"
            onChange={this.OnUsernameChange}
          />
          <PasswordInput
            id="password"
            placeholder="password"
            type="password"
            onChange={this.OnPasswordChange}
            onSubmit={this.onSubmit}
          />
        </form>
        <HelpArea>
          <ForgotPasswordDiv>
            <ForgotPasswordLink to="/password-recovery">
              Forgot Password
            </ForgotPasswordLink>
          </ForgotPasswordDiv>
          <RegisterDiv>
            <RegisterLink to="/register">Register Account</RegisterLink>
          </RegisterDiv>
        </HelpArea>
      </StyledModal>
    );
  }
}

// function mapStateToProps(state) {
//   const { session } = state;
//   return { session };
// }

export default connect()(Login);
