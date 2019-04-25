import React from 'react';
import { connect } from 'react-redux';
import ReactModal from 'react-modal';
import styled from 'styled-components';
import {
  StyledModal,
  DialogHeaderDiv,
  DialogContentDiv
} from '../widgets/Dialog';

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
        <DialogContentDiv>
          <form>
            <section>
              <span>Login info</span>

              <label for="username">Username</label>
              <input id="username" required />

              <label for="password">Password</label>
              <input id="username" required type="password" />

              <label for="confirm-password">Confirm password</label>
              <input id="confirm-password" required type="password" />
            </section>
            <secion>
              <span>Additional information for account recovery</span>
            </secion>
            <LabelInput label="Username" id="username" />
            <LabelInput label="Password" id="password" type="password" />
          </form>
        </DialogContentDiv>
        {/* <TitleArea>
          <span>Register in to Promise</span>
        </TitleArea>
        <form>
          <TextInput
            id="username"
            placeholder="username"
            onChange={this.OnUsernameChange}
          />
          <TextInput id="nickname" placeholder="nikename" />

          <TextInput
            id="password"
            placeholder="password"
            type="password"
            onChange={this.OnPasswordChange}
          />

          <TextInput
            id="confirm-password"
            placeholder="confirm password"
            type="password"
            onChange={this.OnPasswordChange}
          />
          <TextInput id="email" placeholder="email" type="email" />
        </form> */}
      </StyledModal>
    );
  }
}

export default connect()(Register);
