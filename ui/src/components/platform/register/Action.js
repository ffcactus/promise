import { ActionType } from './ConstValue';
import axios from 'axios';

const FAILURE_WAIT_TIME = 3000;

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
 * Register process timeout.
 */
function registerFailureTimeout() {
  return {
    type: ActionType.REGISTER_FAILURE_TIMEOUT
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
      .then(response => {
        dispatch(registerSuccess());
        return;
      })
      .catch(response => {
        setTimeout(() => {
          dispatch(registerFailureTimeout());
        }, FAILURE_WAIT_TIME);
        dispatch(registerFailure(response));
      });
  };
}

export { register };
