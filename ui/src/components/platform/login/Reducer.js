import { ActionType, LoginState } from './ConstValue';

const defaultSessionState = {
  hostname:
    process.env.NODE_ENV === 'development'
      ? window.location.hostname + ':3000'
      : window.location.hostname,
  state: LoginState.LOGOUT,
  token: null,
  loginFailureInfo: null
};

const session = (state = defaultSessionState, action) => {
  switch (action.type) {
    case ActionType.LOGIN_START:
      return {
        ...state,
        state: LoginState.LOGGING,
        token: null
      };
    case ActionType.LOGIN_SUCCESS:
      return {
        ...state,
        state: LoginState.LOGGED,
        hostname: action.info.hostname,
        token: action.info.response.headers['authorization']
      };
    case ActionType.LOGIN_FAILURE:
      return {
        ...state,
        state: LoginState.LOGIN_FAILURE_WAIT,
        token: null,
        loginFailureInfo: action.info
      };
    case ActionType.LOGIN_FAILURE_TIMEOUT:
      return {
        ...state,
        state: LoginState.LOGOUT,
        token: null
      };
    case ActionType.LOGOUT_START:
      return state;
    case ActionType.LOGOUT_SUCCESS:
      return defaultSessionState;
    case ActionType.LOGOUT_FAILURE:
      return state;
    default:
      return state;
  }
};

export default session;
