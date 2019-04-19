import { ActionType } from './ConstValue';
import { push } from 'connected-react-router';
import { doPost } from '../utils/Client';

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
 * @param {object} info - The json response from server.
 */
function loginFailure(info) {
  return {
    type: ActionType.LOGIN_FAILURE,
    info
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
 * @param {string} token
 */
function loginSuccess(hostname, username, token) {
  return {
    type: ActionType.LOGIN_SUCCESS,
    info: {
      hostname,
      username,
      token
    }
  };
}

/**
 * The async action of login. It will involve sync actions.
 * @param {string} username
 * @param {string} password
 * @param {string} afterLoginPath
 */
function login(hostname, username, password, from) {
  return dispatch => {
    dispatch(loginStart());
    doPost('http://' + hostname + '/api/v1/session/login', {
      username,
      password
    })
      .then(response => {
        if (response.status === 200) {
          // dispatch(WsAction.createWsConnection(hostname));
          // TODO
          // We dispatch login success only on ws connection created.
          dispatch(loginSuccess(hostname, username, response.response.token));
          // TODO
          // Is it good to do redirection in action?
          // browserHistory.push(afterLoginPath);
          // dispatch(DesktopAction.setAppCollection());
          dispatch(push(from));
          return;
        }
        setTimeout(() => {
          dispatch(loginFailureTimeout());
        }, LOGIN_FAILURE_WAIT_TIME);
        dispatch(
          loginFailure(
            Array.isArray(response.response)
              ? response.response[0]
              : response.response
          )
        );
      })
      .catch(reason => {
        setTimeout(() => {
          dispatch(loginFailureTimeout());
        }, LOGIN_FAILURE_WAIT_TIME);
        dispatch(loginFailure(reason.message));
      });
  };
}

export { login };
