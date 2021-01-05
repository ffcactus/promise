import React from 'react';
import {connect} from 'react-redux';
import ReactModal from 'react-modal';
import LogoIcon from './images/Logo.png';
import LoginIcon from './images/Login.png';
import LoadingIcon from '../../../images/Loading.gif';
import styled from 'styled-components';
import {Link} from 'react-router-dom';
import ReactModalAdapter from '../widgets/StyledModal';
import {login} from './Action';
import {LoginState} from './ConstValue';

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

/**
 * MessageArea keep a certain height so the page's element won't move with or without message.
 */
const MessageArea = styled.div`
  color: red;
  height: 20px;
  text-align: center;
`;

const UsernameInput = styled(LoginInput)`
  border-top-left-radius: 6px;
  border-top-right-radius: 6px;
`;

const PasswordInput = styled(LoginInput)`
  border-bottom-left-radius: 6px;
  border-bottom-right-radius: 6px;
  background-image: url(${LoginIcon});
  background-image: url(${p => (p.loading ? LoadingIcon : LoginIcon)});
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
        this.onUsernameChange = this.onUsernameChange.bind(this);
        this.onPasswordChange = this.onPasswordChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
        this.onInvalid = this.onInvalid.bind(this);
    }

    onUsernameChange(e) {
        e.preventDefault();
        this.setState({username: e.target.value});
    }

    onPasswordChange(e) {
        e.preventDefault();
        this.setState({password: e.target.value});
    }

    onInvalid(e) {
        if (!e.target.validity.valid) {
            e.target.setCustomValidity(
                'Usernames can only contain upper lowercase letters and numbers.'
            );
        } else {
            e.target.setCustomValidity('');
        }
    }

    onSubmit(e) {
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
                <img src={LogoIcon} alt="Logo" style={{width: '100px'}}/>
                <Title>Sign in to Promise</Title>
                <form
                    id="login-form"
                    onSubmit={this.onSubmit}
                    onInvalid={this.onInvalid}
                >
                    <fieldset style={{border: '0px'}}>
                        <UsernameInput
                            id="username"
                            placeholder="username"
                            required
                            pattern="[A-Za-z0-9]+"
                            onChange={this.onUsernameChange}
                            value={this.state.username}
                        />
                        <PasswordInput
                            form="login-form"
                            id="password"
                            placeholder="password"
                            type="password"
                            required
                            onChange={this.onPasswordChange}
                            loading={this.props.session.state === LoginState.LOGGING}
                            value={this.state.password}
                        />
                    </fieldset>
                    <button
                        type="submit"
                        form="login-form"
                        style={{visibility: 'hidden'}}
                    />
                </form>
                <MessageArea>{this.props.session.message}</MessageArea>
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

function mapStateToProps(state) {
    const {session} = state;
    return {session};
}

export default connect(mapStateToProps)(Login);
