import React from 'react';
import ReactModal from 'react-modal';
import styled from 'styled-components';

function ReactModalAdapter({ className, modalClassName, ...props }) {
  ReactModal.setAppElement('#root');
  return (
    <ReactModal
      className={modalClassName}
      portalClassName={className}
      {...props}
    />
  );
}

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
    max-height: ${p => p.theme.dialog.maxContentHeightPer}%;
    max-width: ${p => p.theme.dialog.maxContentWidthPer}%;
    outline: none;
    background-repeat: no-repeat;
    background-image: linear-gradient(
        to right,
        ${p => p.theme.dialog.title.backgroundColor},
        ${p => p.theme.dialog.title.backgroundColor}
      ),
      linear-gradient(
        to right,
        ${p => p.theme.dialog.title.color},
        ${p => p.theme.dialog.title.color}
      );
    background-size: 100% ${p => p.theme.dialog.title.heightPx}px, 100%;
    border: 0px;
    border-radius: ${p => p.theme.boxRadiusPx}px;
    font-size: 17px;
    text-rendering: optimizeLegibility;
    animation: fade-in 500ms ease-in-out;
    font-weight: normal;
    font-family: 'SF Pro Text', 'SF Pro Icons', 'Helvetica Neue', 'Helvetica',
      'Arial', sans-serif;
  }
`;

const DialogHeaderDiv = styled.div`
  text-align: center;
  max-height: ${p => p.theme.dialog.title.heightPx - 2 * p.theme.boxRadiusPx}px;
  margin: 0px;
  padding: ${p => p.theme.boxRadiusPx}px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: silver;
  font-size: ${p => p.theme.dialog.title.fontSizePx}px;
`;

const DialogForm = styled.form``;

const DialogContentDiv = styled.div`
  min-height: 0px;
  max-height: 80%;
  overflow: auto;
  margin: 0px;
  padding: ${p => p.theme.outMostGapPx}px;
`;

const DialogMessageDiv = styled.div`
  max-height: 10%;
  min-height: 10%;
  color: red;
  overflow: auto;
  margin: 0px;
  padding: ${p => p.theme.outMostGapPx}px;
`;

const DialogControlDiv = styled.div`
  max-height: 10%;
  display: flex;
  flex-direction: row-reverse;
  align-items: center;
  margin: 0px;
  padding: ${p => p.theme.outMostGapPx}px;
`;

export {
  StyledModal,
  DialogForm,
  DialogHeaderDiv,
  DialogContentDiv,
  DialogMessageDiv,
  DialogControlDiv
};
