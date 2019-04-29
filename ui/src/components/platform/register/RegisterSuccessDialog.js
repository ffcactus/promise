import React from 'react';
import { connect } from 'react-redux';
import styled from 'styled-components';
import ReactModal from 'react-modal';
import { Link } from 'react-router-dom';
import { onSuccessConfirm } from './Action';

import {
  StyledModal,
  DialogHeaderDiv,
  DialogContentDiv,
  DialogControlDiv
} from '../widgets/Dialog';
import Button from '../widgets/Button';

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

class RegisterSuccessDialog extends React.Component {
  render() {
    ReactModal.setAppElement('#root');
    return (
      <StyledModal
        isOpen={true}
        shouldReturnFocusAfterClose={false}
        shouldCloseOnEsc={false}
        contentLabel="Your account is created"
      >
        <DialogHeaderDiv>Register to Promise</DialogHeaderDiv>
        <StyledDialogContentDiv>
          <section>
            <p>
              Your account is created, you can login to Promise from login page
              now.
            </p>
          </section>
        </StyledDialogContentDiv>
        <DialogControlDiv>
          <section>
            <Button
              as={Link}
              to="/login"
              primary
              onClick={this.props.dispatch(onSuccessConfirm)}
            >
              OK
            </Button>
          </section>
        </DialogControlDiv>
      </StyledModal>
    );
  }
}

export default connect()(RegisterSuccessDialog);
