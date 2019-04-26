import React from 'react';
import { connect } from 'react-redux';
import ReactModal from 'react-modal';
import styled from 'styled-components';
import {
  StyledModal,
  DialogHeaderDiv,
  DialogContentDiv
} from '../widgets/Dialog';
import Button from '../widgets/Button';
import Input from '../widgets/Input';

const TextInput = styled.input`
  display: block;
  height: 36px;
  width: 18em;
  box-sizing: border-box;
  border: 0;
  border-radius: 6px;
  margin-bottom: 1px;
  padding-left: 15px;
  font-size: 17px;
  line-height: 1.29;
  font-weight: normal;
  font-family: -apple-system, BlinkMacSystemFont, system-ui;
  /* vertical-align: top; */
  text-align: left;
`;

const LabelInput = props => {
  return (
    <div>
      <label>
        {props.label}
        <input {...props} />
      </label>
    </div>
  );
};

const StyledDialogContentDiv = styled(DialogContentDiv)`
  display: block;
  section {
    margin: 10px;
  }

  section > p {
    font-size: 18px;
    font-weight: bold;
  }
`;

class Register extends React.Component {
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
        <DialogHeaderDiv>Register to Promise</DialogHeaderDiv>
        <StyledDialogContentDiv>
          <form>
            <section>
              <p>Login info</p>

              <label for="username">Username</label>
              <Input id="username" required />

              <label for="password">Password</label>
              <Input id="username" required type="password" />

              <label for="confirm-password">Confirm password</label>
              <Input id="confirm-password" required type="password" />
            </section>
            <section>
              <p>Additional information for account recovery</p>
              <label for="email">Email</label>
              <Input id="email" required type="email" />
            </section>
            <section>
              <Button>Cancel</Button>
              <Button>Submit</Button>
            </section>
          </form>
        </StyledDialogContentDiv>
      </StyledModal>
    );
  }
}

export default connect()(Register);
