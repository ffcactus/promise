export const RegisterState = Object.freeze({
  REGISTERING: 'REGISTERING', // Register submitted and wait for response.
  SUCCESS: 'SUCCESS' // telling user to login now.
});

export const ActionType = Object.freeze({
  REGISTER_CANCEL: 'REGISTER_CANCEL',
  REGISTER_START: 'REGISTER_START',
  REGISTER_FAILURE: 'REGISTER_FAILURE',
  REGISTER_SUCCESS: 'REGISTER_SUCCESS',
  REGISTER_SUCCESS_CONFIRM: 'REGISTER_SUCCESS_CONFIRM'
});
