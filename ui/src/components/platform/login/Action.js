import { ActionType } from './ConstValue';
import { push } from 'connected-react-router';
import axios from 'axios';

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
        dispatch(loginFailure(operation.response));
      });
  };
}

export { login };
