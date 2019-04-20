import { ActionType } from './ConstValue';
import { push } from 'connected-react-router';
import axios from 'axios';

const LOGIN_FAILURE_WAIT_TIME = 3000;

/**
 * Login request start.
 * @param {string} username
 * @param {string} password
 */
function loginStart() {
  return {
    type: ActionType.LOGIN_START
  };
}

/**
 * Login failed.
 * @param {object} response - The json response from server.
 */
function loginFailure(response) {
  return {
    type: ActionType.LOGIN_FAILURE,
    info: response
  };
}

/**
 * Login failed, and waited enough time to try again.
 */
function loginFailureTimeout() {
  return {
    type: ActionType.LOGIN_FAILURE_TIMEOUT
  };
}

/**
 * Login success.
 * @param {String} hostname The hostname of the backend service.
 * @param {object} response
 */
function loginSuccess(hostname, response) {
  return {
    type: ActionType.LOGIN_SUCCESS,
    info: {
      hostname,
      response
    }
  };
}

/**
 * The async action of login. It will involve sync actions.
 * @param {string} username
 * @param {string} password
 * @param {string} afterLoginPath
 */
function login(hostname, port, username, password, from) {
  return dispatch => {
    dispatch(loginStart());
    axios
      .post(
        'api/v1/session/login',
        {
          username,
          password
        },
        {
          baseURL: 'http://' + hostname + ':' + port,
          timeout: 10000,
          responseType: 'json',
          validateStatus: status => status === 204
        }
      )
      .then(response => {
        // dispatch(WsAction.createWsConnection(hostname));
        // TODO
        // We dispatch login success only on ws connection created.
        dispatch(loginSuccess(hostname, response));
        // TODO
        // Is it good to do redirection in action?
        // browserHistory.push(afterLoginPath);
        // dispatch(DesktopAction.setAppCollection());
        dispatch(push(from));
        return;
      })
      .catch(response => {
        setTimeout(() => {
          dispatch(loginFailureTimeout());
        }, LOGIN_FAILURE_WAIT_TIME);
        dispatch(loginFailure(response));
      });
  };
}

export { login };
