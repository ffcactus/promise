import React from 'react';
import { connect } from 'react-redux';
import ReactModal from 'react-modal';
import styled from 'styled-components';
import {
  StyledModal,
  DialogHeaderDiv,
  DialogContentDiv,
  DialogControlDiv
} from '../widgets/Dialog';
import Button from '../widgets/Button';
import Input from '../widgets/Input';
import { register } from './Action';
import { RegisterState } from './ConstValue';

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
    this.OnUsernameChange = this.OnUsernameChange.bind(this);
    this.OnPasswordChange = this.OnPasswordChange.bind(this);
    this.OnPasswordConfirmChange = this.OnPasswordConfirmChange.bind(this);
    this.OnEmailChange = this.OnEmailChange.bind(this);
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

  OnPasswordConfirmChange(e) {
    e.preventDefault();
    this.setState({ passwordConfirm: e.target.value });
  }

  OnEmailChange(e) {
    e.preventDefault();
    this.setState({ email: e.target.value });
  }

  OnSubmit(e) {
    e.preventDefault();
    this.props.dispatch(
      register({
        username: this.state.username,
        password: this.state.password,
        email: this.state.email
      })
    );
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
        <form>
          <DialogHeaderDiv>Register to Promise</DialogHeaderDiv>
          <StyledDialogContentDiv>
            <section>
              <p>Login info</p>
              <label htmlFor="username">Username</label>
              <Input id="username" required onChange={this.OnUsernameChange} />
              <label htmlFor="password">Password</label>
              <Input
                id="password"
                required
                type="password"
                onChange={this.OnPasswordChange}
              />
              <label htmlFor="confirm-password">Confirm password</label>
              <Input
                id="confirm-password"
                required
                type="password"
                onChange={this.OnPasswordConfirmChange}
              />
            </section>
            <section>
              <p>Additional information for account recovery</p>
              <label htmlFor="email">Email</label>
              <Input
                id="email"
                required
                type="email"
                onChange={this.OnEmailChange}
              />
            </section>
            <DialogControlDiv>
              <section>
                <Button>Cancel</Button>
                <Button
                  type="submit"
                  primary
                  disabled={
                    this.props.register.state === RegisterState.REGISTERING
                      ? true
                      : false
                  }
                  onClick={this.OnSubmit}
                >
                  SUBMIT
                </Button>
              </section>
            </DialogControlDiv>
          </StyledDialogContentDiv>
        </form>
      </StyledModal>
    );
  }
}

function mapStateToProps(state) {
  const { register } = state;
  return { register };
}

export default connect(mapStateToProps)(RegisterDialog);
