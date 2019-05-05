import React from 'react';
import { connect } from 'react-redux';
import ReactModal from 'react-modal';
import styled from 'styled-components';
import {
  StyledModal,
  DialogHeaderDiv,
  DialogContentDiv,
  DialogControlDiv,
  DialogMessageDiv,
  DialogInputDiv
} from '../widgets/Dialog';
import Button from '../widgets/Button';
import Input from '../widgets/Input';
import { register, onCancel } from './Action';

const StyledDialogContentDiv = styled(DialogContentDiv)`
  display: block;
  section {
    margin: 10px;
  }

  section > p {
    font-size: 18px;
    font-weight: bold;
    margin: ${p => p.theme.boxRadius}px 0px;
  }
`;

class RegisterDialog extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
      passwordConfirm: '',
      email: ''
    };
    this.onUsernameChange = this.onUsernameChange.bind(this);
    this.onPasswordChange = this.onPasswordChange.bind(this);
    this.onPasswordConfirmChange = this.onPasswordConfirmChange.bind(this);
    this.onEmailChange = this.onEmailChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
    this.onCancel = this.onCancel.bind(this);
  }

  onUsernameChange(e) {
    e.preventDefault();
    this.setState({ username: e.target.value });
  }

  onPasswordChange(e) {
    e.preventDefault();
    this.setState({ password: e.target.value });
  }

  onPasswordConfirmChange(e) {
    e.preventDefault();
    this.setState({ passwordConfirm: e.target.value });
  }

  onEmailChange(e) {
    e.preventDefault();
    this.setState({ email: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();
    this.props.dispatch(
      register({
        username: this.state.username,
        password: this.state.password,
        email: this.state.email
      })
    );
  }

  onCancel(e) {
    e.preventDefault();
    // If we can't find a next path after login, we go to login.
    const from = this.props.location.state
      ? this.props.location.state.from
      : '/login';
    this.props.dispatch(onCancel(from));
  }

  render() {
    ReactModal.setAppElement('#root');
    return (
      <StyledModal
        isOpen={true}
        shouldReturnFocusAfterClose={false}
        shouldCloseOnEsc={false}
        contentLabel="Register to Promise"
      >
        <DialogHeaderDiv>Register to Promise</DialogHeaderDiv>
        <StyledDialogContentDiv>
          <DialogInputDiv>
            <section>
              <p>Login info</p>
              <label htmlFor="username">Username</label>
              <Input id="username" required onChange={this.onUsernameChange} />
              <label htmlFor="password">Password</label>
              <Input
                id="password"
                required
                type="password"
                onChange={this.onPasswordChange}
              />
              <label htmlFor="confirm-password">Confirm password</label>
              <Input
                id="confirm-password"
                required
                type="password"
                onChange={this.onPasswordConfirmChange}
              />
            </section>
            <section>
              <p>Additional information for account recovery</p>
              <label htmlFor="email">Email</label>
              <Input
                id="email"
                required
                type="email"
                onChange={this.onEmailChange}
              />
            </section>
          </DialogInputDiv>
          <DialogMessageDiv>
            <p>{this.props.register.message}</p>
          </DialogMessageDiv>
        </StyledDialogContentDiv>
        <DialogControlDiv>
          <section>
            <Button onClick={this.onCancel}>Cancel</Button>
            <Button
              type="submit"
              primary
              disabled={this.props.register.sendingRequest}
              onClick={this.onSubmit}
            >
              SUBMIT
            </Button>
          </section>
        </DialogControlDiv>
      </StyledModal>
    );
  }
}

function mapStateToProps(state) {
  const { register } = state;
  return { register };
}

export default connect(mapStateToProps)(RegisterDialog);
