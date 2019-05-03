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

/**
 * Suppose the max height of a dialog should only occupy 80% of the screen view, and the header, message, control area have fixed height.
 * A dialog's layout, and sizing are demonstrated like this:
 * <StyledModal>               --- max-height: 80%
 *   <DialogHeaderDiv/>        --- height: --headerHeight
 *   <DialogContentDiv>        --- max-height: calc(100% - var(--headerHeight) - var(--controlHeight))
 *     <DialogInputDiv>        --- max-height: calc(100% - var(--messageHeight))
 *     <DialogMessageDiv>      --- max-height: --messageHeight
 *   </DialogContentDiv>
 *   <DialogControlDiv/>       --- height: --controlHeight
 * </StyledModal>
 */
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
    box-sizing: border-box;
    position: absolute;
    display: flex;
    flex-direction: column;
    overflow: auto;
    max-height: ${p => p.theme.dialog.maxHeightPer}%;
    max-width: ${p => p.theme.dialog.maxWidthPer}%;
    outline: none;
    background-color: ${p => p.theme.dialog.backgroundColor};
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
  flex-grow: 0;
  flex-shrink: 0;
  flex-basis: auto;
  box-sizing: border-box;
  height: ${p => p.theme.dialog.title.heightPx}px;
  margin: 0px;
  padding: ${p => p.theme.boxRadiusPx}px;
  background-color: ${p => p.theme.dialog.title.backgroundColor};
  color: ${p => p.theme.dialog.title.color};
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: ${p => p.theme.dialog.title.fontSizePx}px;
`;

const DialogContentDiv = styled.div`
  flex-grow: auto;
  flex-shrink: 1;
  flex-basis: auto;
  box-sizing: border-box;
  overflow: hidden;
  margin: 0px;
  padding: ${p => p.theme.outMostGapPx}px;
  /* Use flex for the input and message div */
  display: flex;
  flex-direction: column;
`;

const DialogInputDiv = styled.div.attrs({
  id: 'dialog-input-div'
})`
  /* Input div in the content div take the rest space other than message div. */
  flex-grow: auto;
  flex-shrink: 1;
  flex-basis: auto;
  box-sizing: border-box;
  overflow: auto;
  margin: 0px;
  padding: ${p => p.theme.outMostGapPx}px;
`;

const DialogMessageDiv = styled.div.attrs({
  id: 'dialog-message-div'
})`
  flex-grow: 0;
  flex-shrink: 0;
  flex-basis: auto;
  box-sizing: border-box;
  overflow: auto;
  max-height: ${p => p.theme.dialog.message.maxHeightPx}px;
  color: red;
  margin: 0px;
  padding: ${p => p.theme.outMostGapPx}px;
`;

const DialogControlDiv = styled.div`
  flex-grow: 0;
  flex-shrink: 0;
  box-sizing: border-box;
  height: ${p => p.theme.dialog.control.heightPx}px;
  margin: 0px;
  padding: ${p => p.theme.outMostGapPx}px;
  background-color: ${p => p.theme.dialog.control.backgroundColor};
  display: flex;
  flex-direction: row-reverse;
  align-items: center;
`;

export {
  StyledModal,
  DialogHeaderDiv,
  DialogContentDiv,
  DialogInputDiv,
  DialogMessageDiv,
  DialogControlDiv
};
