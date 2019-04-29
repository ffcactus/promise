import React from 'react';
import { storiesOf } from '@storybook/react';
import Wallpaper from '../../../components/platform/home/Wallpaper';
import {
  StyledModal,
  DialogHeaderDiv,
  DialogForm,
  DialogContentDiv,
  DialogMessageDiv,
  DialogControlDiv
} from '../../../components/platform/widgets/Dialog';
import Button from '../../../components/platform/widgets/Button';

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
          <DialogForm>
            <DialogContentDiv>
              <p>The dialog content.</p>
            </DialogContentDiv>
            <DialogMessageDiv />
            <DialogControlDiv>
              <section>
                <Button>Cancel</Button>
                <Button primary>Submit</Button>
              </section>
            </DialogControlDiv>
          </DialogForm>
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
          <DialogForm>
            <DialogContentDiv>
              <p>The dialog content.</p>
            </DialogContentDiv>
            <DialogMessageDiv>Some error happend.</DialogMessageDiv>
            <DialogControlDiv>
              <section>
                <Button>Cancel</Button>
                <Button primary>Submit</Button>
              </section>
            </DialogControlDiv>
          </DialogForm>
        </StyledModal>
      </Wallpaper>
    );
  })
  .add('oversized', () => {
    return (
      <Wallpaper>
        <StyledModal
          isOpen={true}
          shouldReturnFocusAfterClose={false}
          shouldCloseOnEsc={false}
        >
          <DialogHeaderDiv>Oversized Dialog</DialogHeaderDiv>
          <DialogForm>
            <DialogContentDiv>
              <p>
                This is the oversized dialog, please check if the width and
                height are suitable. This is the oversized dialog, please check
                if the width and height are suitable. This is the oversized
                dialog, please check if the width and height are suitable. This
                is the oversized dialog, please check if the width and height
                are suitable.{' '}
              </p>
              <p>
                This is the oversized dialog, please check if the width and
                height are suitable. This is the oversized dialog, please check
                if the width and height are suitable. This is the oversized
                dialog, please check if the width and height are suitable. This
                is the oversized dialog, please check if the width and height
                are suitable.{' '}
              </p>
              <p>
                This is the oversized dialog, please check if the width and
                height are suitable. This is the oversized dialog, please check
                if the width and height are suitable. This is the oversized
                dialog, please check if the width and height are suitable. This
                is the oversized dialog, please check if the width and height
                are suitable.{' '}
              </p>
              <p>
                This is the oversized dialog, please check if the width and
                height are suitable. This is the oversized dialog, please check
                if the width and height are suitable. This is the oversized
                dialog, please check if the width and height are suitable. This
                is the oversized dialog, please check if the width and height
                are suitable.{' '}
              </p>
              <p>
                This is the oversized dialog, please check if the width and
                height are suitable. This is the oversized dialog, please check
                if the width and height are suitable. This is the oversized
                dialog, please check if the width and height are suitable. This
                is the oversized dialog, please check if the width and height
                are suitable.{' '}
              </p>
              <p>
                This is the oversized dialog, please check if the width and
                height are suitable. This is the oversized dialog, please check
                if the width and height are suitable. This is the oversized
                dialog, please check if the width and height are suitable. This
                is the oversized dialog, please check if the width and height
                are suitable.{' '}
              </p>
              <p>
                This is the oversized dialog, please check if the width and
                height are suitable. This is the oversized dialog, please check
                if the width and height are suitable. This is the oversized
                dialog, please check if the width and height are suitable. This
                is the oversized dialog, please check if the width and height
                are suitable.{' '}
              </p>
              <p>
                This is the oversized dialog, please check if the width and
                height are suitable. This is the oversized dialog, please check
                if the width and height are suitable. This is the oversized
                dialog, please check if the width and height are suitable. This
                is the oversized dialog, please check if the width and height
                are suitable.{' '}
              </p>
              <p>
                This is the oversized dialog, please check if the width and
                height are suitable. This is the oversized dialog, please check
                if the width and height are suitable. This is the oversized
                dialog, please check if the width and height are suitable. This
                is the oversized dialog, please check if the width and height
                are suitable.{' '}
              </p>
              <p>
                This is the oversized dialog, please check if the width and
                height are suitable. This is the oversized dialog, please check
                if the width and height are suitable. This is the oversized
                dialog, please check if the width and height are suitable. This
                is the oversized dialog, please check if the width and height
                are suitable.{' '}
              </p>
              <p>
                This is the oversized dialog, please check if the width and
                height are suitable. This is the oversized dialog, please check
                if the width and height are suitable. This is the oversized
                dialog, please check if the width and height are suitable. This
                is the oversized dialog, please check if the width and height
                are suitable.{' '}
              </p>
              <p>
                This is the oversized dialog, please check if the width and
                height are suitable. This is the oversized dialog, please check
                if the width and height are suitable. This is the oversized
                dialog, please check if the width and height are suitable. This
                is the oversized dialog, please check if the width and height
                are suitable.{' '}
              </p>
              <p>
                This is the oversized dialog, please check if the width and
                height are suitable. This is the oversized dialog, please check
                if the width and height are suitable. This is the oversized
                dialog, please check if the width and height are suitable. This
                is the oversized dialog, please check if the width and height
                are suitable.{' '}
              </p>
              <p>
                This is the oversized dialog, please check if the width and
                height are suitable. This is the oversized dialog, please check
                if the width and height are suitable. This is the oversized
                dialog, please check if the width and height are suitable. This
                is the oversized dialog, please check if the width and height
                are suitable.{' '}
              </p>
              <p>
                This is the oversized dialog, please check if the width and
                height are suitable. This is the oversized dialog, please check
                if the width and height are suitable. This is the oversized
                dialog, please check if the width and height are suitable. This
                is the oversized dialog, please check if the width and height
                are suitable.{' '}
              </p>
              <p>
                This is the oversized dialog, please check if the width and
                height are suitable. This is the oversized dialog, please check
                if the width and height are suitable. This is the oversized
                dialog, please check if the width and height are suitable. This
                is the oversized dialog, please check if the width and height
                are suitable.{' '}
              </p>
            </DialogContentDiv>
            <DialogMessageDiv>
              Some error happend. Some error happend. Some error happend. Some
              error happend. Some error happend. Some error happend. Some error
              happend. Some error happend. Some error happend. Some error
              happend. Some error happend. Some error happend. Some error
              happend. Some error happend. Some error happend. Some error
              happend. Some error happend. Some error happend. Some error
              happend.
            </DialogMessageDiv>
            <DialogControlDiv>
              <section>
                <Button>Cancel</Button>
                <Button primary>Submit</Button>
              </section>
            </DialogControlDiv>
          </DialogForm>
        </StyledModal>
      </Wallpaper>
    );
  });
