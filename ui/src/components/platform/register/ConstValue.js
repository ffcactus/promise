export const RegisterState = Object.freeze({
  INPUTTING: 'INPUTTING',
  REGISTERING: 'REGISTERING', // Register submitted and wait for response.
  SUCCESS: 'SUCCESS', // telling user to login now.
  ERROR: 'ERROR'
});

export const ActionType = Object.freeze({
  REGISTER_START: 'REGISTER_START',
  REGISTER_FAILURE: 'REGISTER_FAILURE',
  REGISTER_FAILURE_TIMEOUT: 'REGISTER_FAILURE_TIMEOUT',
  REGISTER_SUCCESS: 'REGISTER_SUCCESS'
});
