import { ActionType } from './ConstValue';
import { push } from 'connected-react-router';
import axios from 'axios';

/**
 * Register process started, the submit button should be disabled.
 */
function registerStart() {
  return {
    type: ActionType.REGISTER_START
  };
}

/**
 * Register failed.
 * @param {object} response - The json response from server.
 */
function registerFailure(response) {
  return {
    type: ActionType.REGISTER_FAILURE,
    info: response
  };
}

/**
 * Register process success.
 */
function registerSuccess() {
  return {
    type: ActionType.REGISTER_SUCCESS
  };
}

/**
 * When the user press the confirm button when telling him the account created.
 */
function onSuccessConfirm() {
  return {
    type: ActionType.REGISTER_SUCCESS_CONFIRM
  };
}

/**
 * When cancel the registration.
 */
function onCancel(from) {
  return dispatch => {
    dispatch({
      type: ActionType.REGISTER_CANCEL
    });
    dispatch(push(from));
  };
}

/**
 * The register action after user precess submit button.
 * @param {object} param0 The register information.
 */
function register({ username, password, email }) {
  return (dispatch, getStore) => {
    dispatch(registerStart());
    console.info(getStore());
    axios
      .post(
        'api/v1/users',
        {
          username,
          password,
          email
        },
        {
          baseURL: 'http://' + getStore().session.hostname,
          timeout: 10000,
          responseType: 'json',
          validateStatus: status => status === 201
        }
      )
      .then(operation => {
        dispatch(registerSuccess());
        return;
      })
      .catch(operation => {
        if (operation.code === 'ECONNABORTED') {
          // This indicate the axios request timeout, we need mock the error message.
          operation.response = {
            status: 408,
            statusText: 'Request Timeout',
            message: 'Request timeout.'
          };
        }
        if (operation.response === undefined) {
          // For any unknown error from axios, we mock error.
          operation.response = {
            status: 500,
            statusText: 'Internal error',
            message: 'Internal request error in web.'
          };
        }
        dispatch(registerFailure(operation.response));
      });
  };
}

export { register, onSuccessConfirm, onCancel };
