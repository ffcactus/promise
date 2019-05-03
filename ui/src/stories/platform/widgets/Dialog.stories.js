import React from 'react';
import { storiesOf } from '@storybook/react';
import Wallpaper from '../../../components/platform/home/Wallpaper';
import {
  StyledModal,
  DialogHeaderDiv,
  DialogContentDiv,
  DialogMessageDiv,
  DialogControlDiv,
  DialogInputDiv
} from '../../../components/platform/widgets/Dialog';
import Button from '../../../components/platform/widgets/Button';

const inputText = 'This is the input text. ';
const messageText = 'This is the message. ';
let oversizedInputTest = '';
let oversizedMessageText = '';

for (let i = 0; i < 1000; i++) {
  oversizedInputTest += inputText;
  oversizedMessageText += messageText;
}

storiesOf('Platform/widgets/Dialog', module)
  .add('default', () => {
    return (
      <Wallpaper>
        <StyledModal
          isOpen={true}
          shouldReturnFocusAfterClose={false}
          shouldCloseOnEsc={false}
        >
          <DialogHeaderDiv>Default Dialog</DialogHeaderDiv>
          <DialogContentDiv>
            <DialogInputDiv>
              <p>The dialog content.</p>
            </DialogInputDiv>
            <DialogMessageDiv />
          </DialogContentDiv>
          <DialogControlDiv>
            <section>
              <Button>Cancel</Button>
              <Button primary>Submit</Button>
            </section>
          </DialogControlDiv>
        </StyledModal>
      </Wallpaper>
    );
  })
  .add('withError', () => {
    return (
      <Wallpaper>
        <StyledModal
          isOpen={true}
          shouldReturnFocusAfterClose={false}
          shouldCloseOnEsc={false}
        >
          <DialogHeaderDiv>Default Dialog</DialogHeaderDiv>
          <DialogContentDiv>
            <DialogInputDiv>
              <p>The dialog content.</p>
            </DialogInputDiv>
            <DialogMessageDiv>Some error happend.</DialogMessageDiv>
          </DialogContentDiv>
          <DialogControlDiv>
            <section>
              <Button>Cancel</Button>
              <Button primary>Submit</Button>
            </section>
          </DialogControlDiv>
        </StyledModal>
      </Wallpaper>
    );
  })
  .add('oversized-input', () => {
    return (
      <Wallpaper>
        <StyledModal
          isOpen={true}
          shouldReturnFocusAfterClose={false}
          shouldCloseOnEsc={false}
        >
          <DialogHeaderDiv>Oversized Dialog</DialogHeaderDiv>
          <DialogContentDiv>
            <DialogInputDiv>
              <span>{oversizedInputTest}</span>
            </DialogInputDiv>
            <DialogMessageDiv>
              <span>{messageText}</span>
            </DialogMessageDiv>
          </DialogContentDiv>
          <DialogControlDiv>
            <section>
              <Button>Cancel</Button>
              <Button primary>Submit</Button>
            </section>
          </DialogControlDiv>
        </StyledModal>
      </Wallpaper>
    );
  })
  .add('oversized-message', () => {
    return (
      <Wallpaper>
        <StyledModal
          isOpen={true}
          shouldReturnFocusAfterClose={false}
          shouldCloseOnEsc={false}
        >
          <DialogHeaderDiv>Oversized Dialog</DialogHeaderDiv>
          <DialogContentDiv>
            <DialogInputDiv>
              <span>{inputText}</span>
            </DialogInputDiv>
            <DialogMessageDiv>
              <span>{oversizedMessageText}</span>
            </DialogMessageDiv>
          </DialogContentDiv>
          <DialogControlDiv>
            <section>
              <Button>Cancel</Button>
              <Button primary>Submit</Button>
            </section>
          </DialogControlDiv>
        </StyledModal>
      </Wallpaper>
    );
  })
  .add('oversized-input-message', () => {
    return (
      <Wallpaper>
        <StyledModal
          isOpen={true}
          shouldReturnFocusAfterClose={false}
          shouldCloseOnEsc={false}
        >
          <DialogHeaderDiv>Oversized Dialog</DialogHeaderDiv>
          <DialogContentDiv>
            <DialogInputDiv>
              <span>{oversizedInputTest}</span>
            </DialogInputDiv>
            <DialogMessageDiv>
              <span>{oversizedMessageText}</span>
            </DialogMessageDiv>
          </DialogContentDiv>
          <DialogControlDiv>
            <section>
              <Button>Cancel</Button>
              <Button primary>Submit</Button>
            </section>
          </DialogControlDiv>
        </StyledModal>
      </Wallpaper>
    );
  });
